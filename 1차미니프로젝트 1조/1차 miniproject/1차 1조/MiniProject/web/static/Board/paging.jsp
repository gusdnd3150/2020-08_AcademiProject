<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>paging</title>
</head>
<body>
<div class="col-md-offset-3" id="paging">
    <ul class="pagination">
    <c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
            <li <c:out value="${param.page==index ? 'class=active' : ''}" />>
            <a href="${action}?page=${index}">${index}</a></li>
    </c:forEach>
    </ul>
</div>
</body>
</html>
