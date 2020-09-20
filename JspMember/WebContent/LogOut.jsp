<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//세션 연결 해제하기
	session.invalidate(); //서버 메모리 해제
%>
<script>
	alert("정상적으로 로그아웃 되었습니다.");
	location.href="Login.jsp"; //자바에서의 페이지 이동
</script>