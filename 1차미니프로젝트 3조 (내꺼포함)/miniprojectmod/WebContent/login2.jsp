<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import ="java.util.*,morning.*"
    %>
    
    
    
    <%
     request.setCharacterEncoding("utf-8");
    MemberVO membersList = (MemberVO)session.getAttribute("member");
    String name = membersList.getName(); 
    %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
	<title> 로그인 성공 페이지 </title>
	</head>
	<style>
	.result {
		width: 800px;
		height: 500px;
		position: absolute;
		margin-top: -250px;
		margin-left: -400px;
		top: 50%;
		left: 50%;
		text-align:center;
		}
  .cls1 {
     font-size:40px;
     text-align:center;
   }
   label {
		width: 100px;
		display: inline-block;
		vertical-align: top;
	}
	input, textarea {
		background: #f4f5f4;
		border: none;
		margin-bottom: 10px;
		width: 200px;
		display: inline-block;
	}
	input[type="submit"] {
		background: #1AAB8A;
		color: #fff;
		width: 300px;
		margin-top: 30px;
		border: 1px solid #fff;
		padding: 10px;
	}
	
	input[type="reset"] {
		background: #ebebeb;
		color: #222;
		width: 300px;
		margin-top: 30px;
		border: 1px solid #fff;
		padding: 10px;
		transition: 0.3s ease-in-out;
		-webkit-transition: 0.3s ease-in-out;
		-moz-transition: 0.3s ease-in-out;
	}
	
	input[type="reset"]:hover {
		border: 1px solid #222;
		background: #fff;
	}
	
	input[type="submit"]:hover {
		background: #fff;
		color: #000;
		border: 1px solid #1AAB8A;
	}
	
	input[type="submit"] {
		transition: 0.3s ease-in-out;
		-webkit-transition: 0.3s ease-in-out;
		-moz-transition: 0.3s ease-in-out;
	}
	
	.table {
		border: 1px solid #d2d2d2;
		padding: 20px;
		width: 300px;
		height: 200px;
		margin: 40px auto;
	}
	
	td {
		font-size: 14px;
		margin: 5px 0;
	}
	
	.tr {
		border: none;
	}
	
	.btn {
		border: 1px solid #6b6b6b;
		color: #222;
		text-decoration: none;
		padding: 5px;
		display: inline-block;
		margin: 5px 0;
		transition: 0.3s ease-in-out;
		-webkit-transition: 0.3s ease-in-out;
		-moz-transition: 0.3s ease-in-out;
	}
	
	.btn:hover {
		color: #fff;
		background: #1AAB8A;
		border: 1px solid #fff;
	}
</style>
<body>
<div class="result">
          <h1  align="center"> 로그인을 축하드립니다. <%=name %> 님 </h1><br>
          
              <form action="MemberController" method ="get">    <!-- 로그아웃선택 시 컨트롤러로 요청값 전달  -->
              <table align ="center">
              <input type= "submit" value="로그아웃" />                       
              <input type= "hidden" name ="command" value ="logout" >
              </table>
              </form>
              <br>
              
              <form action="modifyInformation.jsp" method ="get">   <!-- 암호 변경 선택 시  암호변경 페이지로  -->
              <table align ="center">
              <input type= "submit" value="암호 변경" />
              </table>
              </form>
              <br>
              
              <form action="${contextPath}/message/messageList.do" method ="get">
              <table align ="center">
              <input type="submit" value="방명록">
              </table>
              </form>
               <br>
               
              <form action="search.article" method ="post">
              <table align ="center">
              <input type= "submit" value="게시판" />
              <input type= "hidden" name ="command" value ="search" >
              </table>
              </form>
               <br>
               </div>
              
</body>
</html>