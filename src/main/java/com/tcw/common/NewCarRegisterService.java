package com.tcw.common;

import java.util.Date;

import org.apache.log4j.Logger;
import com.tuanche.crm.bean.Member;
import com.tuanche.crm.common.BizCodes;
import com.tuanche.crm.common.Sources;
import com.tuanche.crm.exception.CrmException;
import com.tuanche.crm.service.member.IMemberRegisterService;
import com.tuanche.crm.service.member.impl.MemberRegisterServiceImpl;

/**
 * 新车报名服务类
 *
 */
public class NewCarRegisterService implements Runnable{

	private Logger log = Logger.getLogger(this.getClass());
	private String phone;
	public NewCarRegisterService(String phone){
		this.phone = phone;
	}
	@Override
	public void run() {
		Member member = new Member();
		member.setPhone(Long.parseLong(phone)); //手机号码
		member.setBizCode(BizCodes.EMAICHE_BIZ_CODE);   //来源系统编码
		member.setSource(Sources.SOURCE_WAP);             //注册来源
		try{
			//MemberRegisterServiceImpl 建议用spring管理 一遍后面发布成dubbo服务时好替换
			IMemberRegisterService service = new MemberRegisterServiceImpl();
			member = service.registerMember(member);
		}catch(CrmException e){
			Date date = new Date();
			log.error("二手车报名调用新车接口失败，报名电话："+phone+",时间："+CommonUtils.formatDate(date, "yyyy/MM/dd HH:mm:ss"));
			log.error("Caused by: com.tuanche.crm.exception.CrmException: the user has exist");
		}
		System.out.println("------------------调用新车报名接口服务返回memberId:"+member.getId());
	}

}
