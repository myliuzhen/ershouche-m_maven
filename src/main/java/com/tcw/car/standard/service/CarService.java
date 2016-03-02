package com.tcw.car.standard.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcw.car.dao.CarDao;
import com.tcw.car.dto.CarBrand;
import com.tcw.car.dto.CarModel;
import com.tcw.car.dto.CarSeries;
import com.tcw.common.PinyinComparator;

@Service
public class CarService {
	
	@Autowired
	private CarDao dao;
	/**
	 * 查询车型信息
	 * @return
	 */
	public Map<String, List<CarBrand>> getCarBrands() {
		List<CarBrand> carBrandList = dao.getCarBrandList();
		Map<String,List<CarBrand>> result = new TreeMap<String, List<CarBrand>>();
		for(CarBrand brand : carBrandList){
			String szm = PinyinComparator.shouZiMu(brand.getBrand_name());
			brand.setInitial(szm);
			if(result.containsKey(szm)){
				result.get(szm).add(brand);
			}else{
				List<CarBrand> tempList = new ArrayList<CarBrand>();
				tempList.add(brand);
				result.put(szm, tempList);
			}
		}
		return result;
	}

	public Map<String,List<CarSeries>> getCarSeries(String brandId) {
		List<CarSeries> carSeriesList = dao.getCarSeriesListByCarBrand(brandId);
		Map<String,List<CarSeries>> result = new TreeMap<String, List<CarSeries>>();
		for(CarSeries series : carSeriesList){
			String manu = series.getManufacture();
			if(result.containsKey(manu)){
				result.get(manu).add(series);
			}else{
				List<CarSeries> tempList = new ArrayList<CarSeries>();
				tempList.add(series);
				result.put(manu, tempList);
			}
		}
		return result;
	}

	public Map<String, List<CarModel>> getCarModels(String seriesId) {
		List<CarModel> carModelList = dao.getCarModelListByCarSeries(seriesId);
		Map<String,List<CarModel>> result = new LinkedHashMap<String, List<CarModel>>();
		for(CarModel model : carModelList){
			String year = model.getModel_year();
			if(result.containsKey(year)){
				result.get(year).add(model);
			}else{
				List<CarModel> tempList = new ArrayList<CarModel>();
				tempList.add(model);
				result.put(year, tempList);
			}
		}
		return result;
	}

}
