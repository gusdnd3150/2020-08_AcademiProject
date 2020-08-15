<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,morning.*"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메인페이지</title>
	<style>
		h1 {
			text-align: center;
		}
		form {
			text-align: center;
			margin-top: 100px;
		}
		a {
			padding: 10px;
			color: #222;
			position: relative;
			margin-bottom: 20px;
			display: inline-block;
			transition: 0.5s ease-in-out;
			-webkit-transition: 0.3s ease-in-out;
			-moz-transition: 0.3s ease-in-out;
			text-decoration: none;
		}
		a:after {
			display: block;
			content: "";
			position: absolute;
			width: 0;
			height: 3px;
			left:0;
			bottom:0;
			background: #1aab8a;
			transition: 0.4s ease-in-out;
			-webkit-transition: 0.4s ease-in-out;
			-moz-transition: 0.4s ease-in-out;
		}
		a:hover:after {
			width: 80%;
			left: 50%;
			margin-left: -40%;
			height: 3px;
			background: #1aab8a;
		}
		.mainBox {
			width: 500px;
			height: 500px;
			position: absolute;
			top: 50%;
			left: 50%;
			margin-left: -250px;
			margin-top: -250px;
			border: 1px solid #ebebeb;
		}
	</style>
</head>
<body>
<div class="mainBox">
	<h1 style="margin-top: 50px">MAIN</h1>
	<form method="post" action="main" encType="UTF-8">
		<a href="/pro0721study1/message/messageList.do">방명록</a><br>
		<a href="/pro0721study1/MemberController?command=enter">게시판</a><br>
		<a href="mainlogin.jsp">로그인</a><br>
		<a href="memberForm.jsp">회원가입</a><br>
	</form>
</div>
</body>
</html>