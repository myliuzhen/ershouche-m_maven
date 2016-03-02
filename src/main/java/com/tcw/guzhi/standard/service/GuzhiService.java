package com.tcw.guzhi.standard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tcw.common.Constants;
import com.tcw.common.ERPCreatCusUtil;
import com.tcw.common.HttpUtil;
import com.tcw.guzhi.entity.CarBrand;
import com.tcw.guzhi.entity.CarModel;
import com.tcw.guzhi.entity.CarPriceTrend;
import com.tcw.guzhi.entity.CarSeries;
import com.tcw.guzhi.entity.Che300Result;
import com.tcw.guzhi.entity.CityEntity;
import com.tcw.guzhi.entity.GuzhiEntity;
import com.tcw.guzhi.entity.GuzhiResult;
import com.tcw.guzhi.entity.ProviceEntity;
import com.tcw.maiche.entity.UserRegister;

/**
 * @author liuzhen
 * @date 2015-11-12
 * @description 估值相关业务逻辑层
 */
@Service
public class GuzhiService {

	@Autowired
	private com.tcw.maiche.standard.service.UserRegisterService UserRegisterService;
	
	
	/**
	 * 获取车300车型列表
	 * 
	 * @author liuzhen
	 * @date 2015.11.11
	 * @return
	 */
	public Map<String, List<CarBrand>> getCarBrands() {
		Che300Result result = getChe300Result(Constants.Che300Config.GET_CARBRAND_URL);
		if (result != null) {
			List<CarBrand> brandList = result.getBrand_list();
			Map<String, List<CarBrand>> brandMap = new LinkedHashMap<String, List<CarBrand>>();
			for (CarBrand cb : brandList) {
				String initial = cb.getInitial();
				if (brandMap.containsKey(initial)) {
					brandMap.get(initial).add(cb);
				} else {
					List<CarBrand> brands = new ArrayList<CarBrand>();
					brands.add(cb);
					brandMap.put(initial, brands);
				}
			}
			return brandMap;
		}
		return null;
	}

	/**
	 * 通过品牌ID获取车系列表
	 * 
	 * @param brandId
	 * @return
	 */
	public Map<String, List<CarSeries>> getCarSeries(String brandId) {
		Che300Result result = getChe300Result(Constants.Che300Config.GET_CARSERIES_URL
				.replace("{brandId}", brandId));
		if (result != null) {
			List<CarSeries> seriesList = result.getSeries_list();
			Map<String, List<CarSeries>> seriesMap = new LinkedHashMap<String, List<CarSeries>>();
			for (CarSeries cb : seriesList) {
				String groupName = cb.getSeries_group_name();
				if (seriesMap.containsKey(groupName)) {
					seriesMap.get(groupName).add(cb);
				} else {
					List<CarSeries> brands = new ArrayList<CarSeries>();
					brands.add(cb);
					seriesMap.put(groupName, brands);
				}
			}
			return seriesMap;
		}
		return null;
	}

	/**
	 * 通过车系ID获取车型列表
	 * 
	 * @param seriesId
	 * @return
	 */
	public Map<String, List<CarModel>> getCarModels(String seriesId) {
		Che300Result result = getChe300Result(Constants.Che300Config.GET_CARMODEL_URL
				.replace("{seriesId}", seriesId));
		if (result != null) {
			List<CarModel> modelList = result.getModel_list();
			Map<String, List<CarModel>> modelsMap = new LinkedHashMap<String, List<CarModel>>();
			for (CarModel cb : modelList) {
				String modelYear = cb.getModel_year();
				if (modelsMap.containsKey(modelYear)) {
					modelsMap.get(modelYear).add(cb);
				} else {
					List<CarModel> brands = new ArrayList<CarModel>();
					brands.add(cb);
					modelsMap.put(modelYear, brands);
				}
			}
			return modelsMap;
		}
		return null;
	}

