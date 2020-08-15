<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<html>
<head>
<title>암호확인하기</title>
</head>
<body>
	게시글을 삭제하시려면 암호를 입력하세요
	<br>
	<form method="get" action="${contextPath}/board/confirmOK2.do">
		암호:<input type="password" name="inputPw"><br> 
		<input type="hidden" name="article_no" value="${article_no}"><br>
		<input type="submit" value="게시글 삭제하기">
	</form>
</body>
</html>