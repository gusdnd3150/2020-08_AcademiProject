<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" java.util.*,morning.*"
	pageEncoding="UTF-8"
	isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%
request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.result {
		width: 400px;
		height: 300px;
		position: absolute;
		margin-top: -150px;
		margin-left: -200px;
		top: 50%;
		left: 50%;
		text-align:center;
		}
		.btn {
		border: 1px solid #6b6b6b;
		color: #222;
		text-decoration: none;
		padding: 0 15px;
		display: inline-block;
		margin: 5px 0;
		transition: 0.3s ease-in-out;
		-webkit-transition: 0.3s ease-in-out;
		-moz-transition: 0.3s ease-in-out;
		}
		.btn:hover {
			color: #fff;
			background: #1AAB8A;
			border: 1px solid #fff;
		}
		input[type="submit"] {
		background: #1AAB8A;
		color: #fff;
		width: 300px;
		margin-top: 30px;
		border: 1px solid #fff;
		padding: 10px;
	}
	
	input[type="submit"]:hover {
		background: #fff;
		color: #000;
		border: 1px solid #1AAB8A;
	}
	
	input[type="submit"] {
		transition: 0.3s ease-in-out;
		-webkit-transition: 0.3s ease-in-out;
		-moz-transition: 0.3s ease-in-out;
	}
	</style>
</head>
<body>
	<div class="result">
		<p>메시지를 삭제하시려면 암호를 입력하세요.</p>
		<form action="${contextPath}/message/deleteSuccess.do">
			암호 : <input type="password" name="password">
			<input type="hidden" name="id2" value="${param.mId}"><br>
			<input type="submit" value="메시지 삭제하기">
		</form>
	</div>
</body>
</html>