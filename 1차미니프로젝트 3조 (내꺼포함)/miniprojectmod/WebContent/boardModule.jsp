<%@page import="morning.ArticleVO"%>
<%@page import="morning.Paging"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<jsp:useBean id="articleList" class="java.util.ArrayList" type="java.util.ArrayList<morning.ArticleVO>" scope="request"></jsp:useBean>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: 100px auto 0 auto;
	width: 1100px;
	border-collapse: collapse;
	border-spacing: 1px;
	line-height: 25px;
}

table th {
	font-weight: bold;
	border-bottom: 1px solid #1AAB8A;
	border-top: 2px solid #1AAB8A;
	line-height: 30px;
}

table td {
	text-align: center;
	border-bottom: 1px solid #ccc;
}

button {
	border: 1px solid #ccc;
	float: right;
	width: 80px;
	height: 35px;
	background: #1AAB8A;
	color: #fff;
	transition: 800ms ease all;
	font-size: large;
	border: 0px;
}

button:hover {
	color: #1AAB8A;
	background: #fff;
}

a:link {
	text-decoration: none;
	color: black;
}

a:visited {
	text-decoration: none;
	color: black;
}
</style>
</head>
<body>
	<table>
		<tr>
			<th style="margin-left: 100px;">번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:choose>
			<c:when test="${empty articleList }">
				<tr>
					<td colspan="5" style="align-content: center;">글이 하나도 없어요</td>
				</tr>
			</c:when>
			<c:when test="${!empty articleList }">			<%-- 글목록이 비어있지않을경우 --%>
				<c:forEach items="${articleList}" begin="${paging.minBound}" end="${paging.maxBound}" var="page"> <%--글index 최대최소 값 지정 --%>
					<c:set var="num" value="${0}"></c:set>
					<c:forEach items="${commentList}" var="comment">	<%--댓글 수는 해당글의 articleNo 갖고있는 댓글 전부 합쳐서 확인 --%>
						<c:if test="${comment.articleNo==page.articleNo}">
							<c:set var="num" value="${num+1}"></c:set>
						</c:if>
					</c:forEach>
					<tr>
						<td style="font-size: small; width: 80px;">${page.articleNo}</td>
						<td style="text-align: left; padding-left: 50px;"><a href="read.article?articleNo=${page.articleNo}">${page.title} <c:if test="${num!=0}">
									<p style="font-size: small; color: #aaa999; display: inline; vertical-align: middle;">[${num}]</p>
								</c:if>
						</a></td>
						<td style="width: 100px;">${page.writerId}</td>
						<td style="width: 100px;">${fn:split(page.regdate,' ')[0]}</td>
						<td style="width: 100px;">${page.readCnt}</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		<tr>
			<td colspan="5" style="border-bottom: 0px;"><c:forEach var="i" begin="1" end="${paging.maxNum}">	<%--페이징 넘버보여주는 for문 --%>
					<c:if test="${nowPage==i}">
						<p style="font-weight: bold; display: inline;">${i}</p>	<%--현재페이지는 링크없이 굵은글씨 --%>
					</c:if>
					<c:if test="${nowPage!=i}">
						<a href="search.article?nowPage=${i}">${i}</a>		<%--나머지페이지는 링크 --%>
					</c:if>
				</c:forEach></td>
		</tr>
		<tr>
			<td colspan="5" style="border-bottom: 0px;">
				<button style="float: right;" onclick="location.href='write.article'">글쓰기</button>
			</td>
		</tr>
	</table>
</body>
</html>