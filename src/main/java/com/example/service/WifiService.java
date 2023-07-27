package com.example.service;

import com.example.api.ApiExplorer;
import com.example.json_utils.JsonConverter;
import com.example.json_utils.WifiInfo;
import com.example.repository.WifiRepository;

import java.io.IOException;

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
}
