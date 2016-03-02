$(function(){
	var caseCarCode= getCookieValue("caseCarCode");
	$.ajax({
		url:context+"/carReport/getCarReport",
		data:{"carCode":caseCarCode},
		type:"post",
		success:function(data){
			if(data!=null&&null!=data[0]&&null!=data[1]){
				var car=data[0];
				var report=data[1];
				var slideImg=data[1].traCarBaseImgList;
				if(null!=slideImg){
					var s="";
					var sf="";
					for ( var i = 0; i < slideImg.length; i++) {
						s+='<li><img src="'+slideImg[i].imgName+'" alt="团车二手车"></li>';
						if(i==0){ sf+='<a href="javascript:;" class="act" style="margin-left:2px;" ></a>';}
						else {sf+='<a href="javascript:;" style="margin-left:2px;" ></a>';}
					}
					$("#slideImg").html(s);
				    $("#slideImgF").html(sf);
				    slide();
				}
				$(".adcaseText h4").html(car.carInfo);
				if(car.formatRegDate)$(".adcaseText").find("span").eq(0).html(car.formatRegDate);
				if(car.formatMileage)$(".adcaseText").find("span").eq(1).html(car.formatMileage);
				if(car.emissionGb)$(".adcaseText").find("span").eq(2).html(car.emissionGb);
				if(car.registry)$(".adcaseText").find("span").eq(3).html(car.registry.split("-")[0]);
				if(report.isAccident=='无事故'){$("#isAccident").css("display","inline");}
				$(".carPrice span").html("￥"+((data[0].cost)/10000)+"万");
				$(".prC samp").html("多卖"+car.extraIncomeStr+"元");
				var liangdian="";
				if(report.configStr){
				    	var count=0;
				    	var arr=toSort(report.configStr.split("，"));
				    	for ( var j = 0; j < arr.length; j++){
				    		var imgFlag="";
				    		var imgDesc= arr[j];
				    		if(imgDesc=='导航'){
				    			imgFlag='highlight01';
				    			count++;
				    		}else if(imgDesc=='智能钥匙'){
				    			imgFlag='highlight02';
				    			count++;
				    		}else if(imgDesc=='css定速巡航'){
				    			imgFlag='highlight03';
				    			count++;
				    		}else if(imgDesc=='有天窗'){
				    			imgFlag='highlight04';
				    		}else if(imgDesc=='倒车影像'){
				    			imgFlag='highlight05';
				    			count++;
				    		}else if(imgDesc=='安全气囊'){
				    			imgFlag='highlight06';
				    			count++;
				    		}else if(imgDesc=='多功能方向盘'){
				    			imgFlag='highlight07';
				    			count++;
				    		}else if(imgDesc=='倒车雷达'){
				    			imgFlag='highlight08';
				    			count++;
				    		}else if(imgDesc=='ABS'){
				    			imgFlag='highlight09';
				    			count++;
				    		}
				    		liangdian+='<li><em class="'+imgFlag+'"></em><span>'+imgDesc+'</span></li>';
				    		if(count==5) break;
						}
				    }else{liangdian="无";}
				$(".highlight ul").html(liangdian);
				$(".reportC #carInfo").html(car.carInfo);
				$(".reportInfo #shouxu").html(report.procedures+report.violation);
				$(".reportInfo #jixie").html(report.engine);
				$(".reportInfo #cheshen").html(report.apparent);
				$(".reportInfo #fugai").html(report.paint);
				$(".reportInfo #neishi").html(report.inside);
				$("#score").html(report.carScore);
			}
		}
	});
});

function slide(){
	//设置图片的宽高比例
	var w=$(".caseBox").width();
	var h=Math.round(w/1.75);
	$(".caseBox").css("height",h);
	var count=1;
	//设置手动滑动
	var xx,XX;
	$("#slideImg li").on('touchstart',function(event){
	     xx = event.originalEvent.touches[0].screenX ;
	 });
	$("#slideImg li").on('touchmove',function(event){
		 XX = event.originalEvent.touches[0].screenX ;
	  });
	$("#slideImg li").on('touchend',function(event){
	      var size=$("#slideImg li").size();
	      $("#slideImg li").css("width","0%");
	      $("#slideImgF a[class='act']").removeAttr("class");
	      if(XX-xx<0)  //右滑
	      {
	    	  if(count<$("#slideImg li").size()){
	    		  $("#slideImg li").eq(count).css("width","100%");
			  	  $("#slideImgF a").eq(count).attr("class","act");
	    	  }else{
	    		  count=0;
	    		  $("#slideImg li").eq(count).css("width","100%");
			  	  $("#slideImgF a").eq(count).attr("class","act");
	    	  }
	      }else{
	    	  if(count!=0){
	    		  count--;
	    		  count--;
	    	  }else{
	    		  count=size-1;
	    	  }
	    	  $("#slideImg li").eq(count).css("width","100%");
	  		  $("#slideImgF a").eq(count).attr("class","act");
	      }
	      count++;
	});
	
	//设置自动轮播
	setInterval(function(){
		$("#slideImg li").css("width","0%");
		$("#slideImgF a[class='act']").removeAttr("class");
		var c=$("#slideImg li").size();
		if(count==c){
			count=0;
		}
		if(count!=c){
			$("#slideImg li").eq(count).css("width","100%");
			$("#slideImgF a").eq(count).attr("class","act");
			count++;
		}
	}, 3000);
}

//调到卖车
function tomaiche(){
	var cityId = getRequestUrlArea(); 
	window.location.href=context+"/"+cityId+"/maiche";
}

function toSort(arr){
	for(var i=0;i<arr.length-1;i++){
		for(var j=0;j<arr.length-1;j++){
			var temp;
			if(arr[j].length>arr[j+1].length){
				temp=arr[j+1];
				arr[j+1]=arr[j];
				arr[j]=temp;
			}
		}
	}
	return arr;
}
