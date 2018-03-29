/**
 * 
 */
package com.project.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.project.bean.others.CodeBean;
import com.project.bean.others.VerificationBean;


/**
 * @author sjb
 * 
 */
public class VerificationCodeUtils {


	public static CodeBean sendCode(VerificationBean verificationBean,CodeBean codeBean) throws Exception {
		// 产生随机验证码
		try {
			String time = TimeUtils.getCurrentTime();
			Date date = TimeUtils.getDateFromTime("yyyy-MM-dd HH:mm:ss",time);
			codeBean.setCreate_time(time);
			codeBean.setEffective_time(TimeUtils.getTimeMinuteAfter("yyyy-MM-dd HH:mm:ss",date,verificationBean.getEffective_time()));
			String Url = verificationBean.getVerification_url();
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod(Url);
			post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			NameValuePair userid = new NameValuePair("userid", "");
			NameValuePair account = new NameValuePair(verificationBean.getKey_account(), verificationBean.getVerification_account());
			NameValuePair password = new NameValuePair(verificationBean.getKey_password(), verificationBean.getVerification_password());
			NameValuePair mobile = new NameValuePair(verificationBean.getKey_mobile(), codeBean.getMobile());
			NameValuePair content = new NameValuePair(verificationBean.getKey_content(), codeBean.getCode_desc());
			NameValuePair id = new NameValuePair(verificationBean.getKey_userid(), verificationBean.getVerification_userid());
			NameValuePair sendTime = new NameValuePair("sendTime", codeBean.getCreate_time());
			NameValuePair extno = new NameValuePair("extno", "");
			post.setRequestBody(new NameValuePair[] { userid, account, password, mobile, content,id, sendTime, extno });
			int statu = client.executeMethod(post);
			System.out.println("statu=" + statu);
			String str = post.getResponseBodyAsString();
			System.out.println(str);
			if (statu == 200) {
				return codeBean;
			} else {
				return null;
			}

			// return codeBean;
			/*
			 * // 将字符转化为XML Document doc = DocumentHelper.parseText(str); //
			 * 获取根节点 Element rootElt = doc.getRootElement(); // 获取根节点下的子节点的值
			 * String returnstatus = rootElt.elementText("returnstatus").trim();
			 * String message = rootElt.elementText("message").trim(); String
			 * remainpoint = rootElt.elementText("remainpoint").trim(); String
			 * taskID = rootElt.elementText("taskID").trim(); String
			 * successCounts = rootElt.elementText("successCounts").trim();
			 * 
			 * System.out.println("返回状态为：" + returnstatus);
			 * System.out.println("返回信息提示：" + message);
			 * System.out.println("返回余额：" + remainpoint);
			 * System.out.println("返回任务批次：" + taskID);
			 * System.out.println("返回成功条数：" + successCounts);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 助通
	 * @param verificationBean
	 * @param codeBean
	 * @return
	 * @throws Exception
	 */
	public static CodeBean sendCodeZhutong(VerificationBean verificationBean,CodeBean codeBean) throws Exception {
		// 产生随机验证码
		try {
			String format = "yyyy-MM-dd HH:mm:ss";
			String time = TimeUtils.getCurrentTime(format);
			Date date = TimeUtils.getDateFromTime(format,time);

			codeBean.setCreate_time(time);
			codeBean.setEffective_time(TimeUtils.getTimeMinuteAfter(format,date,verificationBean.getEffective_time()));
			
			String url=verificationBean.getVerification_url();
			String username=verificationBean.getVerification_account();  //账号
			String password=verificationBean.getVerification_password();  //密码
			String tkey=TimeUtils.getCurrentTime("yyyyMMddHHmmss");
			String mobile=codeBean.getMobile();  //发送的手机号
			String content=codeBean.getCode_desc();   //内容
			
			//String time="2016-09-06 17:48:22";//定时信息所需参数时间格式为yyyy-MM-dd HH:mm:ss
			String xh="";
			try {
				content=URLEncoder.encode(content, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				System.out.print(e.getMessage());
			}
			String param="url="+url
					+"&"+verificationBean.getKey_account()+"="+username
					+"&"+verificationBean.getKey_password()+"="+EncodeUtils.MD5Encode(EncodeUtils.MD5Encode(password)+tkey)
					+"&tkey="+tkey
					+"&"+verificationBean.getKey_mobile()+"="+mobile
					+"&"+verificationBean.getKey_content()+"="+content
					+"&xh="+xh
					+"&productid=676767";
			//String param="url="+url+"&username="+username+"&password="+MD5Gen.getMD5(MD5Gen.getMD5(password)+tkey)+"&tkey="+tkey+"&time="+time+"&mobile="+mobile+"&content="+content+"&xh="+xh;//定时信息url输出
			String ret=HttpUtils.sendPost(url,param);//定时信息只可为post方式提交
			System.out.println("ret:"+ret);
			System.out.println(param);
			
			if(ret.contains("1,")){
				return codeBean;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 网信通
	 * @param verificationBean
	 * @param codeBean
	 * @return
	 * @throws Exception
	 */
	public static CodeBean sendCodeWangxintong(VerificationBean verificationBean,CodeBean codeBean) throws Exception {
		// 产生随机验证码
				try {
					String format = "yyyy-MM-dd HH:mm:ss";
					String time = TimeUtils.getCurrentTime(format);
					Date date = TimeUtils.getDateFromTime(format, time);
					codeBean.setCreate_time(time);
					codeBean.setEffective_time(TimeUtils.getTimeMinuteAfter(format, date,verificationBean.getEffective_time()));
					String Url = verificationBean.getVerification_url();
					String tkey = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					String content = codeBean.getCode_desc();
					try {
						content = URLEncoder.encode(content, "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					String param = "url=" + Url + "&username=" + verificationBean.getVerification_account() 
							+ "&password=" + EncodeUtils.MD5Encode(EncodeUtils.MD5Encode(verificationBean.getVerification_password())+tkey)
							+ "&mobile=" + codeBean.getMobile()
							+ "&content=" + content + "&tkey=" + tkey + "&xh" + "";
					String ret = HttpUtils.sendPost(Url, param);
					System.out.println(ret);
					String result = "";
					if (ret.split(",")[0].equals("-1")) {
						result = "用户名或者密码不正确或用户禁用或者是管理账户";
					} else if (ret.split(",")[0].equals("1")) {
						result = "发送短信成功";
					} else if (ret.split(",")[0].equals("0")) {
						result = "发送短信失败";
					}
					System.out.println(result);
					return codeBean;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
	}

}
