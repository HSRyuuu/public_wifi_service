package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class LocationDTO {
    private String lnt;//x좌표 (경도)
    private String lat;//y좌표 (위도)
}
