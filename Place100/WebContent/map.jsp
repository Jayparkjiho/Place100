<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#pjs{
width: 100%;

} 

</style>
<script src="script/jquery-3.1.0.min.js"></script>
<script src="http://cloud.github.com/downloads/processing-js/processing-js/processing-1.4.1.js"></script>
<script>
//PROCESSING.JS
//배열을 받아오고
//그 배열에는 [좌표,좌표,좌표,좌표]등으로 되어있고
//그 좌표를 여기에 저장하고
//[이미지,이미지,이미지,이미지]등으로 된 배열에서
//해당하는 포인트 위에 이미지를 넣어준다(썸네일으로)
//해당 썸네일에 마우스가 올라가면 옆에 정보를 띄워준다.

var a = 350;
var imgLocationArr = [355,262,222,555];
var totalPath = '${totalPath}';
var imageList = '${imageList}'.split(",");
var paths = totalPath.split("#");
var path;
console.log(paths);
var pathCount = paths.length-1;
var clkCnt =0;
alert('총 경로의 수:'+pathCount);
console.log(imageList);
</script>
</head>
<body>

<jsp:include page="header.jsp" flush="false"/>

<script type="text/processing" data-processing-target="pjs">

PImage[] imgs = new PImage[pathCount];
for(var i = 0; i<pathCount+1;i++){
	imgs[i] = loadImage(imageList[i]);
}
PImage arrow;
void setup() {
  size(1200, 1200);
  smooth();
  b = loadImage("img/map.png");
  arrow = loadImage("img/footPrint.png");
 }

 void draw() //초당 60번 자동 반복되는 메쏘드
{
  background(255);
  image(b, 0, 0);
  noLoop();
}

 mouseClicked = function(){
	draw();
	path = paths[clkCnt].split(",");
  	stroke(244,66,197);
  	strokeWeight(4);
  	for(var i = 0; i+3 <path.length();i+=2){
	line(path[i]-20,path[i+1]-10,path[i+2]-20,path[i+3]-10);
	ellipse(path[i]-20,path[i+1]-10,15,15);
	fill(0,0,0);
  }
	if(clkCnt < path.length-1){
		textSize(30);
		text("출발",900,130);
		imgs[clkCnt].resize(200,200);
		image(imgs[clkCnt],900,137);
	}
	if(imgs[clkCnt+1]!=null){
textSize(30);
		text("도착",900,670);
		imgs[clkCnt+1].resize(200,200);
		image(imgs[clkCnt+1],900,437);
	}
if(imgs[clkCnt+1]!=null){
arrow.resize(35,75);
image(arrow,990,350);
}
	fill(0,0,0);
  	ellipse(path[path.length-3]-20,path[path.length-2]-10,15,15);
	textSize(70);

if(clkCnt < path.length-1){
  text(clkCnt+1+"번째 경로",850,80);
}
  fill(0,0,0);

  if((mouseX > 260) && (mouseX < 260+50) && (mouseY > 185) && (mouseY < 195)) {
    image(img2, 260, 195);
  }
	clkCnt++;
if(clkCnt >pathCount){
	clkCnt = 0;
}
};

//void mousePressed(){
//	println(mouseX+"."+mouseY);
//}

</script>
<h1>click to start navigation</h1>
<canvas id="pjs"> </canvas>
</body>
<script>
$(window).click(function() {
	$("h1").remove();
	});
</script>
</html>