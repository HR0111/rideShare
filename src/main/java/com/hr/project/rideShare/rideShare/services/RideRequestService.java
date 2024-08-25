package com.hr.project.rideShare.rideShare.services;


import com.hr.project.rideShare.rideShare.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideById(Long rideRequestId);


    void update(RideRequest rideRequest);
}
