package com.hr.project.rideShare.rideShare.dto;


import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.entities.Wallet;
import com.hr.project.rideShare.rideShare.enums.TransactionMethod;
import com.hr.project.rideShare.rideShare.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder

public class WalletTransactionDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private Ride ride;

    private String transactionId;

    private WalletDto wallet;

    private LocalDateTime timeStamp;
}
