package com.tcw.car.entity;

import java.util.Date;
import java.util.List;

public class CtmManufacturer {

	private String manufacturerCode;	//厂商code
	private String manufacturerName;	//厂商名称
	private String brandName;			
	private String countryName;
	private String carNature;
	private Date createTime;
	private String timeliness;
	private String deleteTag;
	private Date ts;
	private String reservedField1;
	private String reservedField2;
	
	private List<CtmLine> ctmLines;		//车系列表

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

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCarNature() {
		return carNature;
	}

	public void setCarNature(String carNature) {
		this.carNature = carNature;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTimeliness() {
		return timeliness;
	}

	public void setTimeliness(String timeliness) {
		this.timeliness = timeliness;
	}

	public String getDeleteTag() {
		return deleteTag;
	}

	public void setDeleteTag(String deleteTag) {
		this.deleteTag = deleteTag;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public String getReservedField1() {
		return reservedField1;
	}

	public void setReservedField1(String reservedField1) {
		this.reservedField1 = reservedField1;
	}

	public String getReservedField2() {
		return reservedField2;
	}

	public void setReservedField2(String reservedField2) {
		this.reservedField2 = reservedField2;
	}

	public List<CtmLine> getCtmLines() {
		return ctmLines;
	}

	public void setCtmLines(List<CtmLine> ctmLines) {
		this.ctmLines = ctmLines;
	}
	
}