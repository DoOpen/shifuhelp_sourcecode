package com.project.service.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.others.PingSettingBean;
import com.project.bean.others.AdviceBean;
import com.project.bean.others.AdviceImgBean;
import com.project.bean.others.BannerBean;
import com.project.bean.others.CodeBean;
import com.project.bean.others.HtmlBean;
import com.project.bean.others.HtmlStyleBean;
import com.project.bean.others.MsgTypeBean;
import com.project.bean.others.PercentBean;
import com.project.bean.others.PlatformBean;
import com.project.bean.others.VerificationBean;
import com.project.bean.system.AppVersionBean;
import com.project.bean.system.SettingBean;
import com.project.bean.wx.WXSettingBean;
import com.project.dao.interfaces.SettingDaoI;

@Service
@Transactional(rollbackFor = Exception.class)
public class SettingServiceI {
	
	@Autowired
	SettingDaoI settingDao;
	
	/**
	 * 获得消息配置
	 * @param msgTypeBean
	 * @return
	 */
	public MsgTypeBean getMsgType(MsgTypeBean msgTypeBean){
		return settingDao.getMsgType(msgTypeBean);
	}
	
	/**
	 * html模板
	 * @param htmlBean
	 * @return
	 */
	public HtmlStyleBean getHtmlStyleDetail(HtmlStyleBean htmlStyleBean){
		return settingDao.getHtmlStyleDetail(htmlStyleBean);
	}
	
	/**
	 * 提交建议或者投诉
	 * @param adviceBean
	 * @return
	 * @throws Exception 
	 */
	public int insertAdvice(AdviceBean adviceBean,String imgs) throws Exception{
		String[] files=imgs!=null?(imgs.length()>0?imgs.split(","):null):null;
		int num=settingDao.insertAdvice(adviceBean);
		if(num>0){
			if(files!=null){
				for (int i = 0; i < files.length; i++) {
					int h=settingDao.insertAdviceImg(new AdviceImgBean()
							.setAdvice_id(adviceBean.getAdvice_id())
							.setAdvice_img(files[i]));
					if(h<=0){
						throw new Exception("建议图片入库失败");
					}
				}		
			}
		}
		return num;
	}
	
	/**
	 * ping++配置
	 * @param pingSettingBean
	 * @return
	 */
	public PingSettingBean getPingSetting(){
		return settingDao.getPingSetting();
	}
	
	/**
	 * 平台比例设置
	 * @param wxSetingBean
	 * @return
	 */
	public PercentBean getPercentDetail(PercentBean percentBean){
		return settingDao.getPercentDetail(percentBean);
	}
	
	/**
	 * 微信配置
	 * @param wxSetingBean
	 * @return
	 */
	public WXSettingBean getWXSetting(WXSettingBean wxSetingBean){
		return settingDao.getWXSetting(wxSetingBean);
	}
	
	
	/**
	 * 平台基本信息
	 */
	public PlatformBean getSystemPlatform(PlatformBean platformBean){
		return settingDao.getSystemPlatform(platformBean);
	}
	
	/**
	 * 获得所有广告列表
	 * @return
	 */
	public List<BannerBean> getBannerList(BannerBean bannerBean){
		return settingDao.getBannerList( bannerBean);
	}
	
	/**
	 * 根据验证码获得验证信息
	 * @return
	 */
	public CodeBean getCodeBeanByMobile(CodeBean codeBean){
		return settingDao.getCodeBeanByMobile(codeBean);
	}
	
	/**
	 * 短信平台设置
	 * @param verificationBean
	 * @return
	 */
	public VerificationBean getVerificationSetting(VerificationBean verificationBean){
		return settingDao.getVerificationSetting(verificationBean);
	}
	
	/**
	 * 添加新的验证码
	 * @param codeBean
	 * @return
	 */
	public int insertCode(CodeBean codeBean){
		return settingDao.insertCode(codeBean);
	}
	
	/**
	 * 修改验证码
	 * @param codeBean
	 * @return
	 */
	public int deleteCode(CodeBean codeBean){
		return settingDao.deleteCode(codeBean);
	}
	
	/**
	 * 验证手机号验证码是否匹配
	 * @param codeBean
	 * @return
	 */
	public CodeBean getCodeBeanByMobileAndCode(CodeBean codeBean){
		return settingDao.getCodeBeanByMobileAndCode(codeBean);
	}
	
	/**
	 * 删除验证码
	 * @param codeBean
	 * @return
	 */
	public int deleteCodeByMobileAndCode(CodeBean codeBean){
		return settingDao.deleteCodeByMobileAndCode(codeBean);
	}
	/**
	 * 获取系统配置详情
	 * @param setSetting_name
	 * @return
	 */
	public SettingBean getSystemSettingDetail(SettingBean othersBean) {
		return settingDao.getSystemSettingDetail(othersBean);
	}
	/**
	 * 图文详情
	 * @param htmlBean
	 * @return
	 */
	public HtmlBean getHtmlDetail(HtmlBean htmlBean) {
		return settingDao.getHtmlDetail(htmlBean);
	}
	/**
	 * 获取app版本
	 * @param appVersionBean
	 * @return
	 */
	public AppVersionBean getAppVersionDetail(AppVersionBean appVersionBean) {
		return settingDao.getAppVersionDetail(appVersionBean);
	}
}
