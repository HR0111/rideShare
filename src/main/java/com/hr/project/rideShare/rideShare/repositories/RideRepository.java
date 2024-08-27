package com.hr.project.rideShare.rideShare.repositories;

import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface RideRepository extends JpaRepository<Ride , Long> {
    Page<Ride> findByRider(Rider rider, Pageable pageRequest);

    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);
}
