<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,GuestBook.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>


<html>
<head>

<meta charset="UTF-8">
<title>방명록 목록보기</title>
</head>
<body>
<c:choose>
	<c:when test="${empty sessionScope.name}">
		<jsp:include page="../nav1.jsp" />
	</c:when>
	<c:when test="${not empty sessionScope.name}">
		<jsp:include page="../nav2.jsp" />
	</c:when>
</c:choose>
<div align="center">
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
				<td colspan="2" >
					<div>
						<h4 style="color: red">파일첨부:</h4>
						<input type="file" id="fileName"  value="파일선택" name="fileName" style="color: red">
						<input type="submit" class="btn-primary" value="메시지 남기기">
					</div>
				</td>

			</tr>
		</table>
		<input type="hidden" name="command" value="add">
	</form>
</div>
	<br>
	<hr>
	<hr>
	<br>
	<br>
	<c:if test="${empty guestbooksList }">
			등록 된 글이 없습니다.<br>
			<a href="./guestbookForm.jsp">글쓰기</a>
	</c:if>
	<c:if test="${not empty guestbooksList}">
			
			<table style="margin: 0 auto;" border="1" width="500">
		<c:forEach var="GuestbookVO" items="${guestbooksList }"
			varStatus="GuestbookNum">
				<tr>
					<td>메세지 번호: ${GuestbookVO.message_id }<br> 
						손님 이름: ${GuestbookVO.guest_name }<br> 
						메세지: ${GuestbookVO.message }<br>
						<a href = "/static/Member/delGuestbook.jsp">[삭제하기]</a>
						<a href="/static/Member/checkGuest.jsp">[수정하기]</a>
					 <c:if test="${not empty GuestbookVO.fileName}">
						<a href="/download?fileName=${GuestbookVO.fileName}">파일다운로드</a></c:if>
					</td>
				</tr>
			</c:forEach>
			</table>
			<br>
				<jsp:include page="./paging.jsp">
				<jsp:param  value="${paging.page}" name="page"/>
				<jsp:param  value="${paging.beginPage}" name="beginPage"/>
				<jsp:param  value="${paging.endPage}" name="endPage"/>
				<jsp:param value="${paging.prev}" name="prev"/>
				<jsp:param value="${paging.next}" name="next"/>
				</jsp:include>
	</c:if>
</body>
</html>
