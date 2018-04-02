package com.project.service.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.member.CouponBean;
import com.project.bean.member.AddressBean;
import com.project.bean.member.MemberBean;
import com.project.bean.member.MemberReportedBean;
import com.project.bean.member.WithdrawalBean;
import com.project.bean.member.WorkTypeBean;
import com.project.bean.others.LocationBean;
import com.project.dao.controller.MemberDaoC;
import com.project.page.PageBean;
import com.project.utils.GaoDeUtils;
import com.project.utils.NumberUtils;
import com.project.utils.TimeUtils;

@Service
@Transactional(rollbackFor = Exception.class)
public class MemberServiceC {
	
	@Autowired
	MemberDaoC memberDao;
	/**
	 * 用户列表
	 * @param memberBean
	 * @param pageBean
	 * @return
	 */
	public List<MemberBean> getMemberList(MemberBean memberBean, PageBean pageBean) {
		return memberDao.getMemberList(memberBean, pageBean);
	}
	/**
	 * 用户详情
	 * @param memberBean
	 * @return
	 */
	public MemberBean getMemberDetail(MemberBean memberBean) {
		MemberBean memberBean2=memberDao.getMemberDetail(memberBean);
		if(memberBean2!=null) {
			List<AddressBean> addressBeans=memberDao.getMemberAddressList(new AddressBean().setMember_id(memberBean2.getMember_id()));
			memberBean2.setAddressBeans(addressBeans);
		}
		return memberBean2;
	}
	/**
	 * 删除用户
	 * @param memberBean
	 * @return
	 */
	public int deleteMember(MemberBean memberBean) {
		return memberDao.deleteMember(memberBean);
	}
	/**
	 * 修改用户信息
	 * @param memberBean
	 * @return
	 */
	public int updateMember(MemberBean memberBean) {
		MemberBean memberBean2=memberDao.getMemberDetail(memberBean);
		if(memberBean.getMember_state().equals("1")&&!memberBean2.getMember_state().equals("1")) {
			memberBean.setAudit_pass_time(TimeUtils.getCurrentTime());
		}
		LocationBean locationBean=GaoDeUtils.addressToLocation(memberBean.getMember_service_province()+memberBean.getMember_service_city()+memberBean.getMember_service_district()+memberBean.getMember_service_detail());
		if(locationBean!=null) {
			memberBean.setMember_service_longitude(locationBean.getLongitude())
			.setMember_service_latitude(locationBean.getLatitude());
		}
		return memberDao.updateMember(memberBean);
	}
	/**
	 * 优惠券列表
	 * @param couponBean
	 * @param pageBean
	 * @return
	 */
	public List<CouponBean> getCouponList(CouponBean couponBean, PageBean pageBean) {
		return memberDao.getCouponList(couponBean,pageBean);
	}
	/**
	 * 优惠券详情
	 * @param couponBean
	 * @return
	 */
	public CouponBean getCouponDetail(CouponBean couponBean) {
		return memberDao.getCouponDetail(couponBean);
	}
	/**
	 * 删除优惠券
	 * @param couponBean
	 * @return
	 */
	public int deleteCoupon(CouponBean couponBean) {
		return memberDao.deleteCoupon(couponBean);
	}
	/**
	 * 修改优惠券
	 * @param couponBean
	 * @return
	 */
	public int updateCoupon(CouponBean couponBean) {
		return memberDao.updateCoupon(couponBean);
	}
	/**
	 * 添加优惠券
	 * @param couponBean
	 * @return
	 */
	public int insertCoupon(CouponBean couponBean) {
		return memberDao.insertCoupon(couponBean);
	}
	/**
	 * 单一用户分配优惠券
	 * @param couponBean
	 * @return
	 * @throws Exception 
	 * @throws ParseException 
	 */
	public int allocationCoupon(CouponBean couponBean,String member_ids) throws Exception {
		int num=0;
		CouponBean couponBean2=memberDao.getCouponDetail(couponBean);
		if(TimeUtils.compareDate(couponBean2.getEnd_time(), TimeUtils.getCurrentTime(), "yyyy-MM-dd HH:mm:ss")!=1) {
			throw new Exception("优惠券已过期");
		}
		String ids[]=member_ids.split(",");
		for(String id:ids) {
			MemberBean memberBean=memberDao.getMemberDetail(new MemberBean().setMember_id(Integer.valueOf(id)));
			if(memberBean==null) {
				throw new Exception("ID为【"+id+"】的用户不存在");
			}
			CouponBean couponBean3=memberDao.getCouponByCouponIdAndMemberId(couponBean.setMember_id(Integer.valueOf(id)));
			if(couponBean3!=null){
				throw new Exception("ID为【"+id+"】的用户已拥有此优惠券");
			}else {
				String start_time=TimeUtils.getCurrentTime();
				String end_time=TimeUtils.getTimeDayAfter("yyyy-MM-dd HH:mm:ss",start_time, couponBean2.getValid_day());
				couponBean2.setMember_id(Integer.valueOf(id)).setStart_time(start_time).setEnd_time(end_time);
				num=memberDao.allocationCoupon(couponBean);
				if(num==0) {
					throw new Exception("ID为【"+id+"】的用户分配失败");
				}
			}
		}
		return num;
	}
	/**
	 * 所有用户分配优惠券
	 * @param couponBean
	 * @return
	 * @throws Exception
	 */
	public int allocationAllMemberCoupon(CouponBean couponBean) throws Exception {
		CouponBean couponBean2=memberDao.getCouponDetail(couponBean);
		if(TimeUtils.compareDate(couponBean2.getEnd_time(), TimeUtils.getCurrentTime(), "yyyy-MM-dd HH:mm:ss")!=1) {
			throw new Exception("优惠券已过期");
		}
		String start_time=TimeUtils.getCurrentTime();
		String end_time=TimeUtils.getTimeDayAfter("yyyy-MM-dd HH:mm:ss",start_time, couponBean2.getValid_day());
		couponBean2.setStart_time(start_time).setEnd_time(end_time);
		return memberDao.allocationAllMemberCoupon(couponBean2);
	}
	/**
	 * 用户提现列表
	 * @param withdrawalBean
	 * @param pageBean
	 * @return
	 */
	public List<WithdrawalBean> getWithdrawalList(WithdrawalBean withdrawalBean, PageBean pageBean) {
		return memberDao.getWithdrawalList(withdrawalBean,pageBean);
	}
	/**
	 * 修改提现信息
	 * @param withdrawalBean
	 * @return
	 * @throws Exception 
	 */
	public int updateWithdrawal(WithdrawalBean withdrawalBean) throws Exception {
		WithdrawalBean withdrawalBean2=memberDao.getWithdrawalDetail(withdrawalBean);
		if(withdrawalBean.getWithdrawal_state().equals("refuse")&&!withdrawalBean2.getWithdrawal_state().equals("refuse")) {
			MemberBean memberBean=memberDao.getMemberDetail(new MemberBean().setMember_id(withdrawalBean.getMember_id()));
			int num=memberDao.updateMemberBalance(new MemberBean().setMember_id(withdrawalBean.getMember_id())
					.setMember_extract_money(memberBean.getMember_extract_money()+withdrawalBean2.getWithdrawal_price()));
			if(num==0) {
				throw new Exception("用户余额更新失败");
			}
		}else if(withdrawalBean.getWithdrawal_state().equals("accept")&&!withdrawalBean2.getWithdrawal_state().equals("accept")) {
			withdrawalBean.setOrder_no(NumberUtils.createRandom(false, 16));
		}
		return memberDao.updateWithdrawal(withdrawalBean);
	}
	/**
	 * 提现信息详情
	 * @param withdrawalBean
	 * @return
	 */
	public WithdrawalBean getWithdrawalDetail(WithdrawalBean withdrawalBean) {
		return memberDao.getWithdrawalDetail(withdrawalBean);
	}
	/**
	 * 用户收货地址列表
	 * @param addressBean
	 * @return
	 */
	public List<AddressBean> getMemberAddressList(AddressBean addressBean) {
		return memberDao.getMemberAddressList(addressBean);
	}
	/**
	 * 报备信息列表
	 * @param memberReportedBean
	 * @param pageBean
	 * @return
	 */
	public List<MemberReportedBean> getReportedList(MemberReportedBean memberReportedBean, PageBean pageBean) {
		return memberDao.getReportedList(memberReportedBean,pageBean);
	}
	/**
	 * 报备信息详情
	 * @param memberReportedBean
	 * @return
	 */
	public MemberReportedBean getReportedDetail(MemberReportedBean memberReportedBean) {
		return memberDao.getReportedDetail(memberReportedBean);
	}
	/**
	 * 修改报备信息
	 * @param memberReportedBean
	 * @return
	 */
	public int updateReported(MemberReportedBean memberReportedBean) {
		return memberDao.updateReported(memberReportedBean);
	}
	/**
	 * 添加工种
	 * @param workTypeBean
	 * @return
	 */
	public int insertWorkType(WorkTypeBean workTypeBean) {
		return memberDao.insertWorkType(workTypeBean);
	}
	/**
	 * 修改工种
	 * @param workTypeBean
	 * @return
	 */
	public int updateWorkType(WorkTypeBean workTypeBean) {
		return memberDao.updateWorkType(workTypeBean);
	}
	/**
	 * 工种详情
	 * @param workTypeBean
	 * @return
	 */
	public WorkTypeBean getWorkTypeDetail(WorkTypeBean workTypeBean) {
		return memberDao.getWorkTypeDetail(workTypeBean);
	}
	/**
	 * 工种列表
	 * @param workTypeBean
	 * @param pageBean
	 * @return
	 */
	public List<WorkTypeBean> getWorkTypeList(WorkTypeBean workTypeBean, PageBean pageBean) {
		return memberDao.getWorkTypeList(workTypeBean,pageBean);
	}
	/**
	 * 删除工种
	 * @param workTypeBean
	 * @return
	 */
	public int deleteWorkType(WorkTypeBean workTypeBean) {
		return memberDao.deleteWorkType(workTypeBean);
	}
	/**
	 * 导出提现excel
	 * @param withdrawalBean
	 * @return
	 */
	public List<Object> exportWithdrawalExcel(WithdrawalBean withdrawalBean) {
		return memberDao.exportWithdrawalExcel(withdrawalBean);
	}
}
