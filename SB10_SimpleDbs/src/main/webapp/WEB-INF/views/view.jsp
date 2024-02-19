<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1>내용보기</h1>
		<hr>
		작성자 : ${dto.writer} <br>
		제목 : ${dto.title} <br>
		내용 : ${dto.content} <br> <br>
		<p> <a href="list">목록 바로가기</a> </p>
</body>
</html>