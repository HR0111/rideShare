package com.hr.project.rideShare.rideShare.stratergies;


import com.hr.project.rideShare.rideShare.enums.PaymentMethod;
import com.hr.project.rideShare.rideShare.services.WalletService;
import com.hr.project.rideShare.rideShare.stratergies.impl.CODPaymentStratergy;
import com.hr.project.rideShare.rideShare.stratergies.impl.WalletPaymentStratergy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStratergyManager {

    private final WalletPaymentStratergy walletPaymentStratergy;
    private final CODPaymentStratergy codPaymentStratergy;


    public PaymentStratergy paymentStratergy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStratergy;
            case CASH -> codPaymentStratergy;
            //default -> throw new RuntimeException("Invalid payment Method");
        };
    }


}
