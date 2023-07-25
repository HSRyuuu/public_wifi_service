package com.example.test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

public class OkHttpTest {
    public void callGet(){
        try {
            OkHttpClient client = new OkHttpClient();

            String url = "http://localhost:8080/test";

            //builder 생성
            Request.Builder builder = new Request.Builder().url(url).get();
            builder.addHeader("Content-type", "application/json");

            //request 객체 생성
            Request request = builder.build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                String responseString = body.string();
                System.out.println("responseString = " + responseString);
                body.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
