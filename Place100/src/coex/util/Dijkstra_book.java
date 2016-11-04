package coex.util;
import java.util.Map;
import java.util.Vector;

public class Dijkstra_book {

	private int sValue = 0;//현재 경로의 모든 비용 값
	private String[] sValues;//가장 작은 비용값의 목록을 담을 배열
	private String routeStr;//해당 목적지까지의 경로가 저장된 문자열

	int n = 0; // 정점의 갯수
	
	final static int m = 99999; //연결이 되어있지 않은 엣지를 위한 최대값
	int data[][];	//인접행렬
	
	boolean visit[]; // 방문한 vertex 목록
	int dis[]; // 거리
	int prev[]; // 이전Vertex
	
	int s,e;  // 시작 vertex와 끝 vertex
	int stack[]; // 한 경로의 경유경로를 모두 저장할 배열
	
	//TODO:
	Vector<Integer> stackV;
	
	//초기화
	public void init(int dataI[][])
	{
		data=dataI;
		n = data.length;
		
		
		dis = new int[n];
		visit = new boolean[n];
		prev = new int[n];
		stack = new int[n];
		stackV=new Vector<Integer>();
		sValues = new String[n];
		routeStr = "";
	}
	
	//최단거리
	public int theLeastDistance()
	{
		return dis[e-1];
	}
	
	//최단경로탐색을 실시하는 메쏘드
	public void start(int start,int end)
	{
		System.out.println("==========================================================");
		System.out.println("Dijkstra start");
		System.out.println("start From: "+start);
		System.out.println("end at: "+end);
		System.out.println("===========================================================");
		s=start;
		e=end;
		
		int k=0;
		int min=0;
		
		//TODO: 여기서 초기화
		routeStr = "";

		
		for (int i = 0; i < n; i++) { //init all values
			dis[i] = m;
			prev[i] = 0;
			visit[i] = false;
		}

		dis[s - 1] = 0; /* 시작점의 거리는 0 */
 
		for (int i = 0; i < n; i++) {
			min = m;
			for (int j = 0; j < n; j++) { /* 정점의 수만큼 반복 */
				if (visit[j] == false && dis[j] < min) { /* 확인하지 않고 거리가 짧은 정점을 찾음 */
					k = j;
					min = dis[j];
				}
			}
			visit[k] = true; /* 해당 정점 확인 체크 */    
			
			if (min == m)break; /* 연결된 곳이 없으면 종료 */
			
			 /****
			 * I -> J 보다 I -> K -> J의
			 * 거리가 더 작으면
			 * 갱신
			 ****/
			for (int j = 0; j < n; j++) {
				if (dis[k] + data[k][j] < dis[j]) {
					dis[j] = dis[k] + data[k][j]; /* 최단거리 저장*/
					prev[j] = k; /* J로 가기 위해서는 K를 거쳐야 함 */
				}
			}
		}
		nowLeastDistance();   //콘솔에서 최단거리 출력
		inverseFind();			// 콘솔에서 최단 경로 출력
	}
	
	/**** 최단 거리 출력 ****/
	public void nowLeastDistance()
	{
		System.out.printf("최단거리:  %5d       \n", dis[e - 1]);
		//현재 시작점 대비 모든 노드에 대한 이동값 부분
		//즉 모든 노드에서 1로 오도록 실행시키면서
		//아래 for문에서 나오는 값을 저장시키면 된다.
		int cnt = 0;
		for(int i :dis){
			System.out.print(i+", ");
			sValues[cnt++] = i+",";
		}
		System.out.println();
	}
	
	/**** 최단 경로를 저장 ****/
	public void inverseFind()
	{
		int tmp = 0;
		int top = -1;
		tmp = e - 1;
		while (true) {
			stack[++top] = tmp + 1;
			if (tmp == s - 1)break; /* 시작점에 이르렀으면 종료 */
			tmp = prev[tmp];
		}
		
		/* 역추적 결과 출력 */
		stackV.removeAllElements();
		for (int i = top; i > -1; i--) {
			System.out.printf("%d", stack[i]);
			routeStr+=stack[i];
			stackV.add(stack[i]);
			if (i != 0){
				System.out.printf("->");
				routeStr+="->";
			}
		}
		System.out.printf("\n");
	}
	
	public Vector<Integer> getStack()
	{
		return stackV;
	}
	
	
	public int getsValue() {
		return sValue;
	}
	
	public String[] getsValues() {
		return sValues;
	}

	public String getRouteStr() {
		return routeStr;
	}

	public void setRouteStr(String routeStr) {
		this.routeStr = routeStr;
	}
	
	//MAIN/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		int m=9999;
		
