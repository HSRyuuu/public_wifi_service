package com.example.api;

import com.example.dto.WifiApiDTO;
import com.example.json_utils.JsonConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ApiExplorerTest {
    static ApiExplorer explorer = new ApiExplorer();
    static JsonConverter jsonConverter = new JsonConverter();

    @Test
    void getApiJson() throws Exception{
        //given
        int pageStart = 1;
        int pageEnd = 3;

        //when
        String apiJson = explorer.getApiJson(pageStart, pageEnd);

        //then
        System.out.println(apiJson);

    }

    @Test
    void printTest() throws IOException {
        String apiJson = explorer.getApiJson(1, 3);

        WifiApiDTO[] wifiApiDTOS = jsonConverter.jsonToWifiObject(apiJson);
        assertThat(wifiApiDTOS.length).isEqualTo(3);
        printObject(wifiApiDTOS);
    }

    /**
     * 테스트용 출력 메서드
     */
    public void printObject(WifiApiDTO[] wifiApiDTOS) {
        int i = 1;
        for (WifiApiDTO wi : wifiApiDTOS) {
            System.out.println("[" + i++ + "]");
            System.out.println(wi);
            System.out.println("====================");
        }
    }
}