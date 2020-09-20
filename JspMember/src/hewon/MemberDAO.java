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
	public boolean checkId(String id) {
		boolean check = false; //�ߺ� ������ ���� ���� (ó������ ���� ������ ����)
		
		//DB���� (-> ����ó�� �ʿ�)
		try {
			con = pool.getConnection();//���ᰴü ����
			sql = "select id from member where id=?"; //?�� ��ٴ� ���� prepareStatement�� ���ڴ�
			pstmt = con.prepareStatement(sql); //sql���� �� pstmt�� ����
			pstmt.setString(1,id);//���ڸ� �������� setString  (���󿡼� �Է� ���� �ߺ�id��) (?�� ������ �ű⿡ �� ������ ����Ѵ�)
			rs=pstmt.executeQuery(); //ResultSet ��ü�� ������� ����
			check = rs.next(); //rs.next()�� true�� �ߺ����� ����, false�� �ߺ����� ���� ��� ����. (-> �÷��� �κ� �ٽ� Ȯ���ϱ�!)
		}catch(Exception e) {
			System.out.println("checkId()�޼��� ȣ�� ����=>" + e);
		}finally {
			pool.freeConnection(con, pstmt, rs);//������ ó���ϰ� ���� �������� ����(�޸� ����)
		}
		return check;//boolean���� �߱� ������ ��ȯ���� �־�� �Ѵ�.
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
	
	

	/*
	 * insert, update, delete -> �Ű�����O, ��ȯ��X 
	 * where ���ǽ��� �ִ�? -> �Ű�����O
	 */
	//	4) ȸ������ ���
	//ȸ������-> ���1) insert into member values(?,?,?,?,?,?,?,?); //-> �ʵ��� ��� ���� �Է¹޾ƾ� �ϴ� ��쿡 ���
	//             ���2) insert into member(�ʵ��1, �ʵ��2,,,,) values(?,?..);
	public boolean memberInsert(MemberDTO mem) { //call by reference
		boolean check = false; //ȸ������ ��������
		
		try {
			con = pool.getConnection();
			con.setAutoCommit(false); //con.commit()�� ����ϱ� ������ insert, update, delete X
			
			sql="insert into member values(?,?,?,?,?,?,?,?)"; //�ʵ��� ��� �� �޾ƿ�
			pstmt = con.prepareStatement(sql); //���ᰴü ����
			pstmt.setString(1, mem.getMem_id()); //mem.set_mem_id("kkk");     //<->getter
			pstmt.setString(2, mem.getMem_passwd()); //��ȣ
			pstmt.setString(3, mem.getMem_name()); //�̸�
			pstmt.setString(4, mem.getMem_email()); //�̸���
			pstmt.setString(5, mem.getMem_phone()); //����
			pstmt.setString(6, mem.getMem_zipcode()); //�����ȣ
			pstmt.setString(7, mem.getMem_address()); //�ּ�
			pstmt.setString(8, mem.getMem_job()); //����
			
			int insert = pstmt.executeUpdate();//��ȯ�� ���� (insert�� ������ executeUpdate()     //1-> ���� / 0 -> ����
			
			con.commit(); //�������� SQL ���� (���̺� ����)
			
			if(insert > 0) { //�����ߴٸ�
				check = true;
			}
		}catch(Exception e) {
			System.out.println("memberInsert() �޼��� ȣ�� ����=>" + e);
		}finally {
			pool.freeConnection(con, pstmt); //select�� �ƴϱ� ������ result�� ���� ������ �ʿ�� ����. 
		}
		return check; //MemberInsert.jsp�� ���� ��ȯ ����
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	5) ȸ������ ���� �� Ư�� ȸ���� ���� �˻� -> getMember

//	6) ȸ������ �޼���

//	7) ȸ��Ż�� ���

//	8) ������ -> ȸ������Ʈ ��� (-����¡ó���� �ʿ��ؼ� �Խ��� ��� �� ����)
	
}
