package com.project.webservice.interfaces;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.goods.ShopCarBean;
import com.project.page.PageBean;
import com.project.service.interfaces.MemberServiceI;
import com.project.service.interfaces.ShopCarServiceI;
import com.project.webservice.BaseController;

@RestController
@RequestMapping("/shopCarInterfaces.api")
public class ShopCarInterfaces extends BaseController{
	@Autowired
	MemberServiceI memberService;
	
	@Autowired
	ShopCarServiceI shopCarService;
	/** 
	 * 删除购物车
	 * @param memberBean
	 * @param shoppingCarBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteShopCar", method = RequestMethod.POST)
	public void deleteShopCar(String car_ids) throws Exception{
		int num=shopCarService.deleteShopCar(car_ids);
		if(num>0){
			WriteMsg("删除成功");
		}else{
			WriteError("删除失败");
		}
	 }
	
	/** 
	 * 修改购物车
	 * @param memberBean
	 * @param shoppingCarBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "updateShopCarDetail", method = RequestMethod.POST)
	public void updateShopCarDetail(ShopCarBean shopCarBean) throws Exception {
		ShopCarBean shopCarBean2=shopCarService.getShopCarDetail(shopCarBean);
		if(shopCarBean2==null){
			throw new Exception("该购物车已不存在!");
		}
		int num=shopCarService.updateShopCar(shopCarBean2.setGoods_num(shopCarBean.getGoods_num()));
		if(num>0){
			WriteMsg("修改成功");
		}else{
			WriteError("修改失败");
		}
	 }
	/**
	 * 批量修改购物车数量
	 * @param shop_cars
	 * @throws Exception
	 */
	@RequestMapping(params = "updateShopCarList", method = RequestMethod.POST)
	public void updateShopCarList(String shop_cars) throws Exception {
		if(shop_cars==null||"".equals(shop_cars)) {
			WriteError("购物车为空");
			return;
		}
		@SuppressWarnings("unchecked")
		List<ShopCarBean> shopCarBeans=(List<ShopCarBean>) jsonToObject(shop_cars, ArrayList.class, ShopCarBean.class);
		int num=shopCarService.updateShopCarList(shopCarBeans);
		if(num>0){
			WriteMsg("修改成功");
		}else{
			WriteError("修改失败");
		}
	 }
	/** 
	 * 获得自己的购物车列表
	 * @param memberBean
	 * @param shoppingCarBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getShopCarList", method = RequestMethod.POST)
	public void getShopCarList(ShopCarBean shopCarBean,PageBean pageBean) throws Exception {
		WriteObject(shopCarService.getShopCarList(shopCarBean,pageBean),pageBean.getTotal());
	 }
	/**
	 * 添加购物车
	 * 
	 * @param goodsBean
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "insertShopCar", method = RequestMethod.POST)
	public void insertShopCar(ShopCarBean shopCarBean) throws Exception{	
		ShopCarBean shopCarBean2=shopCarService.getShopCarDetail(shopCarBean);
		if(shopCarBean2!=null){
			int num = shopCarService.updateShopCar(shopCarBean2
					.setGoods_num((shopCarBean2.getGoods_num()+shopCarBean.getGoods_num())));
			if (num > 0) {
				WriteObject(shopCarService.getShopCarDetail(new ShopCarBean().setCar_id(shopCarBean2.getCar_id())));
			} else {
				WriteError("添加失败");
			}
		}else{
			int num = shopCarService.insertShopCar(shopCarBean);
			if (num > 0) {
				WriteObject(shopCarService.getShopCarDetail(new ShopCarBean().setCar_id(shopCarBean.getCar_id())));
			} else {
				WriteError("添加失败");
			}
		}
	}
}
