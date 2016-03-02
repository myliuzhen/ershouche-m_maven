package com.tcw.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * Company：团车网
 * Description：通用工具类
 * @author YuNingbo
 * @date 2014-9-4
 * @use 主要用于提供通用的工具或方法
 **/
public class CommonUtils {
	
	
	/**
	 * 将字符串数字转成人民币格式
	 * 人民币格式事例：￥99,999,999
	 */
	public static String convertMoney(String money){
		if(StringUtils.isBlank(money) || "null".equals(money) || "0".equals(money)){
			return "";
		}
		StringBuffer result = new StringBuffer(money);
		if(result.indexOf(".")>0){
			result.delete(result.indexOf("."), result.length());
		}
		if(money.length() >= 4){
			for(int i=result.length()-3;i>0;i=i-3){
				result.insert(i, ",");
			}
		}
		result.insert(0, "¥");
		return  result.toString();
	}
	
	/**
	 * 将字符串数字转成人民币格式
	 * 人民币格式事例：￥99,999,999
	 */
	public static String convertMoneyWithoutY(String money){
		if(StringUtils.isBlank(money) || "null".equals(money) || "0".equals(money)){
			return "";
		}
		StringBuffer result = new StringBuffer(money);
		if(result.indexOf(".")>0){
			result.delete(result.indexOf("."), result.length());
		}
		if(money.length() >= 4){
			for(int i=result.length()-3;i>0;i=i-3){
				result.insert(i, ",");
			}
		}
		return  result.toString();
	}
	
	/**
	 * Company:团车网
	 * Description：保留三位有效数字
	 * @author YuNingbo
	 * @date 2014-11-7
	 */
	public static String parseThreeNumber(double d){
		String str = String.valueOf(d);
		int index = str.indexOf(".");
		if(index>3){
			str = str.substring(0, index);
			return str;
		}else if(index==-1){
			return str;
		}else{
			if(str.length()>4){
				return str.substring(0,4);
			}else{
				for(int i=0;i<4-str.length();i++){
					str += "0";
				}
				return str;
			}
		}
	}
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
	 * 截取url中的cityCode
	 * @param request
	 * @return
	 */
	public static String getRequestUrlArea(HttpServletRequest request) {
		StringBuffer areaUrl = request.getRequestURL();
		if(areaUrl != null && !"".equals(areaUrl) && areaUrl.toString().contains(".tuanche")) {
			//截取规则，域名变化，修改此处
			String[] areaUrls = areaUrl.toString().split("/");
			if(areaUrls.length > 4) {
				return areaUrls[4];
			}
		}
		return "bj";
	}
}