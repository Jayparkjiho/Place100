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
			<a href="javascript:void(0);" id="add">Add More</a><br><br>
			<a id="scheduleGo">스케줄 생성하기</a>
		</div>
		
		<script>
			$(function() {
				$("#element").timeline({
					data: [
						{time: '10:00',
							color: '#555',
							css: 'success',
							content: '방문 목적 선택하기', select_name: 'purpose10', span_id: 'time10'},{time: '11:00',
							color: '#00ff00',
							css: 'success',
							content: '방문 목적 선택하기', select_name: 'purpose11', span_id: 'time11'},{time: '12:00',
							color: '#000',
							css: 'success',
							content: '방문 목적 선택하기', select_name: 'purpose12', span_id: 'time12'},{time: '13:00',
							color: '#000',
							css: 'success',
							content: '방문 목적 선택하기', select_name: 'purpose13', span_id: 'time13'}
					]
				});

				var currTime = 14;
				$("#add").click(function(){
				var time = currTime;
				if(currTime>23){
					return;
				}
						$("#element").timeline("add",
							[
								{	time: time + ":00",
									css: 'success',
									content: '방문 목적 선택하기', select_name: 'purpose'+currTime, span_id: 'time'+currTime}
							]
						 );
				currTime++;
				});
				
				var placeList = [];
				var timeList = [];
				
				$("#getData").on("click", function(){
					for (var i = 10; i < 23; i++) {
						var select_value = $("#purpose"+i).val();
						var time_value = $("#time"+i).html();
						if (select_value != 9) {
							placeList.push(select_value);
							timeList.push(time_value);
						}//if close
					}//for close
					console.log(placeList);
					console.log(timeList);
				});
				
				var childWindow;
				$('input[type=text]').on('click',function(){
					var name = $(this).attr('id');
					childWindow = window.open('storeSearch.action?name='+name,'new','resizable=no scrollbars=yes top=300 left=500 width=620 height=400');
				});
			
				$("#scheduleGo").on("click", function () {
					var place_no = 12;
					var url = "searchSchedule.action?search_text=" + place_no;    
			        $(location).attr('href',url);
				})
				
			});

			function setChildValue(parents, name){
			      document.getElementById(parents).value = name;
			}
			
			
		</script>
	</body>

</html>