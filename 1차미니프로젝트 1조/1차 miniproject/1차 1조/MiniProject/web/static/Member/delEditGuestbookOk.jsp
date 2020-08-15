<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
<c:choose>
   <c:when test="${empty sessionScope.name}">
      <jsp:include page="../nav1.jsp" />
   </c:when>
   <c:when test="${not empty sessionScope.name}">
      <jsp:include page="../nav2.jsp" />
   </c:when>
</c:choose>
   <c:choose>
      <c:when test="${command=='ok'}">
         <c:out value="방명록 삭제가 완료 되었습니다 " /><br>
      
         <a href="/guestbook?command='/listGuestbooks.do'">[목록보기]</a>
         </c:when>
         <c:when test="${command=='notok'}">
         <c:out value="입력한 암호가 올바르지 않습니다. 암호를 확인해주세요. " /><br>
         <a href="/guestbook?command='/listGuestbooks.do'">[목록보기]</a>
      </c:when>
   </c:choose>   
</body>
</html>