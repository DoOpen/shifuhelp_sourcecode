package com.project.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密工具类
 * 
 * @author 方林
 *
 */
public class EncodeUtils {
	/**
	 * md5加密
	 * @param str
	 * @param charset
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 * @throws Exception
	 */
	public static String MD5Encode(String str){
		StringBuffer sb = new StringBuffer(32);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
		    md.update(str.getBytes("utf-8"));
		    byte[] result = md.digest();
		    for (int i = 0; i < result.length; i++) {
		        int val = result[i] & 0xff;
		        if (val <= 0xf) {
		            sb.append("0");
		        }
		        sb.append(Integer.toHexString(val));
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
	    return sb.toString().toLowerCase();
	}
	/**
	 * base64加密
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	public static String base64Encode(String content, String charset) throws Exception {
		char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
				'4', '5', '6', '7', '8', '9', '+', '/' };
		StringBuffer sb = new StringBuffer();
		byte[] data = content.getBytes(charset);
		int len = data.length;
		int i = 0;
		int b1, b2, b3;
		while (i < len) {
			b1 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
				sb.append("==");
				break;
			}
			b2 = data[i++] & 0xff;
			if (i == len) {
				sb.append(base64EncodeChars[b1 >>> 2]);
				sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
				sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
				sb.append("=");
				break;
			}
			b3 = data[i++] & 0xff;
			sb.append(base64EncodeChars[b1 >>> 2]);
			sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
			sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
			sb.append(base64EncodeChars[b3 & 0x3f]);
		}
		return sb.toString();
	}
	/**
	 * sha1(哈希值校验)
	 * @param data
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha1(String data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.update(data.getBytes());
		StringBuffer buf = new StringBuffer();
		byte[] bits = md.digest();
		for (int i = 0; i < bits.length; i++) {

			int a = bits[i];

			if (a < 0)
				a += 256;

			if (a < 16)
				buf.append("0");

			buf.append(Integer.toHexString(a));

		}
		return buf.toString();
	}
}
