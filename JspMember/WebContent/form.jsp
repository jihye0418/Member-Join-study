<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script>
	function check(){
		if(document.myForm.test.value==""){
			alert("입력하세요")
			return;
		}else{
			myForm.submit();
		}
	}
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="myForm" method="post" action="print.jsp">
	전달할 값: <input type="text" name="test">
	<input type="button" value="전송" onclick="check()">
</form>
</body>
</html>