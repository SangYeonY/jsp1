<%@page import="koreait.vo.HrdMember"%>
<%@page import="koreait.dao.HrdMemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정 처리</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	//phone,address,city 입력값 가져오기 와 update 조건값 custno 도 가져오기
	String phone = request.getParameter("tel");
	String address = request.getParameter("addr");
	String city = request.getParameter("city_code");
	int custno = Integer.parseInt(request.getParameter("num"));
	
	HrdMember vo = new HrdMember();
	vo.setAddress(address);
	vo.setPhone(phone);
	vo.setCity(city);
	vo.setCustNo(custno);   
	
	
	HrdMemberDao dao = HrdMemberDao.getInstance();
	dao.update(vo);			//결과는 db테이블에서 확인하세요.
	out.print("<script>alert('회원 정보 수정 완료했습니다.!');location.href='1_index.jsp'");
	out.print("</script>");
%>



<!-- 회원정보 수정이 완료되면 1_index.jsp ulr로 이동하도록 해보세요. -->
</body>
</html>