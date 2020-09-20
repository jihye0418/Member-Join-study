<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오라클 접속예제</title>
</head>
<body>
<%
//DB에 접속을 성공한 상태 = Connection 객체를 얻어 온 경우
Connection con = null;

//접속 위치 =  jdbc:oracle:thin:@상대방의 ip주소:접속포트번호(1521):SID(orcl)
String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//localhost : 내 주소

Statement stmt = null; // Statement 객체 생성 /create table
PreparedStatement pstmt = null; //insert into (데이터를 집어넣음) // PreparedStatement 객체 생성, sql문장을 매개변수로 전달   --> Statement보다 속도가 빨라 선호. 
Statement stmt2 = null; //select 
ResultSet rs = null; //표로 보여주기 위함 (select 검색결과를 표 형태로) // Query 결과를 ResultSet 객체로 생성
String sql = "";//

// SQL 관련 메소드 사용시 SQLException 예외를 처리해야 함
try {
	//1. 접속할 드라이버를 메모리에 올리기.(상위패키지명.하위패키지명.클래스명)====================
	// 접속할 DB 정보로 Connection 객체 생성
	Class.forName("oracle.jdbc.driver.OracleDriver"); //정해져있음.
	
	
	
	//2. 접속 인증 절차 -> 1. 접속경로 2. 계정명 3. 암호===============================
	con = DriverManager.getConnection(url, "scott", "tiger");
	System.out.println("접속 con =>" + con); //실행결과 : 접속 con =>oracle.jdbc.driver.T4CConnection@4629104a
	
	
	
	//3.테이블 생성 (// Statement 객체 생성)==================================
	stmt = con.createStatement(); //문장을 만들어달라~ 메서드 반환형
	// 테이블 생성 SQL문장 작성
	sql = "create table MyTest(name varchar2(20), age number)";
	// sql문장 실행 및 반환 값 저장
	int create = stmt.executeUpdate(sql);
	 // 결과 확인
	System.out.println("테이블 성공유무(create)" + create); //성공하면1 / 실패하면 0
	
	
	
	//4. insert(회원가입) // INSERT문, 테이블에 데이터 입력==========================
	pstmt = con.prepareStatement("insert into MyTest values(?,?)"); //?,? -> 계정명/암호
	//=> sql = "insert into MyTest values(?,?)";
	
	// 첫 번째 매개변수(?)에 "hong" 전달
	pstmt.setString(1, "hong"); //웹 상에서는 request.getParameter("name");
	
	// 두 번째 ?에 23 전달
	pstmt.setInt(2, 23); //웹 상에서는 request.getParameter("age");
	
	// INSERT문 실행 및 반환 값 저장
	int insert = pstmt.executeUpdate(); //성공하면1 / 실패하면 0
	
	System.out.println("데이터 입력 성공 유무(insert)=>" + insert);
	
	
	
	//5. select => 필드별로 출력해서 결과보기==================================
	// SELECT문, 테이블 내 데이터 조회
	stmt2 = con.createStatement();
	
	// Query 반환값 ResultSet 객체 반환
	rs = stmt2.executeQuery("select * from MyTest"); //* -> 테이블의 만들어진 필드 순서
	%>
	
	
	<table border="1" cellspacing="0" cellpadding="0">
	<tr bgcolor="pink">
		<th>name</th>
		<th>age</th>
	</tr>
	
	<%
	//6. 테이블의 데이터 불러오기=========================================
	while(rs.next()) {//rs.next()==true -> 이동시킬 레코드가 계속 존재하는 한
	%>
	<tr>
		<td><%= rs.getString("name") %></td> <!-- name에 "name" 컬럼 데이터 저장 -->
		<td><%= rs.getInt(2) %><!-- age에 "age" 컬럼 데이터 저장 -->
	</tr>
	<% }

	//7. 메모리 해제 순서 => 생성된 객체 역순===================================
	rs.close();
	stmt2.close();
	pstmt.close();
	stmt.close();
	
}catch(Exception e) { //SQLException => SQL 구문 실행 오류 예외처리
	System.out.println("DB연결 실행 =>" + e);
}

%>
</table>
</body>
</html>