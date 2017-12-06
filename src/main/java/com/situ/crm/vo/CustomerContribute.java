package com.situ.crm.vo;

public class CustomerContribute {

	/**
	 * 客户等级
	 */
	private String customerLevel;
	/**
	 * 客户数量
	 */
	private Integer customerNum;
	
	public CustomerContribute() {
		super();
	}

	public CustomerContribute(String customerLevel, Integer customerNum) {
		super();
		this.customerLevel = customerLevel;
		this.customerNum = customerNum;
	}

	public String getCustomerLevel() {
		return customerLevel;
	}

	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}

	public Integer getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(Integer customerNum) {
		this.customerNum = customerNum;
	}

	@Override
	public String toString() {
		return "CustomerConstitute [customerLevel=" + customerLevel + ", customerNum=" + customerNum + "]";
	}
	
}
