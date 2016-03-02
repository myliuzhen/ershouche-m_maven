$(function(){
	$("header,.mask").click(function(){
		$(".slidOpen,.mask").css("display","none");
		$(".searchType .typeact").attr("class","typeC");
	});
	//条件点击
	$("#priceC,#ageC,#tC").click(function(){
		var s=$(this).attr("id");
		$(".searchType .typeact").attr("class","typeC");
		if($("."+s).is(":visible")){
			$("."+s).css("display","none");
			$(".mask").css("display","none");
		}else{
			$(".mask").css("display","inline");
			$(".slidOpen").css("display","none");
			$(this).addClass("typeact");
			if(s!="tC") $("."+s).css("display","inline");
		}
	});
	
	//排序点击
	$(".sortC[class$='sortC']").click(function(){
		var s1=$(this).find("div[class *='arrowTopact']");
		var s2=$(this).find("div[class *='arrowBottomact']");
		$(".arrow-top,.arrow-bottom").removeClass("arrowTopact arrowBottomact");
		if(s1[0]){
			$(this).find("div[class *='arrow-top']").removeClass("arrowTopact");
			$(this).find("div[class *='arrow-bottom']").addClass("arrowBottomact");
		}else if(s2[0]){
			$(this).find("div[class *='arrow-top']").addClass("arrowTopact");
			$(this).find("div[class *='arrow-bottom']").removeClass("arrowBottomact");
		}else{
			$(this).find("div[class *='arrow-top']").addClass("arrowTopact");
		}
		selectCaseList();
	});
	
	//选择a 点击
	$(".slidOpenC a").click(function(){
		var s=$(this).hasClass("fred");
		if(s){$(this).removeClass("fred");
		}else{
			$(this).parent().find("a").removeClass("fred"); $(this).addClass("fred");
		}
		$(this).parent().parent().css("display","none");
		$(".mask").css("display","none");
		$(".searchType .typeact").attr("class","typeC");
		if($(this).attr("id")=='buxian'){if($(this).hasClass("fred")){ $(".hotBrands dd").removeClass("fred");} }
		selectCaseList();
	});
	
	//品牌点击
	$(".hotBrands dd").click(function(){
		var s=$(this).hasClass("fred");
		$(".hotBrands dd").removeClass("fred");
		if(!s){
			$(this).addClass("fred");
			$("#buxian").removeClass("fred");
		}
		$(".tC").css("display","none");
		$(".mask").css("display","none");
		$(".searchType .typeact").attr("class","typeC");
		selectCaseList();
	});
	
	selectCaseList();
	
	//滚动
	 var page=1;
	 $(window).bind("scroll", function(event){
		if($(".caseList ul li").length==10) page=1;
		var h=$(".caseList ul li").height()*($(".caseList ul li").length-3);
		var t=$(window).scrollTop();
		if(h<=t&&$(".caseList").attr("flag")=="0"&&$(".sCarType1").css("display")=='none'){
			if($(".caseList ul li").length%10==0){
				page=$(".caseList ul li").length/10+1;
				selectCaseList(page);
			}
		}
	 });
	 $(".hotBrands dd").eq(11).nextAll().css("display","none");//品牌多余的隐藏
	 
	 initCarModelSelectConfig();
});

