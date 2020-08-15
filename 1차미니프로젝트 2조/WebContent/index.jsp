<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
	// 기본세션 삭제
	if(request.getSession(false)!=null)	request.getSession(false).invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초기 Page</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
</head>
<body>
	<ul class="nav nav-pills nav-stacked">
		<li role="presentation"><a href="${contextPath}/guest">방명록</a></li>
		<li role="presentation"><a href="${contextPath}/board">게시판</a></li>
		<li role="presentation"><a href="${contextPath}/member/login.jsp">로그인</a></li>
		<li role="presentation"><a href="${contextPath}/member/join.jsp">회원가입</a></li>
	</ul>
</body>
</html>