<%@ page import="com.example.service.WifiService" %>
<%@ page import="com.example.entity.WifiDTO" %>
<%@ page import="com.example.entity.LocationDTO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Wifi Detail</title>
    <link rel="stylesheet" type="text/css" href="/css/detail.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    <meta charset="UTF-8">
    <title>Title</title>

    <script src="js/location.js"></script>

</head>
<body>
<%
    WifiService wifiService = new WifiService();
    String key = request.getParameter("key");

    WifiDTO wi = wifiService.getWifiDetail(key, new LocationDTO(37.1, 126.5));

%>

<div class="button-container">
    <button class="button">홈</button>
    <button class="button">위치 히스토리 목록</button>
    <button class="button">Open API 와이파이 정보 가져오기</button>
</div>

<h1>와이파이 상세</h1>
<table>
    <colgroup>
        <col style="width: 20%"/>
        <col style="width: 80%"/>
    </colgroup>
    <tbody>
    <tr>
        <th>거리(km)</th>
        <td><%=wi.getDistance()%></td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td><%=wi.getManageNumber()%></td>
    </tr>
    <tr>
        <th>자치구</th>
        <td><%=wi.getDistrict()%></td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%=wi.getName()%></td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%=wi.getAddr1()%></td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%=wi.getAddr2()%></td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%=wi.getInstallFloor()%></td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%=wi.getInstallType()%></td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%=wi.getInstallCorp()%></td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%=wi.getServiceType()%></td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%=wi.getNetworkType()%></td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%=wi.getInstallYear()%></td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%=wi.getInOrOutDoor()%></td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><%=wi.getWifiAccessEnv()%></td>
    </tr>
    <tr>
        <th>LAT(위도)</th>
        <td><%=wi.getLat()%></td>
    </tr>
    <tr>
        <th>LNT(경도</th>
        <td><%=wi.getLnt()%></td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td><%=wi.getWorkDateTime()%></td>
    </tr>

    </tbody>
</table>


</body>
</html>
