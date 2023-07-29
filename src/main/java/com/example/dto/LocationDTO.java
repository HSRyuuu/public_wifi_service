package com.example.dto;

import lombok.Data;


@Data
public class LocationDTO {
    private double lat;//위도
    private double lnt;//경도

    public LocationDTO() {
    }

    public LocationDTO(double lat, double lnt) {
        this.lat = lat;
        this.lnt = lnt;
    }

    public LocationDTO(String lat, String lnt){
        if(lat.equals("") || lat == null){
            this.lat = 126.978432;
        }else{
            this.lat =  Double.parseDouble(lat);
        }
        if(lnt.equals("") || lnt == null){
            this.lnt = 37.565836;
        }else{
            this.lnt =  Double.parseDouble(lnt);
        }
    }
}
