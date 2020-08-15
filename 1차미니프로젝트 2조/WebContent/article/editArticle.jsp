<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>${contentList.getTitle() }</title>
</head>
<body>
<h1 style = "text-align:center">수정하기</h1>
	<form name = "editForm" method="post" enctype="multipart/form-data" action="${contextPath}/board/confirmArt.do">
		<table border = "0" align ="center">
			<tr>
				<td align = "right">번호: ${article_no} </td><br>
			</tr>
			<tr>
				<td align = "right">제목: </td><br>
				<td colspan ="2"><input type = "text" size="63" maxlength="500" name ="title" value="${contentList.getTitle() }"></td>
			</tr>
			<tr>
				<td align = "right" valign = "top"><br>내용: </td><br>
				<td colspan ="2"><textarea name ="content" rows ="10" cols ="65" maxlength="4000">${contentList.getContent()}</textarea></td>
			</tr>
			<tr>
				<td align = "right"></td>
				<td colspan = "2">
				<input type = "submit" value=글수정>
				
				파일첨부 <input type="file" name="file_name">
				
				<input type="hidden" name="article_no" value="${article_no}">
				<input type="hidden" name="content" value="${contentList.getContent() }">
				<input type="hidden" name="old_file_name" value="${contentList.getFile_name() }">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>