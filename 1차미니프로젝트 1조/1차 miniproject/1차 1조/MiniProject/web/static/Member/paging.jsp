<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
    text-align:center;
}
#paging{
    font-size: 22pt;
}
</style>
</head>
<body>
<div id="paging">
<!-- 1~10까지 있는 페이지의 페이징 -->
<c:url var="action" value="/guestbook"/>
<c:if test="${param.prev}">
    <a href="${action}?command='123'&page=${param.beginPage-1}&command='sdf'">이전페이지</a>
</c:if>
<c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
    <c:choose>
        <c:when test="${param.page==index}">
            ${index}
        </c:when>
        <c:otherwise>
            <a href="${action}?command='123'&page=${index}">${index}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${param.next}">
    <a href="${action}?command='123'&page=${param.endPage+1}">다음페이지</a>
</c:if>
</div>
</body>
</html>