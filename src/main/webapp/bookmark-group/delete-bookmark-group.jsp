<%@ page import="com.example.bookmark_group.BookmarkGroupService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="/css/form.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">

    <title>Delete history</title>
</head>
<body>
<%
    BookmarkGroupService service = new BookmarkGroupService();
    long id = Long.parseLong(request.getParameter("id"));
    service.deleteBookmarkGroup(id);
    response.sendRedirect("bookmark-group.jsp");
%>
</body>
</html>