function brandsMoreList(){
	 var t= $("#brandsMore").html();
	 if(t=='点击收缩'){
		 $(".hotBrands dd").eq(11).nextAll().css("display","none");
		  $("#brandsMore").html("点击查看更多");
		  $('html, body').animate({scrollTop:0}, 'slow');
	 }else{
		 $(".hotBrands dd").eq(11).nextAll().css("display","inline");
		 $("#brandsMore").html("点击收缩");
	 }
	 
}
var caseListReq="";//列表请求参数
//筛选成交车辆
function selectCaseList(page){
	var caseListReq1="";
	if(null!=sessionStorage.getItem("caseListReq"))caseListReq1=JSON.parse(sessionStorage.getItem("caseListReq"));
	sessionStorage.removeItem("caseListReq");
	var caseDto={};
	if(null!=caseListReq1&&caseListReq1!=""){
		if(caseListReq1.ageop!=undefined)$("#"+caseListReq1.ageop).attr("class","fred");
		if(caseListReq1.priceop!=undefined)$("#"+caseListReq1.priceop).attr("class","fred");
		if(caseListReq1.modelop!=undefined){
			$('#tC').data('m',caseListReq1.modelop);
		}
		//if(caseListReq1.modelop!=undefined)$("dd em p['text=']").attr("class","fred");
		$("div").find("div[class *='arrow-bottom']").removeClass("arrowBottomact");
		$("div").find("div[class *='arrow-top']").removeClass("arrowTopact");
		if(caseListReq1.orderByop!=undefined&&$("#"+caseListReq1.orderByop).attr("class")=='arrow-bottom'){
			$("#"+caseListReq1.orderByop).addClass("arrowBottomact");
		}else if(caseListReq1.orderByop!=undefined&&$("#"+caseListReq1.orderByop).attr("class")=='arrow-top'){
			$("#"+caseListReq1.orderByop).addClass("arrowTopact");
		}
		caseDto=caseListReq1;
	}else{
		var bottom=$(".arrowBottomact").attr("id");
		var top=$(".arrowTopact").attr("id");
		caseDto.orderByop=(bottom||top);
		caseDto.ageop=$(".ageC .slidOpenC a[class='fred']").attr("id");
		caseDto.priceop=$(".priceC .slidOpenC a[class='fred']").attr("id");
		//caseDto.modelop=$(".hotBrands dd[class='fred'] p").html();
		var man=$("#car1 ul li[class='fred']").attr("brandname");
		if(man!=undefined) caseDto.modelop=man;//品牌的筛选
	}
	if(null==page) page=1;
	caseDto.nowPage=page;
	caseDto.pageSize=10;
	$(".caseList").attr("flag","1");
	console.log(caseDto);
	$.ajax({
		url:context+"/maiche/getDealedCarsList",
		type:"post",
		data:caseDto,
		async:false,
		success:function(data){
			if(data.returnCode=='000000'){
				if(data.rows!=null){
					var firstCarCode="";
					var lastCarCode="";
					if($(".caseList ul li").length>0){
						firstCarCode=$(".caseList ul li:first").attr("id");
						lastCarCode=$(".caseList ul li:last").attr("id");
					}
					var s="";
					var firstStr="";
					var secondStr="";
					var thirdStr="";
					var dd=data.rows;
					if(dd.length>0){
						 for(var i=0;i<dd.length;i++){
							 if(firstCarCode==dd[i].carCode&&dd.length>10){firstStr=s;s="";secondStr=true;}
							 if(lastCarCode==dd[i].carCode&&dd.length>10){secondStr=s;s="";continue;}
							   var img=dd[i]['img_URL'];
							   var imgUrl = img.replace("${carCode}",dd[i]['carCode']).replace("${carImg}",dd[i]['carFirstImg']).replace("${width}","232").replace("${height}","168");
								s=s+'<li id="'+dd[i].carCode+'" onclick="toCaseDetail(\''+dd[i].carCode+'\')">'+
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
						 thirdStr=s;
						 $("#showEmptyID").hide();
					}else{
						$("#showEmptyID").show();
					}
					if(secondStr!=""&&$(".caseList ul li").length>0&&dd.length>10){
						$(".caseList ul").prepend(firstStr);
						$(".caseList ul").append(thirdStr);
					}else{
						$(".caseList ul").html(thirdStr);
					}
				}
				caseListReq=caseDto;
			}
			 $(".caseList").attr("flag","0");
		}
	});
}

//跳转到卖车的详细
function toCaseDetail(caseCarCode){
	if(caseListReq!="")sessionStorage.setItem("caseListReq", JSON.stringify (caseListReq));
	setCookie("caseCarCode",caseCarCode,context+'/'+getRequestUrlArea());
	var cityId = getRequestUrlArea(); 
	window.location.href=context+"/"+cityId+"/caseDetail";
}

function initCarModelSelectConfig(){
	$("#tC").click(function(){
		var carType1 = $(this).data('carType1');
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
		$(".slidOpen,.mask").css("display","none");
		$(".searchType .typeact").attr("class","typeC");
	});
	
}

//carType1解析
function setHtmlCarType1(data) {
	var modelbuxianClass=$("#modelbuxian").attr("class");
	var str = '';
	var dataList;
	for(var key in data) {
		dataList = data[key]; 
		if(dataList) {
			str += '<dl><dt>'+key+'</dt>';
			str += '<dd><ul>';
			for(var i = 0; i < dataList.length; i++) {
				var classStr="";
				if(null!=$("#tC").data("m")&&dataList[i].brand_name==$("#tC").data("m")) classStr=' class="fred" ';
				str += '<li brandid="'+ dataList[i].brand_id +'" brandname="'+ dataList[i].brand_name +'"'+classStr+'>'+ dataList[i].brand_name + '</li>';
			}
		}
		str += '</ul></dd></dl>';
	}
	$('#car1').html(str);
	if(modelbuxianClass=='fred'){modelbuxianClass=' class="fred" ';}else{modelbuxianClass='';}
	$("#car1").prepend('<dl  style="cursor:pointer;"><dt><a id="modelbuxian" '+modelbuxianClass+'>全部</a></dt><dd></dd></dl>');
	
	//点击车型全部 modelbuxian
	$("#modelbuxian").parent().click(function(){
		$("#tC").data("m",null);
		$('#car1 dl dd ul li').removeClass("fred");
		var s=$("#modelbuxian").hasClass("fred");
		if(s){$("#modelbuxian").removeClass("fred");
		}else{$("#modelbuxian").addClass("fred");}
		selectCaseList();
		$('.sCarType1').hide();
		$(".slidOpen,.mask").css("display","none");
		$(".searchType .typeact").attr("class","typeC");
	});
	
	
	//根据brandId请求车型接口2
	$('#car1 dl dd ul li').on('click',function(){
		 $("#modelbuxian").removeClass("fred");
		   if($(this).attr("class")!='fred'){
				var brandId = $(this).attr("brandid");
				$('#tC').data('carType1',brandId);
				$('#tC').data('m',$(this).attr("brandname"));
				$("#car1 ul li").removeClass("fred");
				$(this).addClass("fred");
			}else{
				$(this).removeClass("fred");
				$("#tC").data("m","");
			}
			selectCaseList();
			$('.sCarType1').hide();
			$(".slidOpen,.mask").css("display","none");
			$(".searchType .typeact").attr("class","typeC");
	});
	
}



