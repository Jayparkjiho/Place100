package coex.util;

import coex.vo.Answer;
import coex.vo.Schedule;

public class CreateSchedule_TEST {
	public static void main(String[] args) {
		CreateSchedule csd = new CreateSchedule();
		
		Schedule sche1 = new Schedule();
		sche1.setSchedule_start_time("12:00");
		sche1.setSchedule_end_time("16:00");
		csd.addEventOnLast(sche1, 10001, 45);
		csd.addEventOnLast(sche1, 10002, 45);
		csd.addEventOnLast(sche1, 10003, 30);
		//System.out.println(sche1.toString());
		//csd.incTimePref(sche1);
		//Answer ans = new Answer();
		//ans.setAnswer_age(10);
		System.out.println("삭제전:"+sche1.toString());
		csd.removeLastEvent(sche1);
		System.out.println("삭제후:"+sche1.toString());
		csd.removeLastEvent(sche1);
		System.out.println("2 번째 삭제후:"+sche1.toString());
		csd.removeLastEvent(sche1);
		System.out.println("3 번째 삭제후:"+sche1.toString());
		csd.removeLastEvent(sche1);
		System.out.println("4 번째 삭제후:"+sche1.toString());
		
	}
}
