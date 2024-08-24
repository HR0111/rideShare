package com.hr.project.rideShare.rideShare.repositories;

import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    //ST_DISTANCE(point1 , point2);
    //ST_DWITHIN(point1 , 1000)

    @Query(value = "SELECT d.* , ST_DISTANCE(D.current_location, :pickupLocation ) AS distance " +
            "FROM drivers d "+
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation,10000) " +
            "ORDER BY distance " +
            "LIMIT 10 " , nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickUpLocation);
}
