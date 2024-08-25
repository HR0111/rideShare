package com.hr.project.rideShare.rideShare.stratergies;


import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.RideRequest;

import java.util.List;


public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);


}
