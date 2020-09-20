<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- MemberDAO 객체를 얻어옴
MemberDAO memMgr = new MemberDAO(); //일반메서드 호출 => 객체 생성 (이렇게 쓰려면 import 필요)
-> 액션태그로 가지고 오려면 반드시 id, passwd를 받아오기 전에 받아와야 함. -->
<jsp:useBean id="memMgr" class="hewon.MemberDAO" />


<%
//id, passwd를 받아서 loginCheck메서드 
String mem_id = request.getParameter("mem_id");
String mem_passwd = request.getParameter("mem_passwd");
System.out.println("mem_id=>" + mem_id + "mem_passwd=>" + mem_passwd);

boolean check = memMgr.loginCheck(mem_id, mem_passwd);
%>


<%
if(check){//id와 비밀번호 값이 일치한다면
	session.setAttribute("idKey", mem_id); //서버의 메모리에 값을 저장함.
	//페이지 이동을 하는데 단순히 페이지 이동을 하는 것(정보를 공유하지 않음)
	response.sendRedirect("Login.jsp"); //로그인 되도 안되도 내가 처리하겠다!
}else{
	response.sendRedirect("LogError.jsp");
}
%>