	private Che300Result getChe300Result(String url) {
		String s = HttpUtil.GETMethod(url, null);
		Che300Result result = JSONObject.parseObject(s, Che300Result.class);
		if ("0".equals(result.getStatus())) {
			return null;
		}
		return result;
	}

	/**
	 * 车300接口查询城市列表
	 * 
	 * @return
	 */
	public List<ProviceEntity> getAllCitys() {
		Che300Result result = getChe300Result(Constants.Che300Config.GET_ALLCITY_URL);
		if (result != null) {
			List<CityEntity> cityList = result.getCity_list();
			Map<String, ProviceEntity> provMap = new LinkedHashMap<String, ProviceEntity>();
			String defaultCityId = result.getCity_id();
			String defaultCityName = "";
			for (CityEntity city : cityList) {
				if (defaultCityId.equals(city.getCity_id())) {
					defaultCityName = city.getCity_name();
				}
			}
			for (CityEntity city : cityList) {
				String provId = city.getProv_id();
				if (provMap.containsKey(provId)) {
					provMap.get(provId).getCityList().add(city);
				} else {
					ProviceEntity prov = new ProviceEntity();
					prov.setDefault_city_id(defaultCityId);
					prov.setDefault_city_name(defaultCityName);
					prov.setProv_id(provId);
					prov.setProv_name(city.getProv_name());
					prov.getCityList().add(city);
					provMap.put(provId, prov);
				}
			}
			return new ArrayList<ProviceEntity>(provMap.values());
		}
		return null;
	}

	/**
	 * 车300在线估值
	 * 
	 * @param guzhi
	 * @return
	 */
	public GuzhiResult guzhi(GuzhiEntity guzhi) {
		String guzhiUrl = Constants.Che300Config.GUZHI_URL
				.replace("{modelId}", guzhi.getCarModel())
				.replace("{regDate}", guzhi.getCarYear())
				.replace("{mile}", guzhi.getMileage())
				.replace("{zone}", guzhi.getCity());
		String s = HttpUtil.GETMethod(guzhiUrl, null);
		GuzhiResult result = JSONObject.parseObject(s, GuzhiResult.class);
		if ("1".equals(result.getStatus())) {
			// 保存报名信息中
			UserRegister user = new UserRegister();
			user.setMobile(guzhi.getMobile());
			user.setUserName("匿名用户");
			user.setShop(guzhi.getShopName());
			user.setFromT(guzhi.getFromT());
			user.setCarModel(guzhi.getCarModelDesc());
			user.setVisitSite(guzhi.getVisitSite());
			user.setCityId(guzhi.getCityId());// 报名城市ID
			user.setSubFrom(guzhi.getSubFrom());// 网站入口
			user.setBusiness(Constants.Business.BUSINESS_GUZHI);//业务类型：估值
			 if(!"1".equals(guzhi.getAgreeBm())){
				 user.setSendInfo(false);
			 }
			UserRegisterService.insertUserRegister(user);//报名接口
			
			
			/**调用erp接口*/
			String userid=user.getUserId();
			String carModel=user.getCarModel();
			String areaName = user.getAreaName();
			String applyId = user.getApplyId();
			String shop = user.getShop();
			ERPCreatCusUtil erp = 
						new ERPCreatCusUtil(userid,
								user.getUserName(),
								Constants.formatDate(new Date(), "yyyy/MM/dd HH:mm:ss"),
								carModel,
								user.getBusiness(),
								user.getMobile(),
								areaName,
								applyId,shop,user.getFromT());
			Thread t = new Thread(erp);
			t.start();
			
			return result;
		}
		return null;
	}
	
	
	
	public List<CarPriceTrend> getPriceTrend(String zone, String modelId, String regDate, String mile) {
		String url = Constants.Che300Config.PRICE_TREND_URL.replace("{zone}", zone)
												 .replace("{modelId}", modelId)
												 .replace("{regDate}", regDate)
												 .replace("{mile}", mile);
		Che300Result result = getChe300Result(url);
		if(result!=null&&"1".equals(result.getStatus())){
			return result.getTrend();
		}
		return null;
	}
}