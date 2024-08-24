package com.hr.project.rideShare.rideShare.repositories;

import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.services.RideService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface RideRepository extends JpaRepository<Ride , Long> {
}
