package com.tcw.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinyinComparator<T> implements Comparator<T> {
	@Override
    public int compare(T t1, T t2) {
		//必须重写实体<T>的toString方法
    	String o1 = t1.toString();
    	String o2 = t2.toString();
        for (int i = 0; i < o1.length() && i < o2.length(); i++) {
            int codePoint1 = o1.charAt(i);
            int codePoint2 = o2.charAt(i);
            if (Character.isSupplementaryCodePoint(codePoint1)
                    || Character.isSupplementaryCodePoint(codePoint2)) {
                i++;
            }
            if (codePoint1 != codePoint2) {
                if (Character.isSupplementaryCodePoint(codePoint1)
                        || Character.isSupplementaryCodePoint(codePoint2)) {
                    return codePoint1 - codePoint2;
                }
                String pinyin1 = pinyin((char) codePoint1);
                String pinyin2 = pinyin((char) codePoint2);
                if (pinyin1 != null && pinyin2 != null) { // 两个字符都是汉字
                    if (!pinyin1.equals(pinyin2)) {
                        return pinyin1.compareTo(pinyin2);
                    }
                } else {
                    return codePoint1 - codePoint2;
                }
            }
        }
        return o1.length() - o2.length();
    }
    /**
     * 字符的拼音，多音字就得到第一个拼音。不是汉字，就return null。
     */
    private static String pinyin(char c) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyins == null) {
            return null;
        }
        if(c=='重'||c=='长'){
        	return pinyins[1];//重庆,长安多音字
        }
        return pinyins[0];
    }
    /**
     * 获取传入字符的第一个字符的首字母并转换成大写字母
     * @param str	需要转换的字符串
     * @return 多音字就得到第一个拼音的首字母。不是汉字，就return null
     */
    public static String shouZiMu(String str) {
    	str = str.trim();
    	if(str.isEmpty()){
    		return null;
    	}
    	boolean e = isEnglish(str);
    	if(e){
    		return str.toUpperCase().substring(0, 1);
    	}
    	char c = str.charAt(0);
    	String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
    	if (pinyins == null) {
    		return null;
    	}
    	String result = pinyins[0];
    	if(c=='重'||c=='长'){
    		result =  pinyins[1];//重庆,长安多音字
    	}
    	return result.substring(0, 1).toUpperCase();
    }
    public static void main(String[] args) {
    	List<String> list = new ArrayList<String>();
    	list.add("宝马");
    	list.add("奥迪");
    	list.add("东风");
    	list.add("A");
    	list.add("a");
    	list.add("b");
    	list.add("C");
    	list.add("B");
    	sortChineseName(list);
    	for(int i=0;i<list.size();i++){
    		System.out.println(list.get(i));
    	}
		String s = " 奥迪";
		System.out.println(shouZiMu(s));
	}
    /**
     * @描述：按中文名排序
     * @参数：@param list
     * @返回类型：void
     * @创建人：yh
     * @创建时间：2014年6月12日
     **/
    public static <T> void sortChineseName(List<T> list) {
//    	Collections.sort(list, new SortChineseName<T>()); // 按中文名排序
    	Collections.sort(list, new PinyinComparator<T>()); // 按中文名排序
    }
    /**
     * 是否是英文
     * @param charaString
     * @return
     */
    public static boolean isEnglish(String charaString){ 
        return charaString.matches("^[a-zA-Z]*"); 
      } 
}