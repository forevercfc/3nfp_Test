<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="500" border="1" cellpadding="0" cellspacing="0">
	<form action="write" method="post">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" size="100"/></td>
		</tr>
		
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" size="100"/></td>
		</tr>
		
		<tr>
			<td>내용</td>
			<td><input type="text" name="content" size="100"/></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="입력">&nbsp;
			
			</td>
			<td><a href="list">리스트로 빽</a></td>
		</tr>
		</form>
	</table>
</body>
</html>