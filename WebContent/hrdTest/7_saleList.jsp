<%@page import="koreait.vo.SaleSum"%>
<%@page import="koreait.dao.HrdSaleDao"%>
<%@page import="java.util.List"%>
<%@page import="koreait.dao.HrdMemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원매출조회</title>
<link rel="stylesheet" href="0_hrdkorea.css">
</head>
	
<body>
  <header>
        <h3 class="header-item">쇼핑몰 회원관리 ver1.0</h3>
    </header>
    <nav class="haeder-nav">
        <ul class="container">
          	<li><a href="2_insertForm.jsp">회원등록</a></li>
            <li><a href="4_memberlist.jsp">회원목록조회/수정</a></li>
            <li><a href="7_saleList.jsp">회원매출조회</a></li>
            <li><a href="1_index.jsp">홈으로</a></li>
        </ul>
    </nav>
	 <section>
            <h2 style="text-align: center;" class="section"><strong>회원매출조회</strong></h2>
            <table style="width: 90%;margin: auto;text-align: center;"> 
                <tr style="text-align: center;">    
                    <td><strong>회원번호</strong></td>       
                    <td><strong>회원성명</strong></td>
                    <td><strong>고객등급</strong></td>
                    <td><strong>매출</strong></td>
                </tr>
            <%
				//테이블의 데이터 모두 가져오기
				HrdSaleDao dao =HrdSaleDao.getInstance();
           		List<SaleSum> list = dao.selectSale();
           		
			%>
			<%
				for(int i=0;i<list.size();i++){
			%>
				<tr> 
					<td><%=list.get(i).getCustNo()%></td>
					<td><%=list.get(i).getCustName()%></td>
					<td><%=list.get(i).getGrade()%></td>
					<td><%=list.get(i).getSum()%></td>
				</tr>
			<%
				}
			%>					
              	
            </table>

        </section>
    <footer>
        <p>HRDKOREA Copyrightⓒ2016 All rights reserved. Human Resources Development Service of Korea</p>
    </footer>
</body>
</html>