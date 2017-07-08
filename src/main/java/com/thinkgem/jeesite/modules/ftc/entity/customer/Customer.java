/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ftc.entity.customer;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 会员Entity
 * @author houyi
 * @version 2017-06-04
 */
public class Customer extends DataEntity<Customer> {
	
	private static final long serialVersionUID = 1L;
	private String userCode;		// 用户编号
	private String userName;		// 昵称
	private String loginPassword;		// 登录密码
	private String salt;		// 加密密码的盐
	private String realName;	// 真实姓名
	private Integer sex;		// 性别
	private Integer age;		// 年龄
	private String picImg;		// 用户头像
	private String status;		// 状态
	private String emailIsActive;		// 邮箱激活
	private String email;		// 电子邮箱
	private String telephone;		// 手机号码
	private String signature;  // 用户签名
	private String shopName;    // 店铺名称
	private String shopBackground; // 店铺背景图
	private Date lastLoginTime;		// 最后登录时间
	private String lastLoginIp;		// 最后登录IP
	private Long loginNumber;		// 登录次数
	private Date registerTime;		// 注册时间
	private BigDecimal billBlance;		// 账户余额
	private BigDecimal amount;		// 消费额
	private String rankId;		// 会员等级ID
	private BigDecimal score;		// 会员积分
	private String qq;		// qq
	private String wechat;		// wechat
	private Integer wishlistNumber; // 关注人数

	private String accessToken; // 令牌
	private Date expiresTime; // 令牌有效时间
	
	public Customer() {
		super();
	}

	public Customer(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户编号长度必须介于 0 和 64 之间")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	@Length(min=0, max=30, message="昵称长度必须介于 0 和 30 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=32, message="登录密码长度必须介于 0 和 32 之间")
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	
	@Length(min=0, max=20, message="加密密码的盐长度必须介于 0 和 20 之间")
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Length(min=0, max=20, message="真实姓名长度必须介于 0 和 20 之间")
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Length(min=0, max=255, message="用户头像长度必须介于 0 和 255 之间")
	public String getPicImg() {
		return picImg;
	}

	public void setPicImg(String picImg) {
		this.picImg = picImg;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=1, message="邮箱激活长度必须介于 0 和 1 之间")
	public String getEmailIsActive() {
		return emailIsActive;
	}

	public void setEmailIsActive(String emailIsActive) {
		this.emailIsActive = emailIsActive;
	}
	
	@Length(min=0, max=50, message="电子邮箱长度必须介于 0 和 50 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=11, message="手机号码长度必须介于 0 和 11 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Length(min=0, max=20, message="最后登录IP长度必须介于 0 和 20 之间")
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	
	public Long getLoginNumber() {
		return loginNumber;
	}

	public void setLoginNumber(Long loginNumber) {
		this.loginNumber = loginNumber;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public BigDecimal getBillBlance() {
		return billBlance;
	}

	public void setBillBlance(BigDecimal billBlance) {
		this.billBlance = billBlance;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=64, message="会员等级ID长度必须介于 0 和 64 之间")
	public String getRankId() {
		return rankId;
	}

	public void setRankId(String rankId) {
		this.rankId = rankId;
	}
	
	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	@Length(min=0, max=255, message="qq长度必须介于 0 和 255 之间")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Length(min=0, max=255, message="wechat长度必须介于 0 和 255 之间")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	@Length(min=0, max=100, message="shopName长度必须介于 0 和 100 之间")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getShopBackground() {
		return shopBackground;
	}

	public void setShopBackground(String shopBackground) {
		this.shopBackground = shopBackground;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(Date expiresTime) {
		this.expiresTime = expiresTime;
	}

	public Integer getWishlistNumber() {
		return wishlistNumber;
	}

	public void setWishlistNumber(Integer wishlistNumber) {
		this.wishlistNumber = wishlistNumber;
	}
}