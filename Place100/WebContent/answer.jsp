<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var purpose='';
	var answer = new Object();
	var actionList='';
	
	$('input[class=radio-button]').click(function(){
		purpose = $(this).val();
		var data = {"answer.answer_purpose_no":0};
		if (purpose == '0') {
			$.ajax({
				url:'getExhibitionList',
				method:'post',
				data:data,
				dataType:'json',
				success:function(response){
					answer = response.answer;
					actionList = response.actionList;
					console.log(actionList);
					var $currentstep = $.global.slider.getStep();
					var $nextstep = parseInt($currentstep) + 1; $.global.slider.setStep($nextstep);
					  
				}
			});//ajax 종료
		}// if문 종료
		else{
			answer.answer_purpose_no=purpose;
			var $currentstep = $.global.slider.getStep();
		    var $nextstep = parseInt($currentstep) + 1; $.global.slider.setStep($nextstep);
		}
	});//클릭 function 종료
	console.log(answer);
	
});
</script>
<title>Answer</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/style2.css">
</head>
<body>

<jsp:include page="header.jsp" flush="false"/>

	<!-- <div id="console"></div> -->

	<div class="arrow" id="right-arrow">></div>
	<div class="arrow" id="left-arrow"><</div>

	<div id="image-carousel" class="dragdealer active">

		<ul id="slides" class="handle"
			style="-webkit-perspective: 1000px; -webkit-backface-visibility: hidden; -webkit-transform: translateX(200px);">

			<li class="slide img1"
				style="background-image: url('img/background.jpg');">
				
				<div class="table">
               <div class="container">
               <div>
                  <div class="radio-tile-group">

                     <div class="input-container">
                        <input id="walk" class="radio-button" type="radio" name="radio" value="0"/>
                        <div class="radio-tile">
                           <div class="icon walk-icon">
                              <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24"
                                 xmlns="http://www.w3.org/2000/svg"> <path
                                 d="M0 0h24v24H0z" fill="none" /> <path
                                 d="M13.5 5.5c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zM9.8 8.9L7 23h2.1l1.8-8 2.1 2v6h2v-7.5l-2.1-2 .6-3C14.8 12 16.8 13 19 13v-2c-1.9 0-3.5-1-4.3-2.4l-1-1.6c-.4-.6-1-1-1.7-1-.3 0-.5.1-.8.1L6 8.3V13h2V9.6l1.8-.7" />
                              </svg>
                           </div>
                           <label for="walk" class="radio-tile-label">Walk</label>
                        </div>
                     </div>

                     <div class="input-container">
                        <input id="bike" class="radio-button" type="radio" name="radio" value="1" />
                        <div class="radio-tile">
                           <div class="icon bike-icon">
                              <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24"
                                 xmlns="http://www.w3.org/2000/svg"> <path
                                 d="M0 0h24v24H0z" fill="none" /> <path
                                 d="M15.5 5.5c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zM5 12c-2.8 0-5 2.2-5 5s2.2 5 5 5 5-2.2 5-5-2.2-5-5-5zm0 8.5c-1.9 0-3.5-1.6-3.5-3.5s1.6-3.5 3.5-3.5 3.5 1.6 3.5 3.5-1.6 3.5-3.5 3.5zm5.8-10l2.4-2.4.8.8c1.3 1.3 3 2.1 5.1 2.1V9c-1.5 0-2.7-.6-3.6-1.5l-1.9-1.9c-.5-.4-1-.6-1.6-.6s-1.1.2-1.4.6L7.8 8.4c-.4.4-.6.9-.6 1.4 0 .6.2 1.1.6 1.4L11 14v5h2v-6.2l-2.2-2.3zM19 12c-2.8 0-5 2.2-5 5s2.2 5 5 5 5-2.2 5-5-2.2-5-5-5zm0 8.5c-1.9 0-3.5-1.6-3.5-3.5s1.6-3.5 3.5-3.5 3.5 1.6 3.5 3.5-1.6 3.5-3.5 3.5z" />
                              </svg>
                           </div>
                           <label for="bike" class="radio-tile-label">Bike</label>
                        </div>
                     </div>

                     <div class="input-container">
                        <input id="drive" class="radio-button" type="radio" name="radio" value="2" />
                        <div class="radio-tile">
                           <div class="icon car-icon">
                              <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24"
                                 xmlns="http://www.w3.org/2000/svg"> <path
                                 d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z" />
                              <path d="M0 0h24v24H0z" fill="none" /> </svg>
                           </div>
                           <label for="drive" class="radio-tile-label">Drive</label>
                        </div>
                     </div>
                  </div>   
                  <div class="radio-tile-group">   
                     <div class="input-container">
                        <input id="fly" class="radio-button" type="radio" name="radio" value="3" />
                        <div class="radio-tile">
                           <div class="icon fly-icon">
                              <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24"
                                 xmlns="http://www.w3.org/2000/svg"> <path d="M10.18 9" />
                              <path
                                 d="M21 16v-2l-8-5V3.5c0-.83-.67-1.5-1.5-1.5S10 2.67 10 3.5V9l-8 5v2l8-2.5V19l-2 1.5V22l3.5-1 3.5 1v-1.5L13 19v-5.5l8 2.5z" />
                              <path d="M0 0h24v24H0z" fill="none" /> </svg>
                           </div>
                           <label for="fly" class="radio-tile-label">Fly</label>
                        </div>
                     </div>
                     
                     <div class="input-container">
                        <input id="drive" class="radio-button" type="radio" name="radio" value="4" />
                        <div class="radio-tile">
                           <div class="icon car-icon">
                              <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24"
                                 xmlns="http://www.w3.org/2000/svg"> <path
                                 d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z" />
                              <path d="M0 0h24v24H0z" fill="none" /> </svg>
                           </div>
                           <label for="drive" class="radio-tile-label">Drive</label>
                        </div>
                     </div>
                     
                     <div class="input-container">
                        <input id="drive" class="radio-button" type="radio" name="radio" value="5" />
                        <div class="radio-tile">
                           <div class="icon car-icon">
                              <svg fill="#000000" height="24" viewBox="0 0 24 24" width="24"
                                 xmlns="http://www.w3.org/2000/svg"> <path
                                 d="M18.92 6.01C18.72 5.42 18.16 5 17.5 5h-11c-.66 0-1.21.42-1.42 1.01L3 12v8c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-1h12v1c0 .55.45 1 1 1h1c.55 0 1-.45 1-1v-8l-2.08-5.99zM6.5 16c-.83 0-1.5-.67-1.5-1.5S5.67 13 6.5 13s1.5.67 1.5 1.5S7.33 16 6.5 16zm11 0c-.83 0-1.5-.67-1.5-1.5s.67-1.5 1.5-1.5 1.5.67 1.5 1.5-.67 1.5-1.5 1.5zM5 11l1.5-4.5h11L19 11H5z" />
                              <path d="M0 0h24v24H0z" fill="none" /> </svg>
                           </div>
                           <label for="drive" class="radio-tile-label">Drive</label>
                        </div>
                     </div>

                  </div>
               </div>   
               </div>
            </div>
			</li>

			<li class="slide img2"
				style="background-image: url('img/background.jpg');">
				<div class="info">
					<!-- <p class="title">Mercedes-Benz 300SL</p>
					<p class="description">
						<strong>1956</strong> —&#8202;2996cc, 212-222hp
					</p> -->
					<h1>2page</h1>
				</div>
				
			</li>

			<li class="slide img3"
				style="background-image: url('img/background.jpg');">
				<div class="info">
					<!-- <p class="title">Jaguar E-Type</p>
					<p class="description">
						<strong>1966</strong> —&#8202;3.8L, 265bhp
					</p> -->
					<h1>3page</h1>
				</div>
			</li>

			<li class="slide img4"
				style="background-image: url('img/background.jpg');">
				<div class="info">
					<!-- <p class="title">Maserati A6</p>
					<p class="description">
						<strong>1950</strong> —&#8202;2L, 120bhp
					</p> -->
					<h1>4page</h1>
				</div> 
			</li>

			<li class="slide img5"
				style="background-image: url('img/background.jpg');">
				<div class="info">
					<!-- <p class="title">Maserati A6</p>
					<p class="description">
						<strong>1950</strong> —&#8202;2L, 120bhp
					</p> -->
					<h1>5page</h1>
				</div>
			</li>


		</ul>
	</div>
	
	
<script src='js/jquery-3.1.0.min.js'></script>
<script src='js/dragdealer.js'></script>
<script src="js/index.js"></script>

</body>
</html>