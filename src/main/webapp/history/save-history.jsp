<%@ page import="com.example.history.HistoryService" %>
<%@ page import="com.example.dto.LocationDTO" %>
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

    String lat = request.getParameter("latitude");
    String lnt = request.getParameter("longitude");
    historyService.saveHistory(new LocationDTO(lat, lnt));
    response.sendRedirect("../list.jsp?latitude="+lat+"&longitude="+lnt);
%>

</body>
</html>