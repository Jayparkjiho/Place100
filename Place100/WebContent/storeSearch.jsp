<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.btn.btn-outline {
    background-color: transparent;
}
.btn.btn-circle {
    border-radius: 50px;
}
</style>
</head>
<body>
<h3>일정을 검색해 주세요.</h3>
<input type="text" id="storesearch"> <input type="button" value="찾기">
<h5>찾고자 하는 가게의 타입을 검색하면 리스트가 나옵니다.<br>
	ex)박람회, 문화/여가, 식사, 카페/디저트, 패션잡화, 미용, 라이프스타일, 캐릭터/디자인, 디지털, 키즈, 기타</h5>
	<input type="hidden" id="parents" value="<s:property value='name'/>">
	<div class="webguidebook-list " id="filterResult">
            <div id="sex_list" class="isolist list-thumbnail type_webguide">
            </div>
    </div>
<script src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
$(function () {
	/* var place_type = '';   */
	var currentPage = 1;
	 $('input[type=button]').on('click',function() {
         console.log("처음 로딩 시 현재페이지: " + currentPage);
         /*화면 준비 후 AJAX로 로그 로딩 */
         /* var member_id = '<s:property value="%{#session.login.member_id}"/>'
         console.log('세션에 저장된 member_id : ' + member_id);
         var data = {
               'member_id' : member_id
         }; */
         /* var place_type = ''; */ 
         
        place_type = $("#storesearch").val();
        if (place_type === '박람회') {
           place_type = 1;
        }
        else if(place_type === '문화' || place_type === '여가'){
           place_type = 2;
        }
        else if(place_type === '식사'){
           place_type = 3;
        }
        else if(place_type === '디저트' || place_type === '카페'){
           place_type = 4;
        }
        else if(place_type === '패션잡화'){
            place_type = 5;
         }
        else if(place_type === '뷰티'){
            place_type = 6;
         }
        else if(place_type === '라이프스타일'){
            place_type = 7;
         }
        else if(place_type === '캐릭터' || place_type === '디자인'){
            place_type = 8;
         }
        else if(place_type === '기타'){
           place_type = 0;
        }
        else if(place_type === '디지털'){
           place_type = 9;
        }
        else if(place_type === '키즈'){
            place_type = 10;
        }
  
           $.ajax({
               url : 'getList',
               type : 'post',
               data : {
                  'place.place_type' : place_type,
                  /* 'currentPage' : currentPage */
               },
               success : function(data) {
                  $('#sex_list').empty();
                  var npp = '<input type="button" class="btn btn-default btn-outline btn-circle collapsed" id="sendparents" value="선택" onclick="javascript:abc()"><br>';
                  $('#sex_list').append(npp);                  
                  $(data.list_place).each(function(index, item) {
                     var np = addLogContent(item);
                     $('#sex_list').append(np);
                    /*  alert("after success" + place_type); */
                     /* var log_id = item.log_id;
                     $('.'+log_id).css('background-image','url(img/'+item.member_id+ '/' + item.log_id+'/'+item.main_photo_name + ')');
                     */
                  }); 
               }
         });//ajax 
     });//button

	
	var addLogContent = function(place) {
	       /* var log_tag = '';
	       $(list_place.log_tag_list).each(function(index, item) {
	          log_tag += ' #' + item.log_tag_name;
	       }); */
	 
	       var output = '';
	       
	       output += "<div>"
	       output += "<input type='radio'name='storeRadio' value='"+ place.place_name +"'>"
	       output += "<span id='placeName'>"+ place.place_name + "</span>"
	       output += "<span id='placeCategory'>"+ place.place_category + "</span>"
	       output += "<br>"
	       output += "<a id=placeImg> <img src="+"'"+place.place_photo_name+"'"+" height=150 width=150/></a>"
	       output += "</div>"
	       
	       return output;
	 
	    /* 내용 채워넣기 */
	    }; 
	    
});
	var cnt =0;
	function abc() {
			var radio = document.getElementsByName("storeRadio");
			for (var i = 0; i < radio.length; i++) {
				if (radio[i].checked) {
					cnt += 1;
					var parents = $("#parents").val();
					var value = radio[i].value;
					opener.setChildValue(parents, value);
					window.close();
					return;
				}
			}
			if (cnt==0) {
					alert("장소를 선택해주세요.");
			}
	}

</script>
</body>
</html>