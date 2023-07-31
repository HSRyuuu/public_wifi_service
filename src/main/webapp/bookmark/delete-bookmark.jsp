<%@ page import="com.example.bookmark.BookmarkService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <title>Delete bookmark</title>
</head>
<body>
<%
    BookmarkService bookmarkService = new BookmarkService();
    String id = request.getParameter("id");
    bookmarkService.delete(Long.parseLong(id));

    response.sendRedirect("bookmark-list.jsp");
%>

</body>
</html>