/**
 * 12132015-11-9
 */
package com.tcw.gouche.standard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcw.car.entity.CarSelled;
import com.tcw.common.Constants;
import com.tcw.common.ResultDTO;
import com.tcw.gouche.entity.BuyerRegister;
import com.tcw.gouche.standard.service.GoucheService;
import com.tcw.maiche.dto.CaseSelDto;

/**
 *@author xyb
 *@date 2015-11-9
 *@description
 */
@Controller
@RequestMapping("/gouche")
public class GoucheController {
	@Autowired
	private GoucheService goucheService;
	
	
	/**
	 * 获取已成交车辆
	 * @param nowPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getSelledCarGroupByCountryForPage")
	@ResponseBody
	public Map<String,Object> getDealCarInfo(int nowPage, int pageSize){
		CaseSelDto carSelled = new CaseSelDto();
		carSelled.setStartRow((nowPage-1)*pageSize);
		carSelled.setEndRow(nowPage*pageSize);
		carSelled.setRowNum(pageSize);
		
		List<CarSelled>  list = goucheService.getDealCarInfo(carSelled);
		int count = goucheService.getDealCarInfoCount(carSelled);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("dataList", list);
		result.put("countPage", count/pageSize);
		return result;
	}
	

	
	@RequestMapping(value = "/registerBuyer", method = RequestMethod.POST)
    @ResponseBody
    public String registerBuyer(BuyerRegister buyer) {
		String result = "";
		if(null == buyer.getMobile()){
			return Constants.RETURN_CODE_MOBILE_ERROR;
		}
		result = goucheService.registerBuyer(buyer);
		return result;
    }
	/**
	 * 根据预约类型获取报名买车数量
	 * @author liuzhen
	 * @param type 预约类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/count/", method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO getGoucheCoutByType(@RequestParam String type){
		ResultDTO<?> res = new ResultDTO();
		try{
			res = goucheService.getGoucheCoutByType(type);
			res.setReturnCode(Constants.RETURN_CODE_SUCCESS);
			res.setReturnMsg(Constants.RETURN_MSG_SUCCESS);
		}catch(Exception e){
			res.setReturnCode(Constants.RETURN_CODE_SYSTEM_FAIL);
			res.setReturnMsg(Constants.RETURN_MSG_SYSTEM_FAIL);
			e.printStackTrace();
		}
		return res;
	}
}
