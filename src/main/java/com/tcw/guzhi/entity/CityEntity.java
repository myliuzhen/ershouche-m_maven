package com.tcw.guzhi.entity;

public class CityEntity {
	/** 城市ID */
	private String city_id;
	/** 城市名称 */
	private String city_name;
	/** 省份ID */
	private String prov_id;
	/** 省份名称 */
	private String prov_name;
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
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
}
