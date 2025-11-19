<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>코딩 게시판</title>
</head>
<body>
    <% String username = (String) session.getAttribute("username"); %>
    <nav>
        <% if(username == null ) { %>
            <a href="signup">회원가입</a>
            <a href="login">로그인</a>
        <% } else { %>
            <p><%= username %>님 환영합니다!!</p>
        <% } %>
    </nav>
    <p>코딩을 배웁니다!</p>
</body>
</html>
