package coex.vo;

public class Action {
	private int action_no;
	private String action_name;
	private int place_no;
	private String action_start_date;
	private String action_end_date;
	private String action_photo_name;
	
	public Action() {
	}
	
	public Action(int action_no, String action_name, int place_no, String action_start_date, String action_end_date,
			String action_photo_name) {
		super();
		this.action_no = action_no;
		this.action_name = action_name;
		this.place_no = place_no;
		this.action_start_date = action_start_date;
		this.action_end_date = action_end_date;
		this.action_photo_name = action_photo_name;
	}

	public int getAction_no() {
		return action_no;
	}

	public void setAction_no(int action_no) {
		this.action_no = action_no;
	}

	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	public int getPlace_no() {
		return place_no;
	}

	public void setPlace_no(int place_no) {
		this.place_no = place_no;
	}

	public String getAction_start_date() {
		return action_start_date;
	}

	public void setAction_start_date(String action_start_date) {
		this.action_start_date = action_start_date;
	}

	public String getAction_end_date() {
		return action_end_date;
	}

	public void setAction_end_date(String action_end_date) {
		this.action_end_date = action_end_date;
	}

	public String getAction_photo_name() {
		return action_photo_name;
	}

	public void setAction_photo_name(String action_photo_name) {
		this.action_photo_name = action_photo_name;
	}

	public String toString() {
		return "Action [action_no=" + action_no + ", action_name=" + action_name + ", place_no=" + place_no
				+ ", action_start_date=" + action_start_date + ", action_end_date=" + action_end_date
				+ ", action_photo_name=" + action_photo_name + "]";
	}
}
