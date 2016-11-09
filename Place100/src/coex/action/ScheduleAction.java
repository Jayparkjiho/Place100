package coex.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import coex.dao.ScheduleDAO;
import coex.vo.Schedule;

public class ScheduleAction extends ActionSupport implements SessionAware{
	
	private Schedule schedule;
	private String search_text;
	private Map<String,Object> session;
	
	
	public String searchSchedule() throws Exception{
		System.out.println("searchSchedule() 메소드 실행");
		System.out.println(search_text);
		ScheduleDAO sdao = new ScheduleDAO();
		schedule = sdao.findSchedule(Integer.parseInt(search_text));
		this.session.put("Schedule_no", schedule.getSchedule_no());
		return SUCCESS;
	}


	public Schedule getSchedule() {
		return schedule;
	}


	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}


	public String getSearch_text() {
		return search_text;
	}


	public void setSearch_text(String search_text) {
		this.search_text = search_text;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
	
	
}
