package com.tcw.report.entity;

import java.util.ArrayList;
import java.util.List;

import com.tcw.car.entity.BaseImg;

public class SingleCarReport {
	private String isAccident="无事故";//是否有事故
	private List<BaseImg> traCarBaseImgList= new ArrayList<BaseImg>();//车辆基本照片
	private String procedures="手续齐全，";//手续描述
	private String Violation="无违章";//有无违章
	private String carScore="60";//成交车辆评分
	private String apparent="车架状况良好";//车架状况
	private String paint="车身无喷漆痕迹";//喷漆情况
	private String engine="发动机状况良好";//发动机情况
	private String configStr="";//配置亮点
	private String inside="内饰状况良好";
	
	
	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getPaint() {
		return paint;
	}

	public void setPaint(String paint) {
		this.paint = paint;
	}

	public String getApparent() {
		return apparent;
	}

	public void setApparent(String apparent) {
		this.apparent = apparent;
	}

	public String getViolation() {
		return Violation;
	}

	public void setViolation(String violation) {
		Violation = violation;
	}

	public String getIsAccident() {
		return isAccident;
	}

	public void setIsAccident(String isAccident) {
		this.isAccident = isAccident;
	}

	public List<BaseImg> getTraCarBaseImgList() {
		return traCarBaseImgList;
	}

	public void setTraCarBaseImgList(List<BaseImg> traCarBaseImgList) {
		this.traCarBaseImgList = traCarBaseImgList;
	}


	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}

	public String getCarScore() {
		if(null==carScore)
			return "0";
		return carScore;
	}

	public void setCarScore(String carScore) {
		this.carScore = carScore;
	}

	public String getConfigStr() {
		return configStr;
	}

	public void setConfigStr(String configStr) {
		this.configStr = configStr;
	}

	public String getInside() {
		return inside;
	}

	public void setInside(String inside) {
		this.inside = inside;
	}
	
	
	
	
	

}
