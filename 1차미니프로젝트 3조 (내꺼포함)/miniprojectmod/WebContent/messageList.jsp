<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" java.util.*,morning.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="file" value="${param.param }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 등록</title>
<style>
	label {
		width: 100px;
		display: inline-block;
		vertical-align: top;
	}
	.result, textarea {
		background: #f4f5f4;
		border: none;
		margin-bottom: 10px;
		width: 200px;
		display: inline-block;
	}
	input[type="text"]{
		background: #f4f5f4;
		border: none;
		margin-bottom: 10px;
		width: 200px;
		display: inline-block;
	}
	
	input[type="password"]{
		background: #f4f5f4;
		border: none;
		margin-bottom: 10px;
		width: 200px;
		display: inline-block;
	}
	
	.result {
		background: #1AAB8A;
		color: #fff;
		width: 300px;
		margin-top: 30px;
		border: 1px solid #fff;
		padding: 10px;
	}
	
	.result:hover {
		background: #fff;
		color: #000;
		border: 1px solid #1AAB8A;
	}
	
	.result {
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
	
	.btn  {
		border: 1px solid #6b6b6b;
		color: #222;
		text-decoration: none;
		padding: 6px;
		display: inline-block;
		margin: 5px 0;
		transition: 0.3s ease-in-out;
		-webkit-transition: 0.3s ease-in-out;
		-moz-transition: 0.3s ease-in-out;
	}
	.btn.down {
		border: 1px solid #ebebeb;
	}
	.btn.down:hover {
		border: 1px solid #222;
		background: #fff;
		color: #222;
	}
	.btn:hover {
		color: #fff;
		background: #1AAB8A;
		border: 1px solid #fff;
	}	
	.tit {
		color: #222;
	}
	.desc {
		color: #666;	
	}
	.page {
		text-decoration: none;	
	}
</style>
</head>
<body>
	<h1 style="font-size: 30px; text-align: center; margin-bottom: 20px;">방명록</h1>
	<form method="post" enctype="multipart/form-data" action="${contextPath}/message/messageWriteResult.do" style="text-align:center;">
		<label for="name">이름 : </label><input type="text" name="name" id="name"><br>
		 <label for="password">암호 : </label><input type="password" name="password" id="password"><br>
		 <label for="message">메시지 : </label><textarea name="message" style="height: 200px" id="message"></textarea><br>
		 <label for="file" style="vertical-align: middle;">파일 첨부 : </label> <input type="file" name="file" style="margin-top:20px;" id="file"><br>
		 <input	type="submit" value="메시지 남기기" class="result">
	</form>
	<table class="table">
		<c:choose>
			<c:when test="${empty messageList }">
				<tr>
					<td colspan="2" style="font-weight: bold; text-align: center;">등록된
						방명록이 없습니다.</td>
				</tr>
			</c:when>
			<c:when test="${messageList != null }">
				<c:forEach var="message" items="${messageList }">
					<tr class="tr">
						<td class="tit">메시지 번호 :</td>
						<td class="desc" colspan="2">${message.messageId}</td>
					</tr>
					<tr class="tr">
						<td class="tit">손님 이름 :</td>
						<td class="desc" colspan="2">${message.guestName}</td>
					</tr>
					<tr class="tr">
						<td class="tit">메시지 :</td>
						<td class="desc" colspan="2">${message.message}</td>
					</tr>
					<tr class="tr">
						<td><a href="${contextPath }/message/deleteCheckForm.do?mId=${message.messageId}" class="btn delete">삭제하기</a></td>
						<td><a href="${contextPath }/message/editCheckForm.do?mId=${message.messageId}" class="btn edit">수정하기</a></td>
						
						<c:if test="${not empty message.fileName}">
						<td><a href="/pro0721study1/Downloadre?fileName=${message.fileName}" class="btn down">파일 다운</a></td></c:if>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<div style="text-align: center; margin-top: 30px;">
		<c:forEach var="page" begin="1"
			end="${totMessage%3==0? totMessage/3 : totMessage/3+1}">
			<a href="${contextPath}/message/messageList.do?pageNum=${page}" class="page">${page}</a>
		</c:forEach>
	</div>
</body>
</html>