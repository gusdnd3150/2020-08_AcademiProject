<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String name = (String)session.getAttribute("name");
System.out.println(name);
%>
<h1><%=name %>님,  안녕하세요!</h1>
<a href = "/static/Member/logOut.jsp"> [로그아웃] </a>  <%-- 초기페이지 주소 입력--%>
<a href = "/static/Member/editPwd.jsp"> [암호변경] </a>
<a href = "/static/Member/delMember.jsp"> [회원탈퇴] </a>
<br>
<h1><a href="/guestbook">방명록</a></h1>
<h1><a href="/board">게시판</a></h1>

</body>
</html>