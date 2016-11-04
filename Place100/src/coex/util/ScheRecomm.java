package coex.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.print.event.PrintEvent;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import coex.dao.ActionDAO;
import coex.dao.AnswerDAO;
import coex.dao.PlaceAndPrefDAO;
import coex.dao.PlaceDAO;
import coex.dao.PreferenceDAO;
import coex.vo.Answer;
import coex.vo.Place;
import coex.vo.PlaceAndPref;
import coex.vo.Preference;
import coex.vo.Schedule;

/**
 * 사용자로부터 스케줄, 답변, 채울 시간을 입력받아 해당 위치에 액션을 채워주는 알고리즘과 각종 필요 메쏘드들을 모아둔 클래스
 * 
 * @author 김진홍 최지영
 * 
 */
public class ScheRecomm {

	/**
	 * Answer를 입력받아 Schedule로 만드는 메쏘드 주의:DB상에 Answer와 Preference는 만들지만 Schedule은
	 * 바로 입력하지 않는다. 추천이 완성되면 넣어주어야 한다
	 */
	// TODO:
	// 맴버아이디도 세션에서 꺼내서 넣어줘야 한다
	public Schedule ansToSche(Answer answer, String memberID) {
		AnswerDAO ansDAO = new AnswerDAO();// 답변 DAO

		// 먼저 Answer를 DB에 넣고
		ansDAO.insertAnswer(answer);
		int answer_no = ansDAO.getSeqNo();
		answer.setAnswer_no(answer_no);
		// 새로운 스케줄 객체 생성
		Schedule resultSche = new Schedule();
		// 스케줄 객체에 답변의 식별자 저장
		resultSche.setAnswerno(answer.getAnswer_no());
		// 스케줄 객체에 스케줄 만든 회원 ID 저장
		// 스케줄 날짜 저장
		Format formatter = new SimpleDateFormat("yy/MM/dd");
		String dateStr = formatter.format(answer.getAnswer_date());
		resultSche.setSchedule_date(dateStr);
		// 스케줄 START TIME 셋팅
		resultSche.setSchedule_start_time(answer.getAnswer_start_time());
		// 스케줄 END_TIME 셋팅
		resultSche.setSchedule_end_time(answer.getAnswer_end_time());
		//스케줄 타임테이블 설정
		resultSche.setSchedule_time_table("");
		// PREF생성 후 해당하는 PREF의 넘버를 PREFNO에 넣어줌
		Preference pref = new Preference();
		PreferenceDAO prefDAO = new PreferenceDAO();
		prefDAO.insertPreference(pref);
		resultSche.setPrefno(prefDAO.getPrefNo());

		return resultSche;
	}

