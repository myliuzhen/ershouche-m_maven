package com.tcw.common;


import com.tuanche.commons.util.Resources;

/**
 * <p>走ng参数的校验</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on 2015/6/27
 * @version 1.0
 * @since 1.0
 */
public class NgixUrlUtil {


    /**
     * 获取根据key生成的一个后缀
     * @return
     */
    public static   String getUrlPostfix () {

        String key = Resources.getString("NGIX.KEY");
        if(key == null || "".equals(key)){
            return "";
            //throw  new BusinessException("on get ngix url postFix,the key is null plese check the key:NGIX.KEY in application.xml");
        }
        Long time = System.currentTimeMillis();
        String sign = MD5Util.securityCon(time+"",key);
        return  "time="+time+"&sign="+sign;
    }

}
