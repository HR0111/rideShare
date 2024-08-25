package com.hr.project.rideShare.rideShare.stratergies;

import com.hr.project.rideShare.rideShare.stratergies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.hr.project.rideShare.rideShare.stratergies.impl.DriverMatchingNearestDriverStrategy;
import com.hr.project.rideShare.rideShare.stratergies.impl.RideFareSurgePricingFareCalculationStrategy;
import com.hr.project.rideShare.rideShare.stratergies.impl.RiderFareDefaultFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStratergyManager {

    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;
    private final RiderFareDefaultFareCalculationStrategy riderFareDefaultFareCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy rideFareSurgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){

        if(riderRating >= 4.8){
            return driverMatchingHighestRatedDriverStrategy;
        }else{
            return driverMatchingNearestDriverStrategy;
        }

    }


    public RideFareCalculationStrategy fareCalculationStrategy(){

        LocalTime surgeStartTime = LocalTime.of(18,0);

        LocalTime surgeEndTime = LocalTime.of(21 , 0);

        LocalTime currentTime = LocalTime.now();


        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return  rideFareSurgePricingFareCalculationStrategy;
        }else{
            return riderFareDefaultFareCalculationStrategy;
        }


    }

}
