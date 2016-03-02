package com.tcw.common;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DesUtil {
	//秘钥
//	private static String DES_KEY = "111";
	private static String DES_KEY = null;
	private static final String DES = "DES";
	private static Cipher cipher = null;

	static {
		// Cipher对象实际完成加密操作
		try {
			cipher = Cipher.getInstance(DES);
			DES_KEY = AuthorizationUtil.getInstance().getString("DES_KSY");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	public static String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()),
					DES_KEY.getBytes()),"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return null;
	}

	

	public static String encrypt(String data) {
		try {
			return byte2hex(encrypt(data.getBytes(),
					DES_KEY.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}

		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}

		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}

		return b2;
	}

	public static void main(String[] args) {
		String url = "v=1201&brandName=上海大众&username=测试李朋&tel=18512345678&applyId=11111111111111";
		url = DesUtil.encrypt(url);
		System.err.println(url);
		url = DesUtil.decrypt("0E5403D09488660BC0FA8F7ABDBD59EA448ADE2F819A1C3C665466978686B0AE3B066B0888330F1915F3724D1B8D0E19AF15E465BCE6ACF9CD7F442DBB153902389EB14134179CB378C01EFB60C8499660D60A4ED0F9AC67C1F8519927A0009D");
		System.out.println(url);
	}
}
