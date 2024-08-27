package com.hr.project.rideShare.rideShare.services;


import com.hr.project.rideShare.rideShare.dto.WalletTransactionDto;
import com.hr.project.rideShare.rideShare.entities.User;
import com.hr.project.rideShare.rideShare.entities.Wallet;
import com.hr.project.rideShare.rideShare.entities.WalletTransaction;
import org.springframework.stereotype.Service;

@Service
public interface WalletTransactionService {


    void createNewWalletTransaction(WalletTransaction walletTransaction);



}
