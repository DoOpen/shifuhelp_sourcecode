package com.project.dao.interfaces;

import java.util.List;

import com.project.bean.goods.ShopCarBean;
import com.project.page.PageBean;

public interface ShopCarDaoI {
	/**
	 * 删除购物车
	 * @param shopCarBean
	 * @return
	 */
	public int deleteShopCar(String car_ids);
	
	/**
	 * 获得自己的购物车列表
	 * @param shopCarBean
	 * @return
	 */
	public List<ShopCarBean> getShopCarList(ShopCarBean shopCarBean,PageBean pageBean);
	
	/**
	 * 修改购物车详情
	 * @param shopCarBean
	 * @return
	 */
	public int updateShopCar(ShopCarBean shopCarBean);
	/**
	 * 购物车详情
	 * @param shopCarBean
	 * @return
	 */
	public ShopCarBean getShopCarDetail(ShopCarBean shopCarBean);
	
	/**
	 * 添加购物车
	 * @param shopCarBean
	 * @return
	 */
	public int insertShopCar(ShopCarBean shopCarBean);
}
