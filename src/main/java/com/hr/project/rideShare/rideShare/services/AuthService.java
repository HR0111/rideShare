package com.hr.project.rideShare.rideShare.services;

import com.hr.project.rideShare.rideShare.dto.DriverDto;
import com.hr.project.rideShare.rideShare.dto.SignupDto;
import com.hr.project.rideShare.rideShare.dto.UserDto;



public interface AuthService {

    String login(String email , String password);  //  return tokens


    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId , String vechicleId);

}
