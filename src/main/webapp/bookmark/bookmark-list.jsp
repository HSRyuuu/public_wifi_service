<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="/css/table_small.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">

    <title>Delete history</title>
</head>
<body>
<h1>북마크 목록</h1>
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
            onclick="location.href='../wifi/load-wifi.jsp'"
    >Open API 와이파이 정보 가져오기</button>

    <button class="button"
            onclick="location.href='bookmark-list.jsp'"
    >북마크 보기
    </button>

    <button class="button"
            onclick="location.href='../bookmark-group/bookmark-group.jsp'"
    >북마크 그룹 관리
    </button>
</div>
<%

%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    </thead>
</table>
</body>
</html>