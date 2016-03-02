package com.tcw.maiche.entity;

/**
 * 报名城市基础表
 * @author liuzhen
 * @date 2015.11.17
 */
public class CRegisterCity {
	/**
	 * 城市ID
	 */
    private String id;
    /**
     * 城市名称简写编码 如北京- bj
     */
    private String cityCode;
    /**
     * 排名序号
     */
    private String sort;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 描述
     */
    private String describe;
    /**
     * 有效性 0无效 1有效
     */
    private String isValid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort == null ? null : sort.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }
}