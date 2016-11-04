package coex.vo;

import java.util.Date;

/**
 * 
 * @author 김진홍
 * 사용자가 질문을 모두 답변했을 시 해당 내용을 기반으로 생성되는 
 * 답변(Answer)객체
 */
public class Answer {
	
	private int answer_no;                       // 질문 번호 (시퀀스)
	private int answer_purpose_no;               // 방문목적 번호 (0: 전시회, 1: 식사, 2: 쇼핑, 3: 문화활동, 4: 데이트, 5: 그외 )
	private Date answer_date;                    // 방문 날짜
	private String answer_start_time;            // 방문 시작시간
	private String answer_end_time;              // 방문 종료시간
	private int answer_sex;                      // 방문자 성별 (0: 남, 1: 여, 2: 남여)
	private int answer_age;                      // 방문자 연령대 (10: 10대, 20: 20데, 30: 30대, 40: 40대, 50: 50데 이상)
	private int answer_head_count;               // 방문 인원 (1: 1명, 2: 2명, 3: 3명, 5: 5명, 10: 10명)
	private int answer_traffic;                  // 교통편 (0: 2호선, 1: 9호선, 3: 버스, 4: 자가용)
	private int answer_meal;                     // 식사 (0: 안 먹어, 1: 점심, 2: 저녁, 3: 점심저녁)
	private int answer_scount;                   // 개인 스케줄 카운트 (0:없어 1:한개 2:2개 3:3개)
	private int answer_sone_eventno; 			 //1번 스케줄(여기는 박람회나 영화 선택시에만 삽입 가능)의 이벤트번호(장소:10000대나 액션:20000대)             
	private String answer_sone_start;			 //1번 스케줄의 시작시간
	private String answer_sone_end;				 //1번 스케줄의 종료시간
	private int answer_sone_node;				 //1번 스케줄의 위치 노드
	private int answer_stwo_eventno;			 //2번 스케줄의 이벤트 번호
	private String answer_stwo_start;			 //2번 스케줄 시작시간
	private String answer_stwo_end;				 //2번 스케줄 종료시간
	private int answer_stwo_node;			  	 //2번 스케줄 노드
	private int answer_sthree_eventno;			 //3번 스케줄의 이벤트번호
	private String answer_sthree_start;			 //3번 스케줄의 시작시간
	private String answer_sthree_end;			 //3번 스케줄의 종료시간
	private int answer_sthree_node;			 	 //3번 스케줄의 노드

	public Answer( ){
	}

	//생성자 (field와)
	public Answer(int answer_no, int answer_purpose_no, Date answer_date, String answer_start_time,
			String answer_end_time, int answer_sex, int answer_age, int answer_head_count, int answer_traffic,
			int answer_meal, int answer_scount, int answer_sone_eventno, String answer_sone_start,
			String answer_sone_end, int answer_sone_node, int answer_stwo_eventno, String answer_stwo_start,
			String answer_stwo_end, int answer_stwo_node, int answer_sthree_eventno, String answer_sthree_start,
			String answer_sthree_end, int answer_sthree_node) {
		this.answer_no = answer_no;
		this.answer_purpose_no = answer_purpose_no;
		this.answer_date = answer_date;
		this.answer_start_time = answer_start_time;
		this.answer_end_time = answer_end_time;
		this.answer_sex = answer_sex;
		this.answer_age = answer_age;
		this.answer_head_count = answer_head_count;
		this.answer_traffic = answer_traffic;
		this.answer_meal = answer_meal;
		this.answer_scount = answer_scount;
		this.answer_sone_eventno = answer_sone_eventno;
		this.answer_sone_start = answer_sone_start;
		this.answer_sone_end = answer_sone_end;
		this.answer_sone_node = answer_sone_node;
		this.answer_stwo_eventno = answer_stwo_eventno;
		this.answer_stwo_start = answer_stwo_start;
		this.answer_stwo_end = answer_stwo_end;
		this.answer_stwo_node = answer_stwo_node;
		this.answer_sthree_eventno = answer_sthree_eventno;
		this.answer_sthree_start = answer_sthree_start;
		this.answer_sthree_end = answer_sthree_end;
		this.answer_sthree_node = answer_sthree_node;
	}

	public int getAnswer_no() {
		return answer_no;
	}

	public void setAnswer_no(int answer_no) {
		this.answer_no = answer_no;
	}

	public int getAnswer_purpose_no() {
		return answer_purpose_no;
	}

	public void setAnswer_purpose_no(int answer_purpose_no) {
		this.answer_purpose_no = answer_purpose_no;
	}

	public Date getAnswer_date() {
		return answer_date;
	}

	public void setAnswer_date(Date answer_date) {
		this.answer_date = answer_date;
	}

	public String getAnswer_start_time() {
		return answer_start_time;
	}

	public void setAnswer_start_time(String answer_start_time) {
		this.answer_start_time = answer_start_time;
	}

	public String getAnswer_end_time() {
		return answer_end_time;
	}

