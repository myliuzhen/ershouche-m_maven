package com.tcw.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

/**
 * ERP统计报名用户接口
 * @author huangh
 *
 */
public class ERPCreatCusUtil implements Runnable{

	private static Log log = LogFactory.getLog(ERPCreatCusUtil.class);
	
	private String userId;
	private String userName;
	private String applyTime;
	private String carModel;
	private String mobile;
	private String areaName;
	private String applyId;
	private String shop;
	private String fromT;
	private String business;
	
	public ERPCreatCusUtil(String userId,String userName,String applyTime,
			String carModel,String business,String mobile,String areaName,
			String applyId,String shop,String fromT){
		this.userId = userId;
		try {
			if(null != userName){
				this.userName = URLDecoder.decode(userName,"UTF-8");
			}else{
				this.userName = "";
			}
			if(null != carModel){
				this.carModel = URLDecoder.decode(carModel,"UTF-8");;
			}else{
				this.carModel = "";
			}
			if(null != areaName){
				this.areaName = URLDecoder.decode(areaName,"UTF-8");;
			}else{
				this.areaName = "";
			}
			if(null != shop){
				this.shop = URLDecoder.decode(shop,"UTF-8");;
			}else{
				this.shop = "";
			}
			if(null != fromT){
				this.fromT = URLDecoder.decode(fromT,"UTF-8");;
			}else{
				this.fromT = "";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		};
		this.applyTime = applyTime;
		this.business = business;
		this.mobile = mobile;
		this.applyId = applyId;
		this.shop = shop;
		this.fromT = fromT;
	}

	@Override
	public void run() {
		try {
				/*String business = "00";
				if(StringUtils.isNotBlank(fromT) && !fromT.equals("null") && ACCESS_FROMT.contains(fromT)){
					business = fromT;
				}*/
				/**business存值00；subFrom存6代表估值**/
				String subFrom = null;//二级入口，6 旧车估值（暂只存估值）
				if("4".equals(business))
					subFrom = "6";
				String businessT = Constants.ERPInfo.BUSINESS;
				//校验渠道号
				if(null==fromT || "".equals(fromT)){
					fromT = "1001";
				}
				String url;
				RestTemplate restTemplate = new RestTemplate();
				String ERP_CREAT_CUSTOMER_URL = Constants.MCONFIG.getString("ERP_CREAT_CUSTOMER_URL");
				url = 	ERP_CREAT_CUSTOMER_URL+
						"?userId="   +userId+
						"&userName="  +userName+
						"&applyTime="+applyTime+
						"&oldCarModel=" +URLEncoder.encode(carModel,"UTF-8")+
						"&business="+businessT+
						"&mobile="	 +mobile+
						"&areaName=" +areaName+
						"&newUserId=" +applyId+
						"&shop="+shop+
						"&fromT="+fromT+
						"&subFrom="+subFrom;
				String res = restTemplate.postForObject(url, null, String.class);
				System.out.println("---------------------连接ERP用户报名接口"+res);
		} catch (Exception e) {
			log.error("---------------------连接ERP用户报名接口失败："+e.toString());
		}
	}
	
}
