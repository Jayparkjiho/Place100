package coex.vo;

public class Schedule {
	private int schedule_no;			//스케줄 식별번호
	private String mem_id = "익명";		//스케줄 보유자 이름
	private String schedule_date;		//스케줄 날짜
	private String schedule_start_time;	//스케줄 시작시간
	private String schedule_end_time;	//스케줄 종료시간
	private String schedule_event_list;	//스케줄 이벤트 목록
	private String schedule_time_list;	//스케줄 시간 목록
	private String schedule_node_list;	//스케줄 노드 목록
	private String schedule_time_table;	//스케줄 시간표{000001111222233334444555556666}
	private String schedule_img_list;   //스케줄 그림 목록
	private int prefno;					//스케줄 선호도 식별번호 //최종적으로 이 스케줄이 가지고 있는 모든 일정의 선호도의 값들을 합산시킨다.
	private float schedule_eval;		//스케줄 평가
	private int answerno;				//스케줄을 만들 때 사용자가 넣었던 답변의 번호
	
	public Schedule() {
	}

	public Schedule(int schedule_no, String mem_id, String schedule_date, String schedule_start_time,
			String schedule_end_time, String schedule_event_list, String schedule_time_list, String schedule_node_list,
			String schedule_time_table, String schedule_img_list, int prefno, float schedule_eval, int answerno) {
		super();
		this.schedule_no = schedule_no;
		this.mem_id = mem_id;
		this.schedule_date = schedule_date;
		this.schedule_start_time = schedule_start_time;
		this.schedule_end_time = schedule_end_time;
		this.schedule_event_list = schedule_event_list;
		this.schedule_time_list = schedule_time_list;
		this.schedule_node_list = schedule_node_list;
		this.schedule_time_table = schedule_time_table;
		this.schedule_img_list = schedule_img_list;
		this.prefno = prefno;
		this.schedule_eval = schedule_eval;
		this.answerno = answerno;
	}

	public int getSchedule_no() {
		return schedule_no;
	}

	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getSchedule_date() {
		return schedule_date;
	}

	public void setSchedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}

	public String getSchedule_start_time() {
		return schedule_start_time;
	}

	public void setSchedule_start_time(String schedule_start_time) {
		this.schedule_start_time = schedule_start_time;
	}

	public String getSchedule_end_time() {
		return schedule_end_time;
	}

	public void setSchedule_end_time(String schedule_end_time) {
		this.schedule_end_time = schedule_end_time;
	}

	public String getSchedule_event_list() {
		return schedule_event_list;
	}

	public void setSchedule_event_list(String schedule_event_list) {
		this.schedule_event_list = schedule_event_list;
	}

	public String getSchedule_time_list() {
		return schedule_time_list;
	}

	public void setSchedule_time_list(String schedule_time_list) {
		this.schedule_time_list = schedule_time_list;
	}

	public String getSchedule_node_list() {
		return schedule_node_list;
	}

	public void setSchedule_node_list(String schedule_node_list) {
		this.schedule_node_list = schedule_node_list;
	}

	public String getSchedule_time_table() {
		return schedule_time_table;
	}

	public void setSchedule_time_table(String schedule_time_table) {
		this.schedule_time_table = schedule_time_table;
	}

	public String getSchedule_img_list() {
		return schedule_img_list;
	}

	public void setSchedule_img_list(String schedule_img_list) {
		this.schedule_img_list = schedule_img_list;
	}

	public int getPrefno() {
		return prefno;
	}

	public void setPrefno(int prefno) {
		this.prefno = prefno;
	}

	public float getSchedule_eval() {
		return schedule_eval;
	}

	public void setSchedule_eval(float schedule_eval) {
		this.schedule_eval = schedule_eval;
	}

	public int getAnswerno() {
		return answerno;
	}

	public void setAnswerno(int answerno) {
		this.answerno = answerno;
	}

	@Override
	public String toString() {
		return "Schedule [schedule_no=" + schedule_no + ", mem_id=" + mem_id + ", schedule_date=" + schedule_date
				+ ", schedule_start_time=" + schedule_start_time + ", schedule_end_time=" + schedule_end_time
				+ ", schedule_event_list=" + schedule_event_list + ", schedule_time_list=" + schedule_time_list
				+ ", schedule_node_list=" + schedule_node_list + ", schedule_time_table=" + schedule_time_table
				+ ", schedule_img_list=" + schedule_img_list + ", prefno=" + prefno + ", schedule_eval=" + schedule_eval
				+ ", answerno=" + answerno + "]";
	}

	
}