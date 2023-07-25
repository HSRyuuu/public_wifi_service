package com.example.api;



import com.example.dto.PointDTO;
import com.example.json_utils.JsonConverter;
import com.example.json_utils.WifiInfo;
import com.example.service.WifiService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.api.ApiConst.*;


public class ApiExplorer {
    static JsonConverter jsonConverter = new JsonConverter();
    static WifiService wifiService = new WifiService();

    public void loadAllWifis() throws IOException {
        String json = getApiJson(1,1);
        int amount = jsonConverter.getRowsAmountByJson(json);
        wifiService.deleteAll();
        int cnt = 0;
        for(int i = 1; i <= amount; i += 1000){
            String jsonStr = getApiJson(i, i + 999);
            WifiInfo[] wifiInfos = jsonConverter.jsonToWifiObject(jsonStr);
            cnt += wifiInfos.length;
            wifiService.save(wifiInfos, new PointDTO(0, 0));
            System.out.println(cnt);
        }

    }

    /**
     * pageStart ~ pageEnd 까지를 검색한 Json String 반환
     * @param pageStart
     * @param pageEnd
     * @return Json String
     */
    public String getApiJson(int pageStart, int pageEnd) throws IOException {
        StringBuilder sb = new StringBuilder();
        //URL
        StringBuilder urlBuilder = new StringBuilder(serverUrl);
        //인증키
        urlBuilder.append("/" + URLEncoder.encode(authenticationKey,"UTF-8") );
        //요청 파일 타입 (xml,xmlf,xls,json)
        urlBuilder.append("/" + URLEncoder.encode("json","UTF-8") );
        //서비스 명(대소문자 구분 필수)
        urlBuilder.append("/" + URLEncoder.encode(serviceName,"UTF-8"));
        //요청시작 (페이징)
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(pageStart),"UTF-8"));
        //요청 종료 (페이징)
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(pageEnd),"UTF-8"));
// 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.
// 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
        //X_SWIFI_WRDOFC : 자치구
        urlBuilder.append("/" + URLEncoder.encode("","UTF-8"));
        //X_SWIFI_ADRES1 : 도로명 주소
        urlBuilder.append("/" + URLEncoder.encode("","UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        //System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader br;
        // 서비스코드가 정상이면 200~300사이의 숫자가 나옴
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        conn.disconnect();

        return sb.toString();
    }

}
