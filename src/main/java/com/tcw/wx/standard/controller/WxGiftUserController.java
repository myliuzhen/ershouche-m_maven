package com.tcw.wx.standard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcw.common.Constants;
import com.tcw.common.HttpUtil;
import com.tcw.common.MD5Util;
import com.tcw.wx.entity.WxGiftUser;

@Controller
@RequestMapping("/wxGiftUserController")
public class WxGiftUserController {
	
	private String url = Constants.MCONFIG.getString("WEIXIN_LINK");

	@RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
	public String sendVerifyCode(String mobile) {
		if(mobile != null && !"".equals(mobile)) {
			//发送短信验证码
			String httpUrl = url + "sendVerifyCode";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mobile", mobile);
			String s = HttpUtil.POSTMethod(httpUrl, map);
			return s;
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/checkVerifyCode", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
	public String checkVerifyCode(String mobile, String verifyCode) {
		try{
			if(mobile != null && !"".equals(mobile) && verifyCode != null && !"".equals(verifyCode)) {
				//check
				String httpUrl = url + "checkVerifyCode";
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("mobile", mobile);
				map.put("verifyCode", verifyCode);
				String s = HttpUtil.POSTMethod(httpUrl, map);
				return s;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 领取红包
     */
    @RequestMapping(value = "/receive", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
	public String receive(WxGiftUser user) {
    	try{
    		if(user.getWechatId() != null && !"".equals(user.getWechatId())) {
    			String httpUrl = url + "receive";
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("mobile", user.getMobile());
    			map.put("wechatId", user.getWechatId());
    			map.put("cityId", user.getCityId());
    			map.put("cityName", user.getCityName());
    			map.put("brandId", user.getBrandId());
    			map.put("modelId", user.getModelId());
    			map.put("modelInfo", user.getModelInfo());
    			map.put("buyWay", user.getBuyWay());
    			String s = HttpUtil.POSTMethod(httpUrl, map);
    			return s;
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
	}
	
	/**
	 * 通过城市ＩＤ获取品牌列表
	 * @param cityId
	 * @return
	 */
	@RequestMapping(value = "/getBrandMap", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
	public String getBrandMap(String cityId) {
		String brandJsons = "";
		try{
			String brandurl = Constants.MCONFIG.getString("NEWCAR_BRAND_INTERFACE");
			String key = Constants.MCONFIG.getString("NGIX.KEY");
	        Long time = System.currentTimeMillis();
	        String sign = MD5Util.securityCon(time+"",key);
	        brandurl = brandurl.replace("${time}", time.toString()).replace("${sign}", sign).replace("${cityId}", cityId);
	        brandJsons = HttpUtil.POSTMethod(brandurl, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		return brandJsons;
	}
	
	/**
	 * 通过品牌和城市获取车型列表
	 * @param brandId
	 * @param cityId
	 * @return
	 */
	@RequestMapping(value = "/getStyleMap", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
	public String getStyleMap(String brandId, String cityId) {
		String modelJsons = "";
		try{
			String modelurl = Constants.MCONFIG.getString("NEWCAR_MODEL_INTERFACE");
			String key = Constants.MCONFIG.getString("NGIX.KEY");
	        Long time = System.currentTimeMillis();
	        String sign = MD5Util.securityCon(time+"",key);
	        modelurl = modelurl.replace("${time}", time.toString()).replace("${sign}", sign).replace("${cityId}", cityId).replace("${brandId}", brandId);
	        modelJsons = HttpUtil.POSTMethod(modelurl, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		return modelJsons;
	}
	/**
	 * 获取城市列表
	 * @return
	 */
	@RequestMapping(value = "/getAllCityInOpenStatus", produces="application/json;charset=UTF-8")
    @ResponseBody
	public String getAllCityInOpenStatus() {
		String cityJsons = "";
		try{
			String cityurl = Constants.MCONFIG.getString("NEWCAR_CITY_INTERFACE");
			String key = Constants.MCONFIG.getString("NGIX.KEY");
	        Long time = System.currentTimeMillis();
	        String sign = MD5Util.securityCon(time+"",key);
	        cityurl = cityurl.replace("${time}", time.toString()).replace("${sign}", sign);
	        cityJsons = HttpUtil.GETMethod(cityurl, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		return cityJsons;
	}
	
	
	@RequestMapping(value="/getWxJsConfigToJson", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getWxJsConfigToJson(String currentUrl) {
		try{
			String httpUrl = url + "getWxJsConfigToJson";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("url", currentUrl);
			String s = HttpUtil.GETMethod(httpUrl, map);
			return s;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}