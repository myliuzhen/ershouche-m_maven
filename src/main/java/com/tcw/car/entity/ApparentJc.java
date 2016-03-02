package com.tcw.car.entity;

/**
 * 多张照片
 * Created by zhaoyc on 2015/2/8 0008.
 */
public class ApparentJc {

   
    private String imgName;//图片
    private String imgText;//图片文本
    private String marKind;//损伤类型
    private String marLevel;//损伤程度
    private String name;
    private String x;
    private String y;

    

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
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
