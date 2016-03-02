package com.tcw.car.entity;

import java.util.Date;

/**
 * 检测报告json格式数据
 * Created by zhaoyc on 2015-01-31
 */
public class VdsTestReportJson {

    private String id;
    /**
     * 检测报告编码
     */
    private String reportCode;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 基本照
     */
    private String basePhotos;
    /**
     * 漆面检查
     */
    private String paintJc;

    /**
     * 车辆验证
     */
    private String carVerification;
    /**
     * 外观检查
     */
    private String appearanceJc;

    /**
     * 外观补充照片
     */
    private String appearanceRepImg;

    /**
     * 事故检查
     */
    private String accidentJc;

    /**
     * 事故补充照片
     */
    private String accidentRepImg;

    /**
     * 泡水检查
     */
    private String throughWaterJc;
    /**
     * 过火检查
     */
    private String throughFireJc;
    /**
     * 配置检查
     */
    private String configJc;

    /**
     * 配置/内室补充照片
     */
    private String configInnerRepImg;

    /**
     * 内室检测
     */
    private String innerJc;

    private String zgImg;
    
    private String carLevel;//基本表中的车辆评分，如：85A

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getBasePhotos() {
        return basePhotos;
    }

    public void setBasePhotos(String basePhotos) {
        this.basePhotos = basePhotos;
    }

    public String getPaintJc() {
        return paintJc;
    }

    public void setPaintJc(String paintJc) {
        this.paintJc = paintJc;
    }

    public String getCarVerification() {
        return carVerification;
    }

    public void setCarVerification(String carVerification) {
        this.carVerification = carVerification;
    }

    public String getAppearanceJc() {
        return appearanceJc;
    }

    public void setAppearanceJc(String appearanceJc) {
        this.appearanceJc = appearanceJc;
    }

    public String getAccidentJc() {
        return accidentJc;
    }

    public void setAccidentJc(String accidentJc) {
        this.accidentJc = accidentJc;
    }

    public String getThroughWaterJc() {
        return throughWaterJc;
    }

    public void setThroughWaterJc(String throughWaterJc) {
        this.throughWaterJc = throughWaterJc;
    }

    public String getThroughFireJc() {
        return throughFireJc;
    }

    public void setThroughFireJc(String throughFireJc) {
        this.throughFireJc = throughFireJc;
    }

    public String getConfigJc() {
        return configJc;
    }

    public void setConfigJc(String configJc) {
        this.configJc = configJc;
    }

    public String getInnerJc() {
        return innerJc;
    }

    public void setInnerJc(String innerJc) {
        this.innerJc = innerJc;
    }

    public String getAppearanceRepImg() {
        return appearanceRepImg;
    }

    public void setAppearanceRepImg(String appearanceRepImg) {
        this.appearanceRepImg = appearanceRepImg;
    }

    public String getAccidentRepImg() {
        return accidentRepImg;
    }

    public void setAccidentRepImg(String accidentRepImg) {
        this.accidentRepImg = accidentRepImg;
    }

    public String getConfigInnerRepImg() {
        return configInnerRepImg;
    }

    public void setConfigInnerRepImg(String configInnerRepImg) {
        this.configInnerRepImg = configInnerRepImg;
    }

    public String getZgImg() {
        return zgImg;
    }

    public void setZgImg(String zgImg) {
        this.zgImg = zgImg;
    }

	public String getCarLevel() {
		return carLevel;
	}

	public void setCarLevel(String carLevel) {
		this.carLevel = carLevel;
	}
    
}