<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- memberReg.html 에서 입력한 7개의 사용자 입력값을 전달 받아서 table 태그에 출력해보세요. -->
<%
	// method='post' 방식으로 데이터 전달되었을 때 인코딩 꼭 하세요.
	request.setCharacterEncoding("UTF-8");

	// hobby 파라미터 받아서 출력하는 것은 잘 안되면 빼놓고 해도 됩니다.
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	int age = Integer.parseInt(request.getParameter("age")); 	// 프론트엔드에서 유효성 검사를 했다는 조건.
	String addr = request.getParameter("addr");
	String gender = request.getParameter("gender");
	String[] hobby = request.getParameterValues("hobby");
	String hobbies = Arrays.toString(request.getParameterValues("hobby"));
	hobbies = hobbies.substring(1,hobbies.length()-1);
%>
<table>
	<tr>
		<th>이름</th>
		<td><%=name %></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><%=password %></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><%=email %></td>
	</tr>
	<tr>
		<th>나이</th>
		<td><%=age %></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><%=addr %></td>
	</tr>
	<tr>
		<th>성별</th>
		<td><%=gender.equals("male")? "남성":"여성" %></td>
	</tr>
	<tr>
		<th>취미</th>
		<td><%=hobbies %></td>
	</tr>
</table>
</body>
</html>