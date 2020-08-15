<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<html>
  <head>
<%--   <c:choose>
   <c:when test='${requestScope.msg=="addMember" }'>
      <script>
         window.onload=function(){
            alert("회원 등록 되었습니다.");
         }
      </script>
   </c:when>
   <c:when test='${requestScope.msg=="updatePwd" }'>
      <script>
        window.onload=function(){
          alert("회원 암호 변경 되었습니다.");
        }
      </script>
   </c:when>
   <c:when test= '${requestScope.msg=="delMember" }'>
      <script>
         window.onload=function(){
            alert("회원 탈퇴 되었습니다.");
        } 
      </script>
</c:when>
</c:choose> --%>
    <title>초기Page</title>
  </head>
  <body>
		<h1><a href="/MiniProject/guestbookForm.jsp">방명록</a></h1>
		<h1><a href="">게시판</a></h1>
		<h1><a href="/MiniProject/logIn.jsp">로그인</a></h1>
		<h1><a href="/MiniProject/addMember.jsp">회원가입</a></h1>
  </body>
</html>
