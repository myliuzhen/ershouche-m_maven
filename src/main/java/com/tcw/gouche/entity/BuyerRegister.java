package com.tcw.gouche.entity;

import java.util.Date;

/**
 * 用户报名实体类   
 **/
public class BuyerRegister {
	private String userId;
	private String userName;
	private String mobile;
	private Date applyTime;
	private String isDelete;
	private String visitSite;	//访问站点
	private String fromT;		//来源
	private String areaName;	//报名地区
	private String messageContent;//报名成功短信内容
	private boolean sendInfo = true;//是否发送短信
	private String isJc;   //是否是检测报名 1-是 
	private String budget; //卖车预算
	private String mGoucheType; //M端买车类型 (0二手车 1准新车 2练手车 3实惠豪车 4经济适用 5全能家用 6宜商宜家)
	/**
	 * 报名城市ID
	 */
	private String cityId;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getVisitSite() {
		return visitSite;
	}
	public void setVisitSite(String visitSite) {
		this.visitSite = visitSite;
	}
	
	public String getFromT() {
		return fromT;
	}
	public void setFromT(String fromT) {
		this.fromT = fromT;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public boolean isSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(boolean sendInfo) {
		this.sendInfo = sendInfo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getIsJc() {
		return isJc;
	}
	public void setIsJc(String isJc) {
		this.isJc = isJc;
	}
	
	@Override
	public String toString() {
		return "BuyerRegister [userId=" + userId + ", userName=" + userName
				+ ", mobile=" + mobile + ", applyTime=" + applyTime
				+ ", isDelete=" + isDelete + ", visitSite=" + visitSite
				+ ", fromT=" + fromT + ", areaName=" + areaName
				+ ", messageContent=" + messageContent + ", sendInfo="
				+ sendInfo + ", isJc=" + isJc + ", budget=" + budget
				+ ", cityId=" + cityId + "]";
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getmGoucheType() {
		return mGoucheType;
	}
	public void setmGoucheType(String mGoucheType) {
		this.mGoucheType = mGoucheType;
	}
}