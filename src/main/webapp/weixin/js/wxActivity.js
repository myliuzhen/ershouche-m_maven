//跳转领红包链接
var toWXgift = 'http://weixin.tuanche.net/wxznq/html/gift.html';
//跳转新车买车
var toNewCar = 'http://m.tuanche.com/wxapply/?sid=91';
//跳转活动
var wxActivity = 'http://weixin.tuanche.net/WxGiftUserController/index';
var context = '/ershouche';
var timer;
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
    	$('#bmask').hide();
        return true;
    }else{
    	$('#bmask').show();
    	alert('请在微信中打开此链接：' + wxActivity);
        return false;
    }
}
isWeiXin();
jQuery(function(){
	$.ajax({
		type:"post",
		url: context + "/wxGiftUserController/getWxJsConfigToJson",
		data:{"url":window.location.href},
		dataType:'json', 
		success:function(data){
			wx.config({
			    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: data.appId, // 必填，公众号的唯一标识
			    timestamp: data.timestamp, // 必填，生成签名的时间戳
			    nonceStr: data.nonceStr, // 必填，生成签名的随机串
			    signature: data.signature,// 必填，签名，见附录1
			    jsApiList: ['onMenuShareAppMessage','showOptionMenu','showMenuItems','hideOptionMenu','onMenuShareTimeline','onMenuShareQQ'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
		}
	});
	
	//判断是否抢完
	var noGift = location.href.GetValue('noGift');
	if(noGift && noGift === '0') {
		inputDisabled('1');
		$('#bmask').show();
		$('.pop01').show();
	}
	
	getCarListData();
	inputDisabled('0');
	inputClear();
	
	$('#carSelect').data('check', '1');
	
	function inputClear() {
		$('#citySelect').data('cityId', '').val('');
		$('#carModelSelect').data('carType1','').data('carType2','').val('');
		$('#phoneNo').val('');
		$('#code').val('');
		$('#carSelect dd').removeClass('check');
		$('#carSelect').find('dd').first().addClass('check');
	}
	
	function inputDisabled(type) {
		//输入框禁用
		if(type === '1') {
			$('#citySelect').prop('disabled',true);
			$('.sumbitC').prop('disabled', true);
		}
		$('#carModelSelect').prop('disabled',true);
		$('#phoneNo').prop('disabled',true);
		$('#verifyCode').prop('disabled',true);
		$('#code').prop('disabled',true);
	}
	
	//预约报名
	$('#newCarAD').on('click', function() {
		location.href = 'http://m.tuanche.com/bj/c166/tuan/';
	});
	$('#newCarDZ').on('click', function() {
		location.href = 'http://m.tuanche.com/bj/c282/tuan/';
	});
	$('#toNewCar').on('click', function() {
		location.href = toNewCar;
	});
	//关闭覆层
	$('.PClose').on('click', function() {
		$('.popC, #bmask').hide();
	});
	//显示活动说明
	$('#rule').on('click', function() {
		$('#bmask').show();
		$('.pop04').show();
	});
	
	//type1关闭
	$('.selectType1').on('click',function() {
		$('.sCarType1').hide();
		phoneInputOpen();
	});
	
	//type2关闭type1
	$('.selectType2').on('click',function() {
		$('.sCarType2').hide();
		$('.sCarType1').show();
	});
	
	//city关闭
	$('.selectCity1').on('click', function() {
		$('.sCity1').hide();
		var cityId = $('#citySelect').data('cityId');
		if(cityId == null || cityId == '') {
			$('#carModelSelect').prop('disabled',true);
			$('#cityWrong').show();
		} else {
			$('#carModelSelect').prop('disabled',false);
			$('#cityWrong').hide();
		}
	});
	
	$('.wx-attention').on('click', function() {
		location.href = 'http://mp.weixin.qq.com/s?__biz=MzAxMTM0MTIxMw==&mid=401721736&idx=1&sn=95cbbd974b11dbe6f40e64cae204003e#rd';
	});
	
	//判断车型选择，是否开启手机号输入框
	function phoneInputOpen() {
		var carType2 = $('#carModelSelect').data('carType2');
		if(carType2 != null && carType2 != '') {
			$('#phoneNo').prop('disabled',false);
			$('#verifyCode').prop('disabled',false);
			$('#carModelWrong').hide();
		} else {
			$('#phoneNo').prop('disabled',true);
			$('#verifyCode').prop('disabled',true);
			$('#carModelWrong').show();
		}
	}
	
	//底边报名关闭
	$('.btclose').on('click', function() {
		$('.bottomC').hide();
	});
	
	//购车方式选择
	$('#carSelect dd').on('click', function() {
		$('#carSelect dd').removeClass('check');
		var $this = $(this);
		$this.addClass('check');
		$('#carSelect').data('check', $this.attr('data-check'));
	});
	
	//手机号
	$('#phoneNo').on('blur', function() {
		var phone = $('#phoneNo').val();
		if(phone == null || phone == '') {
			$('#phoneWrong').show();
			$('#code').prop('disabled',true);
			return;
		}
		if (!mobile(phone)) {
			alert("手机号码有误，请重新输入!");
			$('#phoneWrong').show();
			$('#code').prop('disabled',true);
			return;
		}
		$('#phoneWrong').hide();
		$('#code').prop('disabled',false);
	}).on('keyup', function() {
		var $this = $(this);
		$this.val($this.val().replace(/\D/g,''));
	});
	
	//验证码
	$('#code').on('click', function() {
		var code = $('#code').val();
		if(code == null || code == '') {
			$('#codeWrong').show();
			return;
		}
		$('#codeWrong').hide();
	}).on('keyup', function() {
		var $this = $(this);
		$this.val($this.val().replace(/\D/g,''));
	});
	
	//城市
	$('#citySelect').on('click',function() {
		$('html, body').animate({scrollTop:0});
		var $this = $(this);
		var city1 = $this.data('cityId');
		if(city1 != null && city1 != '') {
			$('.sCity1').show();
			return;
		}
		//请求城市接口
		$.ajax({
			url : context + '/wxGiftUserController/getAllCityInOpenStatus',
			type : "get",
			success : function(data) {
				if(data.code == '10000') {
					setHtmlCityInfo(data.result);
					$('.sCity1').show();
				} else {
					alert('无城市数据！');
				}
			}
		});
	});
	
	//品牌
	$('#carModelSelect').on('click', function() {
		$('html, body').animate({scrollTop:0});
		var $this = $(this);
		var carType1 = $this.data('carType1');
		var carType2 = $this.data('carType2');
		if(carType2 != null && carType2 != '') {
			$('.sCarType2').show();
			return;
		}
		if(carType1 != null && carType1 != '') {
			$('.sCarType1').show();
			return;
		}
		//获取城市
		var cityId = $('#citySelect').data('cityId');
		$.ajax({
			type:"post",
			url: context + "/wxGiftUserController/getBrandMap",
			data : {cityId:cityId},
			dataType:'json',
			success:function(data){
				if(data.code == '10000') {
					var datalist = data.result;
					if(!datalist || datalist.length == 0) {
						alert('很抱歉！您所在区域暂未开通团车业务，您可选择就近区域或致电客服（4006969123）询问详情');
					} else {
						setHtmlCarType1(datalist, cityId);
						$('.sCarType1').show();
						$('.sCarType2').hide();
					}
				} else {
					$('.sCarType1').hide();
				}
			}
		});
	});
	
	//获取验证码
	$('#verifyCode').on('click', function() {
		var phone = $('#phoneNo').val();
		if(phone == null || phone == '') {
			$('#phoneWrong').show();
			return;
		}
		if (!mobile(phone)) {
			alert("手机号码有误，请重新输入!");
			$('#phoneWrong').show();
			return;
		}
		clock("60", "verifyCode");
		$(this).prop('disabled',true);
		$.ajax({
			url : context + '/wxGiftUserController/sendVerifyCode',
			type : "post",
			dataType:'json',
			data : {mobile:phone},
			success : function(data) {
				if(data.returnCode === '000000') {
					$('#code').prop('disabled',false);
					alert(data.returnMsg);
				} else if(data.returnCode === '100200') {
					$('#code').prop('disabled',true);
					alert(data.returnMsg);
				} else {
					alert('请刷新重试!');
					$('#code').prop('disabled',true);
				}
			}
		});
	});
	
	//点击领取红包
	$('.sumbitC').on('click',function() {
		$('.wrong').hide();
		var city = $('#citySelect');
		if(city.data('cityId') == null || city.data('cityId') == '') {
			$('#cityWrong').show();
			return;
		}
		var carModle = $('#carModelSelect');
		if(carModle.data('carType2') == null || carModle.data('carType2') == '') {
			$('#carModelWrong').show();
			return;
		}
		var phone = $('#phoneNo').val();
		if(phone == null || phone == '') {
			$('#phoneWrong').show();
			return;
		}
		if (!mobile(phone)) {
			alert("手机号码有误，请重新输入!");
			$('#phoneWrong').show();
			return;
		}
		var code = $('#code').val();
		if(code == null || code == '') {
			$('#codeWrong').show();
			return;
		}
		
		var saveFlag = false;
		//调用验证接口
		$.ajax({
			url : context + '/wxGiftUserController/checkVerifyCode',
			type : "post",
			data : {mobile:phone,verifyCode:code},
			async : false,
			dataType : 'json',
			success : function(data) {
				if(data.returnCode == '000000') {
					saveFlag = true;
				} else if(data.returnCode == '500000') { //活动结束
					inputDisabled('1');
					$('#bmask').show();
					$('.pop02').show();
					saveFlag = false;
				} else {
					alert(data.returnMsg);
					saveFlag = false;
				}
			}
		});
		if(saveFlag) {
			var wxObject = {};
			wxObject.wechatId = location.href.GetValue('openId');
			wxObject.cityId = city.data('cityId');
			wxObject.cityName = city.val();
			wxObject.brandId = carModle.data('carType1');
			wxObject.modelId = carModle.data('carType2');
			wxObject.modelInfo = carModle.val();
			wxObject.buyWay = $('#carSelect').data('check');
			wxObject.cityName = city.val();
			wxObject.mobile = phone;
			
			//保存接口
			$.post(context + '/wxGiftUserController/receive',wxObject,function(data){
				if(data.returnCode == '000000') { //跳转到微信领红包页
					location.href = toWXgift + '?isFirst=1&mobile=' + wxObject.mobile;
				} else if(data.returnCode == '200100') { //抢完了
					inputDisabled('1');
					$('#bmask').show();
					$('.pop01').show();
				} else {
					alert(data.returnMsg);
				}
			});
		}
	});
	
	
	//图片轮播
	timer = carousel();
	var imgWidth = setCarousel();
	imageSlide(imgWidth);
});

//隐藏菜单
wx.ready(function(){
	wx.hideOptionMenu();
});

//验证码倒计时
var timerClock = null;
function clock(i, id){
	clearTimeout(timerClock);
    i = i - 1;
    if (i > 0) {
    	timerClock =  setTimeout("clock('" + i + "','" + id + "');", 1000);
        $('#' + id).html(i+"s重新发送");
    }
    else {
        $("#" + id).prop("disabled", false).html("获取验证码");
    }
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

//城市解析
function setHtmlCityInfo(datalist) {
	var str = '';
	var letter = '';
	for(var i = 0; i < datalist.length; i++) {
		if(letter === '') {
			letter = datalist[i].py.substr(0,1);
			str += '<dl><dt>' + letter + '</dt>';
			str += '<dd cityId="'+ datalist[i].id +'" cityName="'+ datalist[i].name +'">' + datalist[i].name + '</dd>';
		} else if(letter === datalist[i].py.substr(0,1)) {
			str += '<dd cityId="'+ datalist[i].id +'" cityName="'+ datalist[i].name +'">' + datalist[i].name + '</dd>';
		} else if(letter !== datalist[i].py.substr(0,1)) {
			letter = datalist[i].py.substr(0,1);
			str += '</dl>';
			str += '<dl><dt>' + letter + '</dt>';
			str += '<dd cityId="'+ datalist[i].id +'" cityName="'+ datalist[i].name +'">' + datalist[i].name + '</dd>';
		}
	}
	str += '</dl>';
	$('#city1').html(str);
	
	//添加点击事件
	$('#city1 dl dd').on('click',function() {
		$('html, body').animate({scrollTop:0});
		var $city = $(this);
		$('#citySelect').data('cityId', $city.attr('cityId')).val($city.attr('cityName'));
		$('.sCity1').hide();
		$('#carModelSelect').prop('disabled',false);
		$('#cityWrong').hide();
	});
}

//品牌解析
function setHtmlCarType1(datalist, cityId) {
	var str = '';
	var letter = '';
	for(var i = 0; i < datalist.length; i++) {
		if(letter === '') {
			letter = datalist[i].penname;
			str += '<dl><dt>'+datalist[i].penname+'</dt>';
			str += '<dd><ul>';
			str += '<li brandid="'+ datalist[i].id +'" brandname="'+ datalist[i].name +'">'+ datalist[i].name + '</li>';
		} else if(letter === datalist[i].penname) {
			str += '<li brandid="'+ datalist[i].id +'" brandname="'+ datalist[i].name +'">'+ datalist[i].name + '</li>';
		} else if(letter !== datalist[i].penname) {
			letter = datalist[i].penname;
			str += '</ul></dd></dl>';
			str += '<dl><dt>'+datalist[i].penname+'</dt>';
			str += '<dd><ul>';
			str += '<li brandid="'+ datalist[i].id +'" brandname="'+ datalist[i].name +'">'+ datalist[i].name + '</li>';
		}
	}
	str += '</ul></dd></dl>';
	$('#car1').html(str);
	
	//车型
	$('#car1 dl dd ul li').on('click',function() { //查询车型
		$('html, body').animate({scrollTop:0});
		var brandId = $(this).attr("brandid");
		$('#carModelSelect').data('carType1',brandId);
		//请求车型接口2
		$.ajax({
			url : context + '/wxGiftUserController/getStyleMap',
			data : {brandId:brandId,cityId:cityId},
			type : "post",
			dataType:'json',
			success : function(data) {
				if(data.code == '10000') {
					var typelist = '';
					var typestr = '';
					var styleName = '';
					var cartype = data.result;
					for(var m = 0; m < cartype.length; m++) {
						typelist = cartype[m].styleList;
						for(var i = 0; i < typelist.length; i++) {
							if(styleName === '') {
								styleName = typelist[i].brandName;
								typestr += '<dl><dt>'+styleName+'</dt>';
								typestr += '<dd>';
								typestr += '<p modelid="'+ typelist[i].id +'" modelname="'+ typelist[i].styleName +'">' + typelist[i].styleName + '</p>';
								typestr += '<span>'+ typelist[i].factoryPrice +'</span>';
								typestr += '</dd>';
							} else if(styleName === typelist[i].brandName) {
								typestr += '<dd>';
								typestr += '<p modelid="'+ typelist[i].id +'" modelname="'+ typelist[i].styleName +'">' + typelist[i].styleName + '</p>';
								typestr += '<span>'+ typelist[i].factoryPrice +'</span>';
								typestr += '</dd>';
							} else if(styleName !== typelist[i].brandName) {
								styleName = typelist[i].brandName;
								typestr += '</dl>';
								typestr += '<dl><dt>'+styleName+'</dt>';
								typestr += '<dd>';
								typestr += '<p modelid="'+ typelist[i].id +'" modelname="'+ typelist[i].styleName +'">' + typelist[i].styleName + '</p>';
								typestr += '<span>'+ typelist[i].factoryPrice +'</span>';
								typestr += '</dd>';
							}
						}
						typestr += '</dl>';
					}
					$('#car2').html(typestr);
					$('.sCarType1').hide();
					$('.sCarType2').show();
				} else {
					$('.sCarType2').hide();
				}
				$('#car2 dd').on('click',function() {
					$('html, body').animate({scrollTop:0});
					var $model = $(this);
					var modelId = $model.find('p').attr('modelid');
					var modelName = $model.find('p').attr('modelname');
					$('#carModelSelect').data('carType2',modelId).val(modelName);
					$('.sCarType2').hide();
					$('#phoneNo').prop('disabled', false);
					$('#verifyCode').prop('disabled',false);
					$('#carModelWrong').hide();
				});
			}
		});
	});
}

/**
 * 获取并展示车辆列表
 */
function getCarListData(){
	$.ajax({
		url : context + '/gouche/getSelledCarGroupByCountryForPage',
		type : "post",
		data : {nowPage:1,pageSize: 12},
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
					var imgUrl = car.img_URL.replace("${carCode}",car.carCode).replace("${carImg}",car.carFirstImg).replace("${width}","232").replace("${height}","168");
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
					str += '</samp><div class="arrow-left">箭头</div></div><i>已成交</i>';
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
	setCookie("caseCarCode",caseCarCode,context+'/bj');
	window.location.href=context+"/bj/caseDetail";
}

function toCaseList(){
	window.location.href=context+"/bj/caseList";
}

String.prototype.GetValue=function(para) {  
	  var reg = new RegExp("(^|&)"+ para +"=([^&]*)(&|$)");  
	  var r = this.substr(this.indexOf("\?")+1).match(reg);  
	  if(r!=null && r[2].indexOf("#")>0){
		  r[2] = r[2].substring(0,r[2].indexOf("#"));
	  }
	  if (r!=null) return decodeURI(r[2]); return null;  
};