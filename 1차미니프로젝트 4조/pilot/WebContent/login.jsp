<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 창</title>
</head>
<body>
	<form action="${contextPath}/member/login.do" method="post" name="loginfrm">
		<img src='${contextPath}/img/user_2.png' width='18' height='18'> <input type="text" name="id" placeholder="아이디"><br><br>
		<img src='${contextPath}/img/lock.png' width='18' height='18'> <input type="password" name="pw" placeholder="암호"><br><br>
		<input type="submit" value="로그인">
	</form>
</body>
</html>