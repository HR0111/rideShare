package com.hr.project.rideShare.rideShare.repositories;

import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.User;
import com.hr.project.rideShare.rideShare.entities.Wallet;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {


    Optional<Wallet> findByUser(User user);
}
