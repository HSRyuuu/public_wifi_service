<%@ page import="com.example.history.HistoryService" %>
<%@ page import="com.example.history.History" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>

    <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    <link rel="stylesheet" type="text/css" href="/css/table_small.css">

    <script src="../js/confirm.js"></script>

    <title>위치 히스토리 목록</title>

</head>
<body>
<h1>위치 히스토리 목록</h1>
<div class="button-container">
    <button class="button"
            onclick="location.href='../list.jsp'"
    >Home
    </button>

    <button class="button"
            onclick="location.href='history.jsp'"
    >위치 히스토리 목록
    </button>

    <button class="button"
            style="background-color: #f5c2c7"
            onclick="location.href='../wifi/load-wifi-waiting.html'"
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
    HistoryService historyService = new HistoryService();
    List<History> historyList = historyService.findLatest20Histories();
%>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>위도(LAT)</th>
        <th>경도(LNT)</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (History h : historyList) {
    %>
    <tr>
        <td><%=h.getId()%>
        </td>
        <td><%=h.getLat()%>
        </td>
        <td><%=h.getLnt()%>
        </td>
        <td><%=h.getDateTime()%>
        </td>
        <td>
            <button class="button"
                    onclick="location.href='../list.jsp?lat=<%=h.getLat()%>&lnt=<%=h.getLnt()%>'"
                    style="background-color: lightblue"
            >조회
            </button>
            <button class="button"
                    onclick="confirmDelete('delete-history.jsp?id=<%=h.getId()%>');"
                    style="background-color: #f5c2c7"
            >삭제
            </button>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
