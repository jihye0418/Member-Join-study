<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//Register.jsp-> RegisterProc.jsp(8개의 입력값이 들어온다.)
	request.setCharacterEncoding("utf-8"); //한글 깨짐 방지
	//값 넘어온 것을 저장
	
	/* MemberDTO mem = new MemberDTO();//입력한 값을 저장한다. (setMethod가 MemberDTO에 있으므로 객체를 만들어준다.)
	mem.setMem_id(request.getParameter("mem_id"));
	mem.setMem_passwd(request.getParameter("mem_passwd"));
	 */
%>

<!-- 객체 생성 -->
<jsp:useBean id="mem" class="hewon.MemberDTO" />

<!-- setter메서드 호출 -->
<jsp:setProperty name="mem" property="*" /> 
<%-- <jsp:setProperty name="mem" property="mem_id" value="<%= mem_id %>" /> --%>


<html>
<head>
<title>회원가입 확인</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js?ver=1"></script>
</head>
<body bgcolor="#996600" onLoad="regForm.mem_id.focus()">
<br><br>
<table align="center" border="0" cellspacing="0" cellpadding="5" >
  <tr> 
    <td align="center" valign="middle" bgcolor="#FFFFCC"> 
      <table border="1" cellspacing="0" cellpadding="2"  align="center">
        <form name="regForm" method="post" action="./sub/MemberInsert.jsp">
          <tr align="center" bgcolor="#996600"> 
            <td colspan="3"><font color="#FFFFFF">
            <b><jsp:getProperty name="mem" property="mem_name" />회원님이 작성하신 내용입니다. 확인해 주세요.</b></font></td>
          </tr>
          <tr> 
            <td width="16%">아이디</td>
            <td width="57%"> 
              <input type="text" name="mem_id" size="15" value="<jsp:getProperty name="mem" property="mem_id" />">
          </tr>
          <tr> 
            <td>패스워드</td>
            <td> <input type="password" name="mem_passwd" size="15" value="<jsp:getProperty name="mem" property="mem_passwd" />"> </td>
          </tr>
          <tr> 
            <td>이름</td>
            <td> <input type="text" name="mem_name" size="15" value="<jsp:getProperty name="mem" property="mem_name" />"> </td>
          </tr>
          <tr> 
            <td>이메일</td>
            <td> <input type="text" name="mem_email" size="27" value="<jsp:getProperty name="mem" property="mem_email" />"> </td>
          </tr>
          <tr>  
            <td>전화번호</td>
            <td> <input type="text" name="mem_phone" size="20" value="<jsp:getProperty name="mem" property="mem_phone" />"> </td>
          </tr>
		  <tr>  
            <td>우편번호</td>
            <td> <input type="text" name="mem_zipcode" size="7" value="<jsp:getProperty name="mem" property="mem_zipcode" />">
                 <input type="button" value="우편번호찾기" onClick="zipCheck()"></td>
          </tr>
		  <tr>  
            <td>주소</td>
            <td><input type="text" name="mem_address" size="70" value="<jsp:getProperty name="mem" property="mem_address" />"></td>
          </tr>
		  <tr>  
            <td>직업</td>
            <td><input type="text" name="mem_job" value="<jsp:getProperty name="mem" property="mem_job" />"></td>
          </tr>
          <tr> 
            <td colspan="3" align="center"> 
             <input type="submit" value="확인완료"> 
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
             <input type="button" value="다시쓰기" onclick="history.back()"> 
             <!-- 만약에 이동할 페이지를 알고 있다면 location href='Register.jsp' -->
            </td>
          </tr>
        </form>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
