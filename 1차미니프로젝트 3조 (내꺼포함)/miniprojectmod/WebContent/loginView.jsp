<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />  
<%
request.setCharacterEncoding("UTF-8");

%>    
<html>
<head>
<c:choose>

   <c:when test='${result=="" }'>
      <script>                   
         window.onload=function(){ // 자바스크립트로 작업 완료 후 팝업창을통해 결과 출력
            alert("회원을 등록했습니다.");
            location.href="mainlogin.jsp";
         }
      </script>
   </c:when>
   
   <c:when test='${result=="dup" }'>
      <script>
        window.onload=function(){
          alert("중복되는 아이디입니다.");
          location.href="joinIDcheck.jsp";
        }
      </script>
   </c:when>
   
   <c:when test='${result=="empty" }'>
      <script>
        window.onload=function(){
          alert("이름,비밀번호 필수입니다.");
          location.href="joinIDcheck.jsp";
        }
      </script>
   </c:when>
   
   <c:when test='${result=="pwd" }'>
      <script>
        window.onload=function(){
          alert("비밀번호가 일치하지 않습니다.");
          location.href="joinIDcheck.jsp";
        }
      </script>
   </c:when>
   
      <c:when test='${result=="success" }'>    <!-- resul값이 good일 경우 script내용 실행 -->
      <script>
        window.onload=function(){
          alert("로그인 성공");
          location.href="login2.jsp";    <!--로그인 성공 후 login2로 페이지 이동 -->
        }
      </script>
   </c:when>
   
      <c:when test='${result=="fail" }'>  <!-- resul값이 fail일 경우 script내용 실행 -->
      <script>
        window.onload=function(){
          alert("아이디 비밀번호가 일치하지 않습니다.");
          location.href="mainlogin.jsp";    <!-- 실패 후 다시 로그인화면으로 페이지 이동 -->
        }
      </script>
   </c:when>
   
     <c:when test='${result=="true" }'>
      <script>
        window.onload=function(){
          alert("테스트");
          location.href="search.article";
        }
      </script>
   </c:when>
        <c:when test='${result=="false" }'>
      <script>
        window.onload=function(){
          alert("로그인 후 이용가능합니다");
          location.href="mainlogin.jsp";
        }
      </script>
   </c:when>

   
</c:choose>

   <meta  charset="UTF-8">
   <title>회원 정보 출력창</title>
<style>
     .cls1 {
       font-size:40px;
       text-align:center;
     }
    
     .cls2 {
       font-size:20px;
       text-align:center;
     }
  </style>
  
</head>
<body>
  

</body>
</html>