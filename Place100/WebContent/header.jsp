<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
<title>Parallax Template - Materialize</title>
<style type="text/css">

  .white {
    background-color: #FFFFFF !important;
} 
* {margin:0;padding:0;}
nav {
    color: #fff;
    background-color: #fff;
    width: 100%;
    height: 56px;
    line-height: 56px;
    box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    display: block;
}
*, *:before, *:after {
    box-sizing: inherit;
}
nav .nav-wrap {
    position: relative;
    height: 100%;
    width: 70%
}
.logging {
    margin: 0 auto;
    max-width: 1280px;
    width: 90%;
}
nav ul a, nav .brand-logo {
    color: #444;
}
nav ul a {
    transition: background-color .3s;
    font-size: 1rem;
    color: #444;
    display: block;
    padding: 0 15px;
    padding-top: 0px;
    padding-right: 15px;
    padding-bottom: 0px;
    padding-left: 15px;
    cursor: pointer;
}
nav .brand-logo {
    position: absolute;
    color: #444;
    display: inline-block;
    font-size: 2.1rem;
    padding: 0;
    white-space: nowrap;
}
nav a {
    color: #fff;
    text-decoration:none
}
a {
    text-decoration: none;
    color: #039be5;
    text-decoration: none;
    -webkit-tap-highlight-color: transparent;
    background-color: transparent;
}
nav ul li {
    transition: background-color .3s;
    float: left;
    padding: 0;
}
ul li {
    list-style-type: none;
}
li {
    text-align: -webkit-match-parent;
}
.side-nav {
    position: fixed;
    width: 300px;
    left: 0;
    top: 0;
    margin: 0;
    -webkit-transform: translateX(-100%);
    transform: translateX(-100%);
    height: 100%;
    height: calc(100% + 60px);
    height: -moz-calc(100%);
    padding-bottom: 60px;
    background-color: #fff;
    z-index: 999;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
    overflow-y: auto;
    will-change: transform;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
    -webkit-transform: translateX(-105%);
    transform: translateX(-105%);
}
.right {
    float: right !important;
}
nav ul {
    margin: 0;
}
ul {
    padding: 0;
    list-style-type: none;
}
.fark:HOVER {
   background-color:#e5e4e3;
   text-decoration:none
}

.input[type=text] {
    width: 130px;
    -webkit-transition: width 0.4s ease-in-out;
    transition: width 0.4s ease-in-out;
}

/* When the input field gets focus, change its width to 100% */
input[type=text]:focus {
    width: 100%;
}
</style>

  <!-- CSS  -->
<link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
</head>
<body>
   <nav class="white" role="navigation">
      <div class="nav-wrap logging">
      
         <a id="logo-container" href="main.action" class="brand-logo" style="text-decoration:none">
         <img alt="로고" src="img/coex-raku.png" width="100"></a>

         <ul class="right hide-on-med-and-down">
            <li><a data-activates="nav-mobile" href="answerPage.action"
               class="fark">새 스케줄 생성</a></li>
         </ul>
         
         <ul class="right hide-on-med-and-down">
            <li><a data-activates="nav-mobile" href="answerPage.action"
               class="fark">추천 스케줄</a></li>
         </ul>
         
         <ul class="right hide-on-med-and-down">
            <li><input type="text" name="search_text" placeholder="Search.."></li>
         </ul>
         
         
      </div>
   </nav>

<!-- ==================================================================================== -->   
<!-- jquery 검색 기능 구현 -->
<!-- 11-08-16 작성자: 박지호 -->
<script src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $("input[name=search_text]").keydown(function (key) {
 
        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
            searchSchedule();
        }
 
    });
     
    searchSchedule = function (){
        //검색 찾는 로직 구현
        var search_data = $("input[name=search_text]").val();
        if (search_data.length == 0) {
         alert("나니모 입력 쿠다사이");
         return;
      }
        var url = "searchSchedule.action?search_text=" + search_data;    
        $(location).attr('href',url);
    };
     
});
</script>
</body>
</html>