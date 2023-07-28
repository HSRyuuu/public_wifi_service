package com.example.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationHistory {
    private long id;
    private double lnt;//x좌표
    private double lat;//y좌표
    private LocalDateTime searchDateTime;

}
