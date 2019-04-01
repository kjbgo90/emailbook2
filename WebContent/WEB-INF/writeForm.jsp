<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메일 리스트 가입</h1>
	<p />메일 리스트에 가입하려면, 
	<br>아래 항목을 기입하고 submit 버튼을 클릭하세요.
	
	<form method="post" action="./eb">
		<input type="hidden" name="action" value="insert">
		Last name(성):<input type="text" name="ln" value=""> <br>
		First name(이름):<input type="text" name="fn" value=""> <br>
		Email address:<input type="text" name="email" value=""><br>
		<input type="submit" value="등록"> 
	</form>
	<br>
	<a href="./eb?action=list">리스트 바로가기</a>
</body>
</html>