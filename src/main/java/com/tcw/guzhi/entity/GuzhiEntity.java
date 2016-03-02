package com.tcw.guzhi.entity;

public class GuzhiEntity {
	/** 电话号 */
	private String mobile;
	/** 报名来源 */
	private String fromT;
	/** 预约门店 */
	private String shopName;
	/** 车型 */
	private String carModel;
	/** 车型描述 */
	private String carModelDesc;
	/** 年月 */
	private String carYear;
	/** 城市 */
	private String city;
	/** 行驶里程 */
	private String mileage;
	/** 是否统一帮卖 0否 1是 */
	private String agreeBm;
	/** 报名网站地址,报名统计需要该字段 */
	private String visitSite;
	/** 报名城市ID **/
	private String cityId;
	/*
	 * Web端各个报名入口区分6，估值
	 */
	private String subFrom;
	
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarYear() {
		return carYear;
	}
	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getFromT() {
		return fromT;
	}
	public void setFromT(String fromT) {
		this.fromT = fromT;
	}
	public String getAgreeBm() {
		return agreeBm;
	}
	public void setAgreeBm(String agreeBm) {
		this.agreeBm = agreeBm;
	}
	public String getCarModelDesc() {
		return carModelDesc;
	}
	public void setCarModelDesc(String carModelDesc) {
		this.carModelDesc = carModelDesc;
	}
	public String getVisitSite() {
		return visitSite;
	}
	public void setVisitSite(String visitSite) {
		this.visitSite = visitSite;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getSubFrom() {
		return subFrom;
	}
	public void setSubFrom(String subFrom) {
		this.subFrom = subFrom;
	}
	
}
