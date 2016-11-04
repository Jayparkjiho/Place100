package coex.vo;

/**
 * @author 고경현
 * 코엑스 내부에 존재하는 장소 객체
 */
public class Place {
	
	private int place_no;
	private String place_name;
	private int place_nodeno;
	private String place_info;
	private int place_price;
	private float place_eval_avg;
	private int place_working_day;
	private String place_open_time;
	private String place_close_time;
	private String place_category;
	private int place_runtime_min;
	private int place_runtime_max;
	private String place_photo_name;
	private int place_type;//0:기타,1:박람회,2:문화 여가,3:식당,4:디저트/카페
	private int pref_no;
	
	public Place(){}

	public Place(int place_no, String place_name, int place_nodeno, String place_info, int place_price,
			float place_eval_avg, int place_working_day, String place_open_time, String place_close_time,
			String place_category, int place_runtime_min, int place_runtime_max, String place_photo_name,
			int place_type, int pref_no) {
		this.place_no = place_no;
		this.place_name = place_name;
		this.place_nodeno = place_nodeno;
		this.place_info = place_info;
		this.place_price = place_price;
		this.place_eval_avg = place_eval_avg;
		this.place_working_day = place_working_day;
		this.place_open_time = place_open_time;
		this.place_close_time = place_close_time;
		this.place_category = place_category;
		this.place_runtime_min = place_runtime_min;
		this.place_runtime_max = place_runtime_max;
		this.place_photo_name = place_photo_name;
		this.place_type = place_type;
		this.pref_no = pref_no;
	}

	public int getPlace_no() {
		return place_no;
	}

	public void setPlace_no(int place_no) {
		this.place_no = place_no;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public int getPlace_nodeno() {
		return place_nodeno;
	}

	public void setPlace_nodeno(int place_nodeno) {
		this.place_nodeno = place_nodeno;
	}

	public String getPlace_info() {
		return place_info;
	}

	public void setPlace_info(String place_info) {
		this.place_info = place_info;
	}

	public int getPlace_price() {
		return place_price;
	}

	public void setPlace_price(int place_price) {
		this.place_price = place_price;
	}

	public float getPlace_eval_avg() {
		return place_eval_avg;
	}

	public void setPlace_eval_avg(float place_eval_avg) {
		this.place_eval_avg = place_eval_avg;
	}

	public int getPlace_working_day() {
		return place_working_day;
	}

	public void setPlace_working_day(int place_working_day) {
		this.place_working_day = place_working_day;
	}

	public String getPlace_open_time() {
		return place_open_time;
	}

	public void setPlace_open_time(String place_open_time) {
		this.place_open_time = place_open_time;
	}

	public String getPlace_close_time() {
		return place_close_time;
	}

	public void setPlace_close_time(String place_close_time) {
		this.place_close_time = place_close_time;
	}

	public String getPlace_category() {
		return place_category;
	}

	public void setPlace_category(String place_category) {
		this.place_category = place_category;
	}

	public int getPlace_runtime_min() {
		return place_runtime_min;
	}

	public void setPlace_runtime_min(int place_runtime_min) {
		this.place_runtime_min = place_runtime_min;
	}

	public int getPlace_runtime_max() {
		return place_runtime_max;
	}

	public void setPlace_runtime_max(int place_runtime_max) {
		this.place_runtime_max = place_runtime_max;
	}

	public String getPlace_photo_name() {
		return place_photo_name;
	}

	public void setPlace_photo_name(String place_photo_name) {
		this.place_photo_name = place_photo_name;
	}

	public int getPlace_type() {
		return place_type;
	}

	public void setPlace_type(int place_type) {
		this.place_type = place_type;
	}

	public int getPref_no() {
		return pref_no;
	}

	public void setPref_no(int pref_no) {
		this.pref_no = pref_no;
	}

	@Override
	public String toString() {
		return "Place [place_no=" + place_no + ", place_name=" + place_name + ", place_nodeno=" + place_nodeno
				+ ", place_info=" + place_info + ", place_price=" + place_price + ", place_eval_avg=" + place_eval_avg
				+ ", place_working_day=" + place_working_day + ", place_open_time=" + place_open_time
				+ ", place_close_time=" + place_close_time + ", place_category=" + place_category
				+ ", place_runtime_min=" + place_runtime_min + ", place_runtime_max=" + place_runtime_max
				+ ", place_photo_name=" + place_photo_name + ", place_type=" + place_type + ", pref_no=" + pref_no
				+ "]";
	}

	
	
	
	
}
