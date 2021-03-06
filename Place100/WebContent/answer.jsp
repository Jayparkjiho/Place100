<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var purpose='';
	var answer = {};
	var actionList='';
	var date = '';
	
	var answer_purpose_no = '';
	var answer_date='';
	var answer_start_time='';
	var answer_end_time='';
	var answer_age='';
	var answer_sex='';
	var answer_head_count='';
	
	$('input[class=radio-button]').click(function(){
		purpose = $(this).val();
		if (purpose == 0) {
			var data = {"answer.answer_purpose_no":0};
			$.ajax({
				url:'getExhibitionList',
				method:'post',
				data:data,
				dataType:'json',
				success:function(response){
					answer_purpose_no=purpose;
					actionList = response.actionList;
					var $currentstep = $.global.slider.getStep();
					var $nextstep = parseInt($currentstep) + 1; $.global.slider.setStep($nextstep);
				}
			});//ajax 종료
		}// if문 종료
		
		else{
			answer_purpose_no=purpose;
			var $currentstep = $.global.slider.getStep();
		    var $nextstep = parseInt($currentstep) + 1; $.global.slider.setStep($nextstep);
		}
	});//클릭 function 종료
	
	
	//datepicker
	//----------variables----------//

	var day = "";
	var month = "";
	var year = "";
	var currentDate = "";
	var monthStartDay = "";

	var monthTextArray = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

	var dayTextArray = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

	//----------functions----------//

	function getMonthInfo(year, month) {

	  //use current month to find number of days in month
	  //i dont know why i have to add 1 to month
	  var startDate = new Date(year, month + 1, 0);
	  var monthLength = startDate.getDate();
	  
	  var startDate = new Date(year, month, 1);
	  var monthStartDay = startDate.getDay();
	  
	  return [monthLength, monthStartDay];
	  
	}

	function drawCal(monthInfo) {

	  var daysInMonth = monthInfo[0];
	  var monthStartDays = monthInfo[1];
	  
	  //clear cal tbody
	  $("#cal").empty();
	  $("#cal").append("<tr><td>sun</td><td>mon</td><td>tue</td><td>wed</td><td>thur</td><td>fri</td><td>sat</td>");
	  
	  //create empty row, append to to tbody
	  var $rowOut = $("<tr></tr>");
	  $("#cal").append($rowOut);

	  //shift first row by month start date
	  for (var i = 1; i <= monthStartDays; i++) {
	    var $day = "<td></td>";
	    $("#cal tr:last").append($day);
	  }
	  
	  //for each day, append a td to the row
	  for (var i = 1; i <= daysInMonth; i++) {
	    var $day = "<td class='eachDay'><a class='dayDay'>" + (i) + "</a></td>";
	    $("#cal tr:last").append($day);

	    //if day 7 (w/shift), append row contaning 7 days to tbody and clear row
	    if ((i + monthStartDays) % 7 == 0 & i != 0) {
	      $("#cal").append($rowOut);
	      $rowOut = "<tr></tr>";
	      $("#cal").append($rowOut);
	    }
	  }
	}

	//----------wiring----------//

	$(".button_left").click(function() {

	  month--;

	  if (month < 0) {
	    year--;
	    month = 11;
	  }

	  //left button click
	  $(".cal_head span").text(monthTextArray[month] + " " + year);
	  drawCal(getMonthInfo(year, month));

	});

	//right button click
	$(".button_right").click(function() {

	  month++;

	  if (month > 11) {
	    year++;
	    month = 0;
	  }

	  $(".cal_head span").text(monthTextArray[month] + " " + year);
	  drawCal(getMonthInfo(year, month));

	});

	$("#cal").on("click","a",function(e) {
	  $("td > a").css('color', '#039be5');
	  $(this).css('color', 'red');
		
	  e.preventDefault();
	  //$(this).parent().addClass("circle");
	  
	  
	  var date = year+"-"+(month+1) + "-" + $(this).html();
	  answer_date = date;
	  
	  
	  var outputDate = monthTextArray[month] + " " + $(this).html() +", " + year;
	  console.log(outputDate);
	  $("#outputText").text(outputDate);
	  
	});

	//----------run----------//

	//get current month and year
	currentDate = new Date();
	year = currentDate.getFullYear();
	month = currentDate.getMonth();

	//get text month name from month number and write to span
	$(".cal_head span").text(monthTextArray[month] + " " + year);

	//inital calander draw based on current month
	drawCal(getMonthInfo(year, month));
	
	//시간 시간 시간
	var start_time = '';
	var end_time = '';
	$("input[name='answer.start_time']").on("click", function () {
		start_time = $(this).val();
		answer_start_time = start_time;
	});
	
	$("input[name='answer.end_time']").on("click", function () {
		end_time = $(this).val();
		if (start_time >= end_time) {
			alert("시작시간보다 빠릅니다.");
			$('input:radio[name="answer.end_time"][value="10"]').prop('checked', true);
			$('label[for="e10"]').prop('checked', true);
		}else{
		answer_end_time = end_time;
		}
	});
	
	//나이 answer radio click function
	$("input[name='credit-card']").on("click", function () {
		answer_age = $(this).val();
	});
	
	//성별 answer  radio click function
	$("input[name='gend']").on("click", function () {
		answer_sex = $(this).val();
	});
	
	//사람수 answer radio click funtion
	$("input[name='many']").on("click", function () {
		answer_head_count = $(this).val();
		if(answer_head_count == "1"){
		       $("#con").show();
		       $("#default").hide();
		       $("#con-two").hide();
		       $("#con-three").hide();
		       $("#con-five").hide();
		       $("#con-ten").hide();
		}else if(answer_head_count == "2"){
		       $("#default").hide();
		       $("#con").hide();
		       $("#con-three").hide();
		       $("#con-five").hide();
		       $("#con-ten").hide();
		       $("#con-two").show();
		}else if (answer_head_count == "3") {
		       $("#default").hide();
		       $("#con").hide();
		       $("#con-two").hide();
		       $("#con-five").hide();
		       $("#con-ten").hide();
		       $("#con-three").show();
		}else if (answer_head_count == "5") {
		       $("#default").hide();
		       $("#con").hide();
		       $("#con-two").hide();
		       $("#con-three").hide();
		       $("#con-ten").hide();
		       $("#con-five").show();
		}else if (answer_head_count == "10") {
		       $("#default").hide();
		       $("#con").hide();
		       $("#con-two").hide();
		       $("#con-three").hide();
		       $("#con-five").hide();
		       $("#con-ten").show();
	    }
	});
	
	//버튼입력시 answer을 같이 action으로 넘겨주는 jquery
	$('#btn_answer_ok').on('click',function(){
		/* var data = JSON.stringify(answer); */
		/* var jdata = JSON.parse(data);
		alert(jdata); */
		$.ajax({
			url:'insertAnswer',
			method:'post',
			data: {	"answer.answer_purpose_no":answer_purpose_no,
					"answer.answer_date" :answer_date,
					"answer.answer_start_time":answer_start_time,
					"answer.answer_end_time":answer_end_time,
					"answer.answer_age":parseInt(answer_age,10),
					"answer.answer_sex":parseInt(answer_sex,10),
					"answer.answer_head_count":parseInt(answer_head_count,10)
					},
			dataType:'json',
			success:function(response){
				location.href="schedule.action";
			}
		});//ajax 종료
	});
	
	//버튼입력시 answer 정보를 담아 스케줄 생성페이지로 넘어가는 메소드
	$('#btn_myself-schedule').on('click',function(){
		/* var data = JSON.stringify(answer); */
		/* var jdata = JSON.parse(data);
		alert(jdata); */
		$.ajax({
			url:'myself-schedule',
			method:'post',
			data: {	"answer.answer_purpose_no":answer_purpose_no,
					"answer.answer_date" :answer_date,
					"answer.answer_start_time":answer_start_time,
					"answer.answer_end_time":answer_end_time,
					"answer.answer_age":parseInt(answer_age,10),
					"answer.answer_sex":parseInt(answer_sex,10),
					"answer.answer_head_count":parseInt(answer_head_count,10)
					},
			dataType:'json',
			success:function(response){
				location.href="myself-schedule.action";
			}
		});//ajax 종료
	});
	
});

