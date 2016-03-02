package com.tcw.maiche.dto;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tcw.common.CommonUtils;

/**
 * 神奇代码，请勿改动   
 * Company：团车网
 * Description：车辆信息DTO
 **/
public class CarInfoDto {
	private String id = null;
	private String carCode = null;//车辆编码
	private String carInfo = null;//车辆信息
	private String manufacturer = null;//品牌
	private String model = null;//车型
	private String license = null;//车牌号
	private String seriesName = null;//车系
	private String color = null;//颜色
	private String rank = null;//等级
	private String regDate;//初登日期
	private String mileage;//行驶里程
	private String carFirstImg;//车辆图片
	private String factoryTime;//出厂日期
	private int purchaserClosingCost;//买方成交价-最高出价
	private Date dealTime;//成交时间
	private String country;//车系所属国家
	private String[] countrys;		//车系所属国家
	private String manufacturerCode;//品牌code
	private String seriesCode;		//车系code
	private int startRow;
	private int endRow;
	private int rowNum;
	
	
	
	/**
	 * 初登日期
	 */
	public String getCarRegDate(){
		String d = "";
		if (null != regDate) {
			d = regDate.split("-")[0];
		}
		return d+"年";
	}
	
	/**
	 * 获取车辆出厂年份
	 */
	public String getCarFactoryTime(){
		String d = "";
		if (null != factoryTime) {
			d = factoryTime.split("-")[0];
		}
		return d;
	}
	/**
	 * 获取里程数
	 */
	public String getMileageS(){
		try {
			if(mileage!=null){
				double m = Double.parseDouble(mileage);
				m = m/10000;
				NumberFormat nf = NumberFormat.getNumberInstance();
		        nf.setMaximumFractionDigits(2);
				return nf.format(m)+"万公里";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取里程数
	 */
	public String getMileageS1(){
		try {
			if(mileage!=null){
				double m = Double.parseDouble(mileage);
				m = m/10000;
				return CommonUtils.parseThreeNumber(m)+"万公里";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 格式化车辆价格
	 */
	public String getCarPrice(){
		return "￥" + this.getPurchaserClosingCost()+"元";
	}
	
	/**
	 * Company:团车网
	 * Description：车辆价格-万元
	 * @author YuNingbo
	 * @date 2014-11-7
	 */
	public String getCarPrice1(){
		double price = this.getPurchaserClosingCost()/10000.0;
        return CommonUtils.parseThreeNumber(price);  
	}
	
	/**
	 * Company:团车网
	 * Description：成交时间年月
	 * @author YuNingbo
	 * @date 2014-11-14
	 */
	public String getDealTimeS(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy年M月");
		if(dealTime==null){
			return format.format(new Date());
		}
		
		return format.format(dealTime);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getCarFirstImg() {
		return carFirstImg;
	}
	public void setCarFirstImg(String carFirstImg) {
		this.carFirstImg = carFirstImg;
	}
	public int getPurchaserClosingCost() {
		return purchaserClosingCost;
	}
	public void setPurchaserClosingCost(int purchaserClosingCost) {
		this.purchaserClosingCost = purchaserClosingCost;
	}
	
	public String getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(String factoryTime) {
		this.factoryTime = factoryTime;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public String[] getCountrys() {
		return countrys;
	}
	public void setCountrys(String[] countrys) {
		this.countrys = countrys;
	}
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}
	public String getSeriesCode() {
		return seriesCode;
	}
	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
}
