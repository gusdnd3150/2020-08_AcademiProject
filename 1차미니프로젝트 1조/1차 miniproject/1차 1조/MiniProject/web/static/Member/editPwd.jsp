<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호 변경</title>
<script type="text/javascript">
	function pwdCheck() {
		var pwdCheck = document.editPwd;
		var password = pwdCheck.password.value;
		var newPassword = pwdCheck.newPassword.value;
		if (password.length == 0 || password == "") {
			alert("비밀번호는 필수입니다");
		} else if (newPassword.length == 0 || newPassword == "") {
			alert("새 비밀번호는 필수입니다");
		} else {
			editPwd.method = "post";
			editPwd.action = "/member";
			editPwd.submit();
		}
	}
	</script>
</head>
<body>
<div class="container">
	<div class="col-sm-6 col-md-offset-3">
		<h3>암호 변경</h3>
		<form name="editPwd" action="/member" method="post">
			<div class="form-group">
				<label for="password">현재 암호</label>
				<div class="input-group">
					<input type="password" id="password" name="password"><br>
				</div>
			</div>
			<div class="form-group">
				<label for="newPassword">새 암호</label>
				<div class="input-groupn">
				<input type="password" id="newPassword" name="newPassword">
				</div>
			</div>
			<div class="form-group">
				<input type="button" class="btn btn-primary" value="변경" onclick="pwdCheck()">
				<input type="hidden" name="command" value="editPwd.do" />
			</div>
		</form>
	</div>
</div>
</body>
</html>