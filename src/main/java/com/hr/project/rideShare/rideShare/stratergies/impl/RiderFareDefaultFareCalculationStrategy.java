package com.hr.project.rideShare.rideShare.stratergies.impl;


import com.hr.project.rideShare.rideShare.entities.RideRequest;
import com.hr.project.rideShare.rideShare.services.DistanceService;
import com.hr.project.rideShare.rideShare.stratergies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RiderFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {


    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation() , rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER;
    }
}
