package com.hr.project.rideShare.rideShare.services.Implementation;

import com.hr.project.rideShare.rideShare.dto.DriverDto;
import com.hr.project.rideShare.rideShare.dto.DriverRideDto;
import com.hr.project.rideShare.rideShare.dto.RideDto;
import com.hr.project.rideShare.rideShare.dto.RiderDto;
import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.entities.RideRequest;
import com.hr.project.rideShare.rideShare.entities.User;
import com.hr.project.rideShare.rideShare.enums.RideRequestStatus;
import com.hr.project.rideShare.rideShare.enums.RideStatus;
import com.hr.project.rideShare.rideShare.exceptions.ResourceNotFoundException;
import com.hr.project.rideShare.rideShare.repositories.DriverRepository;
import com.hr.project.rideShare.rideShare.services.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Provider;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final RatingService ratingService;

    @Override
    public DriverRideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideById(rideRequestId);

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride Request Cannot be accepted" + rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();

        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }

//        currentDriver.setAvailable(false);
//        Driver saveddriver = driverRepository.save(currentDriver);
        Driver saveddriver = updateDriverAvailability(currentDriver , false);

        Ride ride =  rideService.createNewRide(rideRequest , saveddriver);

        return modelMapper.map(ride , DriverRideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);

        Driver driver = getCurrentDriver();

        if(!ride.getDriver().equals(driver)){
            throw new RuntimeException("Driver cannot cancel ride because its not the same driver who started the ride"+driver);

        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Driver cannot cancel ride because its not started the ride yet"+driver);

        }

        rideService.updateRideStatus(ride,RideStatus.CANCELLED);
//        driver.setAvailable(true);
//        driverRepository.save(driver);
             updateDriverAvailability(driver , true);

        return modelMapper.map(ride , RideDto.class);

    }

    @Override
    public DriverRideDto startRide(Long rideId , String otp) {

        Ride ride =rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!ride.getDriver().equals(driver)){
            throw new RuntimeException("Driver cannot start a ride as he  has not accpeted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Driver status is not confirmed hence cannot be started , status: "+ride.getRideStatus());
        }

        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("Otp is not valid"+otp);
        }


        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride , RideStatus.ONGOING);

        paymentService.createNewPayment(savedRide);
        ratingService.createNewRating(savedRide);

        return modelMapper.map(savedRide , DriverRideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {

        Ride ride =rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!ride.getDriver().equals(driver)){
            throw new RuntimeException("Driver cannot start a ride as he  has not accpeted it earlier");
        }

        if(!ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Driver status is not OnGoing hence cannot be ended , status: "+ride.getRideStatus());
        }

        ride.setEndedAt(LocalDateTime.now());
        Ride savedride =  rideService.updateRideStatus(ride, RideStatus.ENDED);

        updateDriverAvailability(driver , true);
        paymentService.processPayment(ride);

        return modelMapper.map(savedride , RideDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!driver.equals(driver)){
            throw new RuntimeException("Driver is not the owner of this Ride");
        }

        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Driver status is not Ended hence cannot be start rating , status: "+ride.getRideStatus());
        }

        return  ratingService.rateRider(ride,rating);
    }

    @Override
    public DriverDto getMyProfile() {

        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver,DriverDto.class);

    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {

        Driver currentdriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentdriver,pageRequest).map(
                ride -> modelMapper.map(ride , RideDto.class)
        );

    }

    @Override
    public Driver getCurrentDriver() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return driverRepository.findByUser(user).orElseThrow(()->
                new ResourceNotFoundException("Current Driver not associated with user with id "+user.getId()));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {

//        Driver driver = driverRepository.findById(driverId).orElseThrow(()->new ResourceNotFoundException("Driver not found with id"+driverId));
        driver.setAvailable(available);
        return driverRepository.save(driver);

    }

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
