package com.hr.project.rideShare.rideShare.stratergies;


import com.hr.project.rideShare.rideShare.entities.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;
    double calculateFare(RideRequest rideRequest);

}
