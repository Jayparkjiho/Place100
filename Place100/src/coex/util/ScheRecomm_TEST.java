package coex.util;

import java.util.Date;

import coex.dao.AnswerDAO;
import coex.dao.ScheduleDAO;
import coex.vo.Answer;
import coex.vo.Schedule;
import oracle.sql.DATE;

public class ScheRecomm_TEST {
	public static void main(String[] args) {
		//TODO:돌리지 말것 위험함.
		ScheRecomm scherecomm = new ScheRecomm();
		Answer ans1 = new Answer(0, 1, new Date(), "12:00", "15:00", 1, 20, 1, 1, 1, 1, 1, "", "", 3, 5, "", "", 5, 4, "", "", 7);
		//System.out.println(scherecomm.ansToSche(ans1, "익명").toString());
		Schedule s1 = new Schedule();
		Schedule s2 = new ScheduleDAO().findSchedule(10000);//
		s1.setSchedule_start_time("10:00");
		s1.setSchedule_end_time("21:15");
		scherecomm.scheduleRecomm(ans1, s1, "10:00","20:15");
		System.out.println(s1);
		//scherecomm.scheduleRecomm(ans1, s1, "21:00","22:00");
	}
}
