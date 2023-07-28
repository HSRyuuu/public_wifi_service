package com.example.entity;

import lombok.Data;


@Data
public class LocationDTO {
    private double lnt;//x좌표 (경도)
    private double lat;//y좌표 (위도)

    public LocationDTO() {
    }

    public LocationDTO(double lnt, double lat) {
        this.lnt = lnt;
        this.lat = lat;
    }

    public LocationDTO(String lnt, String lat){
        this.lnt =  Double.parseDouble(lnt);
        this.lat =  Double.parseDouble(lat);
    }
}
