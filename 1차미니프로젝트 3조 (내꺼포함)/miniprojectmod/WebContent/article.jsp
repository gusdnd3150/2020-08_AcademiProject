<%@page import="morning.postCommentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<jsp:useBean id="content" class="morning.ArticleContentVO" scope="request"></jsp:useBean>
<jsp:useBean id="article" class="morning.ArticleVO" scope="request"></jsp:useBean>
<head>
<meta charset="UTF-8">
<title>${article.title}</title>
<style type="text/css">
.box {
	width: 900px;
	height: 700px;
	margin: 100px auto 0 auto;
	border-collapse: collapse;
	border-bottom: 1px solid #ccc;
}

button {
	border: 1px solid #ccc;
	float: right;
	width: 60px;
	height: 35px;
	background: #1AAB8A;
	color: #fff;
	transition: 800ms ease all;
	font-size: medium;
}

button:hover {
	color: #1AAB8A;
	background: #fff;
}

.box td {
	border: 0px solid #ccc;
}
</style>

<script type="text/javascript">
	function getCurrentScrollPercentage() {
		return (window.scrollY + window.innerHeight)
				/ document.body.clientHeight * 100
	}
</script>

</head>
<body>
	<table class="box">
		<tr style="height: 30px; font-weight: bold;">
			<td colspan="2" style="padding: 0 10px 0 10px;">${article.title}</td>
		</tr>
		<tr style="height: 30px; border-bottom: 1px solid #ccc;">
			<td style="padding: 0 10px 0 10px;">${article.writerId} &nbsp; ${article.regdate}</td>
			<td style="text-align: right;">번호 ${article.articleNo} 조회 ${article.readCnt}</td>
		</tr>
		<tr style="height: 600px; vertical-align: top;">
			<td colspan="3" style="padding: 10px 10px 10px 10px;"><c:if test="${not empty article.fileName}">
					<img src="Download?fileName=${article.fileName}">
					<br>
				</c:if> ${content.content}</td>
		</tr>
		<tr>
			<td colspan="3" style="margin-bottom: 30px; padding-bottom: 20px;">
				<button onclick="location.href='mod.jsp?key=1&articleNo=${article.articleNo}'">삭제</button>
				<button onclick="location.href='mod.jsp?key=2&articleNo=${article.articleNo}'">수정</button>
				<button onclick="location.href='search.article'">목록</button> <c:if test="${not empty article.fileName }">
					<a href="Download?fileName=${article.fileName}">다운로드</a> <%--등록파일 존재시 다운로드링크 활성화 --%>
				</c:if>
			</td>
		</tr>
	</table>
	<jsp:include page="viewCommentModule.jsp">
		<jsp:param value="${article.articleNo}" name="articleNo" />
	</jsp:include>
	<jsp:include page="writeCommentModule.jsp">
		<jsp:param value="${article.articleNo}" name="articleNo" />
	</jsp:include>

</body>
</html>