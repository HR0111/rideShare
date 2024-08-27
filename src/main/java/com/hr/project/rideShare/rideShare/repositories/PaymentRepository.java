package com.hr.project.rideShare.rideShare.repositories;

import com.hr.project.rideShare.rideShare.entities.Payment;
import com.hr.project.rideShare.rideShare.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment , Long> {
   Optional<Payment> findByRide(Ride ride);
}
