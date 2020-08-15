<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html>
<html>
<head>
    

<meta charset="UTF-8">

<title> 로그인창 </title>
<style>
	.result {
		width: 300px;
		height: 300px;
		position: absolute;
		margin-top: -150px;
		margin-left: -150px;
		top: 40%;
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
</head>
<body>
<div class="result">
 <h1 class="cls1">로그인</h1>
 
 
<form  method="get" action="MemberController"  encType="UTF-8">
 <table align="center" >
   <tr>
     <td width="200"><p align="right" >아이디 : </td> 
     <td width="400"><input   type="text" name="_id" ></td>
   </tr>
 <tr>
     <td width="200"><p align="right" >비밀번호 : </td>
     <td width="400"><input   type="password" name="_pwd"  >
     </td>
   </tr>
   <tr align="center" >
    <td colspan="2" width="400"><input type="submit"  value="로그인" >
       <input type="reset" value="다시입력" > 
       <input type ="hidden" name ="command" value ="logins"></td>
   </tr>
 </table>
</form>
</div>

</html>