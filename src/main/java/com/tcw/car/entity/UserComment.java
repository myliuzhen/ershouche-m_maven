package com.tcw.car.entity;

import java.util.Date;

public class UserComment {

	private String id;		
	private String ruleName;
	private String mobile;
	private String content;			//评价内容
	private String isSaled;
	private Date created;			//时间
	private String deleteTag;
	private Date ts;
	private String timeliness;
	private String reservedField1;
	private String reservedField2;
	private String reservedField3;
	private String key;
	private String fromT;			//评论来源0:短信下行点评 1：店里扫码点评
	private Integer stars;			//星级评价：团长服务态度
	private Integer businessStars;	//星级评价：团长业务水平
	private Integer priceStars;		//星级评价：价格满意程度
	private String tlName;			//团长名称
	private String storesId;		//店铺编号
	private String storesName;		//店铺名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsSaled() {
		return isSaled;
	}

	public void setIsSaled(String isSaled) {
		this.isSaled = isSaled;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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

	public String getTimeliness() {
		return timeliness;
	}

	public void setTimeliness(String timeliness) {
		this.timeliness = timeliness;
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

	public String getReservedField3() {
		return reservedField3;
	}

	public void setReservedField3(String reservedField3) {
		this.reservedField3 = reservedField3;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFromT() {
		return fromT;
	}

	public void setFromT(String fromT) {
		this.fromT = fromT;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}
	
	public Integer getBusinessStars() {
		return businessStars;
	}

	public void setBusinessStars(Integer businessStars) {
		this.businessStars = businessStars;
	}

	public Integer getPriceStars() {
		return priceStars;
	}

	public void setPriceStars(Integer priceStars) {
		this.priceStars = priceStars;
	}

	public String getTlName() {
		return tlName;
	}

	public void setTlName(String tlName) {
		this.tlName = tlName;
	}

	public String getStoresId() {
		return storesId;
	}

	public void setStoresId(String storesId) {
		this.storesId = storesId;
	}

	public String getStoresName() {
		return storesName;
	}

	public void setStoresName(String storesName) {
		this.storesName = storesName;
	}
	
}