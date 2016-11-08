package coex.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.jdt.internal.compiler.ast.ThrowStatement;

import coex.dao.ActionDAO;
import coex.dao.PlaceDAO;
import coex.dao.PreferenceDAO;
import coex.dao.ScheduleDAO;
import coex.vo.Action;
import coex.vo.Answer;
import coex.vo.Place;
import coex.vo.PlaceAndPref;
import coex.vo.Preference;
import coex.vo.Schedule;

/**
 * 자신만의 스케줄을 생성할 때 사용되는 클래스
 * @author 김진홍
 */
public class CreateSchedule {
	private ScheduleDAO sDAO = new ScheduleDAO();//스케줄 DAO
	private ActionDAO aDAO = new ActionDAO(); //액션 DAO
	private PlaceDAO pDAO = new PlaceDAO(); //장소 DAO
	private Schedule schedule; //계속 사용되는 스케줄
	private Action action; //액션을 담아둘 공간
	private Place place; //장소를 담아둘 공간


	/**
	 * 사용자로부터 스케줄과 이벤트번호(10000대 : place , 20000대 : action)를 입력받아
	 * 스케줄을 현재 존재하는 이벤트 바로 뒤에 붙여주는 메쏘드
	 * @param sche 스케줄
	 * @param eventNo 추가할 이벤트 번호
	 * @param amount 추가할 시간의 양(분단위)
	 * 에러코드 : -1 : 잘못된 이벤트 번호 
	 *           -2 : 잘못된 이벤트 시간(시작시간이 맞지 않거나 종료 시간이 맞지 않거나 등)
	 */
	public int addEventOnLast(Schedule sche, int eventNo, int runTime){
		Action newAction=null;
		Place newPlace=null;
		boolean isAction = false;//새로 넣을 이벤트가 액션인지 플레이스인지 확인하는 Flag
		String eventList;
		String timeList;
		String nodeList;
		String imgList;
		Preference pref;
		String startTime;
		String endTime;
		String newTime;//새로 입력될 시간
		//추가한 시간(분)이 마지막 시간 + 종료시간 보다 작으면 에러 출력후 종료 (자바스크립트에서 해야지 ㅡㅡ)
		
		
		//Step1 : 입력받은 이벤트 넘버 validation check
		if(eventNo>=20000){//만약 이벤트 넘버가 2만대라면(Action이므로 관련 처리)
			isAction = true;
			newAction = aDAO.findAction(eventNo);//액션을 찾아오고
			newPlace = pDAO.findPlace(action.getPlace_no());//해당 액션의 플레이스를 찾아온다
		}else if(eventNo<20000&&eventNo>=10000){//만약 이벤트 넘버가 1만대라면
			isAction = false;
			newPlace = pDAO.findPlace(eventNo);//플레이스만 받아온다.
		}else{
		 //그 외에는 잘못된 이벤트 넘버라고 출력하고 종료한다
			System.out.println("[ERROR]:"+eventNo+"IS WRONG EVENT NO");
			return -1;
		}
		//TODO:입력받은 이벤트 번호가 중복이라면 
		//-1반환
		
		//Step2 스케줄의 각 리스트에 이벤트를 추가해준다.

		//시간추출용
		
		//case1 : 하나도 없을경우
		if(sche.getSchedule_event_list()==null){
			//이벤트 업데이트
			eventList=""+newPlace.getPlace_no();
			//시간 업데이트
			startTime = sche.getSchedule_start_time();
			endTime = this.addTime(startTime, ""+runTime);
			timeList=startTime +"~"+ endTime;
			//노드 업데이트
			nodeList=""+newPlace.getPlace_nodeno();
			//이미지 업데이트
			imgList=""+newPlace.getPlace_photo_name();
		}else{//case2 : 한개라도 있었을 경우
			eventList=sche.getSchedule_event_list()+","+newPlace.getPlace_no();
			String[] times = sche.getSchedule_time_list().split(",|-|~");
			startTime = times[times.length-1];
			endTime = this.addTime(startTime, ""+runTime);
			newTime = startTime+"~"+endTime;
			timeList = sche.getSchedule_time_list()+","+newTime;
			//노드 업데이트
			nodeList=sche.getSchedule_node_list()+","+newPlace.getPlace_nodeno();
			//이미지 업데이트
			imgList=sche.getSchedule_img_list()+","+newPlace.getPlace_photo_name();
		}		
		
		//시간체크 후
		//만약 스케줄 종료시간보다 추가된 시간이 길다면
		if(this.checkTimeDiff(sche.getSchedule_end_time(),endTime)==-1){
			System.out.println("[ERROR]:"+"Time Overadded");
			return -2;
		}
		//스케줄 이벤트 목록, 시간 목록, 노드 목록, 그림 목록 등을 업데이트 하고
		sche.setSchedule_event_list(eventList);
		sche.setSchedule_time_list(timeList);
		sche.setSchedule_node_list(nodeList);
		sche.setSchedule_img_list(imgList);
		
		//모든 상황이 잘 종료되었으면 정상종료값 반환
		return 0;
	}
	
