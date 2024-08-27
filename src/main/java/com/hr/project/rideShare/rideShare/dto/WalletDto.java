package com.hr.project.rideShare.rideShare.dto;

import com.hr.project.rideShare.rideShare.entities.User;
import com.hr.project.rideShare.rideShare.entities.WalletTransaction;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {

    private Long id;

    private UserDto user;

    private Double balance;

    private List<WalletTransactionDto> transactions;

}
