package com.tcw.guzhi.entity;

public class GuzhiResult {
	private String status;
	private String eval_price; //估值结果
	private String low_price; //车况一般的估值
	private String good_price; //车况良好的估值
	private String high_price; //车况优秀的估值
	private String dealer_buy_price; //车商收购价
	private String individual_price; //个人交易价
	private String dealer_price; //车商零售价
	private String url;  //直达该车型在车三百网站详细估值结果页面
	private String price; //车型指导价
	private String title; //车型名称
	private String car_logo_url; //品牌的图片地址
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEval_price() {
		return eval_price;
	}
	public void setEval_price(String eval_price) {
		this.eval_price = eval_price;
	}
	public String getLow_price() {
		return low_price;
	}
	public void setLow_price(String low_price) {
		this.low_price = low_price;
	}
	public String getGood_price() {
		return good_price;
	}
	public void setGood_price(String good_price) {
		this.good_price = good_price;
	}
	public String getHigh_price() {
		return high_price;
	}
	public void setHigh_price(String high_price) {
		this.high_price = high_price;
	}
	public String getDealer_buy_price() {
		return dealer_buy_price;
	}
	public void setDealer_buy_price(String dealer_buy_price) {
		this.dealer_buy_price = dealer_buy_price;
	}
	public String getIndividual_price() {
		return individual_price;
	}
	public void setIndividual_price(String individual_price) {
		this.individual_price = individual_price;
	}
	public String getDealer_price() {
		return dealer_price;
	}
	public void setDealer_price(String dealer_price) {
		this.dealer_price = dealer_price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCar_logo_url() {
		return car_logo_url;
	}
	public void setCar_logo_url(String car_logo_url) {
		this.car_logo_url = car_logo_url;
	}
}