	/*
	 *TODO:
	 * 스케줄에서 마지막이 위치한 스케줄을 제거한다
	 * WARNING:알고리즘 상의 문제로 마지막에 해당하는 이벤트만 제거한다.
	 */
	public void removeLastEvent(Schedule sche){
		String[] eventArr;
		String[] timeArr;
		String[] nodeArr;
		String[] imgArr;
		
		String eventList;
		String timeList;
		String nodeList;
		String imgList;
		
		if(sche.getSchedule_event_list()==null){
			System.out.println("삭제할 이벤트가 없습니다.");
			return;
		}
		if(sche.getSchedule_event_list().length()==0){
			System.out.println("삭제할 이벤트가 없습니다.");
			return;
		}
		
		//한개면 그냥 삭제
		if(sche.getSchedule_event_list().length()<6){
			System.out.println("이벤트 한개 삭제");
			sche.setSchedule_event_list("");
			sche.setSchedule_time_list("");
			sche.setSchedule_node_list("");
			sche.setSchedule_img_list("");
			return;
		}else{//2개 이상이면 뒤에꺼 삭제

			//나눠서 배열에 담고
			eventArr=sche.getSchedule_event_list().split(",");
			timeArr=sche.getSchedule_time_list().split(",");
			nodeArr=sche.getSchedule_node_list().split(",");
			imgArr=sche.getSchedule_img_list().split(",");
			//첫번째 값은 넣은 뒤
			eventList=eventArr[0];
			timeList=timeArr[0];
			nodeList=nodeArr[0];
			imgList=imgArr[0];
			
			//2번째 값부터 끝 -1까지 붙여준다
			for(int i = 1; i<eventArr.length-1;i++){
				eventList+=","+eventArr[i];
				timeList+=","+timeArr[i];
				nodeList+=","+nodeArr[i];
				imgList+=","+imgArr[i];
			}
		}

		System.out.println("삭제된 이벤트명:"+eventList);
		System.out.println("삭제된 이벤트시간:"+timeList);
		System.out.println("삭제된 이벤트위치(노드):"+nodeList);
		System.out.println("삭제된 이벤트 사진이름:"+imgList);
		sche.setSchedule_event_list(eventList);
		sche.setSchedule_time_list(timeList);
		sche.setSchedule_node_list(nodeList);
		sche.setSchedule_img_list(imgList);
	}
	
	/**
	 * 스케줄을 입력받아 해당 스케줄을 DB에 업데이트 한다
	 * 스케줄 식별번호를 추출해 업데이트 한다.
	 * @param sche
	 */
	public void updateSchedule(Schedule sche){
		ScheduleDAO sdao = new ScheduleDAO();
		sdao.updateSchedule(sche);
	}

	/**
	 * Schedule의 모든 place의 Preference 값에 Answer을 기반으로 값을 추가해준다
	 * @param pref
	 * @param prefList
	 */
	public void addPrefNo(Schedule sche,Answer answer){
		String[] allPlace=sche.getSchedule_event_list().split(",");
		int placeNo;
		Action action;
		ActionDAO aDAO = new ActionDAO();
		Place place;
		PlaceDAO pDAO = new PlaceDAO();
		PreferenceDAO prefDAO = new PreferenceDAO();
		Preference pref;
		
		for(String s:allPlace){
			placeNo = Integer.parseInt(s);
			if(placeNo<20000){
				place = pDAO.findPlace(placeNo);
				pref = prefDAO.findPreference(place.getPlace_no());
			}else{
				action= aDAO.findAction(placeNo);
				place = pDAO.findPlace(action.getPlace_no());
				pref = prefDAO.findPreference(place.getPlace_no());
			}
			this.answerToScore(answer, pref);
			this.answerToScore(answer, pref);
		}
	}
	
