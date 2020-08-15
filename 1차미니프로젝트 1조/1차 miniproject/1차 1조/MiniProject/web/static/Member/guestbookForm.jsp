<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<form method="post" action="/upload" enctype="multipart/form-data">
		<table>
			<tr>
				<td>이름: </td>
				<td><input type="text" name="guest_name" required></td>
			</tr>
			<tr>
				<td>암호: </td>
				<td><input type="text" name="password" required></td>
			</tr>
			<tr>
				<td>메시지:</td>
				<td><textarea name=message rows="5" cols="40" required></textarea></td>
			</tr>
			</table>
			<table>
			<tr>
				<td><input type="submit" value="메시지 남기기"></td>
				<td>&nbsp;&nbsp;</td>
				<td>
					<h4 style="color: red">파일첨부:</h4>
				</td>
				<td><input type="file" id="fileName"  value="파일선택" name="fileName"
					style="color: red"></td>
			</tr>
		</table>
		<input type="hidden" name="command" value="add">
	</form>
</body>
</html>