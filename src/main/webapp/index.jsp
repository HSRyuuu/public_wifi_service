<%@ page import="com.example.wifi.WifiService" %>
<%@ page import="com.example.dto.WifiDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dto.LocationDTO" %>
<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/table_main.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">

    <script src="js/location.js"></script>
    <title>Wifi Home</title>
</head>
<body>
<h1>와이파이 정보</h1>

<div class="button-container">
    <button class="button"
            onclick="location.href='index.jsp'"
    >Home
    </button>

    <button class="button"
            onclick="location.href='history/history.jsp'"
    >위치 히스토리 목록
    </button>

    <button class="button"
            onclick="location.href='wifi/load-wifi.jsp'"
            style="background-color: #f5c2c7"
    >Open API 와이파이 정보 가져오기
    </button>

    <button class="button"
            onclick="location.href='bookmark/bookmark-list.jsp'"
    >북마크 보기
    </button>

    <button class="button"
            onclick="location.href='bookmark-group/bookmark-group.jsp'"
    >북마크 그룹 관리
    </button>
</div>
<div>
    <form method="get" action="history/save-history.jsp">
        LAT: <input type="text" id="latitude" name="latitude" value="0.0"/>
        <label for="latitude"></label>

        LNT: <input type="text" id="longitude" name="longitude" value="0.0"/>
        <label for="longitude"></label>

        <button type="button" class="button" onclick="getLocation()">내 위치 불러오기</button>
        <button type="submit" class="button"
                style="background-color: lightblue"
        >근처 WIFI 정보 보기
        </button>
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
        <th>위도(LAT)</th>
        <th>경도(LNT)</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tr>
        <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>

</table>


</body>
</html>
