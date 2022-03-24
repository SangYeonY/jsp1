<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="koreait.dao.HrdMemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원등록 화면</title>
</head>
<body>
		<%
			HrdMemberDao dao = HrdMemberDao.getInstance();
			int next = dao.getNextSeq();
			
			LocalDate today = LocalDate.now();
			String str_today = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		%>
		<div class="wrap_container">
	<header>
			<h3>쇼핑몰 회원관리 ver1.0</h3>
	</header>	
		<nav>
		 <ul class="container">
            <li><a href="2_insertForm.jsp">회원등록</a></li>
            <li><a href="5_updateForm.jsp">회원목록조회/수정</a></li>
            <li><a href="4_memberList.jsp">회원매출조회</a></li>
            <li><a href="1_index.jsp">홈으로</a></li>
        </ul>
		</nav>
		<section style ="height: 500px; background-color: rgb(211,211,211); padding-top: 10px;">
		<h3 style="text-align: center;">홈쇼핑 회원 등록</h3>
		<form action ="3_insertSave.jsp" method="post"></form>
		<input type="hidden" name ="num" value=<%=next %>>
		<table  style="width: 100%;">  <!-- 부모태그 div 너비의 100% -->
        <tr>   
            <td>
                <label for="name" class="title">이름</label>
            </td>
            <td><input type="text" id="name" name="name"></td>
        </tr>
        <tr>
            <td><label for="password" class="title" >패스워드</label></td>
            <td><input type="password" id="password" name="password"></td>
        </tr> 
        <tr>
            <td><label for="email" class="title">이메일</label></td>
            <td><input type="email" id="email" name="email"></td>
        </tr> 
        <tr>
            <td><label for="age" class="title">나이</label></td>
            <td><input type="number" value="30" min="20" max="40" id="age" name="age"></td>
        </tr>
        <tr>
            <td><label for="addr" class="title">주소</label></td>
            <td>
                 <select name="addr" id="addr">
                       <option value="서울">서울</option> 
                       <option value="인천">인천</option> 
                       <option value="대전" selected="selected">대전</option> <!-- 초기 선택 설정 -->
                       <option value="광주">광주</option> 
                       <option value="부산">부산</option> 
                 </select>   
            </td>
        </tr>
        <tr>
            <td><label for="" class="title">성별</label></td>
            <td> 
                <input type="radio" name="gender" checked="checked" id="male" value="male">
                <label for="male">남자</label>
                <input type="radio" name="gender" id="female" value="female">
                <label for="female">여자</label>
            </td>
        </tr>
        <tr>
            <td><label for="" class="title">취미</label></td>
            <td>
                <input type="checkbox" id="hobby1" name="hobby" value="축구">
                <label for="hobby1">축구</label>
                <input type="checkbox" id="hobby2" name="hobby" value="농구">
                <label for="hobby2">농구</label>
                <input type="checkbox" id="hobby3" name="hobby" value="스키">
                <label for="hobby3">스키</label>
                <input type="checkbox" id="hobby4" name="hobby" value="달리기">
                <label for="hobby4">달리기</label>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center;">  <!-- 이 td의 내용은 가운데 정렬합니다. -->  
                <!-- <input type="submit" value="가입하기1"> -->
                <button type="submit">가입하기</button>
                <!-- <input type="reset" value="다시쓰기1"> --> <!-- 입력요소의 내용이 초기화 됩니다. -->
                <button type="reset">다시쓰기</button>
            </td>
        </tr>

    </table>
		</section>		
		</div>
</body>
</html>