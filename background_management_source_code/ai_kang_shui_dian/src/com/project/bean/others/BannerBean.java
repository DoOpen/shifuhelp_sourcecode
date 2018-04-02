package com.project.bean.others;

/**
 * 轮播图广告
 * @author 彭方林
 * @date 2018年4月2日
 */
public class BannerBean {
	private Integer banner_id;
	private String banner_title;//标题
	private String banner_img;//图片路径
	private String banner_url;//本地富文本路径
	private String banner_url_content;//本地富文本内容
	private String banner_type;//common:普通 goods:商品 chain:外链
	private String banner_type_show;
	private String banner_desc;//介绍
	private String create_time;//创建时间
	private Integer sort;//权重
	private String is_delete;//1删除
	private String banner_position;//home:所有主页 wechat_home:微信首页 pc_home:PC主页 app_home:app主页
	private String banner_position_show;
	private Integer goods_id;//商品轮播图关联商品id
	private String chain_url;//外链url
	public Integer getBanner_id() {
		return banner_id;
	}
	public BannerBean setBanner_id(Integer banner_id) {
		this.banner_id = banner_id;
		return this;
	}
	public String getBanner_title() {
		return banner_title;
	}
	public BannerBean setBanner_title(String banner_title) {
		this.banner_title = banner_title;
		return this;
	}
	public String getBanner_img() {
		return banner_img;
	}
	public BannerBean setBanner_img(String banner_img) {
		this.banner_img = banner_img;
		return this;
	}
	public String getBanner_url() {
		return banner_url;
	}
	public BannerBean setBanner_url(String banner_url) {
		this.banner_url = banner_url;
		return this;
	}
	public String getBanner_url_content() {
		return banner_url_content;
	}
	public BannerBean setBanner_url_content(String banner_url_content) {
		this.banner_url_content = banner_url_content;
		return this;
	}
	public String getBanner_type() {
		return banner_type;
	}
	public BannerBean setBanner_type(String banner_type) {
		this.banner_type = banner_type;
		this.banner_type_show="common".equals(banner_type)?"普通广告":
			"goods".equals(banner_type)?"商品广告":
				"chain".equals(banner_type)?"外链广告":"未知";
		return this;
	}
	public String getBanner_desc() {
		return banner_desc;
	}
	public BannerBean setBanner_desc(String banner_desc) {
		this.banner_desc = banner_desc;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public BannerBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public Integer getSort() {
		return sort;
	}
	public BannerBean setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public BannerBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public String getBanner_position() {
		return banner_position;
	}
	public BannerBean setBanner_position(String banner_position) {
		this.banner_position = banner_position;
		this.banner_position_show="home".equals(banner_position)?"所有主页":
			"wechat_home".equals(banner_position)?"微信主页":
				"app_home".equals(banner_position)?"app主页":
					"pc_home".equals(banner_position)?"pc主页":"未知";
		return this;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public BannerBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public String getChain_url() {
		return chain_url;
	}
	public BannerBean setChain_url(String chain_url) {
		this.chain_url = chain_url;
		return this;
	}
	public String getBanner_type_show() {
		return banner_type_show;
	}
	public BannerBean setBanner_type_show(String banner_type_show) {
		this.banner_type_show = banner_type_show;
		return this;
	}
	public String getBanner_position_show() {
		return banner_position_show;
	}
	public BannerBean setBanner_position_show(String banner_position_show) {
		this.banner_position_show = banner_position_show;
		return this;
	}
}
