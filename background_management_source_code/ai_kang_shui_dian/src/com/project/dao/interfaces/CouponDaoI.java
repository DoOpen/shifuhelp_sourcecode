package com.project.dao.interfaces;

import java.util.List;
import java.util.Map;

import com.project.bean.member.CouponBean;
import com.project.page.PageBean;

public interface CouponDaoI {
	
	/**
	 * 单个优惠卷详情
	 * @return
	 */
	public CouponBean getCouponDetail(CouponBean couponBean);
	
	/**
	 * 可领取优惠卷列表
	 * @return
	 */
	public List<CouponBean> getReceiceCouponList(CouponBean couponBean,PageBean pageBean);
	
	/**
	 * 可领取优惠卷列表
	 * @return
	 */
	public List<CouponBean> getReceiceCoupons(CouponBean couponBean);
	/**
	 * 用户领取优惠卷
	 * @return
	 */
	public int memberReceiveCoupon(CouponBean couponBean);
	
	/**
	 * 用户的优惠卷列表
	 * @param couponBean
	 * @return
	 */
	public List<CouponBean> getCouponList(CouponBean couponBean,PageBean pageBean);
	
	/**
	 * 用户的优惠卷列表
	 * @param couponBean
	 * @return
	 */
	public Map<String,Object> getCouponsCount(CouponBean couponBean);
	
	/**
	 * 优惠卷用完 更新优惠卷状态
	 * @param couponBean
	 * @return
	 */
	public int updateCouponState(CouponBean couponBean);
	/**
	 * 获得用户使用的某张优惠券
	 * @param couponBean
	 * @return
	 */
	public CouponBean getCouponByMemberCouponId(CouponBean couponBean);

	public CouponBean getCouponDetailByMemberCouponId(CouponBean couponBean);
}
