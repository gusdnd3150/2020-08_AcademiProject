<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴창</title>
<script type="text/javascript">
	function Check() {
		var Check = document.delMember;
		var member_id = Check.member_id.value;
		var password = Check.password.value;
		if (member_id.length == 0 || member_id == "") {
			alert("아이디 입력은 필수입니다. 입력하세욧!!");
		} else if (password.length == 0 || password == "") {
			alert("비밀번호는 입력은 필수입니다. 입력하세욧!!");
		} else {
			delMember.method = "post";
			delMember.action = "/member";
			delMember.submit();
		}
	}
	</script>
</head>
<body>
<jsp:include page="../nav2.jsp" />
<div class="container">
	<div class="col-sm-6 col-md-offset-3">
		<h3>회원탈퇴</h3>
			<form name="delMember" action="/member" method="post">
				<div class="form-group">
					<label for="id">아이디</label>
					<div class="input-group">
						<input type="text" id="id" name="member_id">
					</div>
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label>
					<div class="input-group">
						<input type="password" id="password" name="password">
					</div>
				</div>
				<div class="form-group">
					<input type="button" class="btn-danger" value="탈퇴" onclick="Check()">
					<input type="hidden" name="command" value="delMember.do" />
				</div>
	</form>
</body>
</html>