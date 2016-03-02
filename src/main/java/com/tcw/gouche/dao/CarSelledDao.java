package com.tcw.gouche.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcw.car.dto.CarInfoDto;
import com.tcw.car.entity.CarSelled;
import com.tcw.common.BaseDao;
import com.tcw.common.BaseDaoERP;
import com.tcw.gouche.entity.BuyerRegister;
import com.tcw.maiche.dto.CaseSelDto;

/**
 * 神奇代码，请勿改动   
 * Company：团车网
 * Description：已成交车dao
 **/
@Component
public class CarSelledDao {
	@Autowired 
	private BaseDaoERP baseDaoERP;
	@Autowired
	private BaseDao BaseDao;
	
	
	/**
	 * @功能描述：根据名称(车系、品牌名)获取成交数据
	 * @返回值：
	 **/
	public List<CarInfoDto> getDealCarInfoByName(CarInfoDto crInfoDto){
		return baseDaoERP.findList("mapper.standard.CarSelledMapper.getDealCarInfoByName",crInfoDto);
	}
	/**
	 * @功能描述：根据国家获取成交车辆
	 * @返回值：
	 **/
	public List<CarSelled> getDealCarInfo(CaseSelDto carSelled){
		return baseDaoERP.findList("mapper.standard.CarSelledMapper.getDealCarInfo",carSelled);
	}
	/**
	 * @功能描述：根据国家获取成交车辆总数(总数大于20只返回20)
	 * @返回值：
	 **/
	public int getDealCarInfoCount(CarSelled carSelled){
		return baseDaoERP.findOne("mapper.standard.CarSelledMapper.getDealCarInfoCount",carSelled);
	}
	
	
	/**
	 * 根据ID获取数据
	 * @param cid
	 * @return
	 */
	public List<CarInfoDto> getCarInfoByCid(String cid) {
		return baseDaoERP.findList("mapper.standard.CarSelledMapper.getCarInfoByCid", cid);
	}
	
	/**
	 * 买车报名
	 * @param buyer
	 * @return
	 */
	public int insertBuyerRegister(BuyerRegister buyer) {
		return BaseDao.create("mapper.standard.UserRegisterMapper.insertBuyerRegister", buyer);
	}
	/**
	 * 根据类型获取报名买车数量
	 * @param type
	 * @return
	 */
	public int getGoucheCoutByType(String type){
		return BaseDao.findOne("mapper.standard.UserRegisterMapper.getGoucheCoutByType", type);
	}
}