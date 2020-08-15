<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style type="text/css">
	body{ padding: 20px; }
	div.page {margin: 5px auto; width:500px};
	a { float: right; margin-right: 4px;}
	a.page{ float: left; margin: 0 auto; }
	.form td { padding: 4px; text-align: center; }
	table {width: 500px; margin: 0 auto;}
	table.contents{ line-height: 200%; border: 1px solid lightgray }
	.contents td { padding: 10px; border: 1px solid lightgray}
</style>
</head>
<body>
	<form action="${contextPath}/guest/add.do" method="post" enctype="multipart/form-data">
		<table class="form">
			<tr>
				<td>이름</td>
				<td><input type="text" class="form-control" name="guest_name"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" class="form-control" name="password"></td>
			</tr>
			<tr>
				<td>메세지</td>
				<td>
					<textarea name="message" class="form-control" rows="10"></textarea>
				</td>
			</tr>
			<tr>
				<td>파일첨부</td>
				<td> <input class="file" type="file" name="file"></td>
			</tr>
			<tr>
				<td colspan="2"><input class="btn btn-primary" type="submit" value="메시지 남기기"></td>
			</tr>
		</table>
		
		
	</form>
	<hr>
	<table class="contents">
		<c:forEach var="vo" items="${requestScope.list}">
			<tr>
				<td>
					<strong>메시지 번호: ${vo.message_id}</strong><br>
					손님 이름: ${vo.guest_name} <br>
					메시지: ${vo.message}<br>
					<a class="btn btn-outline-danger" href="${contextPath}/guest/del.do?message_id=${vo.message_id}" role="button">삭제하기</a>
					<a class="btn btn-outline-primary" href="${contextPath}/guest/edit.do?message_id=${vo.message_id}" role="button">수정하기</a>
					<c:if test="${ vo.fileName != null }">
						<a href="${contextPath}/guest/downdload.do?fileName=${vo.fileName}">파일다운</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="page">
	<c:forEach var="page" begin="1" end="${ totalGuestbook%3==0? totalGuestbook/3 : totalGuestbook/3+1}" step="1" >
			<a class="page" href="${contextPath}/guest/search.do?pageNum=${page}">[ ${page} ] </a> &nbsp;			
	</c:forEach>
	</div>
	
</body>
</html>