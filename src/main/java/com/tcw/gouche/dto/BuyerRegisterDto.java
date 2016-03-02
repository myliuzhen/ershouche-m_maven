package com.tcw.gouche.dto;

/**
 * 用户报名参数
 **/
public class BuyerRegisterDto {

    private String visitSite;    //访问站点
    private String fromT;        //来源
    private String mobile;//手机号
    private String budget;//卖车预算
    private String cityId;//报名城市ID

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
