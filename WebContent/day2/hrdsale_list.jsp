<%@page import="day2.vo.SaleSum"%>
<%@page import="java.util.List"%>
<%@page import="day2.dao.HrdProblemDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hrd sale list</title>
</head>
<body>
<%
	HrdProblemDao dao = HrdProblemDao.getCustomDao();
	List<SaleSum> list = dao.selectSale();
	for(int i = 0; i<list.size(); i++){
	//	out.print(list.get(i));	테스트용
	//	out.print("<br>");		테스트용
%>
	<li><%= list.get(i).getCustNo() %></li>	
	<li><%= list.get(i).getCustName() %></li>	
	<li><%= list.get(i).getGrade()%></li>	
	<li style-"padding-bottom: 20px"><%= list.get(i).getSum()%></li>	
<%
}

%>
</body>
</html>