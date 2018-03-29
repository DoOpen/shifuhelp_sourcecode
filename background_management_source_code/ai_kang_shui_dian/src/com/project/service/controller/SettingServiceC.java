package com.project.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.others.CityBean;
import com.project.bean.others.BannerBean;
import com.project.bean.others.HtmlBean;
import com.project.bean.others.HtmlStyleBean;
import com.project.bean.system.AppVersionBean;
import com.project.bean.system.SettingBean;
import com.project.dao.controller.SettingDaoC;
import com.project.page.PageBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class SettingServiceC{
	
	@Autowired
	SettingDaoC settingDao;
	
	/**
	 * 添加系统html
	 * @param htmlBean
	 * @return
	 */
	public int insertHtml(HtmlBean htmlBean) {
		return settingDao.insertHtml(htmlBean);
	}
	/**
	 * 删除系统html
	 * @param htmlBean
	 * @return
	 */
	public int deleteHtml(HtmlBean htmlBean) {
		return settingDao.deleteHtml(htmlBean);
	}
	/**
	 * 修改系统html
	 * @param htmlBean
	 * @return
	 */
	public int updateHtml(HtmlBean htmlBean) {
		return settingDao.updateHtml(htmlBean);
	}
	/**
	 * 系统html详情
	 * @param htmlBean
	 * @return
	 */
	public HtmlBean getHtmlDetail(HtmlBean htmlBean) {
		return settingDao.getHtmlDetail(htmlBean);
	}
	/**
	 * 系统html列表
	 * @param htmlBean
	 * @param pageBean
	 * @return
	 */
	public List<HtmlBean> getHtmlList(HtmlBean htmlBean, PageBean pageBean) {
		return settingDao.getHtmlList(htmlBean,pageBean);
	}
	/**
	 * 系统广告列表
	 * @param bannerBean
	 * @param pageBean
	 * @return
	 */
	public List<BannerBean> getBannerList(BannerBean bannerBean, PageBean pageBean) {
		return settingDao.getBannerList(bannerBean,pageBean);
	}
	/**
	 * 系统广告详情
	 * @param bannerBean
	 * @return
	 */
	public BannerBean getBannerDetail(BannerBean bannerBean) {
		return settingDao.getBannerDetail(bannerBean);
	}
	/**
	 * 修改系统广告
	 * @param bannerBean
	 * @return
	 */
	public int updateBanner(BannerBean bannerBean) {
		return settingDao.updateBanner(bannerBean);
	}
	/**
	 * 删除系统广告
	 * @param bannerBean
	 * @return
	 */
	public int deleteBanner(BannerBean bannerBean) {
		return settingDao.deleteBanner(bannerBean);
	}
	/**
	 * 添加系统广告
	 * @param bannerBean
	 * @return
	 */
	public int insertBanner(BannerBean bannerBean) {
		return settingDao.insertBanner(bannerBean);
	}
	/**
	 * 自定义多级城市列表
	 * @param cityBean
	 * @param level
	 * @param pageBean
	 * @return
	 */
	public List<CityBean> getCityList(CityBean cityBean, Integer level, PageBean pageBean) {
		level=level==null?1:level;
		List<CityBean> cityBeans=settingDao.getCityList(cityBean, pageBean);
		if(level<2) {
			return cityBeans;
		}else {
			return this.getCityListLevel(cityBeans,2,level,pageBean);
		}
	}
	/**
	 * 递归获取指定层级的城市列表
	 * @param goodsClassBeans
	 * @param now
	 * @param level
	 * @param pageBean
	 * @return
	 */
	private List<CityBean> getCityListLevel(List<CityBean> cityBeans, int now, Integer level,PageBean pageBean) {
		if(cityBeans!=null){
			for(CityBean cityBean:cityBeans) {
				List<CityBean> cityBeans2=settingDao.getCityList(new CityBean().setParent_id(cityBean.getId()), pageBean);
				if(now<level&&cityBean!=null) {
					cityBeans2=this.getCityListLevel(cityBeans2, now+1, level,pageBean);
				}
				cityBean.setCityBeans(cityBeans2);
			}
		}
		return cityBeans;
	}
	/**
	 * 添加城市
	 * @param cityBean
	 * @return
	 */
	public int insertCity(CityBean cityBean) {
		return settingDao.insertCity(cityBean);
	}
	/**
	 * 删除城市
	 * @param cityBean
	 * @return
	 */
	public int deleteCity(CityBean cityBean) {
		return settingDao.deleteCity(cityBean);
	}
	/**
	 * 修改城市
	 * @param cityBean
	 * @return
	 */
	public int updateCity(CityBean cityBean) {
		return settingDao.updateCity(cityBean);
	}
	/**
	 * 城市详情
	 * @param cityBean
	 * @return
	 */
	public CityBean getCityDetail(CityBean cityBean) {
		return settingDao.getCityDetail(cityBean);
	}
	/**
	 * 缓存中获取城市列表
	 * @return
	 */
	@Cacheable(value="cityBeans")
	public List<CityBean> getCityListCache() {
		return settingDao.getCityListCache();
	}
	/**
	 * 系统设置列表
	 * @param settingBean
	 * @param pageBean
	 * @return
	 */
	public List<SettingBean> getSettingList(SettingBean settingBean, PageBean pageBean) {
		return settingDao.getSettingList(settingBean,pageBean);
	}
	/**
	 * 修改系统设置
	 * @param settingBean
	 * @return
	 */
	public int updateSetting(SettingBean settingBean) {
		return settingDao.updateSetting(settingBean);
	}
	/**
	 * 获取html模板
	 * @param htmlStyleBean
	 * @return
	 */
	public HtmlStyleBean getHtmlStyleDetail(HtmlStyleBean htmlStyleBean) {
		return settingDao.getHtmlStyleDetail(htmlStyleBean);
	}
	/**
	 * 获取app版本
	 * @param appVersionBean
	 * @return
	 */
	public AppVersionBean getAppVersionDetail(AppVersionBean appVersionBean) {
		return settingDao.getAppVersionDetail(appVersionBean);
	}
	/**
	 * 修改app版本
	 * @param appVersionBean
	 * @return
	 */
	public int updateAppVersion(AppVersionBean appVersionBean) {
		return settingDao.updateAppVersion(appVersionBean);
	}
}

