/**
 * 12132015-11-11
 */
package com.tcw.maiche.standard.service;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcw.common.Constants;
import com.tcw.common.DicCache;
import com.tcw.common.ERPCreatCusUtil;
import com.tcw.common.MobileMsgSender;
import com.tcw.common.NewCarRegisterService;
import com.tcw.common.ResultDTO;
import com.tcw.maiche.dao.UserRegisterDao;
import com.tcw.maiche.entity.UserRegister;

/**
 *@author xyb
 *@date 2015-11-11
 *@description
 */
@Service
public class UserRegisterService {
	
	@Autowired
	private UserRegisterDao userRegisterDao;
	
	public ResultDTO<String> insertUserRegister(UserRegister user){
		ResultDTO<String> res=new ResultDTO<String>();
		res.setReturnCode(Constants.RETURN_CODE_SUCCESS);
		int retryCount = 5;  
	    boolean transactionCompleted = false; 
		do{
			try {
			/** 插入卖车信息*/
			String cityId = DicCache.getCityId(user.getCityId());
			user.setAreaName(cityId!=null ? DicCache.getCityName(cityId) : "");//设置报名的城市
			user.setCityId(cityId);
			String fromT = user.getFromT();
			if(null != fromT && fromT.length() > 10){
				fromT = fromT.substring(0, 10);
				user.setFromT(fromT);
			}
			user.setTimeliness(Constants.TIMELINESS_YES);
			String brandName = user.getBrandName();
			String biddingCode = user.getBiddingCode();
			String biddingKid = user.getBiddingKid();
			String business = user.getBusiness();
			if(brandName != null)user.setBrandName(URLDecoder.decode(brandName,"UTF-8"));
			if(biddingCode != null)user.setBiddingCode(URLDecoder.decode(biddingCode,"UTF-8"));
			if(biddingKid != null)user.setBiddingKid(URLDecoder.decode(biddingKid,"UTF-8"));
			if(business != null)user.setBusiness(URLDecoder.decode(business,"UTF-8"));
			user.setUserName(Constants.DEFAULT_USERNAME);//默认车主名称
			userRegisterDao.insertRegisterUser(user);
			transactionCompleted = true; 
			
			/** 向用户发送成功信息*/
			String mobile=user.getMobile();
			if(user.isSendInfo()){
				MobileMsgSender.sendMsg(mobile, Constants.CUSTOMER_REGISTER_SUCCESS);
			}
			
			/**调用erp接口*/
			String userid=user.getUserId();
			String carModel=user.getCarModel();
			String areaName = user.getAreaName();
			String applyId = user.getApplyId();
			String shop = user.getShop();
			if(user.getTimeliness() != Constants.TIMELINESS_NO){
				ERPCreatCusUtil erp = 
						new ERPCreatCusUtil(userid,
								user.getUserName(),
								Constants.formatDate(new Date(), "yyyy/MM/dd HH:mm:ss"),
								carModel,
								business,
								mobile,
								areaName,
								applyId,shop,fromT);
				Thread t = new Thread(erp);
				t.start();
			}

			} catch (Exception e) {
				String sqlState="";
				if(e instanceof SQLException){SQLException sqlEx=(SQLException)e; sqlState = sqlEx.getSQLState();}
	    		if ("08S01".equals(sqlState) || "40001".equals(sqlState)){
	    			retryCount--;              
	    		} else {                  
	    			retryCount = 0;              
	    		}
	    		if(retryCount==0){
	    			String isSend =Constants.MCONFIG.getString("SEND_MESSAGE");
	    			if(StringUtils.equals(isSend, "0")){
	    				warningMobile(Constants.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")+" ||"+user.getUserName()+": "+user.getMobile(),e.toString().split("\n")[0]);
	    			}
	    			e.printStackTrace();
	    		}
			}
		}while(!transactionCompleted && retryCount>0);
		
		/** 调用新车接口*/
		try{
				NewCarRegisterService newCar = new NewCarRegisterService(user.getMobile());
				Thread t1 = new Thread(newCar);
				t1.start();
		} catch (Exception e) {
				e.printStackTrace();
		} 
		if(!transactionCompleted){res.setReturnCode(Constants.RETURN_CODE_FAIL);}
		return res;
	}
	
	/**
	 * 错误短信
	 */
	public void warningMobile(String name,String errorCode){
		String mobile = Constants.MCONFIG.getString("WARNING_MOBILE");
		String[] mobiles = mobile.split(",");
		for(int i=0;i<mobiles.length;i++){
			String content = name+" 移动端报名失败："+errorCode;
			MobileMsgSender.sendMsg(mobiles[i], content);
		}
	}

}
