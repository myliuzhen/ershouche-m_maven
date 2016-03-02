/**
 * 12132015-11-6
 */
package com.tcw.maiche.entity;

import java.util.Date;

import com.tcw.common.Constants;
import com.tcw.common.Identities;

/**
 *@author xyb
 *@date 2015-11-6
 *@description 预约卖车实体
 */
public class UserRegister {
	private String userId=Identities.uuid2();;//用户ID
	private String userName;//用户ID
	private String mobile;//手机
	private Date applyTime=new Date();//预约时间
	private String isDelete=Constants.IS_DELETE_0;//0未删除1已删除
	private String timeliness;//0有效1无效
	private int isNotify;//是否回复0-否 1-是
	private Date notifyTime;//回复时间
	private String visitSite;	//访问站点
	private String carModel;	//旧车车型
	private String carYear;	//上牌时间
	private String shop;	//报名时选择的店
	private String fromT;//报名入口来源 , 0.竞价  1001.自然量  1101.团车首页  1201.置换报名 1301.seo  1401.微信端  1501.客户端  1601.网易火车票
	private String brandName;	//品牌，用于置换时保存置换品牌
	private String business;	//业务类型:0.卖车  1.置换  2.报废
	private String biddingCode;	//竞价标识账号名字
	private String biddingKid;	//竞价关键词
	private String applyId;		//新车网站传递过来的，直接传给电销接口newUserId
	private String areaName;	//报名地区
	private Date ts=new Date();
	private boolean sendInfo = true;//是否发送短信
	private String storesId;	//店ID
	private String qc;			//二级来源
	private String cityId;      //城市Id
	/*
	 * M端： 6，估值
	 */
	private String subFrom;
	/**
	 * 注册用户电话加密
	 */
	public String getMobileScr(){
		if(mobile!=null && mobile.length()>10){
			return mobile.substring(0, 3)+"****"+mobile.substring(6,10);
		}
		return mobile;
	}
	
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

	public String getTimeliness() {
		return timeliness;
	}

	public void setTimeliness(String timeliness) {
		this.timeliness = timeliness;
	}

	public int getIsNotify() {
		return isNotify;
	}
	public void setIsNotify(int isNotify) {
		this.isNotify = isNotify;
	}
	public Date getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getVisitSite() {
		return visitSite;
	}
	public void setVisitSite(String visitSite) {
		this.visitSite = visitSite;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getFromT() {
		return fromT;
	}
	public void setFromT(String fromT) {
		this.fromT = fromT;
	}

	public String getCarYear() {
		return carYear;
	}

	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getBiddingCode() {
		return biddingCode;
	}

	public void setBiddingCode(String biddingCode) {
		this.biddingCode = biddingCode;
	}

	public String getBiddingKid() {
		return biddingKid;
	}

	public void setBiddingKid(String biddingKid) {
		this.biddingKid = biddingKid;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public boolean isSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(boolean sendInfo) {
		this.sendInfo = sendInfo;
	}

	public String getStoresId() {
		return storesId;
	}

	public void setStoresId(String storesId) {
		this.storesId = storesId;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getQc() {
		return qc;
	}

	public void setQc(String qc) {
		this.qc = qc;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getSubFrom() {
		return subFrom;
	}

	public void setSubFrom(String subFrom) {
		this.subFrom = subFrom;
	}
	
}