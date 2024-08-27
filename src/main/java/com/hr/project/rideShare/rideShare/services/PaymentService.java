package com.hr.project.rideShare.rideShare.services;


import com.hr.project.rideShare.rideShare.entities.Payment;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.enums.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment , PaymentStatus status);

}
