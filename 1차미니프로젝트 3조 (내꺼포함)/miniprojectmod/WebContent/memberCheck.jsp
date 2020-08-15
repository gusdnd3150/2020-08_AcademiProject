<%@ page language="java" contentType="text/html; charset=UTF-8" 
	import=" java.util.*,morning.*" 
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jstl/sql"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />  

<%
	request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
   <meta  charset="UTF-8">
   <title>회원 정보 출력창</title>
   <style>
		.result {
		width: 500px;
		height: 300px;
		position: absolute;
		margin-top: -150px;
		margin-left: -250px;
		top: 50%;
		left: 50%;
		text-align:center;
		}
		.btn {
		border: 1px solid #6b6b6b;
		color: #222;
		text-decoration: none;
		padding: 5px 15px;
		display: inline-block;
		margin: 5px 0;
		transition: 0.3s ease-in-out;
		-webkit-transition: 0.3s ease-in-out;
		-moz-transition: 0.3s ease-in-out;
		font-size: 13px;
		}
		.btn:hover {
			color: #fff;
			background: #1AAB8A;
			border: 1px solid #fff;
		}
	</style>
</head>
<body>
<body>
<body>
	<div class="result">
		<h2><c:out value="${param.id}" />님, 회원 가입에 성공했습니다. <br /></h2>
		<a href="main.jsp" class="btn">메인으로 가기</a> <!--  연결되야 하는 부분이라 아무거나 적어뒀음 -->
	</div>
</body>
</body>
</html>