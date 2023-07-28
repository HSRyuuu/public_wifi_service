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


    /**
     * DB에 모든 와이파이 데이터 저장
     */
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

    /**
     * 입력받은 위도, 경도 값으로 가장 가까운 20개의 데이터를 불러와서 반환
     * @return List<WifiDTO> 가장 가까운 20개의 리스트
     */
    public List<WifiDTO> getTop20Wifi(LocationDTO loc){
        List<WifiDTO> wifiDTOList = wifiRepository.selectTop20Wifi(loc);
        for (WifiDTO wi : wifiDTOList){
            wi.setDistance(calculateDistance(loc, new LocationDTO(wi.getLnt(), wi.getLat())));
        }
        return wifiDTOList;
    }

    //TODO : 거리계산 로직 수정
    private String calculateDistance(LocationDTO loc1, LocationDTO loc2){
        return String.format("%.4f",
                Math.sqrt( Math.pow(loc1.getLnt()-loc2.getLnt(), 2) + Math.pow(loc1.getLat()-loc2.getLat(), 2))
                );
    }

    public WifiDTO getWifiDetail(String key, LocationDTO loc){
        WifiDTO wifiDTO = wifiRepository.findByManageNumber(key);
        wifiDTO.setDistance(calculateDistance(new LocationDTO(wifiDTO.getLnt(), wifiDTO.getLat()), loc));
        return wifiDTO;
    }
}
