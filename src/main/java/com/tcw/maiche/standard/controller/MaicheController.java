/**
 * 12132015-11-11
 */
package com.tcw.maiche.standard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcw.car.entity.CarSelled;
import com.tcw.common.Constants;
import com.tcw.common.DesUtil;
import com.tcw.common.ResultDTO;
import com.tcw.gouche.standard.service.GoucheService;
import com.tcw.maiche.dto.CaseSelDto;
import com.tcw.maiche.entity.UserRegister;
import com.tcw.maiche.standard.service.CaseService;
import com.tcw.maiche.standard.service.MaicheService;
import com.tcw.maiche.standard.service.UserRegisterService;

/**
 *@author xyb
 *@date 2015-11-11
 *@description 卖车相关控制层
 */
@Controller
@RequestMapping("/maiche")
public class MaicheController {
	
	@Autowired
	private MaicheService maicheService;
	
	@Autowired
	private UserRegisterService userRegisterService;
	
	@Autowired
	private GoucheService goucheService;
	
	@Autowired
	private CaseService caseService;
	
	/**
	 * 卖车报名
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertRegister")
	public ResultDTO<String> insertUserRegister(UserRegister user){
		ResultDTO<String> res=userRegisterService.insertUserRegister(user);
		return res;
	}
	
	@RequestMapping(value = "/getKeys", method = RequestMethod.GET)
	@ResponseBody
	public Object getKeys(String keys) {
	        if (keys == null) {
	            return null;
	        }
	        Map<String, String> keymap = new HashMap<String, String>();
	        try {
	            String keys_dec = DesUtil.decrypt(keys);
	            keys_dec = java.net.URLDecoder.decode(keys_dec);
	            String key_str[] = keys_dec.split("&");
	            for (String key : key_str){
	                keymap.put(key.substring(0, key.indexOf("=")),
	                        key.substring(key.indexOf("=") + 1, key.length()));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return keymap;
	}
	
	/**
	 * 获取已成交车辆
	 * @param nowPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/getDealedCars")
	@ResponseBody
	public ResultDTO<CarSelled> getDealCarInfo(int nowPage, int pageSize){
		ResultDTO<CarSelled> res=new ResultDTO<CarSelled>();
		try {
			CaseSelDto carSelled = new CaseSelDto();
			carSelled.setStartRow((nowPage-1)*pageSize);
			carSelled.setEndRow(nowPage*pageSize);
			carSelled.setRowNum(pageSize);
			List<CarSelled>  list = caseService.getDealCarInfo(carSelled);
			int count = goucheService.getDealCarInfoCount(carSelled);
			res.setRows(list);
			res.setTotal(count);
			res.setReturnCode(Constants.RETURN_CODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			res.setReturnCode(Constants.RETURN_CODE_FAIL);
		}
		return res;
	}
	
	/**
	 * 获取已成交车辆列表
	 * @return
	 */
	@RequestMapping(value="/getDealedCarsList")
	@ResponseBody
	public ResultDTO<CarSelled> getDealCarInfoList(CaseSelDto carSelled,int nowPage, int pageSize){
		ResultDTO<CarSelled> res=new ResultDTO<CarSelled>();
		try {
			carSelled.setStartRow(0);
			carSelled.setEndRow(nowPage*pageSize);
			carSelled.setRowNum(nowPage*pageSize);
			List<CarSelled>  list = caseService.getDealCarInfo(carSelled);
			int count = goucheService.getDealCarInfoCount(carSelled);
			res.setRows(list);
			res.setTotal(count);
			res.setReturnCode(Constants.RETURN_CODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			res.setReturnCode(Constants.RETURN_CODE_FAIL);
		}
		return res;
	}
	
}
