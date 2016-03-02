/***************************二手车全局初始加载类******************************/
/********************可包含：通用常量、渠道号规则处理、百度统计代码方法******************************/
String.prototype.GetValue=function(para) {  
	  var reg = new RegExp("(^|&)"+ para +"=([^&]*)(&|$)");  
	  var r = this.substr(this.indexOf("\?")+1).match(reg);  
	  if(r!=null && r[2].indexOf("#")>0){
		  r[2] = r[2].substring(0,r[2].indexOf("#"));
	  }
	  if (r!=null) return decodeURI(r[2]); return null;  
};
var context = "/ershouche";
/** 页面显示的车辆数 **/
var _CAR_SHOW_COUNT = "3";
/** 首页分页显示每页条数 **/
var _INDEX_PAGE_SIZE = 12;
var fromT="1001";//报名入口来源 0.竞价  1001.自然量  1101.团车首页  1201.置换报名 1301.seo  1401.微信端  1501.客户端  1601.网易火车票  1701.今日头条
//**************变量默认值**************//
var _SUBFROM_DEF_GUZHI="6";//估值对应的二级入口默认值（subFrom值）
/**
 * 初始加载
 */
$(document).ready(function() {
	//渠道号规则处理
	loadcookie();
	//百度统计
	baiduTJ();
	initParamsFromUrls();
	//头部banner添加首页跳转
	$("header").click(function(){
		location.href="/ershouche/"+getRequestUrlArea();
	});
});

//复杂的各种判断，作者已写懵圈，非专业人士勿动
/**
 * 地址栏赋值，并加入渠道号
 */
function loadcookie(){
	var retUrl = '';
	var localUrl = '';
	var _vhref = document.location.href;
	if(_vhref) {
		var urls = _vhref.split('/');
		if(!urls[4]) {//判断区域
			if(_vhref.indexOf(urls[3]+'/') !== -1) {
				localUrl = 'bj';
			} else {
				localUrl = '/bj';
			}
		} else {
			if(!urls[5]) { //判断页面
				if(_vhref.indexOf(urls[4]+'/') !== -1) {
					localUrl = '@';
				} else {
					localUrl = '/';
				}
			} else { //判断是否有斜杠
				if(_vhref.indexOf(urls[5]+'/') !== -1) {
					localUrl = '@';
				}
			}
		}
	}
	var _valueV=_vhref.GetValue('v');
	if(null==_valueV || ""==_valueV || undefined==_valueV){
		_valueV = getCookieValue("ershouchefromT");
	}
	if(null==_valueV || ""==_valueV || undefined==_valueV){
		_valueV = "1001";
	}
	setCookieTime("ershouchefromT", _valueV, '', context);
	//不存在任何参数
	if(_vhref.indexOf('?')==-1){
		if(_vhref.indexOf('v=')==-1){
			if(localUrl === '@') {
				retUrl=location.href +"?v="+_valueV;
			} else {
				retUrl=location.href + localUrl +"/?v="+_valueV;
			}
			if(retUrl.indexOf("//?") != -1) {
				retUrl = retUrl.replace("//?", "/?");
			}
			location.href = retUrl;
		}
	}else{
		if(_vhref.indexOf('v=')==-1){
			if(localUrl === '@') {
				retUrl=location.href +"&v="+_valueV;
			} else {
				if(localUrl === '/') {
					retUrl=location.href +"&v="+_valueV;
				} else {
					retUrl=location.href + localUrl +"&v="+_valueV;
				}
			}
			if(retUrl.indexOf("//?") != -1) {
				retUrl = retUrl.replace("//?", "/?");
			}
			location.href = retUrl;
		}
	}
}
//百度统计代码
function baiduTJ(){
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?9751ca2223f3fb3481dde55570cd721c";//2ad8f85482ed5ad2aa4d2221d7259314
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
}

//获取城市的cityId
function getRequestUrlArea() {
	var areaUrl = location.href;
	//截取规则，域名变化，修改此处
	if(areaUrl && areaUrl.indexOf("ershouche") != -1) {
		var urls = areaUrl.split('/');
		if(urls[4]) {
			return urls[4];
		}
	}
	return 'bj';
}

//访问地址带来的参数处理
function initParamsFromUrls(){
	var business = "0";		//业务类型:0.卖车  1.置换  2.报废  3.SEO 4.估值
	var brandName='';
	var code = getCookieValue("ershouchecode");
	var kid = getCookieValue("ershouchebdkid");
	var brand = getCookieValue("ershouchebrand");
	var qc = getCookieValue("ershoucheqc");
	var applyId = getCookieValue("ershoucheapplyId");
	//初始化code kid
	if((code == "null" || code =="") && (kid == "null" || kid=="")){
		var biddingCode = document.location.href.GetValue("code");
		var biddingKid = document.location.href.GetValue("kid");
		setCookieTime("ershouchecode",encodeURI(biddingCode) ,'', context);
		setCookieTime("ershouchekid",encodeURI(biddingKid),'', context);
	}
	//初始化applyId
	if(applyId==null || applyId==""){
		var applyId_url = document.location.href.GetValue("applyId");
		if(applyId_url!=null && applyId_url!=""){
			setCookieTime("ershoucheapplyId", applyId_url, '', context);
		}
	}
	//初始化brandname business，某些条件更改fromT
	if(brand == "null" || brand==""){
		brandName = document.location.href.GetValue("brandName");
		if(brandName != "null" && brandName != "" && brandName !=null){
			business = "1";
			fromT = "1201";
		}else{
			var s = document.location.href.GetValue("s");
			if(s){
				$.ajax({
					type: "get",
					url: "/ershouche/maiche/getKeys",
					data: {keys:encodeURI(s)},
					async:false,
					dataType: 'json',
					success: function(result){
						if(result == null) return;
						business = "1";
						fromT = "1201";
						brandName = result.brandName;
						setCookieTime("ershoucheapplyId",result.applyId,'', context);
						setCookieTime("ershouchebrand",encodeURI(brandName),'', context);
						setCookieTime("business",business,'', context);
						setCookieTime("ershouchetel",result.tel,'', context);
					}
				});
			}
		}
	}else{
		business = "1";
		fromT = "1201";
		brandName=(brand=='undefined'?'':brand);
	}
	//初始化qc
	if(qc == "null" || qc==""){
		var qctemp = document.location.href.GetValue("qc");
		setCookieTime("ershoucheqc",qctemp,'', context);
	}
	//初始化business
	setCookieTime("business",business,'', context);
	//初始化ershouchebrand
	setCookieTime("ershouchebrand",brandName,'', context);
}


//显示最新买车卖车需求
var needCarRequest = [{second:'3秒',name:'沈女士'},
                      {second:'7秒',name:'王大爷'},
                      {second:'12秒',name:'张先生'},
                      {second:'16秒',name:'刘先生'},
                      {second:'22秒',name:'李小姐'},
                      {second:'30秒',name:'肖老板'},
                      {second:'44秒',name:'赵女士'},
                      {second:'50秒',name:'牛先生'},
                     ];

function randomBuyerInfo() {
	var random = Math.floor(Math.random()*10);
	if(random <= 7) {
		return needCarRequest[random];
	} else {
		return randomBuyerInfo();
	}
}
