package coex.util;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;

import coex.vo.Alarm;


public class AlarmClock {

	public void checkAlarm(Alarm alarm){
		Thread th = new Thread(){//새 쓰레드
			
			public void run(){//쓰레드 실행
				System.out.println("thread돌아갑니다");
				Alarm a = alarm; //입력받은 알람 객체  전화번호, 날짜, 시간목록, 장소가 들어있다
				int wl = 0;//와일문 반복 제어를 위한 변수
				Date date1 = new Date();
				date1 = DateUtils.addSeconds(date1, 10);
				while(wl==0){
					int cycle = 0;
					for (int i = 0; i < a.getTimes().size(); i++) {//스케줄 내의 모든 시간단위로 돌면서

						while (cycle<a.getTimes().size()) {//사이클이 타임 사이즈보다 커질때까지 반복하면서
						
							Calendar c = new GregorianCalendar();//새 달력 객체 생성
							int hours = c.get(Calendar.HOUR);//현재시간 받기
							int mins = c.get(Calendar.MINUTE);//현재 분 받기
							String time = "" + hours + ":" + mins;//현재시간
							
							//테스트용 (10초 뒤에 모든 일정을 문자로 보낸다)
							/*							//데이트 2개 만들고 하나는 1분 추가
							Date currDate = new Date();
							System.out.println(date1.toString());
							System.out.println(currDate.toString());
							
							if(currDate.compareTo(date1)>0){
								System.out.println();
								String message = a.getPlaces().get(i) + " 에 가야할 시간입니다.";
								String number = a.getNumber();
								SendSMS ss = new SendSMS(number, message);
								break;
							}*/
							try {
								this.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							if (time.equals(a.getTimes().get(i))) {//만약 현재시간과 새로받은 시간이 같다면
								String message = a.getPlaces().get(i) + "에 가야할 시간입니다.";
								String number = a.getNumber();
								SendSMS ss = new SendSMS(number, message);
								/*System.out.println(number + "," + message);*/
								break;
							}
						}
						cycle++;
						wl = wl + 1;
					}
				}
			}
		};
		th.setPriority(Thread.MIN_PRIORITY);
		th.start();
	}

}
