package coex.vo;

import java.util.ArrayList;

public class Alarm {
	
	private String number;
	private String day;
	private ArrayList<String> times;
	private ArrayList<String> places;
	
	public Alarm(){}
	
	public Alarm(String number, String day, ArrayList<String> times, ArrayList<String> places) {
		this.number = number;
		this.day = day;
		this.times = times;
		this.places = places;
	}



	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public ArrayList<String> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<String> times) {
		this.times = times;
	}

	public ArrayList<String> getPlaces() {
		return places;
	}

	public void setPlaces(ArrayList<String> places) {
		this.places = places;
	}

	@Override
	public String toString() {
		return "Alarm [number=" + number + ", day=" + day + ", times=" + times + ", places=" + places + "]";
	}

	
	
	
	
	

}
