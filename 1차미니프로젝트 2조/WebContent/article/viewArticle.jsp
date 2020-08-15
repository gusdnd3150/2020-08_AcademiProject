<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding("UTF-8");%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>${ contentList.get(0).title }</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
	<style type="text/css">
		body { padding: 20px;}
		div { width: 80%; margin: 0 auto;  }
	</style>
</head>
<body>
	<div>
		<table class="table table-bordered">
		      <tr>
		         <th width="10%">번호</th>
		         <td class="text-left" >${article_no }</td>
		      </tr>
		      <tr>
		         <th>작성자</th>
		         <td class="text-left">${contentList.get(0).writer_name}</td>
		      </tr>
		      <tr>
		         <th>제목</th>
		         <td class="text-left">${contentList.get(0).title}</td>
		      </tr>
		      <tr>
		         <th>내용</th>
		         <td class="text-left">${contentList.get(0).content}</td>
		      </tr>
		
		   </table>
		   <a class="btn btn-outline-primary" href="${contextPath }/board/listArticles.do">목록</a>
		   <a class="btn btn-outline-primary" href="${contextPath}/board/editArticle.do?article_no=${article_no}">게시글수정</a>
		   <a class="btn btn-outline-danger" href="${contextPath}/board/removePwd.do?article_no=${article_no}">게시글삭제</a>
		   <c:if test="${contentList.get(0).file_name!= null }">
				<a href="${contextPath}/board/download.do?file_name=${contentList.get(0).file_name}">[파일다운]</a>
			</c:if> 
	</div>
</body>
</html>