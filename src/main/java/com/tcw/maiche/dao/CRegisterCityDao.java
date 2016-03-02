package com.tcw.maiche.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tcw.common.BaseDao;
import com.tcw.maiche.entity.CRegisterCity;

@Component
public class CRegisterCityDao {
	
	@Autowired 
	private BaseDao baseDao;
	
    public int delete(String id){
    	return baseDao.create("mapper.standard.CRegisterCityMapper.delete", id);
    }

    public int insert(CRegisterCity city){
    	return baseDao.create("mapper.standard.CRegisterCityMapper.insert", city);
    }

    public CRegisterCity selectCRegisterCityByID(String id){
    	return baseDao.findOne("mapper.standard.CRegisterCityMapper.selectCRegisterCityByID", id);
    }

    public int update(CRegisterCity city){
    	return baseDao.findOne("mapper.standard.CRegisterCityMapper.update", city);
    }
    
    public List<Map<String, String>> selectRegCityCodeMap() {
    	return baseDao.findList("mapper.standard.CRegisterCityMapper.selectRegCityCodeMap");
    }

    public List<Map<String, String>> selectRegCityNameMap() {
    	return baseDao.findList("mapper.standard.CRegisterCityMapper.selectRegCityNameMap");
    }
}