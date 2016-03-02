package com.tcw.car.entity;

/**
 * 多张照片
 * Created by zhaoyc on 2015/2/8 0008.
 */
public class PaintJc {

   
    private String imgText;//图片文本
    private String marKind;//损伤类型
    private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
