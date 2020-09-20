<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "hewon.MemberDAO"%>
<!DOCTYPE html>

<!-- 객체 생성 -->
<jsp:useBean id="mem" class="hewon.MemberDTO" scope="page"/>
<!-- default값은 page, 현재 페이지에만 적용 -->

<!-- setter메서드 호출 -->
<jsp:setProperty name="mem" property="*" /> 

<%
	//action="deletePro.jsp?mem_id=?의 값을 받음
	String mem_id = request.getParameter("mem_id"); //입력 받지 않은 값도 request로 받아온다.
	String passwd = request.getParameter("passwd"); //입력 받은 값
	
	System.out.println("MemberUpdateProc.jsp의 mem_id=>" + mem_id);
	System.out.println("MemberUpdateProc.jsp의 passwd=>" + passwd);
	
	MemberDAO memMgr = new MemberDAO(); //메서드 호출을 위한 객체 형성 (수정해주는 메서드 호출)
	int check = memMgr.deleteMember(mem_id,passwd);
	System.out.println("deletePro.jsp의 회원탈퇴유무(check값 확인) =>" + check);
%>

<%
	if(check==1){ //회원탈퇴에 성공했다면
		session.invalidate();//세션 해제
%>
		<script>
			alert("<%=mem_id%>님 성공적으로 탈퇴처리 되었습니다.");
			location.href="Login.jsp"; //로그인 창으로 이동
		</script>
	<%}else{%>
		<script>
			alert("비밀번호가 틀립니다.\n 다시 확인바랍니다.");
			history.back(); // => history.go(-1);
		</script>
<%}%>