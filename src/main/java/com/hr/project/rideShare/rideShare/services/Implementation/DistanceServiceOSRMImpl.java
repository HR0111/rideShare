package com.hr.project.rideShare.rideShare.services.Implementation;

import com.hr.project.rideShare.rideShare.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static final String OSRM_API = "http://router.project-osrm.org/route/v1/driving/13.388860,52.517037;13.397634,52.529407;13.428555,52.523219";

    @Override
    public double calculateDistance(Point src, Point des) {



        try {
            String uri = src.getX()+","+src.getY()+","+des.getX()+","+des.getY();
            //API OSRM
            OSRMResponseDto osrmResponseDto = RestClient
                    .builder()
                    .baseUrl(OSRM_API)
                    .build()
                    .get()
//                    .uri("{},{},{},{}", src.getX(), src.getY(), des.getX(), des.getY())
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDto.class);

            return osrmResponseDto.getRoutes().get(0).getDistance() / 1000.0;

        }catch (Exception e){
            throw new RuntimeException("NOt getting data from OSRM" + e.getMessage());
        }
    }
}
@Data
class OSRMResponseDto{

    private List<OSRMRoutes> routes;

}

@Data
class OSRMRoutes{
    private Double distance;
}
