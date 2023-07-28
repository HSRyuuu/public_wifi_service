package com.example.json_utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
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
     private String LNT;
     private String LAT;
     private String WORK_DTTM;//작업일자

     @Override
     public String toString() {
          return "WifiInfo{" + "\n"+
                  "X_SWIFI_MGR_NO='" + X_SWIFI_MGR_NO + '\'' + "\n"+
                  ", X_SWIFI_WRDOFC='" + X_SWIFI_WRDOFC + '\'' + "\n"+
                  ", X_SWIFI_MAIN_NM='" + X_SWIFI_MAIN_NM + '\'' + "\n"+
                  ", X_SWIFI_ADRES1='" + X_SWIFI_ADRES1 + '\'' + "\n"+
                  ", X_SWIFI_ADRES2='" + X_SWIFI_ADRES2 + '\'' + "\n"+
                  ", X_SWIFI_INSTL_FLOOR='" + X_SWIFI_INSTL_FLOOR + '\'' + "\n"+
                  ", X_SWIFI_INSTL_TY='" + X_SWIFI_INSTL_TY + '\'' + "\n"+
                  ", X_SWIFI_INSTL_MBY='" + X_SWIFI_INSTL_MBY + '\'' + "\n"+
                  ", X_SWIFI_SVC_SE='" + X_SWIFI_SVC_SE + '\'' + "\n"+
                  ", X_SWIFI_CMCWR='" + X_SWIFI_CMCWR + '\'' + "\n"+
                  ", X_SWIFI_CNSTC_YEAR='" + X_SWIFI_CNSTC_YEAR + '\'' + "\n"+
                  ", X_SWIFI_INOUT_DOOR='" + X_SWIFI_INOUT_DOOR + '\'' + "\n"+
                  ", X_SWIFI_REMARS3='" + X_SWIFI_REMARS3 + '\'' + "\n"+
                  ", LNT='" + LNT + '\'' + "\n"+
                  ", LAT='" + LAT + '\'' + "\n"+
                  ", WORK_DTTM='" + WORK_DTTM + '\'' + "\n"+
                  '}';
     }
}
