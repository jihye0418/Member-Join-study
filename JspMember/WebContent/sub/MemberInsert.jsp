<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "hewon.MemberDAO"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8"); //한글 깨짐 방지
%>

<!-- 객체 생성 -->
<jsp:useBean id="mem" class="hewon.MemberDTO" scope="page"/>
<!-- default값은 page, 현재 페이지에만 적용 -->

<!-- setter메서드 호출 -->
<jsp:setProperty name="mem" property="*" /> 

<%
	//memberInsert메서드 호출
	MemberDAO memMgr = new MemberDAO();
	boolean check = memMgr.memberInsert(mem); //반환값이 있어야 한다.
%>

<html>
<head>
<meta charset="UTF-8">
<title>회원가입 확인</title>
</head>
<body bgcolor="#ffffcc">
	<center>
		<%
			if(check){ //회원가입에 성공했다면
				out.println("<b>회원가입을 축하드립니다.</b><p>");
				out.println("<a href=../Login.jsp>로그인</a>");
			}else{
				out.println("<b>다시 입력하여 주십시오.</b><p>");
				out.println("<a href=Register.jsp>다시 가입</a>");
			}
		%>
	</center>

</body>
</html>