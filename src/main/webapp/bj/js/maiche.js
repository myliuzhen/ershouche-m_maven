$(function(){
	setInterval(function(){
		var reqMember = randomBuyerInfo();
		$('#sed').html(reqMember.second);
		$('#rqName').html(reqMember.name + ' ');
	},5000);
	
	//手机号校验
	$("#phoneNo").keyup(function(){
		 this.value = this.value.replace(/\D|^0/g, '');//.replace(/(\d{4})(?=\d)/g, "$1 ");
	});
	$("#phoneNo").blur(function(){
		if(!mobile(this.value)){ 
			$(this).siblings("em").show();
			$(this).val('');
		}else{
			$(this).siblings("em").hide();
		}
	});
	/** 预约卖车请求处理*/
	$(".maicheSubmit").click(function(event){
		var mob=$("#phoneNo").val();
		var carModel=$("#budget").val();//车型
		//校验手机号
		if(!mobile(mob)){
			$("#phoneNo").siblings("em").show();
        	$("#phoneNo").val('');
        	$("#phoneNo").focus();
            return;
		}
		//校验车型
		var carModelV=$("#budget").val();//车型
        if (carModelV =='0' || carModelV=='') {
            alert("请选择车型！");
            return;
        }
		var user = new Object();
		user.mobile = $.trim(mob);
		user.visitSite = $.trim(window.location.host);
		user.carModel = $.trim(carModel);
		user.cityId = getRequestUrlArea(); //传递cityCode,后台转换为cityId
		user.fromT = (getCookieValue("ershouchefromT")=='null'?'':getCookieValue("ershouchefromT"));
		user.brandName = (getCookieValue("ershouchebrand")=='null'?'':getCookieValue("ershouchebrand"));
		user.business =(getCookieValue("business")=='null'?'':getCookieValue("business"));
		user.biddingCode = (getCookieValue("ershouchecode")=='null'?'':getCookieValue("ershouchecode"));
		user.biddingKid = (getCookieValue("ershouchebdkid")=='null'?'':getCookieValue("ershouchebdkid"));
		user.qc=(getCookieValue("ershoucheqc")=='null'?'':getCookieValue("ershoucheqc"));
		user.applyId=(getCookieValue("ershoucheapplyId")=='undefined'?'':getCookieValue("ershoucheapplyId"));
		$.ajax({
			type:"post",
			url: context +"/maiche/insertRegister",
			data:user,	
			success:function(data){
				if(data.returnCode=='000000'){
					window.location.href=context+"/"+user.cityId+"/maicheSuccess";
				}
			}
		});
	});
	
	initddealedCars();
	initCarModelSelectConfig();
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
	$(".process").click(function(){
		$('html, body').animate({scrollTop:0}, 'slow');
	});
});

//初始化页面的成交车辆信息
function initddealedCars(){
	var d={"nowPage":1,"pageSize":4};
	$.ajax({
		type:"post",
		data:d,
		url: context +"/maiche/getDealedCars",
		success:function(data){
			 if(data.returnCode=='000000'){
			 var dd =data.rows;
			 var s='';
			 if(null!=dd&& dd.length>0){
				 for(var i=0;i<dd.length;i++){
					   var img=dd[i]['img_URL'];
					   var imgUrl = img.replace("${carCode}",dd[i]['carCode']).replace("${carImg}","b"+dd[i]['carFirstImg']).replace("${width}","232").replace("${height}","168");
						s=s+'<li onclick="toCaseDetail(\''+dd[i].carCode+'\')">'+
						'<div class="imgBox"><img src="'+imgUrl+'" alt="团车二手车"></div>'+
						'<div class="caseText">'+
							'<h4>'+dd[i].carInfo+'</h4>'+
							'<span class="standard">'+dd[i].formatRegDate+'/'+dd[i].formatMileage+'/'+dd[i].registry.split("-")[0]+'/'+dd[i].emissionGb+'</span>'+
							'<div class="carPrice">'+
								'<span>￥'+dd[i].formatPrice+'</span>'+
								'<div class="prC prC-left"><samp>多卖'+dd[i].extraIncome+'元</samp><div class="arrow-left">箭头</div></div>'+
							'</div>'+
						'</div>'+
					'</li>';
				 }
			 }
			 $("#dealedCars").html(s);
			}
		}
	});
}

//初始化旧车车型
function initCarModelSelectConfig(){
	//车型选择
	$('#budget').on('click', function() {
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
			url : context + '/car/getCarBrands',
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
}

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
		var brandId = $(this).attr("brandid");
		$('#budget').data('carType1',brandId);
		$('#budget').data('m',$(this).attr("brandname"));
		//请求车型接口2
		$.ajax({
			url : context + '/car/getCarSeries/'+brandId,
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
		$('html, body').animate({scrollTop:0}, 'slow');
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
		var seriesId = $(this).attr("seriesid");
		$('#budget').data('carType2',seriesId);
		$('#budget').data('s',$(this).attr("seriesname"));
		//请求车型接口3
		$.ajax({
			url : context + '/car/getCarModels/'+seriesId,
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
		$('html, body').animate({scrollTop:0}, 'slow');
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
			/*	str += '<span>'+ dataList[i].model_price +'万</span>';*/
				str += '</dd>';
			}
			str += '</dl>';
		}
	}
	$('#car3').html(str);
	
	$('#car3 dd').on('click',function(){
		var $model = $(this);
		var modelId = $model.find('p').attr('modelid');
		var modelName = $model.find('p').attr('modelname');
		$('#budget').data('carType3',modelId).val($('#budget').data('m')+' '+$('#budget').data('s')+' '+modelName);
		$('.sCarType3').hide();
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

