<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import = "controller.member.*"
	import = "java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<%
	request.setCharacterEncoding("utf-8");
	if(request.getSession(false)!=null)	request.getSession(false).invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/sign-in/">
<link href="${contextPath}/member/login.css" rel="stylesheet">
</head>
<body class="text-center">
  <form class="form-signin" method="post" action="${contextPath}/members/login.do">
  <h1 class="h3 mb-3 font-weight-normal">Login</h1>
  <label for="member_id" class="sr-only">ID</label>
  <input type="text" id="member_id" class="form-control" name="member_id" placeholder="ID" required="" autofocus="">
  <label for="password" class="sr-only">Password</label>
  <input type="password" id="password" class="form-control" name="password" placeholder="Password" required="">
  <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
  <p class="mt-5 mb-3 text-muted">Pilot Project</p>
</form>


</body>

</html>