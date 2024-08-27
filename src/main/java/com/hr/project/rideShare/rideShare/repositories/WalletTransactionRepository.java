package com.hr.project.rideShare.rideShare.repositories;

import com.hr.project.rideShare.rideShare.entities.Wallet;
import com.hr.project.rideShare.rideShare.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {


}
