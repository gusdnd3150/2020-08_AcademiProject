<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${param.key == 1}">
	게시글을 삭제하려면 암호를 입력하세요
	<br>
		<form action="delete.article" method="post">
			<input type="password" name="password"><br> <input type="hidden" name="articleNo" value="${param.articleNo}">
			<input type="submit" value="게시글 삭제하기">
		</form>
	</c:if>
	<c:if test="${param.key == 2}">
		게시글을 수정하려면 암호를 입력하세요
	<br>
		<form action="mod.article" method="post">
			<input type="password" name="password"><br> <input type="hidden" name="articleNo" value="${param.articleNo}">
			<input type="submit" value="게시글 수정하기">
		</form>
	</c:if>
</body>
</html>