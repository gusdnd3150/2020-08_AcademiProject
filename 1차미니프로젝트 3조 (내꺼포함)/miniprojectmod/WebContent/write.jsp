<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<jsp:useBean id="rewrite" class="java.lang.String" scope="request"></jsp:useBean>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: 100px auto 100px auto;
	border-collapse: collapse;
}

textarea {
	resize: none;
	padding: 10px 10px 10px 10px;
	width: 900px;
	height: 700px;
	border: 1px solid #ccc;
}

textarea:focus {
	outline: 1px solid #bbb;
}

input {
	padding: 0px 10px 0px 10px;
	border-radius: 0px;
	border: 1px solid #ccc;
}

input:focus {
	outline: 1px solid #bbb;
}

input[type=submit] {
	border: 1px solid #ccc;
	float: right;
	width: 70px;
	height: 35px;
	background: #1AAB8A;
	color: #fff;
	transition: 800ms ease all;
	font-size: large;
}

input[type=submit]:hover {
	color: #1AAB8A;
	background: #fff;
}

.file_input_textbox {
	float: left
}

.file_input_div {
	position: relative;
	width: 400px;
	height: 23px;
	overflow: hidden;
	height: 23px
}

.file_input_button {
	width: 100px;
	position: absolute;
	top: 0px;
	background-color: #aaa;
	color: #fff;
	border-style: solid;
}

.file_input_hidden {
	font-size: 45px;
	position: absolute;
	right: 0px;
	top: 0px;
	opacity: 0;
	filter: alpha(opacity = 0);
	-ms-filter: "alpha(opacity=0)";
	-khtml-opacity: 0;
	-moz-opacity: 0;
}
</style>
<script>
	function submitCheck() {
		if (document.myvalue.title.value.trim() == "") {
			alert("제목을 입력해주세요.");
			return false;
		} else if (document.myvalue.password.value == "") {
			alert("비밀번호를 입력해주세요.");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body>
	<c:if test="${empty rewrite}">
		<form name="myvalue" action="post.article" method="post" onsubmit="return submitCheck()" enctype="multipart/form-data">
			<table>
				<tr>
					<td><input type="text" name="title" placeholder="제목을 입력해 주세요." style="width: 600px; height: 30px; margin-bottom: 20px;"></td>
					<td><input type="password" name="password" style="height: 30px; float: right; margin-bottom: 20px;" placeholder="패스워드"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="20" cols="1" name="content"></textarea></td>
				</tr>
				<tr>
					<td>파일1 : <input type="file" name="file">
					</td>
					<td colspan="1"><input type="submit" value="등록"></td>
				</tr>
			</table>
		</form>
	</c:if>
	<c:if test="${rewrite=='rewrite'}">
		<form name="myvalue" action="mod.article" method="post" onsubmit="return submitCheck()">
			<table>
				<tr>
					<td><input type="text" name="title" value="${article.title}" placeholder="제목을 입력해 주세요." style="width: 600px; height: 30px; margin-bottom: 20px;"></td>
					<td><input type="hidden" name="re" value="re"><input type="hidden" name="articleNo" value="${param.articleNo}"><input type="password" name="password" value="${article.password}" style="height: 30px; float: right; margin-bottom: 20px;" placeholder="패스워드"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="20" cols="50" name="content">${articleCo.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정"></td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>