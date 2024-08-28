package com.hr.project.rideShare.rideShare.dto;


import com.hr.project.rideShare.rideShare.enums.PaymentMethod;
import com.hr.project.rideShare.rideShare.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDto {


    private Long id;

    private PointDto pickUpLocation;

    private PointDto dropUpLocation;

    private LocalDateTime createdTime;

    private RiderDto rider;

    private DriverDto driver;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private String otp;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;


}
