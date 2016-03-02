package com.tcw.topurl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcw.common.Constants;

/**
 * 顶级跳转controller
 */
@Controller
@RequestMapping("/")
public class TopUrlController {
	
	/**
	 * 卖车跳转
	 * @return
	 */
	@RequestMapping(value = "/{area}/maiche")
	public String maiche(@PathVariable String area) {
		return "/" + area + "/html/maiche.html";
	}
	
	/**
	 * 买车跳转
	 * @return
	 */
	@RequestMapping(value = "/{area}/gouche")
	public String gouche(@PathVariable String area) {
		return "/" + area + "/html/gouche.html";
	}
	
	/**
	 * 只有区域时跳转
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}")
	public String area(@PathVariable String area){
		return "/"+area+"/html/index.html";
	}
	
	/**
	 * 买车保障
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}/baozhang")
	public String bzBuy(@PathVariable String area){
		return "/"+area+"/html/baozhang.html";
	}
	
	/**
	 * 估值
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}/guzhi")
	public String guzhi(@PathVariable String area){
		return "/"+area+"/html/guzhi.html";
	}
	
	
	/**
	 * 买车详情(显示信息状态存入cookie)
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}/goucheDetail")
	public String goucheDetail(@PathVariable String area) {
		return "/"+area+"/html/buyDetail.html";
	}

	/***
	 * 成交车辆的详细
	 * @return
	 */
	@RequestMapping(value = "{area}/caseDetail")
	public String caseDetail(@PathVariable String area){
		return "/"+area+"/html/caseDetail.html";
	}
	
	/**
	 * 跳转估值成功页面
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}/guzhiResult")
	public String guzhiResult(@PathVariable String area, String str) {
		return "/"+area+"/html/guzhiResult.html?" + str;
	}

	
	/**
	 * 跳转二手车团购成功
	 * @param area
	 * @param str
	 * @return
	 */
	@RequestMapping(value = "{area}/newCarGoucheSuccess")
	public String newCarGoucheSuccess(@PathVariable String area) {
		return "/"+area+"/html/newCarGoucheSuccess.html";
	}
	
	/**
	 * 跳转新车团购成功
	 * @param area
	 * @param str
	 * @return
	 */
	@RequestMapping(value = "{area}/goucheSuccess")
	public String goucheSuccess(@PathVariable String area) {
		return "/"+area+"/html/goucheSuccess.html";
	}
	/**
	 * 跳转卖车成功页面
	 * @param area
	 * @return
	 */
	/**
	 *跳转到卖车成功页面
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}/maicheSuccess")
	public String maicheSuccess(@PathVariable String area) {
		return "/"+area+"/html/maicheSuccess.html";
	}
	
	/**
	 * 跳转到成交车辆列表
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}/caseList")
	public String caseList(@PathVariable String area){
		return "/"+area+"/html/caseList.html";
	}
	/**
	 * 关于我们/联系我们
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}/aboutUs")
	public String aboutUs(@PathVariable String area) {
		return "/"+area+"/html/aboutUs.html";
	}
	/**
	 * 关于我们/联系我们
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}/dianmian")
	public String dianmian(@PathVariable String area) {
		return "/"+area+"/html/map.html";
	}
	/**
	 * 微信活动链接
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "{area}/weixin")
	public String weixin(@PathVariable String area) {
		return "redirect:"+Constants.MCONFIG.getString("WEIXIN_LINK");
	}
	
}