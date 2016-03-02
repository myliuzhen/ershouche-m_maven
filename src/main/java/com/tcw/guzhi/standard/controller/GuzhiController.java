/**
 * 12132015-11-11
 */
package com.tcw.guzhi.standard.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcw.guzhi.entity.CarBrand;
import com.tcw.guzhi.entity.CarModel;
import com.tcw.guzhi.entity.CarPriceTrend;
import com.tcw.guzhi.entity.CarSeries;
import com.tcw.guzhi.entity.GuzhiEntity;
import com.tcw.guzhi.entity.GuzhiResult;
import com.tcw.guzhi.entity.ProviceEntity;
import com.tcw.guzhi.standard.service.GuzhiService;

/**
 * @author liuzhen
 * @date 2015-11-12
 * @description 估值相关控制层
 */
@Controller
@RequestMapping("/guzhi")
public class GuzhiController {

	@Autowired
	private GuzhiService guzhiService;

	/**
	 * 车300在线估值
	 * 
	 * @param guzhi
	 * @return
	 */
	@RequestMapping(value = "/guzhi300", method = RequestMethod.POST)
	@ResponseBody
	public GuzhiResult guzhi(GuzhiEntity guzhi) {
		return guzhiService.guzhi(guzhi);
	}

	/**
	 * 获取车300车型列表（评估）
	 * 
	 * @author liuzhen
	 * @return
	 */
	@RequestMapping(value = "/getCarBrands", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<CarBrand>> getCarBrands() {
		return guzhiService.getCarBrands();
	}

	/**
	 * 通过品牌ID获取车系列表
	 * 
	 * @param brandId
	 * @return
	 */
	@RequestMapping(value = "/getCarSeries/{brandId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<CarSeries>> getCarBrands(
			@PathVariable("brandId") String brandId) {
		return guzhiService.getCarSeries(brandId);
	}

	/**
	 * 通过车系ID获取车型列表
	 * 
	 * @param seriesId
	 * @return
	 */
	@RequestMapping(value = "/getCarModels/{seriesId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<CarModel>> getCarModels(
			@PathVariable("seriesId") String seriesId) {
		return guzhiService.getCarModels(seriesId);
	}

	/**
	 * 通过车300接口查询城市列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllCitys", method = RequestMethod.POST)
	@ResponseBody
	public List<ProviceEntity> getAllCitys() {
		return guzhiService.getAllCitys();
	}

	/**
	 * Company:团车网
	 * Description：获取未来两年价格走势
	 */
	@RequestMapping(value="/getPriceTrend",method=RequestMethod.POST)
	@ResponseBody
	public List<CarPriceTrend> getPriceTrend(@RequestParam("zone")String zone,
			@RequestParam("modelId")String modelId,@RequestParam("regDate")String regDate,@RequestParam("mile")String mile){
		return guzhiService.getPriceTrend(zone,modelId,regDate,mile);
	}
	
}