</script>
<style type="text/css">
.ghost-button {
  display: inline-block;
  width: 200px;
  padding: 8px;
  color: black;
  border: 2px solid black;
  border-radius : 20px;
  text-align: center;
  outline: none;
  text-decoration: none;
  transition: background-color 0.2s ease-out,
              color 0.2s ease-out;
}
.ghost-button:hover,
.ghost-button:active {
  background-color: #039be5;
  color: white;
  transition: background-color 0.3s ease-in,
              color 0.3s ease-in;
}
html { 
  background: url("img/test3.jpg") no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover; 
  -o-background-size: cover; 
  background-size: cover; 
  background-color: rgba(255,255,255,0.5);
}
.content{
	margin-top: 12%;
	text-align: center;
/*    height: 70%;
   width : 70%; */
   /*  margin-top: 10%;
    margin-left: 15%; */
    background-color: white;
    border-radius: 25px;
    opacity: 0.8;
  /*   display: flex; */
}

</style>
<title>Answer</title>

</head>

<body>

<jsp:include page="header.jsp" flush="false"/>

	<!-- <div id="console"></div> -->

	<div class="arrow" id="right-arrow"><h1>></h1></div>
	<div class="arrow" id="left-arrow"><h1><</h1></div>

	<div id="image-carousel" class="dragdealer active">

		<ul id="slides" class="handle"
			style="-webkit-perspective: 1000px; -webkit-backface-visibility: hidden; -webkit-transform: translateX(200px);">

			<!-- 1page -->
			<li class="slide img1">
				<div class="table">
               		<div class="container">
               		<div class="content" style="width: 60%;margin-left: 20%;">
               		<h1 >1단계 : 코엑스 방문 목적을 알려주세요!</h1><br>
							<div class="radio-tile-group">

								<div class="input-container">
									<input id="walk" class="radio-button" type="radio" name="radio"
										value="0" />
									<div class="radio-tile">
										<div class="icon walk-icon">
										<img src="img/exhibi.png" width="80" height="80">
										</div>
										<label for="exhibition" class="radio-tile-label">전시회</label>
									</div>
								</div>

								<div class="input-container">
									<input id="bike" class="radio-button" type="radio" name="radio"
										value="1" />
									<div class="radio-tile">
										<div class="icon bike-icon">
										<img src="img/food.png" width="80" height="80">
										</div>
										<label for="restaurant" class="radio-tile-label">식당</label>
									</div>
								</div>

								<div class="input-container">
									<input id="drive" class="radio-button" type="radio"
										name="radio" value="2" />
									<div class="radio-tile">
										<div class="icon car-icon">
										<img src="img/shop.png" width="80" height="80">
										</div>
										<label for="shopping" class="radio-tile-label">쇼핑</label>
									</div>
								</div>
							</div>

							<div class="radio-tile-group">
								<div class="input-container">
									<input id="fly" class="radio-button" type="radio" name="radio" value="3" />
									<div class="radio-tile">
										<div class="icon fly-icon">
										<img src="img/movie.png" width="80" height="80">
										</div>
										<label for="movie" class="radio-tile-label">영화</label>
									</div>
								</div>

								<div class="input-container">
									<input id="drive" class="radio-button" type="radio"
										name="radio" value="4" />
									<div class="radio-tile">
										<div class="icon car-icon">
											<img src="img/date.png" width="80" height="80">
										</div>
										<label for="lover" class="radio-tile-label">데이트</label>
									</div>
								</div>

								<div class="input-container">
									<input id="drive" class="radio-button" type="radio"
										name="radio" value="5" />
									<div class="radio-tile">
										<div class="icon car-icon">
											<img src="img/guitar.png" width="80" height="80">
										</div>
										<label for="another" class="radio-tile-label">기타</label>
									</div>
								</div>

							</div>
						</div>   
               </div>
            </div>
			</li>
			
			<!-- 2page -->
			<li class="slide img2" >
		
				<div class="calander noselect" style="margin-top: 10%;">
				<div class="content">
					<h1>2단계 : 방문 날짜를 알려주세요!</h1><br>
				
					<div class="cal_head paper-shadow-top-z-2">

						<button class="button_left">
							<i class="material-icons"><</i>
						</button>

						<span id="month_label">Month</span>

						<button class="button_right">
							<i class="material-icons">></i>
						</button>

					</div>


					<div class="cal_body paper-shadow-bottom-z-1">

						<table style="width: 100%">
							<tbody id="cal">
							</tbody>
						</table>
					</div>

					<div class="cal_output paper-shadow-top-z-1">
						<span id="outputText"></span>
					</div>
					</div>
				</div>
			</li>
			
			<!-- 3page -->
			<li class="slide img3">
				<div class="content" style="width: 80%;margin: auto; height: 30%;">
				<div class="timetime" style="margin: 12%; width: 100%;text-align: justify;">
				<h1 >3단계 : 방문시간을 알려주세요</h1><br>
				<div class="spaceEmOut">
					<fieldset class="switch switch-five">
						<legend>시작시간</legend>
						<input id="s10" name="answer.start_time" value="10:00" type="radio" checked /> <label for="s10">10시</label>
						<input id="s11" name="answer.start_time" value="11:00" type="radio" /> <label for="s11">11시</label>
						<input id="s12" name="answer.start_time" value="12:00" type="radio" /> <label for="s12">12시</label>
						<input id="s13" name="answer.start_time" value="13:00" type="radio" /> <label for="s13">13시</label>
						<input id="s14" name="answer.start_time" value="14:00" type="radio" /> <label for="s14">14시</label>
						<input id="s15" name="answer.start_time" value="15:00" type="radio" /> <label for="s15">15시</label>
						<input id="s16" name="answer.start_time" value="16:00" type="radio" /> <label for="s16">16시</label>
						<input id="s17" name="answer.start_time" value="17:00" type="radio" /> <label for="s17">17시</label>
						<input id="s18" name="answer.start_time" value="18:00" type="radio" /> <label for="s18">18시</label>
						<input id="s19" name="answer.start_time" value="19:00" type="radio" /> <label for="s19">19시</label>
						<input id="s20" name="answer.start_time" value="20:00" type="radio" /> <label for="s20">20시</label>
						<input id="s21" name="answer.start_time" value="21:00" type="radio" /> <label for="s21">21시</label>
						<input id="s22" name="answer.start_time" value="22:00" type="radio" /> <label for="s22">22시</label>
						<span class="switch-button"></span>
					</fieldset>
				</div>
				<div class="spaceEmOut" style="margin-top: 10%;">
					<fieldset class="switch switch-five2">
						<legend>종료시간</legend>
						<input id="e10" name="answer.end_time" value="10:00" type="radio" checked /> <label for="e10">10시</label>
						<input id="e11" name="answer.end_time" value="11:00" type="radio" /> <label for="e11">11시</label>
						<input id="e12" name="answer.end_time" value="12:00" type="radio" /> <label for="e12">12시</label>
						<input id="e13" name="answer.end_time" value="13:00" type="radio" /> <label for="e13">13시</label>
						<input id="e14" name="answer.end_time" value="14:00" type="radio" /> <label for="e14">14시</label>
						<input id="e15" name="answer.end_time" value="15:00" type="radio" /> <label for="e15">15시</label>
						<input id="e16" name="answer.end_time" value="16:00" type="radio" /> <label for="e16">16시</label>
						<input id="e17" name="answer.end_time" value="17:00" type="radio" /> <label for="e17">17시</label>
						<input id="e18" name="answer.end_time" value="18:00" type="radio" /> <label for="e18">18시</label>
						<input id="e19" name="answer.end_time" value="19:00" type="radio" /> <label for="e19">19시</label>
						<input id="e20" name="answer.end_time" value="20:00" type="radio" /> <label for="e20">20시</label>
						<input id="e21" name="answer.end_time" value="21:00" type="radio" /> <label for="e21">21시</label>
						<input id="e22" name="answer.end_time" value="22:00" type="radio" /> <label for="e22">22시</label>
						<span class="switch-button"></span>
					</fieldset>
				</div>
				</div>
				</div>
			<!-- 	<div class="timetime" style="margin: 12%; width: 100%;">
				</div> -->
			</li>

			<!-- 4page -->
			<li class="slide img4 ">
				<div class="agender-many" style="margin: 11%;">
				<div class="content">
					<h1>4단계 : 당신의 나이 성별 그리고 인원을 알려주세요!</h1><br>
					<div class="cc-selector">
						<input id="one" type="radio" name="credit-card" value="10" /> <label
							class="drinkcard-cc one" for="one"></label> <input id="two"
							type="radio" name="credit-card" value="20" /> <label
							class="drinkcard-cc two" for="two"></label> <input id="three"
							type="radio" name="credit-card" value="30" /> <label
							class="drinkcard-cc three" for="three"></label> <input id="four"
							type="radio" name="credit-card" value="40" /> <label
							class="drinkcard-cc four" for="four"></label>
					</div>
					<div class="gender">
						<input id="male" type="radio" name="gend" value="0" /> <label
							class="drinkgender-gd male" for="male"></label> <input
							id="female" type="radio" name="gend" value="1" /> <label
							class="drinkgender-gd female" for="female"></label> <input
							id="both" type="radio" name="gend" value="2" /> <label
							class="drinkgender-gd both" for="both"></label>
					</div>

					<div class="how-many">
						<!-- 인원수 -->
						<input id="hitori" type="radio" name="many" value="1"/> 
							<label class="drinkmany-pp hitori" for="hitori"></label> 
						
						<input id="hutari" type="radio" name="many" value="2" /> 
							<label class="drinkmany-pp hutari" for="hutari"></label> 
						
						<input id="san" type="radio" name="many" value="3" />
							<label class="drinkmany-pp san" for="san"></label> 
						
						<input id="go" type="radio" name="many" value="5" /> 
							<label class="drinkmany-pp go" for="go" style="size: 100%"></label> 
						
						<input id="ten" type="radio" name="many" value="10" /> 
							<label class="drinkmany-pp ten" for="ten"></label>
					</div>
					<div class="many-img">
						<div id="default" style="display: block;">
							<img alt="기본" src="img/many-default.png" style="width: 60%;">
						</div>
						<div id="con" style="display: none">
							<img alt="1명" src="img/many-one.png" style="width: 60%;">
						</div>
						<div id="con-two" style="display: none">
							<img alt="2명" src="img/many-two.png" style="width: 60%;">
						</div>
						<div id="con-three" style="display: none;">
							<img alt="3명" src="img/many-three.png" style="width: 60%;">
						</div>
						<div id="con-five" style="display: none">
							<img alt="5명" src="img/many-five.png" style="width: 60%;">
						</div>
						<div id="con-ten" style="display: none">
							<img alt="10명" src="img/many-ten.png" style="width: 60%;">
						</div>
					</div>
					
					
					<div class="row center">
						<s:if test="schtype==0">
       						<a class="ghost-button" id="btn_answer_ok">제출하기</a>
						</s:if>
						<s:else>
							<a class="ghost-button" id="btn_myself-schedule">다음화면</a>
						</s:else>
   					</div>
   					</div>
				</div>

			</li>

			<!-- 추가질문 여분 슬라이드 -->
			<!-- <li class="slide img5"
				style="background-image: url('img/background.jpg');">
				<div class="info">
					
					<h1>5page</h1>
				</div>
			</li> -->


		</ul>
	</div>
	
	
<script src='js/jquery-3.1.0.min.js'></script>
<script src='js/dragdealer.js'></script>
<script src="js/index.js"></script>

</body>
</html>