package com.project.dao.controller;

import java.util.List;

import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsClassBean;
import com.project.bean.goods.GoodsImgBean;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.goods.SpecificationBean;
import com.project.page.PageBean;

public interface GoodsDaoC {
	/**
	 * 商品类别列表
	 * @param goodsClassBean
	 * @param pageBean
	 * @return
	 */
	public List<GoodsClassBean> getGoodsClassList(GoodsClassBean goodsClassBean,PageBean pageBean);
	/**
	 * 商品分类详情
	 * @param goodsClassBean
	 * @return
	 */
	public GoodsClassBean getGoodsClassDetail(GoodsClassBean goodsClassBean);
	/**
	 * 删除商品分类
	 * @param goodsClassBean
	 * @return
	 */
	public int deleteGoodsClass(GoodsClassBean goodsClassBean);
	/**
	 * 添加商品分类
	 * @param goodsClassBean
	 * @return
	 */
	public int insertGoodsClass(GoodsClassBean goodsClassBean);
	/**
	 * 修改商品分类
	 * @param goodsClassBean
	 * @return
	 */
	public int updateGoodsClass(GoodsClassBean goodsClassBean);
	/**
	 * 商品分类列表
	 * @param goodsBean
	 * @param pageBean
	 * @return
	 */
	public List<GoodsBean> getGoodsList(GoodsBean goodsBean, PageBean pageBean);
	/**
	 * 商品分类详情
	 * @param goodsBean
	 * @return
	 */
	public GoodsBean getGoodsDetail(GoodsBean goodsBean);
	/**
	 * 删除商品
	 * @param goodsBean
	 * @return
	 */
	public int deleteGoods(GoodsBean goodsBean);
	/**
	 * 添加商品
	 * @param goodsBean
	 * @return
	 */
	public int insertGoods(GoodsBean goodsBean);
	/**
	 * 修改商品
	 * @param goodsBean
	 * @return
	 */
	public int updateGoods(GoodsBean goodsBean);
	/**
	 * 规格总表列表
	 * @param specificationBean
	 * @param pageBean
	 * @return
	 */
	public List<SpecificationBean> getSpecificationList(SpecificationBean specificationBean, PageBean pageBean);
	/*
	 * 规格总表详情
	 */
	public SpecificationBean getSpecificationDetail(SpecificationBean specificationBean);
	/**
	 * 删除规格总表
	 * @param specificationBean
	 * @return
	 */
	public int deleteSpecification(SpecificationBean specificationBean);
	/**
	 * 修改规格总表
	 * @param specificationBean
	 * @return
	 */
	public int updateSpecification(SpecificationBean specificationBean);
	/**
	 * 添加规格总表
	 * @param specificationBean
	 * @return
	 */
	public int insertSpecification(SpecificationBean specificationBean);
	/**
	 * 商品轮播图列表
	 * @param goodsImgBean
	 * @param pageBean
	 * @return
	 */
	public List<GoodsImgBean> getGoodsImgList(GoodsImgBean goodsImgBean, PageBean pageBean);
	/**
	 * 通过规格id列表获取规格列表
	 * @param specificationIds
	 * @return
	 */
	public List<SpecificationBean> getSpecificationListByIds(String specificationIds);
	/**
	 * 获取子规格的父规格
	 * @param specificationBean
	 * @return
	 */
	public SpecificationBean getParentSpecification(SpecificationBean specificationBean);
	/**
	 * 商品规格列表
	 * @param goodsBean
	 * @return
	 */
	public List<GoodsSpecificationBean> getGoodsSpecificationList(GoodsSpecificationBean goodsSpecificationBean);
	/**
	 * 修改商品规格信息
	 * @param goodsSpecificationBean
	 * @return
	 */
	public int updateGoodsSpecification(GoodsSpecificationBean goodsSpecificationBean);
	/**
	 * 添加商品规格信息
	 * @param goodsSpecificationBean
	 * @return
	 */
	public int insertGoodsSpecification(GoodsSpecificationBean goodsSpecificationBean);
	/**
	 * 删除商品规格信息
	 * @param goodsSpecificationBean
	 * @return
	 */
	public int deleteGoodsSpecification(GoodsSpecificationBean goodsSpecificationBean);
	/**
	 * 添加商品轮播图
	 * @param goodsImgBean
	 * @return
	 */
	public int insertGoodsImg(GoodsImgBean goodsImgBean);
	/**
	 * 更新商品轮播图
	 * @param goodsImgBean
	 * @return
	 */
	public int updateGoodsImg(GoodsImgBean goodsImgBean);
	/**
	 * 删除商品轮播图
	 * @param setGoods_id
	 */
	public void deleteGoodsImg(GoodsImgBean goodsImgBean);
	/**
	 * 获取商品规格组合详情
	 * @param setSpecification_id
	 * @return
	 */
	public GoodsSpecificationBean getGoodsSpecificationDetail(GoodsSpecificationBean goodsSpecificationBean);
}
