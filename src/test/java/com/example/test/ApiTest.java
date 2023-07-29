package com.example.test;

import com.example.api.ApiExplorer;
import com.example.json_utils.JsonConverter;
import com.example.dto.WifiInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ApiTest {
    static ApiExplorer explorer = new ApiExplorer();
    static JsonConverter jsonConverter = new JsonConverter();

    @Test
    void printTest() throws IOException {
        String apiJson = explorer.getApiJson(1, 3);
        WifiInfo[] wifiInfos = jsonConverter.jsonToWifiObject(apiJson);
        jsonConverter.printObject(wifiInfos);
    }



}
