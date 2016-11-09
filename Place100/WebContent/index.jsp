<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">

.ghost-button {
  display: inline-block;
  width: 200px;
  padding: 8px;
  color: white;
  border: 2px solid white;
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
.ghost{
position: absolute;
	margin-top: 10%;
	margin-left: 42%;
}

html { 
  background: url("img/coex-back-blur.png") no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover; 
  -o-background-size: cover; 
  background-size: cover; 
  background-color: rgba(255,255,255,0.5);
  
}
.story{
margin-top: 30%;
	text-align: center;
	font-size: 50px;
	color: white;
}
.most{
	font-size: 90px;
}
.coex100{
	font-size: 80px;
}
.best{
	font-size:60px;
}
</style>
<title>코엑스 100배 즐기기</title>

</head>
<body>
<jsp:include page="header.jsp" flush="false"/>

   <div class="parallax" >
   <div class="story">
   <p class="best">코엑스를<span class="most">가장</span><br><br><br>
   			알차게 즐기는 방법</p><br>
   <p class="coex100"><strong>코엑스 100배 즐기기</strong></p>
   
   
   </div >
   <div class="ghost">
       <a class="ghost-button" href="userCheck.action"><strong>시작하기</strong></a>
      </div>
   </div>
   <!--  Scripts-->
   <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
</body>
</html>