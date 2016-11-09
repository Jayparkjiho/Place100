package coex.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import coex.dao.ActionDAO;
import coex.dao.AnswerDAO;
import coex.dao.PathDAO;
import coex.dao.PlaceDAO;
import coex.dao.ScheduleDAO;
import coex.util.AlarmClock;
import coex.util.CoexMap;
import coex.util.ScheRecomm;
import coex.vo.Action;
import coex.vo.Alarm;
import coex.vo.Answer;
import coex.vo.Place;
import coex.vo.Schedule;

public class AnswerAction extends ActionSupport implements SessionAware {
	
	private Answer answer;
	private Map<String,Object> session;
	private Schedule schedule;
	private String[] eventList;
	private String[] startTimeList;	
	private String phone_num;
	private ArrayList<Place> placeList = new ArrayList<>();
	private Place place;
	private ArrayList<String> timeList = new ArrayList<>();
	private ArrayList<Action> actionList = new ArrayList<>();
	private String totalPath; 
	private String imageList;
	private int scheNo;
	
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
	 * 새부일정을 입력받은 answer객체에 최종으로 저장하고 answer를 저장하기 위해 dao호출 한뒤 
	 * 데이터베이스에 저장
	 * @return
	 */
	public String insertAnswer(){
		System.out.println("insertAnswer()메소드 실행");
		System.out.println(answer);
		AnswerDAO dao = new AnswerDAO();
		dao.insertAnswer(answer);
		System.out.println("입력끝");
		ScheRecomm scheRecom = new ScheRecomm();
		Schedule newSche = scheRecom.ansToSche(answer, "익명");
		scheRecom.scheduleRecomm(answer, newSche, newSche.getSchedule_start_time(), newSche.getSchedule_end_time());
		System.out.println(newSche.toString());
		ScheduleDAO sdao = new ScheduleDAO();
		sdao.insertSchedule(newSche);
		
		this.session.put("Schedule_no", sdao.getLastNo());
		return SUCCESS;
	}
	
	/**
	 * @author JayPark
	 * schedule에 대한 정보를 화면에 뿌려줄때 사용하는 xml tag
	 * @return
	 */
	public String schedule(){
		int schedule_no = (int)session.get("Schedule_no");
		ScheduleDAO dao = new ScheduleDAO();
		schedule = dao.findSchedule(schedule_no);
		System.out.println("찾아온 스케줄222 : "+schedule.toString());
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
	
	/**
	 * @author JayPark
	 * client단에서 스케줄에 대한 문자 서비스를 요청할 경우 메세지를 보내주는 xml tag
	 * @return
	 */
	public String sendSms(){
		System.out.println("sendSms()메소드 실행");
		
		int schedule_no = (int)session.get("Schedule_no");
		ScheduleDAO dao = new ScheduleDAO();
		schedule = dao.findSchedule(schedule_no);
		System.out.println(schedule);
		eventList = schedule.getSchedule_event_list().split(",");
		startTimeList = schedule.getSchedule_time_list().split(",");
		PlaceDAO placeDao = new PlaceDAO();
		for (int i = 0; i < eventList.length; i++) {
			place = placeDao.findPlace(Integer.parseInt(eventList[i]));
			placeList.add(place);
			timeList.add(startTimeList[i]);
		}
		System.out.println("schedule객체 : " + schedule);
		System.out.println("placeList리스트 : " + placeList);
		System.out.println(phone_num);
		
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
		a.setDay(schedule.getSchedule_date());
		a.setTimes(lastTime);
		a.setPlaces(lastPlace);
		
		System.out.println(a.toString());
		
		AlarmClock ac = new AlarmClock();
		ac.checkAlarm(a);
		return SUCCESS;
	}

	/**
	 * @author JayPark
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
		
		if (hour >= 12) {
			hour = hour - 12;
		}
		tt = "" + hour + ":" + min;
		System.out.println(tt);
		return tt;
	}
	public String paintMap(){
		int schedule_no = (int)session.get("Schedule_no");
		String str ="";
		String node ="";
		CoexMap map = new CoexMap();
		ScheduleDAO dao = new ScheduleDAO();
		Schedule sche = dao.findSchedule(schedule_no);
		
		String nodeList = sche.getSchedule_node_list();
		String [] nodelist = nodeList.split(",");
		System.out.println("끝"+nodelist[nodelist.length-1]);
		for (int i = 0; i < nodelist.length; i++) {
			PathDAO paDao = new PathDAO();
			if(i == nodelist.length-1){
				break;
			}else{
			str = paDao.findSchedule(Integer.parseInt(nodelist[i]), Integer.parseInt(nodelist[i+1]))+"->";
			System.out.println(str);
			node += map.nodeToXY(str);
			System.out.println(node);
			System.out.println("찍?");
						}
		}
		if (node.endsWith(",")) {
			System.out.println("끝");
		  node= node.substring(0, node.length()-1);
		}
		System.out.println(node);
		totalPath = node;
		
		imageList = sche.getSchedule_img_list();
		
		
		return SUCCESS;
	}
	
	
	public String showScheNo(){
		this.setScheNo((int)session.get("Schedule_no"));
		return SUCCESS;
	}
	
	
	

	public int getScheNo() {
		return scheNo;
	}

	public void setScheNo(int scheNo) {
		this.scheNo = scheNo;
	}

	public String getImageList() {
		return imageList;
	}

	public void setImageList(String imageList) {
		this.imageList = imageList;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getTotalPath() {
		return totalPath;
	}

	public void setTotalPath(String totalPath) {
		this.totalPath = totalPath;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
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

