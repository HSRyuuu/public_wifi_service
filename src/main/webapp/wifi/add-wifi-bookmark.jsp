<%@ page import="com.example.bookmark.BookmarkService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <title>add wifi bookmark</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    String bmg = request.getParameter("select");

    BookmarkService bookmarkService = new BookmarkService();
    bookmarkService.save(id, bmg);
%>
<script>
    alert("저장되었습니다.");
    history.back();

</script>

</body>
</html>