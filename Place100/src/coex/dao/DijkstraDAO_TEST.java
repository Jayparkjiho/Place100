package coex.dao;

import java.util.ArrayList;
import java.util.List;

import coex.util.Dijkstra_book;



public class DijkstraDAO_TEST {
	public static void main(String[] args) {
		dijkstraDAO dao = new dijkstraDAO();
		List<String> arrList = new ArrayList<>();
		arrList = dao.getAdjList();
		int adjMatrix[][] = new int[arrList.size()][arrList.size()];
		for (int i = 0; i <= adjMatrix.length-1; i++) {
			String str = arrList.get(i);
			String splitedStr[] = str.split(",");
			for (int j = 0; j < adjMatrix.length; j++) {
				adjMatrix[i][j] = Integer.parseInt(splitedStr[j]);
			}
		}

		
		for (int i = 0; i < 64; i++) {
			for (int j = 0; j < 64; j++) {
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
		
		Dijkstra_book dijk = new Dijkstra_book();
		
		dijk.init(adjMatrix);
		dijk.start(63, 8);
		//현재작업 이후 1의 최단경로 테이블 출력
		for(String s : dijk.getsValues()){
			System.out.print(s);
		}
		
		System.out.println(adjMatrix.length);
		//for문으로 start를 1부터 64 까지 부르면서
		//문자열 배열에 getsValues()의 결과를 넣어주면
		//64개의 문자열이 들어간 값이 나온다
		
		String[] shortests = new String[64];
		for(int i = 1;i<=64;i++){
			dijk.start(i, 64);
			shortests[i-1]="";
			for(String s : dijk.getsValues()){
				shortests[i-1]+=s;
			}
		}
		for(int i = 0; i<=63;i++){
			System.out.println(shortests[i]);
		}
	}
}
