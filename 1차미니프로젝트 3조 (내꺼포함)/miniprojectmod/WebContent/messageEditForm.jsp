<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" java.util.*,morning.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
	label {
		width: 100px;
		display: inline-block;
		vertical-align: top;
	}
	input, textarea {
		background: #f4f5f4;
		border: none;
		margin-bottom: 10px;
		width: 200px;
		display: inline-block;
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
	
	.table {
		border: 1px solid #d2d2d2;
		padding: 20px;
		width: 300px;
		height: 200px;
		margin: 40px auto;
	}
	
	td {
		font-size: 14px;
		margin: 5px 0;
	}
	
	.tr {
		border: none;
	}
	
	.btn {
		border: 1px solid #6b6b6b;
		color: #222;
		text-decoration: none;
		padding: 5px;
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
	</style>
</head>
<body>
	<h1 style="font-size: 30px; text-align: center; margin-bottom: 20px;">메시지 수정하기</h1>
	<form method="post" style="text-align:center;" action="${contextPath}/message/editSuccess.do">
		<label for="name">이름 : </label><input type="text" name="name" id="name" value="${messageList2.get(0).getGuestName() }"><br>
		 <label for="password">암호 : </label><input type="password" name="password" id="password" value="${messageList2.get(0).getPassword() }"><br>
		 <label for="message">메시지 : </label><textarea name="message" style="height: 200px" id="message">${messageList2.get(0).getMessage() }</textarea><br>
		 <input type="hidden" name="id3" value="${id}"><br>
		 <input	type="submit" value="메시지 수정하기">
	</form>
</body>
</html>