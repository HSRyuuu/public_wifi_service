package com.example.wifi;

import com.example.api.ApiExplorer;
import com.example.dto.LocationDTO;
import com.example.dto.WifiDTO;
import com.example.json_utils.JsonConverter;
import com.example.dto.WifiApiDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WifiService {
    private static ApiExplorer apiExplorer = new ApiExplorer();
    private static WifiRepository wifiRepository = new WifiRepository();
    private static WifiLoader wifiLoader = new WifiLoader();
    private static JsonConverter jsonConverter = new JsonConverter();

    /**
     * DB에 모든 와이파이 데이터 저장
     */
    public int loadAllWifiOnDB() throws IOException {
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
        List<WifiApiDTO[]> list = new ArrayList<>();
        for (int i = 1; i <= rowsAmount; i += 1000) {
            String jsonStr = apiExplorer.getApiJson(i, i + 999);
            WifiApiDTO[] wifiApiDTOS = jsonConverter.jsonToWifiObject(jsonStr);
            cnt += wifiApiDTOS.length;
            list.add(wifiApiDTOS);
            System.out.println(cnt);
        }
        try {
            wifiLoader.loadAll(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 입력받은 위도, 경도 값으로 가장 가까운 20개의 데이터를 불러와서 반환한다.
     */
    public List<WifiDTO> findWifisByLoc(LocationDTO loc) {
        return wifiRepository.selectTop20Wifi(loc);
    }

    public WifiDTO getWifiWithDistance(String manageNumber, LocationDTO loc) {
        return wifiRepository.findByManageNumber(manageNumber, loc);
    }

    public WifiDTO findByManageNumber(String manageNumber) {
        return wifiRepository.findByManageNumber(manageNumber, new LocationDTO(0.0, 0.0));
    }

}
