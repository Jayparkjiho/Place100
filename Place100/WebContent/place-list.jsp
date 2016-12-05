<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장소 소개</title>
<style type="text/css">
img.wp-smiley, img.emoji {
	display: inline !important;
	border: none !important;
	box-shadow: none !important;
	height: 1em !important;
	width: 1em !important;
	margin: 0 .07em !important;
	vertical-align: -0.1em !important;
	background: none !important;
	padding: 0 !important;
}

.filters .sort-group--btns .title {
	width: 11% !important;
}
</style>
<link rel='stylesheet' id='wpa-css-css'  href='http://trip-curator.co.kr/wp-content/plugins/wp-attachments/styles/0/wpa.css?ver=4.4.5' type='text/css' media='all' />
<link rel='stylesheet' id='smoothness-css'  href='http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css?ver=4.4.5' type='text/css' media='all' />
<link rel='stylesheet' id='project-style-css'  href='http://trip-curator.co.kr/wp-content/themes/tripcurator/css/style.css?ver=4.4.5' type='text/css' media='all' />
<script type='text/javascript' src='http://trip-curator.co.kr/wp-includes/js/jquery/jquery.js?ver=1.11.3'></script>
<script type='text/javascript' src='http://trip-curator.co.kr/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.2.1'></script>
<link rel='https://api.w.org/' href='http://trip-curator.co.kr/wp-json/' />
<link rel="canonical" href="http://trip-curator.co.kr/web-guide-book" />
<link rel="alternate" type="application/json+oembed" href="http://trip-curator.co.kr/wp-json/oembed/1.0/embed?url=http%3A%2F%2Ftrip-curator.co.kr%2Fweb-guide-book" />
<link rel="alternate" type="text/xml+oembed" href="http://trip-curator.co.kr/wp-json/oembed/1.0/embed?url=http%3A%2F%2Ftrip-curator.co.kr%2Fweb-guide-book&#038;format=xml" />
<!-- BEGIN TRACKJS -->
<script type="text/javascript">window._trackJs = { token: '7ff748ae47ce4199be6a81ac0453e1cc' };</script>
<script type="text/javascript" src="http://d2zah9y47r7bi2.cloudfront.net/releases/current/tracker.js"></script>
<!-- END TRACKJS -->

<!-- jquery 실행 부분 -->
<script src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
var currentPage = 1;
var place_type = 11;
var scroll_flag = false;

