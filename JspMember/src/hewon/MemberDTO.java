package hewon;

//DTO는 웹상에서 데이터를 입력할 때(ex.회원가입, 회원수정) 필드별로 관리해주는 클래스다.
public class MemberDTO {
	//멤버변수의 이름은 테이블의 필드명과 같을수도, 다를수도 있다.
	//Register.jsp에서 <input type="text" name="mem_id">로 작성을 했다. 여기서 정해준 name과 이름을 맞춰야한다. 
	//-> input type의 name과 멤버변수의 이름은 반드시 동일해야한다.
	private String mem_id; //회원 아이디
	private String mem_passwd; //비번
	private String mem_name; //회원명
	private String mem_email; //이메일
	private String mem_phone; //전화번호
	private String mem_zipcaode; //우편번호
	private String mem_address; //주소
	private String mem_job; //직업
	
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
