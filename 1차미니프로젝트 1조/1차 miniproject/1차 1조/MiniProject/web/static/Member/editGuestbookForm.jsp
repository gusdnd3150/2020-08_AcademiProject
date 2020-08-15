<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<% request.setCharacterEncoding("utf-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 수정</title>
</head>
<body>
  <form method="post" action="/upload" enctype="multipart/form-data">
    
    메세지 번호: <input type = "text" name = "message_id" value ="${guestInfo.message_id }"><br>
      손님 이름: <input type = "text" name = "guest_name" value ="${guestInfo.guest_name }"><br>
      메시지:    <textarea name=message rows="5" cols="40" required>${guestInfo.message }</textarea><br>
      <h4 style="color: red">파일첨부:</h4></td>
      <input type="file" name="fileName" value="파일선택" style="color:red">
            <input type = "submit" value = "수정완료">
            <input type = "reset" value = "다시입력">
            <input type="hidden" name="command" value="editGuestbook.do">
   </form>
</body>
</html>
</html>