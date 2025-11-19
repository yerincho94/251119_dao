<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 가입</title>
</head>
<body>
<nav>
    <a href="<%= request.getContextPath() %>/">메인</a>
    <a href="<%= request.getContextPath() %>/login">로그인</a>
</nav>
<form method="post">
    <input name="username" placeholder="회원 이름 입력"> <br>
    <input name="password" type="password" placeholder="비밀번호 입력"> <br>
    <button>가입</button>
</form>
</body>
</html>
