package com.hr.project.rideShare.rideShare.services;

import com.hr.project.rideShare.rideShare.dto.RideRequestDto;
import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.entities.RideRequest;
import com.hr.project.rideShare.rideShare.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);
    void matchWithDriver(RideRequestDto rideRequestDto);
    Ride createNewRide(RideRequest rideRequest , Driver driver);
    Ride updateRideStatus(Ride ride , RideStatus rideStatus);
    Page<Ride> getAllRidesOfRider(Long riderId , PageRequest pageRequest);
    Page<Ride> getAllRidesOfDriver(Long driverId , PageRequest pageRequest);


}
