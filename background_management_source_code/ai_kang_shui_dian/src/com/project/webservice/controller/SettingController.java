package com.project.webservice.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.others.CityBean;
import com.project.bean.others.HtmlStyleBean;
import com.project.bean.system.AppVersionBean;
import com.project.bean.system.SettingBean;
import com.project.others.NotToken;
import com.project.bean.others.BannerBean;
import com.project.bean.others.HtmlBean;
import com.project.page.PageBean;
import com.project.service.controller.SettingServiceC;
import com.project.utils.TimeUtils;
import com.project.webservice.BaseController;

@RestController
@RequestMapping("/settingController.api")
public class SettingController extends BaseController{
	@Autowired
	SettingServiceC settingService;
	
	/**
	 * 获取app版本
	 * @param appVersionBean
	 */
	@RequestMapping(params = "getAppVersionDetail")
	public void getAppVersionDetail(AppVersionBean appVersionBean) {
		WriteObject(settingService.getAppVersionDetail(appVersionBean));
	}
	/**
	 * 修改app版本
	 * @param appVersionBean
	 */
	@RequestMapping(params = "updateAppVersion")
	public void updateAppVersion(AppVersionBean appVersionBean) {
		if(settingService.updateAppVersion(appVersionBean)>0) {
			WriteObject("修改成功");
		}else {
			WriteError("修改失败");
		}
	}
	/**
	 * 添加系统html
	 * @param htmlBean
	 */
	@RequestMapping(params="insertHtml")
	public void insertHtml(HtmlBean htmlBean) {
		HtmlBean htmlBean2=settingService.getHtmlDetail(htmlBean);
		if(htmlBean2!=null) {
			WriteError("名称重复!");
			return;
		}
		if(htmlBean.getHtml_url_content()!=null) {
			if(htmlBean.getHtml_url()==null||"".equals(htmlBean.getHtml_url())) {
				String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE);
				String path="/html/others/"+fileName+".html";
				boolean result=writeHtml(path, htmlBean.getHtml_url_content(),settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common")));
				if(result) {
					htmlBean.setHtml_url(path);
				}
			}else {
				writeHtml(htmlBean.getHtml_url(), htmlBean.getHtml_url_content(),settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common")));
			}
		}
		int num=settingService.insertHtml(htmlBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 删除系统html
	 * @param htmlBean
	 */
	@RequestMapping(params="deleteHtml")
	public void deleteHtml(HtmlBean htmlBean) {
		int num=settingService.deleteHtml(htmlBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改系统html
	 * @param htmlBean
	 */
	@RequestMapping(params="updateHtml")
	public void updateHtml(HtmlBean htmlBean) {
		if(htmlBean.getHtml_url_content()!=null) {
			if(htmlBean.getHtml_url()==null||"".equals(htmlBean.getHtml_url())) {
				String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE);
				String path="/html/others/"+fileName+".html";
				boolean result=writeHtml(path, htmlBean.getHtml_url_content(),settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common")));
				if(result) {
					htmlBean.setHtml_url(path);
				}
			}else {
				writeHtml(htmlBean.getHtml_url(), htmlBean.getHtml_url_content(),settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common")));
			}
		}
		int num=settingService.updateHtml(htmlBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 系统html详情
	 * @param htmlBean
	 */
	@RequestMapping(params="getHtmlDetail")
	public void getHtmlDetail(HtmlBean htmlBean) {
		WriteObject(settingService.getHtmlDetail(htmlBean));
	}
	/**
	 * 系统html列表
	 * @param htmlBean
	 * @param pageBean
	 */
	@RequestMapping(params="getHtmlList")
	public void getHtmlList(HtmlBean htmlBean,PageBean pageBean) {
		WriteObject(settingService.getHtmlList(htmlBean,pageBean),pageBean.getTotal());
	}
	
	/**
	 * 添加系统广告
	 * @param bannerBean
	 */
	@RequestMapping(params="insertBanner")
	public void insertBanner(BannerBean bannerBean) {
		if("common".equals(bannerBean.getBanner_type())&&bannerBean.getBanner_url_content()!=null) {
			if(bannerBean.getBanner_url()==null||"".equals(bannerBean.getBanner_url())) {
				String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE);
				String path="/html/banner/"+fileName+".html";
				boolean result=writeHtml(path, bannerBean.getBanner_url_content(),settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common")));
				if(result) {
					bannerBean.setBanner_url(path);
				}
			}else {
				writeHtml(bannerBean.getBanner_url(), bannerBean.getBanner_url_content(),settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common")));
			}
		}
		int num=settingService.insertBanner(bannerBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 删除系统广告
	 * @param bannerBean
	 */
	@RequestMapping(params="deleteBanner")
	public void deleteBanner(BannerBean bannerBean) {
		int num=settingService.deleteBanner(bannerBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改系统广告
	 * @param bannerBean
	 */
	@RequestMapping(params="updateBanner")
	public void updateBanner(BannerBean bannerBean) {
		if("common".equals(bannerBean.getBanner_type())&&bannerBean.getBanner_url_content()!=null) {
			if(bannerBean.getBanner_url()==null||"".equals(bannerBean.getBanner_url())) {
				String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE);
				String path="/html/banner/"+fileName+".html";
				boolean result=writeHtml(path, bannerBean.getBanner_url_content(),settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common")));
				if(result) {
					bannerBean.setBanner_url(path);
				}
			}else {
				writeHtml(bannerBean.getBanner_url(), bannerBean.getBanner_url_content(),settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common")));
			}
		}
		int num=settingService.updateBanner(bannerBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 系统广告详情
	 * @param bannerBean
	 */
	@RequestMapping(params="getBannerDetail")
	public void getBannerDetail(BannerBean bannerBean) {
		WriteObject(settingService.getBannerDetail(bannerBean));
	}
	/**
	 * 系统广告列表
	 * @param bannerBean
	 * @param pageBean
	 */
	@RequestMapping(params="getBannerList")
	public void getBannerList(BannerBean bannerBean,PageBean pageBean) {
		WriteObject(settingService.getBannerList(bannerBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 添加城市
	 * @param cityBean
	 */
	@RequestMapping(params="insertCity")
	public void insertCity(CityBean cityBean) {
		int num=settingService.insertCity(cityBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 删除城市
	 * @param cityBean
	 */
	@RequestMapping(params="deleteCity")
	public void deleteCity(CityBean cityBean) {
		int num=settingService.deleteCity(cityBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改城市
	 * @param cityBean
	 */
	@RequestMapping(params="updateCity")
	public void updateCity(CityBean cityBean) {
		int num=settingService.updateCity(cityBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 城市详情
	 * @param cityBean
	 */
	@RequestMapping(params="getCityDetail")
	public void getCityDetail(CityBean cityBean) {
		WriteObject(settingService.getCityDetail(cityBean));
	}
	/**
	 * 城市列表
	 * @param cityBean
	 * @param level
	 * @param pageBean
	 */
	@RequestMapping(params="getCityList")
	public void getCityList(CityBean  cityBean,Integer level,PageBean pageBean) {
		WriteObject(settingService.getCityList(cityBean,level,pageBean),pageBean.getTotal());
	}
	/**
	 * 缓存中获取城市列表
	 * @param cityBean
	 * @param level
	 * @param pageBean
	 */
	@RequestMapping(params="getCityListCache")
	public void getCityListCache() {
		WriteObject(settingService.getCityListCache());
	}
	/**
	 * 系统设置列表
	 * @param cityBean
	 * @param level
	 * @param pageBean
	 */
	@RequestMapping(params="getSettingList")
	public void getSettingList(SettingBean settingBean,PageBean pageBean) {
		WriteObject(settingService.getSettingList(settingBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 修改系统设置
	 * @param cityBean
	 * @param level
	 * @param pageBean
	 */
	@RequestMapping(params="updateSetting")
	public void updateSetting(SettingBean settingBean) {
		if(settingService.updateSetting(settingBean)>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	@NotToken
	@RequestMapping(params="getHtmlDesc")
	public void getHtmlDesc(String url){
		try {
			String desc=readHtml(url);
			int start=desc.indexOf("<content>");
			int end=desc.lastIndexOf("</content>");
			if(start>=0&&end>=0) {
				desc=desc.substring(start+9, end);
			}
			WriteMsg(desc);
		}catch (Exception e) {
			WriteError("读取内容出现异常");
		}
	}
	@RequestMapping(params = "setHtmlDesc")
	public void setHtmlDesc(String url,String desc){
		if(writeHtml(url,desc,settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("phone_show")))){
			WriteMsg("保存成功");
		}else{
			WriteError("保存失败");
		}
	}
	/**
	 * 上传图片
	 * @param request
	 * @param 
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "uploadImgs")
	public void uploadFiels() throws Exception {
		Map<String, Object> map = getJsonWithImgs();
		if ("0".equals(map.get("result"))) {
			WriteError("上传失败");
		} else {
			WriteObject(map.get("file"));
		}
	}
	@NotToken
	@RequestMapping(params = "uploadImg")
	public void uploadFiel() throws Exception {
		Map<String, Object> result=uploadFileForm();
		if(result.get("state").equals("0")) {
			WriteError("上传失败");
		}else {
			WriteObject(result.get("file"));
		}
	}
}
