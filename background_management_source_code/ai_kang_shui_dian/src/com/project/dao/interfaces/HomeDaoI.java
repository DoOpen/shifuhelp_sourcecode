package com.project.dao.interfaces;

import java.util.List;

import com.project.bean.goods.GoodsClassBean;
import com.project.page.PageBean;

public interface HomeDaoI {
	/**
	 * 首页推荐分类
	 * @param goodsClassBean
	 * @return
	 */
	public List<GoodsClassBean> getRecommendClassList(PageBean pageBean);

}
