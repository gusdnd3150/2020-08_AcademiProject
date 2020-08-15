<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<c:set var="totArticles" value="${totArticles }" />
<c:set var="pageNum" value="${pageNum }" />
<!DOCTYPE html>
<html>
<head>
<style type="text/css"></style>
<title>게시글 목록창</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style type="text/css">
	div { width: 80%; margin: 0 auto;  }
	body {padding: 20px};
</style>
</head>
<body>
	<div>
		<a class="btn btn-primary margin-bottom" style="margin-bottom: 10px" href="${contextPath}/board/boardWrite.do">게시글 쓰기</a>
		<table class="table">
		<thead>
			<tr height="10" align="center">
				<th width="10%">번호</th>
				<th width="30%">제목</th>
				<th width="10%">작성자</th>
				<th width="10%">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${articlesList == null }">
					<tr height="10">
						<td colspan="4">
							<p align="center">
								<b>등록된 글이 없습니다.</b>
							</p>
						</td>
					</tr>
				</c:when>
				<c:when test="${articlesList != null }">
					<c:forEach var="article" items="${articlesList }">
						<tr align="center">
							<td>${article.article_no }</td>
							<td class="text-left"><a href="${contextPath}/board/viewArticle.do?article_no=${article.article_no}">${article.title }</a></td>
							<td>${article.writer_id }</td>
							<td>${article.read_cnt}</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
		</table>
		<div class="page" style="text-align:center;">
		<c:forEach var="page" begin="1" end="${totArticles%10==0? totArticles/10 : totArticles/10+1}" >
			<a href ="${contextPath}/board/listArticles.do?pageNum=${page}">[${page}]</a>
		</c:forEach>
		</div>
	</div>
</body>
</html>