package com.project.dao.interfaces;

import java.util.List;

import com.project.bean.others.PingSettingBean;
import com.project.bean.others.AdviceBean;
import com.project.bean.others.AdviceImgBean;
import com.project.bean.others.BannerBean;
import com.project.bean.others.CodeBean;
import com.project.bean.others.HtmlBean;
import com.project.bean.others.HtmlStyleBean;
import com.project.bean.others.MsgTypeBean;
import com.project.bean.others.VerificationBean;
import com.project.bean.system.AppVersionBean;
import com.project.bean.system.SettingBean;
import com.project.bean.wx.WXSettingBean;


public interface SettingDaoI {

	/**
	 * 获得消息配置
	 * @param msgTypeBean
	 * @return
	 */
	public MsgTypeBean getMsgType(MsgTypeBean msgTypeBean);
	
	/**
	 * 图文配置详情
	 * @param htmlBean
	 * @return
	 */
	public HtmlStyleBean getHtmlStyleDetail(HtmlStyleBean htmlStyleBean);
	
	/**
	 * 提交建议或者投诉
	 * @param adviceBean
	 * @return
	 */
	public int insertAdvice(AdviceBean adviceBean);
	
	/**
	 * 提交建议或者投诉的图片
	 * @param adviceBean
	 * @return
	 */
	public int insertAdviceImg(AdviceImgBean adviceImgBean);
	/**
	 * ping++配置
	 * @param pingSettingBean
	 * @return
	 */
	public PingSettingBean getPingSetting();
	
	/**
	 * 微信配置
	 * @param wxSetingBean
	 * @return
	 */
	public WXSettingBean getWXSetting(WXSettingBean wxSetingBean);
	
	/**
	 * 获得所有广告列表
	 * @return
	 */
	public List<BannerBean> getBannerList(BannerBean bannerBean);
	
	/**
	 * 根据验证码获得验证信息
	 * @return
	 */
	public CodeBean getCodeBeanByMobile(CodeBean codeBean);
	
	/**
	 * 短信平台设置
	 * @param verificationBean
	 * @return
	 */
	public VerificationBean getVerificationSetting(VerificationBean verificationBean);
	
	/**
	 * 添加新的验证码
	 * @param codeBean
	 * @return
	 */
	public int insertCode(CodeBean codeBean);
	/**
	 * 修改验证码
	 * @param codeBean
	 * @return
	 */
	public int deleteCode(CodeBean codeBean);
	/**
	 * 验证手机号验证码是否匹配
	 * @param codeBean
	 * @return
	 */
	public CodeBean getCodeBeanByMobileAndCode(CodeBean codeBean);
	
	/**
	 * 删除验证码
	 * @param codeBean
	 * @return
	 */
	public int deleteCodeByMobileAndCode(CodeBean codeBean);
	/**
	 * 获取系统配置详情
	 * @param settingBean
	 * @return
	 */
	public SettingBean getSystemSettingDetail(SettingBean settingBean);
	/**
	 * 图文详情
	 * @param htmlBean
	 * @return
	 */
	public HtmlBean getHtmlDetail(HtmlBean htmlBean);
	/**
	 * 获取app版本
	 * @param appVersionBean
	 * @return
	 */
	public AppVersionBean getAppVersionDetail(AppVersionBean appVersionBean);
}
