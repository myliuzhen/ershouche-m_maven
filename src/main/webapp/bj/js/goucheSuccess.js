jQuery(function(){
	$('#toMaiche').on('click', function() {
		location.href = context+'/'+getRequestUrlArea()+'/maiche';
	});
	
	$('#toIndex').on('click', function() {
		location.href = context+'/'+getRequestUrlArea()+'/';
	});
	
	//图片轮播
	timer = carousel();
	var imgWidth = setCarousel();
	imageSlide(imgWidth);
});