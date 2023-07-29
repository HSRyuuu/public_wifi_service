<%@ page import="com.example.service.WifiService" %>
<%@ page import="com.example.entity.WifiDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.entity.LocationDTO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/table_main.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    <meta charset="UTF-8">
    <title>Wifi List</title>

    <script src="../js/location.js"></script>

</head>
<body>
<h1>와이파이 정보</h1>
<%
    WifiService wifiService = new WifiService();
%>


<div class="button-container">
    <button class="button"
            onclick="location.href='list.jsp'"
    >홈</button>
    <button class="button">위치 히스토리 목록</button>
    <button class="button">Open API 와이파이 정보 가져오기</button>
</div>
<%
    List<WifiDTO> top20Wifi = wifiService.getTop20Wifi(new LocationDTO(37.565836, 126.978432));
    String lat = "";
    String lnt = "";
    if("POST".equals(request.getMethod())){
        lat = request.getParameter("latitudeInput");
        lnt = request.getParameter("longitudeInput");
        top20Wifi = wifiService.getTop20Wifi(new LocationDTO(lat, lnt));
    }
%>
<div>
    <form method="post" action="list.jsp">
        LAT: <input type="text" id="latitudeInput" name="latitudeInput"/>
        LNT: <input type="text" id="longitudeInput" name="longitudeInput"/>
        <button type="button" class="button" onclick="getLocation()">내 위치 불러오기</button>
        <button type="submit" class="button">근처 WIFI 정보 보기</button>
    </form>
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
        <th>LAT(위도)</th>
        <th>LNT(경도)</th>
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
            <a href="../detail.jsp?key=<%=wi.getManageNumber()%>&lat=<%=lat%>&lnt=<%=lnt%>">
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
        <td><%=wi.getLat()%></td>
        <td><%=wi.getLnt()%></td>
        <td><%=wi.getWorkDateTime()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>


</body>
</html>
