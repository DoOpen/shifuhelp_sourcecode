package com.project.dao.controller;

import java.util.List;

import com.project.bean.others.CityBean;
import com.project.bean.others.PingSettingBean;
import com.project.bean.system.AppVersionBean;
import com.project.bean.system.SettingBean;
import com.project.bean.others.BannerBean;
import com.project.bean.others.HtmlBean;
import com.project.bean.others.HtmlStyleBean;
import com.project.bean.others.MsgTypeBean;
import com.project.page.PageBean;

public interface SettingDaoC {
	/**
	 * 添加系统html
	 * @param htmlBean
	 * @return
	 */
	public int insertHtml(HtmlBean htmlBean);
	/**
	 * 删除系统html
	 * @param htmlBean
	 * @return
	 */
	public int deleteHtml(HtmlBean htmlBean);
	/**
	 * 修改系统html
	 * @param htmlBean
	 * @return
	 */
	public int updateHtml(HtmlBean htmlBean);
	/**
	 * 系统html详情
	 * @param htmlBean
	 * @return
	 */
	public HtmlBean getHtmlDetail(HtmlBean htmlBean);
	/**
	 * 系统html列表
	 * @param htmlBean
	 * @param pageBean
	 * @return
	 */
	public List<HtmlBean> getHtmlList(HtmlBean htmlBean, PageBean pageBean);
	/**
	 * 系统广告列表
	 * @param bannerBean
	 * @param pageBean
	 * @return
	 */
	public List<BannerBean> getBannerList(BannerBean bannerBean, PageBean pageBean);
	/**
	 * 系统广告详情
	 * @param bannerBean
	 * @return
	 */
	public BannerBean getBannerDetail(BannerBean bannerBean);
	/**
	 * 修改系统广告
	 * @param bannerBean
	 * @return
	 */
	public int updateBanner(BannerBean bannerBean);
	/**
	 * 删除系统广告
	 * @param bannerBean
	 * @return
	 */
	public int deleteBanner(BannerBean bannerBean);
	/**
	 * 添加系统广告
	 * @param bannerBean
	 * @return
	 */
	public int insertBanner(BannerBean bannerBean);
	/**
	 * 修改城市信息
	 * @param cityBean3
	 * @return
	 */
	public int updateCity(CityBean cityBean3);
	/**
	 * 城市列表
	 * @param cityBean
	 * @param pageBean
	 * @return
	 */
	public List<CityBean> getCityList(CityBean cityBean, PageBean pageBean);
	/**
	 * 添加城市
	 * @param cityBean
	 * @return
	 */
	public int insertCity(CityBean cityBean);
	/**
	 * 删除城市
	 * @param cityBean
	 * @return
	 */
	public int deleteCity(CityBean cityBean);
	/**
	 * 城市详情
	 * @return
	 */
	public CityBean getCityDetail(CityBean cityBean);
	/**
	 * 缓存中获取城市列表
	 * @return
	 */
	public List<CityBean> getCityListCache();
	/**
	 * 获取系统预设消息类型
	 * @param setMsg_type
	 * @return
	 */
	public MsgTypeBean getMsgType(MsgTypeBean setMsg_type);
	/**
	 * 获取ping++配置
	 * @return
	 */
	public PingSettingBean getPingSetting();
	/**
	 * 系统设置列表
	 * @param settingBean
	 * @param pageBean
	 * @return
	 */
	public List<SettingBean> getSettingList(SettingBean settingBean, PageBean pageBean);
	/**
	 * 修改系统设置
	 * @param settingBean
	 * @return
	 */
	public int updateSetting(SettingBean settingBean);
	/**
	 * html模板详情
	 * @param htmlStyleBean
	 * @return
	 */
	public HtmlStyleBean getHtmlStyleDetail(HtmlStyleBean htmlStyleBean);
	/**
	 * 获取app版本
	 * @param appVersionBean
	 * @return
	 */
	public AppVersionBean getAppVersionDetail(AppVersionBean appVersionBean);
	/**
	 * 修改app版本
	 * @param appVersionBean
	 * @return
	 */
	public int updateAppVersion(AppVersionBean appVersionBean);
}
