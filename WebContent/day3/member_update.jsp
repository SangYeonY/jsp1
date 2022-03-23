<%@page import="koreait.dao.HrdMemberDao"%>
<%@page import="koreait.vo.HrdMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	// phone,address,city 입력값 가져오기 와 update 조건값 custno 도 가져오기
	String phone = request.getParameter("tel");
	String address = request.getParameter("addr");
	String city = request.getParameter("city_code");
	int custno = Integer.parseInt(request.getParameter("num"));
	
	HrdMember vo = new HrdMember();
	vo.setAddress(address);
	vo.setPhone(phone);
	vo.setCity(city);
	vo.setCustNo(custno);
	out.print(vo);		// 확인

	HrdMemberDao dao = HrdMemberDao.getInstance();
	dao.update(vo); //결과는 db테이블에서 확인
	out.print("<br>");
	out.print(dao.selectOne(custno));	//또는 다시 select 조회 해보세요.
%>

</body>
</html>