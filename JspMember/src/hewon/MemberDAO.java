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
	private PreparedStatement pstmt = null; //SQL������ �����ϱ� ���� �ʿ�
	private ResultSet rs = null; //select ���� ó���� �� �ʿ�(select ����� ����-> SQL ���� �� �����)
	//=========================================
	private String sql = null; //������ SQL���� ó���ϱ� ���� �ʿ�(SQL�� ���⿡ �ۼ�)
	
	
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
			con=pool.getConnection(); //DB�� ������ �Ǹ� Connection�� �����ش�.
			System.out.println("con=>" + con);
			sql="select id,passwd from member where id=? and passwd=?";//� ���� ������ �𸣱� ������ ? �ۼ�.
			pstmt=con.prepareStatement(sql); //���� �ۼ��� sql �ڵ带 DB�� �־��ش�.
			pstmt.setString(1,id); //?�� ����, �Է��� ��
			pstmt.setString(2,passwd);
			rs=pstmt.executeQuery(); //��������� ResultSet�� ��ü ���� ��ȯ��. (select������ ������ �� ���)
			check=rs.next(); //�����Ͱ� �����ϸ� true, ������ false���� check�� ����.
		}catch(Exception e) {
			System.out.println("loginCheck()������ ��������=>" + e);
		} finally{//�� DB���� ����
			pool.freeConnection(con, pstmt, rs);
		}
		return check; //LoginProc.jsp�� ���� -> true�� -> LoginSucess.jsp�� 
	}
	
	
//	2) �ߺ�idüũ ���
	public boolean idCheck(String id) {
		boolean idCheck = false;
		try {
			con = pool.getConnection();
			sql="select * from member where id = ?";
			pstmt = con.prepareStatement(sql); //SQL ���� DB�� ����
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			idCheck = rs.next();
		}catch(Exception e) {
			System.out.println("idCheck()������ ��������=>" + e);
		}
		return idCheck;
	}

	
	
	
	
	
//	3) �����ȣ �˻�(�ǽð� �˻�)
	public Vector<ZipcodeDTO> zipcodeRead(String area3){
		Vector<ZipcodeDTO> vecList = new Vector();
		
		try {
			con = pool.getConnection(); //��ü ������
			//SQL ����
			//sql = "select* from zipcode where area3 like '%�ߵ�%'";
			sql = "select* from zipcode where area3 like'" + area3+"%'";
			pstmt = con.prepareStatement(sql); //SQL ���� DB�� ����
			rs = pstmt.executeQuery();
			
			//�˻��� ���ڵ� ������ŭ ã�� �����Ͱ� ������ ZipcodeDTO�� ��ƶ�
			while(rs.next()) {
				//��� ���? -> ZipcodeDTO�� �ʵ庰�� ���� => rs.getString("�ʵ��"); ==> setter�� �ʵ庰�� ����
				ZipcodeDTO tempZipcode = new ZipcodeDTO();
				tempZipcode.setZipcode(rs.getString("zipcode")); //�����ȣ
				tempZipcode.setArea1(rs.getString("area1"));
				tempZipcode.setArea2(rs.getString("area2"));
				tempZipcode.setArea3(rs.getString("area3"));
				tempZipcode.setArea4(rs.getString("area4"));
				
				//���Ϳ� �߰�
				vecList.add(tempZipcode); //������ ���� ���� �� vector�� ArrayList�� ��ƾ� �� (�˻������ ������ ������ �ʿ䰡 �ִ�.)
			}
		}catch(Exception e) {
			System.out.println("zipcodeRead() ����=>" + e);
		}finally {
			pool.freeConnection(con, pstmt, rs); //�ڵ����� �޸� ����
		}
		return vecList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	4) ȸ������ ���

//	5) ȸ������ ���� �� Ư�� ȸ���� ���� �˻�

//	6) ȸ������ ���

//	7) ȸ��Ż�� ���

//	8) ������ -> ȸ������Ʈ ��� (-����¡ó���� �ʿ��ؼ� �Խ��� ��� �� ����)
	
}
