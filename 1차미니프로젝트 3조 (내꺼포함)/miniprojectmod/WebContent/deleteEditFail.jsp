<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" java.util.*,morning.*"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
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
		width: 300px;
		height: 300px;
		position: absolute;
		margin-top: -150px;
		margin-left: -150px;
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
		margin: 20px 0;
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
	<div class="result">
		입력한 암호가 올바르지 않습니다.<br> 암호를 확인해주세요.<br>
		<a href="${contextPath }/message/messageList.do" class="btn">목록 보기</a>
	</div>
</body>
</html>