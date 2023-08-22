<%@ page import="com.example.bookmark.group.BookmarkGroupService" %>
<%@ page import="com.example.bookmark.group.BookmarkGroup" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="/css/form.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    <script src="../js/validation.js"></script>

    <title>북마크 그룹 수정</title>
</head>
<body>
<%
    long id = Long.parseLong(request.getParameter("id"));
    BookmarkGroupService service = new BookmarkGroupService();
    BookmarkGroup bmg = service.findById(id);
%>

<h1>북마크 그룹 수정</h1>
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
            onclick="location.href='../bookmark/bookmark-list.jsp'"
    >북마크 보기
    </button>

    <button class="button"
            onclick="location.href='bookmark-group.jsp'"
    >북마크 그룹 관리
    </button>
</div>

<form method="get" action="edit-bookmark-group.jsp" onsubmit="return validateBookmarkGroupForm()">
    <table>
        <input type="hidden" id="id" name="id" value="<%=id%>">
        <colgroup>
            <col style="width: 20%"/>
            <col style="width: 80%"/>
        </colgroup>
        <tbody>
        <tr>
            <th>북마크 이름</th>
            <td>
                <input type="text" id="name" name="name"
                       value="<%=bmg.getName()%>"
                       style="text-align: left">
                <label for="name"></label>
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td>
                <input type="text" id="priority" name="priority"
                       value="<%=bmg.getPriority()%>"
                       style="text-align: left">
                <label for="priority"></label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="button" class="button" onclick="location.href='bookmark-group.jsp'">돌아가기</button>
                <button type="submit" class="button">수정</button>
            </td>
        </tr>
        </tbody>
    </table>

</form>
</body>
</html>