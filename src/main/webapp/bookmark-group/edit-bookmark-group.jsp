<%@ page import="com.example.bookmark_group.BookmarkGroupService" %>
<%@ page import="com.example.dto.BookmarkGroupDTO" %>
<%@ page import="com.example.bookmark_group.BookmarkGroup" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <title>Delete history</title>
</head>
<body>
<%
    BookmarkGroupService service = new BookmarkGroupService();

    long id = Long.parseLong(request.getParameter("id"));
    String name = request.getParameter("name");
    int priority = Integer.parseInt(request.getParameter("priority"));

    BookmarkGroupDTO dto = new BookmarkGroupDTO(name, priority);

    service.editBookmarkGroup(id, dto);
    response.sendRedirect("bookmark-group.jsp");
%>

</body>
</html>