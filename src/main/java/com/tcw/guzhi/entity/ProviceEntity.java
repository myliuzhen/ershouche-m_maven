package com.tcw.guzhi.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * Company：团车网
 * Description：省份实例
 **/
public class ProviceEntity {
	/** 默认显示的城市Id */
	private String default_city_id;
	/** 默认显示的城市名称 */
	private String default_city_name;
	/** 省份ID */
	private String prov_id;
	/** 省份名称 */
	private String prov_name;
	/** 城市列表 */
	private List<CityEntity> cityList = new ArrayList<CityEntity>();
	
	public String getProv_id() {
		return prov_id;
	}
	public void setProv_id(String prov_id) {
		this.prov_id = prov_id;
	}
	public String getProv_name() {
		return prov_name;
	}
	public void setProv_name(String prov_name) {
		this.prov_name = prov_name;
	}
	public List<CityEntity> getCityList() {
		return cityList;
	}
	public void setCityList(List<CityEntity> cityList) {
		this.cityList = cityList;
	}
	public String getDefault_city_id() {
		return default_city_id;
	}
	public void setDefault_city_id(String default_city_id) {
		this.default_city_id = default_city_id;
	}
	public String getDefault_city_name() {
		return default_city_name;
	}
	public void setDefault_city_name(String default_city_name) {
		this.default_city_name = default_city_name;
	}
}
