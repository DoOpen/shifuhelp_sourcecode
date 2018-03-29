package com.project.webservice.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.system.SystemAccountBean;
import com.project.bean.system.SystemModuleBean;
import com.project.bean.system.SystemRoleBean;
import com.project.others.NotToken;
import com.project.page.PageBean;
import com.project.service.controller.SystemServiceC;
import com.project.utils.EncodeUtils;
import com.project.webservice.BaseController;

/**
 * 系统权限控制器
 * @author 方林
 *
 */
@RestController
@RequestMapping("/systemController.api")
public class SystemController extends BaseController{
	@Autowired
	SystemServiceC systemService;
	
	/**
	 * 通过账号role_id获取对应的模块列表
	 * @throws Exception
	 */
	@RequestMapping(params="getSystemModuleListBySystemAccount",method=RequestMethod.POST)
	public void getSystemModuleListBySystemAccount(SystemAccountBean systemAccountBean) throws Exception {
		WriteObject(systemService.getSystemModuleListBySystemAccount(systemAccountBean));
	}
	/**
	 * 系统账号登录
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params="systemAccountLogin",method=RequestMethod.POST)
	public void systemAccountLogin(SystemAccountBean systemAccountBean){
		systemAccountBean.setSystem_password(EncodeUtils.MD5Encode(systemAccountBean.getSystem_password()));
		SystemAccountBean systemAccountBean2=systemService.systemAccountLogin(systemAccountBean);
		if(systemAccountBean2==null) {
			WriteError("账号或密码错误");
		}else if("1".equals(systemAccountBean2.getIs_disable())) {
			WriteError("账号已冻结,请联系客服");
		}else {
			String newToken=UUID.randomUUID().toString();
			int num=systemService.updateToken(systemAccountBean2.setSystem_token(newToken).setSystem_password(""));
			if(num>0) {
				WriteObject(systemAccountBean2);
			}else {
				WriteError("token更新失败");
			}
		}
	}
	/**
	 * 添加系统账号
	 * @param systemAccountBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "insertSystemAccount", method = RequestMethod.POST)
	public void insertSystemAccount(SystemAccountBean systemAccountBean) throws Exception {	
		int num=systemService.insertSystemAccount(systemAccountBean.setSystem_password(EncodeUtils.MD5Encode(systemAccountBean.getSystem_password())));
		if(num>0){
			WriteMsg("添加成功");
		}else{
			WriteError("添加失败");
		}
	}
	/**
	 * 修改系统账号
	 * @param systemAccountBean
	 */
	@RequestMapping(params="updateSystemAccount")
	public void updateSystemAccount(SystemAccountBean systemAccountBean) {
		SystemAccountBean systemAccountBean2=systemService.getSystemAccountDetail(systemAccountBean);
		if(systemAccountBean.getSystem_password()!=null&&!"".equals(systemAccountBean.getSystem_password())) {
			if(EncodeUtils.MD5Encode(systemAccountBean.getSystem_password()).equals(systemAccountBean2.getSystem_password())) {
				WriteError("不可以与旧密码相同奥!");
				return;
			}
			if(!EncodeUtils.MD5Encode(systemAccountBean.getSystem_old_password()).equals(systemAccountBean2.getSystem_password())) {
				WriteError("原密码输入错误");
				return;
			}else {
				systemAccountBean.setSystem_password(EncodeUtils.MD5Encode(systemAccountBean.getSystem_password()));
			}
		}
		int num=systemService.updateSystemAccount(systemAccountBean);
		if(num>0) {
			WriteObject(systemAccountBean.setSystem_password(null).setSystem_old_password(null));
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 获取系统模块列表
	 * @param pageBean
	 * @param systemModuleBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getSystemModuleList")
	public void getSystemModuleList(PageBean pageBean,SystemModuleBean systemModuleBean){
		WriteObject(systemService.getSystemModuleList(systemModuleBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 添加系统模块
	 * @param systemModuleBean
	 * @throws IOException 
	 */
	@RequestMapping(params="insertSystemModule")
	public void insertSystemModule(SystemModuleBean systemModuleBean){
		int num=systemService.insertSystemModule(systemModuleBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 删除系统模块
	 * @param systemModuleBean
	 * @throws IOException 
	 */
	@RequestMapping(params="deleteSystemModule")
	public void deleteSystemModule(SystemModuleBean systemModuleBean){
		int num=systemService.deleteSystemModule(systemModuleBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改系统模块
	 * @param systemModuleBean
	 * @throws IOException 
	 */
	@RequestMapping(params="updateSystemModule")
	public void updateSystemModule(SystemModuleBean systemModuleBean){
		int num=systemService.updateSystemModule(systemModuleBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 单个系统模块详情
	 * @param systemModuleBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getSystemModuleDetail")
	public void getSystemModuleDetail(SystemModuleBean systemModuleBean){
		WriteObject(systemService.getSystemModuleDetail(systemModuleBean));
	}
	/**
	 * 添加角色
	 * @param systemRoleBean
	 * @throws IOException 
	 */
	@RequestMapping(params="insertSystemRole")
	public void insertSystemRole(SystemRoleBean systemRoleBean){
		int num=systemService.insertSystemRole(systemRoleBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 删除角色
	 * @param systemRoleBean
	 * @throws IOException 
	 */
	@RequestMapping(params="deleteSystemRole")
	public void deleteSystemRole(SystemRoleBean systemRoleBean ){
		int num=systemService.deleteSystemRole(systemRoleBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 删除系统账号
	 * @param systemAccountBean
	 */
	@RequestMapping(params="deleteSystemAccount")
	public void deleteSystemAccount(SystemAccountBean systemAccountBean) {
		if(systemService.deleteSystemAccount(systemAccountBean)>0) {
			WriteObject("删除成功");
		}else {
			WriteError("删除失败");
		}
	}
	/**
	 * 修改角色权限
	 * @param systemRoleBean
	 * @throws IOException 
	 */
	@RequestMapping(params="updateSystemRole")
	public void updateSystemRole(SystemRoleBean systemRoleBean){
		int num=systemService.updateSystemRole(systemRoleBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 角色详情
	 * @param systemRoleBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getSystemRoleDetail")
	public void getSystemRoleDetail(SystemRoleBean systemRoleBean){
		WriteObject(systemService.getSystemRoleDetail(systemRoleBean));
	}
	/**
	 * 角色列表
	 * @param pageBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getSystemRoleList")
	public void getSystemRoleList(PageBean pageBean){
		WriteObject(systemService.getSystemRoleList(pageBean),pageBean.getTotal());
	}
	/**
	 * 获取所有的模块列表
	 */
	@RequestMapping(params="getAllModule")
	public void getAllModule(){
		WriteObject(systemService.getAllModule());
	}
	/**
	 * 系统账号列表
	 * @param systemAccountBean
	 * @param pageBean
	 */
	@RequestMapping(params="getSystemAccountList")
	public void getSystemAccountList(SystemAccountBean systemAccountBean,PageBean pageBean) {
		WriteObject(systemService.getSystemAccountList(systemAccountBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 系统账号详情
	 * @param systemAccountBean
	 */
	@RequestMapping(params="getSystemAccountDetail")
	public void getSystemAccountDetail(SystemAccountBean systemAccountBean) {
		WriteObject(systemService.getSystemAccountDetail(systemAccountBean).setSystem_password(null));
	}
}
