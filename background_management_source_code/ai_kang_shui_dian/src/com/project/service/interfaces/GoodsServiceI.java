package com.project.service.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsClassBean;
import com.project.bean.goods.GoodsImgBean;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.goods.HotSearchBean;
import com.project.bean.goods.SpecificationBean;
import com.project.bean.order.AssessmentBean;
import com.project.dao.interfaces.GoodsDaoI;
import com.project.dao.interfaces.OrderDaoI;
import com.project.page.PageBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceI {
	@Autowired
	GoodsDaoI goodsDao;
	
	@Autowired
	OrderDaoI orderDao;
	
	@Autowired
	OrderServiceI orderService;
	
	/**
	 * 修改商品详情
	 * @param goodsBean
	 * @return
	 */
	public int updateGoodsDetail(GoodsBean goodsBean){
		return goodsDao.updateGoodsDetail(goodsBean);
	}
	
	/**
	 * 商品详情
	 * @param goodsBean
	 * @return
	 * @throws Exception 
	 */
	public GoodsBean getGoodsDetail(GoodsBean goodsBean) throws Exception{
		GoodsBean goodsBean2=goodsDao.getGoodsDetail(goodsBean);
		if(goodsBean2!=null){
			List<GoodsImgBean> goodsImgBeans=getGoodsImgs(new GoodsImgBean().setGoods_id(goodsBean.getGoods_id()));
			goodsBean2.setGoodsImgBeans(goodsImgBeans);
			List<GoodsSpecificationBean> goodsSpecificationBeans=goodsDao
					.getGoodsSpecificationBeans(new GoodsSpecificationBean().setGoods_id(goodsBean2.getGoods_id()));
			goodsBean2.setGoodsSpecificationBeans(goodsSpecificationBeans);// 商品规格
			List<SpecificationBean> specificationBeans = getGoodsSpecificationBeans(
					new GoodsSpecificationBean().setGoods_id(goodsBean2.getGoods_id()));
			List<SpecificationBean> tempSpecificationBeans = new ArrayList<SpecificationBean>();
			if(goodsSpecificationBeans!=null&&goodsSpecificationBeans.size()>0){
				String[] ids = goodsSpecificationBeans.get(0).getSpecification_ids().split(",");
				for (int i = 0; i < ids.length; i++) {
					int id =Integer.valueOf(ids[i]);
					for(SpecificationBean parent:specificationBeans){
						for(SpecificationBean child:parent.getSpecificationBeans()){
							if(id==child.getSpecification_id()){
								tempSpecificationBeans.add(parent);
								break;
							}
						}
					}
				}
			}
			goodsBean2.setSpecificationBeans(tempSpecificationBeans);
		}
		return goodsBean2;
	}
	/**
	 * 商品的规格详情
	 * @param goodsSpecificationBean
	 * @return
	 */
	public GoodsSpecificationBean getGoodsSpecificationDetail(GoodsSpecificationBean goodsSpecificationBean){
		return goodsDao.getGoodsSpecificationDetail(goodsSpecificationBean);
	}
	/**
	 * 修改规格详情
	 * @param goodsSpecificationBean
	 * @return
	 */
	public int updateGoodsSpecificationDetail(GoodsSpecificationBean goodsSpecificationBean){
		return goodsDao.updateGoodsSpecificationDetail(goodsSpecificationBean);
	}
	/**
	 * 通过商品id获取商品规格信息
	 * @param specificationBean
	 * @return
	 */
	private List<SpecificationBean> getGoodsSpecificationBeans(GoodsSpecificationBean goodsSpecificationBean){
		List<SpecificationBean> specificationBeans=goodsDao.getGoodsSpecificationParent(goodsSpecificationBean);
		for(SpecificationBean bean:specificationBeans){
			List<SpecificationBean> specificationBeanList=goodsDao
					.getGoodsSpecificationChild(goodsSpecificationBean.setParent_id(bean.getSpecification_id()));
			bean.setSpecificationBeans(specificationBeanList);
		}
		return specificationBeans;
	}
	
	/**
	 * 商品轮播图片列表
	 * @param goodsImgBean
	 * @return
	 */
	public List<GoodsImgBean> getGoodsImgs(GoodsImgBean goodsImgBean){
		return goodsDao.getGoodsImgs(goodsImgBean);
	}
	
	/**
	 * 获得商品列表
	 * @param goodsBean
	 * @return
	 */
	public List<GoodsBean> getGoodsList(GoodsBean goodsBean,PageBean pageBean){
		List<GoodsBean> goodsBeans=goodsDao.getGoodsList(goodsBean,pageBean);
		if(goodsBean.getGoods_name()!=null&&!"".equals(goodsBean.getGoods_name())) {
			goodsDao.insertHotSearch(new HotSearchBean().setSearch_name(goodsBean.getGoods_name()));
		}
		return goodsBeans;
	}
	
	/**
	 * 自定义多级商品分类列表
	 * @param goodsClassBean
	 * @param level
	 * @param pageBean
	 * @return
	 */
//	@Cacheable(value="goodsClassBeans")
	public List<GoodsClassBean> getGoodsClassListCache() {
		return goodsDao.getGoodsClassListCache();
	}
	/**
	 * 商品热搜关键字列表
	 * @param hotSearchBean
	 * @return
	 */
	public List<HotSearchBean> getHotSearchList(HotSearchBean hotSearchBean,PageBean pageBean) {
		return goodsDao.getHotSearchList(hotSearchBean,pageBean);
	}
	/**
	 * 评价列表
	 * @param assessmentBean
	 * @param pageBean
	 * @return
	 */
	public List<AssessmentBean> getAssessmentList(AssessmentBean assessmentBean,PageBean pageBean) {
		return goodsDao.getAssessmentList(assessmentBean,pageBean);
	}
	/**
	 * 获取主页各分类四个热卖商品
	 * @return
	 */
	public List<GoodsClassBean> getHomeClassGoodsList(GoodsBean goodsBean) {
		List<GoodsClassBean> goodsClassBeans=goodsDao.getGoodsClassList(new GoodsClassBean().setParent_id(-1));
		for(GoodsClassBean goodsClassBean1:goodsClassBeans){
			List<GoodsBean> goodsBeans=goodsDao.getHotGoodsListByClass(goodsBean.setClass_id(goodsClassBean1.getClass_id()));
			goodsClassBean1.setGoodsBeans(goodsBeans);
		}
		return goodsClassBeans;
	}
}
