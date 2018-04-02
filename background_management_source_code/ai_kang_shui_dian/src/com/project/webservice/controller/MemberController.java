package com.project.webservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.member.AddressBean;
import com.project.bean.member.CouponBean;
import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberReportedBean;
import com.project.bean.member.WithdrawalBean;
import com.project.bean.member.WorkTypeBean;
import com.project.bean.others.ExcelBean;
import com.project.bean.others.HtmlStyleBean;
import com.project.service.controller.MemberServiceC;
import com.project.service.controller.SystemServiceC;
import com.project.service.interfaces.SettingServiceI;
import com.project.utils.ExcelUtils;
import com.project.utils.TimeUtils;
import com.project.webservice.BaseController;

import com.project.page.PageBean;

/**
 * 用户管理
 * @author 方林
 *
 */
@RestController
@RequestMapping("/memberController.api")
public class MemberController extends BaseController{
	@Autowired
	MemberServiceC memberService;
	@Autowired
	SystemServiceC systemService;
	@Autowired
	SettingServiceI settingService;
	
	
	/**
	 * 工种列表
	 * @param workTypeBean
	 */
	@RequestMapping(params="getWorkTypeList")
	public void getWorkTypeList(WorkTypeBean workTypeBean,PageBean pageBean) {
		WriteObject(memberService.getWorkTypeList(workTypeBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 工种详情
	 * @param workTypeBean
	 */
	@RequestMapping(params="getWorkTypeDetail")
	public void getWorkTypeList(WorkTypeBean workTypeBean) {
		WriteObject(memberService.getWorkTypeDetail(workTypeBean));
	}
	/**
	 * 修改工种信息
	 * @param workTypeBean
	 */
	@RequestMapping(params="updateWorkType")
	public void updateWorkType(WorkTypeBean workTypeBean) {
		if(memberService.updateWorkType(workTypeBean)>0) {
			WriteObject("修改成功");
		}else {
			WriteError("修改失败");
		}
	}
	/**
	 * 添加工种信息
	 * @param workTypeBean
	 */
	@RequestMapping(params="insertWorkType")
	public void insertWorkType(WorkTypeBean workTypeBean) {
		if(memberService.insertWorkType(workTypeBean)>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 删除工种
	 * @param workTypeBean
	 */
	@RequestMapping(params="deleteWorkType")
	public void deleteWorkType(WorkTypeBean workTypeBean) {
		if(memberService.deleteWorkType(workTypeBean)>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 报备信息列表
	 * @param addressBean
	 */
	@RequestMapping(params="getReportedList")
	public void getReportedList(MemberReportedBean memberReportedBean,PageBean pageBean) {
		WriteObject(memberService.getReportedList(memberReportedBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 报备信息详情
	 * @param addressBean
	 */
	@RequestMapping(params="getReportedDetail")
	public void getReportedList(MemberReportedBean memberReportedBean) {
		WriteObject(memberService.getReportedDetail(memberReportedBean));
	}
	/**
	 * 修改报备信息
	 * @param addressBean
	 */
	@RequestMapping(params="updateReported")
	public void updateReported(MemberReportedBean memberReportedBean) {
		if(memberService.updateReported(memberReportedBean)>0) {
			WriteObject("修改成功");
		}else {
			WriteError("修改失败");
		}
	}
	/**
	 * 用户收货地址列表
	 * @param addressBean
	 */
	@RequestMapping(params="getMemberAddressList")
	public void getMemberAddressList(AddressBean addressBean) {
		WriteObject(memberService.getMemberAddressList(addressBean));
	}
	/**
	 * 用户提现列表
	 * @param withdrawalBean
	 * @param pageBean
	 */
	@RequestMapping(params="getWithdrawalList")
	public void getWithdrawalList(WithdrawalBean withdrawalBean,PageBean pageBean) {
		WriteObject(memberService.getWithdrawalList(withdrawalBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 导出提现申请excel
	 * @param withdrawalBean
	 */
	@RequestMapping(params="exportWithdrawalExcel")
	public void exportWithdrawalExcel(WithdrawalBean withdrawalBean) {
		String fileName="/excel/withdrawal/"+TimeUtils.getCurrentTime("yyyyMMddHHmmss")+".xls";
		List<ExcelBean> excelBeans=new ArrayList<ExcelBean>();
		excelBeans.add(new ExcelBean().setName("用户ID").setType("member_id"));
		excelBeans.add(new ExcelBean().setName("师傅账号").setType("member_account"));
		excelBeans.add(new ExcelBean().setName("师傅姓名").setType("member_real_name"));
		excelBeans.add(new ExcelBean().setName("提现金额").setType("withdrawal_price"));
		excelBeans.add(new ExcelBean().setName("用户余额").setType("member_extract_money"));
		excelBeans.add(new ExcelBean().setName("打款账号").setType("withdrawal_no"));
		excelBeans.add(new ExcelBean().setName("打款时间").setType("pay_time"));
		excelBeans.add(new ExcelBean().setName("流水号").setType("order_no"));
		excelBeans.add(new ExcelBean().setName("银行名称").setType("bank_name"));
		excelBeans.add(new ExcelBean().setName("开户行名称").setType("bank_open_name"));
		excelBeans.add(new ExcelBean().setName("开户人姓名").setType("bank_user_name"));
		excelBeans.add(new ExcelBean().setName("开户人手机号").setType("bank_open_mobile"));
		List<Object> withdrawalBeans=memberService.exportWithdrawalExcel(withdrawalBean);
		boolean is_success=ExcelUtils.exportExcel(fileName, excelBeans,withdrawalBeans);
		if(is_success){
			WriteObject(fileName);
		}else{
			WriteError("导出失败");
		}
	}
	/**
	 * 提现信息详情
	 * @param withdrawalBean
	 */
	@RequestMapping(params="getWithdrawalDetail")
	public void getWithdrawalDetail(WithdrawalBean withdrawalBean) {
		WriteObject(memberService.getWithdrawalDetail(withdrawalBean));
	}
	/**
	 * 修改提现信息
	 * @param withdrawalBean
	 * @throws Exception 
	 */
	@RequestMapping(params="updateWithdrawal")
	public void updateWithdrawal(WithdrawalBean withdrawalBean) throws Exception {
		int num=memberService.updateWithdrawal(withdrawalBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 用户列表
	 * @param memberBean
	 * @param pageBean
	 */
	@RequestMapping(params="getMemberList")
	public void getMemberList( MemberBean memberBean,PageBean pageBean){
		WriteObject(memberService.getMemberList(memberBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 用户详情
	 * @param memberBean
	 * @throws Exception
	 */
	@RequestMapping(params="getMemberDetail")
	public void getMemberDetail( MemberBean memberBean){
		WriteObject(memberService.getMemberDetail(memberBean));
	} 
	/**
	 * 删除用户
	 * @param memberBean
	 * @throws Exception
	 */
	@RequestMapping(params="deleteMember")
	public void deleteMember( MemberBean memberBean){
		int num=memberService.deleteMember(memberBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改用户信息
	 * @param memberBean
	 * @throws IOException 
	 */
	@RequestMapping(params="updateMember")
	public void updateMember(MemberBean memberBean ){
		HtmlStyleBean htmlStyleBean=settingService.getHtmlStyleDetail(new HtmlStyleBean().setStyle_type("common"));
		if(memberBean.getStar_worker_info_content()!=null) {
			if(memberBean.getStar_worker_info()==null||"".equals(memberBean.getStar_worker_info())) {
				String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE);
				String path="/html/member/"+fileName+".html";
				boolean result=writeHtml(path, memberBean.getStar_worker_info_content(),htmlStyleBean);
				if(result) {
					memberBean.setStar_worker_info(path);
				}
			}else {
				writeHtml(memberBean.getStar_worker_info(), memberBean.getStar_worker_info_content(),htmlStyleBean);
			}
		}
		if(memberBean.getMember_certificate_content()!=null) {
			if(memberBean.getMember_certificate()==null||"".equals(memberBean.getMember_certificate())) {
				String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE);
				String path="/html/member/"+fileName+".html";
				boolean result=writeHtml(path, memberBean.getMember_certificate_content(),null);
				if(result) {
					memberBean.setMember_certificate(path);
				}
			}else {
				writeHtml(memberBean.getMember_certificate(), memberBean.getMember_certificate_content(),null);
			}
		}
		if(memberBean.getProcess_show_content()!=null) {
			if(memberBean.getProcess_show_content()==null||"".equals(memberBean.getProcess_show())) {
				String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE);
				String path="/html/member/"+fileName+".html";
				boolean result=writeHtml(path, memberBean.getProcess_show_content(),null);
				if(result) {
					memberBean.setProcess_show(path);
				}
			}else {
				writeHtml(memberBean.getProcess_show(), memberBean.getProcess_show_content(),null);
			}
		}
		int num=memberService.updateMember(memberBean);
		if(num>0) {
			WriteMsg("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	
	/**
	 * 优惠券列表
	 * @param couponBean
	 * @param pageBean
	 */
	@RequestMapping(params="getCouponList")
	public void getCouponList( CouponBean couponBean,PageBean pageBean){
		WriteObject(memberService.getCouponList(couponBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 优惠券详情
	 * @param couponBean
	 * @throws Exception
	 */
	@RequestMapping(params="getCouponDetail")
	public void getCouponDetail( CouponBean couponBean){
		WriteObject(memberService.getCouponDetail(couponBean));
	} 
	/**
	 * 删除优惠券
	 * @param couponBean
	 * @throws Exception
	 */
	@RequestMapping(params="deleteCoupon")
	public void deleteCoupon( CouponBean couponBean){
		int num=memberService.deleteCoupon(couponBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改优惠券信息
	 * @param couponBean
	 * @throws IOException 
	 */
	@RequestMapping(params="updateCoupon")
	public void updateCoupon(CouponBean couponBean ){
		int num=memberService.updateCoupon(couponBean);
		if(num>0) {
			WriteMsg("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 添加优惠券信息
	 * @param couponBean
	 */
	@RequestMapping(params="insertCoupon")
	public void insertCoupon(CouponBean couponBean ){
		int num=memberService.insertCoupon(couponBean);
		if(num>0) {
			WriteMsg("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 单一用户分配优惠券
	 * @param couponBean
	 * @throws Exception 
	 */
	@RequestMapping(params="allocationCoupon")
	public void allocationCoupon(CouponBean couponBean,String member_ids) throws Exception {
		int num=memberService.allocationCoupon(couponBean,member_ids);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 所有用户分配优惠券
	 * @param couponBean
	 * @throws Exception 
	 */
	@RequestMapping(params="allocationAllMemberCoupon")
	public void allocationAllMemberCoupon(CouponBean couponBean) throws Exception{
		int num= memberService.allocationAllMemberCoupon(couponBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
}
