package com.hr.project.rideShare.rideShare.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointDto {

    private double[] coordinates;

    public PointDto(double[] coordinates) {
        this.coordinates = coordinates;
    }

    private String type = "Point";

}
