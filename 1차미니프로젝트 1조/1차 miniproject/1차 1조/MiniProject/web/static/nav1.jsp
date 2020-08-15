<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>nav</title>
    <meta name="viewport" content="width=device-width">
    <style>
        li {
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
    <nav class = "navbar navbar-default">
            <li><a href="/guestbook?command=123">방명록</a></li>
            <li><a href="/board">게시판</a></li>
            <li><a href="/static/Member/logIn.jsp">로그인</a></li>
            <li><a href="/static/Member/addMember.jsp">회원가입</a></li>
    </nav>
</body>
</html>
