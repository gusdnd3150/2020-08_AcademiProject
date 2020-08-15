<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="./addMember.js"></script>
</head>
<body>
<jsp:include page="../nav1.jsp" />
	<div class="container">
		<div class="col-sm-6 col-md-offset-3">
			<h3>회원가입</h3>
			<form role="form">
				<div class="form-group">
					<label for="member_id">아이디:</label>
					<div class="input-group">
						<input type="text" id="member_id">   <input type="button" class="btn" id="checkId" value="중복 확인"><label id="result" style="color: red"></label>
					</div>
				</div>
				<div class="form-group">
					<label for="name">이름:</label>
					<div class="input-group">
						<input type="text" id="name">
					</div>
				</div>
				<div class="form-group">
					<label for="password">암호:</label>
					<div class="input-group">
						<input type="password" id="password">
					</div>
				</div>
				<div class="form-group">
					<label for="confirm">확인:</label>
						<div class="input-group">
							<input type="password" id="confirm">
						</div>
				</div>
				<div class="form-group">
					<input type="button" class="btn btn-primary" id="join-submit" value="가입">
					<input type="hidden" id="command" value="addMember.do">
					<input type="hidden" id="check" value="check.do">
					<input type="hidden" id="idCheckResult" value="0">
				</div>
			</form>
		</div>
	</div>
</body>
</html>

