$(function () {
    showMap();
    $("ul li:eq(0)").click();

//    $(".moreShop em").click(function () {
//    	$(this).toggleClass("packUp");
//    	$(".nameMore").toggle();
//    });
    //地图高度控制
    var width = $("#shopMap").width();
	$("#shopMap").css("height", width/1.6 + 'px');
//	if($("#platform").length>0) $("#platform").css('top','65px');
    $(window).resize(function(){
    	var width = $("#shopMap").width();
    	$("#shopMap").css("height", width/1.5 + 'px');
    });
});
/**
 * 显示店铺地图
 */
var mapId = "shopMap";
var json1 = {
	point1: "120.113276",
    point2: "30.331672",
    title: "杭州莫干山路店",
    content: "地址：杭州市拱墅区莫干山路1333号<br/>电话：18058447006",
    isOpen: 0,
    icon: {w: 21, h: 21, l: 0, t: 0, x: 6, lb: 5},
    shopImg: "/ershouche/hz/img/shopShow01.jpg"
};

var myArray = [json1];
function showMap() {
    $("ul li").click(function () {
        $("ul li").removeClass("act-locat");
        $(this).addClass("act-locat");
        var index = $("ul li").index(this);
        createMap(myArray[index]);
    });
}
function createMap(json) {
// 百度地图API功能
    var mp = new BMap.Map(mapId);
    mp.centerAndZoom(new BMap.Point(json.point1, json.point2), 15);
    mp.enableScrollWheelZoom();
    // 复杂的自定义覆盖物
    function ComplexCustomOverlay(point) {
        this._point = point;
    }
    var point = new BMap.Point(json.point1,json.point2);
    var iconImg = new BMap.Icon("/ershouche/hz/img/ico-location.png" , new BMap.Size(21,28),{imageOffset: new BMap.Size(-0, -0),infoWindowOffset: new BMap.Size(10,1),offset:new BMap.Size(21,28)});
    var marker = new BMap.Marker(point,{icon:iconImg});
    var label = new BMap.Label(json.title,{ "offset": new BMap.Size(json.icon.lb-json.icon.x+10,- 40)});
    marker.setLabel(label);
    mp.addOverlay(marker);
    label.setStyle({
                borderColor: "#808080",
                color: "#333",
                cursor: "pointer"
    });
    ComplexCustomOverlay.prototype = new BMap.Overlay();
    ComplexCustomOverlay.prototype.initialize = function (map) {
        this._map = map;
        var div = document.createElement("div");
        var marker = this._marker = document.createElement("div");
        marker.setAttribute("class", "icoLocation");
        marker.style.display='none';
        div.appendChild(marker);

        var pic = this._pic = document.createElement("div");
        pic.setAttribute("class", "shopShow");

        var shopShowC = document.createElement("div");
        shopShowC.setAttribute("class", "shopShowC");

        var arrow = document.createElement("div");
        arrow.setAttribute("class", "arrow-left");
        shopShowC.appendChild(arrow);

        var shopImg = document.createElement("img");//门店图片
        shopImg.setAttribute("src", json.shopImg);
        shopShowC.appendChild(shopImg);

        pic.appendChild(shopShowC);
        div.appendChild(pic);
        mp.getPanes().labelPane.appendChild(div);
        return div;
    }
    ComplexCustomOverlay.prototype.draw = function () {
        var map = this._map;
        var pixel = map.pointToOverlayPixel(this._point);
        this._marker.style.left = pixel.x + "px";
        this._marker.style.top = pixel.y + "px";
        this._pic.style.left = pixel.x + 20 + "px";
        this._pic.style.top = pixel.y - 10 + "px";
    }
    mp.addOverlay(new ComplexCustomOverlay(new BMap.Point(json.point1, json.point2)));

    //更新门店地址
    $(".shopInfo dt").html(json.title);
    $(".shopInfo dd").html(json.content);
}