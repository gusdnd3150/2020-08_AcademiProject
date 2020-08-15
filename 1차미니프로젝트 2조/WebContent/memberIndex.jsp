<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="controller.member.*" import="java.util.*"%>
	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
 	request.setCharacterEncoding("utf-8");
 %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 

<%
	request.setCharacterEncoding ("utf-8");
	//String name=request.getParameter("name");


	//MemberVO memberVO = new MemberVO();
	//memberVO.setName(name);
	//MemberDAO dao = new MemberDAO();
	

	//List list = dao.listMembers();
	try {
				boolean isLogined = (boolean)session.getAttribute("isLogined");
				System.out.println("isLogined : "+isLogined);
				
	} catch (NullPointerException e) {}
				
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<style type="text/css">
	div { padding: 15px }
</style>
<title>로그인 초기페이지</title>
</head>
<body>
	<div>
	${loginedMember.name}님, 안녕하세요  &nbsp;
	<a href="${contextPath}/members/logout.do" >[로그아웃]</a>
	<a href="${contextPath}/members/changePw.do">[암호변경]</a> <br> <br> 
	</div>
	<ul class="nav nav-pills nav-stacked">
		<li role="presentation"><a href="${contextPath}/guest">방명록</a></li>
		<li role="presentation"><a href="${contextPath}/board">게시판</a></li>
	</ul>
			
</body>
</html>