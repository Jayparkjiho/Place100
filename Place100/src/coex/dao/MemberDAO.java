package coex.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import coex.util.MybatisConfig;
import coex.vo.Member;

public class MemberDAO {
	
	SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	SqlSession sqlSession;

	/**
	 * 회원가입 페이지에서 입력받은 member객체를 db에 저장하는 메소든
	 * @param member
	 */
	public void insertMember(Member member){
		System.out.println(member);
		try{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("MemberMapper.insertMember", member);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 회원가입시 입력받는 아이디가 db에 있는지 확인하는 메소드
	 * @param id
	 * @return
	 */
	public boolean idCheck(String id){
		System.out.println(id);
		Member member = null;
		boolean result = false;
		try {
			sqlSession = sqlSessionFactory.openSession();
			member = sqlSession.selectOne("MemberMapper.idCheck", id);
			if (member == null) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}
	
	/**
	 * 로그인 메소드 로그인할 아이디와 비밀번호를 입력받은 member를 가져와서 디비에서 확인후 그 맴버 객체를 보낸다.
	 * @param member
	 * @return
	 */
	public Member login(Member member){
		System.out.println(member);
		Member result = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			result = sqlSession.selectOne("MemberMapper.login", member);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}
	
	/*//회원목록을 가져오는 메소드
	public List<Member> print(){
		List<Member> mem_list = null;
		try {
			sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
			mem_list = sqlSession.selectList("MemberMapper.print");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return mem_list;
	}
	
	public void delete(int mem_num){
		try {
			sqlSession = MybatisConfig.getSqlSessionFactory().openSession();
			sqlSession.delete("MemberMapper.delete", mem_num);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}*/
	
}
