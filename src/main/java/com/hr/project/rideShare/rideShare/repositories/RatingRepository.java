package com.hr.project.rideShare.rideShare.repositories;

import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.Rating;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating , Long> {

    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);


    Optional<Rating> findByRide(Ride ride);
}
