/**
 * 12132015-11-11
 */
package com.tcw.maiche.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcw.common.BaseDao;
import com.tcw.maiche.entity.UserRegister;

/**
 *@author xyb
 *@date 2015-11-11
 *@description
 */
@Component
public class UserRegisterDao {
	
	@Autowired
	private BaseDao baseDao;

	/**
	 * @param user
	 */
	public void insertRegisterUser(UserRegister user) {
		baseDao.create("mapper.standard.UserRegisterMapper.insertSelective", user);
	}
	
	
	

}
