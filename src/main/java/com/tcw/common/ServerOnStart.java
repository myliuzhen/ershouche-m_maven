package com.tcw.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.tcw.maiche.standard.service.CRegisterCityService;

@Component
public class ServerOnStart implements BeanPostProcessor{
//	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private CRegisterCityService cRegisterCityService;
	
	
	/** 是否是第一次初始化，防止重复工作 */
	private static boolean isInited = false;
	@Override
	public Object postProcessAfterInitialization(Object arg0, String arg1)
			throws BeansException {
		if(!isInited){
			isInited = true;
			try {
				//城市编码初始化
				initCityCodeMap();
				//城市名称初始化
				initCityNameMap();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return arg0;
	}

	@Override
	public Object postProcessBeforeInitialization(Object arg0, String arg1)
			throws BeansException {
		return arg0;
	}

	/**
	 * 存储方式 ID:城市简写编码，如 10:bj
	 */
	private void initCityCodeMap(){
		DicCache.initCityCodeMap(cRegisterCityService.selectRegCityCodeMap());
	}
	/**
	 * 存储方式 ID:城市简写编码，如 10:北京
	 */
	private void initCityNameMap(){
		DicCache.initCityNameMap(cRegisterCityService.selectRegCityNameMap());
	}
	
}
