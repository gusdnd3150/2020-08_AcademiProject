<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import = "controller.member.*"
	import = "java.util.*"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입 완료</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<style type="text/css">
		body { padding: 20px;}
		table,tr,td {padding: 5px}
		div{ width:500px; margin-top: 10px }
	</style>	
	</head>
	<body>
<%
   request.setCharacterEncoding ("utf-8");
	String idCheck = ((String)request.getAttribute("idCheck")==null? "": (String)request.getAttribute("idCheck"));
	String pwdCheck = ((String)request.getAttribute("pwdCheck")==null? "": (String)request.getAttribute("pwdCheck"));

%>
			<form method="post" action="joinOk.jsp">
				<table>
					<tr>
						<td class="text-right">아이디</td>
						<td><input type="text" name="member_id"> <%= idCheck%>
						</td>
					</tr>
					<tr>
						<td class="text-right">이름</td>
						<td><input type="text" name="name"></td>
					</tr>
					<tr>
						<td class="text-right">비밀번호</td>
						<td><input type="password" name="password" ></td>
					</tr>
					<tr>
						<td class="text-right">비밀번호 확인</td>
						<td><input type="password" name="checkPw"> <%= pwdCheck%> </td> 
					</tr>
				</table>
				<div class="text-center">
					<input class="btn btn-primary" type="submit" value="가입하기">
				</div>
				<input type="hidden" name="command" value="join">
			</form>

	</body>
	</html>
	
