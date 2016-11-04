package coex.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import coex.util.MybatisConfig;
import coex.vo.Action;

public class ActionDAO {

	SqlSession sqlSession;
	SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	
	//insert
	public void insertAction(Action action){
		try{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("ActionMapper.insert",action);
			sqlSession.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
	}
	
	public List<Action> getExhibitionList(){
		List<Action> actionList = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			actionList = sqlSession.selectList("ActionMapper.getActionList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return actionList;
	}
	
	//select param:actionNo
	public Action findAction(int actionNo){
		Action result = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			result = sqlSession.selectOne("ActionMapper.find", actionNo);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
		return result;
	}
	
	
	//selectList param : placeNo
	public List<Action> getPlaceActionList(int placeNo){
		System.out.println("DB에서 장소번호["+placeNo+"] 의 목록 찾는중");
		List<Action> actions = null;
		//결과 레코드 중 읽을 위치와 개수
		try{
			sqlSession = sqlSessionFactory.openSession();
			actions = sqlSession.selectList("ActionMapper.placeActionList", placeNo);
			System.out.println("총"+ actions.size() + "의 Action 확인");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSession != null){
				sqlSession.close();
			}
		}
		return actions;
	}
	
	//delete param:actionNo
	public Action deleteAction(int actionNo){
		Action result = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			result = sqlSession.selectOne("ActionMapper.delete", actionNo);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
		return result;
	}
	
	//update param:Action
		public void updateAction(Action action){
			int result = 0;
			try{
				sqlSession = sqlSessionFactory.openSession();
				result = sqlSession.update("ActionMapper.update", action);
				System.out.println(result+"개의 값 업데이트 완료");
				sqlSession.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(sqlSession !=null){
					sqlSession.close();
				}
			}
		}
}
