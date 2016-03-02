var agreeBmVal = "1";//是否统一帮卖 0否 1是，是则发短信
/**
 * 初始加载
 */
$(document).ready(function() {
	switchCheck();//是否同意
	checkGuzhiInput();//输入项校验
	
	$("#citySelect").val("北京");
	$("#citySelect").data("city2","1");
	
	//车型选择
	$('#carModelSelect').on('click', function() {
		var $this = $(this);
		var carType1 = $this.data('carType1');
		var carType2 = $this.data('carType2');
		var carType3 = $this.data('carType3');
		if(carType3 != null && carType3 != '') {
			$('.sCarType3').show();
			return;
		}
		if(carType2 != null && carType2 != '') {
			$('.sCarType2').show();
			return;
		}
		if(carType1 != null && carType1 != '') {
			$('.sCarType1').show();
			return;
		}
		
		//请求车型接口1
		$.ajax({
			url : context + '/guzhi/getCarBrands',
			type : "post",
			success : function(data) {
				if(data) {
					setHtmlCarType1(data);
					$('.sCarType1').show();
					$('.sCarType2').hide();
					$('.sCarType3').hide();
				} else {
					alert('无车型数据！');
				}
			}
		});
	});
	
	//上牌时间
	$('#carYear').on('click',function() {
		var carYear = $('#carModelSelect').data('modelyear');
		if(carYear == null || carYear == '') {
			alert('请选择车型！');
			return;
		}
		var nowYear = new Date().getFullYear();
		var contentYear = new Array();
		contentYear.push('<dl>');
		for(var i=parseInt(carYear);i<=nowYear;i++){
			contentYear.push('<dd year="'+i+'">'+i+'年</dd>');
		}
		contentYear.push('</dl>');
		$('#year1').html(contentYear.join(''));
		
		var contentMonth = new Array();
		contentMonth.push('<dl>');
		for(var i=1;i<=12;i++){
			contentMonth.push('<dd month="'+i+'">'+i+'月</dd>');
		}
		contentMonth.push('</dl>');
		$('#year2').html(contentMonth.join(''));
		$('.sCarYear1').show();
		
		$("#year1 dl dd").on("click",function(){
	    	var choosedYear = $(this).attr("year");
	    	$('#carYear').data('carYear', choosedYear);
	    	$('.sCarYear1').hide();
	    	$('.sCarYear2').show();
	    	
	    	$("#year2 dl dd").on("click",function(){
	        	var choosedMonth = $(this).attr("month");
	        	var $carYear = $('#carYear');
	        	var yearValue = $carYear.data('carYear') + '年' + choosedMonth + '月';
	        	$carYear.data('carMonth', choosedMonth).val(yearValue);
	        	$('.sCarYear2').hide();
	        });
	    });
	});
	
	//type1关闭
	$('.selectType1').on('click',function() {
		$('.sCarType1').hide();
	});
	
	//type2关闭type1
	$('.selectType2').on('click',function() {
		$('.sCarType2').hide();
		$('.sCarType1').show();
	});
	
	//type3关闭type2
	$('.selectType3').on('click',function() {
		$('.sCarType3').hide();
		$('.sCarType2').show();
	});
	
	//选择城市
	$('#citySelect').on('click',function() {
		$('html, body').animate({scrollTop:0});
		var $this = $(this);
		var city1 = $this.data('city1');
		var city2 = $this.data('city2');
		if(city2 != null && city2 != '' && city2 !== '1') {
			$('.sCity2').show();
			return;
		}
		if(city1 != null && city1 != '') {
			$('.sCity1').show();
			return;
		}
		//请求城市接口
		$.ajax({
			url : context + '/guzhi/getAllCitys',
			type : "post",
			success : function(data) {
				if(data && data.length > 0) {
					_all_city_data = data;
					setHtmlCityInfo(data);
					$('.sCity1').show();
					$('.sCity2').hide();
				} else {
					alert('无城市数据！');
				}
			}
		});
	});
	
	$('.selectCity1').on('click',function() {
		$('.sCity1').hide();
	});
	
	$('.selectCity2').on('click',function() {
		$('.sCity1').show();
		$('.sCity2').hide();
	});
	
	$('.selectYear1').on('click',function() {
		$('.sCarYear1').hide();
	});
	
	$('.selectYear2').on('click',function() {
		$('.sCarYear1').show();
		$('.sCarYear2').hide();
	});
});

var _all_city_data;

