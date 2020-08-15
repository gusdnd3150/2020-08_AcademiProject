<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.*"
    import="java.util.*"
    %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--pageContext 내장객체의 컨텍스트 이름을 변수 contextPath에 미리 설정 --%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
    <%
 MemberVO vo = new MemberVO(); 
 vo = (MemberVO)session.getAttribute("loginUser"); // 로그인 성공 후 MemberVO에 담은 정보(ID,PW,NAME) 가져오기
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
   <!-- 비밀번호 변경 정보 MemberController에 changePwd로 전달 -->
   <form method="get" action="${contextPath}/member/changePwd.do">
   <input type='hidden' name='id' value='<%=vo.getMember_id()%>'>
   <input type='hidden' name='pw' value='<%=vo.getPassword()%>'>
   <table>
      <tr>
         <td>
            <p><img src='${contextPath}/img/lock2.png' width='18' height='18'> 
         </td>
         <td><input type="password" name="crtPw" placeholder="현재암호"></td>
      </tr>
      <tr>
         <td>
            <p><img src='${contextPath}/img/lock3.png' width='18' height='18'> 
         </td>
         <td><input type="password" name="newPw" placeholder="새 암호"></td>
      </tr>
      <tr>
         <td>
            <p>&nbsp;</p>
         </td>
         <td>
         <input type="submit" value="암호변경"><br>
         <!--  암호변경이 성공적으로 처리되면 로그인 후 초기Page로 이동한다-->
         </td>
      </tr>
      </table>
   </form>
</body>
</html>