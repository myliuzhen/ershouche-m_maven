/**
 * 12132015-11-11
 */
package com.tcw.car.entity;

import java.text.NumberFormat;

import com.tcw.common.Constants;

/**
 *@author xyb
 *@date 2015-11-11
 *@description
 */
public class CarSelled {
	
	/** 车辆编号 */
	private String carCode;
	/** 车辆第一张图片 */
	private String carFirstImg;
	/** 车辆标题 */
	private String carInfo;
	/** 评分 */
	private String rank;
	/** 上牌时间 */
	private String regDate;
	/** 行驶里程 */
	private String miles;
	/** 成交价 */
	private String dealPrice;
	/** 排量*/
	private String emissionGb;
	/**车辆注册地*/
	private String registry;
	private String IMG_URL = Constants.MCONFIG.getString("IMG_URL");
	/** 多买价格*/
	private String extraIncome;
	/**成交时间*/
	private String dealTime;//成交时间
	private int rowNum;					//显示条数
	private int startRow;				//分页开始
	private int endRow;					//分页结束

	public String getFormatRegDate() {
		if(regDate != null && !"".equals(regDate)) {
			String[] split = regDate.split("-");
			return split[0]+"年"+split[1]+"月";
		} else {
			return "";
		}
	}
	
	public String getFormatMileage() {	//字段 formatMileage(格式化后的里程)
		try {
			if(miles!=null&&miles!=""){
				double m = Double.parseDouble(miles);
				NumberFormat nf = NumberFormat.getNumberInstance();
		        nf.setMaximumFractionDigits(2);
		        if(m<100){
		        	return m+"公里";
		        }else{
		        	return nf.format(m/10000)+"万公里";
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getFormatPrice() {
		try {
			if(dealPrice!=null&&dealPrice!=""){
				double m = Double.parseDouble(dealPrice);
				NumberFormat nf = NumberFormat.getNumberInstance();
		        nf.setMaximumFractionDigits(2);
		        if(m<10000){
		        	return m+"元";
		        }else{
		        	return nf.format(m/10000)+"万";
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getCarName(){			//字段 carName(格式化后的成立名称)
		if (null != regDate) {
			return regDate.split("-")[0]+"年 "+carInfo;
		}else {
			return carInfo;
		}
	}
	
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getCarFirstImg() {
		return carFirstImg;
	}
	public void setCarFirstImg(String carFirstImg) {
		this.carFirstImg = carFirstImg;
	}
	public String getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
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
	public String getMiles() {
		return miles;
	}
	public void setMiles(String miles) {
		this.miles = miles;
	}
	public String getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(String dealPrice) {
		this.dealPrice = dealPrice;
	}
	public String getEmissionGb() {
		return emissionGb;
	}
	public void setEmissionGb(String emissionGb) {
		this.emissionGb = emissionGb;
	}
	public String getRegistry() {
		return registry;
	}
	public void setRegistry(String registry) {
		this.registry = registry;
	}
	public String getIMG_URL() {
		return IMG_URL;
	}
	public void setIMG_URL(String iMG_URL) {
		IMG_URL = iMG_URL;
	}
	public String getExtraIncome() {
		return extraIncome;
	}
	public void setExtraIncome(String extraIncome) {
		this.extraIncome = extraIncome;
	}
	public String getDealTime() {
		return dealTime;
	}
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
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
}