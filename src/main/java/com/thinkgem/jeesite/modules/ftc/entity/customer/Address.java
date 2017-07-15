/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.customer;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;

/**
 * 收货地址Entity
 * @author houyi
 * @version 2017-06-19
 */
public class Address extends DataEntity<Address> {
	
	private static final long serialVersionUID = 1L;
	private Customer customer = new Customer();		// 用户ID
	private String userName;  // 收件人姓名
	private String userTag;		// 地址标签
	private String userPhone;		// 手机号码
	private Area province = new Area();		// 省份ID
	private Area city = new Area();		// 城市ID
	private Area district = new Area();		// 区域ID
	private String userAdress;		// 详细地址
	private String userZipcode;		// 邮政编码
	private String isDefault;  // 是否默认

	public Address() {
		super();
	}

	public Address(String id){
		super(id);
	}

	@Length(min=0, max=64, message="地址标签长度必须介于 0 和 64 之间")
	public String getUserTag() {
		return userTag;
	}

	public void setUserTag(String userTag) {
		this.userTag = userTag;
	}
	
	@Length(min=0, max=11, message="手机号码长度必须介于 0 和 11 之间")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public Area getProvince() {
		return province;
	}

	public void setProvince(Area province) {
		this.province = province;
	}

	public Area getCity() {
		return city;
	}

	public void setCity(Area city) {
		this.city = city;
	}
	
	public Area getDistrict() {
		return district;
	}

	public void setDistrict(Area district) {
		this.district = district;
	}
	
	@Length(min=0, max=255, message="详细地址长度必须介于 0 和 255 之间")
	public String getUserAdress() {
		return userAdress;
	}

	public void setUserAdress(String userAdress) {
		this.userAdress = userAdress;
	}
	
	@Length(min=0, max=6, message="邮政编码长度必须介于 0 和 6 之间")
	public String getUserZipcode() {
		return userZipcode;
	}

	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}