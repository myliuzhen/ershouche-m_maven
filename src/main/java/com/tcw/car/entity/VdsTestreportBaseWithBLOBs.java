package com.tcw.car.entity;

/**
 * 检测报告json对象
 * @author liuzhen
 * @date 2014.7.9
 */
public class VdsTestreportBaseWithBLOBs extends VdsTestReportJson {
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
     * 事故检查
     */
    private String accidentJc;
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
     * 内室检测
     */
    private String innerJc;

    /**
     * 报废拍照
     */
    private String scrapPhotos;

    
    
}