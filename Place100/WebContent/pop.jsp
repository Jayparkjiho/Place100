<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

function sendSms() {
	opener.sendsms();
	window.close();
}
</script>
<title>문자알람서비스</title>
</head>
<body>
<form action="sendSms" method="post" id="sendSmsForm" name="sendSmsForm">
	<h4>알림 서비스를 받을 전화번호를 입력해 주세요.</h4>
	<input type="text" id="phone_num" name="phone_num">
	<input type="button"  value="확인" onclick="sendSms()">
</form>
</body>
</html>