/**
 * 12132015-11-19
 */
package com.tcw.maiche.standard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcw.car.entity.CarSelled;
import com.tcw.gouche.dao.CarSelledDao;
import com.tcw.gouche.standard.service.GoucheService;
import com.tcw.maiche.dto.CaseSelDto;
import com.tcw.report.standard.service.CarReportService;

/**
 *@author xyb
 *@date 2015-11-19
 *@description
 */
@Service
public class CaseService {
	
	@Autowired
	private CarSelledDao carSelledDao;
	
	@Autowired
	private CarReportService carReportService;
	
	@Autowired
	private GoucheService goucheService;
	
	/**
	 * 获取成交车辆
	 * @param crInfoDto
	 * @return
	 */
	public List<CarSelled> getDealCarInfo(CaseSelDto caseSel){
		List<CarSelled> result = carSelledDao.getDealCarInfo(caseSel);
		char[] mile = null;
		if(result != null && result.size() > 0) {
			for(CarSelled car : result) {
				car.setEmissionGb(goucheService.getEmissionGb(car.getEmissionGb()));
				mile = carReportService.getExtraIncome(car.getDealPrice());
				if(mile != null) {
					car.setExtraIncome(new String(mile));
				}else{car.setExtraIncome("0");}
			}
		}
		return result;
	}
	
}
