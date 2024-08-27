package com.hr.project.rideShare.rideShare.services.Implementation;


import com.hr.project.rideShare.rideShare.dto.WalletTransactionDto;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.entities.User;
import com.hr.project.rideShare.rideShare.entities.Wallet;
import com.hr.project.rideShare.rideShare.entities.WalletTransaction;
import com.hr.project.rideShare.rideShare.enums.TransactionMethod;
import com.hr.project.rideShare.rideShare.enums.TransactionType;
import com.hr.project.rideShare.rideShare.exceptions.ResourceNotFoundException;
import com.hr.project.rideShare.rideShare.repositories.WalletRepository;
import com.hr.project.rideShare.rideShare.services.WalletService;
import com.hr.project.rideShare.rideShare.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId , Ride ride , TransactionMethod trasactionMethod) {

//        Wallet w = walletRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Wallet not found for user with id"+user.getId()));
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId).ride(ride).wallet(wallet).transactionType(TransactionType.CREDIT)
                .transactionMethod(trasactionMethod).amount(amount).build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);

    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId , Ride ride , TransactionMethod trasactionMethod) {

        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance()-amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId).ride(ride).wallet(wallet).transactionType(TransactionType.DEBIT)
                .transactionMethod(trasactionMethod).amount(amount).build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);

    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {



    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(()->new ResourceNotFoundException("Wallet not found with id"+walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
         return walletRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Wallet not found for user with id"+user.getId()));
    }
}
