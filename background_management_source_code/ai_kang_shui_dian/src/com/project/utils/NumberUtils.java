package com.project.utils;

import java.math.BigDecimal;

public class NumberUtils {
	
	/**
	 * 判断对象是否为null或""
	 * @param object
	 * @return
	 */
	public static boolean isNull(Object object) {
		if(object==null||"".equals(object)) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 产生随机数
	 * 
	 * @param numberFlag
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);
		return retStr;
	}
	
	public static Double Double(String value){
		return Double.valueOf(value==null||"".equals(value)?"0":value);
	}
	
	public static int Integer(String value){
		try{
			return Integer.valueOf(value==null||"".equals(value)?"0":value);
		}catch(Exception e){
			return -1;
		}
	}
	
	public static long Long(String value){
		try{
			return Long.valueOf(value==null||"".equals(value)?"0":value);
		}catch(Exception e){
			return -1;
		}
	}
	
	public static float Float(String value){
		return Float.valueOf(value==null||"".equals(value)?"0":value);
	}
	/**
	 * 保留2位小数
	 * @param value
	 * @param count
	 * @return
	 */
	public static float KeepDecimal(double value,int count){
		BigDecimal   bd   =   new   BigDecimal(value);   
		bd=bd.setScale(count,BigDecimal.ROUND_HALF_UP);   
		return bd.floatValue();
	}
	
	/**
	 * 保留2位小数
	 * @param value
	 * @param count
	 * @return
	 */
	public static double KeepDecimal(String value,int count){
		BigDecimal   bd   =   new   BigDecimal(value);   
		bd=bd.setScale(count,BigDecimal.ROUND_HALF_UP);   
		return bd.doubleValue();
	}
	/**
	 * 保留2位小数
	 * @param value
	 * @param count
	 * @return
	 */
	public static int KeepDecimal(double value){
		BigDecimal   bd   =   new   BigDecimal(value);   
		bd=bd.setScale(0,BigDecimal.ROUND_HALF_UP);   
		return bd.intValue();
	}
	

	

	
	/**
	 * 保留2位小数
	 * @param value
	 * @param count
	 * @return
	 */
	public static int KeepDecimal(String value){
		BigDecimal   bd   =   new   BigDecimal(value);   
		bd=bd.setScale(0,BigDecimal.ROUND_HALF_UP);   
		return bd.intValue();
	}
}
