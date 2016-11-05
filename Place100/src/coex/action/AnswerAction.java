package coex.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import coex.dao.ActionDAO;
import coex.dao.AnswerDAO;
import coex.dao.PlaceDAO;
import coex.dao.ScheduleDAO;
import coex.util.AlarmClock;
import coex.util.ScheRecomm;
import coex.vo.Action;
import coex.vo.Alarm;
import coex.vo.Answer;
import coex.vo.Place;
import coex.vo.Schedule;

public class AnswerAction extends ActionSupport implements SessionAware {
	
	Answer answer;
	Map<String,Object> session;
	Schedule schedule;
	String[] eventList;
	String[] startTimeList;	
	String phone_num;
	ArrayList<Place> placeList = new ArrayList<>();
	Place place;
	ArrayList<String> timeList = new ArrayList<>();
	ArrayList<Action> actionList = new ArrayList<>();
	
	/**
	 * 입력받은 방문 목적이 전시회일때 전시회에 관련된 action객체 list를 db에서
	 * 가져오는 메소드
	 * @return actionList
	 */
	public String getExhibitionList(){
		System.out.println("getExhibitionList()메소드 실행");
		System.out.println(answer);
		ActionDAO dao = new ActionDAO();
		actionList = (ArrayList<Action>) dao.getExhibitionList();
		System.out.println("가져온 action리스트 : " + actionList.toString());
		return SUCCESS;
	}

	/**
	 * answer객체의 목적 데이타를 answer객체에 저장하고, session에 저장
	 * @return 세션에 저장받은 answer객체
	 * 
	 */
	public String question2(){
		
		session.put("purpose_no", this.answer.getAnswer_purpose_no());
		System.out.println(session.get("purpose_no"));
		return SUCCESS;
	}
	
	/**
	 * answer객체의 개인정보 데이타를 answer객체에 저장하고, session에 저장
	 * @return 세션에 저장받은 answer객체
	 */
	public String question3(){
		
		session.put("answer", this.answer);
		
		return SUCCESS;
	}
	
	/**
	 * 새부일정을 입력받은 answer객체에 최종으로 저장하고 answer를 저장하기 위해 dao호출 한뒤 
	 * 데이터베이스에 저장
	 * @return
	 */
	public String insertAnswer(){
		//세션에 있는 데이터를 새로운 Answer객체로 이동
		Answer answer2 = new Answer();
		answer2 = (Answer)session.get("answer");
		//Answer에 데이터 합치기
		answer.setAnswer_purpose_no((int)session.get("purpose_no"));
		answer.setAnswer_date(answer2.getAnswer_date());
		answer.setAnswer_start_time(answer2.getAnswer_start_time());
		answer.setAnswer_end_time(answer2.getAnswer_end_time());
		answer.setAnswer_sex(answer2.getAnswer_sex());
		answer.setAnswer_age(answer2.getAnswer_age());
		answer.setAnswer_head_count(answer2.getAnswer_head_count());
		answer.setAnswer_traffic(answer2.getAnswer_traffic());
		answer.setAnswer_meal(answer2.getAnswer_meal());
		ScheRecomm scheRecom = new ScheRecomm();
		Schedule newSche = scheRecom.ansToSche(answer, "익명");
		scheRecom.scheduleRecomm(answer, newSche, newSche.getSchedule_start_time(), newSche.getSchedule_end_time());
		System.out.println(newSche.toString());
		ScheduleDAO sdao = new ScheduleDAO();
		sdao.insertSchedule(newSche);
		
		this.session.put("Schedule_no", sdao.getLastNo());
		return SUCCESS;
	}
	
	//스케줄 테스트
	public String schedule(){
		int schedule_no = (int)session.get("Schedule_no");
		ScheduleDAO dao = new ScheduleDAO();
		schedule = dao.findSchedule(schedule_no);
		System.out.println("찾아온 스케줄 : "+schedule.toString());
		eventList = schedule.getSchedule_event_list().split(",");
		startTimeList = schedule.getSchedule_time_list().split(",");
		PlaceDAO placeDao = new PlaceDAO();
		for (int i = 0; i < eventList.length; i++) {
			place = placeDao.findPlace(Integer.parseInt(eventList[i]));
			placeList.add(place);
			timeList.add(startTimeList[i]);
		}
		System.out.println(placeList.toString());
		return SUCCESS;
	}
	
	//user단에서 스케줄에 대한 문자서비스를  받을지 안받을지에 대한 확인 메소드
	public String sendSms(){
		Calendar c = new GregorianCalendar();
		Alarm a = new Alarm();
		
		String[] timeValues2 = null;
		
		ArrayList<String> lastPlace = new ArrayList<>();
		ArrayList<String> lastTime = new ArrayList<>();
		
		String times = schedule.getSchedule_time_list();
		String[] timeValues1 = times.split(",");
		String places = null;
		
		for (Place place : placeList) {
			places += ""+place.getPlace_name()+",";
		}
		
		String[] placeValues1 = places.split(",");
		
		//여러 시간의 시작시간만 짜른다.
		for (int i = 0; i < timeValues1.length; i++) {
			timeValues2 = timeValues1[i].split("~");
			for (int j = 0; j < timeValues2.length; j=j+2) {
				lastTime.add(minusTime(timeValues2[j]));
			}
		}
		
		//String인 장소를 각각 풀어서 arraylist에 저장한다,
		for (int i = 0; i < placeValues1.length; i++) {
			lastPlace.add(placeValues1[i]);
			System.out.println(placeValues1[i]);
		}
		
		System.out.println(lastPlace.toString());
		
		a.setNumber(phone_num);
		a.setDay("16/11/02");
		a.setTimes(lastTime);
		a.setPlaces(lastPlace);
		
		AlarmClock ac = new AlarmClock();
		ac.checkAlarm(a);
		return SUCCESS;
	}

	/**
	 * 문자 메세지를 보내줄 시간의 10분을 빼기 위한 메소드
	 * @param time
	 * @return 전송시간
	 */
	public static String minusTime(String time){
		String tt = time;
		String[] ab = tt.split(":");
		int hour = Integer.parseInt(ab[0]);
		int min = Integer.parseInt(ab[1]);
		System.out.println(ab[0] +" " + ab[1]);
		
		if (min < 10) {
			hour = hour - 1;
			min = min + 60 - 10;
		}
		else{
			min = min - 10;
			if (min == 0 ) {
				min = 00;
			}
		}
		
		if (hour > 12) {
			hour = hour - 12;
		}
		tt = "" + hour + ":" + min;
		System.out.println(tt);
		return tt;
	}
	
	
	
	
	

	public ArrayList<Place> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(ArrayList<Place> placeList) {
		this.placeList = placeList;
	}

	public ArrayList<String> getTimeList() {
		return timeList;
	}

	public void setTimeList(ArrayList<String> timeList) {
		this.timeList = timeList;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String[] getEventList() {
		return eventList;
	}

	public void setEventList(String[] eventList) {
		this.eventList = eventList;
	}

	public String[] getStartTimeList() {
		return startTimeList;
	}

	public void setStartTimeList(String[] startTimeList) {
		this.startTimeList = startTimeList;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	public ArrayList<Action> getActionList() {
		return actionList;
	}

	public void setActionList(ArrayList<Action> actionList) {
		this.actionList = actionList;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	
	
}

