package coex.util;


import java.io.IOException;

public class SendSMS {
	
	public SendSMS(String calNum, String message){
		SMS sms = new SMS();
		sms.appversion("TEST/1.0");
		sms.charset("utf8");
		sms.setuser("wlgh159", "qwg90188");

		String number = calNum; // 받을 사람 폰번호

		SmsMessagePdu pdu = new SmsMessagePdu();
		pdu.type = "SMS";
		pdu.destinationAddress = number;
		pdu.scAddress = "01094218900"; // 발신자 번호
		pdu.text = message; // 보낼 메세지 내용
		sms.add(pdu);

		try {
			sms.connect();
			sms.send();
			sms.disconnect();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		sms.printr();
		sms.emptyall();
	}
	

}
