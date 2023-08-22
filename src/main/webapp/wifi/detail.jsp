<%@ page import="com.example.wifi.WifiService" %>
<%@ page import="com.example.dto.WifiDTO" %>
<%@ page import="com.example.dto.LocationDTO" %>
<%@ page import="com.example.bookmark.group.BookmarkGroupService" %>
<%@ page import="com.example.bookmark.group.BookmarkGroup" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <title>Wifi Detail</title>
    <link rel="stylesheet" type="text/css" href="/css/detail.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    <meta charset="UTF-8">

    <script src="../js/location.js"></script>
    <script src="../js/validation.js"></script>

    <title>와이파이 상세</title>
</head>
<body>
<h1>와이파이 상세</h1>
<div class="button-container">
    <button class="button"
            onclick="location.href='../list.jsp'"
    >Home
    </button>

    <button class="button"
            onclick="location.href='../history/history.jsp'"
    >위치 히스토리 목록
    </button>

    <button class="button"
            style="background-color: #f5c2c7"
            onclick="location.href='load-wifi-waiting.html'"
    >Open API 와이파이 정보 가져오기
    </button>

    <button class="button"
            onclick="location.href='../bookmark/bookmark-list.jsp'"
    >북마크 보기
    </button>

    <button class="button"
            onclick="location.href='../bookmark-group/bookmark-group.jsp'"
    >북마크 그룹 관리
    </button>
</div>
<%
    WifiService wifiService = new WifiService();
    String key = request.getParameter("key");
    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");
    WifiDTO wi = wifiService.getWifiWithDistance(key, new LocationDTO(lat, lnt));

    BookmarkGroupService bmgService = new BookmarkGroupService();
    List<BookmarkGroup> bmgList = bmgService.findAllGroupList();
%>
<form method="get" action="add-wifi-bookmark.jsp" onsubmit="return validateSelectBox()">
    <input type="hidden" id="id" name="id" value="<%=key%>">

    <select id="select" name="select">
        <option value="" disabled selected>== 북마크 그룹 선택 ==</option>
        <% for (BookmarkGroup bmg : bmgList) { %>
        <option value="<%=bmg.getName()%>"><%=bmg.getName()%>
        </option>
        <%}%>
    </select>

    <button type="submit" class="button">북마크에 추가하기</button>
</form>

<table>
    <colgroup>
        <col style="width: 20%"/>
        <col style="width: 80%"/>
    </colgroup>
    <tbody>
    <tr>
        <th>거리(km)</th>
        <td><%=wi.getDistance()%>
        </td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td><%=wi.getManageNumber()%>
        </td>
    </tr>
    <tr>
        <th>자치구</th>
        <td><%=wi.getDistrict()%>
        </td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%=wi.getName()%>
        </td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td><%=wi.getAddr1()%>
        </td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td><%=wi.getAddr2()%>
        </td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td><%=wi.getInstallFloor()%>
        </td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td><%=wi.getInstallType()%>
        </td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td><%=wi.getInstallCorp()%>
        </td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td><%=wi.getServiceType()%>
        </td>
    </tr>
    <tr>
        <th>망종류</th>
        <td><%=wi.getNetworkType()%>
        </td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td><%=wi.getInstallYear()%>
        </td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td><%=wi.getInOrOutDoor()%>
        </td>
    </tr>
    <tr>
        <th>WIFI접속환경</th>
        <td><%=wi.getWifiAccessEnv()%>
        </td>
    </tr>
    <tr>
        <th>LAT(위도)</th>
        <td><%=wi.getLat()%>
        </td>
    </tr>
    <tr>
        <th>LNT(경도)</th>
        <td><%=wi.getLnt()%>
        </td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td><%=wi.getWorkDateTime()%>
        </td>
    </tr>

    </tbody>
</table>


</body>
</html>
