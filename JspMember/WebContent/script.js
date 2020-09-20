function loginCheck(){
	//형식)document.폼객체명.입력양식객체명.속성
	if(document.login.mem_id.value==""){
		alert("아이디를를 입력해 주세요.");
		document.login.mem_id.focus();
		return; //return false;
	}
	if(document.login.mem_passwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.login.mem_passwd.focus();
		return;
	}
	document.login.submit();
}

function memberReg(){
	
}

function inputCheck(){
	if(document.regForm.mem_id.value==""){
		alert("아이디를 입력해 주세요.");
		document.regForm.mem_id.focus();
		return;
	}
	if(document.regForm.mem_passwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.regForm.mem_passwd.focus();
		return;
	}
	if(document.regForm.mem_repasswd.value==""){
		alert("비밀번호를 확인해 주세요");
		document.regForm.mem_repasswd.focus();
		return;
	}
	if(document.regForm.mem_name.value==""){
		alert("이름을 입력해 주세요.");
		document.regForm.mem_name.focus();
		return;
	}
	if(document.regForm.mem_email.value==""){
		alert("이메일을 입력해 주세요.");
		document.regForm.mem_email.focus();
		return;
	}
	if(document.regForm.mem_phone.value==""){
		alert("연락처를 입력해 주세요.");
		document.regForm.mem_phone.focus();
		return;
	}
	if(document.regForm.mem_job.value=="0"){
		alert("직업을 선택해 주세요.");
		document.regForm.mem_job.focus();
		return;
	}
	
	if(document.regForm.mem_passwd.value != document.regForm.mem_repasswd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regForm.mem_repasswd.focus();
		return;
	}
	//document.폼객체명.submit() -> 전송해라~~~ (action="LoginProc.jsp");
	document.regForm.submit();
}

//중복ID체크 해주는 자바스크립트함수 선언
function idCheck(id){ //함수명 확인
	if(id == ""){ //값을 입력하지 않았다면
		alert("아이디를 먼저 입력하세요.");
		document.regForm.mem_id.focus() //refForm(폼 이름)의 mem_id에 포커스를 줘라.
	}else{
		url="IdCheck.jsp?mem_id=" + id;
		window.open(url,"post","width=300, height=150");
		//window.open()불러올 변수명, 창의 이름, 창의 옵션)
	}
}



//우편번호를 검색해주는 함수선언
function zipCheck(){ //javascript와 jsp의 연결★
    //우편번호 검색창 띄우기
	url = "ZipCheck.jsp?check=y" //check=y가 되었을 때는 검색창 , check=n이 되면 결과창
	window.open(url, "post","left = 400, top=220, width=500, height=300, menubar=no, toolbar=no, status=yes, scrollbars=yes");
	//툴바, 메뉴바는 필요하지 않다 
}
