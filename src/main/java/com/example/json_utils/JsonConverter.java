package com.example.json_utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class JsonConverter {
    /**
     * json String을 받아서 WifiInfo 배열로 변환
     * @param json
     * @return WifiInfo[] 배열
     */
    public WifiInfo[] jsonToWifiObject(String json) {
        JsonObject jObj = JsonParser.parseString(json).getAsJsonObject();

        JsonObject jsonObject = jObj.getAsJsonObject("TbPublicWifiInfo");
        JsonArray rowArray = jsonObject.getAsJsonArray("row");

        Gson gson = new Gson();
        WifiInfo[] wifiInfos = gson.fromJson(rowArray, WifiInfo[].class);
        return wifiInfos;
    }

    /**
     * API로 받은 데이터의 "list_total_count" 를 파싱해서 총 데이터 건수를 반환한다.
     *
     * @param json
     * @return 총 데이터 건수(row 수)
     */
    public int getRowsAmountByJson(String json) {
        JsonObject jObj = JsonParser.parseString(json).getAsJsonObject();
        JsonObject jsonObject = jObj.getAsJsonObject("TbPublicWifiInfo");
        return jsonObject.getAsJsonPrimitive("list_total_count").getAsInt(); //전체 row 수
    }

    /**
     * 테스트용 출력 메서드
     */
    public void printObject(WifiInfo[] wifiInfos) {
        int i = 1;
        for (WifiInfo wi : wifiInfos) {
            System.out.println("[" + i++ + "]");
            System.out.println(wi);
            System.out.println("====================");
        }
    }

}