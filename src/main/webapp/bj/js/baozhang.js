jQuery(function(){
	$('.tabSafe ul li').on('click', function() {
		$('.tabSafe ul li').removeClass('act');
		$(this).addClass('act');
		$('.baozhang').hide();
		var thisType = $(this).attr('data-type'); 
		if(thisType === 'maiche') {
			$('.baozhang.maiche').show();
		} else {
			$('.baozhang.gouche').show();
		}
	});
	/**点击买车流程跳转买车页；卖车流程跳转卖车页**/
	$("#maicheBzImg").click(function(){
		location.href="/ershouche/bj/maiche";
	});
	$("#goucheBzImg").click(function(){
		location.href="/ershouche/bj/gouche";
	});
	//问题解答收缩控制
	$(".qc-buy dl dt").click(function(){
		var c=$(this).next();
		if(c.css("display")=="none"){
			c.css("display","inline");
			$(this).find("em").addClass("act");
		}else{
			c.css("display","none");
			$(this).find("em").removeClass("act");
		}
	});
});