$(function(){
	$.ajax({
		url : 'getPlaces',
		type : 'post',
		data : {
			'place.place_type' : 11,
			'currentPage' : 1
		},
		dataType:'json',
		success : function(data) {
			$(data.placeList).each(function(index, item){
				var contents = addContent(item);
				$('#place_list').append(contents);
			});
			currentPage+=1;
			scroll_flag = false;
		}//success 끝
	});//ajax 끝
	
	$(document).scroll(function(){
		var scrollPersent = ($(window).scrollTop()/ ($(document).height()-$(window).height())) * 100;
		if(scrollPersent >= 70){
			
			if(window.scroll_flag) return;
			window.scroll_flag = true;
			setTimeout(function(){
				window.scroll_flag = false;
			},1000);
			
			if (scroll_flag) {
				$.ajax({
					url : 'getPlaces',
					type : 'post',
					data : {
						'place.place_type' : place_type,
						'currentPage' : currentPage
					},
					dataType:'json',
					success : function(data) {
						$(data.placeList).each(function(index, item){
							var contents = addContent(item);
							$('#place_list').append(contents);
						});
					currentPage = currentPage + 1;
					scroll_flag = false;
					}//success 끝
				});//ajax 종료
			}
		}
	});//document scroll 이벤트 종료
	
	
	$('input[type=button]').on('click', function(){
		place_type = $(this).val();
		currentPage = 1;
		if (place_type === '박람회') {
			place_type = 1;
		}
		else if(place_type === '문화/여가'){
			place_type = 2;
		}
		else if(place_type === '식당'){
			place_type = 3;
		}
		else if(place_type === '디저트/카페'){
			place_type = 4;
		}
		else if(place_type === '의류매장'){
			place_type = 5;
		}
		else if(place_type === '기타'){
			place_type = 0;
		}
		else if(place_type === '전부'){
			place_type = 11;
		}
		
		$.ajax({
			url : 'getPlaces',
			type : 'post',
			data : {
				'place.place_type' : place_type,
				'currentPage' : currentPage
			},
			dataType:'json',
			success : function(data) {
				$('#place_list').empty();
				$(data.placeList).each(function(index, item){
					var contents = addContent(item);
					$('#place_list').append(contents);
				});
				currentPage = currentPage + 1;
				scroll_flag = false;
			}//success 끝
		});//ajax 종료
	}); //button click 이벤트 종료
	
	
	
	
	
	var addContent = function(place){
		var output = '';
		/* 내용 채우기 */
	    output += "<div id='"+ place.place_no + "' class='item lm'>"
	    output += "<a href='' title='"+ place.place_name+ "' class='bd'>"
	    output += "<div class='post-tag'>"+ place.place_category + "</div>"
	    output += "<div class='post-thumbnail'>"
	    output += "<img width='520' height='347' src="+ place.place_photo_name +" class='attachment-medium size-medium wp-post-image' alt='yagaji01' sizes='(max-width: 520px) 100vw, 520px' />   </div>"
	    output += "<div class='post-content'>"
	    output += "<h2 class='title'>"+ place.place_name + "</h2>"
	    output += "</div></a></div>"	
	    
	    return output;
	};//addContent 끝
	
});

</script>

</head>
<body class="page page-id-6 page-template-default singular page-web-guide-book">
	
	<jsp:include page="header.jsp" flush="false"/>
	
	<main id="main" class="site-main" role="main">
	<div id="content" class="site-content" role="main">

		<div class="page-content">

			<section class="section-sub section-webguide--list">
			<div class="container">
				
				<div class="filters">
					<div class="sort-group sort-group--btns">
						<div class="inner">
							<div class="sortbox s01 sync">
								<h2 class="title">카테고리별</h2>
								<div class="btn-group button-desc" data-filter-group="district">
									<input type="button" class="btn btn-filter is-checked" id="11" name="place_type" value="전부" /> 
									<input type="button" class="btn btn-filter" id="1" name="place_type" value="박람회" />
									<input type="button" class="btn btn-filter" id="2" name="place_type" value="문화/여가" /> 
									<input type="button" class="btn btn-filter" id="3" name="place_type" value="식당" />
									<input type="button" class="btn btn-filter" id="4" name="place_type" value="디저트/카페" /> 
									<input type="button" class="btn btn-filter" id="5" name="place_type" value="의류매장" />
									<input type="button" class="btn btn-filter" id="0" name="place_type" value="기타" />
								</div>
							</div>
						</div>
					</div>
					<div class="sort-group sort-group--keyword">
						<div class="inner">
							<div class="sortbox s03">
								<h2 class="title">키워드 검색</h2>
								<label> <span class="screen-reader-text">키워드 검색</span> <input
									type="text" class="keyword-field inp" id="schkeyword" name=""
									placeholder="키워드를 입력해주세요." value="">
								</label> <input type="button" class="btn btn-dark keyword-submit"
									id="keword-button" value="검색">
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="webguidebook-list " id="filterResult">
				<div id="place_list" class="isolist list-thumbnail type_webguide">
					<!-- 내용이 들어갈 곳 -->
				</div>
			</div>
			</section>

			<!-- <form class="check" id="check" name="check" action="" method="post">
				<input type="hidden" name="fdistrict" value=".all"> 
				<input type="hidden" name="ftype" value=".all"> 
				<input type="hidden" name="fkeyword" value="">
			</form> -->
		
		</div>
		<!-- .page-content -->


	</div>
	<!-- #content -->
	</main>
</body>

</html>