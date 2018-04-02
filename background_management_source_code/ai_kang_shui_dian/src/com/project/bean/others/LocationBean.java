package com.project.bean.others;

/**
 * 地图定位经纬度
 * @author 彭方林
 * @date 2018年4月2日
 */
public class LocationBean {
	private String longitude;
	private String latitude;
	public String getLongitude() {
		return longitude;
	}
	public LocationBean setLongitude(String longitude) {
		this.longitude = longitude;
		return this;
	}
	public String getLatitude() {
		return latitude;
	}
	public LocationBean setLatitude(String latitude) {
		this.latitude = latitude;
		return this;
	}
}
