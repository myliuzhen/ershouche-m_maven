jQuery(function(){
	//准新车
	var zxc = {
		cTypeName:"准新车",
		typeDesc:"两年以内，犹如新车",
		cPrice:"依车型定",
		cMile:"1万以内",
		imgPath:"/ershouche/bj/img/hotimg/img-hot01.png"
	};
	//练手车
	var lsc = {
		cTypeName:"练手车",
		typeDesc:"好开不贵，练手好车",
		cPrice:"5万以内",
		cMile:"10万以内",
		imgPath:"/ershouche/bj/img/hotimg/img-hot02.png"
			
	};
	//实惠豪车
	var shhc = {
		cTypeName:"实惠豪车",
		typeDesc:"大气稳重，品质豪车",
		cPrice:"30万以上",
		cMile:"依车型定",
		imgPath:"/ershouche/bj/img/hotimg/img-hot03.png"
	};
	//经济实用
	var jjsy = {
		cTypeName:"经济实用",
		typeDesc:"时尚代步，经济实惠",
		cPrice:"5-10万",
		cMile:"8万以内",
		imgPath:"/ershouche/bj/img/hotimg/img-hot04.png"
	};
	//全能家用
	var qnjy = {
		cTypeName:"全能家用",
		typeDesc:"全能实用，功能强大",
		cPrice:"10-20万",
		cMile:"8万以内",
		imgPath:"/ershouche/bj/img/hotimg/img-hot05.png"
	};
	//宜商宜家
	var ysyj = {
		cTypeName:"宜商宜家",
		typeDesc:"品质生活，宜商宜家",
		cPrice:"10-20万",
		cMile:"8万以内",
		imgPath:"/ershouche/bj/img/hotimg/img-hot06.png"
	};
	//M端买车类型 (0二手车 1准新车 2练手车 3实惠豪车 4经济适用 5全能家用 6宜商宜家)
	//显示所选择车类型
	setSelectCarTypeInfo();
	
	function setSelectCarTypeInfo() {
		var cookieSelectType = getCookieValue('selectCarType');
		var result;
		switch(cookieSelectType){
			case "1":
				result = zxc;
				break;
			case "2":
				result = lsc;
				break;
			case "3":
				result = shhc;
				break;
			case "4":
				result = jjsy;
				break;
			case "5":
				result = qnjy;
				break;
			case "6":
				result = ysyj;
				break;
			default:
				result = jjsy;
		}
		$('#buyCarBoxImgID').attr('src',result.imgPath);
		$("#typeTitle").html(result.cTypeName);
		$('#cTypeName').html(result.cTypeName);
		$('#typeDesc').html(result.typeDesc);
		$('#cPrice').html(result.cPrice);
		$('#cMile').html(result.cMile);
		//已有报名人数
		var applyCount = 108;
		$.get(context + "/gouche/count/", {type:getCookieValue('selectCarType')}, function (data) {
            if (data.returnCode == '000000') {
            	applyCount = data.total+88;
            	$("#applyCountID").html("已有"+applyCount+"人报名");
            } else {
            	$("#applyCountID").html("已有"+applyCount+"人报名");
            }
        });
	}
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
	//预约报名
	$('#submit').on('click', function() {
		var phoneObj = $("#phoneNo");
        var budget = $("#budget").find("option:selected").text();
        if (!mobile(phoneObj.val())) {
        	$("#phoneNo").siblings("em").show();
        	$("#phoneNo").val('');
        	$("#phoneNo").focus();
            return;
        }
        phoneObj.next().hide();
        var budgetV=$("#budget").find("option:selected").val();
        if (budgetV =='0' || budgetV=='') {
            alert("请选择买车预算！");
            return;
        }
        var selectType = getCookieValue('selectCarType');
        if(!selectType) {
        	selectType = '1';
        }
        var budgetObj = {};
        budgetObj.mobile = phoneObj.val();
        budgetObj.visitSite = $.trim(location.href.split('?')[0]);
        budgetObj.fromT = getCookieValue('ershouchefromT');
        budgetObj.budget = budget;
        budgetObj.cityId = getRequestUrlArea();
        budgetObj.mGoucheType = selectType;
        $.post(context + "/gouche/registerBuyer", budgetObj, function (data) {
            if (data == '000000') {
            	location.href = context + '/'+getRequestUrlArea()+'/newCarGoucheSuccess';
                //alert('成功了啊，瞧见没，成功了~');
            } else {
                alert("报名失败，请稍候重试！");
            }
        });
	});
});