	/**
	 * 스케줄을 입력받아 빈 요청한 시간대 내부에 값을 넣어주는 메쏘드 재귀방식으로 반복해서 넣어준다
	 * 
	 * @param answer
	 * @param schedule
	 * @param startTime
	 * @param endTime
	 */
	public void scheduleRecomm(Answer answer, Schedule schedule, String startTime, String endTime) {
		// TODO
		// 만약 startTime과 endTime이 null이라면 자동적으로 마지막 스케줄의 종료시간과 총 스케줄의 종료시간을 넣어준다

		// 변수목록 및 초기화
		String lastEventEndTime;// 스케줄의 일정 중 마지막 일정의 종료시간
		int mealCnt = 0; // 스케줄 속의 식사 이벤트 갯수를 센다
		int cafeCnt = 0; // 스케줄 속의 카페 이벤트 갯수를 센다
		int cultureCnt = 0;// 스케줄 속의 문화/여가 활동 갯수를 센다
		int shoppingCnt = 0;// 스케줄 속의 쇼핑 갯수를 센다(3개까지?)
		String[] pArray = null;
		ArrayList<Place> pList = null;
		ActionDAO actDAO = new ActionDAO();// 액션 DAO
		Place prevPlace = null;

		// TODO:Base Case
		// 스케줄 꽉 채워줬는지 여부를 체크하는 부분
		if (schedule.getSchedule_time_list()!= null) {// 스케줄에 일정이 하나라도
																// 있으면
			String[] splitted = schedule.getSchedule_time_list().split(",|~");
			lastEventEndTime = splitted[splitted.length - 1];

			// 스케줄의 마지막 이벤트 시간과 스케줄의 마지막 시간을 비교해 같으면 바로 종료한다.
			if (checkTimeDiff(endTime, lastEventEndTime) == 0) {
				System.out.println("스케줄 꽉 참 완성!!");
				if(schedule.getSchedule_event_list().endsWith(","))schedule.setSchedule_event_list(schedule.getSchedule_event_list().substring(0,schedule.getSchedule_event_list().length()-1));
				if(schedule.getSchedule_time_list().endsWith(","))schedule.setSchedule_time_list(schedule.getSchedule_time_list().substring(0,schedule.getSchedule_time_list().length()-1));
			    if(schedule.getSchedule_node_list().endsWith(","))schedule.setSchedule_node_list(schedule.getSchedule_node_list().substring(0,schedule.getSchedule_node_list().length()-1));
			    if(schedule.getSchedule_img_list().endsWith(","))schedule.setSchedule_img_list(schedule.getSchedule_img_list().substring(0,schedule.getSchedule_img_list().length()-1));
			   
				return;
			}
		}

		// 만약 이벤트 목록이 존재하고, 길이가 0 이상이면
		if (schedule.getSchedule_event_list() != null) {
			PlaceDAO pDAO = new PlaceDAO();
			pList = new ArrayList<>();// Schedule의 현재 객체 목록을 담을 ArrayList
			pArray = schedule.getSchedule_event_list().split(",");

			// 액션목록을 채워준다
			for (String s : pArray) {
				if(s.length()<2)continue;
				
				// 만약 액션이면 해당하는 액션의 플레이스 넣어줌
				if (Integer.parseInt(s) > 20000) {
					Place pp = pDAO.selectPlace(actDAO.findAction(Integer.parseInt(s)).getPlace_no());
					pList.add(pp);
				} else {
					pList.add(pDAO.selectPlace(Integer.parseInt(s)));
				}
			}
			// 마지막 액션(즉 이전 액션을 채워줌)
			prevPlace = pList.get(pList.size() - 1);
			System.out.println("이전 Place : " + prevPlace.toString());
			for (Place p : pList) {
				// 식사갯수체크
				if (p.getPlace_type() == 3) {
					mealCnt++;
				}
				// 카페갯수체크
				if (p.getPlace_type() == 4) {
					cafeCnt++;
				}
				// 문화여가 갯수체크
				if (p.getPlace_type() == 2) {
					cultureCnt++;
				}
			}
		}
		System.out.println("식사 횟수: " + mealCnt);
		System.out.println("카페 수: " + cafeCnt);
		System.out.println("문화생활 횟수: " + cultureCnt);
		// TODO: 이전 플레이스 : PrevPlace

		// 현재 스캐줄을 채워줄 시작시간, 종료시간

		// 먼저 DB에서 PlaceAndPref를 가져와서 ArrayList에 담는다.
		PlaceAndPrefDAO pnpDAO = new PlaceAndPrefDAO();
		ArrayList<PlaceAndPref> pnpList = (ArrayList<PlaceAndPref>) pnpDAO.getAllList();

		// 그 후 소거 파트로 넘어간다

		// 소거부분--------------------------------------------------------------------------------------------------------------------------------------------

		for (Iterator<PlaceAndPref> pnp = pnpList.iterator(); pnp.hasNext();) {
			PlaceAndPref pandp = pnp.next();

			if (checkTimeDiff(pandp.getPlace_open_time(), startTime) > 0) {
				System.out.println(pandp.getPlace_name() + " 아직 영업개시 안해서 삭제");// 영업개시
																				// 아직
																				// 안해서
																				// 제거
				pnp.remove();
			} else if (checkTimeDiff(pandp.getPlace_close_time(), startTime) < 0) {// 영업시간
																					// 끝난곳
																					// 제거
				System.out.println(pandp.getPlace_name() + " 영업 종료해서 삭제");
				pnp.remove();
			} else if (pandp.getPlace_type() == 1) {// 박람회 관람이 아닌 경우 제거
				System.out.println(pandp.getPlace_name() + " 박람회라서 삭제");
				pnp.remove();
			} else if ((mealCnt == 2 && pandp.getPlace_type() == 3) || (prevPlace!=null&&prevPlace.getPlace_type()==3&&pandp.getPlace_type() == 3)) {// 식사가 꽉 차거나 이전 작업이 식사이면
																	// 제거
				System.out.println(pandp.getPlace_name() + " 식사가 꽉 차서 삭제");
				pnp.remove();
			} else if (cafeCnt == 2 && pandp.getPlace_type() == 4) {// 카페가 꽉 차면
																	// 제거
				System.out.println(pandp.getPlace_name() + " 커피숍이 꽉 차서 삭제");
				pnp.remove();
			} else if (cultureCnt == 2 && pandp.getPlace_type() == 2) {// 문화/여가가
																		// 꽉 차면
																		// 제거
				System.out.println(pandp.getPlace_name() + " 문화생활이 꽉 차서 삭제");
				pnp.remove();
			} else if (this.timeMinuteDiff(Integer.parseInt(startTime.split(":")[0]),
					Integer.parseInt(startTime.split(":")[1]), Integer.parseInt(endTime.split(":")[0]),
					Integer.parseInt(endTime.split(":")[1])) < pandp.getPlace_runtime_min()) {
				System.out.println(pandp.getPlace_name() + " 최소런타임이 현재 빈 공간보다 커서 삭제");
				pnp.remove();
			}

		}

		// 이미 이벤트 목록에 있는 것들 제거
		for (Iterator<PlaceAndPref> pnp = pnpList.iterator(); pnp.hasNext();) {
			PlaceAndPref pandp = pnp.next();
			if (pList != null) {
				for (Place p : pList) {
					if (pandp.getPlace_no() == p.getPlace_no()) {
						System.out.println(pandp.getPlace_name() + " 이미 존재하는 공간이라 제거");
						pnp.remove();
					}
				}
			}
		}

		System.out.println("삭제후 남은 장소 목록" + pnpList.size());

		for (PlaceAndPref p : pnpList) {
			p.setPref_no(0);
		}
		// 점수계산부분-----------------------------------------------------------------------------------------------------------------------
		// 식사시간이면 식사에 1000점씩 추가 (pref_no)에 추가
		if ((checkTimeDiff("10:59", startTime) == -1 && checkTimeDiff(startTime, "14:00") == -1)//TODO:10:59?
				|| (checkTimeDiff("16:59", startTime) == -1 && checkTimeDiff(startTime, "20:00") == -1)) {
			for (PlaceAndPref p : pnpList) {
				if (p.getPlace_type() == 3) {
					p.setPref_no(1000);
				}
			}

		}else{
			for (PlaceAndPref p : pnpList) {
				if (p.getPlace_type() == 3) {
					p.setPref_no(-1000);
				}
			}
		}
		
		
		if (prevPlace != null) {
			// PrevPlace의 번호가 가 식사(3)였다면 카페/디저트 1000점 추가
			if (prevPlace.getPlace_type() == 3) {
				for (PlaceAndPref p : pnpList) {
					if (p.getPlace_type() == 4) {
						p.setPref_no(1000);
					}
				}
			}
			// PrevEvent가 디저트/카페(4) 였다면 문화/여가활동에 1000점 추가 (ex : 영화, 버스킹)
			if (prevPlace.getPlace_type() == 4) {
				for (PlaceAndPref p : pnpList) {
					if (p.getPlace_type() == 2) {
						p.setPref_no(1000);
					}
				}
			}
		}
		// TODO: PrevEvent가 쇼핑이었으면? + 그 외 부분

		// 가산점 확인
		/*
		 * for(PlaceAndPref p : pnpList){ System.out.println(p.getPlace_name()
		 * +":"+ p.getPref_no()); }
		 */

		// 최종선택부분=====================================================================
		HashMap<Integer, Integer> placeNoAndScore = new HashMap<>();
		for (PlaceAndPref p : pnpList) {
			int score = this.answerToScore(answer, p, startTime);
			int placeNo = p.getPlace_no();
			// System.out.println(placeNo + ":" + score);
			placeNoAndScore.put(placeNo, score);
		}

		// 벨류로 정렬
		ArrayList<Integer> finalWinners = new ArrayList<>();
		Iterator<Integer> it = this.sortByValue(placeNoAndScore).iterator();

		int aaa = it.next();
		int count = 0;
		while (it.hasNext() && count < 30) {
			int temp = (Integer) it.next();
			//System.out.println(temp + " = " + placeNoAndScore.get(temp));
			finalWinners.add(temp);
			count++;
		}
		Place finalWinner=null;
		if (prevPlace!=null && prevPlace.getPlace_type() == 4) {//이전행위가 커피면
			finalWinner = new PlaceDAO().selectPlace(finalWinners.get(0));//한번에 빼온다
		}else{
		// 섞고
		 Collections.shuffle(finalWinners);
		// 빼온다
		finalWinner = new PlaceDAO().selectPlace(finalWinners.get(0));
		}
		System.out.println("최종후보 : " + finalWinner.getPlace_name());

		// Place의 runtime을 고려해 넣어줘야 한다
		// 스케줄의 최대 런타임부터 최소 런타임까지 15씩 빼가며 반복하며
		// 현재 스캐줄의 입력시간에 남은 시간에 들어갈 수 있는 크기가 되면 넣어준다
		int addTime = 0;
		String newStartTime = "";
		for (int i = finalWinner.getPlace_runtime_max(); i >= finalWinner.getPlace_runtime_min(); i = i - 15) {
			if (checkTimeDiff(this.addTime2("" + i, startTime), endTime) <= 0) {
				addTime = i;
				break;
			}
		}

		// 새로운 시작시간은 더할시간 + 시작시간
		newStartTime = addTime2("" + addTime, startTime);
		// 종료시간은 유지
		// null인경우 처리
		if (schedule.getSchedule_event_list() == null) {
			schedule.setSchedule_event_list("");
		}
		if (schedule.getSchedule_time_list() == null) {
			schedule.setSchedule_time_list("");
		}
		if (schedule.getSchedule_node_list() == null) {
			schedule.setSchedule_node_list("");
		}
		if (schedule.getSchedule_img_list() == null) {
			schedule.setSchedule_img_list("");
		}

		// 스케줄의 시간리스트에 시작시간~새로운시작시간 넣어줌
		schedule.setSchedule_time_list(schedule.getSchedule_time_list() + startTime + "~" + newStartTime+",");
		// 스케줄의 이벤트리스트에 새로운 이벤트 번호 넣어줌
		schedule.setSchedule_event_list(schedule.getSchedule_event_list() + finalWinner.getPlace_no()+",");
		// 스케줄의 노드리스트에 새로운 이벤트의 노드 넣어줌
		schedule.setSchedule_node_list(schedule.getSchedule_node_list() + finalWinner.getPlace_nodeno()+",");
		// 스케줄의 이미지리스트에 새로운 이벤트의 이미지 넣어줌
		schedule.setSchedule_img_list(schedule.getSchedule_img_list() + finalWinner.getPlace_photo_name()+",");
		// 그 후 새 시작시간값과 종료시간을 넣어 재귀호출
		System.out.println(schedule.toString());
		// 스케줄 객체에 시간값, 이벤트, 시간목록, 노드, 사진 추가해주고

		// 재귀로 불러야함 시작시간과 종료시간을 넣어 다시 호출한다
		scheduleRecomm(answer, schedule, newStartTime, endTime);
	}

	
	/**
	 * 사용자가 입력한 답변을 바탕으로 Preference에서 필요한 값만 합산해 점수로 환산하는 메소드
	 * @param answer
	 * @param Pref
	 * @param startTime
	 * @return
	 */
	public int answerToScore(Answer answer, PlaceAndPref Pref, String startTime) {
		int totalScore = 0;
		totalScore += Pref.getPref_no();// 가산점 추가
		switch (answer.getAnswer_purpose_no()) {// answer의 목적별 분류
		case 0: // answer의 목적이 전시회일때
			totalScore += Pref.getPref_conference();
			break;
		case 1: // answer의 목적이 식사일때
			totalScore += Pref.getPref_meal();
			break;
		case 2: // answer의 목적이 쇼핑일때
			totalScore += Pref.getPref_shopping();
			break;
		case 3: // answer의 목적이 영화일때
			totalScore += Pref.getPref_culture();
			break;
		case 4: // answer의 목적이 연인일때
			totalScore += Pref.getPref_date();
			break;
		case 5: // answer의 목적이 기타일때
			totalScore += Pref.getPref_etc();
			break;
		}
		switch (answer.getAnswer_age()) { // 나이대에 따른 분류
		case 10:
			totalScore += Pref.getPref_10();
			break;
		case 20:
			totalScore += Pref.getPref_20();
			break;
		case 30:
			totalScore += Pref.getPref_30();
			break;
		case 40:
			totalScore += Pref.getPref_40();
			break;
		case 50:
			totalScore += Pref.getPref_40();
			break;
		}
		switch (answer.getAnswer_sex()) { // answer의 성별에 따른 구분
		case 0:
			totalScore += Pref.getPref_male();
			break;
		case 1:
			totalScore += Pref.getPref_female();
			break;
		case 2:
			totalScore += Pref.getPref_male();
			totalScore += Pref.getPref_female();
		}
		switch (answer.getAnswer_head_count()) { // answer의 헤드카운드별로 계산

		case 1:
			totalScore += Pref.getPref_single();
			break;
		case 2:
			totalScore += Pref.getPref_two();
			break;
		case 3:
			totalScore += Pref.getPref_three();
			break;
		case 5:
			totalScore += Pref.getPref_five();
			break;
		case 10:
			totalScore += Pref.getPref_ten();
			break;
		}
		SimpleDateFormat time = new SimpleDateFormat("H:mm"); // 시간대별로
		// 계산해주는 부분
		try {
			Date start = time.parse(startTime);

			if (start.after(time.parse("10:00")) && start.before(time.parse("10:59"))) {
				totalScore += Pref.getPref_10_11();
			} else if (start.after(time.parse("11:00")) && start.before(time.parse("11:59"))) {
				totalScore += Pref.getPref_11_12();
			} else if (start.after(time.parse("12:00")) && start.before(time.parse("12:59"))) {
				totalScore += Pref.getPref_12_13();
			} else if (start.after(time.parse("13:00")) && start.before(time.parse("13:59"))) {
				totalScore += Pref.getPref_13_14();
			} else if (start.after(time.parse("14:00")) && start.before(time.parse("14:59"))) {
				totalScore += Pref.getPref_14_15();
			} else if (start.after(time.parse("15:00")) && start.before(time.parse("15:59"))) {
				totalScore += Pref.getPref_15_16();
			} else if (start.after(time.parse("16:00")) && start.before(time.parse("16:59"))) {
				totalScore += Pref.getPref_16_17();
			} else if (start.after(time.parse("17:00")) && start.before(time.parse("17:59"))) {
				totalScore += Pref.getPref_17_18();
			} else if (start.after(time.parse("18:00")) && start.before(time.parse("18:59"))) {
				totalScore += Pref.getPref_18_19();
			} else if (start.after(time.parse("19:00")) && start.before(time.parse("19:59"))) {
				totalScore += Pref.getPref_19_20();
			} else if (start.after(time.parse("20:00")) && start.before(time.parse("20:59"))) {
				totalScore += Pref.getPref_20_21();
			} else if (start.after(time.parse("21:00")) && start.before(time.parse("22:00"))) {
				totalScore += Pref.getPref_21_22();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalScore;
	}

	/**
	 * 시간체크하는 메쏘드 1번 문자열 시간이 2번 문자열 시간보다 이후면 1 1번 문자열 시간이 2번 문자열 시간보다 이전이면 -1 2번
	 * 문자열 시간이 1번 문자열 시간보다 이후면 -1 1번 문자열과 2번 문자열의 시간이 같으면 0
	 * 
	 * @param firstTime
	 * @param secondTime
	 * @return
	 */
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
	 * 장소평가 목록과 장소번호를 받아 해당 장소를 삭제하는 메쏘드
	 * 
	 * @param pnpList
	 *            점포와선호도가 같이있는 객체들이 담긴 어레이리스트
	 * @param place_no
	 *            삭제하고자 하는 장소번호
	 */
	public void removeEvent(ArrayList<PlaceAndPref> pnpList, int place_no) {

		for (Iterator<PlaceAndPref> pnp = pnpList.iterator(); pnp.hasNext();) {
			PlaceAndPref pandp = pnp.next();
			if (pandp.getPlace_no() == place_no) {
				pnp.remove();
			}
		}

	}

	/*
	 * 해쉬맵 정렬에 사용되는 함수
	 */
	public static List<Integer> sortByValue(final Map<Integer, Integer> map) {
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(map.keySet());

		Collections.sort(list, new Comparator<Object>() {
			@SuppressWarnings("unchecked")
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);

				return ((Comparable<Object>) v1).compareTo(v2);
			}
		});
		Collections.reverse(list); // 주석시 오름차순
		return list;
	}

	// 두 시간 사이의 분을 계산하는 함수
	public int timeMinuteDiff(int startTime, int startMin, int endTime, int endMin) {
		Date d1 = new Date(2011, 01, 01, startTime, startMin);
		Date d2 = new Date(2011, 01, 01, endTime, endMin);
		int diffMs = (int) (d2.getTime() - d1.getTime());
		int diffSec = diffMs / 1000;
		int min = diffSec / 60;
		// System.out.println("The difference is "+min+" minutes");
		return min;
	}

	// 두 시간을 받아 더해주는 함수
	public String addTime(String time1, String time2) {

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date date1 = null;
		Date date2 = null;
		try {
			date1 = timeFormat.parse(time1);
			date2 = timeFormat.parse(time2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long sum = date1.getTime() + date2.getTime();

		String date3 = timeFormat.format(new Date(sum));
		return date3;
	}

	// 분값과 시간을 받아 더해주는 알고리즘
	public String addTime2(String minute, String time2) {
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

	public static void main(String[] args) {
		// System.out.println(new ScheRecomm().addTime("12:30", "01:35"));
		System.out.println(new ScheRecomm().addTime2("70", "01:35"));

	}
}
