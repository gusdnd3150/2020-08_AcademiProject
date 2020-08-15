<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<jsp:include page="../nav1.jsp" />
<div class="container">
	<div class="col-sm-6 col-md-offset-3">
		<h3>로그인</h3>
		<form action="/Login" method="post">
			<div class="form-group">
				<label for="member_id">아이디:</label>
				<div class="input-group">
					<input type="text" id="member_id" name="member_id" >
				</div>
			</div>
			<div class="form-group">
				<label for="password">암호:</label>
				<div class="input-group">
					<input type="password" id="password" name="password" >
				</div>
			</div>
		<br>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="로그인">
				<input type="hidden" name="command" value="loginCheck.do">
			</div>
		</form>
	</div>
</div>
</body>
</html>