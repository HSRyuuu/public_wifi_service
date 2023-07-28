package com.example.service;

import com.example.api.ApiExplorer;
import com.example.entity.LocationDTO;
import com.example.entity.WifiDTO;
import com.example.json_utils.JsonConverter;
import com.example.json_utils.WifiInfo;
import com.example.repository.WifiRepository;

import java.io.IOException;
import java.util.List;

public class WifiService {
    private static ApiExplorer apiExplorer = new ApiExplorer();
    private static WifiRepository wifiRepository = new WifiRepository();
    private static JsonConverter jsonConverter = new JsonConverter();

    public void loadAllWifiOnDB() throws IOException {
        wifiRepository.deleteAll();
        int rowsAmount = apiExplorer.getRowsAmount();
        loadAllWifi(rowsAmount);
    }
    private void loadAllWifi(int rowsAmount) throws IOException {
        int cnt = 0;
        for(int i = 1; i <= rowsAmount; i += 1000){
            String jsonStr = apiExplorer.getApiJson(i, i + 999);
            WifiInfo[] wifiInfos = jsonConverter.jsonToWifiObject(jsonStr);
            cnt += wifiInfos.length;
            wifiRepository.loadAll(wifiInfos);
            System.out.println(cnt);
        }
    }



    public List<WifiDTO> getTop20Wifi(double myLnt, double myLat){
        List<WifiDTO> wifiDTOList = wifiRepository.selectTop20Wifi(myLnt, myLat);
        for (WifiDTO wi : wifiDTOList){
            wi.setDistance(calculateDistance(myLnt, myLat, new LocationDTO(wi.getLnt(), wi.getLat())));
        }
        return wifiDTOList;
    }

    //TODO : 거리계산 로직 수정
    private String calculateDistance(double myLnt, double myLat, LocationDTO locationDTO){
        double result = 0;
        double lnt = Double.parseDouble(locationDTO.getLnt());
        double lat = Double.parseDouble(locationDTO.getLat());

        result = Math.sqrt( Math.pow(myLnt-lnt, 2) + Math.pow(myLat-lat, 2));

        return String.format("%.4f",result);
    }
}
