package coex.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import coex.util.MybatisConfig;
import coex.vo.Answer;

public class AnswerDAO {
	SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	SqlSession sqlSession;
	
	
	/**
	 * 최종으로 입력받은 answer객체를 db에 저장하는 메소드
	 * @param answer
	 */
	public void insertAnswer(Answer answer){
		System.out.println("insertAnswer()DAO method 실행");
		try{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("AnswerMapper.insertAnswer", answer);
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
	 * db에서 마지막으로 입력받은 answer객체의 기본키(answer_no)을 찾아서 보내는 메소드
	 * @return answer_no
	 */
	public int getSeqNo(){
		int result=0;
		try{
			sqlSession = sqlSessionFactory.openSession();
			result=sqlSession.selectOne("AnswerMapper.getSeqNo");
			sqlSession.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}
	
}
