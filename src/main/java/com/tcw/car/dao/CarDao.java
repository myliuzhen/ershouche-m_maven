package com.tcw.car.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcw.car.dto.CarBrand;
import com.tcw.car.dto.CarModel;
import com.tcw.car.dto.CarSeries;
import com.tcw.common.BaseDaoERP;

@Component
public class CarDao {
	@Autowired
	private BaseDaoERP baseDaoErp;

	public List<CarBrand> getCarBrandList() {
		return baseDaoErp.findList("mapper.standard.TcwCarModel.getCarBrandList");
	}
	
	public List<CarSeries> getCarSeriesListByCarBrand(String carBrand){
		return baseDaoErp.findList("mapper.standard.TcwCarModel.getSeriesListByBrandId",carBrand);
	}
	
	public List<CarModel> getCarModelListByCarSeries(String carSeries){
		return baseDaoErp.findList("mapper.standard.TcwCarModel.getCarModelListBySeriesId",carSeries);
	}
}
