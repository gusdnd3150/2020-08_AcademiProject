<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import ="java.util.*,morning.*"%>
    
    
    
    <%
    request.setCharacterEncoding("utf-8");
    MemberVO membersList = (MemberVO)session.getAttribute("member");
    String pass = membersList.getPassword();
    %>
    
  <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/javascript" >
   function check(){	
      var frmLogin=document.frmLogin;
      var curentPass=frmLogin.curentPass.value;
      var modiPass=frmLogin.modiPass.value;
      var modicheck=frmLogin.modicheck.value;
      
      if(curentPass != <%=pass%> ) {
	 alert("현재 비밀번호가 잘못 되었습니다.");
      }else if(curentPass ==<%=pass%>&& modiPass ==modicheck){
	frmLogin.method="get";
	frmLogin.action="MemberController";
	frmLogin.submit();
      }else if ( curentPass ==<%=pass%> && modiPass != modicheck){
    	  alert("비밀번호가 일치하지 않습니다.");	  
      }
   }
</script>


<title>비밀번호 변경창</title>
</head>
<body>


   
<form name ="frmLogin" method="get" action="MemberController"  encType="UTF-8">
 <table align="center" >
   <tr>
     <td width="200"><p align="right" >현재 비밀번호</td> 
     <td width="400"><input   type="password" name="curentPass" ></td>
   </tr>
 <tr>
     <td width="200"><p align="right" >새로운 비밀번호</td>
     <td width="400"><input   type="password" name="modiPass"  >
     </td>
   </tr>
    <tr>
     <td width="200"><p align="right" >비밀번호 확인</td>
     <td width="400"><input   type="password" name="modicheck"  >
     </td>
   </tr>
   <tr align="center" >
    <td colspan="2" width="400"> <input type="button" onClick="check()" value="변경">
       <input type="reset" value="다시입력" > 
       <input type ="hidden" name ="command" value ="modify"></td>
   </tr>
 </table>
</form>
  
  
  
  
</body>
</html>



