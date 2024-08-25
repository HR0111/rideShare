package com.hr.project.rideShare.rideShare.services;

import com.hr.project.rideShare.rideShare.dto.DriverDto;
import com.hr.project.rideShare.rideShare.dto.RideDto;
import com.hr.project.rideShare.rideShare.dto.RideRequestDto;
import com.hr.project.rideShare.rideShare.dto.RiderDto;
import com.hr.project.rideShare.rideShare.entities.Rider;
import com.hr.project.rideShare.rideShare.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);


    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId , Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getcurrentRider( );

}
