package com.project.webservice.interfaces;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.others.AdviceBean;
import com.project.bean.others.BannerBean;
import com.project.bean.others.CodeBean;
import com.project.bean.others.HtmlBean;
import com.project.bean.others.PercentBean;
import com.project.bean.others.PlatformBean;
import com.project.bean.others.VerificationBean;
import com.project.bean.system.AppVersionBean;
import com.project.bean.system.SettingBean;
import com.project.bean.wx.WXBean;
import com.project.bean.wx.WXSettingBean;
import com.project.others.NotToken;
import com.project.page.PageBean;
import com.project.service.interfaces.MemberServiceI;
import com.project.service.interfaces.SettingServiceI;
import com.project.utils.EncodeUtils;
import com.project.utils.NumberUtils;
import com.project.utils.TimeUtils;
import com.project.utils.VerificationCodeUtils;
import com.project.utils.WXUtils;
import com.project.webservice.BaseController;

@RestController
@RequestMapping("/settingInterfaces.api")
public class SettingInterfaces extends BaseController{
	@Autowired
	SettingServiceI settingService; 
	@Autowired
	MemberServiceI memberService;
	
	/**
	 * 获取app版本
	 * @param appVersionBean
	 */
	@NotToken
	@RequestMapping(params = "getAppVersionDetail")
	public void getAppVersionDetail(AppVersionBean appVersionBean) {
		WriteObject(settingService.getAppVersionDetail(appVersionBean));
	}
	/**
	 * 获取平台各种配置信息
	 * @param request
	 * @param response
	 */
	@NotToken
	@RequestMapping(params = "getSystemPlatform")
	public void getSystemPlatform(PlatformBean platformBean) {
		WriteObject(settingService.getSystemPlatform(platformBean));
	}
	
	/**
	 * 获取html页面内容
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@NotToken
	@RequestMapping(params = "getHtmlContent")
	public void getHtmlContent(String url) throws Exception {
		String desc=readHtml(url);
		int start=desc.indexOf("<content>");
		int end=desc.indexOf("</content>");
		if(start>0&&end>0){
			desc=desc.substring(start+9,end);
		}
		WriteOnlyMsg(desc);
	}
	/**
	 * 获取图文详情
	 * @param title
	 */
	@NotToken
	@RequestMapping(params="getHtmlDetail",method=RequestMethod.POST)
	public void getHtmlDetail(HtmlBean htmlBean) {
		WriteObject(settingService.getHtmlDetail(htmlBean));
	}
	/**
	 * 提交建议或者投诉
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "insertAdvice", method = RequestMethod.POST)
	public void insertAdvice(AdviceBean adviceBean,String advice_imgs) throws Exception {
		int num = settingService.insertAdvice(adviceBean,advice_imgs);
		if (num >= 0) {
			WriteObject("感谢您的宝贵意见!");
		} else {
			WriteError("哎呀!太遗憾了,我们没能成功收到您的宝贵意见!");
		}
	}
	/**
	 * 获得微信分享权限
	 * 
	 * @param percentBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getWxAccessToken", method = RequestMethod.POST)
	public void getWxAccessToken(PercentBean percentBean)
			throws Exception {
		WXSettingBean wxSetingBean = settingService.getWXSetting(new WXSettingBean().setWeixin_type("3"));
		WriteObject(WXUtils.getAccess_token(wxSetingBean.getWeixin_appid(), wxSetingBean.getWeixin_secret()));
	}
	/**
	 * 获得微信分享权限
	 * 
	 * @param percentBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getWxAutho", method = RequestMethod.POST)
	public void getWxAutho(PercentBean percentBean, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WXSettingBean wxSetingBean = settingService.getWXSetting(new WXSettingBean().setWeixin_type("2"));
		String url = request.getParameter("url");
		String access_token = WXUtils.getAccess_token(wxSetingBean.getWeixin_appid(), wxSetingBean.getWeixin_secret());
		String jsapi_ticket = WXUtils.getJsapi(access_token);
		String nonceStr = NumberUtils.createRandom(false, 16);
		long timestamp = Long.parseLong(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
		WXBean wxBean = new WXBean();
		wxBean.setJsapi_ticket(jsapi_ticket);
		wxBean.setAppId(wxSetingBean.getWeixin_appid());
		wxBean.setNonceStr(nonceStr);
		wxBean.setTimestamp(timestamp + "");
		String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
				+ url;
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("jsapi_ticket", jsapi_ticket);
		// map.put("timestamp", timestamp);
		// map.put("url", url);
		wxBean.setSignature(EncodeUtils.sha1(sign));
		WriteObject(wxBean);
	}
	/**
	 * 获得所有广告列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getBannerList", method = RequestMethod.POST)
	public void getBannerList(BannerBean bannerBean,PageBean pageBean) throws Exception {
		WriteObject(settingService.getBannerList(bannerBean));
	}
	
	
	/**
	 * 上传多张图片/文件
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "uploadImgs")
	public void uploadImgs() throws Exception {
		Map<String, Object> map = getJsonWithImgs();
		if ("0".equals(map.get("result"))) {
			WriteError("上传失败");
		} else {
			WriteObject(map.get("file"));
		}
	}
	/**
	 * 上传单张图片/文件
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "uploadImg")
	public void uploadImg() throws Exception {
		Map<String, Object> result=uploadFileForm();
		if(result.get("state").equals("0")) {
			WriteError("上传失败");
		}else {
			WriteObject(result.get("file"));
		}
	}
	/**
	 * 系统配置信息
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getSystemSettingDetail")
	public void getSystemSettingDetail(SettingBean settingBean) throws Exception {
		WriteObject(settingService.getSystemSettingDetail(settingBean));
	}
	/**
	 * 发送验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "sendCode", method = RequestMethod.POST)
	public void sendCode(CodeBean codeBean) throws Exception {
		CodeBean codeBean2=settingService.getCodeBeanByMobile(codeBean.setCreate_time(TimeUtils.getCurrentTime()));
		if (codeBean2 != null) {
			if(TimeUtils.getMinCompareDate("yyyy-MM-dd HH:mm:ss",TimeUtils.getCurrentTime(),codeBean2.getCreate_time())<2){
				WriteError("2分钟内不可重复发送!");
				return;
			}
			settingService.deleteCode(codeBean2);
		}
		String code = NumberUtils.createRandom(true, 6);
		VerificationBean verificationBean = settingService.getVerificationSetting(new VerificationBean());
		CodeBean codeBean1 = VerificationCodeUtils.sendCodeWangxintong(verificationBean,
				codeBean.setCode(code).setCode_desc(verificationBean.getVerification_content().replace("code", code)));
		if (codeBean1 != null) {
			int num = settingService.insertCode(codeBean1);
			if (num > 0) {
				WriteMsg(code);
			} else {
				WriteError("发送失败");
			}
		} else {
			WriteError("发送失败");
		}
	}
}
