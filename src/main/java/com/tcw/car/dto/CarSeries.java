package com.tcw.car.dto;

/**
 * 神奇代码，请勿改动   
 * Company：团车网
 * Description：车系
 **/
public class CarSeries {
	/** 车系ID */
	private String series_id;
	
	/** 车系名称 */
	private String series_name;
	
	/** 厂家 */
	private String manufacture;
	
	public String getSeries_id() {
		return series_id;
	}

	public void setSeries_id(String series_id) {
		this.series_id = series_id;
	}

	public String getSeries_name() {
		return series_name;
	}

	public void setSeries_name(String series_name) {
		this.series_name = series_name;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
}
