<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인실패</title>
</head>
<body>
	아이디나 비밀번호를 확인해주세요<br>
	<a href='${contextPath}/login.jsp'>로그인화면으로 돌아가기</a>
</body>
</html>