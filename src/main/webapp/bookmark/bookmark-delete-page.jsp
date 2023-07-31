<%@ page import="com.example.bookmark.BookmarkService" %>
<%@ page import="com.example.bookmark.Bookmark" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="/css/detail.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">

    <script src="../js/confirm.js"></script>

    <title>북마크 삭제</title>
</head>
<body>
<h1>북마크 삭제</h1>
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
<p>북마크를 삭제하시겠습니까?</p>

<%
    long id = Long.parseLong(request.getParameter("id"));
    BookmarkService bookmarkService = new BookmarkService();
    Bookmark bm = bookmarkService.findById(id);

%>

<table>
    <colgroup>
        <col style="width: 20%"/>
        <col style="width: 80%"/>
    </colgroup>
    <tbody>
    <tr>
        <th>북마크 그룹 이름</th>
        <td><%=bm.getBookmarkGroupName()%>
        </td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%=bm.getWifiName()%>
        </td>
    </tr>
    <tr>
        <th>등록일자</th>
        <td><%=bm.getDateTime()%>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <button type="button" class="button" onclick="location.href='bookmark-list.jsp'">돌아가기</button>
            <button type="button" class="button"
                    style="background-color: #f5c2c7"
                    onclick="confirmDelete('delete-bookmark.jsp?id=<%=id%>')"
            >삭제
            </button>
        </td>
    </tr>

    </tbody>
</table>

</body>
</html>