package hewon;

//DTO�� ���󿡼� �����͸� �Է��� ��(ex.ȸ������, ȸ������) �ʵ庰�� �������ִ� Ŭ������.
public class MemberDTO {
	//��������� �̸��� ���̺��� �ʵ��� ��������, �ٸ����� �ִ�.
	//Register.jsp���� <input type="text" name="mem_id">�� �ۼ��� �ߴ�. ���⼭ ������ name�� �̸��� ������Ѵ�. 
	//-> input type�� name�� ��������� �̸��� �ݵ�� �����ؾ��Ѵ�.
	private String mem_id; //ȸ�� ���̵�
	private String mem_passwd; //���
	private String mem_name; //ȸ����
	private String mem_email; //�̸���
	private String mem_phone; //��ȭ��ȣ
	private String mem_zipcaode; //�����ȣ
	private String mem_address; //�ּ�
	private String mem_job; //����
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_passwd() {
		return mem_passwd;
	}
	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = mem_passwd;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_zipcaode() {
		return mem_zipcaode;
	}
	public void setMem_zipcaode(String mem_zipcaode) {
		this.mem_zipcaode = mem_zipcaode;
	}
	public String getMem_address() {
		return mem_address;
	}
	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}
	public String getMem_job() {
		return mem_job;
	}
	public void setMem_job(String mem_job) {
		this.mem_job = mem_job;
	}
	
	
	
	
}
