package com.hr.project.rideShare.rideShare.dto;

import com.hr.project.rideShare.rideShare.enums.PaymentMethod;
import com.hr.project.rideShare.rideShare.enums.RideStatus;

import java.time.LocalDateTime;

public class DriverRideDto {

    private Long id;

    private PointDto pickUpLocation;

    private PointDto dropUpLocation;

    private LocalDateTime createdTime;

    private RiderDto rider;

    private DriverDto driver;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

}
