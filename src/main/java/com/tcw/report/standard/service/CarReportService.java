/**
 * 12132015-11-10
 */
package com.tcw.report.standard.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tcw.car.dao.UsedCarDao;
import com.tcw.car.dto.CarInfoDto;
import com.tcw.car.entity.AccidentJc;
import com.tcw.car.entity.ApparentJc;
import com.tcw.car.entity.BaseImg;
import com.tcw.car.entity.PaintJc;
import com.tcw.car.entity.Photos;
import com.tcw.car.entity.ThroughFireJc;
import com.tcw.car.entity.ThroughWaterJc;
import com.tcw.car.entity.VdsTestReportJson;
import com.tcw.common.Constants;
import com.tcw.common.JSONUtil;
import com.tcw.report.entity.SingleCarReport;


/**
 *@author xyb
 *@date 2015-11-10
 *@description
 */
@Service
public class CarReportService {
	
	@Autowired
	private UsedCarDao usedCarDao;
	
	public Object[] getSingleInfo(CarInfoDto carInfoDto) {
		SingleCarReport report = new SingleCarReport();
		List<CarInfoDto> list =new  ArrayList<CarInfoDto>();
		list = usedCarDao.getNewOrder(carInfoDto);
		if(list.size()==1){
			carInfoDto=list.get(0);
			if(null==carInfoDto.getYearStyle()){
				carInfoDto.setYearStyle("");
			}
			/*成交日期*/
			if(null!=carInfoDto.getDealTime()){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				carInfoDto.setDealTimeStr(sdf.format(carInfoDto.getDealTime()));
			}
			/*国标*/
			if(null!=carInfoDto.getEmissionGb()){
				switch (Integer.parseInt(carInfoDto.getEmissionGb())) {
				case 0:
					carInfoDto.setEmissionGb("国一");
					break;
				case 1:
					carInfoDto.setEmissionGb("国二");
					break;
				case 2:
					carInfoDto.setEmissionGb("国三");
					break;
				case 3:
					carInfoDto.setEmissionGb("国四");
					break;
				case 4:
					carInfoDto.setEmissionGb("国五");
					break;
				default:
					carInfoDto.setEmissionGb("国三");
					break;
				}
			}else{
				carInfoDto.setEmissionGb("国三");
			}
			
			if(null!=carInfoDto.getCost()){
				carInfoDto.setExtraIncomeStr(new String(getExtraIncome(carInfoDto.getCost())));
			}
			if(null!=carInfoDto.getRegDate()){
				carInfoDto.setRegDate(carInfoDto.getRegDate().substring(0, 7));
			}else{
				carInfoDto.setRegDate("");
			}
			/*手续及违章状况*/
			StringBuffer procedures = new StringBuffer();
			if(null==carInfoDto.getOwnerIdType()){
				procedures.append("无车辆所有人证件，");
			}
			if(null==carInfoDto.getIsInsurance()||!carInfoDto.getIsInsurance().equals("0")){
				procedures.append("未交强险，");
			}
			if(null==carInfoDto.getIsInvoice()||!carInfoDto.getIsInvoice().equals("0")){
				procedures.append("无新车发票，");
			}
			if(null==carInfoDto.getIsPurchase()||!carInfoDto.getIsPurchase().equals("0")){
				procedures.append("无车辆购置税，");
			}
			if(null==carInfoDto.getIsTravel()||!carInfoDto.getIsTravel().equals("0")){
				procedures.append("无行驶证，");
			}
			if(procedures.length()==0){
				procedures.append("手续齐全，");
			}
			report.setProcedures(procedures.toString());
			if(null==carInfoDto.getViolation()&&""!=carInfoDto.getViolation()){
				report.setViolation("无违章");
			}else{
				if(carInfoDto.getViolation().contains("无")||carInfoDto.getViolation().contains("0")){
					report.setViolation("无违章");
				}else{
					report.setViolation("有违章");
				}
			}
			
			
		}else{
			return null;
		}
		VdsTestReportJson vdsTestReportJson= usedCarDao.getReportInfo(carInfoDto.getCarCode());
		if(null!=vdsTestReportJson){
			String traCarBaseImgJson=vdsTestReportJson.getBasePhotos()!=null?vdsTestReportJson.getBasePhotos():"";
			//照片
			if(!"".equals(traCarBaseImgJson)){
				List<BaseImg> BaseImgs = JSON.parseArray(traCarBaseImgJson, BaseImg.class);
				for (int i=0;i<BaseImgs.size();i++) {
					BaseImg baseImg =BaseImgs.get(i);
					if(baseImg.getImgText().contains("铭牌")||baseImg.getImgText().contains("VIN码")){
						BaseImgs.remove(i);
						i--;
					}else{
						baseImg.setImgName(Constants.MCONFIG.getString("IMG_DOWNLOAD_PATH")+"pictureV/"+carInfoDto.getCarBaseId()+"/b"+baseImg.getImgName());
					}
				}
				report.setTraCarBaseImgList(BaseImgs);
			}
			/*泡水检查*/
			ThroughWaterJc throughWaterJc = null;
			Map<String,String> waterMap = null;
			String throughWaterJcJson=vdsTestReportJson.getThroughWaterJc()!=null?vdsTestReportJson.getThroughWaterJc():"";
			if(!"".equals(throughWaterJcJson)&&!report.getIsAccident().equals("有事故")){
				throughWaterJc=JSONUtil.json2Entity(throughWaterJcJson, ThroughWaterJc.class.getName(), true);
				waterMap = JSONUtil.json2Entity(throughWaterJcJson, Map.class.getName(), true);
				for (Entry<String, String> key :waterMap.entrySet()) {
					if(!key.getValue().equals("0")){	
						report.setIsAccident("有事故");
						break;
					}
				}
			}
			/*过火检查*/
			ThroughFireJc throughFireJc = null;
			Map<String,String> fireMap = null;
			String throughFireJcJson=vdsTestReportJson.getThroughFireJc()!=null?vdsTestReportJson.getThroughFireJc():"";
			if(!"".equals(throughFireJcJson)&&!report.getIsAccident().equals("有事故")){
				throughFireJc=JSONUtil.json2Entity(throughFireJcJson, ThroughFireJc.class.getName(), true);
				fireMap = JSONUtil.json2Entity(throughFireJcJson, Map.class.getName(), true);
				for (Entry<String, String> key :fireMap.entrySet()) {
					if(!key.getValue().equals("0")){
						report.setIsAccident("有事故");
						break;
					}
				}
			}
			/*事故检查*/
			AccidentJc accidentJc = null;
			Map<String,String> accidentMap = null;
			String accidentJcJson=vdsTestReportJson.getAccidentJc()!=null?vdsTestReportJson.getAccidentJc():"";
			if(!"".equals(accidentJcJson)&&!report.getIsAccident().equals("有事故")){
				accidentJc=JSONUtil.json2Entity(accidentJcJson, AccidentJc.class.getName(), true);
				if(null!=accidentJc.getEngineType()){//发动机描述
					report.setEngine(accidentJc.getEngineType());
				}else{
					report.setEngine("发动机状况良好");
				}
				for (Photos photo : accidentJc.getPhotos()) {
					if(null!=photo.getMarLevel()){
						photo.getMarLevel().equals("严重");
					}
//					report.setIsAccident("有事故");
					break;
				}
			}
			/*车架状况*/
			String apparenceJcJson=vdsTestReportJson.getAppearanceJc()!=null?vdsTestReportJson.getAppearanceJc():"";
			if(!"".equals(apparenceJcJson)){
				StringBuffer sb =new StringBuffer();
				 List<ApparentJc> apparentJcs = JSON.parseArray(apparenceJcJson, ApparentJc.class);  
				 int i=0;
		         for (ApparentJc apparentJc : apparentJcs) {  
		            String content = apparentJc.getImgText()+apparentJc.getMarLevel()+apparentJc.getMarKind()+",";
		            sb.append(content);
		            i++;
		            if(i==2)
		            break;//只添加两句描述	
		         }  
		         if(sb.toString().length()>0){
		        	 report.setApparent(sb.toString().substring(0, sb.toString().length()-1));
		         }else{
		        	 report.setApparent("车架状况良好");
		         }
			}
			//漆面评价
			String paintJcJson=vdsTestReportJson.getPaintJc()!=null?vdsTestReportJson.getPaintJc():"";
			if(""!=paintJcJson){
				StringBuffer sb =new StringBuffer();
				List<PaintJc> paintJcs = JSON.parseArray(paintJcJson, PaintJc.class);
				int i=0;
		         for (PaintJc paintJc : paintJcs) {  
		            String content = paintJc.getImgText()+paintJc.getMarKind()+",";
		            sb.append(content);
		            i++;
		            if(i==2)
		            break;//只添加两句描述	
		         } 
		         if(sb.toString().length()>0){
		        	 report.setPaint(sb.toString().substring(0, sb.toString().length()-1));
		         }else{
		        	 report.setPaint("车身无喷漆痕迹");
		         }
			}
			//内饰状况
			String innerJcJson=vdsTestReportJson.getInnerJc()!=null?vdsTestReportJson.getInnerJc():"";
			if(!"".equals(innerJcJson)){
				StringBuffer sb =new StringBuffer();
				 List<ApparentJc> innerJcs = JSON.parseArray(innerJcJson, ApparentJc.class); 
				 if(innerJcs.size()>0){
					 int i=0;
					 for (ApparentJc apparentJc : innerJcs) {  
						 String content = apparentJc.getImgText()+apparentJc.getMarKind()+",";
						 sb.append(content);
						 i++;
						 if(i==2){
							 break;//只添加两句描述	
						 }
					 }
				 }
		         if(sb.toString().length()>0){
		        	 report.setInside(sb.toString().substring(0,sb.length()-1));
		         }else{
		        	 report.setInside("内饰状况良好");
		         }
			}
			//成交车辆评分
			if(null!=vdsTestReportJson.getCarLevel()){
				String score = vdsTestReportJson.getCarLevel().substring(0, 2);
				String letter = vdsTestReportJson.getCarLevel().substring(2, 3);
				/*if(letter.equals("A")){
					score=String.valueOf(Integer.parseInt(score)+15);
				}
				if(letter.equals("B")){
					score=String.valueOf(Integer.parseInt(score)+10);
				}
				if(letter.equals("C")){
					score=String.valueOf(Integer.parseInt(score)+5);
				}*/
				/**修改为只在原分数加5，并且最高为100*/
				int scoreInt=Integer.parseInt(score)+5;
				if(scoreInt>=100) scoreInt=100;
				score=String.valueOf(scoreInt);
				report.setCarScore(score);
			}
			/*配置亮点*/
			if(null!=vdsTestReportJson.getConfigJc()){
				StringBuffer configStr=new StringBuffer();
				Map<String,Object> configMap  = JSONUtil.json2Entity(vdsTestReportJson.getConfigJc(), Map.class.getName(), true);
				for (Entry<String, Object> key :configMap.entrySet()) {
					/*
					
					if(key.getKey().equals("seatFabric")&&!key.getValue().equals("无")){
						configStr.append(key.getValue()+"，");
					}
					
					if(key.getKey().equals("cruise")&&!key.getValue().equals("无")){
						configStr.append("定速巡航，"); 
					}
					
					*/
					
					if(key.getKey().equals("asternRadar")&&!key.getValue().equals("无")){
						configStr.append("倒车雷达，");
					}
					
					if(key.getKey().equals("smartKey")&&!key.getValue().equals("无")){
						configStr.append("智能钥匙，"); 
					}
					
					if(key.getKey().equals("navigation")&&!key.getValue().equals("无")){
						configStr.append("导航，"); 
					}
					
					if(key.getKey().equals("abs")&&!key.getValue().equals("无")){
						configStr.append("ABS，");
					}
					
					if(key.getKey().equals("cruise")&&!key.getValue().equals("无")){
						configStr.append("css定速巡航，"); 
					}
					
					if(key.getKey().equals("airbagNo")&&!key.getValue().toString().equals("0")&&!key.getValue().toString().equals("无")){
						configStr.append("安全气囊，"); 
					}
					 
					if(key.getKey().equals("asternPhoto")&&!key.getValue().equals("无")){
						configStr.append("倒车影像，");
					}
					
					if(key.getKey().equals("sunroof")&&!key.getValue().equals("无")){
						configStr.append("有天窗，");
					}
					
				}
				if(configStr.toString().length()>0)report.setConfigStr(configStr.toString().substring(0, configStr.length()-1));
			}
		}
		Object[] result=new Object[2];
		result[0]=carInfoDto;
		result[1]=report;
		return result ;
	}
	
