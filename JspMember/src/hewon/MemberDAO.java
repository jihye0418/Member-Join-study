package hewon;

//웹상에서 호출되는 메서드를 선언해주는 클래스(먼저DB에 접속해야 된다.) 
//DAO를 비지니스 로직빈이라고도 부른다. 
import java.sql.*; //DB연동
import java.util.*; //컬렉션(vector, ArrayList, HashMap... 사용)

public class MemberDAO {  //has a관계

	//1. DB ConnectionMgr객체 필요
	private DBConnectionMgr pool = null; //DB에 미리 연결해서 대기.(연결 요청이 들어오면 연결(connection)을 빌려주고 종료되면 pool에게 connection반납함.)
	//필수======================================
	private Connection con = null; //자바를 DB에 연결하기
	private PreparedStatement pstmt = null; //SQL문장을 실행하기 위해 필요
	private ResultSet rs = null; //select 구문 처리할 때 필요(select 결과를 담음-> SQL 실행 후 결과값)
	//=========================================
	private String sql = null; //공통의 SQL구문 처리하기 위해 필요(SQL은 여기에 작성)
	
	
	//has a 관계 성립 조건2. 생성자를 통해서 객체를 생성한다.
	public MemberDAO() {
		//DB연결은 예외처리!
		try {
			pool = DBConnectionMgr.getInstance();//싱글톤은 정적메서드다. (pool 객체를 가져옴 => DB연결을 하겠다. )
			System.out.println("pool=>" + pool);
		}catch(Exception e) {
			System.out.println("DB연결 오류=>" + e);
		}
	}
	
	
	//3. 웹상에서 호출되는 기능(회원관리)
//	1) 회원로그인 기능
	public boolean loginCheck(String id, String passwd) { //아이디, 비밀번호는 매개변수로 '문자'를 받는다. 있다면 true, 없다면 false
		//① DB연결
		boolean check = false; //계정 존재 유무 변수의 default값을 false로 둠
		//② 실행시킬 SQL구문 필요
			//SQL구문은 예외처리가 필요하다.
		try {
			con=pool.getConnection(); //DB에 연결이 되면 Connection을 빌려준다.
			System.out.println("con=>" + con);
			sql="select id,passwd from member where id=? and passwd=?";//어떤 값이 들어올지 모르기 때문에 ? 작성.
			pstmt=con.prepareStatement(sql); //내가 작성한 sql 코드를 DB에 넣어준다.
			pstmt.setString(1,id); //?의 순서, 입력할 값
			pstmt.setString(2,passwd);
			rs=pstmt.executeQuery(); //결과값으로 ResultSet의 객체 값을 반환함. (select구문을 수행할 때 사용)
			check=rs.next(); //데이터가 존재하면 true, 없으면 false값을 check로 받음.
		}catch(Exception e) {
			System.out.println("loginCheck()실행중 에러유발=>" + e);
		} finally{//③ DB연결 해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check; //LoginProc.jsp로 전송 -> true면 -> LoginSucess.jsp로 
	}
	
	
//	2) 중복id체크 기능
	public boolean checkId(String id) {
		boolean check = false; //중복 계정의 존재 유무 (처음에는 없는 것으로 간주)
		
		//DB연결 (-> 예외처리 필요)
		try {
			con = pool.getConnection();//연결객체 얻어옴
			sql = "select id from member where id=?"; //?를 썼다는 것은 prepareStatement를 쓰겠다
			pstmt = con.prepareStatement(sql); //sql문에 들어갈 pstmt를 얻어옴
			pstmt.setString(1,id);//문자를 넣을때는 setString  (웹상에서 입력 받은 중복id값) (?를 썼으니 거기에 들어갈 변수를 써야한다)
			rs=pstmt.executeQuery(); //ResultSet 객체에 결과값을 담음
			check = rs.next(); //rs.next()가 true면 중복계정 존재, false면 중복계정 없어 사용 가능. (-> 컬렉션 부분 다시 확인하기!)
		}catch(Exception e) {
			System.out.println("checkId()메서드 호출 에러=>" + e);
		}finally {
			pool.freeConnection(con, pstmt, rs);//문제를 처리하고 나서 빠져나갈 구문(메모리 해제)
		}
		return check;//boolean으로 했기 때문에 반환값이 있어야 한다.
	}

	
	
	
	
	
//	3) 우편번호 검색(실시간 검색)
	public Vector<ZipcodeDTO> zipcodeRead(String area3){
		Vector<ZipcodeDTO> vecList = new Vector();
		
		try {
			con = pool.getConnection(); //객체 가져옴
			//SQL 구문
			//sql = "select* from zipcode where area3 like '%중동%'";
			sql = "select* from zipcode where area3 like'" + area3+"%'";
			pstmt = con.prepareStatement(sql); //SQL 문장 DB에 전달
			rs = pstmt.executeQuery();
			
			//검색된 레코드 개수만큼 찾은 데이터가 있으면 ZipcodeDTO에 담아라
			while(rs.next()) {
				//어디에 담아? -> ZipcodeDTO에 필드별로 저장 => rs.getString("필드명"); ==> setter에 필드별로 저장
				ZipcodeDTO tempZipcode = new ZipcodeDTO();
				tempZipcode.setZipcode(rs.getString("zipcode")); //우편번호
				tempZipcode.setArea1(rs.getString("area1"));
				tempZipcode.setArea2(rs.getString("area2"));
				tempZipcode.setArea3(rs.getString("area3"));
				tempZipcode.setArea4(rs.getString("area4"));
				
				//백터에 추가
				vecList.add(tempZipcode); //여러개 담을 때는 꼭 vector나 ArrayList에 담아야 함 (검색결과가 많으면 저장할 필요가 있다.)
			}
		}catch(Exception e) {
			System.out.println("zipcodeRead() 에러=>" + e);
		}finally {
			pool.freeConnection(con, pstmt, rs); //자동으로 메모리 해제
		}
		return vecList;
	}
	
	

	/*
	 * insert, update, delete -> 매개변수O, 반환값X 
	 * where 조건식이 있다? -> 매개변수O
	 */
	//	4) 회원가입 기능
	//회원가입-> 방법1) insert into member values(?,?,?,?,?,?,?,?); //-> 필드의 모든 것을 입력받아야 하는 경우에 사용
	//             방법2) insert into member(필드명1, 필드명2,,,,) values(?,?..);
	public boolean memberInsert(MemberDTO mem) { //call by reference
		boolean check = false; //회원가입 성공유무
		
		try {
			con = pool.getConnection();
			con.setAutoCommit(false); //con.commit()을 사용하기 전까지 insert, update, delete X
			
			sql="insert into member values(?,?,?,?,?,?,?,?)"; //필드의 모든 값 받아옴
			pstmt = con.prepareStatement(sql); //연결객체 얻어옴
			pstmt.setString(1, mem.getMem_id()); //mem.set_mem_id("kkk");     //<->getter
			pstmt.setString(2, mem.getMem_passwd()); //암호
			pstmt.setString(3, mem.getMem_name()); //이름
			pstmt.setString(4, mem.getMem_email()); //이메일
			pstmt.setString(5, mem.getMem_phone()); //전번
			pstmt.setString(6, mem.getMem_zipcode()); //우편번호
			pstmt.setString(7, mem.getMem_address()); //주소
			pstmt.setString(8, mem.getMem_job()); //직업
			
			int insert = pstmt.executeUpdate();//반환값 변수 (insert기 때문에 executeUpdate()     //1-> 성공 / 0 -> 실패
			
			con.commit(); //실질적인 SQL 실행 (테이블에 저장)
			
			if(insert > 0) { //실행했다면
				check = true;
			}
		}catch(Exception e) {
			System.out.println("memberInsert() 메서드 호출 에러=>" + e);
		}finally {
			pool.freeConnection(con, pstmt); //select가 아니기 때문에 result의 값을 가져올 필요는 없다. 
		}
		return check; //MemberInsert.jsp가 값을 반환 받음
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	5) 회원정보 수정 전 특정 회원의 정보 검색 -> getMember

//	6) 회원수정 메서드

//	7) 회원탈퇴 기능

//	8) 관리자 -> 회원리스트 출력 (-페이징처리가 필요해서 게시판 배운 뒤 진행)
	
}
