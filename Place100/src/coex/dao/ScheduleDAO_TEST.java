package coex.dao;

import coex.vo.Schedule;

public class ScheduleDAO_TEST {
	
	public static void main(String[] args) {
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		/*Schedule s = null;
		s = scheduleDAO.findSchedule(9999); 
		System.out.println(s.toString());
		s.setMem_id("익명입니다");
		scheduleDAO.updateSchedule(s);
		s = scheduleDAO.findSchedule(9999); 
		System.out.println(s.toString());*/
		System.out.println(scheduleDAO.getLastNo());
	}//TEST SUCCEED

}
