<%@ page import="com.example.history.HistoryService" %>
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
  HistoryService historyService = new HistoryService();
  String id = request.getParameter("id");
  historyService.deleteHistoryById(id);
  response.sendRedirect("history.jsp");
%>

</body>
</html>