//carType1解析
function setHtmlCarType1(data) {
	var str = '';
	var dataList;
	for(var key in data) {
		dataList = data[key]; 
		if(dataList) {
			str += '<dl><dt>'+key+'</dt>';
			str += '<dd><ul>';
			for(var i = 0; i < dataList.length; i++) {
				str += '<li brandid="'+ dataList[i].brand_id +'" brandname="'+ dataList[i].brand_name +'">'+ dataList[i].brand_name + '</li>';
			}
		}
		str += '</ul></dd></dl>';
	}
	$('#car1').html(str);
	
	//根据brandId请求车型接口2
	$('#car1 dl dd ul li').on('click',function() {
		$('html, body').animate({scrollTop:0});
		var brandId = $(this).attr("brandid");
		$('#carModelSelect').data('carType1',brandId);
		//请求车型接口2
		$.ajax({
			url : context + '/guzhi/getCarSeries/'+brandId,
			type : "post",
			success : function(data) {
				if(data) {
					setHtmlCarType2(data);
					$('.sCarType1').hide();
					$('.sCarType2').show();
					$('.sCarType3').hide();
				} else {
					$('.sCarType1').hide();
				}
			}
		});
	});
}

//carType2解析
function setHtmlCarType2(data) {
	var str = '';
	var dataList;
	for(var key in data) {
		dataList = data[key];
		if(dataList) {
			str += '<dl><dt>'+key+'</dt>';
			for(var i = 0; i < dataList.length; i++) {
				str += '<dd seriesid="'+ dataList[i].series_id +'" seriesname="'+ dataList[i].series_name +'">' + dataList[i].series_name + '</dd>';
			}
			str += '</dl>';
		}
	}
	$('#car2').html(str);
	
	//根据seriesId请求车型接口3
	$('#car2 dd').on('click',function() {
		$('html, body').animate({scrollTop:0});
		var seriesId = $(this).attr("seriesid");
		$('#carModelSelect').data('carType2',seriesId);
		//请求车型接口3
		$.ajax({
			url : context + '/guzhi/getCarModels/'+seriesId,
			type : "post",
			success : function(data) {
				if(data) {
					setHtmlCarType3(data);
					$('.sCarType1').hide();
					$('.sCarType2').hide();
					$('.sCarType3').show();
				} else {
					$('.sCarType2').hide();
				}
			}
		});
	});
}

//carType3解析
function setHtmlCarType3(data) {
	var str = '';
	var dataList;
	for(var key in data) {
		dataList = data[key]; 
		if(dataList) {
			str += '<dl><dt>'+key+'款</dt>';
			for(var i = 0; i < dataList.length; i++) {
				str += '<dd>';
				str += '<p modelid="'+ dataList[i].model_id +'" modelname="'+ dataList[i].model_name +'" modelyear="'+ dataList[i].model_year +'" pfbz="'+ dataList[i].discharge_standard +'">' + dataList[i].model_name + '</p>';
				str += '<span>'+ dataList[i].model_price +'万</span>';
				str += '</dd>';
			}
			str += '</dl>';
		}
	}
	$('#car3').html(str);
	
	$('#car3 dd').on('click',function() {
		var $model = $(this);
		var modelId = $model.find('p').attr('modelid');
		var modelName = $model.find('p').attr('modelname');
		var modelyear = $model.find('p').attr('modelyear');
		var pfbz = $model.find('p').attr('pfbz');
		$('#carModelSelect').data('carType3',modelId).data('modelyear', modelyear).data('pfbz',pfbz).val(modelName);
		$('.sCarType3').hide();
	});
}

//城市解析
function setHtmlCityInfo(data) {
	var str = '<dl>';
	for(var i = 0; i < data.length; i++) {
		str += '<dd provid="'+ data[i].prov_id +'" provname="'+ data[i].prov_name +'">' + data[i].prov_name + '</dd>';
	}
	str += '</dl>';
	$('#city1').html(str);
	
	//添加点击事件
	$('#city1 dl dd').on('click',function() {
		var $city = $(this);
		$('#citySelect').data('city1', $city.attr('provid'));
		getCityListByProvId($city.attr('provid'), $city.attr('provname'));
		$('.sCity1').hide();
		$('.sCity2').show();
	});
	
	
	function getCityListByProvId(provId,provName) {
		var str = '<dl>';
		for(var i=0,j=_all_city_data.length;i<j;i++){
			if(_all_city_data[i].prov_id==provId){
				for(var z = 0; z < _all_city_data[i].cityList.length; z++) {
					str += '<dd cityid="'+ _all_city_data[i].cityList[z].city_id +'" cityname="'+ _all_city_data[i].cityList[z].city_name +'">'+ _all_city_data[i].cityList[z].city_name +'</dd>';
				}
				break;
			}
		}
		str += '</dl>';
		$('#city2').html(str);
		
		//添加点击事件
		$('#city2 dl dd').on('click',function() {
			var $city = $(this);
			$('#citySelect').data('city2', $city.attr('cityid')).val($city.attr('cityname'));
			$('.sCity2').hide();
		});
	}
}


/**
 * 切换是否同意委托
 */
