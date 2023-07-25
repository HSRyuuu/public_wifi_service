package com.example.dto;


public class PointDTO {
    private double lnt;//x좌표(경도)
    private double lat;//y좌표(위도)

    public PointDTO() {
    }

    public PointDTO(double lnt, double lat) {
        this.lnt = lnt;
        this.lat = lat;
    }
}
