package com.hr.project.rideShare.rideShare.stratergies.impl;

import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.Payment;
import com.hr.project.rideShare.rideShare.entities.Wallet;
import com.hr.project.rideShare.rideShare.enums.PaymentStatus;
import com.hr.project.rideShare.rideShare.enums.TransactionMethod;
import com.hr.project.rideShare.rideShare.repositories.PaymentRepository;
import com.hr.project.rideShare.rideShare.services.PaymentService;
import com.hr.project.rideShare.rideShare.services.WalletService;
import com.hr.project.rideShare.rideShare.stratergies.PaymentStratergy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CODPaymentStratergy implements PaymentStratergy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();

       // Wallet driverWallet = walletService.findByUser(driver.getUser());

         double platFormCommission = payment.getAmount()*PLATFORM_COMMISSION;

         walletService.deductMoneyFromWallet(driver.getUser(), platFormCommission , null , payment.getRide() , TransactionMethod.RIDE);

//         paymentService.updatePaymentStatus(payment , PaymentStatus.CONFIRMED);//
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
