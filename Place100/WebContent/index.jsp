<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.content{
   height: 50%;
}
.ghost-button {
  display: inline-block;
  width: 200px;
  padding: 8px;
  color: black;
  border: 2px solid black;
  text-align: center;
  outline: none;
  text-decoration: none;
  transition: background-color 0.2s ease-out,
              color 0.2s ease-out;
}
.ghost-button:hover,
.ghost-button:active {
  background-color: black;
  color: white;
  transition: background-color 0.3s ease-in,
              color 0.3s ease-in;
}
</style>
<title>코엑스 100배 즐기기</title>
<!-- 새로 작성될 예정  -->
</head>
<body>
<jsp:include page="header.jsp" flush="false"/>

   <div class="parallax">
      <img src="img/coex-main-another.jpg" alt="Unsplashed background img 1">
   </div>

   <div id="index-banner" class="parallax-container">
      <div class="section no-pad-bot">
         <div class="container">
            <br> <br>

            <br> <br>

         </div>
      </div>
   </div>
   <div class="row center">
       <a class="ghost-button" href="userCheck">시작하기</a>
   </div>



   <!--  Scripts-->
   <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
   <script src="js/materialize.js"></script>
   <script src="js/init.js"></script>


</body>
</html>