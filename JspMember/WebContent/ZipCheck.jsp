<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "hewon.*, java.util.*"%>
<!DOCTYPE html>
<%
	//zipCheck()는 2개의 매개변수 값을 받는다. (창, area3)
	
	//한글처리필요
	request.setCharacterEncoding("utf-8");
	
	String check = request.getParameter("check"); //check=y를 받아옴 (내부적으로 전달 - 직접 입력받지는 않음) 
	String area3 = request.getParameter("area3"); //직접 입력해서 전달함
	System.out.println("check=>" + check + ", area3=>" + area3); //제대로 입력 받았는지 확인
	
	//MemberDAO memMgr = new MemberDAO(); //객체 얻어옴 -----> 이거 쓰기 싫으면<@page 안에 import = "hewon.*, java.util.*">
	//Vector를 사용했기 때문에 java.util.*도 써야 함
	MemberDAO memMgr = new MemberDAO();
	Vector<ZipcodeDTO> zipcodeList = memMgr.zipcodeRead(area3);
	int totalList = zipcodeList.size(); //List인터페이스의 크기는 size()
	System.out.println("검색된 총레코드수 (totalList)=>" + totalList);
%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우편번호 검색</title>
<link href="style.css" rel="stylesheet"
         type="text/css">
<script>
//동이름을 체크할 함수선언
  function dongCheck(){
	  if(document.zipForm.area3.value==""){
		  alert("동이름을 먼저 입력하세요!");
		  document.zipForm.area3.focus();
		  return;
	  }
	  document.zipForm.submit();
  }
  
  
  //검색된 레코드 중에서 sendAddress(4개의 매개변수전달 받음); -> 자기 주소와 거의 비슷한 주소 클릭
  //Regiester.jsp(부모창) -> 넣어주는 함수(자식창)
  //-> 자바에서 부모창을 가리킬 때는 opener (자바에서 부모 생성자를 super라고 하는 것과 같은 격)
  //opener.document.폼객체명.입력양식.속성 = 값
  function sendAddress(zipcode, area1, area2, area3, area4){
	  var address = area1 +" " + area2 + " " + area3 + " " + area4;
	  opener.document.regForm.mem_zipcode.value=zipcode; //우편번호 넣음
	  opener.document.regForm.mem_address.value = address; //나머지 주소
	  self.close(); //self=> 현재 열려 있는 자식창(ZipCheck.jsp_검색창)
  }
  
  
  
  
</script>
</head>
<body bgcolor="#FFFFCC">
  <center>
    <b>우편번호 찾기</b>
    <table>
  
  <!-- 현재 창 밑에 실시간으로 찾은 데이터를 출력시키기 위함  -->
  
   <form name="zipForm" method="post" action="ZipCheck.jsp"> <!-- 나에게 동작을 하라고 함(요청하는 페이지 = 결과 보여주는 페이지) -->
        <tr>
        <td><br>
        동이름 입력:<input type="text" name="area3">
        <input type="button" value="검색" onclick="dongCheck()"> <!-- 입력 유무 확인 -->
        </td>
        </tr>    
        <input type="hidden" name="check" value="n">
   </form>
   
   
   <%
   	//검색어를 입력하고 검색버튼을 눌렀다면
   	if(check.equals("n")){//검색 버튼 눌렀다면
   		if(zipcodeList.isEmpty()){//찾은 데이터가 없다면 == if(zipcodeList.size()==0)%>
	      <tr><td align="center">
	            <br>검색된 레코드가 없습니다.
	          </td>
	      </tr>
	      <%}else{ %>
			   <tr><td align="center"><br>
			   *검색후 ,아래 우편번호를 클릭하면 자동으로 입력됩니다</td></tr>
			     <%
			     	for(int i = 0; i<totalList; i++){
			     		ZipcodeDTO zipBean = zipcodeList.elementAt(i);//ArrayList로 나타냈다면 zipcodeList.get(i);
			     		String tempZipcode = zipBean.getZipcode(); //우편번호
			     		String tempArea1 = zipBean.getArea1().trim(); //trim: 공백 제거 //////시
			     		String tempArea2 = zipBean.getArea2().trim(); //구
			     		String tempArea3 = zipBean.getArea3().trim(); //읍
			     		String tempArea4 = zipBean.getArea4().trim(); //나머지
			     %>
			  <tr><td>
			  <a href="JavaScript:sendAddress('<%= tempZipcode %>', '<%= tempArea1 %>', '<%= tempArea2 %>', '<%= tempArea3 %>', ' <%= tempArea4 %>') ">
			  <%= tempZipcode %>&nbsp;<%=tempArea1 %>&nbsp;
			  <%=tempArea2 %>&nbsp;<%=tempArea3 %>&nbsp;<%=tempArea4 %>
  			  </a><br>
 			<%
			     	}//--for문
			     } //--else 문 
   			}//--if문
 			%>
 
 
    </td></tr>
    <tr><td align="center"><br>
<a href="JavaScript:this.close()">닫기</a>                      
    </table>
  </center>
</body>
</html>