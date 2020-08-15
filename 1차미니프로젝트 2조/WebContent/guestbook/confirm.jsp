<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<style type="text/css">
		body { padding: 20px;}
		div { width: 80%; margin: 0 auto;  }
	</style>
</head>
<body>
	<form action="${contextPath}/guest/${action2}Ok.do" method="post">
		메시지를 ${action}하려면 암호를 입력하세요.<br>
		암호: <input type="password" name="inputPassword">
		<input type="hidden" name="message_id" value="${message_id}">
		<input class="btn btn-primary" type="submit" value="메시지 ${action}하기">
	</form>
</body>
</html>