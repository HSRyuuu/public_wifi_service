<%@ page import="com.example.bookmark.BookmarkService" %>
<%@ page import="com.example.bookmark.Bookmark" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="/css/table_small.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">

    <script src="../js/location.js"></script>


    <title>북마크 목록</title>
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
            onclick="location.href='../wifi/load-wifi-waiting.html'"
    >Open API 와이파이 정보 가져오기
    </button>

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
    BookmarkService bookmarkService = new BookmarkService();
    List<Bookmark> list = bookmarkService.findAll();
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
    <tbody>
    <%
        for (Bookmark b : list) {
    %>
    <tr>
        <td><%=b.getId()%>
        </td>
        <td><%=b.getBookmarkGroupName()%>
        </td>

        <td>
            <a href="javascript:void(0);" onclick="getLocationAndGoToDetail('<%=b.getWifiManageNumber()%>')">
                <%=b.getWifiName()%>
            </a>
        </td>
        <td><%=b.getDateTime()%>
        </td>
        <td>
            <button class="button"
                    onclick="location.href='bookmark-delete-page.jsp?id=<%=b.getId()%>'"
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