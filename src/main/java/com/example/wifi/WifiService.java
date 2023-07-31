package com.example.wifi;

import com.example.api.ApiExplorer;
import com.example.dto.LocationDTO;
import com.example.dto.WifiDTO;
import com.example.json_utils.JsonConverter;
import com.example.dto.WifiApiDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WifiService {
    private static ApiExplorer apiExplorer = new ApiExplorer();
    private static WifiRepository wifiRepository = new WifiRepository();
    private static JsonConverter jsonConverter = new JsonConverter();

    public WifiDTO findWifiDetail(String key, LocationDTO loc) {
        WifiDTO wifiDTO = wifiRepository.findByManageNumber(key);
        wifiDTO.setDistance(calculateDistance(new LocationDTO(wifiDTO.getLat(), wifiDTO.getLnt()), loc));
        return wifiDTO;
    }

    public WifiDTO findById(String key) {
        return wifiRepository.findByManageNumber(key);
    }

    /**
     * DB에 모든 와이파이 데이터 저장
     */
    public int loadAllWifiFromApiToDB() throws IOException {
        wifiRepository.deleteAll();
        int rowsAmount = apiExplorer.getRowsAmount();
        loadAllWifi(rowsAmount);
        return rowsAmount;
    }

    /**
     * api는 한번에 1000개의 데이터까지만 받을 수 있기 때문에 1000개씩 끊어서 db에 저장
     *
     * @param rowsAmount : 전체 row 수
     */
    private void loadAllWifi(int rowsAmount) throws IOException {
        int cnt = 0;
        for (int i = 1; i <= rowsAmount; i += 1000) {
            String jsonStr = apiExplorer.getApiJson(i, i + 999);
            WifiApiDTO[] wifiApiDTOS = jsonConverter.jsonToWifiObject(jsonStr);
            cnt += wifiApiDTOS.length;
            wifiRepository.loadAll(wifiApiDTOS);
            System.out.println(cnt);
        }
    }

    /**
     * 입력받은 위도, 경도 값으로 가장 가까운 20개의 데이터를 불러와서 반환
     * repository에서 가져온 30개의 데이터를 Haversin 공식에 의해 측정된 거리로 재가공
     *
     * @return List<WifiDTO> 가장 가까운 20개의 리스트
     */
    public List<WifiDTO> findWifisByLoc(LocationDTO loc) {
        List<WifiDTO> wifiDTOList = wifiRepository.selectTop30Wifi(loc);
        for (WifiDTO wi : wifiDTOList) {
            wi.setDistance(calculateDistance(loc, new LocationDTO(wi.getLat(), wi.getLnt())));
        }
        Collections.sort(wifiDTOList, (WifiDTO x, WifiDTO y) -> {
            if (Double.parseDouble(x.getDistance()) > Double.parseDouble(y.getDistance())) {
                return 1;
            } else {
                return -1;
            }
        });

        List<WifiDTO> list = new ArrayList<>();
        for (int i = 0; i < wifiDTOList.size(); i++) {
            if (i >= 20) {
                break;
            }
            list.add(wifiDTOList.get(i));
        }
        return list;
    }

    /**
     * Haversine 공식을 이용해 위도, 경도 값으로 거리(km) 계산
     */
    public String calculateDistance(LocationDTO loc1, LocationDTO loc2) {
        double RADIUS = 6371.0; // 지구의 반지름 (단위: km)
        double radLat1 = Math.toRadians(loc1.getLat());
        double radLon1 = Math.toRadians(loc1.getLnt());
        double radLat2 = Math.toRadians(loc2.getLat());
        double radLon2 = Math.toRadians(loc2.getLnt());

        // 위도와 경도의 차이 계산
        double dLat = radLat2 - radLat1;
        double dLon = radLon2 - radLon1;

        // Haversine 공식 적용
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 두 지점 간의 거리 계산
        double distance = RADIUS * c;

        return String.format("%.4f", distance);
    }

}
