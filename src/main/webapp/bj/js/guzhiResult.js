
$(document).ready(function(){
	var href = window.location.href;
	var carImg = href.GetValue("carImg");
	var carInfo = href.GetValue("carInfo"); //宝马 X5(进口)  xDrive35i 3.0T 手自一体 豪华型
	var modelId = href.GetValue("modelId");
	var zone = href.GetValue("zone");
	var pfbz = href.GetValue("pfbz");
	if(null==pfbz || ""==pfbz || "null"==pfbz){pfbz="未知";}
	var mileage = href.GetValue("mileage");
	var regDate = href.GetValue("regDate");
	var price = href.GetValue("price");
	var evalPrice = href.GetValue("evalPrice");
	evalPrice = parseFloat(parseFloat(evalPrice)*1.3).toFixed(2);
	
	//$("#carImg").attr("src",carImg);
	$('#carName').html(carInfo);
	$("#pfbz").html(pfbz);
	$("#mileage").html(mileage?mileage+'万公里':'');
	$("#regDate").html(regDate);
	$("#price").html(price?price+'万元':'');
	$("#evalPrice").html(evalPrice?evalPrice+'万元':'');
	priceCurveChart(modelId,zone,regDate,mileage);
	loadcookie();
});
/**
 * cookie取值判断渠道 add by liuzhen 2015.09.08
 */
function loadcookie(){
	var _wantcarhref = document.location.href;
	var wantcarV=_wantcarhref.GetValue('v');
	if(null==wantcarV || ""==wantcarV || undefined==wantcarV){
		wantcarV = getCookieValue("ershouchefromT");
	}
	if(null==wantcarV || ""==wantcarV || undefined==wantcarV){
		wantcarV = "1001";
	}
	if(_wantcarhref.indexOf('v=')==-1){
		document.location.href=document.location.href+"&v="+wantcarV;
	}
}
/**
 * 价格行情折线图
 */
function priceCurveChart(modelId,zone,regDate,mile){
	var param = new Object();
	param.modelId = modelId;
	param.zone = zone;
	param.regDate = regDate;
	param.mile = mile;
	$.post(context+"/guzhi/getPriceTrend",param,function(data){
		if(data){
			dreawTrend(data);
		}else{
			$("#priceCurve").html("<font style='color:red'>无法获取价格趋势走势图！</font>");
		}
	});
}

function dreawTrend(priceList){
	var xAxisData = new Array();
	var seriesData = new Array();
	for(var i=0,j=priceList.length;i<j;i++){
		xAxisData.push(priceList[i].register_year+"年");
		var aa = parseFloat(priceList[i].eval_price)*1.3;
		var bb = aa.toFixed(2);
		seriesData.push(parseFloat(bb));
	}
	var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'priceCurve',
            type: 'bar'
        },
        title: {
            text: ''
        },
        xAxis: {
            categories: xAxisData
        },
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        legend: {
            backgroundColor: '#FFFFFF',
            reversed: true
        },
        tooltip: {
            formatter: function() {
                return ''+this.x + '<br/>●价值:' + this.y +'万元';
            }
        },
        plotOptions: {
            series: {
                stacking: 'normal'
            }
        },
        series: [{
            name: '评估车价(万元)',
            data: seriesData
        }]
    });
}