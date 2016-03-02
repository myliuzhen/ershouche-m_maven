$(function () {
    showMap();
    $("ul li:eq(0)").click();

    $(".moreShop em").click(function () {
    	$(this).toggleClass("packUp");
    	$(".nameMore").toggle();
    });
    //地图高度控制
    var width = $("#shopMap").width();
	$("#shopMap").css("height", width/1.6 + 'px');
	if($("#platform").length>0) $("#platform").css('top','65px');
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
    point1: "116.326586",
    point2: "39.903227",
    title: "北京西站店",
    content: "地址：北京市海淀区羊坊店路21号瑞海大厦地下3层<br/>电话：010-51656556",
    isOpen: 0,
    icon: {w: 21, h: 21, l: 0, t: 0, x: 6, lb: 5},
    shopImg: "/ershouche/bj/img/shopShow01.jpg"
};
var json2 = {
    point1: "116.343578",
    point2: "40.048408",
    title: "清河小营店",
    content: "地址：北京市海淀区清河东方美都汽配城<br/>东门展厅3号<br/>电话：010-51656556",
    isOpen: 0,
    icon: {w: 21, h: 21, l: 0, t: 0, x: 6, lb: 5},
    shopImg: "/ershouche/bj/img/shopShow02.jpg"
};
var json3 = {
    point1: "116.462636",
    point2: "40.026222",
    title: "来广营店",
    content: "地址：北京市朝阳区来广营西路8号D座1层<br/>电话：010-51656556",
    isOpen: 0,
    icon: {w: 21, h: 21, l: 0, t: 0, x: 6, lb: 5},
    shopImg: "/ershouche/bj/img/shopShow03.jpg"
};

var json4 = {
    point1: "116.527746",
    point2: "39.947044",
    title: "姚家园店",
    content: "北京市朝阳区姚家园路甲1号(姚家园家乐福停车场西侧)<br/>电话：010-51656556",
    isOpen: 0,
    icon: {w: 21, h: 21, l: 0, t: 0, x: 6, lb: 5},
    shopImg: "/ershouche/bj/img/shopShow04.jpg"
};
var json5 = {
    point1: "116.258174",
    point2: "40.055864",
    title: "百旺店",
    content: "北京海淀区百旺茉莉二期101号1-3层 <br/>电话：010-51656556",
    isOpen: 0,
    icon: {w: 21, h: 21, l: 0, t: 0, x: 6, lb: 5},
    shopImg: "/ershouche/bj/img/shopShow05.jpg"
};
var json6 = {
    point1: "116.190442",
    point2: "39.922514",
    title: "石景山店",
    content: "北京石景山古城西路汽车园区F区11号 <br/>电话：010-51656556",
    isOpen: 0,
    icon: {w: 21, h: 21, l: 0, t: 0, x: 6, lb: 5},
    shopImg: "/ershouche/bj/img/shopShow06.jpg"
};
var myArray = [json1, json2, json3, json4, json6];
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
    var iconImg = new BMap.Icon("/ershouche/bj/img/ico-location.png" , new BMap.Size(21,28),{imageOffset: new BMap.Size(-0, -0),infoWindowOffset: new BMap.Size(10,1),offset:new BMap.Size(21,28)});
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