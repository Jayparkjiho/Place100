package coex.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import coex.util.MybatisConfig;
import coex.vo.Preference;
import coex.vo.Schedule;

public class ScheduleDAO {
	SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	SqlSession sqlSession;

	
	//insertSchedule
	public void insertSchedule(Schedule schedule){
		System.out.println(schedule.toString());
		try{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("ScheduleMapper.insert", schedule);
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
	
	//findSchedule
	public Schedule findSchedule(int scheduleNo){
		System.out.println(scheduleNo+ "찾기");
		Schedule sche = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			sche = sqlSession.selectOne("ScheduleMapper.findSchedule",scheduleNo);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return sche;
	}
	
	public int getSeqNo(){
		int result=0;
		try{
			sqlSession = sqlSessionFactory.openSession();
			result = sqlSession.selectOne("ScheduleMapper.getSeqNo");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return result;
	}
	
	public int getLastNo(){
		int result=0;
		try{
			sqlSession = sqlSessionFactory.openSession();
			result = sqlSession.selectOne("ScheduleMapper.getLastNo");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return result;
	}
	
	//updateSchedule
	public void updateSchedule(Schedule schedule){
		try{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.update("ScheduleMapper.updateSchedule", schedule);
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
}
