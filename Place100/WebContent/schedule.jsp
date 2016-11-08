<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.1.0.min.js"></script>
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function(){
	$('a[title]').tooltip();
	});
	
	var placeList = '';
	var timeList = '';
	var output = '';
	var output2 = '';
	var place_name = '';
	
	$.ajax({
    	url:'getData',
    	method:'post',
    	datatype:'json',
    	success: function(response){
    		placeList = response.placeList;
    		timeList = response.timeList;
    		
    		$.each(placeList, function(index, item){
    			var place_category = item.place_category.split( '#' );
    			place_category = place_category[1];

    			if (index == 0) {
    				output += "<li class='active'>"
					output2 += "<div class='tab-pane fade in active' id="+item.place_name+">"
    			}
    			else{
    				output += "<li>"
    				output2 += "<div class='tab-pane fade' id="+item.place_name+">"
    			}
    			output +=	"<a class="+item.place_no+" href=#" +item.place_name+ " data-toggle='tab' title=" + timeList[index] + ">"  //이미지 입혀야 함
    			output +=		"<span class='round-tabs one"+index+"'>" 
    			if (place_category.length == 3) {
    				output += "<h3 class='category3'>"+place_category+"</h3>"
				} else if (place_category.length == 4) {
					output += "<h3 class='category4'>"+place_category+"</h3>"
				} else {
					place_category = place_category.substring(0,3);
					output += "<h3 class='category3'>"+place_category+"</h3>"
				}
    			output +=		"</span>"
    			output +=	"</a>"
    			output +="</li>"
    			/* $('a title').attr('src',item.place_photo_name); */
				output2 += 	"<h3 class='head text-center'>"+ item.place_name +"("+ timeList[index] + ")" +"</h3>"
				output2 += "<p class='narrow text-center'>"+ item.place_info + "</p>"
				output2 += "<p class='text-center'>"
				
				/* 가게이름 공란 제거 - 이미지 제목 때문임 */
				/* place_name = item.place_name;
				place_name = place_name.replace(/\s/gi, '');  */
				
				output2 +=	"<img class='place_photo' src='"+item.place_photo_name+"'>" 
				output2 +=  "</p>"
				output2 +=  "</div>"
    		}); 
    		
    		$('#myTab').append(output);
    		$('.tab-content').append(output2);
    		
    		
    		for (var int = 0; int < placeList.length; int++) {
    			var size = (100/placeList.length) + '%';
    			$('.nav-tabs > li').css('width', size);
			}
    	}
	});
</script>

<style type="text/css">
@import url(http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700);
/* written by riliwan balogun http://www.facebook.com/riliwan.rabo*/

span > .category3{
	font-size: 20px !important; 
	margin-top: 23px !important;
}

span > .category4{
	font-size: 16px !important;
	margin-top: 26px !important;
}

.board{
    width: 75%;
margin: 60px auto;
height: 500px;
background: #fff;
/*box-shadow: 10px 10px #ccc,-10px 20px #ddd;*/
}
.board .nav-tabs {
    position: relative;
    /* border-bottom: 0; */
    /* width: 80%; */
    margin: 40px auto;
    margin-bottom: 0;
    box-sizing: border-box;

}

.board > div.board-inner{
    background: #fafafa url(http://subtlepatterns.com/patterns/geometry2.png);
    background-size: 30%;
}

p.narrow{
    width: 60%;
    margin: 10px auto;
}

.liner{
    height: 2px;
    background: #ddd;
    position: absolute;
    width: 75%;
    margin: 0 auto;
    left: 0;
    right: 0;
    top: 50%;
    z-index: 1;
}

.nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus {
    color: #555555;
    cursor: default;
    /* background-color: #ffffff; */
    border: 0;
    border-bottom-color: transparent;
}

span.round-tabs{
    width: 70px;
    height: 70px;
    line-height: 70px;
    display: inline-block;
    border-radius: 100px;
    background: white;
    z-index: 2;
    position: absolute;
    left: 0;
    text-align: center;
    font-size: 25px;
}

span.round-tabs.one0{
    color: rgb(34, 194, 34);border: 2px solid rgb(34, 194, 34);
}

li.active span.round-tabs.one0{
    background: #fff !important;
    border: 2px solid #ddd;
    color: rgb(34, 194, 34);
}

span.round-tabs.one1{
    color: #febe29;border: 2px solid #febe29;
}

li.active span.round-tabs.one1{
    background: #fff !important;
    border: 2px solid #ddd;
    color: #febe29;
}

span.round-tabs.one2{
    color: #3e5e9a;border: 2px solid #3e5e9a;
}

li.active span.round-tabs.one2{
    background: #fff !important;
    border: 2px solid #ddd;
    color: #3e5e9a;
}

span.round-tabs.one3{
    color: #f1685e;border: 2px solid #f1685e;
}

