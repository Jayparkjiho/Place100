<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

function closeWindow() {
	window.close();
}
</script>

<title>스케줄 번호 받기</title>
</head>
<body>

	<h4>스케줄 번호 : <s:property value="scheNo"/></h4>
	<input type="button"  value="확인" onclick="javascript:closeWindow()">
</body>
</html>
