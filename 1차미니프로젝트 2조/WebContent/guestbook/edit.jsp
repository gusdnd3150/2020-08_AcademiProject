<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 수정</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style type="text/css">
	body{ padding: 20px }
	.form td { padding: 4px; text-align: center; }
	table {width: 500px; }
</style>
</head>
<body>
	<form action="${contextPath}/guest/editCompl.do" method="post" enctype="multipart/form-data">
		<table class="form">
			<tr>
				<td>이름</td>
				<td><input type="text" class="form-control" name="guest_name" value="${vo.guest_name}"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" class="form-control" name="password" value="${vo.password}"></td>
			</tr>
			<tr>
				<td>메세지</td>
				<td>
					<textarea name="message" class="form-control" rows="10">${vo.message}</textarea>
				</td>
			</tr>
			<tr>
					<td>파일첨부: <input class="file" type="file" name="file" ></td>
			</tr>
			<tr>
				<td><input class="btn btn-primary" type="submit" value="수정하기">
				<input type="hidden" name="message_id" value="${vo.message_id}">
				<input type="hidden" name="old_file" value="${vo.fileName}">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>