	public void setAnswer_end_time(String answer_end_time) {
		this.answer_end_time = answer_end_time;
	}

	public int getAnswer_sex() {
		return answer_sex;
	}

	public void setAnswer_sex(int answer_sex) {
		this.answer_sex = answer_sex;
	}

	public int getAnswer_age() {
		return answer_age;
	}

	public void setAnswer_age(int answer_age) {
		this.answer_age = answer_age;
	}

	public int getAnswer_head_count() {
		return answer_head_count;
	}

	public void setAnswer_head_count(int answer_head_count) {
		this.answer_head_count = answer_head_count;
	}

	public int getAnswer_traffic() {
		return answer_traffic;
	}

	public void setAnswer_traffic(int answer_traffic) {
		this.answer_traffic = answer_traffic;
	}

	public int getAnswer_meal() {
		return answer_meal;
	}

	public void setAnswer_meal(int answer_meal) {
		this.answer_meal = answer_meal;
	}

	public int getAnswer_scount() {
		return answer_scount;
	}

	public void setAnswer_scount(int answer_scount) {
		this.answer_scount = answer_scount;
	}

	public int getAnswer_sone_eventno() {
		return answer_sone_eventno;
	}

	public void setAnswer_sone_eventno(int answer_sone_eventno) {
		this.answer_sone_eventno = answer_sone_eventno;
	}

	public String getAnswer_sone_start() {
		return answer_sone_start;
	}

	public void setAnswer_sone_start(String answer_sone_start) {
		this.answer_sone_start = answer_sone_start;
	}

	public String getAnswer_sone_end() {
		return answer_sone_end;
	}

	public void setAnswer_sone_end(String answer_sone_end) {
		this.answer_sone_end = answer_sone_end;
	}

	public int getAnswer_sone_node() {
		return answer_sone_node;
	}

	public void setAnswer_sone_node(int answer_sone_node) {
		this.answer_sone_node = answer_sone_node;
	}

	public int getAnswer_stwo_eventno() {
		return answer_stwo_eventno;
	}

	public void setAnswer_stwo_eventno(int answer_stwo_eventno) {
		this.answer_stwo_eventno = answer_stwo_eventno;
	}

	public String getAnswer_stwo_start() {
		return answer_stwo_start;
	}

	public void setAnswer_stwo_start(String answer_stwo_start) {
		this.answer_stwo_start = answer_stwo_start;
	}

	public String getAnswer_stwo_end() {
		return answer_stwo_end;
	}

	public void setAnswer_stwo_end(String answer_stwo_end) {
		this.answer_stwo_end = answer_stwo_end;
	}

	public int getAnswer_stwo_node() {
		return answer_stwo_node;
	}

	public void setAnswer_stwo_node(int answer_stwo_node) {
		this.answer_stwo_node = answer_stwo_node;
	}

	public int getAnswer_sthree_eventno() {
		return answer_sthree_eventno;
	}

	public void setAnswer_sthree_eventno(int answer_sthree_eventno) {
		this.answer_sthree_eventno = answer_sthree_eventno;
	}

	public String getAnswer_sthree_start() {
		return answer_sthree_start;
	}

	public void setAnswer_sthree_start(String answer_sthree_start) {
		this.answer_sthree_start = answer_sthree_start;
	}

	public String getAnswer_sthree_end() {
		return answer_sthree_end;
	}

	public void setAnswer_sthree_end(String answer_sthree_end) {
		this.answer_sthree_end = answer_sthree_end;
	}

	public int getAnswer_sthree_node() {
		return answer_sthree_node;
	}

	public void setAnswer_sthree_node(int answer_sthree_node) {
		this.answer_sthree_node = answer_sthree_node;
	}

	@Override
	public String toString() {
		return "Answer [answer_no=" + answer_no + ", answer_purpose_no=" + answer_purpose_no + ", answer_date="
				+ answer_date + ", answer_start_time=" + answer_start_time + ", answer_end_time=" + answer_end_time
				+ ", answer_sex=" + answer_sex + ", answer_age=" + answer_age + ", answer_head_count="
				+ answer_head_count + ", answer_traffic=" + answer_traffic + ", answer_meal=" + answer_meal
				+ ", answer_scount=" + answer_scount + ", answer_sone_eventno=" + answer_sone_eventno
				+ ", answer_sone_start=" + answer_sone_start + ", answer_sone_end=" + answer_sone_end
				+ ", answer_sone_node=" + answer_sone_node + ", answer_stwo_eventno=" + answer_stwo_eventno
				+ ", answer_stwo_start=" + answer_stwo_start + ", answer_stwo_end=" + answer_stwo_end
				+ ", answer_stwo_node=" + answer_stwo_node + ", answer_sthree_eventno=" + answer_sthree_eventno
				+ ", answer_sthree_start=" + answer_sthree_start + ", answer_sthree_end=" + answer_sthree_end
				+ ", answer_sthree_node=" + answer_sthree_node + "]";
	}
	
}//end of class