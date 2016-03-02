/**
 * 12132015-11-10
 */
package com.tcw.report.standard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcw.car.dto.CarInfoDto;
import com.tcw.report.standard.service.CarReportService;

/**
 *@author xyb
 *@date 2015-11-10
 *@description
 */
@Controller
@RequestMapping("/carReport")
public class CarReportController {
	
	@Autowired
	private CarReportService carReportService;
	
	@ResponseBody
	@RequestMapping("/getCarReport")
	public Object[] getCarReport(CarInfoDto carInfoDto){
		Object[] o=null;
		try {
			o=carReportService.getSingleInfo(carInfoDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}
	
	/*@ResponseBody
	@RequestMapping("/test")
	public Object[]  test(){
		//C20150923110230
		CarInfoDto carInfoDto=new CarInfoDto();
		carInfoDto.setCarCode("C20150923110230");
		Object[] o=carReportService.getSingleInfo(carInfoDto);
		return o;
	}*/

}
