<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
<meta charset="UTF-8">
<title>글쓰기창</title>
</head>
<body>
	<h1 style = "text-align:center">새 글 쓰기</h1>
	<form name = "articleForm" method="post" action="${contextPath}/board/addArticle.do" enctype="multipart/form-data">
		<table border = "0" align ="center">
			<tr>
				<td align = "right">제목: </td>
				<td colspan ="2"><input type = "text" size="63" maxlength="500" name ="title" ></td>
			</tr>
			<tr>
				<td align = "right" valign = "top"><br>내용: </td>
				<td colspan ="2"><textarea name ="content" rows ="10" cols ="65" maxlength="4000"></textarea></td>
			</tr>
			<tr>
				<td align = "right">비밀번호: </td>
				<td colspan ="2"><input type = "password" maxlength="500" name ="pwd" ></td>
			</tr>
			<tr>
				<td align = "right"></td>
				<td colspan = "2"><input type = "submit" value="새글등록"/>
				파일첨부 <input type = "file" name="uploadfile" value="파일선택">
				</td>		
			</tr>
		</table>
	</form>
</body>
</html>