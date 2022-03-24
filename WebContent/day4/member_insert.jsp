<%@page import="koreait.dao.HrdMemberDao"%>
<%@page import="koreait.vo.HrdMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>day4 회원등록 insert</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		int custno = Integer.parseInt(request.getParameter("num"));
		String custName = request.getParameter("name");
		String phone = request.getParameter("tel");
		String address = request.getParameter("addr");
		String grade = request.getParameter("grade");
		String city = request.getParameter("city_code");
	
		HrdMember vo = new HrdMember(custno,custName,phone,address,null,grade,city);
		HrdMemberDao dao = HrdMemberDao.getInstance();
		dao.insert(vo);
		out.print(dao.selectOne(vo.getCustNo()));    //dao.selectOne(vo.getCustNo())널이 아니면 정상등록완료
		if(dao.selectOne(vo.getCustNo()) !=null) {
			out.print("<script>alert('회원등록 완료했습니다.!')");
			out.print("</script>");
		}	
		
	%>
</body>
</html>