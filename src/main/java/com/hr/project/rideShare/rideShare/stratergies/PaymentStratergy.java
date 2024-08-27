package com.hr.project.rideShare.rideShare.stratergies;

import com.hr.project.rideShare.rideShare.entities.Payment;

public interface PaymentStratergy {

    void processPayment(Payment payment);

    static final Double PLATFORM_COMMISSION = 0.3;


}
