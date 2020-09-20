<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import = "hewon.*" %>
<jsp:useBean id="memMgr" class="hewon.MemberDAO" />

<%
	//MemberUpdate.jsp?mem_id='nup';으로 값을 넘겼다면
	//String mem_id = request.getParameter("mem_id");로 처리를 해야 한다. 
	
	String mem_id = (String)session.getAttribute("idKey"); //형변환 필요 (객체에 저장할 때는 Object형으로 저장되기 때문)
	System.out.println("MemberUpdate.jsp의 mem_id=>" + mem_id);
	
	//MemberDAO의 getMember()를 호출해야 한다. --> action태그로 위쪽에 작성했다.
	//<jsp:useBean id="memMgr" class="hewon.MemberDAO" />
	
	//MemberDTO객체를 얻어온다.
	MemberDTO mem = memMgr.getMember(mem_id);
%>

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
        <form name="regForm" method="post" action="./sub/MemberUpdateProc.jsp">
          <tr align="center" bgcolor="#996600"> 
            <td colspan="3"><font color="#FFFFFF">
            <b>회원수정</b></font></td>
          </tr>
          <tr> 
            <td width="16%">아이디</td>
            <td width="57%">
              <!-- 아이디는 수정하지 않고 있는 그대로 출력 -->
              <%=mem.getMem_id() %>
            </td>
          </tr>
          <tr> 
            <td>패스워드</td>
            <td> <input type="password" name="mem_passwd" size="15" value="<%=mem.getMem_passwd() %>"> </td>
          </tr>
          <tr> 
            <td>이름</td>
            <td> <input type="text" name="mem_name" size="15" value="<%=mem.getMem_name() %>"> </td>
          </tr>
          <tr> 
            <td>이메일</td>
            <td> <input type="text" name="mem_email" size="27" value="<%=mem.getMem_email() %>"> </td> 
            <!-- 우리가 필드명은 e_mail로 했는데 이건 필드명이 아니기 때문에 email로 쓴다 -->
          </tr>
          <tr>  
            <td>전화번호</td>
            <td> <input type="text" name="mem_phone" size="20" value="<%=mem.getMem_phone() %>"> </td>
          </tr>
		  <tr>  
            <td>우편번호</td>
            <td> <input type="text" name="mem_zipcode" size="7" value="<%=mem.getMem_zipcode() %>">
                 <input type="button" value="우편번호찾기" onClick="zipCheck()"></td>
          </tr>
		  <tr>  
            <td>주소</td>
            <td><input type="text" name="mem_address" size="70" value="<%=mem.getMem_address() %>"></td>
          </tr>
		  <tr>  
            <td>직업</td>
            <td>
            	<select name=mem_job>
 					<option value="0">선택하세요.
 					<option value="회사원">회사원
 					<option value="연구전문직">연구전문직
 					<option value="교수학생">교수학생
 					<option value="일반자영업">일반자영업
 					<option value="공무원">공무원
 					<option value="의료인">의료인
 					<option value="법조인">법조인
 					<option value="종교,언론,에술인">종교.언론/예술인
 					<option value="농,축,수산,광업인">농/축/수산/광업인
 					<option value="주부">주부
 					<option value="무직">무직
 					<option value="기타">기타
				 </select>
				 <script>
				 	document.regForm.mem_job.value="<%=mem.getMem_job()%>";
				 	//document.폼이름.이름.value
				 </script>
		  	</td>
          </tr>
          <tr> 
            <td colspan="3" align="center"> 
             <input type="submit" value="수정완료"> 
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
             <input type="button" value="수정취소" onclick="history.back()"> 
            </td>
          </tr>
          <!-- hidden객체를 이용(직접적으로 입력x 매개변수 전달하는 방법) -->
          <input type="hidden" name="mem_id" value="<%=mem_id%>">
        </form>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