	//시간과 분을 입력받아 더해주는 알고리즘
	public String addTime(String time2,String minute) {
		minute = "00:" + minute;

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date date1 = null;
		Date date2 = null;
		try {
			date1 = timeFormat.parse(minute);
			date2 = timeFormat.parse(time2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long sum = date1.getTime() + date2.getTime();

		String date3 = timeFormat.format(new Date(sum));
		return date3;
	}
	
	//두 시간을 입력받아 차이를 말하는 함수 a가 b보다 작으면 -1 반환
	public int checkTimeDiff(String firstTime, String secondTime) {
		int result = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		try {
			Date firstDate = formatter.parse(firstTime);
			Date secondDate = formatter.parse(secondTime);

			int compare = firstDate.compareTo(secondDate);
			if (compare > 0) {
				// System.out.println("fistDate > secondDate");
				return 1;
			} else if (compare < 0) {
				// System.out.println("fistDate < secondDate");
				return -1;
			} else {
				// System.out.println("day1 == day2");
				return 0;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -100;
	}

	/**
	 * Answer을 기반으로 해당 Answer의 값에 맞는 값에만 +1점씩 더해주는 함수 
	 */
	public void answerToScore(Answer answer, Preference pref) {
		switch (answer.getAnswer_purpose_no()) {// answer의 목적별 분류
		case 0: // answer의 목적이 전시회일때
			 pref.incPref_conference();
			break;
		case 1: // answer의 목적이 식사일때
			pref.incPref_meal();
			break;
		case 2: // answer의 목적이 쇼핑일때
			pref.incPref_shopping();
			break;
		case 3: // answer의 목적이 문화활동일때
			pref.incPref_culture();
			break;
		case 4: // answer의 목적이 데이트일때
			pref.incPref_date();
			break;
		case 5: // answer의 목적이 기타일때
			pref.incPref_etc();
			break;
		}
		switch (answer.getAnswer_age()) { // 나이대에 따른 분류
		case 10://answer의 답이 10대일때
			pref.incPref_ten();
			break;
		case 20:
			pref.incPref_20();
			break;
		case 30:
			pref.incPref_30();
			break;
		case 40:
			pref.incPref_40();
			break;
		case 50:
			//50대 사라짐
			break;
		}
		switch (answer.getAnswer_sex()) { // answer의 성별에 따른 구분
		case 0:
			pref.incPref_male();
			break;
		case 1:
			pref.incPref_female();
			break;
		case 2:
			pref.incPref_male();
			pref.incPref_female();
		}
		switch (answer.getAnswer_head_count()) { // answer의 헤드카운드별로 계산

		case 1:
			pref.incPref_single();
			break;
		case 2:
			pref.incPref_two();
			break;
		case 3:
			pref.incPref_three();
			break;
		case 5:
			pref.incPref_five();
			break;
		case 10:
			pref.incPref_ten();
			break;
		}
		//업데이트
		new PreferenceDAO().updatePreference(pref);
	}
	
	//스케줄을 받아와 해당 스케줄의 이벤트 목록에 있는 모든 값에 해당 시작시간에 해당하는 선호도에 +1점씩 해주는 메쏘드
	public void incTimePref(Schedule sche){
		String[] eventList = sche.getSchedule_event_list().split(",");
		String[] timeList = sche.getSchedule_time_list().split(",|~");
		int timeIdx=0;
		int placeNo;
		Place place;
		PreferenceDAO prefDAO = new PreferenceDAO();
		for(int i = 0;i<eventList.length;i++){
			placeNo = Integer.parseInt(eventList[i]);
			if(placeNo<20000){
				place = pDAO.findPlace(placeNo);
			}else{
				action= aDAO.findAction(placeNo);
				place = pDAO.findPlace(action.getPlace_no());
			}
			
			this.addTimePref(prefDAO.findPreference(place.getPref_no()), timeList[timeIdx]);
			timeIdx+=2;
		}
	}

	//선호도와 시간을 입력받으면 해당 시간에 선호도를 +1 시켜주고 DB에 업데이트 까지 해주는 함수
	public void addTimePref(Preference pref, String startTime){
		SimpleDateFormat time = new SimpleDateFormat("H:mm"); // 시간대별로 계산해주는 부분
		try {
			Date start = time.parse(startTime);

			if (start.after(time.parse("10:00")) && start.before(time.parse("10:59"))) {
				pref.incPref_10_11();
			} else if (start.after(time.parse("11:00")) && start.before(time.parse("11:59"))) {
				pref.incPref_11_12();
			} else if (start.after(time.parse("12:00")) && start.before(time.parse("12:59"))) {
				pref.incPref_12_13();
			} else if (start.after(time.parse("13:00")) && start.before(time.parse("13:59"))) {
				pref.incPref_13_14();
			} else if (start.after(time.parse("14:00")) && start.before(time.parse("14:59"))) {
				pref.incPref_14_15();
			} else if (start.after(time.parse("15:00")) && start.before(time.parse("15:59"))) {
				pref.incPref_15_16();
			} else if (start.after(time.parse("16:00")) && start.before(time.parse("16:59"))) {
				pref.incPref_16_17();
			} else if (start.after(time.parse("17:00")) && start.before(time.parse("17:59"))) {
				pref.incPref_17_18();
			} else if (start.after(time.parse("18:00")) && start.before(time.parse("18:59"))) {
				pref.incPref_18_19();
			} else if (start.after(time.parse("19:00")) && start.before(time.parse("19:59"))) {
				pref.incPref_19_20();
			} else if (start.after(time.parse("20:00")) && start.before(time.parse("20:59"))) {
				pref.incPref_20_21();
			} else if (start.after(time.parse("21:00")) && start.before(time.parse("22:00"))) {
				pref.incPref_21_22();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		new PreferenceDAO().updatePreference(pref);
	}
}//class
