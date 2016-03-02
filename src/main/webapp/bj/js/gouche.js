jQuery(function(){
	setInterval(function(){
		var reqMember = randomBuyerInfo();
		$('#sed').html(reqMember.second);
		$('#rqName').html(reqMember.name + ' ');
	},5000);
	
	//热门团购好车点击跳转
	$('.hot ul li').on('click', function() {
		var selectType = $(this).attr('data-flag');
		setCookie('selectCarType', selectType, context+'/'+getRequestUrlArea());
		location.href = context+'/'+getRequestUrlArea()+'/goucheDetail';
	});
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
	$('#goucheSubmit').on('click', function() {
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
        var budgetObj = {};
        budgetObj.mobile = phoneObj.val();
        budgetObj.visitSite = $.trim(location.href.split('?')[0]);
        budgetObj.fromT = getCookieValue('ershouchefromT');
        budgetObj.budget = budget;
        budgetObj.cityId = getRequestUrlArea();
        budgetObj.mGoucheType = '0'; //二手车
        $.post(context + "/gouche/registerBuyer", budgetObj, function (data) {
            if (data == '000000') {
            	location.href = context + '/'+getRequestUrlArea()+'/goucheSuccess';
                //alert('成功了啊，瞧见没，成功了~');
            } else {
                alert("报名失败，请稍候重试！");
            }
        });
	});
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