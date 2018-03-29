package com.project.service.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.goods.GoodsBean;
import com.project.bean.member.CollectionBean;
import com.project.dao.interfaces.CollectionDaoI;
import com.project.dao.interfaces.GoodsDaoI;
import com.project.page.PageBean;

@Service
@Transactional(rollbackFor = Exception.class)
public class CollectionServiceI {
	@Autowired
	CollectionDaoI collectionDao;
	
	@Autowired
	GoodsDaoI goodsDao;
	
	@Autowired
	MemberServiceI memberService;
	/**
	 * 获得收藏列表
	 * @param collectionBean
	 * @param pageBean
	 * @return
	 */
	public List<CollectionBean> getCollectionList(CollectionBean collectionBean,PageBean pageBean){
		 List<CollectionBean> collectionBeans=collectionDao.getCollectionList(collectionBean, pageBean);
		 if(collectionBeans!=null){
			 for (int i = 0; i < collectionBeans.size(); i++) {
				CollectionBean collectionBean2=collectionBeans.get(i);
				GoodsBean goodsBean=goodsDao.getGoodsDetail(new GoodsBean()
						.setGoods_id(collectionBean2.getGoods_id())
						.setMember_id(collectionBean.getMember_id()));
				collectionBean2.setGoodsBean(goodsBean);
			}
		 }
		 return collectionBeans;
	}
	
	/**
	 * 添加收藏
	 * @param collectionBean
	 */
	public int insertCollection(CollectionBean collectionBean){
		return collectionDao.insertCollection(collectionBean);
	}
	
	/**
	 * 修改收藏
	 * @param collectionBean
	 */
	public int updateCollection(CollectionBean collectionBean){
		return collectionDao.updateCollection(collectionBean);
	}
	
	/**
	 * 获得收藏详情
	 * @param collectionBean
	 */
	public CollectionBean getCollectionDetail(CollectionBean collectionBean){
		return collectionDao.getCollectionDetail(collectionBean);
	}
}
