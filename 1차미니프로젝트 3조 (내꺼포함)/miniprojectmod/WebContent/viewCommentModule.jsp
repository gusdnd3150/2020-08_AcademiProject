<%@page import="morning.postCommentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="commentList" class="java.util.ArrayList" type="java.util.ArrayList<morning.postCommentVO>" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.tablepattern {
	width: 900px;
	margin: 30px auto 0 auto;
	border-collapse: collapse;
}

table td {
	margin: 6px 0 6px 0;
	padding: 5px 10px 5px 10px;
}

input.img-button {
	background: url("Download?fileName=x.png") no-repeat;
	border: none;
	width: 15px;
	height: 15px;
	cursor: pointer;
}
</style>
</head>
<body>
	<table class="tablepattern">
		<c:forEach items="${commentList}" var="comment">
			<tr style="border-bottom: 1px solid #eee;">
				<td style="width: 120px; padding-left: 10px;">${comment.writerId}</td>
				<td>${comment.postComment}</td>
				<td style="float: right; padding-right: 5px; width: 120px; float: right; text-align: right;">${comment.regdate}</td>
				<td style="width: 20px; margin: 0 0 0 0; padding: 0 0 0 0;"><form action="deleteComment.article" method="post">
						<input type="hidden" value="${param.articleNo}" name="articleNo">
						<input type="hidden" value="${comment.postCommentNo}" name="commentNo">
						<input class="img-button"  type="submit" value="" style="border: 1px solid #ccc; border-radius: 0 0 0 0; ">
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<%
		session.removeAttribute("commentList");
	%>
</body>
</html>