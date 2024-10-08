package com.hr.project.rideShare.rideShare.controllers;


import com.hr.project.rideShare.rideShare.dto.*;
import com.hr.project.rideShare.rideShare.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/riders")
@Secured("ROLE_RIDER")
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver")
    public ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto ratingDto) {
        return ResponseEntity.ok(riderService.rateDriver(ratingDto.getRideId(), ratingDto.getRating()));
    }

    @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDto> getMyProfile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageoffset,
                                                       @RequestParam(defaultValue = "10" , required = false) Integer pageSize){

        PageRequest pageRequest = PageRequest.of(pageoffset , pageSize,
                Sort.by(Sort.Direction.DESC , "createdTime" , "id"));
        return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
    }

//    @PostMapping("/rateDriver/{driverId}/{rating}")
//    public ResponseEntity<DriverDto> rateDriver(@PathVariable Long driverId , @PathVariable Integer rating){
//        return ResponseEntity.ok(riderService.rateDriver(driverId, rating));
//    }

}
