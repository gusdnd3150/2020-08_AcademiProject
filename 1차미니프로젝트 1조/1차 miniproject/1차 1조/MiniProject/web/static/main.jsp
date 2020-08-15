<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>초기Page</title>
  <meta name="viewport" content="width=device-width">
</head>
<body>
  <c:choose>
    <c:when test="${empty sessionScope.name}">
      <jsp:include page="./nav1.jsp" />
      메인화면 입니다.
    </c:when>
    <c:when test="${not empty sessionScope.name}">
      <jsp:include page="./nav2.jsp" />
    </c:when>
  </c:choose>
</body>
</html>
