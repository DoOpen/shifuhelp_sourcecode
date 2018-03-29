package com.project.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.others.PingSettingBean;
import com.project.bean.system.SystemAccountBean;
import com.project.bean.system.SystemModuleBean;
import com.project.bean.system.SystemRoleBean;
import com.project.dao.controller.SystemDaoC;
import com.project.page.PageBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class SystemServiceC {
	@Autowired
	SystemDaoC systemDao;
	/**
	 * 商家token验证
	 * @return
	 */
	public boolean validationToken(SystemAccountBean systemAccountBean) {
		if(systemDao.validationToken(systemAccountBean)==null) {
			return false;
		}else {
			return true;
		}
	}
	/**
	 * 获取ping++配置信息
	 */
	public PingSettingBean getPingSetting() {
		return systemDao.getPingSetting();
	}
	/**
	 * 获取商家权限对应的模块列表
	 * @return
	 */
	public List<SystemModuleBean> getSystemModuleListBySystemAccount(SystemAccountBean systemAccountBean) {
		systemAccountBean=systemDao.getSystemAccountDetail(systemAccountBean.setAccount_id(systemAccountBean.getAccount_login_id()));
		SystemRoleBean systemRoleBean=systemDao.getSystemRoleDetail(new SystemRoleBean().setRole_id(systemAccountBean.getRole_id()));
		List<SystemModuleBean> systemModuleBeans=systemDao.getSystemModuleListByRole(systemRoleBean);
		return systemModuleBeans;
	}
	/**
	 * 系统账号登录
	 * @return
	 */
	public SystemAccountBean systemAccountLogin(SystemAccountBean systemAccountBean) {
		return systemDao.systemAccountLogin(systemAccountBean);
	}
	/**
	 * 更新token
	 * @param setMerchants_token
	 * @return
	 */
	public int updateToken(SystemAccountBean systemAccountBean) {
		return systemDao.updateToken(systemAccountBean.setAccount_login_id(systemAccountBean.getAccount_id()));
	}
	/**
	 * 获取系统模块列表
	 * @param systemModuleBean
	 * @param pageBean
	 * @return
	 */
	public List<SystemModuleBean> getSystemModuleList(SystemModuleBean systemModuleBean,PageBean pageBean) {
		return systemDao.getSystemModuleList(systemModuleBean);
	}
	/**
	 * 添加系统模块
	 * @param systemModuleBean
	 * @return
	 */
	public int insertSystemModule(SystemModuleBean systemModuleBean) {
		return systemDao.insertSystemModule(systemModuleBean);
	}
	/**
	 * 删除系统模块
	 * @param systemModuleBean
	 * @return
	 */
	public int deleteSystemModule(SystemModuleBean systemModuleBean) {
		return systemDao.deleteSystemModule(systemModuleBean);
	}
	/**
	 * 修改系统模块
	 * @param systemModuleBean
	 * @return
	 */
	public int updateSystemModule(SystemModuleBean systemModuleBean) {
		return systemDao.updateSystemModule(systemModuleBean);
	}
	/**
	 * 系统模块详情
	 * @param systemModuleBean
	 * @return
	 */
	public SystemModuleBean getSystemModuleDetail(SystemModuleBean systemModuleBean) {
		return systemDao.getSystemModuleDetail(systemModuleBean);
	}
	/**
	 * 角色列表
	 * @param pageBean
	 * @return
	 */
	public List<SystemRoleBean> getSystemRoleList(PageBean pageBean) {
		return systemDao.getSystemRoleList(pageBean);
	}
	/**
	 * 角色详情
	 * @param systemRoleBean
	 * @return
	 */
	public SystemRoleBean getSystemRoleDetail(SystemRoleBean systemRoleBean) {
		return systemDao.getSystemRoleDetail(systemRoleBean);
	}
	/**
	 * 修改角色信息
	 * @param systemRoleBean
	 * @return
	 */
	public int updateSystemRole(SystemRoleBean systemRoleBean) {
		return systemDao.updateSystemRole(systemRoleBean);
	}
	/**
	 * 删除橘色
	 * @param systemRoleBean
	 * @return
	 */
	public int deleteSystemRole(SystemRoleBean systemRoleBean) {
		return systemDao.deleteSystemRole(systemRoleBean);
	}
	/**
	 * 添加角色信息
	 * @param systemRoleBean
	 * @return
	 */
	public int insertSystemRole(SystemRoleBean systemRoleBean) {
		return systemDao.insertSystemRole(systemRoleBean);
	}
	/**
	 * 获取所有的模块列表
	 * @return
	 */
	public List<SystemModuleBean> getAllModule() {
		List<SystemModuleBean> systemModuleBeans=systemDao.getSystemModuleList(new SystemModuleBean().setParent_id(-1));
		for(SystemModuleBean systemModuleBean:systemModuleBeans) {
			List<SystemModuleBean> systemModuleBeans2=systemDao.getSystemModuleList(new SystemModuleBean().setParent_id(systemModuleBean.getModule_id()));
			systemModuleBean.setSystemModuleBeans(systemModuleBeans2);
		}
		return systemModuleBeans;
	}
	/**
	 * 系统账号详情
	 * @param systemAccountBean
	 * @return
	 */
	public SystemAccountBean getSystemAccountDetail(SystemAccountBean systemAccountBean) {
		return systemDao.getSystemAccountDetail(systemAccountBean);
	}
	/**
	 * 更改系统账号信息
	 * @param systemAccountBean
	 * @return
	 */
	public int updateSystemAccount(SystemAccountBean systemAccountBean) {
		return systemDao.updateSystemAccount(systemAccountBean);
	}
	/**
	 * 系统账号列表
	 * @param systemAccountBean
	 * @param pageBean
	 * @return
	 */
	public List<SystemAccountBean> getSystemAccountList(SystemAccountBean systemAccountBean, PageBean pageBean) {
		return systemDao.getSystemAccountList(systemAccountBean,pageBean);
	}
	/**
	 * 添加系统账号
	 * @param systemAccountBean
	 * @return
	 * @throws Exception 
	 */
	public int insertSystemAccount(SystemAccountBean systemAccountBean) throws Exception {
		SystemAccountBean systemAccountBean2=systemDao.getSystemAccountDetail(systemAccountBean);
		if(systemAccountBean2!=null) {
			throw new Exception("账号已存在");
		}
		return systemDao.insertSystemAccount(systemAccountBean);
	}
	/**
	 * 删除系统账号
	 * @param systemAccountBean
	 * @return
	 */
	public int deleteSystemAccount(SystemAccountBean systemAccountBean) {
		return systemDao.deleteSystemAccount(systemAccountBean);
	}
}
