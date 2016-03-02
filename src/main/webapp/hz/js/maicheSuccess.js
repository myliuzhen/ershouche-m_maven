jQuery(function(){
	$('#toGouche').on('click', function() {
		location.href = context+'/'+getRequestUrlArea()+'/gouche';
	});
	
	$('#toIndex').on('click', function() {
		location.href = context+'/'+getRequestUrlArea()+'/';
	});
	
	//图片轮播
	timer = carousel();
	var imgWidth = setCarousel();
	imageSlide(imgWidth);
});