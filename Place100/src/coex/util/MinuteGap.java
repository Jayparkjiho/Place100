package coex.util;
import java.util.Date;

public class MinuteGap {
	public long timeMinuteDiff(int startTime, int startMin, int endTime, int endMin){
				Date d1 = new Date(2011,01,01,startTime,startMin);
				Date d2 = new Date(2011,01,01,endTime,endMin);
				long diffMs = d2.getTime() - d1.getTime();
				long diffSec = diffMs / 1000;
				long min = diffSec / 60;
				System.out.println("The difference is "+min+" minutes");
			return min;	
	}
	public static void main(String[] args) {
		System.out.println(new MinuteGap().timeMinuteDiff(10,00,22,00)/15);
		System.out.println(new MinuteGap().timeMinuteDiff(9,30,21,30)/15);
	}
}
