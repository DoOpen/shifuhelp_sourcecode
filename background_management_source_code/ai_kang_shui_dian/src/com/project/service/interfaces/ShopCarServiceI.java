package com.project.service.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.goods.ShopCarBean;
import com.project.dao.interfaces.GoodsDaoI;
import com.project.dao.interfaces.ShopCarDaoI;
import com.project.page.PageBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopCarServiceI {
	@Autowired
	ShopCarDaoI shopCarDao;
	
	@Autowired
	GoodsServiceI goodsService;
	
	@Autowired
	GoodsDaoI goodsDao;
	
	/**
	 * 删除购物车
	 * @param shopCarBean
	 * @return
	 * @throws Exception 
	 */
	public int deleteShopCar(String car_ids) throws Exception{
		if(car_ids==null||"".equals(car_ids)) {
			throw new Exception("购物车为空");
		}
		return shopCarDao.deleteShopCar(car_ids);
	}
	
	/**
	 * 获得自己的购物车列表
	 * @param shopCarBean
	 * @return
	 */
	public List<ShopCarBean> getShopCarList(ShopCarBean shopCarBean,PageBean pageBean){
		List<ShopCarBean> shopCarBeans=shopCarDao.getShopCarList(shopCarBean,pageBean);
		return shopCarBeans;
	}
	
	/**
	 * 修改购物车详情
	 * @param shopCarBean
	 * @return
	 * @throws Exception 
	 */
	public int updateShopCar(ShopCarBean shopCarBean) throws Exception{
		GoodsBean goodsBean=goodsDao.getGoodsDetail(new GoodsBean().setGoods_id(shopCarBean.getGoods_id()));
		if(goodsBean==null){
			throw new Exception("商品不存在!");
		}
		if("0".equals(goodsBean.getGoods_state())){
			throw new Exception("商品已下架!");
		}
		GoodsSpecificationBean goodsSpecificationBean=goodsDao
				.getGoodsSpecificationDetail(new GoodsSpecificationBean()
						.setSpecification_id(shopCarBean.getSpecification_id()));
		if(goodsSpecificationBean==null){
			throw new Exception("该规格商品不存在!");
		}
		if(!goodsSpecificationBean.getGoods_id().equals(goodsBean.getGoods_id())){
			throw new Exception("商品规格不匹配!");
		}
		if("0".equals(goodsSpecificationBean.getSpecification_state())){
			throw new Exception("该规格商品已下架!");
		}
		if(shopCarBean.getGoods_num()<=0){
			throw new Exception("购物车数量异常!");
		}
		if(goodsSpecificationBean.getSpecification_stock()<shopCarBean.getGoods_num()){
			throw new Exception("该规格库存不足!");
		}
		int num= shopCarDao.updateShopCar(shopCarBean.setGoods_name(goodsBean.getGoods_name())
				.setGoods_img(goodsBean.getGoods_img())
				.setSpecification_ids(goodsSpecificationBean.getSpecification_ids())
				.setSpecification_names(goodsSpecificationBean.getSpecification_names()));
		return num;
	}
	
	/**
	 * 购物车详情
	 * @param shopCarBean
	 * @return
	 */
	public ShopCarBean getShopCarDetail(ShopCarBean shopCarBean){
		return shopCarDao.getShopCarDetail(shopCarBean);
	}
	
	/**
	 * 添加购物车
	 * @param shopCarBean
	 * @return
	 * @throws Exception 
	 */
	public int insertShopCar(ShopCarBean shopCarBean) throws Exception{
		GoodsBean goodsBean=goodsDao.getGoodsDetail(new GoodsBean().setGoods_id(shopCarBean.getGoods_id()));
		if(goodsBean==null){
			throw new Exception("商品不存在!");
		}
		if("0".equals(goodsBean.getGoods_state())){
			throw new Exception("商品已下架!");
		}
		GoodsSpecificationBean goodsSpecificationBean=goodsDao
				.getGoodsSpecificationDetail(new GoodsSpecificationBean()
						.setSpecification_id(shopCarBean.getSpecification_id()));
		if(goodsSpecificationBean==null){
			throw new Exception("该规格商品不存在!");
		}
		if(!goodsSpecificationBean.getGoods_id().equals(goodsBean.getGoods_id())){
			throw new Exception("商品规格不匹配!");
		}
		if("0".equals(goodsSpecificationBean.getSpecification_state())){
			throw new Exception("该规格商品已下架!");
		}
		if(shopCarBean.getGoods_num()<=0){
			throw new Exception("购物车数量异常!");
		}
		shopCarDao.insertShopCar(shopCarBean
									.setGoods_name(goodsBean.getGoods_name())
									.setGoods_img(goodsBean.getGoods_img())
									.setSpecification_ids(goodsSpecificationBean.getSpecification_ids())
									.setSpecification_names(goodsSpecificationBean.getSpecification_names()));
		return shopCarBean.getCar_id();
	}
	/**
	 * 批量修改购物车数量
	 * @param shopCarBeans
	 * @return
	 * @throws Exception 
	 */
	public int updateShopCarList(List<ShopCarBean> shopCarBeans) throws Exception {
		int num=0;
		for(ShopCarBean shopCarBean:shopCarBeans) {
			ShopCarBean shopCarBean2=shopCarDao.getShopCarDetail(shopCarBean);
			if(shopCarBean2==null) {
				throw new Exception("购物车不存在");
			}
			GoodsBean goodsBean=goodsDao.getGoodsDetail(new GoodsBean().setGoods_id(shopCarBean2.getGoods_id()));
			if(goodsBean==null){
				throw new Exception("商品不存在!");
			}
			if("0".equals(goodsBean.getGoods_state())){
				throw new Exception("商品已下架!");
			}
			GoodsSpecificationBean goodsSpecificationBean=goodsDao
					.getGoodsSpecificationDetail(new GoodsSpecificationBean()
							.setSpecification_id(shopCarBean2.getSpecification_id()));
			if(goodsSpecificationBean==null){
				throw new Exception("该规格商品不存在!");
			}
			if(!goodsSpecificationBean.getGoods_id().equals(goodsBean.getGoods_id())){
				throw new Exception("商品规格不匹配!");
			}
			if("0".equals(goodsSpecificationBean.getSpecification_state())){
				throw new Exception("该规格商品已下架!");
			}
			if(shopCarBean.getGoods_num()<=0){
				throw new Exception("购物车数量异常!");
			}
			num=shopCarDao.updateShopCar(shopCarBean);
			if(num==0) {
				throw new Exception("购物车数量更新异常");
			}
		}
		return num;
	}
}
