package com.tcw.common;

import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p></p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on 2015/6/1 0001.
 * @version 1.0
 * @since 1.0
 */
public class MD5Util {

    private static final String MD5_LOCAL = "MD5";

    /**
     * md5加密
     * @param input
     * @param key
     * @return
     */
    public static String securityCon (String input,final String key) {
        try {
            input = input+key;
            // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
            MessageDigest messageDigest = MessageDigest.getInstance(MD5_LOCAL);
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes();
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return Hex.encodeHexString(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 后服务请求加密方法
     * @param userId        用户id
     * @param orderId      订单id
     * @param price          订单金额
     * @param flag            特殊字符串 sold_2015
     * @return
     */
    public static String generateSoldSign(Long userId,Long orderId,Integer price,String flag) {
        try {
            MessageDigest md5= MessageDigest.getInstance(MD5_LOCAL);
            BASE64Encoder base64en = new BASE64Encoder();
            String data = userId + "_" + orderId + "_" +(price==null?"":(price+"_")) + flag;
            System.out.println(data);
            String signStr = base64en.encode(md5.digest(data.getBytes("utf-8")));
            return signStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @description 验签
     * @param userId
     * @param orderId
     * @param price
     * @param verify
     * @return
     */
    private static boolean verifySign(Long userId,String orderId,Integer price,String verify) {
        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            String data = userId + "_" + orderId + "_"  +(price==null?"":(price+"_")) + "sold_2015";
            String signStr = base64en.encode(md5.digest(data.getBytes("utf-8")));
            if(signStr.equals(verify)){
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {

        String bb = MD5Util.generateSoldSign(2662853L, 4162058L, 16, "sold_2015");
        System.out.println(bb);
//        {getType=10, price=16, getProductId = 4162058, userId = 2662853, very=qIFlapNWYlilLJ+uDJ0Fzw==}
//        Long userId,String orderId,Integer price,String verify
        Boolean aa = MD5Util.verifySign(2662853L, 4162058 + "", 16, "qIFlapNWYlilLJ+uDJ0Fzw==");
        System.out.println(aa);
    }
//    public static void main(String[] args) {
//        String aa = MD5Util.securityCon("1111","tuanche1234abcd1234");
//        System.out.println(aa);
//
//    }
}
