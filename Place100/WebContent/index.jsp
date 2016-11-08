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
  color: #039be5;
  border: 2px solid #039be5;
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
	margin-top: 30%;
	margin-left: 42%;
}

html { 
  background: url("img/coex-main-another.jpg") no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover; 
  -o-background-size: cover; 
  background-size: cover; 
}
</style>
<title>코엑스 100배 즐기기</title>

</head>
<body>
<jsp:include page="header.jsp" flush="false"/>

   <div class="parallax" >
   <div class="ghost">
       <a class="ghost-button" href="userCheck.action"><strong>시작하기</strong></a>
      </div>
   </div>
   <!--  Scripts-->
   <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
</body>
</html>