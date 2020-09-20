<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
/*url 창에 로그인이 완료된 페이지의 URL을 붙이면 로그인이 되지 않을까? 
		-> 그래서 로그인이 된 사람인지 아닌지 판단을 해야 한다.
*/
String mem_id = (String)session.getAttribute("idKey"); //형변환 필요 (객체에 저장할 때는 Object형으로 저장되기 때문)
System.out.println("LoginSuccess.jsp의 mem_id=>" + mem_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인에 성공했을 때 보여줄 페이지</title>
</head>
<body bgcolor="ffffcc">
	<center>
		<%
			if(mem_id != null){ //로그인을 한 사람이라면
		%><!-- html태그가 사용되기 때문에 태그를 한번 막아둠 -->
		<b><%=mem_id %></b>님 환영합니다 :)<p>
		당신은 제한된 기능을 사용할 수 있습니다.<p>
		<a href="LogOut.jsp">로그아웃</a>
		<%} %>
	</center>
</body>
</html>