li.active span.round-tabs.one3{
    background: #fff !important;
    border: 2px solid #ddd;
    color: #f1685e;
}

span.round-tabs.one4{
    color: #33FF33;border: 2px solid #33FF33;
}

li.active span.round-tabs.one4{
    background: #fff !important;
    border: 2px solid #ddd;
    color: #33FF33;
}

span.round-tabs.one5{
    color: #999;border: 2px solid #999;
}

li.active span.round-tabs.one5{
    background: #fff !important;
    border: 2px solid #ddd;
    color: #999;
}

.nav-tabs > li.active > a span.round-tabs{
    background: #fafafa;
}
.nav-tabs > li {
    width: 20%;
}
/*li.active:before {
    content: " ";
    position: absolute;
    left: 45%;
    opacity:0;
    margin: 0 auto;
    bottom: -2px;
    border: 10px solid transparent;
    border-bottom-color: #fff;
    z-index: 1;
    transition:0.2s ease-in-out;
}*/
.nav-tabs > li:after {
    content: " ";
    position: absolute;
    left: 45%;
   opacity:0;
    margin: 0 auto;
    bottom: 0px;
    border: 5px solid transparent;
    border-bottom-color: #ddd;
    transition:0.1s ease-in-out;
    
}
.nav-tabs > li.active:after {
    content: " ";
    position: absolute;
    left: 45%;
   opacity:1;
    margin: 0 auto;
    bottom: 0px;
    border: 10px solid transparent;
    border-bottom-color: #ddd;
    
}
.nav-tabs > li a{
   width: 70px;
   height: 70px;
   margin: 20px auto;
   border-radius: 100%;
   padding: 0;
}

.nav-tabs > li a:hover{
    background: transparent;
}

.tab-content{
}
.tab-pane{
   position: relative;
padding-top: 50px;
}
.tab-content .head{
    font-family: 'Roboto Condensed', sans-serif;
    font-size: 25px;
    text-transform: uppercase;
    padding-bottom: 10px;
}
.btn-outline-rounded{
    padding: 10px 40px;
    margin: 20px 0;
    border: 2px solid transparent;
    border-radius: 25px;
}

.btn.green{
    background-color:#5cb85c;
    /*border: 2px solid #5cb85c;*/
    color: #ffffff;
}

@media( max-width : 585px ){
    
    .board {
width: 90%;
height:auto !important;
}
    span.round-tabs {
        font-size:16px;
width: 50px;
height: 50px;
line-height: 50px;
    }
    .tab-content .head{
        font-size:20px;
        }
    .nav-tabs > li a {
width: 50px;
height: 50px;
line-height:50px;
}

.nav-tabs > li.active:after {
content: " ";
position: absolute;
left: 35%;
}

.btn-outline-rounded {
    padding:12px 20px;
    }
}

.place_photo{
	max-height: 137px; 
	max-width: 210px;
}

.navbar-nav.navbar-right .btn{
    position: relative;
    z-index: 2;
    padding: 4px 20px;
    margin: 10px auto;
    transition: transform 0.3s;
}

.btn.btn-outline {
    background-color: transparent;
}
.btn.btn-circle {
    border-radius: 50px;
}
.board-footer{
	top: 71%;
    left: 50%;
    position: absolute;
}

</style>
</head>
<body>
<section style="background:#efefe9;">
	<div class="container">
		<div class="row">
			<div class="board">
                <!-- <h2>Welcome to IGHALO!<sup>™</sup></h2>-->
				<!-- 스케줄 시간별 순서 -->
				<div class="board-inner">
					<ul class="nav nav-tabs" id="myTab">
						<div class="liner"></div>
						

					</ul>
				</div>

				<!-- 각 장소별 설명 -->
				<div class="tab-content">					
					<div class="clearfix"></div>
				</div>
			
				<!-- 보드 footer 이동하는 버튼 -->
				<div class="board-footer">
					<a class="btn btn-default btn-outline btn-circle collapsed" href="main.action" >메인화면 돌아가기</a>
					<a class="btn btn-default btn-outline btn-circle collapsed" id="Sms_btn">일정 알림</a>
				</div>
			</div>
			
		</div>
	</div>
</section>
<!--  -->

<form action="sendSms" method="post" id="sendSmsForm" name="sendSmsForm">
	<input type="hidden" id="phone_num" name="phone_num">
</form>


<!--  -->
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript">

//sms보내기 버튼 클릭시에
var childWindow;
$('#Sms_btn').on('click',function(){
	childWindow = window.open('pop.action','new','resizable=no scrollbars=yes top=300 left=500 width=300 height=180');
});

function sendsms() {
	var phone_num = childWindow.document.getElementById("phone_num").value;
	document.getElementById('phone_num').value = phone_num;
	document.sendSmsForm.submit();
}
</script>


                   
                   

</body>
</html>