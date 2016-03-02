/***************************二手车全局通用js工具类******************************/
/**
 * 获取url参数
 * @param name key值
 * @returns
 */
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
/**
 * 设置cookie
 */
function setCookie(cookname,value,path){
	if(!path) {
		document.cookie = cookname+"="+escape(value);
	} else {
		document.cookie = cookname+"="+escape(value)+';path='+path;
	}
}
/**
 * 获取Cookie值
 */
function getCookieValue(cookname){
    var name = escape(cookname);
    var allcookies = document.cookie;
    name += "=";
    var pos = allcookies.indexOf(name);
    if (pos != -1){                                     
        var start = pos + name.length;                 
        var end = allcookies.indexOf(";",start);        
        if (end == -1) end = allcookies.length;         
        var value = allcookies.substring(start,end);    
        return unescape(value);                         
        }
    else return "";                                     
} 
/**
 * 设置带失效时间的cookie
 * hours为空字符串时,cookie的生存期至浏览器会话结束
 * hours为数字0时,建立的是一个失效的cookie
 */
function setCookieTime(cookname,cookvalue,sconds,path){
    var name = escape(cookname);
    var value = escape(cookvalue);
    var expires = new Date();
    expires.setTime(expires.getTime() + sconds*1000);
    path = path == "" ? "" : ";path=" + path;
    _expires = (typeof sconds) == "string" ? "" : ";expires=" + expires.toUTCString();
    document.cookie = name + "=" + value + _expires + path;
}

/**
* 获取厂商信息
*/
function getAllManufacturerData(manufacturerId){
	var resultData = null;
	$.ajax({
		type:'POST',
		url:context + '/carinfo/getmanufacturerlist',
		dataType: 'json',
		async:false,
		success:function(json){
			var html = "<option value =''>请选择旧车品牌</option>";
			for(var i=0;i<json.length;i++){
				html += "<option value='"+json[i].manufacturerCode+"'>"+json[i].manufacturerName+"</option>";
			}
			$("#"+manufacturerId).html(html);
		}
	});
	return resultData;
}
/**
 * 获取车系
 * @param data
 */
function getSeriesInfo(seriesId,manufacturerCode){
	if(manufacturerCode ==''){
		var html = "<option value =''>请选择旧车车型</option>";
		$("#"+seriesId).html(html);
		return;
	}
	$.ajax({
		type:"POST",
		url:context + "/carinfo/getseries",
		data:{manufacturerCode:manufacturerCode},
		dataType:"json",
		async:false,
		success:function(json){
			var html = "<option value =''>选择型号</option>";
			for(var i=0;i<json.length;i++){
				html += "<option value='"+json[i].seriesName+"'>"+json[i].seriesName+"</option>";
			}
			$("#"+seriesId).html(html);
		}
	});
}
/**
 * 车辆年限
 * @param carYear
 */
function carYear(carYear){
	var currentYear = new Date().getFullYear();
	var html = "<option value =''>选择年份</option>";
	for(var i=0;i<16;i++){
		var year = (currentYear - i)+'年';
		html += "<option value='"+year+"'>"+year+"</option>";
	}
	$("#"+carYear).html(html);
}
/**
 * 判断是否为空
 */
function isNull(v,defaultValue){
	if(v.val()=='' || v.val()==null || v.val()==undefined || v.val()=='null' || $.trim(v.val())==defaultValue){
		return true;
	}
	return false;
}
/**
 * 验证手机号
 * 13段、15段、17段、18段的11位手机号返回true，否则false
 */
function mobile(val){
	return /^(13|15|17|18)\d{9}$/i.test(val);
}
/**
 * 整数返回true，否则false
 */
function integer(val){
	return /^[+]?[1-9]+\d*$/i.test(val);
}
/**
 * 价格正则，保留2位小数
 */
function price(val){
	 var userreg=/^[0-9]+([.]{1}[0-9]{1,2})?$/;
	return userreg.test(val);
}
/**
 * 获取城市编码
 * @returns 城市简码
 */
function getRequestUrlArea() {
	var areaUrl = location.href;
	//截取规则，域名变化，修改此处
	if(areaUrl && areaUrl.indexOf("tuanche") == -1) {
		var urls = areaUrl.split('/');
		if(urls[4]) {
			return urls[4];
		}
	}
	return 'bj';
}

//设置图片轮播
function setCarousel() {
	var sWidth = $(".ad-box");
	sWidth.css('position','relative');
	var ul = sWidth.find('ul');
	ul.css('position','absolute');
	var li = ul.find('li');
	var liWidth = li.width();
	var liMargin = li.css('margin-left');
	var imgWidth = parseFloat(sWidth.width()) || sWidth.prop("offsetWidth");
	ul.css("width", imgWidth*li.length + 'px');
	li.css("width", liWidth).css('margin-left',liMargin).css('margin-right',liMargin);
	//控制滚动图片高度
	var liimg=li.find('img');
	sWidth.css("height", liWidth/1.95 + 'px');
	ul.css("height", liWidth/1.95 + 'px');
	li.css("height", liWidth/1.95 + 'px');
	liimg.css("height", liWidth/1.95 + 'px');
	return imgWidth;
}
//////////////////////////////图片轮播及滑动切换begin/////////////////////////////
//图片轮播
function carousel(){
	var imgWidth = setCarousel();
	
	var timer = setInterval(function() {
		showPics(imgWidth);
	}, 3000);
	
	function showPics(imgWidth) {
		var ul = $(".ad-box").find('ul');
		$(".ad-box ul").animate({
			"left" : "-" + (imgWidth/2) + 'px'
		}, 800, function() {
			ul.children("li:first").insertAfter(ul.children("li:last"));
			ul.css("left", 0);
	    });
	}
	return timer;
}
function imageSlide(imgWidth){
	var diff = 0;
	$(".ad-box ul li").on("mousedown touchstart", function(e) {
	    var startX = e.pageX || e.originalEvent.touches[0].pageX,
	        winW = $(window).width();
	    diff = 0;
	    $(".ad-box ul li").on("mousemove touchmove", function(e) {
	      var x = e.pageX || e.originalEvent.touches[0].pageX;
	      diff = (startX - x) / winW * 70;
	    });
	  });
	
	$(".ad-box ul li").on("mouseup touchend", function(e) {
		clearInterval(timer);
		$(".ad-box ul li").off("mousemove touchmove");
		var ul = $(".ad-box").find('ul');
	    if (diff >= 8) {
	    	$(".ad-box ul").animate({
	    		"left" : "-" + (imgWidth/2) + 'px'
	    	}, 800, function() {
	    		ul.css("left", 0);
	    		ul.children("li:first").insertAfter(ul.children("li:last"));
	        });
	    }
	    if (diff <= -8) {
	    	ul.children("li:last").insertBefore(ul.children("li:first"));
	    	ul.css("left", "-"+(imgWidth/2)+"px");
	    	$(".ad-box ul").animate({
	    		"left" : '0px'
	    	}, 800, function() {
	    		ul.css("left", 0);
	        });
	    }
	    timer = carousel();
	  });
}
////////////////////////////图片轮播及滑动切换end/////////////////////////////
//车型字母锚点处理
$(".character").delegate("a","click",function(){
    var letter_i = $(this).text().toLowerCase();
    $("#car1 dl, #city1 dl").each(function(){
        var letter_c = $(this).find("dt").text().toLowerCase();
        if(letter_i == letter_c){
        	$(window).scrollTop($(this).position().top);
        }
    });
});
