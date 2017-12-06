package com.situ.crm.vo;

public class CustomerConstitute {

	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 客户贡献总额
	 */
	private Integer customerTotal;
	
	public CustomerConstitute() {
		super();
	}

	public CustomerConstitute(String customerName, Integer customerTotal) {
		super();
		this.customerName = customerName;
		this.customerTotal = customerTotal;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCustomerTotal() {
		return customerTotal;
	}

	public void setCustomerTotal(Integer customerTotal) {
		this.customerTotal = customerTotal;
	}

	@Override
	public String toString() {
		return "CustomerConstitute [customerName=" + customerName + ", customerTotal=" + customerTotal + "]";
	}
	
}
