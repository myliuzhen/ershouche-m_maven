/**
 * 12132015-11-19
 */
package com.tcw.maiche.dto;

import com.tcw.car.entity.CarSelled;

/**
 *@author xyb
 *@date 2015-11-19
 *@description
 */
public class CaseSelDto extends CarSelled {
	
	private String pageop;
	
	private String orderByop;
	
	private String ageop;
	
	private String priceop;
	
	private String modelop;

	public String getPageop() {
		return pageop;
	}

	public void setPageop(String pageop) {
		this.pageop = pageop;
	}

	public String getOrderByop() {
		return orderByop;
	}

	public void setOrderByop(String orderByop) {
		this.orderByop = orderByop;
	}

	public String getAgeop() {
		return ageop;
	}

	public void setAgeop(String ageop) {
		this.ageop = ageop;
	}

	public String getPriceop() {
		return priceop;
	}

	public void setPriceop(String priceop) {
		this.priceop = priceop;
	}

	public String getModelop() {
		return modelop;
	}

	public void setModelop(String modelop) {
		this.modelop = modelop;
	}
}
