<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>nav</title>
    <meta name="viewport" content="width=device-width">
    <style>
        .nav_menu {
            font-size: 2em;
            font-weight: bolder;
            list-style: none;
            float: left;
            margin-left: 30px;
        }
    </style>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<body>
<%
    String name = (String)session.getAttribute("name");
    System.out.println(name);
%>
<nav class = "navbar navbar-default">
        <li class="nav_menu"><a href="/guestbook?command=123">방명록</a></li>
        <li class="nav_menu"><a href="/board">게시판</a></li>

        <div class="mx-auto order-0">
            <a class="navbar-brand mx-auto"><b><%=name %>님,  안녕하세요!</b></a>
            <a class="navbar-brand mx-auto" href = "/static/Member/editPwd.jsp"> 암호변경 </a>
            <a class="navbar-brand mx-auto" href = "/static/Member/delMember.jsp"> 회원탈퇴 </a>
            <a class="navbar-brand mx-auto" href = "/static/Member/logOut.jsp"> 로그아웃 </a>
        </div>
</nav>
</body>
</html>
