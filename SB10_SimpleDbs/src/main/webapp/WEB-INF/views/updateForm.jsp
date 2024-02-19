<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update" method="post">
    <input type="hidden" name="id" value="${dto.id}">
    작성자: <input type="text" name="writer" value="${dto.writer}" readonly><br>
    제목: <input type="text" name="title" value="${dto.title}"><br>
    내용: <textarea name="content">${dto.content}</textarea><br>
    <input type="submit" value="수정">
</form>
</body>
</html>