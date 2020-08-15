<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style type="text/css">
	body { padding: 20px;}
	table,tr,td {padding: 5px}
	div{ width:500px; margin-top: 10px }
</style>
</head>
<body>
	<form method="post" action="joinOk.jsp">
		<table>
			<tr>
				<td class="text-right">아이디</td>
				<td><input type="text" name="member_id">
				<!--  <input	type="button" value="ID중복확인" onclick="idCheck()"> -->
				</td>
			</tr>
			<tr>
				<td class="text-right">이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td class="text-right">비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td class="text-right">비밀번호 확인</td>
				<td><input type="password" name="checkPw"></td>
			</tr>
		</table>
		<div class="text-center">
			<input class="btn btn-primary" type="submit" value="가입하기">
		</div>
			<input type="hidden" name="command" value="join">
			
	</form>
</body>
</html>