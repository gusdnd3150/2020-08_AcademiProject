<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
   <%--  <jsp:useBean id="dao" class="Member.MemberDAO"></jsp:useBean>
    <%<%-- int rst =0;  --%>
   <% String name = (String) request.getAttribute("name"); %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addMemberOk</title>
</head>
<body>
<jsp:include page="../nav1.jsp" />
<h1> ${param.name} 님, 환영합니다  <br>회원 가입에 성공했습니다. </h1>
<a href="/static/main.jsp"> 초기화면으로 </a>
</body>
</html>