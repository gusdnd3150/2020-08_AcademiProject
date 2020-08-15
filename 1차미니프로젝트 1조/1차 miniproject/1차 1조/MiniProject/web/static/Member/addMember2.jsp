<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%--  <jsp:useBean id="dao" class="Member.MemberDAO"></jsp:useBean>
    <%
    boolean check1 = false;
    check1 = (Boolean)request.getAttribute("idDup");%>
    <%
    boolean check2 = false;
    check2 = (Boolean)request.getAttribute("pwdDup");%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>


</head>
<body>
	<form name="addMemberForm" method="post" action="/member">
		이미 사용중인 아이디이거나 암호와 확인이 일치하지 않아. <br>
	둘 중 하나가 문제이니 알아서 잘 확인하고 입력하아아아아아~~
		<table>
			<tr>
			<tr>
				<td>아이디:</td>
				<td><input type="text" name="member_id"></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>암호:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>확인:</td>
				<td><input type="password" name="confirm"></td>

			</tr>
		</table>
		<input type="submit" value="가입">
		<input type="hidden" name="command" value="addMember.do">
	</form>
</body>
</html>

