<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="koreait.vo.HrdMember"%>
<%@page import="koreait.dao.HrdMemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<title>회원등록 </title>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 정보 수정 화면</title>
    <link rel="stylesheet" href="0_hrdkorea.css">
</head>
<style>
td,th{
    border: 1px dotted gray;
}
td.col1_title {
    text-align: center;
    font-weight: bold;
    width: 40%;
    padding: 7px;
}
input[type=text]{
    padding: 5px;
    margin-left: 10px;
}
button {
    padding: 5px 15px;
    margin: 5px;
}
</style>
<!-- src속성에 지정한 자바스크립트 파일 소스가 그대로 삽입됩니다. -->
<script src="0_member_valid.js"></script>
<script type="text/javascript">
	function isSubmit(){
    const frm = document.forms[0];
		if(valid_check()){	//유효성 검사 함수를 호출/실행.
			//유효성 검사 통과	
			frm.submit();
		out.print("<script>alert('회원정보 수정 완료했습니다.!');location.href='1_index.jsp'");
		}
	}
	function init() {
		const frm = document.forms[0];
		frm.name.value="";
		frm.tel.value="";
		frm.addr.value="";
		frm.grade.value="";
		frm.city_code.value="";
	}
	function list() {
		
	}
</script>
<body>
<%
		String custno = request.getParameter("custno");
		out.print("custno :");
		out.print(custno);
		out.print("<br>");
		int no=0;
		
		if(custno == null){   //?custno 자체가 없을때  http://localhost:8081/jsp1/day3/get_result.jsp
			out.print("<script>alert('검색값이 없습니다.');location.href='get_test.jsp';");
			out.print("</script>");
		}else {
			try{
				no = Integer.parseInt(custno);
			}catch(NumberFormatException e) {
		//form 태그 조회값 없이 실행됬을 때 http://localhost:8081/jsp1/day3/get_result.jsp?custno=
				out.print("<script>alert('잘못된 검색값입니다.');location.href='get_test.jsp';");
				out.print("</script>");
			}
		}
		
		HrdMemberDao dao = HrdMemberDao.getInstance();
		HrdMember member = dao.selectOne(no);
		if(member==null) {
			out.print("<script>alert('검색결과가 없습니다.');location.href='get_test.jsp';");
			out.print("</script>");
		}
%>
<div class="wrap_container">
    <header>
        <h3 class="header-item">쇼핑몰 회원관리 ver1.0</h3>
    </header>
    <nav class="haeder-nav">
        <ul class="container">
            <li><a href="2_insertForm.jsp">회원등록</a></li>
            <li><a href="4_memberList.jsp">회원목록조회/수정</a></li>
            <li><a href="7_saleList.jsp">회원매출조회</a></li>
            <li><a href="1_index.jsp">홈으로</a></li>
        </ul>
    </nav>
        <section>
            <h2 style="text-align: center;" class="section"><strong>홈쇼핑 회원 정보 수정</strong></h2>
            <form action="6_updateSave.jsp" method="post"> 
            <!-- action 설정된 url로 입력요소에 작성된 데이터를 서버로 전송. 
                form 태그 속성 method="post" 있을때 와 없을 때 차이점이 있습니다. -> 백엔드 배우고 확인.
            -->
               <input type="hidden" name="num" value="<%= member.getCustNo() %>" >
               <!--hidden 추가 대신 사용할 다른 방법은 회원번호 input의 readonly="readonly" 속성으로 합니다.
               	입력불가를 표현하기 위한 css가 필요합니다.  -->
                <table style="width: 70%;margin:auto">
                    <tr>
                        <td class="col1_title">회원번호(자동발생)</td>
                        <td><input type="text" name="num" value="<%= member.getCustNo() %>" disabled="disabled"></td>
                    </tr>
                    <tr>
                        <td class="col1_title">회원성명</td>
                        <td><input type="text" name="name" value="<%= member.getCustName() %>" ></td>
                        <!-- required="required" 를 간단히 표현 -->
                    </tr>
                    <tr>
                        <td class="col1_title">회원전화</td>
                        <td><input type="text" name="tel" size="30" value="<%= member.getPhone() %>"></td>
                    </tr>
                    <tr>
                        <td class="col1_title">회원주소</td>
                        <td><input type="text" name="addr" size="40" value="<%= member.getAddress() %>"></td>
                    </tr>
                    <tr>
                        <td class="col1_title">가입일자</td>
                        <td><input type="text" name="reg_date" value="<%= member.getJoindate() %>" disabled="disabled" ></td>
                    </tr>
                    <tr>
                        <td class="col1_title">고객등급[A:VIP,B:일반,C:직원]</td>
                        <td><input type="text" name="grade" value="<%= member.getGrade() %>" ></td>
                    </tr>
                    <tr>
                        <td class="col1_title">도시코드</td>
                        <td><input type="text" name="city_code"  value="<%= member.getCity() %>"></td>
                    </tr>
                   	<tr>
                        <td colspan="2" style="text-align: center;">
                            <button type="button" onclick="isSubmit()">수정</button>
                            <button type="button" onclick="location.href='4_memberlist.jsp'">조회</button>
                            <!-- button로 링크 또는 기능 설정하기
                            onclick 이벤트와 자바스크립트 명령을 이용합니다.    
                            location.href 는 a태그의 href 속성설정과 같은 내용입니다.
                            -->
                        </td>
                        
                    </tr>
                </table>
            </form>
        </section>
     <script type="text/javascript">
	        	const reg = document.forms[0].reg_date;
    	    	const today = new Date();
    	    	const year = today.getFullYear();
    	    	const month = (today.getMonth()+1).toString().padStart(2,0);
    	    	const day = today.getDate().toString().padStart(2,0);
        		reg.value=[year,month,day].join('');     
        		
        </script>
    <footer>
        <p>HRDKOREA Copyrightⓒ2016 All rights reserved. Human Resources Development Service of Korea</p>
    </footer>
    </div>
</body>
</html>