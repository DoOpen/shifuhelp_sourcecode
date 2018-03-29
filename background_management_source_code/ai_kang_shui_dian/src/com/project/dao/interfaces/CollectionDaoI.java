package com.project.dao.interfaces;

import java.util.List;

import com.project.bean.member.CollectionBean;
import com.project.page.PageBean;


public interface CollectionDaoI {
	
	/**
	 * 获得收藏列表
	 * @param collectionBean
	 * @param pageBean
	 * @return
	 */
	public  List<CollectionBean> getCollectionList(CollectionBean collectionBean,PageBean pageBean);
	
	/**
	 * 添加收藏
	 * @param collectionBean
	 */
	public int insertCollection(CollectionBean collectionBean);
	
	/**
	 * 修改收藏
	 * @param collectionBean
	 */
	public int updateCollection(CollectionBean collectionBean);
	
	/**
	 * 获得收藏详情
	 * @param collectionBean
	 */
	public CollectionBean getCollectionDetail(CollectionBean collectionBean);
	
}
