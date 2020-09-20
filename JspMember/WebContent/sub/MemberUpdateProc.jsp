<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "hewon.MemberDAO"%>
<!DOCTYPE html>
<%
	//MemberUpdate.jsp에서 MemberUpdateProc.jsp로 넘어옴
	request.setCharacterEncoding("utf-8"); //한글 깨짐 방지
%>

<!-- 객체 생성 -->
<jsp:useBean id="mem" class="hewon.MemberDTO" scope="page"/>
<!-- default값은 page, 현재 페이지에만 적용 -->

<!-- setter메서드 호출 -->
<jsp:setProperty name="mem" property="*" /> 

<%
	//memberUpdate메서드 호출
	String mem_id = request.getParameter("mem_id"); //id값을 넘겨 받아야한다.
	System.out.println("MemberUpdateProc.jsp의 mem_id=>" + mem_id);
	
	MemberDAO memMgr = new MemberDAO(); //메서드 호출을 위한 객체 형성 (수정해주는 메서드 호출)
	boolean check = memMgr.memberUpdate(mem); //반환값이 있어야 한다.
	System.out.println("MemberUpdateProc.jsp의 회원수정유무(check값 확인) =>" + check);
%>

<%
	if(check){ //회원수정에 성공했다면
%>
		<script>
			alert("성공적으로 수정되었습니다.");
			location.href="../Login.jsp";
		</script>
	<%}else{%>
		<script>
			alert("수정도중 에러가 발생되었습니다.");
			history.back();
		</script>
<%}%>