package com.hr.project.rideShare.rideShare.controllers;

import com.hr.project.rideShare.rideShare.dto.DriverDto;
import com.hr.project.rideShare.rideShare.dto.SignupDto;
import com.hr.project.rideShare.rideShare.dto.UserDto;
import com.hr.project.rideShare.rideShare.dto.onBoardDriverDto;
import com.hr.project.rideShare.rideShare.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignupDto  signupDto){
        return new ResponseEntity<>(service.signup(signupDto) , HttpStatus.CREATED);
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onboardNewDriver(@PathVariable Long userId , @RequestBody onBoardDriverDto onBoardDriverDto){
        return new ResponseEntity<>( service.onboardNewDriver(userId , onBoardDriverDto.getVechicleId()) , HttpStatus.CREATED);
    }
}
