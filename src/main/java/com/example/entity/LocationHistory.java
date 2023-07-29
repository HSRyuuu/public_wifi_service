package com.example.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationHistory {
    private long id;
    private double lat;//위도
    private double lnt;//경도
    private LocalDateTime searchDateTime;

}
