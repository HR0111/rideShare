package com.hr.project.rideShare.rideShare.services.Implementation;

import com.hr.project.rideShare.rideShare.dto.DriverDto;
import com.hr.project.rideShare.rideShare.dto.RideDto;
import com.hr.project.rideShare.rideShare.dto.RideRequestDto;
import com.hr.project.rideShare.rideShare.dto.RiderDto;
import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.RideRequest;
import com.hr.project.rideShare.rideShare.entities.Rider;
import com.hr.project.rideShare.rideShare.entities.User;
import com.hr.project.rideShare.rideShare.enums.RideRequestStatus;
import com.hr.project.rideShare.rideShare.exceptions.ResourceNotFoundException;
import com.hr.project.rideShare.rideShare.repositories.RideRequestRepository;
import com.hr.project.rideShare.rideShare.repositories.RiderRepository;
import com.hr.project.rideShare.rideShare.services.RiderService;
import com.hr.project.rideShare.rideShare.stratergies.RideStratergyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStratergyManager rideStratergyManager;
    private final RiderRepository riderRepository;
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
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
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
