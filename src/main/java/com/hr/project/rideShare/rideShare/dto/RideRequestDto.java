package com.hr.project.rideShare.rideShare.dto;


import com.hr.project.rideShare.rideShare.enums.PaymentMethod;
import com.hr.project.rideShare.rideShare.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private Long id;

    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDateTime requestedTime;

    private RiderDto rider;
    private Double fare;
    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;





}
