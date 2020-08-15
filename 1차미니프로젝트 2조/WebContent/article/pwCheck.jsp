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
	게시글을 수정하시려면 암호를 입력하세요
	<br>
	<form action="${contextPath}/board/confirmOK.do">
		암호:<input type="password" name="inputPw"><br> 
		<input type="submit" value="게시글 수정하기"><br>
		<input type="hidden" name="article_no" value="${article_no}">
		<input type="hidden" name="title" value="${title}">
		<input type="hidden" name="content" value="${content}">
		<input type="hidden" name="file_name" value="${file_name }">
		<input type="hidden" name="old_file_name" value="${old_file_name }">
	</form>
</body>
</html>