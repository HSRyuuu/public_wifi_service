package com.example.dto;


import lombok.*;

@Data
public class WifiDTO {
    private String distance; // 거리
    private String manageNumber;//관리 번호
    private String district; //자치구
    private String name;//와이파이명
    private String addr1;//도로명 주소
    private String addr2;//상세주소
    private String installFloor;//설치위치(층)
    private String installType;//설치유형
    private String installCorp;//설치기관
    private String serviceType;//서비스구분
    private String networkType;//망종류
    private String installYear;//설치년도
    private String inOrOutDoor;//실내외구분
    private String wifiAccessEnv;//wifi접속환경
    private String lat;//위도
    private String lnt;//경도
    private String workDateTime;//작업일자

    public WifiDTO() {
    }
    public void setDistance(double distance){
        this.distance = String.format("%.4f", distance);
    }
}
