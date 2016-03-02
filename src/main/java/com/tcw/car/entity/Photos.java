package com.tcw.car.entity;

import java.util.List;

/**
 * 多张照片
 * Created by zhaoyc on 2015/2/8 0008.
 */
public class Photos {

    /**
     * 图片
     */
    private List<String> imgNames;

    /**
     * 图片文本
     */
    private String imgText;

    /**
     * 损伤类型
     * */
    private String marKind;

    /**
     * 损伤程度
     */
    private String marLevel;
    
    private String name;

    public List<String> getImgNames() {
        return imgNames;
    }

    public void setImgNames(List<String> imgNames) {
        this.imgNames = imgNames;
    }

    public String getImgText() {
        return imgText;
    }

    public void setImgText(String imgText) {
        this.imgText = imgText;
    }

    public String getMarKind() {
        return marKind;
    }

    public void setMarKind(String marKind) {
        this.marKind = marKind;
    }

    public String getMarLevel() {
        return marLevel;
    }

    public void setMarLevel(String marLevel) {
        this.marLevel = marLevel;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
