package hewon;

//���󿡼� ȣ��Ǵ� �޼��带 �������ִ� Ŭ����(����DB�� �����ؾ� �ȴ�.) 
//DAO�� �����Ͻ� �������̶�� �θ���. 
import java.sql.*; //DB����
import java.util.*; //�÷���(vector, ArrayList, HashMap... ���)

public class MemberDAO {  //has a����

	//1. DB ConnectionMgr��ü �ʿ�
	private DBConnectionMgr pool = null; //DB�� �̸� �����ؼ� ���.(���� ��û�� ������ ����(connection)�� �����ְ� ����Ǹ� pool���� connection�ݳ���.)
	//�ʼ�======================================
	private Connection con = null; //�ڹٸ� DB�� �����ϱ�
	private PreparedStatement pstmt = null; //sql������ �����ϱ� ���� �ʿ� (SQL���� �����ϰ� ����� ��ȯ�ϴ� ����� ĸ��ȭ�� �������̽�)
	private ResultSet rs = null; //select ���� ó���� �� �ʿ�(select ����� ����-> SQL ���� �� �����)
	//=========================================
	private String sql = null; //������ sql���� ó���ϱ� ���� �ʿ�(SQL�� ���⿡ �ۼ�)
	
	
	//has a ���� ���� ����2. �����ڸ� ���ؼ� ��ü�� �����Ѵ�.
	public MemberDAO() {
		//DB������ ����ó��!
		try {
			pool = DBConnectionMgr.getInstance();//�̱����� �����޼����. (pool ��ü�� ������ => DB������ �ϰڴ�. )
			System.out.println("pool=>" + pool);
		}catch(Exception e) {
			System.out.println("DB���� ����=>" + e);
		}
	}
	
	
	//3. ���󿡼� ȣ��Ǵ� ���(ȸ������)
//	1) ȸ���α��� ���
	public boolean loginCheck(String id, String passwd) { //���̵�, ��й�ȣ�� �Ű������� '����'�� �޴´�. �ִٸ� true, ���ٸ� false
		//�� DB����
		boolean check = false; //���� ���� ���� ������ default���� false�� ��
		//�� �����ų SQL���� �ʿ�
			//SQL������ ����ó���� �ʿ��ϴ�.
		try {
			con=pool.getConnection();
			System.out.println("con=>" + con);
			sql="select id,passwd from member where id=? and passwd=?";//� ���� ������ �𸣱� ������ ? �ۼ�.
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id); //?�� ����, �Է��� ��
			pstmt.setString(2,passwd);
			rs=pstmt.executeQuery();
			check=rs.next(); //�����Ͱ� �����ϸ� true, ������ false���� check�� ����.
		}catch(Exception e) {
			System.out.println("loginCheck()������ ��������=>" + e);
		} finally{//�� DB���� ����
			pool.freeConnection(con, pstmt, rs);
		}
		return check; //LoginProc.jsp�� ���� -> true�� -> LoginSucess.jsp�� 
	}
	
	
//	2) �ߺ�idüũ ���

//	3) �����ȣ �˻�(�ǽð� �˻�)

//	4) ȸ������ ���

//	5) ȸ������ ���� �� Ư�� ȸ���� ���� �˻�

//	6) ȸ������ ���

//	7) ȸ��Ż�� ���

//	8) ������ -> ȸ������Ʈ ��� (-����¡ó���� �ʿ��ؼ� �Խ��� ��� �� ����)
	
}
