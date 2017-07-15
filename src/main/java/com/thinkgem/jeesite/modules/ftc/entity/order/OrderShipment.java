/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.order;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 订单配送Entity
 * @author houyi
 * @version 2017-05-30
 */
public class OrderShipment extends DataEntity<OrderShipment> {
	
	private static final long serialVersionUID = 1L;
	private Order order = new Order();		// 订单ID
	private String userName;		// 姓名
	private String userPhone;		// 手机号码
	private String provinceId;		// 省份ID
	private String provinceName;		// 省份名字
	private String cityId;		// 城市ID
	private String cityName;		// 城市名字
	private String districtId;		// 区域ID
	private String districtName;		// 区域名字
	private String userAdress;		// 详细地址
	private Integer userZipcode;		// 邮政编码
	
	public OrderShipment() {
		super();
	}

	public OrderShipment(String id){
		super(id);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=11, message="手机号码长度必须介于 0 和 11 之间")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	@Length(min=0, max=64, message="省份ID长度必须介于 0 和 64 之间")
	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	@Length(min=0, max=64, message="省份名字长度必须介于 0 和 64 之间")
	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Length(min=0, max=64, message="城市ID长度必须介于 0 和 64 之间")
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Length(min=0, max=64, message="城市名字长度必须介于 0 和 64 之间")
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	@Length(min=0, max=64, message="区域ID长度必须介于 0 和 64 之间")
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	@Length(min=0, max=64, message="区域名字长度必须介于 0 和 64 之间")
	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	@Length(min=0, max=255, message="详细地址长度必须介于 0 和 255 之间")
	public String getUserAdress() {
		return userAdress;
	}

	public void setUserAdress(String userAdress) {
		this.userAdress = userAdress;
	}
	
	public Integer getUserZipcode() {
		return userZipcode;
	}

	public void setUserZipcode(Integer userZipcode) {
		this.userZipcode = userZipcode;
	}
	
}