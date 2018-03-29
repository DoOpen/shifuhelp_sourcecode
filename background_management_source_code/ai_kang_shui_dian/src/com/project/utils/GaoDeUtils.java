package com.project.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bean.others.LocationBean;

public class GaoDeUtils {
	public static LocationBean addressToLocation(String address) {
		LocationBean locationBean=null;
		String params="key=4a4775afef857b08ca7e3ed2407a91ba"+"&address="+address;
		String result=HttpUtils.sendGet("http://restapi.amap.com/v3/geocode/geo", params);
		try {
			JsonNode jsonNode = new ObjectMapper().readTree(result);
            if(jsonNode.findValue("status").textValue().equals("1")) {
                String location = jsonNode.findValue("location").textValue();
			if(location!=null&&!location.equals("")) {
			 locationBean=new LocationBean().setLongitude(location.split(",")[0]).setLatitude(location.split(",")[1]);
			 return locationBean;
		}
            }
		}catch (Exception e) {
			e.printStackTrace();
			return locationBean;
		}
		return locationBean;
	}
}
