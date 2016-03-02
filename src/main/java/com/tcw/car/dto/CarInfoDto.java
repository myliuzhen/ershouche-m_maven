package com.tcw.car.dto;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import com.tcw.car.entity.CarCaseComment;
import com.tcw.common.Constants;

public class CarInfoDto {
	private String id;
	private String carCode;				//车辆编码
	private String carInfo;				//车辆信息
	private String manufacturer;		//品牌
	private String manufacturerCode;	//品牌
	private String model;				//车型
	private String license;				//车牌号
	private String seriesCode;			//车系code
	private String seriesName;			//车系
	private String color;				//颜色
	private String rank;				//等级
	private String regDate;				//初登日期
	private String mileage;				//行驶里程
	private String carFirstImg;			//车辆图片
	private String carFirstImg1;			//车辆图片转换后
	private String carAgeArea;          //出厂日期区间
	private String orderCeatedTime;     //订单生成时间
	private Date factoryTime;			//出厂日期
	private String violation;           //违章记录
	private int purchaserClosingCost;	//买方成交价-最高出价
	private Date dealTime;				//成交时间
	private String country;				//车系所属国家
	private String[] countrys;			//车系所属国家数组
	private String storesName;			//门店名称
	private int rowNum;					//显示条数
	private int startRow;				//分页开始
	private int endRow;					//分页结束
	private String orderWay;			//排序方式
	private CarCaseComment carCaseComment;//点评信息
	private List<String> ids;			//id集合
	private String IMG_URL =Constants.MCONFIG.getString("IMG_URL");//图片地址
	private String mobile;	//用户电话
	private String cost; //上成交价格
	private String carBaseId;//基本表id
	private char[] extraIncome;//多卖
	private String extraIncomeStr;//多卖字符串
	private int n;//取出记录个数
	private String indexSearch;//页头搜索字段
	private String resultSearch;//结果中查询字段
	private String ownerIdType;//车辆所有人证件类型
	private String isTravel;//是否有行驶证
	private String isPurchase;//是否有车辆购置税
	private String isInsurance;//是否交强险
	private String isInvoice;//是否有新车发票
	private String yearStyle;//年款
	private String emissionGb;//排量
	private String localImport;//进出口
	private String dealTimeStr;//成交时间字符串
	private String registry;//上牌地区
	
	public String getRegistry() {
		return registry;
	}
	public void setRegistry(String registry) {
		this.registry = registry;
	}
	/*** 图片 **/
	public String getCarImg632x436() {	//车辆大图字段 carImg632x436
		if(IMG_URL==null || carCode==null || carFirstImg==null){
			return "";
		}
		return IMG_URL.replace("${width}", "632")
					  .replace("${height}", "436")
					  .replace("${carCode}", carCode)
					  .replace("${carImg}", carFirstImg);
	}
	/*** 图片 **/
	public String getCarImg150x113() {	//车辆预览字段 carImg150x113
		if(IMG_URL==null || carCode==null || carFirstImg==null){
			return "";
		}
		return IMG_URL.replace("${width}","150")
					  .replace("${height}","113")
					  .replace("${carCode}",carCode)
					  .replace("${carImg}", carFirstImg);
	}
	/*** 图片 **/
	public String getCarImg270x192() {	//车辆预览字段 carImg270x192
		if(IMG_URL==null || carCode==null || carFirstImg==null){
			return "";
		}
		return IMG_URL.replace("${width}", "270")
					  .replace("${height}", "192")
					  .replace("${carCode}", carCode)
					  .replace("${carImg}", carFirstImg);
	}
	/*** 图片 **/
	public String getCarImg232x168() {	//车辆预览字段 carImg232x168
		if(IMG_URL==null || carCode==null || carFirstImg==null){
			return "";
		}
		return IMG_URL.replace("${width}", "232")
					  .replace("${height}", "168")
					  .replace("${carCode}", carCode)
					  .replace("${carImg}", carFirstImg);
	}
	/*** 图片 **/
	public String getCarImg263x175() {	//车辆预览字段 carImg263x175
		if(IMG_URL==null || carCode==null || carFirstImg==null){
			return "";
		}
		return IMG_URL.replace("${width}", "263")
					  .replace("${height}", "175")
					  .replace("${carCode}", carCode)
					  .replace("${carImg}", carFirstImg);
	}
	public String getCarImg200x132() {	//车辆预览字段 carImg200x132
		if(IMG_URL==null || carCode==null || carFirstImg==null){
			return "";
		}
		return IMG_URL.replace("${width}", "200")
					  .replace("${height}", "132")
					  .replace("${carCode}", carCode)
					  .replace("${carImg}", carFirstImg);
	}
	public String getCarName(){			//字段 carName(格式化后的成立名称)
		if (null != regDate) {
			return regDate.split("-")[0]+"年 "+carInfo;
		}else {
			return carInfo;
		}
	}
	/*** 初登日期 **/
	public String getFormatRegDate(){	//字段 formatRegDate(格式化后的日期)
		if (null != regDate) {
			String[] d = regDate.split("-");
			if (d.length>=2 ) {
				return d[0]+"年"+d[1]+"月";
			}
		}
		return "";
	}
	
