package com.tcw.maiche.standard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcw.maiche.dao.CRegisterCityDao;

@Service
public class CRegisterCityService {
	@Autowired
	private CRegisterCityDao cRegisterCityDao;

	public List<Map<String,String>> selectRegCityCodeMap(){
		return cRegisterCityDao.selectRegCityCodeMap();
	}
	
	public List<Map<String,String>> selectRegCityNameMap(){
		return cRegisterCityDao.selectRegCityNameMap();
	}
}
