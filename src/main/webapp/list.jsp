<%@ page import="com.example.service.WifiService" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <style>
        table {
            width: 100%;
        }

        th, td {
            border: solid 1px #000;
        }
    </style>
</head>
<body>
<%
    WifiService wifiService = new WifiService();

    System.out.println("hello");
    MemberService memberService = new MemberService();
    List<Member> memberList = memberService.selectAllByMemberType("email");
%>
<h1>회원 목록</h1>
<table>
    <thead>
    <tr>
        <th>회원구분</th>
        <th>아이디</th>
        <th>비밀번호</th>
        <th>이름</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Member member : memberList) {
    %>
    <tr>
        <td><%=member.getMemberType()%></td>
        <td>
            <a href="detail.jsp?memberType=<%=member.getMemberType()%>&userId=<%=member.getUserId()%>">
                <%=member.getUserId()%>
            </a>
        </td>
        <td><%=member.getPassword()%></td>
        <td><%=member.getName()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>


</body>
</html>
