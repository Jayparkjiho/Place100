package coex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import coex.util.MybatisConfig;
import coex.vo.PlaceAndPref;

public class PlaceAndPrefDAO {
	SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	SqlSession sqlSession;

	public List<PlaceAndPref> getAllList() {
		System.out.println("PlaceAndPrefDAO getAllList");
		List<PlaceAndPref> placeAndPrefs = null;
		try{
			sqlSession = sqlSessionFactory.openSession();
			placeAndPrefs = sqlSession.selectList("PlaceAndPrefMapper.getAllList");
		}catch(Exception e){
			e.printStackTrace();
		}
		return placeAndPrefs;
	}

}
