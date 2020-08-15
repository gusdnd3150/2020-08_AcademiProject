<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String result = request.getParameter("result");
    System.out.println(result);
%>
<jsp:include page="../nav2.jsp" />
<form name="checkForm" method="post">
    <c:if test="${paramPage eq 'checkUpdateBoard'}">
        <div class="col-sm-6 col-md-offset-3">
            게시글을 수정하시려면 암호를 입력하세요
            <div class="form-group">
                <label for="password">암호</label>
                <div class="input-group">
                    <input type="password" id="password" name="password">
                </div>
            <div class="form-group">
                <input type="submit" class="btn-success" value="게시글 수정하기" formaction="/board?paramPage=updateBoard&article_no=${param.article_no}">
            </div>
        </div>
    </c:if>
    <c:if test="${paramPage eq 'checkDeleteCheck'}">
        <div class="col-sm-6 col-md-offset-3">
            게시글을 삭제하시려면 암호를 입력하세요
            <div class="form-group">
                <label for="password">암호</label>
                <div class="input-group">
                    <input type="text" name="password">
                </div>
            <div class="form-group">
                <input type="submit" class="btn-danger" value="게시글 삭제하기" formaction="/board?command=delBoard&article_no=${param.article_no}">
            </div>
    </c:if>
</form>
</body>
</html>
