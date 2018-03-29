package com.project.webservice.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.member.CollectionBean;
import com.project.page.PageBean;
import com.project.service.interfaces.CollectionServiceI;
import com.project.service.interfaces.MemberServiceI;
import com.project.webservice.BaseController;

@RestController
@RequestMapping("/collectionInterfaces.api")
public class CollectionInterfaces extends BaseController{
	
	@Autowired
	MemberServiceI memberService;
	
	@Autowired
	CollectionServiceI collectionService;
	
	/**
	 * 获取用户的收藏的列表
	 * @param collectionBean
	 * @param pageBean
	 * @throws Exception
	 */
	@RequestMapping(params = "getCollectionList", method = RequestMethod.POST)
	public void getCollectionList(CollectionBean collectionBean,PageBean pageBean) throws Exception {
		WriteObject(collectionService.getCollectionList(collectionBean, pageBean),pageBean.getTotal());
	}
	/**
	 * 添加收藏
	 * @param memberBean
	 * @param collectionBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "insertCollection", method = RequestMethod.POST)
	public void insertCollection(CollectionBean collectionBean) throws Exception {
		CollectionBean collectionBean2=collectionService.getCollectionDetail(collectionBean);
		if (collectionBean2 != null && collectionBean2.getIs_delete().equals("0")) {
			WriteError("已收藏");
			return;
		}
		if (collectionBean2 != null && collectionBean2.getIs_delete().equals("1")) {
			int num = collectionService.updateCollection(collectionBean2.setIs_delete("0"));
			if (num > 0) {
				WriteMsg(String.valueOf(collectionBean2.getCollection_id()));
			} else {
				WriteError("收藏失败");
			}
		}else {
			int num=collectionService.insertCollection(collectionBean);
			if (num > 0) {
				WriteMsg(String.valueOf(collectionBean.getCollection_id()));
			} else {
				WriteError("收藏失败");
			}
		}
	}
	/**
	 * 取消收藏
	 * @param memberBean
	 * @param collectionBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "cancelCollection", method = RequestMethod.POST)
	public void cancelCollection(CollectionBean collectionBean) throws Exception {
		int num = collectionService.updateCollection(collectionBean.setIs_delete("1"));
		if (num > 0) {
			WriteMsg("取消成功");
		} else {
			WriteError("取消失败");
		}
	}
	
}
