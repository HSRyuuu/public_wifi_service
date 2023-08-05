package com.example.json_utils;

import com.example.api.ApiExplorer;
import com.example.dto.WifiApiDTO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class JsonConverterTest {

    ApiExplorer apiExplorer = new ApiExplorer();
    JsonConverter jsonConverter = new JsonConverter();
    @Test
    void jsonToWifiObject() throws IOException {
        //given
        String apiJson = apiExplorer.getApiJson(1, 3);

        //when
        WifiApiDTO[] wifiApiDTOS = jsonConverter.jsonToWifiObject(apiJson);

        //then
        assertThat(wifiApiDTOS.length).isEqualTo(3);

    }

    @Test
    void getRowsAmountByJson() throws IOException {
        int amount = 23331; // 변할 수 있음

        //given
        int pageStart = 1;
        int pageEnd = 3;
        String apiJson = apiExplorer.getApiJson(pageStart, pageEnd);

        //when
        int rowsAmount = apiExplorer.getRowsAmount();

        //then
        assertThat(rowsAmount).isEqualTo(amount);

    }
}