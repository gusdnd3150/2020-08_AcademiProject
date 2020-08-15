<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<% request.setCharacterEncoding("utf-8"); %>

  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method = "post" action = "/guestbook">
		메시지를 삭제하려면 암호를 입력하세요: <br>
		암호: <input type = "text" name = "password"><br>
			<input type =  "submit" value = "메시지 삭제하기">
			<input type="hidden" name="command" value="/delGuestbook.do">
			
	</form>
</body>
</html>