<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    import="member.*"
    import="java.util.*"
    %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
 <%
 	request.setCharacterEncoding("utf-8");
 %>
 <%
 MemberVO vo = new MemberVO(); 
 vo = (MemberVO)session.getAttribute("loginUser");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과</title>
</head>
<body>
	<div><%= vo.getName() %> 님, 안녕하세요.</div>
		<a href='${contextPath}/member/logout.do'>[로그아웃하기]</a>
		&nbsp;<a href='${contextPath}/changepwd.jsp'>[암호변경하기]</a>
		<br><br>
	<a href="book"><img src='${contextPath}/img/guestbook.png' width='200'></a><br><a href="./article/listArticles.do"><img src='${contextPath}/img/board.png' width='200'></a>
</body>
</html>