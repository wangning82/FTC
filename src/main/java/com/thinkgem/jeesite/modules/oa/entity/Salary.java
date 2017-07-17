/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.oa.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 工资Entity
 * @author wangqignxiang
 * @version 2017-07-17
 */
public class Salary extends DataEntity<Salary> {
	
	private static final long serialVersionUID = 1L;
	private Integer year;		// 年
	private Integer month;		// 月
	private User user;		// 用户id
	private BigDecimal basicWage;		// 岗位工资
	private BigDecimal performanceWage;		// 绩效工资
	private BigDecimal degreeSubsidy;		// 学历津贴
	private BigDecimal ageSubsidy;		// 工龄津贴
	private BigDecimal busSubsidy;		// 通勤补助
	private BigDecimal foodSubsidy;		// 餐费补助
	private BigDecimal realPerformanceWage;		// 应发绩效
	private BigDecimal hotSubsidy;		// 防暑降温
	private BigDecimal totalSubsidy;		// 津补贴合计
	private BigDecimal coldSubsidy;		// 冬季采暖
	private BigDecimal pensionInsurance;		// 养老保险
	private BigDecimal unempolyedInsurance;		// 失业保险
	private BigDecimal medicalInsurance;		// 医疗保险
	private BigDecimal incomeTax;		// 个人所得税
	private BigDecimal pInsurance;		// 保险
	private BigDecimal pBond;		// 公积金扣除
	private BigDecimal shoudPay;		// 应发
	private BigDecimal realPay;		// 实发
	private BigDecimal addWage;		// 补发工资
	private BigDecimal reward;		// 奖金
	private BigDecimal deductWork;		// 出勤扣除
	private BigDecimal deductLeave;		// 病/事假扣除
	private BigDecimal bondInterest;		// 公积金贷款利息
	private BigDecimal other1;		// 其他1
	private BigDecimal other2;		// 其他2
	private BigDecimal other3;		// 其他3
	private BigDecimal other4;		// 其他4
	private BigDecimal other5;		// 其他5
	private BigDecimal singleSubsidy;		// 独生子女费
	private BigDecimal score;		// 绩效得分
	private String status;		// 状态
	
	public Salary() {
		super();
	}

	public Salary(String id){
		super(id);
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public BigDecimal getBasicWage() {
		return basicWage;
	}

	public void setBasicWage(BigDecimal basicWage) {
		this.basicWage = basicWage;
	}
	
	public BigDecimal getPerformanceWage() {
		return performanceWage;
	}

	public void setPerformanceWage(BigDecimal performanceWage) {
		this.performanceWage = performanceWage;
	}
	
	public BigDecimal getDegreeSubsidy() {
		return degreeSubsidy;
	}

	public void setDegreeSubsidy(BigDecimal degreeSubsidy) {
		this.degreeSubsidy = degreeSubsidy;
	}
	
	public BigDecimal getAgeSubsidy() {
		return ageSubsidy;
	}

	public void setAgeSubsidy(BigDecimal ageSubsidy) {
		this.ageSubsidy = ageSubsidy;
	}
	
	public BigDecimal getBusSubsidy() {
		return busSubsidy;
	}

	public void setBusSubsidy(BigDecimal busSubsidy) {
		this.busSubsidy = busSubsidy;
	}
	
	public BigDecimal getFoodSubsidy() {
		return foodSubsidy;
	}

	public void setFoodSubsidy(BigDecimal foodSubsidy) {
		this.foodSubsidy = foodSubsidy;
	}
	
	public BigDecimal getRealPerformanceWage() {
		return realPerformanceWage;
	}

	public void setRealPerformanceWage(BigDecimal realPerformanceWage) {
		this.realPerformanceWage = realPerformanceWage;
	}
	
	public BigDecimal getHotSubsidy() {
		return hotSubsidy;
	}

	public void setHotSubsidy(BigDecimal hotSubsidy) {
		this.hotSubsidy = hotSubsidy;
	}
	
	public BigDecimal getTotalSubsidy() {
		return totalSubsidy;
	}

	public void setTotalSubsidy(BigDecimal totalSubsidy) {
		this.totalSubsidy = totalSubsidy;
	}
	
	public BigDecimal getColdSubsidy() {
		return coldSubsidy;
	}

	public void setColdSubsidy(BigDecimal coldSubsidy) {
		this.coldSubsidy = coldSubsidy;
	}
	
	public BigDecimal getPensionInsurance() {
		return pensionInsurance;
	}

	public void setPensionInsurance(BigDecimal pensionInsurance) {
		this.pensionInsurance = pensionInsurance;
	}
	
	public BigDecimal getUnempolyedInsurance() {
		return unempolyedInsurance;
	}

	public void setUnempolyedInsurance(BigDecimal unempolyedInsurance) {
		this.unempolyedInsurance = unempolyedInsurance;
	}
	
	public BigDecimal getMedicalInsurance() {
		return medicalInsurance;
	}

	public void setMedicalInsurance(BigDecimal medicalInsurance) {
		this.medicalInsurance = medicalInsurance;
	}
	
	public BigDecimal getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(BigDecimal incomeTax) {
		this.incomeTax = incomeTax;
	}
	
	public BigDecimal getPInsurance() {
		return pInsurance;
	}

	public void setPInsurance(BigDecimal pInsurance) {
		this.pInsurance = pInsurance;
	}
	
	public BigDecimal getPBond() {
		return pBond;
	}

	public void setPBond(BigDecimal pBond) {
		this.pBond = pBond;
	}
	
	public BigDecimal getShoudPay() {
		return shoudPay;
	}

	public void setShoudPay(BigDecimal shoudPay) {
		this.shoudPay = shoudPay;
	}
	
	public BigDecimal getRealPay() {
		return realPay;
	}

	public void setRealPay(BigDecimal realPay) {
		this.realPay = realPay;
	}
	
	public BigDecimal getAddWage() {
		return addWage;
	}

	public void setAddWage(BigDecimal addWage) {
		this.addWage = addWage;
	}
	
	public BigDecimal getReward() {
		return reward;
	}

	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}
	
	public BigDecimal getDeductWork() {
		return deductWork;
	}

	public void setDeductWork(BigDecimal deductWork) {
		this.deductWork = deductWork;
	}
	
	public BigDecimal getDeductLeave() {
		return deductLeave;
	}

	public void setDeductLeave(BigDecimal deductLeave) {
		this.deductLeave = deductLeave;
	}
	
	public BigDecimal getBondInterest() {
		return bondInterest;
	}

	public void setBondInterest(BigDecimal bondInterest) {
		this.bondInterest = bondInterest;
	}
	
	public BigDecimal getOther1() {
		return other1;
	}

	public void setOther1(BigDecimal other1) {
		this.other1 = other1;
	}
	
	public BigDecimal getOther2() {
		return other2;
	}

	public void setOther2(BigDecimal other2) {
		this.other2 = other2;
	}
	
	public BigDecimal getOther3() {
		return other3;
	}

	public void setOther3(BigDecimal other3) {
		this.other3 = other3;
	}
	
	public BigDecimal getOther4() {
		return other4;
	}

	public void setOther4(BigDecimal other4) {
		this.other4 = other4;
	}
	
	public BigDecimal getOther5() {
		return other5;
	}

	public void setOther5(BigDecimal other5) {
		this.other5 = other5;
	}
	
	public BigDecimal getSingleSubsidy() {
		return singleSubsidy;
	}

	public void setSingleSubsidy(BigDecimal singleSubsidy) {
		this.singleSubsidy = singleSubsidy;
	}
	
	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}