function switchCheck() {
	$('#switchEM').click(function() {
		$(this).toggleClass("inchecked");
		if($(this).hasClass("inchecked")) agreeBmVal="1";
		else agreeBmVal="0";
	});
}
/**
 * 校验估值输入
 */
function checkGuzhiInput(){
	//必录项控制
	$(".checkGuzhi").blur(function() {
        if ($(this).val() == "" || $(this).val() == null || $(this).val() == "请输入行驶里程" || $(this).val() == "请输入您的手机号") {
        	$(this).siblings("em").show();
            $(this).siblings("em").addClass("wrong");
         }else{
        	 $(this).siblings("em").hide();
        	 $(this).siblings("em").removeClass("wrong");
         }
    });
	/*$("#carModelSelect").blur(function(){
		var carModel = $("#carModelSelect").data("carType3");
		if (carModel == "" || carModel == null){
			$("#carModelSelect").siblings("em").show();
			$("#carModelSelect").siblings("em").addClass("wrong");
		}else{
			$("#carModelSelect").siblings("em").hide();
			$("#carModelSelect").siblings("em").removeClass("wrong");
		}
	});*/
	//里程数字校验
	$("#mileageInput").keyup(function(){
		 this.value = this.value.replace(/[^0-9.]/g, '');//.replace(/(\d{4})(?=\d)/g, "$1 ");
		 if ($(this).val() == "" || $(this).val() == null){
			 
		 }
	});
	//手机号校验
	$("#mobile").keyup(function(){
		 this.value = this.value.replace(/\D|^0/g, '');//.replace(/(\d{4})(?=\d)/g, "$1 ");
	});
	$("#mobile").blur(function(){
		if(!mobile(this.value)){ 
			$(this).siblings("em").show();
			$(this).siblings("em").addClass("wrong");
			$(this).val('');
		};
	});
}
/**
 * 提交评估(车300在线估值)
 */
function submitGuzhi(){
	//车型
	var carModel = $("#carModelSelect").data("carType3");
	if (carModel == "" || carModel == null || carModel==undefined){
		alert("请选择车型！");
		return;
	}
	//首次上牌
	var carYear = $('#carYear').data('carMonth');
	if (carYear == "" || carYear == null || carYear==undefined){
		alert("请选择首次上牌时间！");
		return;
	}
	//城市
	var cityCheck = $('#citySelect').data('city2');
	if (cityCheck == "" || cityCheck == null || cityCheck==undefined){
		alert("请选择城市！");
		return;
	}
	var k = 0;
	$(".checkGuzhi").each(function(i, obj) {
		if ($(obj).val().trim() == "" || $(obj).val().trim() == null || $(obj).val().trim() == "请输入行驶里程" || $(obj).val().trim() == "请输入您的手机号") {
            k++;
            $(this).siblings("em").show();
            $(this).siblings("em").addClass("wrong");
            $(this).focus();
            return false;
        }
    });
    if (k != 0) return;
	
	var guzhi = new Object();
	guzhi.carModel = $("#carModelSelect").data("carType3");
	var visitSite = location.href.split('?')[0];
	guzhi.visitSite = $.trim(visitSite);
	guzhi.carModelDesc = $("#carModelSelect").val();
	guzhi.carYear = $('#carYear').data('carYear')+'-'+$('#carYear').data('carMonth');
	guzhi.city = $('#citySelect').data('city2');
	guzhi.mileage = $('#mileageInput').val();
	guzhi.mobile = $('#mobile').val();
	//guzhi.shopName = shopName;
	guzhi.fromT = getCookieValue('ershouchefromT');
	guzhi.cityId = getRequestUrlArea();
	/**估值二级入口**/
	guzhi.subFrom=_SUBFROM_DEF_GUZHI;
	guzhi.agreeBm=agreeBmVal;//是否帮卖 0否 1是
	$.post(context+"/guzhi/guzhi300",guzhi,function(data){
		if(!data){
			alert("没有该车辆的估价信息，请确保填写信息的真实性！");
			return;
		}
		var str = new Array();
		str.push("carImg="+data.car_logo_url);
		str.push("&modelId="+$("#carModelSelect").data("carType3"));
		str.push("&zone="+$('#citySelect').data('city2'));
		str.push("&carInfo="+$("#carModelSelect").val());
		str.push("&pfbz="+$("#carModelSelect").data('pfbz'));
		str.push("&mileage="+$('#mileageInput').val());
		str.push("&regDate="+$('#carYear').data('carYear')+'-'+$('#carYear').data('carMonth'));
		str.push("&price="+data.price);
		str.push("&evalPrice="+data.individual_price);
		/**重置估值元素**/
		$("#carModelSelect").val('');
		$("#carYear").val('');
		$("#citySelect").val('');
		$("#mileageInput").val('');
		$("#mobile").val('');
		window.open(context+"/hz/guzhiResult/?"+encodeURI(str.join('')),"_self");
	});
}
