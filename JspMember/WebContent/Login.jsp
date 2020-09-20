<%@page contentType="text/html;charset=UTF-8"%>

<%
/*url 창에 로그인이 완료된 페이지의 URL을 붙이면 로그인이 되지 않을까? 
		-> 그래서 로그인이 된 사람인지 아닌지 판단을 해야 한다.
*/
String mem_id = (String)session.getAttribute("idKey"); //형변환 필요 (객체에 저장할 때는 Object형으로 저장되기 때문)
System.out.println("LoginSuccess.jsp의 mem_id=>" + mem_id);
%>

<HTML>
 <HEAD>
  <TITLE> 로그인 </TITLE>
<link href="style.css" rel="stylesheet"
      type="text/css">
<SCRIPT LANGUAGE="JavaScript" src="script.js">
</SCRIPT>
 </HEAD>

 <BODY onload="document.login.mem_id.focus()" bgcolor="#FFFFCC">
  <center>
  
  
  <!-- 로그인이 된 상태(mem_id가 null이 아니면) -->
<%
  if(mem_id != null){ //로그인을 한 사람이라면
	%><!-- html태그가 사용되기 때문에 태그를 한번 막아둠 -->
	<b><%=mem_id %></b>님 환영합니다 :)<p>
	당신은 제한된 기능을 사용할 수 있습니다.<p>
	<a href="LogOut.jsp">로그아웃</a>
<%} else{%>
  <br><br><br>
  <!-- 로그인 안된 상태 -->
     <TABLE>
    <form name="login" method="post" action="LoginProc.jsp">
     <TR>
		<TD align="center" colspan="2">
		<h4>로그인</h4></TD>
     </TR>

     <TR>
		<TD>아이디</TD>
		<TD><INPUT TYPE="text" NAME="mem_id"></TD>
     </TR>
     <TR>
		<TD>비밀번호</TD>
		<TD><INPUT TYPE="password" NAME="mem_passwd"></TD>
     </TR>
     <TR>
		<TD colspan="2"><div align="center">
		<INPUT TYPE="button" value="로그인" onclick="loginCheck()">&nbsp;
		<INPUT TYPE="button" value="회원가입"
		onclick="memberReg()">
		</div>
		</TD>
     </TR>
	 </form>
     </TABLE>
<%} %>
  </center>
 </BODY>
</HTML>
