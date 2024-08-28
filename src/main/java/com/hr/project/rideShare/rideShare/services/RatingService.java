package com.hr.project.rideShare.rideShare.services;


import com.hr.project.rideShare.rideShare.dto.DriverDto;
import com.hr.project.rideShare.rideShare.dto.RiderDto;
import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.entities.Rider;

public interface RatingService {

    DriverDto rateDriver(Ride ride , Integer rating);
    RiderDto rateRider(Ride ride , Integer rating);

    void createNewRating(Ride ride);

}
