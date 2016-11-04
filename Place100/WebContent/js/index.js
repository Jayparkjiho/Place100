$.global = new Object();

$.global.item = 1;
$.global.total = 0;
$.global.slider = 0;


$(document).ready(function() 
	{    
	
  var SlideCount = $('#slides li').length;	
	var SliderWidth = SlideCount * 100;
	var SlideWidth = 100 / SlideCount;
	
    $.global.total = SlideCount; 
    
	$('#image-carousel .handle').css('width',''+SliderWidth+'%');
	$('#image-carousel .handle .slide').css('width',''+SlideWidth+'%');
	
	DragIt();
  
  
  $('#right-arrow').click(function() {                   
    var $currentstep = $.global.slider.getStep();
    var $nextstep = parseInt($currentstep) + 1; $.global.slider.setStep($nextstep);
     });
  
   $('#left-arrow').click(function() {                   
    var $currentstep = $.global.slider.getStep();
    var $nextstep = parseInt($currentstep) - 1; $.global.slider.setStep($nextstep);
     });
  
  });
   


function DragIt()
	{
  $.global.slider = new Dragdealer('image-carousel', {
  steps: $.global.total,
  speed: 0.2,
  loose: true,
  css3: true,
    top:0,
    bottom:0,
    left:0,
    right:0,
  requestAnimationFrame: true,
  callback: function(x, y) {
    $('#console').html(x);}
  });
	}