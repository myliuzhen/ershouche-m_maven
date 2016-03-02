package com.tcw.car.dto;

/**
 * 神奇代码，请勿改动   
 * Company：团车网
 * Description：车辆品牌
 **/
public class CarBrand {
	/** 品牌ID */
	private String brand_id;
	/** 品牌名称 */
	private String brand_name;
	/** 首字母 */
	private String initial;
	public String getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getInitial() {
		return initial;
	}
	public void setInitial(String initial) {
		this.initial = initial;
	}
}
