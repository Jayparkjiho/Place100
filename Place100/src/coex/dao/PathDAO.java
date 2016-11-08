package coex.dao;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import coex.util.MybatisConfig;

public class PathDAO {
	SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	SqlSession sqlSession;
	
	public String findSchedule(int from,int to){
		String str ="";
		System.out.println("from"+from+"to"+to+ "찾기");
		Map<String , Integer> map = new HashMap<>();
		map.put("from", from);
		map.put("to", to);
		try{
			sqlSession = sqlSessionFactory.openSession();
			str = sqlSession.selectOne("PathMapper.select",map);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return str;
	}
}
