<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import = "java.util.*"
	import = "member.*"%>
	
	<%
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<style type="text/css">
		body { padding: 20px;}
	</style>
</head>
<body>
	<form method = "post" action="${contextPath}/members/changeOk.do">
		현재 비밀번호 : <input type="password" name="nowPw"> <br> 
		변경할 비밀번호 : <input type="password" name="newPw"><br> 
		<input class="btn btn-primary" type="submit" value="비밀번호 변경">
	</form>
</body>
</html>