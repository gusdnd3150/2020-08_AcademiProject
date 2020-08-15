<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../nav2.jsp" />
<c:choose>
    <c:when test="${paramPage eq 'writeBoard'}">
        <div class="container">
            <table class="table table-hover">
                <thead>
                    <th colspan="2">
                        <p>[<a href="/board?paramPage=writeBoard">게시글쓰기</a>]</p>
                    </th>
                    </thead>
                    <form name="boardForm" method="post" action="/uploadBoard" enctype="multipart/form-data">
                    <tr>
                        <th width="100">제목 :</th>
                        <td><input type="text" name="title"></td>
                    </tr>
                    <tr>
                        <th width="100">비밀번호 :</th>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <th width="100">내용 :</th>
                        <td><textarea name="content" rows="10" cols="50"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div>
                                <p>파일첨부: <input type="file" name="file"></p>
                                <p><input type="submit" class="btn-primary" value="새 글 등록"></p>
                            </div>
                        </td>
                    </tr>
                        <input type="hidden" name="command" value="addBoard">
                        <input type="hidden" name="writer_id" value="${sessionScope.member_id}">
                        <input type="hidden" name="writer_name" value="${sessionScope.name}">
            </form>
            </table>
        </div>
    </c:when>
    <c:when test="${paramPage eq 'getBoard'}">
        <div class="container">
            <table class="table table-hover">
                <thead>
                    <th colspan="2">
                        <p>[<a href="/board?paramPage=writeBoard">게시글쓰기</a>]</p>
                    </th>
                </thead>
                <tr>
                    <th width="100">번호</th>
                    <td>${vo.article_no}</td>
                </tr>
                <tr>
                    <th width="100">작성자</th>
                    <td>${vo.writer_name}</td>
                </tr>
                <tr>
                    <th width="100">제목</th>
                    <td>${vo.title}</td>
                </tr>
                <tr>
                    <th width="100">내용</th>
                    <td>${vo.content}</td>
                </tr>
                <tr>
                    <td colspan="2">
                        [<a href="/board">목록</a>] [<a href="/board?paramPage=checkUpdateBoard&article_no=${vo.article_no}">게시글수정</a>] [<a href="/board?paramPage=checkDeleteCheck&article_no=${vo.article_no}">게시글삭제</a>]
                        <c:if test="${not empty vo.fileName}">
                        [<a href="/downloadBoard?article_no=${vo.article_no}&fileName=${vo.fileName}">파일다운</a>]
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </c:when>
    <c:when test="${paramPage eq 'updateBoard'}">
        <div class="container">
            <table class="table table-hover">
                <thead>
                    <th colspan="2">
                        <p>[<a href="/board?paramPage=writeBoard">게시글쓰기</a>]</p>
                    </th>
                </thead>
                    <form name="updateForm" method="post" action="/uploadBoard" enctype="multipart/form-data">
                        <tr>
                            <th>번호</th>
                            <td>${vo.article_no}</td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" name="title" value="${vo.title}"></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td><textarea name="content" rows="10" cols="50">${vo.content}</textarea></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <p>파일첨부: <input type="file" name="file"></p>
                                <p><input type="submit" class="btn-primary" value="글 수정"> </p>
                            </td>
                        </tr>
                        <input type="hidden" name="command" value="updateBoard">
                        <input type="hidden" name="article_no" value="${vo.article_no}">
                    </form>
            </table>
        </div>
    </c:when>
</c:choose>

</body>
</html>
