<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.contents{
   height: 70%;
   width : 70%;
    margin-top: 10%;
    margin-left: 15%;
    background-color: white;opacity:1;
    border-radius: 25px;
    opacity: 0.8;
    display: flex;
   text-align: center;
}
.parallax img{
   overflow: hidden;
   height: 100%;
   background-size: cover;
   
}

.left-box{
   display: inline-block;
   height: 70%;
   width: 50%;
   margin-top: 10%;
}
.right-box{
height: 70%;
   width: 50%;
   display: inline-block;
   margin-top: 10%;
}

</style>
</head>
<body>
<jsp:include page="header.jsp" flush="false"/>
   <div class="parallax">
      <img src="img/coex-main-another.jpg" alt="Unsplashed background img 1">
   </div>


	<div class="contents">
		<div class="left-box" style="border-right: 1px solid black;">
			<a href="answerPage.action"><h2 style="text-align: center;">
					일정 <br> 추천받기
				</h2>
				<p>추천설명하시떼</p> <!-- <img alt="image" src="img/recommend.png"> --> </a>
			<!-- <a class="ghost-button" href="#" style="margin-top: 20%;">Ghost button text</a> -->
		</div>

		<div class="right-box" style="border-left: 1px solid black;">
			<a href="#"><h2 style="text-align: center;">
					내가 <br> 직접짜기
				</h2>
				<p>직접짜라</p> <!-- <img alt="image" src="img/else.png"> --> </a>
		</div>
	</div>


<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>
<script type="text/javascript">
	$(function() {
		$("#imgDino").hover(function() {
			$(this).attr("src", "img/animated.gif");
		}, function() {
			$(this).attr("src", "img/static.JPG");
		});
	});
</script>
</body>
</html>