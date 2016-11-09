<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>jquery.timeline Demo</title>
		<link rel="stylesheet" type="text/css" href="css/jquery.timeline.css" />
		<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
		<script src="js/jquery.timeline.js"></script>
		<script src="js/bootstrap.js"></script>
		<style type="text/css">
			body {
				font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
				font-size: 14px;
				line-height: 1.42857143;
				color: #58666e;
				background-color: white;
				margin-left: 40%;
			}
		</style>
        <link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
	</head>
	<body>
	
	
	


	<h1 style="margin:150px auto 30px auto;">스케줄 생성하기</h1>
		<div id="element"></div>
		
		<div>
			<a href="javascript:void(0);" id="add">Add More</a>
		</div>
		
		<script>
			$(function() {
				$("#element").timeline({
					data: [
						{time: '10:00',
							color: '#555',
							css: 'success',
							content: '방문 목적 선택하기', select_name: 'purpose10'},{time: '11:00',
							color: '#00ff00',
							css: 'success',
							content: '방문 목적 선택하기', select_name: 'purpose11'},{time: '12:00',
							color: '#000',
							css: 'success',
							content: '방문 목적 선택하기', select_name: 'purpose12'},{time: '13:00',
							color: '#000',
							css: 'success',
							content: '방문 목적 선택하기', select_name: 'purpose13'}
					]
				});

				var currTime = 14;
				$("#add").click(function(){
				var time = currTime;
				if(currTime>23){
					alert("집에가씨발");
					return;
				}
						$("#element").timeline("add",
							[
								{	time: time + ":00",
									css: 'success',
									content: '방문 목적 선택하기', select_name: 'purpose'+currTime}
							]
						 );
				currTime++;
				});
				
				
				$('#myModal').on('shown.bs.modal', function () {
					  $('#myInput').focus()
					})
			});



		</script>
	</body><script type="text/javascript">

</script>

</html>