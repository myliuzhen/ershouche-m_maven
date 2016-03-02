package com.tcw.common;

import java.util.Random;

import com.tcw.common.MobileMsgSender;

/**
 * 验证码工具类
 * @author
 *
 */
public class VerifyCodeUtil {

	private static long expirationTiem = 120000;//验证码保存时间 120秒
	private String verifyCode;					//验证码
	private String mobilePhone ;				//验证码对应的手机号
	private long creatTime;						//验证码生成时间
	
	/**
	 * 传入手机号初始化
	 * @param mobilePhone	手机号
	 * @author huanghao		
	 */
	public VerifyCodeUtil(String mobilePhone){
		this.verifyCode = creatVerifyCode(mobilePhone);
		this.mobilePhone = mobilePhone;
		this.creatTime = System.currentTimeMillis();
	}
	
	/**
	 * 验证 验证码
	 * @param code		验证码
	 * @param mobile	手机号
	 * @return
	 */
	public boolean CheckCode(String code,String mobile){
		if(null == code || "" == code || code.length() < 1 || null == mobile || "" == mobile){
			return false;
		}
		long currentTime = System.currentTimeMillis();
		if((currentTime-creatTime)>expirationTiem){ 
			return false;
		}
		if(!code.equals(verifyCode) || !mobile.equals(mobilePhone)){
			return false;
		}
		return true;
	}
	/**
	 * 验证 验证码 不计时间
	 * @param code		验证码
	 * @param mobile	手机号
	 * @return
	 */
	public boolean CheckCodeRegardlessTime(String code,String mobile){
		System.out.println("========================verifyCode:"+verifyCode);
		if(null == code || "" == code || code.length() < 1 || null == mobile || "" == mobile){
			return false;
		}
		if(!code.equals(verifyCode) || !mobile.equals(mobilePhone)){
			return false;
		}
		return true;
	}
	
	/**
	 * 生成验证码
	 * @return
	 */
	private static String creatVerifyCode(String mobile){
		int max=9999;
        int min=1000;
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        MobileMsgSender.sendMsg(mobile, Constants.VERIFY_CODE_MSG.replace("VerifyCode", s+""));
        return String.valueOf(s);
	}
}
