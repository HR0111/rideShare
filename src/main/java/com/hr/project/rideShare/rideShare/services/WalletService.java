package com.hr.project.rideShare.rideShare.services;


import com.hr.project.rideShare.rideShare.entities.Payment;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.entities.User;
import com.hr.project.rideShare.rideShare.entities.Wallet;
import com.hr.project.rideShare.rideShare.enums.TransactionMethod;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId , Ride ride , TransactionMethod trasactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId , Ride ride , TransactionMethod trasactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);



}
