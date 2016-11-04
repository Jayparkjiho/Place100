package coex.util;


import java.util.Calendar;
import java.util.GregorianCalendar;

import coex.vo.Alarm;


public class AlarmClock {

	public void checkAlarm(Alarm alarm){
		Thread th = new Thread(){
			
			public void run(){
				Alarm a = new Alarm();
				a = alarm;
				int wl = 0;
				while(wl==0){
					int cycle = 0;
					for (int i = 0; i < a.getTimes().size(); i++) {
						
						while (cycle<a.getTimes().size()) {
							
							Calendar c = new GregorianCalendar();
							int hours = c.get(Calendar.HOUR);
							int mins = c.get(Calendar.MINUTE);
							String time = "" + hours + ":" + mins;
							
							if (time.equals(a.getTimes().get(i))) {
								String message = a.getPlaces().get(i) + " 에 가야할 시간입니다.";
								String number = a.getNumber();
								SendSMS ss = new SendSMS(number, message);
								break;
							}
						}
						cycle++;
					}
				}
			}
		};
		th.setPriority(Thread.MIN_PRIORITY);
		th.start();
	}

}
