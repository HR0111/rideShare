package com.hr.project.rideShare.rideShare.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {

    private UserDto user;
    private Double rating;

    private Boolean available;
    private String vehicalId;



}
