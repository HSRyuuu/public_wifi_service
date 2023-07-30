package com.example.wifi;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Wifi {
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
    private String lat;//y좌표 (위도)
    private String lnt;//x좌표 (경도)
    private String workDateTime;//작업일자

}
