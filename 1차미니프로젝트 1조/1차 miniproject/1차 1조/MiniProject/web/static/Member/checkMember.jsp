<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
      <c:when test="${command=='NotDel'}">
         <c:out value="잘못된 정보입니다. 아이디와 비밀번호를 다시 확인해주세요" /><br>	
         <a href="/static/Member/delMember.jsp">[돌아가기]</a>
         </c:when>
         <c:when test="${command=='OkDel'}">
         <c:out value="회원 탈퇴가 완료 되었습니다." /><br>
         <a href="/static/main.jsp">[메인 화면으로]</a>
      </c:when>
       <c:when test="${command=='NotPassword'}">
         <c:out value="잘못된 비밀번호 입니다." /><br>
         <a href="/static/Member/logIn.jsp">[로그인 화면으로 이동]</a>
      </c:when>
       <c:when test="${command=='OkPassword'}">
         <c:out value="비밀번호 변경이 완료 되었습니다. 다시 로그인 해주세요" /><br>
         <a href="/static/Member/logOut.jsp">[메인 화면으로]</a>
      </c:when>
</c:choose> 
</body>
</html>