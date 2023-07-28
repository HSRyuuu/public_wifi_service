<%@ page import="com.example.service.WifiService" %>
<%@ page import="com.example.entity.WifiDTO" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/table_main.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    <meta charset="UTF-8">
    <title>Title</title>

    <script src="location.js"></script>

</head>
<body>
<%
    WifiService wifiService = new WifiService();
    List<WifiDTO> top20Wifi = wifiService.getTop20Wifi(37.1, 126.5);
%>
<h1>와이파이 정보</h1>

<div class="button-container">
    <button class="button">홈</button>
    <button class="button">위치 히스토리 목록</button>
    <button class="button">Open API 와이파이 정보 가져오기</button>
</div>
<div>
    LAT: <input type="text" id="latitudeInput" />  LNG: <input type="text" id="longitudeInput" />
    <button class="button" onclick="getLocation()">내 위치 불러오기</button>
    <button class="button">근처 WIFI 정보 보기</button>
</div>

<table>
    <thead>
    <tr>
        <th>거리(km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (WifiDTO wi : top20Wifi) {
    %>
    <tr>
        <td><%=wi.getDistance()%></td>
        <td><%=wi.getManageNumber()%></td>
        <td><%=wi.getDistrict()%></td>
        <td>
            <a href="detail.jsp?key=<%=wi.getManageNumber()%>">
                <%=wi.getName()%>
            </a>
        </td>
        <td><%=wi.getAddr1()%></td>
        <td><%=wi.getAddr2()%></td>
        <td><%=wi.getInstallFloor()%></td>
        <td><%=wi.getInstallType()%></td>
        <td><%=wi.getInstallCorp()%></td>
        <td><%=wi.getServiceType()%></td>
        <td><%=wi.getNetworkType()%></td>
        <td><%=wi.getInstallYear()%></td>
        <td><%=wi.getInOrOutDoor()%></td>
        <td><%=wi.getWifiAccessEnv()%></td>
        <td><%=wi.getLnt()%></td>
        <td><%=wi.getLat()%></td>
        <td><%=wi.getWorkDateTime()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>


</body>
</html>
