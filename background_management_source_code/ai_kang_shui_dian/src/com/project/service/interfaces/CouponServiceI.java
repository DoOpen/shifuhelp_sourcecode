package com.project.service.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.member.CouponBean;
import com.project.dao.interfaces.CouponDaoI;
import com.project.page.PageBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class CouponServiceI {
	@Autowired
	CouponDaoI couponDao;
	
	/**
	 * 单个优惠卷详情
	 * @return
	 */
	public CouponBean getCouponDetail(CouponBean couponBean){
		return couponDao.getCouponDetail(couponBean);
	}
	
	/**
	 * 可领取优惠卷列表
	 * @return
	 */
	public List<CouponBean> getReceiceCoupons(CouponBean couponBean){
		return couponDao.getReceiceCoupons(couponBean);
	}
	
	/**
	 * 可领取优惠卷列表
	 * @return
	 */
	public List<CouponBean> getReceiceCouponList(CouponBean couponBean,PageBean pageBean){
		return couponDao.getReceiceCouponList(couponBean,pageBean);
	}
	/**
	 * 用户领取优惠卷
	 * @return
	 */
	public int memberReceiveCoupon(CouponBean couponBean){
		return couponDao.memberReceiveCoupon(couponBean);
	}
	
	/**
	 * 用户的优惠卷列表
	 * @param couponBean
	 * @return
	 */
	public List<CouponBean> getCouponList(CouponBean couponBean,PageBean pageBean){
		List<CouponBean> couponBeans=couponDao.getCouponList(couponBean,pageBean);
		if(couponBeans!=null&&"expired".equals(couponBean.getCoupon_state())){
			for(CouponBean coupon:couponBeans){
				coupon.setCoupon_state("expired");
			}
		}
		return couponBeans;
	}
	
	/**
	 * 用户的优惠卷列表
	 * @param couponBean
	 * @return
	 */
	public Map<String,Object> getCouponsCount(CouponBean couponBean){
		return couponDao.getCouponsCount(couponBean);
	}
	/**
	 * 优惠卷用完 更新优惠卷状态
	 * @param couponBean
	 * @return
	 */	
	public int updateCouponState(CouponBean couponBean){
		return couponDao.updateCouponState(couponBean);
	}
	public CouponBean getCouponDetailByMemberCouponId(CouponBean couponBean) {
		return couponDao.getCouponDetailByMemberCouponId(couponBean);
	}
}
