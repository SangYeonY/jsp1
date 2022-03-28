<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward</title>
</head>
<body>
	<%
		request.setAttribute("data","abc");	// data 란 이름으로 "abc" 값 저장
	
		// forward_test.jsp url로 요청된 정보를 C.jsp로 전달합니다. 
		pageContext.forward("C.jsp");	// 사용자를 위한 출력화면입니다.
		// context : 문맥
	
	
	%>
</body>
</html>