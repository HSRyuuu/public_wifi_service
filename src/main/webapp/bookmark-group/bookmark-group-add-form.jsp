<%@ page import="com.example.bookmark.group.BookmarkGroupService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">

    <link rel="stylesheet" type="text/css" href="/css/form.css">
    <link rel="stylesheet" type="text/css" href="/css/buttons.css">
    <script src="../js/validation.js"></script>

    <title>북마크 그룹 추가</title>
</head>
<body>
<h1>북마크 그룹 추가</h1>
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

<form method="get" action="add-bookmark-group.jsp" onsubmit="return validateBookmarkGroupForm()">
    <table>
        <colgroup>
            <col style="width: 20%"/>
            <col style="width: 80%"/>
        </colgroup>
        <tbody>
        <tr>
            <th>북마크 이름</th>
            <td>
                <input type="text" id="name" name="name"
                       placeholder="이름을 입력하세요."
                       style="text-align: left">
                <label for="name"></label>
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td>
                <input type="text" id="priority" name="priority"
                       placeholder="순서를 입력하세요."
                       style="text-align: left">
                <label for="priority"></label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="button" class="button" onclick="location.href='bookmark-group.jsp'">돌아가기</button>
                <button type="submit" class="button">추가</button>
            </td>
        </tr>
        </tbody>
    </table>

</form>
</body>
</html>