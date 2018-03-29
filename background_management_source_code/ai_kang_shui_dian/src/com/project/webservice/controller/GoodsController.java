package com.project.webservice.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsClassBean;
import com.project.bean.goods.GoodsImgBean;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.goods.SpecificationBean;
import com.project.page.PageBean;
import com.project.service.controller.GoodsServiceC;
import com.project.utils.TimeUtils;
import com.project.webservice.BaseController;

@RestController
@RequestMapping("/goodsController.api")
public class GoodsController extends BaseController{
	
	@Autowired
	GoodsServiceC goodsService;
	/**
	 * 商品规格列表
	 * @param goodsBean
	 */
	@RequestMapping(params="getGoodsSpecificationList")
	public void getGoodsSpecificationList(GoodsSpecificationBean goodsSpecificationBean,PageBean pageBean) {
		WriteObject(goodsService.getGoodsSpecificationList(goodsSpecificationBean),pageBean.getTotal());
	}
	/**
	 * 商品已选择的规格列表
	 * @param goodsSpecificationBean
	 * @param pageBean
	 */
	@RequestMapping(params="getSelectSpecificationList")
	public void getSelectSpecificationList(GoodsSpecificationBean goodsSpecificationBean) {
		WriteObject(goodsService.getSelectSpecificationList(goodsSpecificationBean));
	}
	/**
	 *商品类别列表
	 * @param goodsClassBean
	 * @param pageBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getGoodsClassList")
	public void getGoodsClassList(GoodsClassBean goodsClassBean,Integer level,PageBean pageBean) {
		WriteObject(goodsService.getGoodsClassList(goodsClassBean,level,pageBean),pageBean.getTotal());
	}
	/**
	 * 商品类别详情
	 * @param goodsClassBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getGoodsClassDetail")
	public void getGoodsClassDetail( GoodsClassBean goodsClassBean){
		WriteObject(goodsService.getGoodsClassDetail(goodsClassBean));
	}
	/**
	 * 删除商品类别
	 * @param goodsClassBean
	 * @throws IOException 
	 */
	@RequestMapping(params="deleteGoodsClass")
	public void deleteGoodsClass( GoodsClassBean goodsClassBean){
		int num=goodsService.deleteGoodsClass(goodsClassBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 添加商品类别
	 * @param goodsClassBean
	 * @throws IOException 
	 */
	@RequestMapping(params="insertGoodsClass")
	public void insertGoodsClass( GoodsClassBean goodsClassBean){
		int num=goodsService.insertGoodsClass(goodsClassBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改商品类别
	 * @param goodsClassBean
	 * @throws IOException 
	 */
	@RequestMapping(params="updateGoodsClass")
	public void updateGoodsClass( GoodsClassBean goodsClassBean){
		int num=goodsService.updateGoodsClass(goodsClassBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 *商品列表
	 * @param goodsBean
	 * @param pageBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getGoodsList")
	public void getGoodsList( GoodsBean goodsBean,PageBean pageBean){
		WriteObject(goodsService.getGoodsList(goodsBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 商品详情
	 * @param goodsBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getGoodsDetail")
	public void getGoodsDetail( GoodsBean goodsBean){
		WriteObject(goodsService.getGoodsDetail(goodsBean));
	}
	/**
	 * 删除商品
	 * @param goodsBean
	 * @throws IOException 
	 */
	@RequestMapping(params="deleteGoods")
	public void deleteGoods( GoodsBean goodsBean){
		int num=goodsService.deleteGoods(goodsBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 添加商品
	 * @param goodsBean
	 * @throws Exception 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="insertGoods")
	public void insertGoods(GoodsBean goodsBean,String goods_specifications,String goods_imgs) throws Exception{
		List<GoodsSpecificationBean> goodsSpecificationBeans=(List<GoodsSpecificationBean>) jsonToObject(goods_specifications, ArrayList.class, GoodsSpecificationBean.class);
		goodsBean.setGoodsSpecificationBeans(goodsSpecificationBeans);
		List<GoodsImgBean> goodsImgBeans=(List<GoodsImgBean>) jsonToObject(goods_imgs, ArrayList.class, GoodsImgBean.class);
		goodsBean.setGoodsImgBeans(goodsImgBeans);
		int num=0;
		if(goodsBean.getGoods_url_content()!=null) {
			if(goodsBean.getGoods_url()==null||"".equals(goodsBean.getGoods_url())) {
				String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE);
				String path="/html/goods/"+fileName+".html";
				boolean result=writeHtml(path, goodsBean.getGoods_url_content(),null);
				if(result) {
					goodsBean.setGoods_url(path);
				}
			}else {
				writeHtml(goodsBean.getGoods_url(), goodsBean.getGoods_url_content(),null);
			}
		}
		num = goodsService.insertGoods(goodsBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改商品
	 * @param goodsBean
	 * @throws Exception 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="updateGoods")
	public void updateGoods( GoodsBean goodsBean,String goods_specifications,String goods_imgs) throws Exception{
		List<GoodsSpecificationBean> goodsSpecificationBeans=(List<GoodsSpecificationBean>) jsonToObject(goods_specifications, ArrayList.class, GoodsSpecificationBean.class);
		goodsBean.setGoodsSpecificationBeans(goodsSpecificationBeans);
		System.out.println(goods_imgs);
		List<GoodsImgBean> goodsImgBeans=(List<GoodsImgBean>) jsonToObject(goods_imgs, ArrayList.class, GoodsImgBean.class);
		goodsBean.setGoodsImgBeans(goodsImgBeans);
		int num=0;
		if(goodsBean.getGoods_url_content()!=null) {
			if(goodsBean.getGoods_url()==null||"".equals(goodsBean.getGoods_url())) {
				String fileName=TimeUtils.getCurrentTime("yyyyMMddHHmmss")+new Random().nextInt(Integer.MAX_VALUE);
				String path="/html/goods/"+fileName+".html";
				boolean result=writeHtml(path, goodsBean.getGoods_url_content(),null);
				if(result) {
					goodsBean.setGoods_url(path);
				}
			}else {
				writeHtml(goodsBean.getGoods_url(), goodsBean.getGoods_url_content(),null);
			}
		}
		num = goodsService.updateGoods(goodsBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 *规格总表列表
	 * @param goodsClassBean
	 * @param pageBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getSpecificationList")
	public void getSpecificationList( SpecificationBean specificationBean,Integer level,PageBean pageBean){
		WriteObject(goodsService.getSpecificationList(specificationBean,level,pageBean),pageBean.getTotal());
	}
	/**
	 * 规格总表详情
	 * @param goodsClassBean
	 * @throws IOException 
	 */
	@RequestMapping(params="getSpecificationDetail")
	public void getSpecificationDetail( SpecificationBean specificationBean){
		WriteObject(goodsService.getSpecificationDetail(specificationBean));
	}
	/**
	 * 删除规格总表
	 * @param goodsClassBean
	 * @throws IOException 
	 */
	@RequestMapping(params="deleteSpecification")
	public void deleteSpecification( SpecificationBean specificationBean){
		int num=goodsService.deleteSpecification(specificationBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 添加规格总表
	 * @param goodsClassBean
	 * @throws IOException 
	 */
	@RequestMapping(params="insertSpecification")
	public void insertSpecification( SpecificationBean specificationBean){
		int num=goodsService.insertSpecification(specificationBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
	/**
	 * 修改规格总表
	 * @param specificationBean
	 * @throws IOException 
	 */
	@RequestMapping(params="updateSpecification")
	public void updateSpecification( SpecificationBean specificationBean){
		int num=goodsService.updateSpecification(specificationBean);
		if(num>0) {
			WriteObject("操作成功");
		}else {
			WriteError("操作失败");
		}
	}
}
