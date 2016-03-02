package com.tcw.guzhi.entity;

/**
 * Company：团车网
 * Description：车辆价格走势
 **/
public class CarPriceTrend {
	/** 年份 */
	private String register_year;
	/** 价格 */
	private String eval_price;
	public String getRegister_year() {
		return register_year;
	}
	public void setRegister_year(String register_year) {
		this.register_year = register_year;
	}
	public String getEval_price() {
		return eval_price;
	}
	public void setEval_price(String eval_price) {
		this.eval_price = eval_price;
	}
}