	/**
	 * @功能描述：根据成交价格获取多卖价格
	 * @输入参数：
	 * @必填参数：
	 * @创建人： zhangfan
	 * @日期：2015年05月27日
	 * @返回值：
	 **/
	public char[] getExtraIncome(String money){
		if(null==money) return null;
		int n =Integer.parseInt(money);
		if(8000<=n&&n<15000){
			return String.valueOf((n*13/10000*100)).toCharArray();
		}
		if(15000<=n&&n<20000){
			return String.valueOf((n*9/10000*100)).toCharArray();
		}
		if((20000<=n&&n<30000)||(30000<=n&&n<45000)||(80000<=n&&n<100000)){
			return String.valueOf((n*11/10000*100)).toCharArray();
		}
		if((45000<=n&&n<60000)||(60000<=n&&n<80000)){
			return String.valueOf((n*12/10000*100)).toCharArray();
		}
		if(100000<=n&&n<130000){
			return String.valueOf((n*10/10000*100)).toCharArray();
		}
		if((130000<=n&&n<150000)||(150000<=n&&n<180000)||(180000<=n&&n<200000)){
			return String.valueOf((n*8/10000*100)).toCharArray();
		}
		if((200000<=n&&n<250000)||(250000<=n&&n<300000)){
			return String.valueOf((n*7/10000*100)).toCharArray();
		}
		if((n>=300000)){
			return String.valueOf((n*6/10000*100)).toCharArray();
		}
		return null;
	}
}
