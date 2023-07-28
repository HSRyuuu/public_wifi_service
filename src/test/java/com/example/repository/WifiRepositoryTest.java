package com.example.repository;

import com.example.entity.LocationDTO;
import com.example.entity.WifiDTO;
import com.example.service.WifiService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
    void getTop20Wifi(){
        LocationDTO loc = new LocationDTO(37.147590, 127.149493);

        List<WifiDTO> top20Wifi = wifiService.getTop20Wifi(loc);

        assertThat(top20Wifi.size()).isEqualTo(20);

        int cnt = 1;
        for(WifiDTO wi : top20Wifi){
            System.out.println("< "+cnt+" >");
            System.out.println(wi);
            cnt++;
        }
    }

    @Test
    void findByManageNumberTest(){
        String key = "---EP000001";

        WifiDTO wifiDTO = wifiService.getWifiDetail(key, new LocationDTO(37.147590, 127.149493));

        assertThat(wifiDTO.getManageNumber()).isEqualTo(key);

        System.out.println(wifiDTO);
    }

}