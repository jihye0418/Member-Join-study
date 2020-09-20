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
	private PreparedStatement pstmt = null; //sql문장을 실행하기 위해 필요 (SQL문장 실행하고 결과를 반환하는 기능을 캡슐화한 인터페이스)
	private ResultSet rs = null; //select 구문 처리할 때 필요(select 결과를 담음-> SQL 실행 후 결과값)
	//=========================================
	private String sql = null; //공통의 sql구문 처리하기 위해 필요(SQL은 여기에 작성)
	
	
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
			con=pool.getConnection();
			System.out.println("con=>" + con);
			sql="select id,passwd from member where id=? and passwd=?";//어떤 값이 들어올지 모르기 때문에 ? 작성.
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id); //?의 순서, 입력할 값
			pstmt.setString(2,passwd);
			rs=pstmt.executeQuery();
			check=rs.next(); //데이터가 존재하면 true, 없으면 false값을 check로 받음.
		}catch(Exception e) {
			System.out.println("loginCheck()실행중 에러유발=>" + e);
		} finally{//③ DB연결 해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check; //LoginProc.jsp로 전송 -> true면 -> LoginSucess.jsp로 
	}
	
	
//	2) 중복id체크 기능

//	3) 우편번호 검색(실시간 검색)

//	4) 회원가입 기능

//	5) 회원정보 수정 전 특정 회원의 정보 검색

//	6) 회원수정 기능

//	7) 회원탈퇴 기능

//	8) 관리자 -> 회원리스트 출력 (-페이징처리가 필요해서 게시판 배운 뒤 진행)
	
}
