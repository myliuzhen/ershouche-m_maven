package com.tcw.common;

/**
 * 神奇代码，请勿改动   
 * Company：团车网
 * Description：车辆品牌
 * @author YuNingbo
 * @date 2014-11-11
 **/
public class CtmBrand {
	private String manufacturerCode;
	private String manufacturerName;
	
	public String getId(){
		return manufacturerCode;
	}
	public String getName(){
		return manufacturerName;
	}
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
}
