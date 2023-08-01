<%@ page import="com.example.wifi.WifiService" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title>Load-WIFI</title>
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    <title>와이파이 정보 불러오기..</title>

</head>
<body>
<%
    WifiService wifiService = new WifiService();
    int rows = wifiService.loadAllWifiOnDB();
%>
<div style="display: flex; justify-content: center; align-items: center">
    <h1><%=rows%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
</div>
<div style="display: flex; justify-content: center; align-items: center">
    <button class="button"
            onclick="location.href='../list.jsp'"
    >Home
    </button>
</div>

</body>
</html>
