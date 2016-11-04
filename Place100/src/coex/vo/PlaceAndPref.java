package coex.vo;

//각 장소와 해당 장소에 해당하는 Preference(선호도) 도 같이 가지고 있는 객체
//DB에서는 MAP 방식으로 가져와서 DAO에서 채워줘야 할 것 같다

public class PlaceAndPref {
	private int place_no;	//가게번호
	private String place_name;//가게이름
	private int place_nodeno; //가게위치노드번호
	private String place_info; //가게정보
	private int place_price; //가게비용
	private float place_eval_avg; //가게 평가평균
	private int place_working_day; //업무일
	private String place_open_time; //여는시간
	private String place_close_time; //닫는시간
	private String place_category; //카테고리
	private int place_runtime_min; //최소사용시간
	private int place_runtime_max; //최대사용시간
	private String place_photo_name; //가게사진이름
	private int place_type; //가게타입(0 박람회 1 문화/여가 2기타)
	int pref_no;			//선호도 번호(식별자)
	private int pref_10; 	//10대 선호도
	private int pref_20;	//20대 선호도
	private int pref_30;	//30대 선호도
	private int pref_40;	//40대 선호도
	private int pref_male;	//남성 선호도
	private int pref_female;//여성 선호도
	private int pref_conference;//방문목적이 컨퍼런스 일 때 선호도
	private int pref_shopping;//방문목적이 쇼핑일 때 선호도
	private int pref_date;//방문목적이 데이트일때 선호도
	private int pref_meal;//방문목적이 식사일때 선호도
	private int pref_culture;//방문목적이 문화관람일 때 선호도
	private int pref_etc;//방문목적이 기타일 때 선호도
	private int pref_single;//방문자가 한명일 때 선호도
	private int pref_two;//방문자가 두명일 때 선호도
	private int pref_three;//방문자가 세명일 때 선호도
	private int pref_four;//방문자가 네명일 때 선호도
	private int pref_five;//방문자가 다섯명일 때 선호도
	private int pref_ten;//방문자가 열명일 때 선호도
	private int pref_10_11;//방문시간이 10시~10:59시일때 선호도
	private int pref_11_12;//방문시간이 11시~11:59시일때 선호도
	private int pref_12_13;//방문시간이 12시~12:59시일때 선호도
	private int pref_13_14;//방문시간이 13시~13:59시일때 선호도
	private int pref_14_15;//방문시간이 14시~14:59시일때 선호도
	private int pref_15_16;//방문시간이 15시~15:59시일때 선호도
	private int pref_16_17;//방문시간이 16시~16:59시일때 선호도
	private int pref_17_18;//방문시간이 17시~17:59시일때 선호도
	private int pref_18_19;//방문시간이 18시~18:59시일때 선호도
	private int pref_19_20;//방문시간이 19시~19:59시일때 선호도
	private int pref_20_21;//방문시간이 20시~20:59시일때 선호도
	private int pref_21_22;//방문시간이 21시~21:59시일때 선호도
	
	public PlaceAndPref() {
	}
	
