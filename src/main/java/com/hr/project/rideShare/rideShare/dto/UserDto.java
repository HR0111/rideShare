package com.hr.project.rideShare.rideShare.dto;


import com.hr.project.rideShare.rideShare.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;
    private String email;
    private Set<Role> roles;


}
