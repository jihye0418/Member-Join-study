<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="memMgr" class="hewon.MemberDAO" />

<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	String mem_id = request.getParameter("mem_id");
	boolean check = memMgr.idCheck(mem_id);
%>
<html>
<body bgcolor="#FFFFCC">
<center>
<%
if(check){%>
<b><%=mem_id %></b>는 이미 존재하는 ID입니다.<p>
<%}else{%>
<b><%=mem_id %></b>는 사용 가능합니다.<p>
<%}%>
<a href="javascript:window.close()">닫기</a>
</center>
</body>
</html>