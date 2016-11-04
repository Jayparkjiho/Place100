package coex.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import coex.util.MybatisConfig;
import coex.vo.Preference;

public class PreferenceDAO {

	SqlSessionFactory sqlSessionFactory = MybatisConfig.getSqlSessionFactory();
	SqlSession sqlSession;

	// insertPreference
	public void insertPreference(Preference preference) {
		System.out.println(preference.toString());
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.insert("PreferenceMapper.insert", preference);
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

	// findPreference
	public Preference findPreference(int prefNo) {
		System.out.println(prefNo + "찾기");
		Preference pref = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			pref = sqlSession.selectOne("PreferenceMapper.findPref", prefNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return pref;
	}
	// getSeqNo

	public int getPrefNo() {
		int result = 0;
		try {
			sqlSession = sqlSessionFactory.openSession();
			result = sqlSession.selectOne("PreferenceMapper.getPrefNo");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}

	// updatePreference
	public void updatePreference(Preference pref) {
		try {
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.update("PreferenceMapper.update", pref);
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
