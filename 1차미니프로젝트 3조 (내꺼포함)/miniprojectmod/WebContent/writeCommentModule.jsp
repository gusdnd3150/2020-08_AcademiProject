<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.commentTable {
	width: 900px;
	height: 150px;
	border-collapse: collapse;
	margin: 30px auto 0px auto;
}

.textarea {
	width: 700px;
	height: 80px;
	padding: 5px 5px 5px 5px;
	margin: 0 0px 0 -1px;
	float: right;
}

table td {
	padding: 0px 0px 0px 0px;
}

.submit {
	width: 90px;
	height: 90px;
}
</style>
</head>
<body>
	<form action="writeComment.article" method="post">
		<table class="commentTable">
			<tr>
				<td style="width: 760px;"><textarea class="textarea" name="postComment" style="resize: none;"></textarea></td>
				<td><input type="hidden" value="${param.articleNo}" name="articleNo"><input class="submit" type="submit" value="등록"></td>
			</tr>
		</table>
	</form>
</body>
</html>