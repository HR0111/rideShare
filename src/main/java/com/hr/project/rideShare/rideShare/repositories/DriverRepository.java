package com.hr.project.rideShare.rideShare.repositories;

import com.hr.project.rideShare.rideShare.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    //ST_DISTANCE(point1 , point2);
    //ST_DWITHIN(point1 , 1000)

    @Query(value = "SELECT d.* , ST_Distance(D.current_location, :pickupLocation ) AS distance " +
            "FROM driver d "+
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickUpLocation,10000) " +
            "ORDER BY distance " +
            "LIMIT 10 " , nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickUpLocation);



    @Query(value = "SELECT d.* " +
            "FROM driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickUpLocation , 15000) "+
            "ORDER BY d.rating DESC "+
            "LIMIT 10 " , nativeQuery = true)
    List<Driver> findTenNearByTopRatedDrivers(Point pickUpLocation);

}
