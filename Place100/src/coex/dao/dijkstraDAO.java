package coex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import coex.util.MybatisConfig;


public class dijkstraDAO {

	// 모든 인접행렬 목록 가져오기
	public List<String> getAdjList() {
		List<String> edges = null;
		SqlSession ss = MybatisConfig.getSqlSessionFactory().openSession();
		try {
			edges = ss.selectList("DijkstraMapper.getAdjList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ss != null)
				ss.close();
		}
		return edges;
	}

	// 특정 노드로부터 시작하는 모든 최단거리 값 저장하기
	public void insertShortestValue(int nodeNo, String shortests) {
		HashMap<String, String> NodeAndValue = new HashMap<String, String>();
		NodeAndValue.put(""+nodeNo, shortests);
		SqlSession ss = MybatisConfig.getSqlSessionFactory().openSession();
		try {
			ss.insert("DijkstraMapper.insertShortestValue", NodeAndValue);
		} catch (Exception e) {
		} finally {
			if (ss != null) {
				ss.close();
			}
		}
	}
	
	// 특정한 경로와 경로 사이의 총 거리 비용 가져오기
	//TODO : MybatisMapper 설정
	public void findShortestValue(int fromNode, int toNode ){
		Map<String, Object> fromNto = null;
		SqlSession ss = MybatisConfig.getSqlSessionFactory().openSession();
		try{
			fromNto.put("from", ""+fromNode);
			fromNto.put("to", ""+toNode);
			ss.selectOne("DijkstraMapper.insertShortestValue", fromNto);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(ss!=null){
				ss.close();
			}
		}
		
	}
}