	public PlaceAndPref(int place_no, String place_name, int place_nodeno, String place_info, int place_price,
			float place_eval_avg, int place_working_day, String place_open_time, String place_close_time,
			String place_category, int place_runtime_min, int place_runtime_max, String place_photo_name,
			int place_type, int pref_no, int pref_10, int pref_20, int pref_30, int pref_40, int pref_male,
			int pref_female, int pref_conference, int pref_shopping, int pref_date, int pref_meal, int pref_culture,
			int pref_etc, int pref_single, int pref_two, int pref_three, int pref_four, int pref_five, int pref_ten,
			int pref_10_11, int pref_11_12, int pref_12_13, int pref_13_14, int pref_14_15, int pref_15_16,
			int pref_16_17, int pref_17_18, int pref_18_19, int pref_19_20, int pref_20_21, int pref_21_22) {
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
		this.pref_10 = pref_10;
		this.pref_20 = pref_20;
		this.pref_30 = pref_30;
		this.pref_40 = pref_40;
		this.pref_male = pref_male;
		this.pref_female = pref_female;
		this.pref_conference = pref_conference;
		this.pref_shopping = pref_shopping;
		this.pref_date = pref_date;
		this.pref_meal = pref_meal;
		this.pref_culture = pref_culture;
		this.pref_etc = pref_etc;
		this.pref_single = pref_single;
		this.pref_two = pref_two;
		this.pref_three = pref_three;
		this.pref_four = pref_four;
		this.pref_five = pref_five;
		this.pref_ten = pref_ten;
		this.pref_10_11 = pref_10_11;
		this.pref_11_12 = pref_11_12;
		this.pref_12_13 = pref_12_13;
		this.pref_13_14 = pref_13_14;
		this.pref_14_15 = pref_14_15;
		this.pref_15_16 = pref_15_16;
		this.pref_16_17 = pref_16_17;
		this.pref_17_18 = pref_17_18;
		this.pref_18_19 = pref_18_19;
		this.pref_19_20 = pref_19_20;
		this.pref_20_21 = pref_20_21;
		this.pref_21_22 = pref_21_22;
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

	public int getPref_10() {
		return pref_10;
	}

	public void setPref_10(int pref_10) {
		this.pref_10 = pref_10;
	}

	public int getPref_20() {
		return pref_20;
	}

	public void setPref_20(int pref_20) {
		this.pref_20 = pref_20;
	}

	public int getPref_30() {
		return pref_30;
	}

	public void setPref_30(int pref_30) {
		this.pref_30 = pref_30;
	}

	public int getPref_40() {
		return pref_40;
	}

	public void setPref_40(int pref_40) {
		this.pref_40 = pref_40;
	}

	public int getPref_male() {
		return pref_male;
	}

	public void setPref_male(int pref_male) {
		this.pref_male = pref_male;
	}

	public int getPref_female() {
		return pref_female;
	}

	public void setPref_female(int pref_female) {
		this.pref_female = pref_female;
	}

	public int getPref_conference() {
		return pref_conference;
	}

	public void setPref_conference(int pref_conference) {
		this.pref_conference = pref_conference;
	}

	public int getPref_shopping() {
		return pref_shopping;
	}

	public void setPref_shopping(int pref_shopping) {
		this.pref_shopping = pref_shopping;
	}

	public int getPref_date() {
		return pref_date;
	}

	public void setPref_date(int pref_date) {
		this.pref_date = pref_date;
	}

	public int getPref_meal() {
		return pref_meal;
	}

	public void setPref_meal(int pref_meal) {
		this.pref_meal = pref_meal;
	}

	public int getPref_culture() {
		return pref_culture;
	}

	public void setPref_culture(int pref_culture) {
		this.pref_culture = pref_culture;
	}

	public int getPref_etc() {
		return pref_etc;
	}

	public void setPref_etc(int pref_etc) {
		this.pref_etc = pref_etc;
	}

	public int getPref_single() {
		return pref_single;
	}

	public void setPref_single(int pref_single) {
		this.pref_single = pref_single;
	}

	public int getPref_two() {
		return pref_two;
	}

	public void setPref_two(int pref_two) {
		this.pref_two = pref_two;
	}

	public int getPref_three() {
		return pref_three;
	}

	public void setPref_three(int pref_three) {
		this.pref_three = pref_three;
	}

	public int getPref_four() {
		return pref_four;
	}

	public void setPref_four(int pref_four) {
		this.pref_four = pref_four;
	}

	public int getPref_five() {
		return pref_five;
	}

	public void setPref_five(int pref_five) {
		this.pref_five = pref_five;
	}

	public int getPref_ten() {
		return pref_ten;
	}

	public void setPref_ten(int pref_ten) {
		this.pref_ten = pref_ten;
	}

	public int getPref_10_11() {
		return pref_10_11;
	}

	public void setPref_10_11(int pref_10_11) {
		this.pref_10_11 = pref_10_11;
	}

	public int getPref_11_12() {
		return pref_11_12;
	}

	public void setPref_11_12(int pref_11_12) {
		this.pref_11_12 = pref_11_12;
	}

	public int getPref_12_13() {
		return pref_12_13;
	}

	public void setPref_12_13(int pref_12_13) {
		this.pref_12_13 = pref_12_13;
	}

	public int getPref_13_14() {
		return pref_13_14;
	}

	public void setPref_13_14(int pref_13_14) {
		this.pref_13_14 = pref_13_14;
	}

	public int getPref_14_15() {
		return pref_14_15;
	}

	public void setPref_14_15(int pref_14_15) {
		this.pref_14_15 = pref_14_15;
	}

	public int getPref_15_16() {
		return pref_15_16;
	}

	public void setPref_15_16(int pref_15_16) {
		this.pref_15_16 = pref_15_16;
	}

	public int getPref_16_17() {
		return pref_16_17;
	}

	public void setPref_16_17(int pref_16_17) {
		this.pref_16_17 = pref_16_17;
	}

	public int getPref_17_18() {
		return pref_17_18;
	}

	public void setPref_17_18(int pref_17_18) {
		this.pref_17_18 = pref_17_18;
	}

	public int getPref_18_19() {
		return pref_18_19;
	}

	public void setPref_18_19(int pref_18_19) {
		this.pref_18_19 = pref_18_19;
	}

	public int getPref_19_20() {
		return pref_19_20;
	}

	public void setPref_19_20(int pref_19_20) {
		this.pref_19_20 = pref_19_20;
	}

	public int getPref_20_21() {
		return pref_20_21;
	}

	public void setPref_20_21(int pref_20_21) {
		this.pref_20_21 = pref_20_21;
	}

	public int getPref_21_22() {
		return pref_21_22;
	}

	public void setPref_21_22(int pref_21_22) {
		this.pref_21_22 = pref_21_22;
	}

	@Override
	public String toString() {
		return "PlaceAndPref [place_no=" + place_no + ", place_name=" + place_name + ", place_nodeno=" + place_nodeno
				+ ", place_info=" + place_info + ", place_price=" + place_price + ", place_eval_avg=" + place_eval_avg
				+ ", place_working_day=" + place_working_day + ", place_open_time=" + place_open_time
				+ ", place_close_time=" + place_close_time + ", place_category=" + place_category
				+ ", place_runtime_min=" + place_runtime_min + ", place_runtime_max=" + place_runtime_max
				+ ", place_photo_name=" + place_photo_name + ", place_type=" + place_type + ", pref_no=" + pref_no
				+ ", pref_10=" + pref_10 + ", pref_20=" + pref_20 + ", pref_30=" + pref_30 + ", pref_40=" + pref_40
				+ ", pref_male=" + pref_male + ", pref_female=" + pref_female + ", pref_conference=" + pref_conference
				+ ", pref_shopping=" + pref_shopping + ", pref_date=" + pref_date + ", pref_meal=" + pref_meal
				+ ", pref_culture=" + pref_culture + ", pref_etc=" + pref_etc + ", pref_single=" + pref_single
				+ ", pref_two=" + pref_two + ", pref_three=" + pref_three + ", pref_four=" + pref_four + ", pref_five="
				+ pref_five + ", pref_ten=" + pref_ten + ", pref_10_11=" + pref_10_11 + ", pref_11_12=" + pref_11_12
				+ ", pref_12_13=" + pref_12_13 + ", pref_13_14=" + pref_13_14 + ", pref_14_15=" + pref_14_15
				+ ", pref_15_16=" + pref_15_16 + ", pref_16_17=" + pref_16_17 + ", pref_17_18=" + pref_17_18
				+ ", pref_18_19=" + pref_18_19 + ", pref_19_20=" + pref_19_20 + ", pref_20_21=" + pref_20_21
				+ ", pref_21_22=" + pref_21_22 + "]";
	}
	
	
	
	
}