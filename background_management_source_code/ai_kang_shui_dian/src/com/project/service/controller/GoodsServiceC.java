package com.project.service.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsClassBean;
import com.project.bean.goods.GoodsImgBean;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.goods.SpecificationBean;
import com.project.dao.controller.GoodsDaoC;
import com.project.page.PageBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsServiceC {
	@Autowired
	GoodsDaoC goodsDao;
	/**
	 * 商品类别详情
	 * @param goodsClassBean
	 * @return
	 */
	public GoodsClassBean getGoodsClassDetail(GoodsClassBean goodsClassBean) {
		return goodsDao.getGoodsClassDetail(goodsClassBean);
	}
	/**
	 * 删除商品类别
	 * @param goodsClassBean
	 * @return
	 */
	public int deleteGoodsClass(GoodsClassBean goodsClassBean) {
		return goodsDao.deleteGoodsClass(goodsClassBean);
	}
	/**
	 * 添加商品分类
	 * @param goodsClassBean
	 * @return
	 */
	public int insertGoodsClass(GoodsClassBean goodsClassBean) {
		if(goodsClassBean.getParent_id()==null) {
			goodsClassBean.setParent_id(-1);
		}
		GoodsClassBean goodsClassBean2=goodsDao.getGoodsClassDetail(new GoodsClassBean().setClass_id(goodsClassBean.getParent_id()));
		goodsClassBean.setClass_uuid("#"+UUID.randomUUID().toString());
		if(goodsClassBean2!=null) {
			goodsClassBean.setParent_uuid(goodsClassBean2.getParent_uuid()+goodsClassBean.getClass_uuid());
		}else {
			goodsClassBean.setParent_uuid(goodsClassBean.getClass_uuid());
		}
		return goodsDao.insertGoodsClass(goodsClassBean);
	}
	/**
	 * 修改商品分类
	 * @param goodsClassBean
	 * @return
	 */
	public int updateGoodsClass(GoodsClassBean goodsClassBean) {
		return goodsDao.updateGoodsClass(goodsClassBean);
	}
	/**
	 * 添加规格总表
	 * @param specificationBean
	 * @return
	 */
	public int insertSpecification(SpecificationBean specificationBean) {
		return goodsDao.insertSpecification(specificationBean);
	}
	/**
	 * 修改规格总表
	 * @param specificationBean
	 * @return
	 */
	public int updateSpecification(SpecificationBean specificationBean) {
		return goodsDao.updateSpecification(specificationBean);
	}
	/**
	 * 删除规格总表
	 * @param specificationBean
	 * @return
	 */
	public int deleteSpecification(SpecificationBean specificationBean) {
		return goodsDao.deleteSpecification(specificationBean);
	}
	/**
	 * 规格总表详情
	 * @param specificationBean
	 * @return
	 */
	public SpecificationBean getSpecificationDetail(SpecificationBean specificationBean) {
		return goodsDao.getSpecificationDetail(specificationBean);
	}
	/**
	 * 规格总表列表
	 * @param specificationBean
	 * @param pageBean
	 * @return
	 */
	public List<SpecificationBean> getSpecificationList(SpecificationBean specificationBean,Integer level, PageBean pageBean) {
		level=level==null?1:level;
		List<SpecificationBean> specificationBeans=goodsDao.getSpecificationList(specificationBean, pageBean);
		if(level<2) {
			return specificationBeans;
		}else {
			return this.getSpecificationListLevel(specificationBeans,2,level,pageBean);
		}
	}
	/**
	 * 递归获取指定层级的规格总表列表
	 * @param goodsClassBeans
	 * @param now
	 * @param level
	 * @param pageBean
	 * @return
	 */
	private List<SpecificationBean> getSpecificationListLevel(List<SpecificationBean> specificationBeans, int now, Integer level,PageBean pageBean) {
		if(specificationBeans!=null){
			for(SpecificationBean specificationBean:specificationBeans) {
				List<SpecificationBean> specificationBeans2=goodsDao.getSpecificationList(new SpecificationBean().setParent_id(specificationBean.getSpecification_id()), pageBean);
				if(now<level&&specificationBean!=null) {
					specificationBeans2=this.getSpecificationListLevel(specificationBeans2, now+1, level,pageBean);
				}
				specificationBean.setSpecificationBeans(specificationBeans2);
			}
		}
		return specificationBeans;
	}
	/**
	 * 修改商品详情
	 * @param goodsBean
	 * @return
	 * @throws Exception 
	 */
	public int updateGoods(GoodsBean goodsBean) throws Exception {
		int total_stock=0;
		int total_sales=0;
		float min_price=Float.MAX_VALUE;
		float max_price=Float.MIN_VALUE;
		String now_price="0";
		if(goodsBean.getGoodsImgBeans()!=null) {
			goodsDao.deleteGoodsImg(new GoodsImgBean().setGoods_id(goodsBean.getGoods_id()));
			for(int i=0;i<goodsBean.getGoodsImgBeans().size();i++) {
				GoodsImgBean goodsImgBean=goodsBean.getGoodsImgBeans().get(i);
				goodsImgBean.setGoods_id(goodsBean.getGoods_id()).setSort(String.valueOf(i+1));
				int num=0;
				if(goodsImgBean.getImg_id()==null) {
					num=goodsDao.insertGoodsImg(goodsImgBean);
				}else {
					num=goodsDao.updateGoodsImg(goodsImgBean);
				}
				if(num<0) {
					throw new Exception("商品轮播图入库失败");
				}
			}
		}
		if(goodsBean.getGoodsSpecificationBeans()!=null) {
			goodsDao.deleteGoodsSpecification(new GoodsSpecificationBean().setGoods_id(goodsBean.getGoods_id()));
			for(GoodsSpecificationBean goodsSpecificationBean:goodsBean.getGoodsSpecificationBeans()) {
				total_sales+=goodsSpecificationBean.getSpecification_sales();
				total_stock+=goodsSpecificationBean.getSpecification_stock();
				if(goodsSpecificationBean.getSpecification_price()<min_price) {
					min_price=goodsSpecificationBean.getSpecification_price();
				}
				if(goodsSpecificationBean.getSpecification_price()>max_price) {
					max_price=goodsSpecificationBean.getSpecification_price();
				}
				if(goodsSpecificationBean.getSpecification_id()==null) {
					int num=goodsDao.insertGoodsSpecification(goodsSpecificationBean.setGoods_id(goodsBean.getGoods_id()));
					if(num==0) {
						throw new Exception("规格添加失败");
					}
				}else {
					int num=goodsDao.updateGoodsSpecification(goodsSpecificationBean);
					if(num==0) {
						throw new Exception("规格更新失败");
					}
				}
			}
		}
		if(min_price==Float.MAX_VALUE) {
			min_price=0;
		}
		if(max_price==Float.MIN_VALUE) {
			max_price=0;
		}
		now_price=min_price==max_price?String.valueOf(min_price):min_price+"~"+max_price;
		goodsBean.setGoods_stock(total_stock).setGoods_now_price(now_price)
		.setTotal_sales(total_sales);
		GoodsClassBean goodsClassBean=goodsDao.getGoodsClassDetail(new GoodsClassBean().setClass_id(goodsBean.getClass_id()));
		if(goodsClassBean!=null) {
			goodsBean.setClass_uuid(goodsClassBean.getClass_uuid());
		}
		int num=goodsDao.updateGoods(goodsBean.setGoods_min_price(min_price).setGoods_max_price(max_price));
		if(num==0) {
			throw new Exception("商品更新失败");
		}
		return num;
	}
	/**
	 * 添加商品
	 * @param goodsBean
	 * @return
	 * @throws Exception 
	 */
	public int insertGoods(GoodsBean goodsBean) throws Exception {
		int total_stock=0;
		int total_sales=0;
		float min_price=Float.MAX_VALUE;
		float max_price=Float.MIN_VALUE;
		String now_price="0";
		GoodsClassBean goodsClassBean=goodsDao.getGoodsClassDetail(new GoodsClassBean().setClass_id(goodsBean.getClass_id()));
		if(goodsClassBean!=null) {
			goodsBean.setClass_uuid(goodsClassBean.getClass_uuid());
		}
		int num=goodsDao.insertGoods(goodsBean);
		if(num==0) {
			throw new Exception("商品信息添加失败");
		}
		if(goodsBean.getGoodsImgBeans()!=null) {
			for(int i=0;i<goodsBean.getGoodsImgBeans().size();i++) {
				GoodsImgBean goodsImgBean=goodsBean.getGoodsImgBeans().get(i);
				goodsImgBean.setGoods_id(goodsBean.getGoods_id()).setSort(String.valueOf(i+1));
				num=goodsDao.insertGoodsImg(goodsImgBean);
				if(num<0) {
					throw new Exception("商品轮播图入库失败");
				}
			}
		}
		if(goodsBean.getGoodsSpecificationBeans()!=null) {
			for(GoodsSpecificationBean goodsSpecificationBean:goodsBean.getGoodsSpecificationBeans()) {
				total_sales+=goodsSpecificationBean.getSpecification_sales();
				total_stock+=goodsSpecificationBean.getSpecification_stock();
				if(goodsSpecificationBean.getSpecification_price()<min_price) {
					min_price=goodsSpecificationBean.getSpecification_price();
				}
				if(goodsSpecificationBean.getSpecification_price()>max_price) {
					max_price=goodsSpecificationBean.getSpecification_price();
				}
				num=goodsDao.insertGoodsSpecification(goodsSpecificationBean.setGoods_id(goodsBean.getGoods_id()));
				if(num==0) {
					throw new Exception("规格信息添加失败");
				}
			}
		}
		if(min_price==Float.MAX_VALUE) {
			min_price=0;
		}
		if(max_price==Float.MIN_VALUE) {
			max_price=0;
		}
		now_price=min_price==max_price?String.valueOf(min_price):min_price+"~"+max_price;
		goodsBean.setGoods_stock(total_stock).setGoods_now_price(now_price).setActual_sales(0)
		.setTotal_sales(total_sales).setGoods_min_price(min_price).setGoods_max_price(max_price)
		.setExpress_price(goodsBean.getExpress_price()==null?0:goodsBean.getExpress_price());
		num=goodsDao.updateGoods(goodsBean);
		if(num==0) {
			throw new Exception("商品信息更新失败");
		}
		return num;
	}
	/**
	 * 删除商品
	 * @param goodsBean
	 * @return
	 */
	public int deleteGoods(GoodsBean goodsBean) {
		return goodsDao.deleteGoods(goodsBean);
	}
	/**
	 * 商品详情
	 * @param goodsBean
	 * @return
	 */
	public GoodsBean getGoodsDetail(GoodsBean goodsBean) {
		GoodsBean goodsBean2=goodsDao.getGoodsDetail(goodsBean);
		if(goodsBean2!=null) {
			List<GoodsImgBean> goodsImgBeans=goodsDao.getGoodsImgList(new GoodsImgBean().setGoods_id(goodsBean2.getGoods_id()), new PageBean().setLimit(Integer.MAX_VALUE));
			goodsBean2.setGoodsImgBeans(goodsImgBeans);
		}
		return goodsDao.getGoodsDetail(goodsBean);
	}
	/**
	 * 商品列表
	 * @param goodsBean
	 * @param pageBean
	 * @return
	 */
	public List<GoodsBean> getGoodsList(GoodsBean goodsBean, PageBean pageBean) {
		return goodsDao.getGoodsList(goodsBean,pageBean);
	}
	/**
	 * 商品规格列表
	 * @param goodsBean
	 * @return
	 */
	public List<GoodsSpecificationBean> getGoodsSpecificationList(GoodsSpecificationBean goodsSpecificationBean) {
		return goodsDao.getGoodsSpecificationList(goodsSpecificationBean);
	}
	/**
	 * 商品已选择的规格列表
	 * @param goodsSpecificationBean
	 * @return
	 */
	public List<SpecificationBean> getSelectSpecificationList(GoodsSpecificationBean goodsSpecificationBean) {
		List<GoodsSpecificationBean> goodsSpecificationBeans=goodsDao.getGoodsSpecificationList(goodsSpecificationBean);
		StringBuffer sb=new StringBuffer(",");
		for(GoodsSpecificationBean goodsSpecificationBean2:goodsSpecificationBeans) {
			sb.append(goodsSpecificationBean2.getSpecification_ids()).append(",");
		}
		Set<String> specificationSet=new HashSet<String>(Arrays.asList(sb.toString().split(",")));
		sb.delete(0, sb.length());
		for(String specificationId:specificationSet) {
			sb.append(specificationId).append(",");
		}
		List<SpecificationBean> specificationBeans=goodsDao.getSpecificationListByIds(sb.toString());
		return specificationBeans;
	}
	/**
	 * 自定义多级商品分类列表
	 * @param goodsClassBean
	 * @param level
	 * @param pageBean
	 * @return
	 */
	public List<GoodsClassBean> getGoodsClassList(GoodsClassBean goodsClassBean,Integer level,PageBean pageBean) {
		level=level==null?1:level;
		List<GoodsClassBean> goodsClassBeans=goodsDao.getGoodsClassList(goodsClassBean, pageBean);
		if(level<2) {
			return goodsClassBeans;
		}else {
			return this.getGoodsClassListLevel(goodsClassBeans,2,level,pageBean);
		}
	}
	/**
	 * 递归获取指定层级的商品列表
	 * @param goodsClassBeans
	 * @param now
	 * @param level
	 * @param pageBean
	 * @return
	 */
	private List<GoodsClassBean> getGoodsClassListLevel(List<GoodsClassBean> goodsClassBeans, int now, Integer level,PageBean pageBean) {
		if(goodsClassBeans!=null){
			for(GoodsClassBean goodsClassBean:goodsClassBeans) {
				List<GoodsClassBean> goodsClassBeans2=goodsDao.getGoodsClassList(new GoodsClassBean().setParent_id(goodsClassBean.getClass_id()), pageBean);
				if(now<level&&goodsClassBean!=null) {
					goodsClassBeans2=this.getGoodsClassListLevel(goodsClassBeans2, now+1, level,pageBean);
				}
				goodsClassBean.setGoodsClassBeans(goodsClassBeans2);
			}
		}
		return goodsClassBeans;
	}
}
