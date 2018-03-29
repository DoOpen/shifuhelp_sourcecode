package com.project.dao.interfaces;

import java.util.List;

import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsClassBean;
import com.project.bean.goods.GoodsImgBean;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.goods.HotSearchBean;
import com.project.bean.goods.SpecificationBean;
import com.project.bean.order.AssessmentBean;
import com.project.page.PageBean;

public interface GoodsDaoI {
	
	/**
	 * 修改商品详情
	 * @param goodsBean
	 * @return
	 */
	public int updateGoodsDetail(GoodsBean goodsBean);
	
	/**
	 * 商品详情
	 * @param goodsBean
	 * @return
	 */
	public GoodsBean getGoodsDetail(GoodsBean goodsBean);
	
	/**
	 * 商品图片列表
	 * @param goodsImgBean
	 * @return
	 */
	public List<GoodsImgBean> getGoodsImgs(GoodsImgBean goodsImgBean);
	
	/**
	 * 商品的规格详情
	 * @param goodsSpecificationBean
	 * @return
	 */
	public GoodsSpecificationBean getGoodsSpecificationDetail(GoodsSpecificationBean goodsSpecificationBean);
	
	/**
	 * 修改规格详情
	 * @param goodsSpecificationBean
	 * @return
	 */
	public int updateGoodsSpecificationDetail(GoodsSpecificationBean goodsSpecificationBean);
	
	/**
	 * 通过商品id获取商品规格信息
	 * @param specificationBean
	 * @return
	 */
	public List<GoodsSpecificationBean> getGoodsSpecificationBeans(GoodsSpecificationBean goodsSpecificationBean);
	
	
	/**
	 * 商品包含的父级规格
	 * @param goodsSpecificationBean
	 * @return
	 */
	public List<SpecificationBean> getGoodsSpecificationParent(GoodsSpecificationBean goodsSpecificationBean);
	
	/**
	 * 商品包含的子级规格
	 * @param goodsSpecificationBean
	 * @return
	 */
	public List<SpecificationBean> getGoodsSpecificationChild(GoodsSpecificationBean goodsSpecificationBean);
	
	/**
	 * 获得商品列表
	 * @param goodsBean
	 * @return
	 */
	public List<GoodsBean> getGoodsList(GoodsBean goodsBean,PageBean pageBean);
	
	/**
	 * 商品类别列表
	 * @param goodsClassBean
	 * @param pageBean
	 * @return
	 */
	public List<GoodsClassBean> getGoodsClassListCache();
	/**
	 * 商品热搜关键字列表
	 * @param hotSearchBean
	 * @param pageBean
	 * @return
	 */
	public List<HotSearchBean> getHotSearchList(HotSearchBean hotSearchBean, PageBean pageBean);
	/**
	 * 添加搜索记录
	 * @param hotSearchBean
	 * @return
	 */
	public int insertHotSearch(HotSearchBean hotSearchBean);
	/**
	 * 评价列表
	 * @param assessmentBean
	 * @param pageBean
	 * @return
	 */
	public List<AssessmentBean> getAssessmentList(AssessmentBean assessmentBean, PageBean pageBean);
	/**
	 * 首页推荐商品
	 * @param pageBean
	 * @return
	 */
	public List<GoodsBean> getRecommendGoodsList(PageBean pageBean);
	/**
	 * 商品分类列表
	 * @param setParent_id
	 * @return
	 */
	public List<GoodsClassBean> getGoodsClassList(GoodsClassBean goodsClassBean);
	/**
	 * 获取分类下热卖商品
	 * @param goodsClass
	 * @return
	 */
	public List<GoodsBean> getHotGoodsListByClass(GoodsBean goodsBean);
}
