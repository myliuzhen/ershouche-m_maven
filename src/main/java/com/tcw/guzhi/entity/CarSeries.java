package com.tcw.guzhi.entity;

/**
 * Company：团车网
 * Description：车系
 **/
public class CarSeries {
	/** 车系ID */
	private String series_id;
	
	/** 车系名称 */
	private String series_name;
	
	/** 车系组名 */
	private String series_group_name;

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

	public String getSeries_group_name() {
		return series_group_name;
	}

	public void setSeries_group_name(String series_group_name) {
		this.series_group_name = series_group_name;
	}
}
