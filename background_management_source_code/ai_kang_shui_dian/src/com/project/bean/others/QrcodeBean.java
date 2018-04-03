package com.project.bean.others;
/**
 * 二维码
 * @author 彭方林
 * @date 2018年4月2日
 */
public class QrcodeBean {
	private Integer qrcode_id;
	private String qrcode_desc;//二维码路径
	private String qrcode_type;//二维码类型
	private String is_delete;//1删除
	private String create_time;//创建时间
	public Integer getQrcode_id() {
		return qrcode_id;
	}
	public QrcodeBean setQrcode_id(Integer qrcode_id) {
		this.qrcode_id = qrcode_id;
		return this;
	}
	public String getQrcode_desc() {
		return qrcode_desc;
	}
	public QrcodeBean setQrcode_desc(String qrcode_desc) {
		this.qrcode_desc = qrcode_desc;
		return this;
	}
	public String getQrcode_type() {
		return qrcode_type;
	}
	public QrcodeBean setQrcode_type(String qrcode_type) {
		this.qrcode_type = qrcode_type;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public QrcodeBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public QrcodeBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}	
}
