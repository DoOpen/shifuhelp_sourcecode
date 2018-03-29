package com.project.dao.interfaces;

import java.util.List;

import com.project.bean.member.AddressBean;
import com.project.bean.others.CityBean;
import com.project.page.PageBean;

public interface AddressDaoI {
	/**
	 * 获取省市区列表信息
	 */
	public List<CityBean> getCityListCache();
	/**
	 * 添加地址
	 * @param addressBean
	 * @return
	 */
	public int insertAddress(AddressBean addressBean);
	
	/**
	 * 修改地址
	 * @param addressBean
	 * @return
	 */
	public int updateAddress(AddressBean addressBean);
	
	/**
	 * 获得自己的地址列表
	 * @param addressBean
	 * @return
	 */
	public  List<AddressBean> getMemberAddressList(AddressBean addressBean,PageBean pageBean);

	/**
	 * 刪除地址
	 * @param addressBean
	 * @return
	 */
	public int deleteAddress(AddressBean addressBean);
	
	/**
	 * 设置默认地址
	 * @return
	 */
	public int setDefaultAddress(AddressBean addressBean);
	
	
	/**
	 * 获得默认地址
	 * @param addressBean
	 * @return
	 */
	public AddressBean getDefaultAddress(AddressBean addressBean);
	
	
	/**
	 * 通过地址Id得到详细信息
	 * @param addressBean
	 * @return
	 */
	public AddressBean getAddressDetail(AddressBean addressBean);
}
