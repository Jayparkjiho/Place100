<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.contents{
   height: 78%;
   width : 70%;
    margin-top: 5%;
    margin-left: 15%;
   background-color: white;opacity:1;
    border-radius: 25px;
   background:rgba(255,255,255,.7);
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
.sett{
opacity: 1;
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
			<a href="answerPage.action?schtype=0"><h1 style="text-align: center;">
					일정 <br> 추천받기
				</h1>
				<br>
				<div class="sett">
				<img alt="qna" src="img/flow.gif" style="width: 80%; opacity: 1;">
				</div>
				<p>4단계의 간단한<br> 
				질문답변을 통해<br>
				나를 위한 추천스케줄을<br>
				받아보세요.</p> <!-- <img alt="image" src="img/recommend.png"> --> </a>
			<!-- <a class="ghost-button" href="#" style="margin-top: 20%;">Ghost button text</a> -->
		</div>

		<div class="right-box" style="border-left: 1px solid black;">
			<a href="answerPage.action?schtype=1"><h1 style="text-align: center;">
					내가 <br> 직접짜기
				</h1>
				<br>
				<div class="sett">
				<img alt="직접" src="img/self.gif" style="width: 80%; opacity: 1;"><br>
				</div>
				<p>나만의 코엑스 <br>
				스케줄을 만들고<br> 
				사람들과 공유해보세요.</p> <!-- <img alt="image" src="img/else.png"> --> </a>
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