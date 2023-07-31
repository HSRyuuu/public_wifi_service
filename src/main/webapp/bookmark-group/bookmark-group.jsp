<%@ page import="com.example.bookmark_group.BookmarkGroupService" %>
<%@ page import="com.example.bookmark_group.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="/css/table_small.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">

    <script src="../js/confirm.js"></script>

    <title>북마크 그룹 관리</title>
</head>
<body>
<h1>북마크 그룹</h1>

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
            onclick="location.href='bookmark-group.jsp'"
    >북마크 그룹 관리
    </button>
</div>
<div>
    <button class="button"
            onclick="location.href='bookmark-group-add-form.jsp'"
            style="background-color: lightblue"
    >북마크 그룹 추가
    </button>
</div>
<%
    BookmarkGroupService service = new BookmarkGroupService();
    List<BookmarkGroup> groupList = service.findAllGroupList();
%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (BookmarkGroup bmg : groupList) {
    %>
    <tr>
        <td><%=bmg.getId()%>
        </td>
        <td><%=bmg.getName()%>
        </td>
        <td><%=bmg.getPriority()%>
        </td>
        <td><%=bmg.getCreateDateTime()%>
        </td>
        <td><%=bmg.getEditDateTime()%>
        </td>
        <td>
            <button class="button"
                    onclick="location.href='bookmark-group-edit-form.jsp?id=<%=bmg.getId()%>'"
                    style="background-color: lightblue"
            >수정
            </button>

            <button class="button"
                    onclick="confirmDelete('delete-bookmark-group.jsp?id=<%=bmg.getId()%>')"
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