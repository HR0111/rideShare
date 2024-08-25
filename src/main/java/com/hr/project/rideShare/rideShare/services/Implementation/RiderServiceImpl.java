package com.hr.project.rideShare.rideShare.services.Implementation;

import com.hr.project.rideShare.rideShare.dto.DriverDto;
import com.hr.project.rideShare.rideShare.dto.RideDto;
import com.hr.project.rideShare.rideShare.dto.RideRequestDto;
import com.hr.project.rideShare.rideShare.dto.RiderDto;
import com.hr.project.rideShare.rideShare.entities.*;
import com.hr.project.rideShare.rideShare.enums.RideRequestStatus;
import com.hr.project.rideShare.rideShare.enums.RideStatus;
import com.hr.project.rideShare.rideShare.exceptions.ResourceNotFoundException;
import com.hr.project.rideShare.rideShare.repositories.DriverRepository;
import com.hr.project.rideShare.rideShare.repositories.RideRequestRepository;
import com.hr.project.rideShare.rideShare.repositories.RiderRepository;
import com.hr.project.rideShare.rideShare.services.DriverService;
import com.hr.project.rideShare.rideShare.services.RideService;
import com.hr.project.rideShare.rideShare.services.RiderService;
import com.hr.project.rideShare.rideShare.stratergies.RideStratergyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStratergyManager rideStratergyManager;
    private final RideService rideService;
    private final DriverService driverService;
    private final RiderRepository riderRepository;
    private final DriverRepository driverRepository;
    private final RideRequestRepository rideRequestRepository;

    @Transactional
    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        Rider rider = getcurrentRider();
        // Here converting DTO to entity with the help of modelMapper
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare = rideStratergyManager.fareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers = rideStratergyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);

        // TODO now send notification to all the drivers about the request

        return  modelMapper.map(savedRideRequest , RideRequestDto.class);

//        log.info(rideRequest.toString());

    }

    @Override
    public RideDto cancelRide(Long rideId) {

        Rider rider = getcurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider cannot cancel ride because its not the same rider who started the ride"+rideId);

        }

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("RIde cannot cancel ride"+ride.getRideStatus());

        }

        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(), true);




        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {


        Ride ride = rideService.getRideById(rideId);
        if(ride == null){
            throw new RuntimeException("Ride not found with id: " + rideId);
        }

        Driver driver = driverService.getCurrentDriver();

        Double driverRating = driver.getRating();

        Double newRating  = (driverRating == 0) ? rating.doubleValue() : (rating+driverRating) / 2;


        // Update the driver's rating
        driver.setRating(newRating);

        // Save the updated driver
        driverRepository.save(driver);

        return modelMapper.map(driver , DriverDto.class);
    }

    @Override
    public RiderDto getMyProfile() {

        Rider currentrider = getcurrentRider();
        return modelMapper.map(currentrider,RiderDto.class);

    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {

        Rider currentrider = getcurrentRider();
        return rideService.getAllRidesOfRider(currentrider.getId(),pageRequest).map(
                ride -> modelMapper.map(ride , RideDto.class)
        );


    }

    @Override
    public Rider createNewRider(User user) {

        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);


    }

    @Override
    public Rider getcurrentRider() {
    //
        return riderRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException(" Rider not found with id"+1));
    }
}
