package com.hr.project.rideShare.rideShare.controllers;

import com.hr.project.rideShare.rideShare.dto.SignupDto;
import com.hr.project.rideShare.rideShare.dto.UserDto;
import com.hr.project.rideShare.rideShare.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    UserDto signUp(@RequestBody SignupDto  signupDto){
        return service.signup(signupDto);
    }
}
