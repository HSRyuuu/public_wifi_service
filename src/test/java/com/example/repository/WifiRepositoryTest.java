package com.example.repository;

import com.example.domain.Wifi;
import com.example.domain.WifiDTO;
import com.example.service.WifiService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WifiRepositoryTest {

    static WifiService wifiService = new WifiService();

    @Test
    void loadAll() throws IOException {
        wifiService.loadAllWifiOnDB();
        System.out.println("=======");
        System.out.println("DB 확인 해보기");
        System.out.println("=======");
    }

    @Test
    void getTop20Wifi() throws IOException {
        double myLnt = 37.147590;
        double myLat = 127.149493;
        List<WifiDTO> top20Wifi = wifiService.getTop20Wifi(myLnt, myLat);
        int cnt = 1;
        for(WifiDTO wi : top20Wifi){
            System.out.println("< "+cnt+" >");
            System.out.println(wi);
            cnt++;
        }
    }
}