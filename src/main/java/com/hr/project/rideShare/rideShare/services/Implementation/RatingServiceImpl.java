package com.hr.project.rideShare.rideShare.services.Implementation;

import com.hr.project.rideShare.rideShare.dto.DriverDto;
import com.hr.project.rideShare.rideShare.dto.RideDto;
import com.hr.project.rideShare.rideShare.dto.RiderDto;
import com.hr.project.rideShare.rideShare.entities.Driver;
import com.hr.project.rideShare.rideShare.entities.Rating;
import com.hr.project.rideShare.rideShare.entities.Ride;
import com.hr.project.rideShare.rideShare.entities.Rider;
import com.hr.project.rideShare.rideShare.exceptions.ResourceNotFoundException;
import com.hr.project.rideShare.rideShare.exceptions.RuntimeConflictException;
import com.hr.project.rideShare.rideShare.repositories.DriverRepository;
import com.hr.project.rideShare.rideShare.repositories.RatingRepository;
import com.hr.project.rideShare.rideShare.repositories.RiderRepository;
import com.hr.project.rideShare.rideShare.services.RatingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Driver  driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for id with id"+ride.getId()));

        if (ratingObj.getDriverRating() != null){
            throw new RuntimeConflictException("Driver has already been rated ,  cannot rate again");
        }

        ratingObj.setDriverRating(rating);

       Double newRating =  ratingRepository.findByDriver(driver).stream()
                .mapToDouble(ratingg -> ratingg.getDriverRating()).average().orElse(0.0);
       driver.setRating(newRating);

        Driver saveddriver =   driverRepository.save(driver);
        return modelMapper.map(saveddriver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {

        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for id with id"+ride.getId()));

        if (ratingObj.getRiderRating() != null){
            throw new RuntimeConflictException("Rider has already been rated ,  cannot rate again");
        }

        ratingObj.setRiderRating(rating);

        Double newRating =  ratingRepository.findByRider(rider).stream()
                .mapToDouble(ratingg -> ratingg.getRiderRating()).average().orElse(0.0);
        rider.setRating(newRating);

      Rider savedrider =   riderRepository.save(rider);
        return modelMapper.map(savedrider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {

        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}