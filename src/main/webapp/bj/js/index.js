var timer;
jQuery(function(){
	getCarListData();
	
	//热门团购好车点击跳转
	$('.hot ul li').on('click', function() {
		var selectType = $(this).attr('data-flag');
		setCookie('selectCarType', selectType, context+'/'+getRequestUrlArea());
		location.href = context+'/'+getRequestUrlArea()+'/goucheDetail';
	});
	
	//图片轮播
	timer = carousel();
	var imgWidth = setCarousel();
	imageSlide(imgWidth);
	
	cutCity();//切换城市
});
function cutCity(){
	$("header .indexC").click(function(event){
		var cs=$("header .addCity");
		if(cs.css("display")=='none') cs.css("display","block");
		else cs.css("display","none");
		event.stopPropagation();
	});
}


/**
 * 获取并展示车辆列表
 */
function getCarListData(){
	$.ajax({
		url : context + '/gouche/getSelledCarGroupByCountryForPage',
		type : "post",
		data : {nowPage:1,pageSize:_INDEX_PAGE_SIZE},
		success : function(data) {
			var str = '';
			var list = data.dataList;
			//首页只显示4条数据
			if(list) {
				if(list.length > 4) {
					//显示查看更多
					$('#showMore').show();
				} else {
					//不显示查看更多
					$('#showMore').hide();
				}
				str += '<ul>';
				for(var i = 0; i < 4; i++) {
					var car = list[i];
					var imgUrl = car.img_URL.replace("${carCode}",car.carCode).replace("${carImg}","b"+car.carFirstImg).replace("${width}","232").replace("${height}","168");
					str += '<li onclick="toCaseDetail(\''+car.carCode+'\');">';
					str += '<div class="imgBox">';
					str += '<img src="'+ imgUrl +'" alt="团车二手车">';
					str += '</div>';
					str += '<div class="caseText">';
					str += '<h4>'+car.carName+'</h4>';
					str += '<span class="standard">' + car.formatRegDate + '/' + (car.formatMileage ? car.formatMileage+'/' : '') + (car.registry ? car.registry.split("-")[0] + '/' : '') + car.emissionGb + '</span>';
					str += '<div class="carPrice">';
					str += '<span>￥'+ car.formatPrice +'</span>';
					str += '<div class="prC prC-left"><samp>';
					if(car.extraIncome != null && car.extraIncome !== '') {
						str += '多卖' + car.extraIncome + '元';
					}
					str += '</samp><div class="arrow-left">箭头</div></div>';
					str += '</div></div></li>';
				}
				str += '</ul><div class="clearfix"></div>';
				$('.caseList').html(str);
			}
		}
	});
}

//跳转到卖车的详细
function toCaseDetail(caseCarCode){
	setCookie("caseCarCode",caseCarCode,context+'/'+getRequestUrlArea());
	var cityId = getRequestUrlArea(); 
	window.location.href=context+"/"+cityId+"/caseDetail";
}

function toCaseList(){
	var cityId = getRequestUrlArea(); 
	window.location.href=context+"/"+cityId+"/caseList";
}