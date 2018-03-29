package com.project.service.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.bean.member.AddressBean;
import com.project.bean.others.CityBean;
import com.project.bean.others.LocationBean;
import com.project.dao.interfaces.AddressDaoI;
import com.project.page.PageBean;
import com.project.utils.GaoDeUtils;
@Service
@Transactional(rollbackFor = Exception.class)
public class AddressServiceI {
	
	@Autowired
	AddressDaoI addressDao;
	
	/**
	 * 获得省市区信息
	 * @param cityBean
	 * @return
	 */
	@Cacheable(value="cityBeans")
	public List<CityBean> getCityListCache(){
		return addressDao.getCityListCache();
	}
	
	/**
	 * 添加地址
	 * 
	 * @param addressBean
	 * @return
	 */
	public int insertUpdateAddress(AddressBean addressBean) {
		List<AddressBean> addressBeans = addressDao.getMemberAddressList(addressBean,new PageBean().setLimit(Integer.MAX_VALUE));
		String address=addressBean.getAddress_province()+addressBean.getAddress_city()+addressBean.getAddress_district()+addressBean.getAddress_detail();
		LocationBean locationBean=GaoDeUtils.addressToLocation(address);
		if(addressBean.getAddress_id()==null) {
			if (addressBeans == null || addressBeans.size() == 0) {
				addressBean.setIs_default("1");
			} else {
				addressBean.setIs_default("0");
			}
			return addressDao.insertAddress(locationBean!=null?addressBean.setAddress_longitude(locationBean.getLongitude()).setAddress_latitude(locationBean.getLatitude()):addressBean);
		}else {
			return addressDao.updateAddress(locationBean!=null?addressBean.setAddress_longitude(locationBean.getLongitude()).setAddress_latitude(locationBean.getLatitude()):addressBean);
		}
	}

	/**
	 * 获得自己的地址列表
	 * 
	 * @param addressBean
	 * @return
	 */
	public List<AddressBean> getMemberAddressList(AddressBean addressBean,PageBean pageBean) {
		return addressDao.getMemberAddressList(addressBean,pageBean);
	}
	/**
	 * 刪除地址
	 * 
	 * @param addressBean
	 * @return
	 */
	public int deleteAddress(AddressBean addressBean) {
		int num = addressDao.deleteAddress(addressBean);
		if(num>0){
			List<AddressBean> addressBeans = addressDao
					.getMemberAddressList(addressBean,new PageBean().setLimit(Integer.MAX_VALUE));
			boolean is_hava_default = false;
			/*
			 *删除完后 还有地址 就要判断还有没有默认地址了 没有就要设置一条
			 */
			if (addressBeans != null && addressBeans.size() > 0) {
				for (int i = 0; i < addressBeans.size(); i++) {
					if (addressBeans.get(i).getIs_default().equals("1")) {
						is_hava_default=true;
						break;
					}
				}
				/*
				 *如果没有默认的了 就设置第一条为默认的 
				 */
				if(!is_hava_default){
					setDefaultAddress(addressBeans.get(0));
				}
			}	
		}
		return num;
	}

	/**
	 * 设置默认地址
	 * 
	 * @return
	 */
	public int setDefaultAddress(AddressBean addressBean) {
		return addressDao.setDefaultAddress(addressBean);
	}
	
	/**
	 * 获得默认地址
	 * @param addressBean
	 * @return
	 */
	public AddressBean getDefaultAddress(AddressBean addressBean){
		return addressDao.getDefaultAddress(addressBean);
	}
	
	/**
	 * 通过地址Id得到详细信息
	 * @param addressBean
	 * @return
	 */
	public AddressBean getAddressDetail(AddressBean addressBean){
		return addressDao.getAddressDetail(addressBean);
	}
}
