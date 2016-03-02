/**
 * 12132015-11-9
 */
package com.tcw.gouche.standard.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcw.car.entity.CarSelled;
import com.tcw.common.AuthorizationUtil;
import com.tcw.common.Constants;
import com.tcw.common.DicCache;
import com.tcw.common.Identities;
import com.tcw.common.MobileMsgSender;
import com.tcw.common.ResultDTO;
import com.tcw.gouche.dao.CarSelledDao;
import com.tcw.gouche.entity.BuyerRegister;
import com.tcw.maiche.dto.CaseSelDto;
import com.tcw.report.standard.service.CarReportService;

/**
 *@author xyb
 *@date 2015-11-9
 *@description
 */
@Service
public class GoucheService {
	@Autowired
	private CarSelledDao carSelledDao;
	@Autowired
	private CarReportService carReportService;
	
	/**
	 * 获取成交车辆
	 * @param crInfoDto
	 * @return
	 */
	public List<CarSelled> getDealCarInfo(CaseSelDto carSelled){
		List<CarSelled> result = carSelledDao.getDealCarInfo(carSelled);
		char[] mile = null;
		if(result != null && result.size() > 0) {
			for(CarSelled car : result) {
				car.setEmissionGb(getEmissionGb(car.getEmissionGb()));
				mile = carReportService.getExtraIncome(car.getDealPrice());
				if(mile != null) {
					car.setExtraIncome(new String(mile));
				}else{car.setExtraIncome("0");}
			}
		}
		return result;
	}
	
	public String getEmissionGb(String emissionGb) {
		String restr = "";
		/*国标*/
		if(null!=emissionGb && !"".equals(emissionGb)){
			switch (Integer.parseInt(emissionGb)) {
			case 0:
				restr = "国一";
				break;
			case 1:
				restr = "国二";
				break;
			case 2:
				restr = "国三";
				break;
			case 3:
				restr = "国四";
				break;
			case 4:
				restr = "国五";
				break;
			default:
				restr = "国三";
				break;
			}
		}else{
			restr = "国三";
		}
		return restr;
	}
	
	/**
	 * 获取成交车辆总数
	 * @param crInfoDto
	 * @return
	 */
	public int getDealCarInfoCount(CarSelled carSelled){
		return carSelledDao.getDealCarInfoCount(carSelled);
	}
	
	public String registerBuyer(BuyerRegister buyer) {
		String result = Constants.RETURN_CODE_SUCCESS;
		int retryCount = 5;  
	    boolean transactionCompleted = false; 
	    do{
	    	try {
	    		String userid=Identities.uuid2();
	    		String userName=buyer.getUserName();
	    		String mobile=buyer.getMobile();
	    		buyer.setUserId(userid);
	    		String fromT = buyer.getFromT();
	    		if(null != fromT && fromT.length() > 10){
	    			fromT = fromT.substring(0, 10);
	    			buyer.setFromT(fromT);
	    		}
	    		Date date = new Date();
	    		
	    		buyer.setIsDelete(Constants.DELETE_TAG_NO);
	    		buyer.setApplyTime(date);
	    		/**存储城市ID及名称 add by liuzhen**/
	    		String cityCode = buyer.getCityId();/**前端传值为城市编码**/
	    		String cityId = DicCache.getCityId(cityCode);//城市ID
	    		String areaName = DicCache.getCityName(cityId);
	    		buyer.setCityId(cityId);
	    		buyer.setAreaName(areaName);//城市名称
	    		buyer.setUserName(Constants.DEFAULT_USERNAME);//默认车主名称
	    		carSelledDao.insertBuyerRegister(buyer);
	    		String isSend = AuthorizationUtil.getInstance().getString("SEND_MESSAGE");
	    		transactionCompleted=true;
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		SQLException sqlEx=(SQLException)e;
	    		String sqlState = sqlEx.getSQLState();  
	    		if ("08S01".equals(sqlState) || "40001".equals(sqlState)){
	    			retryCount--;              
	    		} else {                  
	    			retryCount = 0;              
	    		}
	    		if(retryCount==0){
	    			String userMsg = Constants.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")+"| "+buyer.getUserName()+": "+buyer.getMobile();
	    			String isSend = AuthorizationUtil.getInstance().getString("SEND_MESSAGE");
	    			if(StringUtils.equals(isSend, "0")){
	    				warningMobile(userMsg,e.toString().split("\n")[0]);
	    			}
	    			result = Constants.RETURN_CODE_SYSTEM_FAIL;
	    		}
	    	}
	    }while(!transactionCompleted && (retryCount > 0));
		return result;
	}
	
	
	public void warningMobile(String name,String errorCode){
		String mobile = AuthorizationUtil.getInstance().getString("WARNING_MOBILE");
		String[] mobiles = mobile.split(",");
		for(int i=0;i<mobiles.length;i++){
			String content = name+" PC端报名失败："+errorCode;
			MobileMsgSender.sendMsg(mobiles[i], content);
		}
	}
	/**
	 * 根据预约类型获取报名买车数量
	 * @param type 预约类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResultDTO<?> getGoucheCoutByType(String type){
		ResultDTO<?> res = new ResultDTO();
		int count = carSelledDao.getGoucheCoutByType(type);
		res.setTotal(count);
		return res;
	}
}