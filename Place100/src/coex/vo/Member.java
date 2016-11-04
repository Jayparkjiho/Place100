package coex.vo;

/**
 * 
 * @author 박지호
 * 사용자 객체(회원가입 및 로그인 등등에 사용)
 */
public class Member {

	private int mem_num;
	private String mem_id;
	private String mem_pw;
	private String mem_sex;
	private int mem_age;
	private String mem_phone;
	private String mem_email;
	
	public Member(){}

	public Member(int mem_num, String mem_id, String mem_pw, String mem_sex, int mem_age, String mem_phone,
			String mem_email) {
		this.mem_num = mem_num;
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_sex = mem_sex;
		this.mem_age = mem_age;
		this.mem_phone = mem_phone;
		this.mem_email = mem_email;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public String getMem_sex() {
		return mem_sex;
	}

	public void setMem_sex(String mem_sex) {
		this.mem_sex = mem_sex;
	}

	public int getMem_age() {
		return mem_age;
	}

	public void setMem_age(int mem_age) {
		this.mem_age = mem_age;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	@Override
	public String toString() {
		return "Member [mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_pw=" + mem_pw + ", mem_sex=" + mem_sex
				+ ", mem_age=" + mem_age + ", mem_phone=" + mem_phone + ", mem_email=" + mem_email + "]";
	}

	
	

}
