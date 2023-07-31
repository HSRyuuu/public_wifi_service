package com.example.wifi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoadServlet")
public class LoadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        WifiService wifiService = new WifiService();
        int rows = wifiService.loadAllWifiFromApiToDB();

        request.setAttribute("rows", rows);
        request.getRequestDispatcher("load-wifi.jsp").forward(request, response);
    }
}
