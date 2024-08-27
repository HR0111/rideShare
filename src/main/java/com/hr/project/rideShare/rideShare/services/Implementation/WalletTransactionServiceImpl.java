package com.hr.project.rideShare.rideShare.services.Implementation;

import com.hr.project.rideShare.rideShare.dto.WalletTransactionDto;
import com.hr.project.rideShare.rideShare.entities.User;
import com.hr.project.rideShare.rideShare.entities.Wallet;
import com.hr.project.rideShare.rideShare.entities.WalletTransaction;
import com.hr.project.rideShare.rideShare.repositories.WalletTransactionRepository;
import com.hr.project.rideShare.rideShare.services.WalletTransactionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {

        walletTransactionRepository.save(walletTransaction);

    }


}
