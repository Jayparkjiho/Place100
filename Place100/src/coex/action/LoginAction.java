package coex.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import coex.dao.MemberDAO;
import coex.vo.Member;

public class LoginAction extends ActionSupport implements SessionAware{

	Member member;
	Map<String , Object> session;
	boolean result;
	String message = "";
	MemberDAO dao = new MemberDAO();
	
	
	/**
	 * 회원가입 페이지에서 입력받은 member객체를 dao를 호출 저장하는 메소드
	 * @return success
	 * @throws Exception
	 */
	public String insertMember() throws Exception{
		System.out.println("insertMember()메소드 실행");
		System.out.println(member);
		dao.insertMember(member);
		return SUCCESS;
	}
	
	/**
	 * 입력받은 loginid와 password를 가지고 dao호출하고 
	 * @return member객체가 null일 경우 message에 오류 메세지를 보낸다  null이아니라면 로그인 됨
	 * @throws Exception
	 */
	public String login() throws Exception{
		System.out.println("login");
		member = dao.login(member);
		if (member == null) {
			message = "없는 아이디이거나 아이디와 비밀번호가 맞지 않습니다";
		}
		else {
			session.put("loginId", member);
		}
		return SUCCESS;
	}
	
	/**
	 * 회원가입시 db에 같은 아이디가 있는지 확인하는 메소드
	 * @return boolean result로 값이 있는지 없는 확인
	 * @throws Exception
	 */
	public String idCheck() throws Exception{
		System.out.println("idCheck()메소드 실행 ");
		System.out.println(member.getMem_id());
		result = dao.idCheck(member.getMem_id());
		message = "성공";
		return SUCCESS;
	}
	

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
