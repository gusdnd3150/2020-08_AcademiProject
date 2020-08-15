<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name='passInputFrm' method='get' action='book'>
<!-- 글 수정이나 삭제 모두 하나의 페이지로 해결하기 위해 커맨드값을 받아와서 다시 submit함 -->
<input type='hidden' name='command' value='${param.command }'>
<input type='hidden' name='id' value='${param.id }'>
<b>비밀번호를 입력하세요</b><br>
<input type='password' name='pw'>
<input type='submit' value='확인'>
</form>
</body>
</html>