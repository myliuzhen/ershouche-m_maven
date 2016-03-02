package com.tcw.guzhi.entity;

import java.util.List;


/**
 * Company：团车网
 * Description：车300数据返回基本格式
 **/
public class Che300Result {
	/** 1 表示成功/0 表示失败 */
	private String status;
	/** 车300URL */
	private String url;
	
	/**  当前省份ID  */
	private String prov_id;
	
	/** 当前城市ID */
	private String city_id;
	
	/** 城市列表 */
	private List<CityEntity> city_list;
	
	/** 车辆品牌列表 */
	private List<CarBrand> brand_list;
	
	/** 车系列表 */
	private List<CarSeries> series_list;
	
	/** 车型列表 */
	private List<CarModel> model_list;
	
	/** 未来两年价格走势 */
	private List<CarPriceTrend> trend;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProv_id() {
		return prov_id;
	}

	public void setProv_id(String prov_id) {
		this.prov_id = prov_id;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	public List<CityEntity> getCity_list() {
		return city_list;
	}

	public void setCity_list(List<CityEntity> city_list) {
		this.city_list = city_list;
	}

	public List<CarBrand> getBrand_list() {
		return brand_list;
	}

	public void setBrand_list(List<CarBrand> brand_list) {
		this.brand_list = brand_list;
	}

	public List<CarSeries> getSeries_list() {
		return series_list;
	}

	public void setSeries_list(List<CarSeries> series_list) {
		this.series_list = series_list;
	}

	public List<CarModel> getModel_list() {
		return model_list;
	}

	public void setModel_list(List<CarModel> model_list) {
		this.model_list = model_list;
	}

	public List<CarPriceTrend> getTrend() {
		return trend;
	}

	public void setTrend(List<CarPriceTrend> trend) {
		this.trend = trend;
	}
}
