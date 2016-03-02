package com.tcw.car.standard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcw.car.dto.CarBrand;
import com.tcw.car.dto.CarModel;
import com.tcw.car.dto.CarSeries;
import com.tcw.car.standard.service.CarService;
import com.tcw.common.JSONUtil;

@Controller
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	/**
	 * 获取车型数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getCarBrands",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,List<CarBrand>> getCarBrands(HttpServletRequest request){
		return carService.getCarBrands();
	}
	
	@RequestMapping(value="/getCarBrandsRemote",method=RequestMethod.GET)
	@ResponseBody
	public void getCarModels1(HttpServletRequest request,HttpServletResponse response){
		try {
			String callBack=request.getParameter("callback");
			response.setContentType("text/xml;charset=utf-8");   
	        response.setHeader("Cache-Control", "no-cache");
	        PrintWriter pw = response.getWriter();
	        pw.print(callBack+"("+JSONUtil.toJson(carService.getCarBrands())+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getCarSeries/{brandId}",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,List<CarSeries>> getCarBrands(@PathVariable("brandId")String brandId){
		return carService.getCarSeries(brandId);
	}
	
	@RequestMapping(value="/getCarModels/{seriesId}",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,List<CarModel>> getCarModels(@PathVariable("seriesId")String seriesId){
		return carService.getCarModels(seriesId);
	}
}
