package com.tcw.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

public class Constants {
	
	//**********************资源文件信息**************************//
	//配置文件 ershouche.properties
	public static ResourceBundle MCONFIG = ResourceBundle.getBundle("ershouche");

	//**********************操作提示语**************************//
	public final static String RETURN_CODE_SUCCESS           = "000000";// 成功
	public final static String RETURN_MSG_SUCCESS    		 = "操作成功";//成功
	
	public final static String RETURN_CODE_FAIL = "111111";
	public final static String RETURN_MSG_FAIL = "操作失败";
	
	public final static String RETURN_CODE_SYSTEM_FAIL = "200000"; // 系统错误
	public final static String RETURN_MSG_SYSTEM_FAIL = "系统错误，请联系相关人员！";
	/** 电话错误 */
	public final static String RETURN_CODE_MOBILE_ERROR      = "500000";
	/** 删除标识-否*/
	public static String DELETE_TAG_NO = "0";
	
	//**********************时间格式转换**************************//
	/**时间格式 yyyy-MM-dd HH:mm:ss*/
	public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/**时间格式 yyyy-MM-dd HH:mm*/
	public final static String TIME_FORMAT2 = "yyyy-MM-dd HH:mm";
	/**时间格式 yyyy-MM-dd*/
	public final static String TIME_FORMAT3 = "yyyy-MM-dd";
	/**时间格式ss mm HH dd MM ? yyyy*/
	public final static String TIME_FORMAT4 = "ss mm HH dd MM ? yyyy";
	/**时间格式 HH:mm:ss*/
	public final static String TIME_FORMAT5 = "HH:mm:ss";
	/**时间格式 yyyy/MM/dd HH:mm:ss*/
	public final static String TIME_FORMAT6 = "yyyy/MM/dd HH:mm:ss";
	
	//**********************其他**************************//
	/** 验证码短信提醒 */
	public static String VERIFY_CODE_MSG = "验证码是:VerifyCode";
	/** 客户报名成功短信提醒 */
	public static String CUSTOMER_REGISTER_SUCCESS = "您已成功报名了团车二手车的旧车竞卖服务，稍后会有工作人员跟您联系，确定到店检车时间，咨询电话：51656556";
    
    /**
     * 获取服务器域名
     * @param request
     * @return
     */
    public static String getHostUrl(HttpServletRequest request){
    	String host = "http://";
    	 //获取所有的头和值
        Enumeration<String> e = request.getHeaders("Host");
        while(e.hasMoreElements()){
        	host+=e.nextElement();
        }
        return host;
    }
    
    /**
     * ERP相关
     *
     */
    public static final class ERPInfo{
    	/**
    	 * ERP报名业务类型
    	 */
		public static final String BUSINESS="00";
	}
    /**
     * 车300相关配置
     */
    public static final class Che300Config{
    	/** 车300 接入访问标示 */
    	private static final String TOKEN = "f716a50ab8f41f8ade954c38f3f38177";
    	
    	/** 接口基础URL */
    	private static final String BASE_API_URL = "http://api.che300.com/service/PublicService.php?oper=";
    	
    	/** 查询车辆品牌接口 */
    	public static final String GET_CARBRAND_URL = BASE_API_URL + "getCarBrandList&token="+TOKEN; 
    	
    	/** 获取车辆车系接口 */
    	public static final String GET_CARSERIES_URL = BASE_API_URL + "getCarSeriesList&brandId={brandId}&token="+TOKEN;
    	
    	/** 获取车辆型号接口 */
    	public static final String GET_CARMODEL_URL = BASE_API_URL + "getCarModelList&seriesId={seriesId}&token="+TOKEN;
    	
    	/** 获取城市列表 */
    	public static final String GET_ALLCITY_URL = BASE_API_URL + "getAllCity&token="+TOKEN;
    	
    	/** 估值接口 */
    	public static final String GUZHI_URL = BASE_API_URL + "getUsedCarPrice&modelId={modelId}&regDate={regDate}&mile={mile}&zone={zone}&token="+TOKEN;

    	/** 价格趋势接口 */
    	public static final String PRICE_TREND_URL = BASE_API_URL + "getPriceTrend&zone={zone}&modelId={modelId}&regDate={regDate}&mile={mile}&token="+TOKEN;
    }
    
    public static final int IS_NOTIFY_NO=0;
    
    public static final int IS_NOTIFY_YES=1;
    
    public static final String IS_DELETE_0="0";
    
    public static final String TIMELINESS_NO="1";
    
    public static final String TIMELINESS_YES="0";
    
    public static final String DEFAULT_USERNAME="匿名车主";
    
    public static final String FROMT_6="6";
    
    /**
     * 格式化日期
     * @param date    需格式化的日期
     * @param format  格式化类型，如yyyy/MM/dd HH:mm:ss 等
     * @return
     */
    public static String formatDate(Date date,String format){
    	if(date == null){
    		return "";
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	return sdf.format(date);
    } 
    /**
     * 业务类型:0.卖车  1.置换  2.报废  3.SEO 4.估值
     * @author liuzhen
     */
    public static final class Business{
    	
    	public static final String BUSINESS_MAICHE="0";
    	
    	public static final String BUSINESS_ZHIHUAN="1";
    	
    	public static final String BUSINESS_BAOFEI="2";
    	
    	public static final String BUSINESS_SEO="3";
    	
    	public static final String BUSINESS_GUZHI="4";
    }
    
}
