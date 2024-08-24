package com.hr.project.rideShare.rideShare.services.Implementation;

import com.hr.project.rideShare.rideShare.dto.DriverDto;
import com.hr.project.rideShare.rideShare.dto.SignupDto;
import com.hr.project.rideShare.rideShare.dto.UserDto;
import com.hr.project.rideShare.rideShare.entities.User;
import com.hr.project.rideShare.rideShare.enums.Role;
import com.hr.project.rideShare.rideShare.exceptions.RuntimeConflictException;
import com.hr.project.rideShare.rideShare.repositories.UserRepository;
import com.hr.project.rideShare.rideShare.services.AuthService;
import com.hr.project.rideShare.rideShare.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto signup(SignupDto signupDto) {

       User user =  userRepository.findByEmail(signupDto.getEmail()).orElse(null);

       if(user != null)
          throw new RuntimeConflictException("Cannot signup already exist" + signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto , User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        // create user related entity

        riderService.createNewRider(savedUser);
        // TODO WALLET SERVICES HERE

        return modelMapper.map(savedUser, UserDto.class);


    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}
