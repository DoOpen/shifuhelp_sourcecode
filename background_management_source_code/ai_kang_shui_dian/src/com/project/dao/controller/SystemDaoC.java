package com.project.dao.controller;

import java.util.List;

import com.project.bean.others.PingSettingBean;
import com.project.bean.system.SystemAccountBean;
import com.project.bean.system.SystemModuleBean;
import com.project.bean.system.SystemRoleBean;
import com.project.page.PageBean;

public interface SystemDaoC {
	/**
	 * 商家token验证
	 * @return
	 */
	public SystemAccountBean validationToken(SystemAccountBean systemAccountBean);
	/**
	 * 获取ping++配置信息
	 * @return
	 */
	public PingSettingBean getPingSetting();
	/**
	 * 根据权限id获取模块列表
	 * @return
	 */
	public List<SystemModuleBean> getSystemModuleListByRole(SystemRoleBean systemRoleBean);
	/**
	 * 获取系统模块列表
	 * @param systemModuleBean
	 * @return
	 */
	public List<SystemModuleBean> getSystemModuleList(SystemModuleBean systemModuleBean);
	/**
	 * 系统模块详情
	 * @param systemModuleBean
	 * @return
	 */
	public SystemModuleBean getSystemModuleDetail(SystemModuleBean systemModuleBean);
	/**
	 * 修改系统模块
	 * @param systemModuleBean
	 * @return
	 */
	public int updateSystemModule(SystemModuleBean systemModuleBean);
	/**
	 * 删除系统模块
	 * @param systemModuleBean
	 * @return
	 */
	public int deleteSystemModule(SystemModuleBean systemModuleBean);
	/**
	 * 添加系统模块
	 * @param systemModuleBean
	 * @return
	 */
	public int insertSystemModule(SystemModuleBean systemModuleBean);
	/**
	 * 角色列表
	 * @param pageBean
	 * @return
	 */
	public List<SystemRoleBean> getSystemRoleList(PageBean pageBean);
	/**
	 * 获取角色信息
	 * @param systemRoleBean
	 * @return
	 */
	public SystemRoleBean getSystemRoleDetail(SystemRoleBean systemRoleBean);
	/**
	 * 修改角色信息
	 * @param systemRoleBean
	 * @return
	 */
	public int updateSystemRole(SystemRoleBean systemRoleBean);
	/**
	 * 删除角色信息
	 * @param systemRoleBean
	 * @return
	 */
	public int deleteSystemRole(SystemRoleBean systemRoleBean);
	/**
	 * 添加角色信息
	 * @param systemRoleBean
	 * @return
	 */
	public int insertSystemRole(SystemRoleBean systemRoleBean);
	/**
	 * 获取系统账号详情
	 * @param systemAccountBean
	 * @return
	 */
	public SystemAccountBean getSystemAccountDetail(SystemAccountBean systemAccountBean);
	/**
	 * 系统账号登录
	 * @param systemAccountBean
	 * @return
	 */
	public SystemAccountBean systemAccountLogin(SystemAccountBean systemAccountBean);
	/**
	 * 登录后更新账号token
	 * @param setSystem_login_id
	 * @return
	 */
	public int updateToken(SystemAccountBean systemAccountBean);
	/**
	 * 修改系统账号信息
	 * @param systemAccountBean
	 * @return
	 */
	public int updateSystemAccount(SystemAccountBean systemAccountBean);
	/**
	 * 系统账号列表
	 * @param systemAccountBean
	 * @param pageBean
	 * @return
	 */
	public List<SystemAccountBean> getSystemAccountList(SystemAccountBean systemAccountBean, PageBean pageBean);
	/**
	 * 添加系统账号
	 * @param SystemAccountBean
	 * @return
	 */
	public int insertSystemAccount(SystemAccountBean systemAccountBean);
	/**
	 * 删除系统账号
	 * @param systemAccountBean
	 * @return
	 */
	public int deleteSystemAccount(SystemAccountBean systemAccountBean);
}
