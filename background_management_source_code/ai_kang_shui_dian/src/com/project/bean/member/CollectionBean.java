package com.project.bean.member;

import com.project.bean.goods.GoodsBean;
public class CollectionBean {
	private Integer collection_id;
	private Integer member_id;//用户id
	private String collection_ids;
	private Integer goods_id;
	private String create_time;//
	private String update_time;//
	private String is_delete;//
	private GoodsBean goodsBean;
	private MemberBean memberBean;
	public Integer getGoods_id() {
		return goods_id;
	}
	public CollectionBean setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
		return this;
	}
	public Integer getCollection_id() {
		return collection_id;
	}
	public CollectionBean setCollection_id(Integer collection_id) {
		this.collection_id = collection_id;
		return this;
	}
	public String getCollection_ids() {
		return collection_ids;
	}
	public CollectionBean setCollection_ids(String collection_ids) {
		this.collection_ids = collection_ids;
		return this;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public CollectionBean setMember_id(Integer member_id) {
		this.member_id = member_id;
		return this;
	}
	public String getCreate_time() {
		return create_time;
	}
	public CollectionBean setCreate_time(String create_time) {
		this.create_time = create_time;
		return this;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public CollectionBean setUpdate_time(String update_time) {
		this.update_time = update_time;
		return this;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public CollectionBean setIs_delete(String is_delete) {
		this.is_delete = is_delete;
		return this;
	}
	public GoodsBean getGoodsBean() {
		return goodsBean;
	}
	public CollectionBean setGoodsBean(GoodsBean goodsBean) {
		this.goodsBean = goodsBean;
		return this;
	}
	public MemberBean getMemberBean() {
		return memberBean;
	}
	public CollectionBean setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
		return this;
	}
	
}
