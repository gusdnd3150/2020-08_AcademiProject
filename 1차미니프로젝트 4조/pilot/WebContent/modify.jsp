<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 글 수정 페이지. 기존 객체가 갖고 있던 값들을 미리 value로 가지고 있다. 아무것도 만지지 않으면 수정된 값 없이 그대로 전송함 -->
<form name='modifyFrm' method='get' action='book'>
<input type='hidden' name='command' value='modify2'>
<input type='hidden' name='id' value='${vo.id }'> 이름 <input
			type='text' name='name' value='${vo.name }'><br> 암호 <input
			type='password' name='pw' value='${vo.pw }'><br> 메세지 <br>
		<textarea name='msg' rows="10" cols="30">${vo.msg }</textarea>
		<input type='submit' value='수정하기'>
</form>
</body>
</html>