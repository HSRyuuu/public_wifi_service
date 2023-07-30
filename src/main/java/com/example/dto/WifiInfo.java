package com.example.dto;


import lombok.*;

@Data
public class WifiInfo {
     private String X_SWIFI_MGR_NO;//관리 번호
     private String X_SWIFI_WRDOFC; //자치구
     private String X_SWIFI_MAIN_NM;//와이파이명
     private String X_SWIFI_ADRES1;//도로명 주소
     private String X_SWIFI_ADRES2;//상세주소
     private String X_SWIFI_INSTL_FLOOR;//설치위치(층)
     private String X_SWIFI_INSTL_TY;//설치유형
     private String X_SWIFI_INSTL_MBY;//설치기관
     private String X_SWIFI_SVC_SE;//서비스구분
     private String X_SWIFI_CMCWR;//망종류
     private String X_SWIFI_CNSTC_YEAR;//설치년도
     private String X_SWIFI_INOUT_DOOR;//실내외구분
     private String X_SWIFI_REMARS3;//wifi접속환경
     private String LAT;//작은값 (위도)
     private String LNT; //큰 값(경도)
     private String WORK_DTTM;//작업일자

}
