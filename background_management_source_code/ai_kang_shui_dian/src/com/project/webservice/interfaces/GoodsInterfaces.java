package com.project.webservice.interfaces;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.bean.goods.GoodsBean;
import com.project.bean.goods.GoodsSpecificationBean;
import com.project.bean.goods.HotSearchBean;
import com.project.bean.order.AssessmentBean;
import com.project.others.NotToken;
import com.project.page.PageBean;
import com.project.service.interfaces.GoodsServiceI;
import com.project.service.interfaces.MemberServiceI;
import com.project.webservice.BaseController;

@Controller
@RequestMapping("/goodsInterfaces.api")
public class GoodsInterfaces extends BaseController{
	@Autowired
	GoodsServiceI goodsService;
	
	@Autowired
	MemberServiceI memberService;
	
	/**
	 * 获取主页各分类四个热卖商品
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getHomeClassGoodsList", method = RequestMethod.POST)
	public void getHomeClassGoodsList(GoodsBean goodsBean) throws Exception{
		WriteObject(goodsService.getHomeClassGoodsList(goodsBean));
	}
	/**
	 * 评价列表
	 * @param assessmentBean
	 */
	@NotToken
	@RequestMapping(params = "getAssessmentList", method = RequestMethod.POST)
	public void getAssessmentList(AssessmentBean assessmentBean,PageBean pageBean) {
		WriteObject(goodsService.getAssessmentList(assessmentBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 商品热搜关键字列表
	 * @param hotSearchBean
	 */
	@NotToken
	@RequestMapping(params = "getHotSearchList", method = RequestMethod.POST)
	public void getHotSearchList(HotSearchBean hotSearchBean,PageBean pageBean) {
		WriteObject(goodsService.getHotSearchList(hotSearchBean,pageBean),pageBean.getTotal());
	}
	/**
	 * 商品规格详情
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getGoodsSpecificationDetail", method = RequestMethod.POST)
	public void getGoodsSpecificationDetail(GoodsSpecificationBean goodsSpecificationBean) throws Exception {
		WriteObject(goodsService.getGoodsSpecificationDetail(goodsSpecificationBean));
	}
	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getGoodsDetail", method = RequestMethod.POST)
	public void getGoodsDetail(GoodsBean goodsBean) throws Exception {
		GoodsBean goodsBean2=goodsService.getGoodsDetail(goodsBean);
		String goods_url_desc=readHtml(goodsBean2.getGoods_url());
		WriteObject(goodsBean2.setGoods_url_content(goods_url_desc));
	}
	/**
	 * 获得商品列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@NotToken
	@RequestMapping(params = "getGoodsList", method = RequestMethod.POST)
	public void getGoodsList(GoodsBean goodsBean,PageBean pageBean) throws Exception {
		WriteObject(goodsService.getGoodsList(goodsBean, pageBean),pageBean.getTotal());
	}
	
	/**
	 *商品类别列表
	 * @param goodsClassBean
	 * @param pageBean
	 * @throws IOException 
	 */
	@NotToken
	@RequestMapping(params="getGoodsClassListCache")
	public void getGoodsClassListCache() {
		WriteObject(goodsService.getGoodsClassListCache());
	}
}
