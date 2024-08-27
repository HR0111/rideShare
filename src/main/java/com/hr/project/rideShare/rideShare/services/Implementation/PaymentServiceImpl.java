package com.hr.project.rideShare.rideShare.services.Implementation;

import com.hr.project.rideShare.rideShare.entities.Payment;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.enums.PaymentStatus;
import com.hr.project.rideShare.rideShare.exceptions.ResourceNotFoundException;
import com.hr.project.rideShare.rideShare.repositories.PaymentRepository;
import com.hr.project.rideShare.rideShare.services.PaymentService;
import com.hr.project.rideShare.rideShare.stratergies.PaymentStratergy;
import com.hr.project.rideShare.rideShare.stratergies.PaymentStratergyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStratergyManager paymentStratergyManager;
    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride)
                        .orElseThrow(()-> new ResourceNotFoundException("Payment not found for ride with id"
                        +ride.getId()));
        paymentStratergyManager.paymentStratergy(payment.getPaymentMethod()).processPayment(payment);


    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder().ride(ride).paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare()).paymentStatus(PaymentStatus.PENDING).build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
