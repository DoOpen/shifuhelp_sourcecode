package com.project.webservice.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.bean.member.AddressBean;
import com.project.others.NotToken;
import com.project.page.PageBean;
import com.project.service.interfaces.AddressServiceI;
import com.project.service.interfaces.MemberServiceI;
import com.project.webservice.BaseController;

@RestController
@RequestMapping("/addressInterfaces.api")
public class AddressInterfaces extends BaseController {
	@Autowired
	MemberServiceI memberService;

	@Autowired
	AddressServiceI addressService;

	/**
	 * 获取城市列表
	 * @param cityBean
	 * @param level
	 * @param pageBean
	 */
	@NotToken
	@RequestMapping(params="getCityListCache")
	public void getCityListCache() {
		WriteObject(addressService.getCityListCache());
	}

	
	/**
	 * 添加地址
	 * 
	 * @param memberBean
	 * @param addressBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "insertUpdateAddress", method = RequestMethod.POST)
	public void insertUpdateAddress(AddressBean addressBean){
		int num = addressService.insertUpdateAddress(addressBean);
		if (num > 0) {
			WriteObject(addressBean.getAddress_id());
		} else {
			WriteError(addressBean.getAddress_id()==null?"添加地址失败":"修改地址失败");
		}
	}

	/**
	 * 获得自己的地址详情
	 * 
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getAddressDetail", method = RequestMethod.POST)
	public void getAddressDetail(AddressBean addressBean) throws Exception {
		WriteObject(addressService.getAddressDetail(addressBean));
	}

	/**
	 * 获得自己的地址列表
	 * 
	 * @param memberBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "getMemberAddressList", method = RequestMethod.POST)
	public void getMemberAddressList(AddressBean addressBean,PageBean pageBean) throws Exception {
		WriteObject(addressService.getMemberAddressList(addressBean,pageBean),pageBean.getTotal());
	}

	/**
	 * 删除地址
	 * 
	 * @param memberBean
	 * @param addressBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "deleteAddress", method = RequestMethod.POST)
	public void deleteAddress(AddressBean addressBean) throws Exception {
		int num = addressService.deleteAddress(addressBean);
		if (num > 0) {
			WriteObject("刪除成功");
		} else {
			WriteError("删除失败");
		}
	}
	/**
	 * 设置默认地址
	 * 
	 * @param memberBean
	 * @param addressBean
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "setDefaultAddress", method = RequestMethod.POST)
	public void setDefaultAddress(AddressBean addressBean) throws Exception {
		int num = addressService.setDefaultAddress(addressBean);
		if (num > 0) {
			WriteObject("设置成功");
		} else {
			WriteError("设置失败");
		}
	}

	/**
	 * 获得默认地址
	 * 
	 * @param addressBean
	 * @return
	 */
	@RequestMapping(params = "getDefaultAddress", method = RequestMethod.POST)
	public void getDefaultAddress(AddressBean addressBean) throws Exception {
		WriteObject(addressService.getDefaultAddress(addressBean));
	}
}
