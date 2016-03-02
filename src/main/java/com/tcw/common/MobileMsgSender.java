package com.tcw.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * 神奇代码，请勿改动   
 * Company：团车网
 * Description：发送短信工具类
 **/
public class MobileMsgSender {
	private static Logger log = Logger.getLogger(MobileMsgSender.class);
	
	public static void main(String[] args) {
		MobileMsgSender.sendMsg("", "测试短信");
	}
	
	/**
	 * 发送短信
	 * @param mobile	电话号码
	 * @param content	发送内容
	 */
	public static void sendMsg(String mobile,String content){
		StringBuffer params = new StringBuffer(150);
		params.append("pszMobis=").append(mobile)
			  .append("&pszMsg=").append(content)
			  .append("&sendSystem=3");
		String postResponse=postURL(params.toString(),"http://interface.tuanche.net/dream/sendSMS");
		log.info("短信内容："+content+",手机号码："+mobile);
		log.info("返回值："+postResponse);
	}

	private static String postURL(String commString, String address) {
		String rec_string = "";
		URL url = null;
		HttpURLConnection urlConn = null;
		try {
			/*得到url地址的URL类*/
			url = new URL(address);
			/*获得打开需要发送的url连接*/
			urlConn = (HttpURLConnection) url.openConnection();
			/*设置连接超时时间*/
			urlConn.setConnectTimeout(30000);
			/*设置读取响应超时时间*/
			urlConn.setReadTimeout(30000);
			/*设置post发送方式*/
			urlConn.setRequestMethod("POST");
			/*发送commString*/
			urlConn.setDoOutput(true);
			urlConn.setDoInput(true);
			OutputStream out = urlConn.getOutputStream();
			out.write(commString.getBytes());
			out.flush();
			out.close();
			/*发送完毕 获取返回流，解析流数据*/
			BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GBK"));
			StringBuffer sb = new StringBuffer();
			int ch;
			while ((ch = rd.read()) > -1) {
				sb.append((char) ch);
			}
			rec_string = sb.toString().trim();
			/*解析完毕关闭输入流*/
			rd.close();
		} catch (Exception e) {
			/*异常处理*/
			rec_string = "-107";
			log.error(e.getMessage());
		} finally {
			if (urlConn != null) {
				/*关闭URL连接*/
				urlConn.disconnect();
			}
		}
		/*返回响应内容*/
		return rec_string;
	}
	
}
