<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="hewon.MemberDAO"%>
<!DOCTYPE html>
<%
	//script.js에서 IdCheck.jsp?mem_id='kkk' 를 넘겼다면  (? -> get방식)
	String mem_id = request.getParameter("mem_id"); //mem_id 값을 받아옴
	System.out.println("IdCheck.jsp의 mem_id =>" + mem_id);
	
	//자동으로 함수 호출(MemberDAO의 checkId메서드 호출)
	//MemberDAO의 객체를 가져와야 한다. action태그를 쓰거나 import를 쓰거나.
	
	MemberDAO memMgr = new MemberDAO();
	boolean check = memMgr.checkId(mem_id); //MemberDAO에 저장된 mem_id의 값을 받아온다.
%>



<html>
<head>
<meta charset="UTF-8">
<title>ID중복 체크</title>
</head>
<body bgcolor="#ffffcc">
	<center>
		<b><%=mem_id %></b>
		<%
			if(check){//check==true 이미 아이디가 존재한다면
				out.println("는 이미 존재하는 아이디입니다.<p>");
			}else{
				out.println("는 사용가능한 아이디입니다.<p>");
			}
		%>
		<a href="#" onclick="self.close()">닫기</a>
		<!-- 원래는 window.close()인데 자기 자신을 닫는 것이기 때문에 self.close()를 해도 된다. -->
	</center>
</body>
</html>