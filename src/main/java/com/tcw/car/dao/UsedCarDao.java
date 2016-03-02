/**
 * 12132015-11-10
 */
package com.tcw.car.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcw.car.dto.CarInfoDto;
import com.tcw.car.entity.CtmLine;
import com.tcw.car.entity.CtmManufacturer;
import com.tcw.car.entity.VdsTestReportJson;
import com.tcw.common.BaseDao;
import com.tcw.common.BaseDaoERP;

/**
 *@author xyb
 *@date 2015-11-10
 *@description
 */
@Component
public class UsedCarDao {
	
	@Autowired 
	private BaseDaoERP baseDaoERP;
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * @功能描述：获取已成交车辆信息列表
	 * @输入参数：
	 * @必填参数：
	 * @创建人： huanghao
	 * @日期：2014年1月9日
	 * @返回值：
	 **/
	public List<CarInfoDto> getDealCarInfo(CarInfoDto carinfo){
		return baseDaoERP.findList("mapper.standard.usedCarMapper.getDealCarInfo",carinfo);
	}
	/**
	 * @功能描述：通过厂商名称获或车系名称 取已成交车辆信息列表
	 * @输入参数：
	 * @必填参数：
	 * @创建人： huanghao
	 * @日期：2015年1月22日
	 * @返回值：
	 **/
	public List<CarInfoDto> getDealCarInfoByName(CarInfoDto carinfo){
		return baseDaoERP.findList("mapper.standard.usedCarMapper.getDealCarInfoByName",carinfo);
	}
	/**
	 * @功能描述：获取所有厂商品牌
	 * @输入参数：
	 * @必填参数：
	 * @创建人： huanghao
	 * @日期：2014年1月9日
	 * @返回值：
	 **/
	public List<CtmManufacturer> getAllManufacturer(){
		return baseDaoERP.findList("mapper.standard.usedCarMapper.getAllManufacturer");
	}
	
	/**
	 * @功能描述：获取车系实体
	 * @输入参数：
	 * @必填参数：
	 * @创建人： huanghao
	 * @日期：2014年1月9日
	 * @返回值：
	 **/
	public CtmLine getCtmLine(CtmLine ctmLine){
		return baseDaoERP.findOne("mapper.standard.usedCarMapper.getCtmLine",ctmLine);
	}
	/**
	 * @功能描述：获取车系列表升序
	 * @输入参数：
	 * @必填参数：
	 * @创建人： huanghao
	 * @日期：2014年1月9日
	 * @返回值：
	 **/
	public List<CtmLine> getCtmLineOrderBySeriesNameAsc(CtmLine ctmLine){
		return baseDaoERP.findList("mapper.standard.usedCarMapper.getCtmLineOrderBySeriesNameAsc1",ctmLine);
	}
	/**
	 * @功能描述：在团车二手车卖掉旧车卖掉的总数
	 * @输入参数：
	 * @必填参数：
	 * @创建人： huanghao
	 * @日期：2014年1月9日
	 * @返回值：
	 **/
	public int getDealCount(){
		return baseDaoERP.findOne("mapper.standard.usedCarMapper.getDealCount");
	}
	
	/**
	 * @功能描述：通过车牌号获取卖家电话号码
	 * @输入参数：
	 * @必填参数：
	 * @创建人： huanghao
	 * @日期：2015年4月3日
	 * @返回值：
	 **/
	public String getUserMobileByLicense(CarInfoDto carinfo){
		String mobile = null;
		 List<CarInfoDto> list = baseDaoERP.findList("mapper.standard.usedCarMapper.getUserMobileByLicense",carinfo);
		 if (list.size() > 0) {
			 mobile = list.get(0).getMobile();
		}
		return mobile;
	}
	/**
	 * @功能描述：获取成交车辆信息
	 * @输入参数：
	 * @必填参数：
	 * @创建人： zhangfan
	 * @日期：2015年5月20日
	 * @返回值:list
	 */
	public List<CarInfoDto> getAllDealModel(CarInfoDto carInfoDto){
		List<CarInfoDto> list = baseDaoERP.findList("mapper.standard.usedCarMapper.getDealCarModel",carInfoDto);
		return list;
	}
	/**
	 * @功能描述：根据baseid 获取单个成交车辆详情
	 * @输入参数：baseid
	 * @必填参数：
	 * @创建人： zhangfan
	 * @日期：2015年5月28日
	 * @返回值:list
	 */
	public List<CarInfoDto> getSingleDealCar(String carCode){
		List<CarInfoDto> list = baseDaoERP.findList("mapper.standard.usedCarMapper.getSingleDealCar",carCode);
		return list;
	}
	/**
	 * @功能描述：获取成交车辆信息
	 * @输入参数：
	 * @必填参数：
	 * @创建人： zhangfan
	 * @日期：2015年6月3日
	 * @返回值:list
	 */
	public List<CarInfoDto> getNewOrder(CarInfoDto carInfoDto){
		List<CarInfoDto> list = baseDaoERP.findList("mapper.standard.usedCarMapper.getNewOrder",carInfoDto);
		return list;
	}
	/**
	 * @功能描述：新版二手车页面获取所有车型（如：suv，越野车）
	 * @输入参数：
	 * @必填参数：
	 * @创建人： zhangfan
	 * @日期：2015年6月5日
	 * @返回值：
	 **/
	public List<CarInfoDto> getAllCarModel(){
		return baseDaoERP.findList("mapper.standard.usedCarMapper.getAllCarModel");
	}
	
	/**
	 * @功能描述：根据条件获取所有车辆
	 * @输入参数：
	 * @必填参数：
	 * @创建人： zhangfan
	 * @日期：2015年6月3日
	 * @返回值:list
	 */
	public List<CarInfoDto> selectOrderList(CarInfoDto carInfoDto,int start,int length){
		List<CarInfoDto> list = baseDaoERP.findList("mapper.standard.usedCarMapper.selectOrderList", carInfoDto, start, length);
		return list;
	}
	public int countOrderList(CarInfoDto carInfoDto){
		int total = baseDaoERP.findOne("mapper.standard.usedCarMapper.countOrderList",carInfoDto);
		return total;
	}
	public VdsTestReportJson getReportInfo(String carCode){
		return baseDaoERP.findOne("mapper.standard.usedCarMapper.getReportInfo", carCode);
	}
	/**
	 * @功能描述：根据carcode获取所有车辆
	 * @输入参数：
	 * @必填参数：
	 * @创建人： niuxiangquan
	 * @日期：2015年9月28日
	 * @返回值:list
	 */
	public List<CarInfoDto> getAllDealModelByIds(List<String> ids) {
		Map< String, Object> map =new HashMap<String, Object>();
		map.put("ids", ids);
		return baseDaoERP.findList("mapper.standard.usedCarMapper.getDealCarInfoByIds",map);
	}
	
	

}