		//테스트 케이스 1
		int [][]data = new int[][] {
			      //A  B  C  D  E  F  G  H
			/*A*/ { 0, 4, 3, 1, m, m, m, m },
			/*B*/ { 4, 0, m, m, m, m, m, 7 },
			/*C*/ { 3, m, 0, 1, 1, m, m, m },
			/*D*/ { 1, m, 1, 0, 2, 3, m, 3 },
			/*E*/ { m, m, 1, 2, 0, 5, m, m },
			/*F*/ { m, m, m, 3, 5, 0, 4, m },
			/*G*/ { m, m, m, m, m, 4, 0, m },
			/*H*/ { m, 4, m, 3, m, m, m, 0 } 
			};//data
			//실제 데이터(이걸 DB에서 가져와야 한다)
		int [][]realData = new int[][]{
			      /*N1      N2      N3      N4      N5      N6      N7      N8      N9      N10     N11     N12     13      14      15      16      17      18      19      20      21      22      23      24      25      26      27      28      29      30      31      32      33      34      35      36      37      38      39      40      41      42      43      44      45      46      47      48      49      50      51      52      53      54      55      56      57      58      59      60      61      62      63      64 */
			/*N1*/ {0	,	30	,	30	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N2*/ {30	,	0	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N3*/ {30	,	m	,	0	,	10	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N4*/ {m	,	m	,	10	,	0	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N5*/ {m	,	m	,	20	,	m	,	0	,	m	,	m	,	20	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N6*/ {m	,	40	,	m	,	m	,	m	,	0	,	m	,	m	,	20	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N7*/ {m	,	m	,	m	,	40	,	m	,	m	,	0	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N8*/ {m	,	m	,	m	,	m	,	20	,	m	,	m	,	0	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N9*/ {m	,	m	,	m	,	m	,	m	,	20	,	m	,	m	,	0	,	m	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N10*/{m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	0	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			
			/*N11*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	m	,	0	,	20	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N12*/{m	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	20	,	0	,	m	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N13*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	0	,	m	,	m	,	40	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N14*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	m	,	0	,	20	,	m	,	60	,	m	,	m	,	m	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N15*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	0	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N16*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	0	,	m	,	m	,	40	,	m	,	m	,	50	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N17*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	60	,	m	,	m	,	0	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N18*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	0	,	240	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N19*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	240	,	0	,	m	,	m	,	m	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N20*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	0	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			
			/*N21*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	0	,	m	,	m	,	m	,	m	,	m	,	10	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N22*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	50	,	40	,	m	,	m	,	m	,	m	,	0	,	m	,	m	,	10	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N23*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	m	,	m	,	m	,	0	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N24*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	0	,	20	,	m	,	m	,	m	,	10	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N25*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	m	,	20	,	0	,	m	,	m	,	m	,	m	,	10	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N26*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	0	,	10	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N27*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	m	,	m	,	m	,	m	,	10	,	0	,	40	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N28*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	0	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N29*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	m	,	m	,	m	,	m	,	0	,	30	,	m	,	m	,	30	,	m	,	10	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N30*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	m	,	m	,	m	,	30	,	0	,	10	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},

			/*N31*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	10	,	0	,	m	,	m	,	m	,	m	,	30	,	m	,	30	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N32*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	40	,	m	,	m	,	m	,	0	,	m	,	m	,	m	,	m	,	m	,	10	,	m	,	m	,	10	,	50	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N33*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	m	,	m	,	m	,	0	,	30	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N34*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	0	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N35*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	m	,	m	,	m	,	m	,	20	,	0	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N36*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	m	,	m	,	m	,	20	,	0	,	30	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N37*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	0	,	40	,	20	,	m	,	m	,	m	,	m	,	30	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N38*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	10	,	m	,	m	,	m	,	m	,	40	,	0	,	m	,	10	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N39*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	m	,	0	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N40*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	m	,	0	,	10	,	m	,	m	,	50	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},

			/*N41*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	0	,	m	,	30	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N42*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	50	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	0	,	m	,	m	,	m	,	m	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N43*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	m	,	0	,	m	,	m	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N44*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	m	,	m	,	50	,	m	,	m	,	m	,	0	,	120	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N45*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	120	,	0	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N46*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	m	,	m	,	0	,	m	,	m	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N47*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	m	,	m	,	m	,	m	,	0	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N48*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	0	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N49*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	m	,	40	,	0	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N50*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	20	,	0	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},

			/*N51*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	0	,	m	,	m	,	30	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N52*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	0	,	10	,	40	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	m	,	m	,	m},
			/*N53*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	0	,	20	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N54*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	20	,	0	,	10	,	m	,	m	,	m	,	m	,	300	,	m	,	m	,	m	,	300},
			/*N55*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	10	,	0	,	30	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N56*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	0	,	30	,	m	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N57*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	0	,	30	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N58*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	30	,	0	,	m	,	m	,	m	,	m	,	m	,	m},
			/*N59*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	40	,	m	,	m	,	m	,	m	,	0	,	m	,	m	,	m	,	m	,	m},
			/*N60*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	300	,	m	,	m	,	m	,	m	,	m	,	0	,	m	,	m	,	m	,	m},

			/*N61*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	0	,	60	,	m	,	m},
			/*N62*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	0	,	60	,	m},
			/*N63*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	60	,	0	,	m},
			/*N64*/{m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	300	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	m	,	0},
		};
			
			String []namesOfNodes = new String[] {"U1"};
			
			
			
			
		/*for(int i= 0;i<data.length;i++){
			for(int j =0;j<data[i].length;j++){
				System.out.print(data[i][j]);
				System.out.print(", ");
			}
			System.out.println();*/
			
			
/*			for(int i= 0;i<realData.length;i++){
				for(int j =0;j<realData[i].length;j++){
					System.out.printf("%d \t",realData[i][j]);
					System.out.print(", ");
				}
				System.out.println();
			//이렇게 DB에 넣어준다
			//하지만 넣어줄 때 Key값(노드ID)를 넣어줘야 하므로
			//Node ID가 모두 적혀진 Enum 값이 필요하다.
			
		}*/
			
		Dijkstra_book k=new Dijkstra_book();
		//k.init(data);
		k.init(realData);
		
		k.start(1, 35);
		String[] pathArr = new String[64*64];
		int p = 0;
		while(p<=4096-1){
			for(int f = 1;f<=realData.length;f++){
				for(int j = 1; j<=realData.length;j++){
					System.out.println(f+" "+j);
					k.start(f, j);
					pathArr[p++]=k.getRouteStr();
					System.out.println("----------------------:"+p);
				}
			}
		}
		//4096
		/*for(int i=3000;i<=4096-1;i++){
			System.out.println(pathArr[i]);
		}*/
/*		for(int i=1;i<=64;i++){
			for(int j=1;j<=64;j++){
				System.out.println(i);
			}
		}
*/
		}

}