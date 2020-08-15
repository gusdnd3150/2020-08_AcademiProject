<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 글 작성 form -->
	<form name='frmBook' method='post' action='bookUpload'
		enctype='multipart/form-data'>
		<input type='hidden' name='command' value='write'> <img src='./img/user_2.png' width='18' height='18'> <input
			type='text' name='name' placeholder='이름'><br> <img src='./img/lock.png' width='18' height='18'> <input
			type='password' name='pw' placeholder='암호'><br> <br>
		<img src='./img/chat.png' width='18' height='18'> <br>
		<textarea name='msg' rows="10" cols="30">메세지를 입력하세요</textarea>
		<br> <img src='./img/folder.png' width='18' height='18'> <input type='file' name='file'> <br><br>
		<input type='submit' value='메세지 남기기'>
		
	</form>
	<br>
	<hr>
	<!-- hr 구분선 아랫쪽은 쓰여진 글 조회하는 부분 -->
	<br>
	<table border="1">
	<!-- 컨트롤러에서 요청받은 페이지를 리스트에 담아 정렬까지 해서 보내므로 jsp에선 그냥 forEach로 찍어주기만 하면 된다. -->
		<c:forEach var="item" items="${list }">
			<tr>
				<td width='300'><img src='./img/list.png' width='18' height='18'> ${item.id }<br> <img src='./img/profile.png' width='18' height='18'> 
					${item.name }<br> <img src='./img/chat.png' width='18' height='18'> ${item.msg }<br> <a
					href='./passwordCheck.jsp?command=delete&id=${item.id }'><img src='./img/cancel.png' width='18' height='18'>[삭제하기]</a>
					<a href='./passwordCheck.jsp?command=modify&id=${item.id }'><img src='./img/edit.png' width='18' height='18'>[수정하기]</a>
					<c:if test='${item.path != "noFile"}'>
						<a href='./download?fileName=${item.path }'><img src='./img/save.png' width='18' height='18'>[파일다운]</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<!-- 페이징 처리 부분. 컨트롤러에 보고싶은 페이지를 요청하는 방식.-->
		<tr>
			<td><c:if test="${size >0 }">
					<a href='book?page=1'>[1]</a>&nbsp;</c:if> <c:if test="${size >3 }">
					<a href='book?page=2'>[2]</a>&nbsp;
	</c:if> <c:if test="${size >6 }">
					<a href='book?page=3'>[3]</a>&nbsp;
	</c:if> <c:if test="${size >9 }">
					<a href='book?page=4'>[4]</a>&nbsp;
	</c:if> <c:if test="${size >12 }">
					<a href='book?page=5'>[5]</a>&nbsp;
	</c:if> <c:if test="${size >15 }">
					<a href='book?page=6'>[6]</a>&nbsp;
	</c:if> <c:if test="${size >18 }">
					<a href='book?page=7'>[7]</a>&nbsp;
	</c:if> <c:if test="${size >21 }">
					<a href='book?page=8'>[8]</a>&nbsp;
	</c:if> <c:if test="${size >24 }">
					<a href='book?page=9'>[9]</a>&nbsp;
	</c:if> <c:if test="${size >27 }">
					<a href='book?page=10'>[10]</a>
				</c:if></td>
		</tr>
	</table>
</body>
</html>