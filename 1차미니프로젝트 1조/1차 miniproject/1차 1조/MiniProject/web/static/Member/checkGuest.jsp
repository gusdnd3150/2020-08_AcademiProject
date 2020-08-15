<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method = "post" action = "/guestbook">
	메시지를 수정하시려면 암호를 입력하세요.<br>
	암호: <input type = "text" name = "password"><br>
		<input type = "submit" value = "메시지 수정하기">
		<input type="hidden" name="command" value="/checkGuest.do">
	</form>
</body>
</html>