	public String getFormatMileage() {	//字段 formatMileage(格式化后的里程)
		try {
			if(mileage!=null&&mileage!=""){
				double m = Double.parseDouble(mileage);
				NumberFormat nf = NumberFormat.getNumberInstance();
		        nf.setMaximumFractionDigits(2);
		        if(m<100){
		        	return m+"公里";
		        }else{
		        	return nf.format(m/10000)+"万公里";
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getCarInfo() {
		return carInfo;
	}
	public void setCarInfo(String carInfo) {
		this.carInfo = carInfo;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getManufacturerCode() {
		return manufacturerCode;
	}
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	
	public String getSeriesCode() {
		return seriesCode;
	}
	public void setSeriesCode(String seriesCode) {
		this.seriesCode = seriesCode;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getRank() {
		if(rank == null){
			return "";
		}
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getCarFirstImg() {
		return carFirstImg;
	}
	public void setCarFirstImg(String carFirstImg) {
		this.carFirstImg = carFirstImg;
	}
	public int getPurchaserClosingCost() {
		return purchaserClosingCost;
	}
	public void setPurchaserClosingCost(int purchaserClosingCost) {
		this.purchaserClosingCost = purchaserClosingCost;
	}
	public Date getFactoryTime() {
		return factoryTime;
	}
	public void setFactoryTime(Date factoryTime) {
		this.factoryTime = factoryTime;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String[] getCountrys() {
		return countrys;
	}
	public void setCountrys(String[] countrys) {
		this.countrys = countrys;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public CarCaseComment getCarCaseComment() {
		return carCaseComment;
	}
	public void setCarCaseComment(CarCaseComment carCaseComment) {
		this.carCaseComment = carCaseComment;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getCarBaseId() {
		return carBaseId;
	}
	public void setCarBaseId(String carBaseId) {
		this.carBaseId = carBaseId;
	}
	public char[] getExtraIncome() {
		return extraIncome;
	}
	public void setExtraIncome(char[] extraIncome) {
		this.extraIncome = extraIncome;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public String getCarAgeArea() {
		return carAgeArea;
	}
	public void setCarAgeArea(String carAgeArea) {
		this.carAgeArea = carAgeArea;
	}
	public String getOrderWay() {
		return orderWay;
	}
	public void setOrderWay(String orderWay) {
		this.orderWay = orderWay;
	}
	public String getOrderCeatedTime() {
		return orderCeatedTime;
	}
	public void setOrderCeatedTime(String orderCeatedTime) {
		this.orderCeatedTime = orderCeatedTime;
	}
	public String getViolation() {
		return violation;
	}
	public void setViolation(String violation) {
		this.violation = violation;
	}
	public String getResultSearch() {
		return resultSearch;
	}
	public void setResultSearch(String resultSearch) {
		this.resultSearch = resultSearch;
	}
	public String getStoresName() {
		return storesName;
	}
	public void setStoresName(String storesName) {
		this.storesName = storesName;
	}
	public String getOwnerIdType() {
		return ownerIdType;
	}
	public void setOwnerIdType(String ownerIdType) {
		this.ownerIdType = ownerIdType;
	}
	public String getIsTravel() {
		return isTravel;
	}
	public void setIsTravel(String isTravel) {
		this.isTravel = isTravel;
	}
	public String getIsPurchase() {
		return isPurchase;
	}
	public void setIsPurchase(String isPurchase) {
		this.isPurchase = isPurchase;
	}
	public String getIsInsurance() {
		return isInsurance;
	}
	public void setIsInsurance(String isInsurance) {
		this.isInsurance = isInsurance;
	}
	public String getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}
	public String getYearStyle() {
		return yearStyle;
	}
	public void setYearStyle(String yearStyle) {
		this.yearStyle = yearStyle;
	}
	public String getEmissionGb() {
		return emissionGb;
	}
	public void setEmissionGb(String emissionGb) {
		this.emissionGb = emissionGb;
	}
	public String getLocalImport() {
		return localImport;
	}
	public void setLocalImport(String localImport) {
		this.localImport = localImport;
	}
	public String getIndexSearch() {
		return indexSearch;
	}
	public void setIndexSearch(String indexSearch) {
		this.indexSearch = indexSearch;
	}
	public String getExtraIncomeStr() {
		return extraIncomeStr;
	}
	public void setExtraIncomeStr(String extraIncomeStr) {
		this.extraIncomeStr = extraIncomeStr;
	}
	public String getDealTimeStr() {
		return dealTimeStr;
	}
	public void setDealTimeStr(String dealTimeStr) {
		this.dealTimeStr = dealTimeStr;
	}
	public String getCarFirstImg1() {
		return carFirstImg1;
	}
	public void setCarFirstImg1(String carFirstImg1) {
		this.carFirstImg1 = carFirstImg1;
	}
}
