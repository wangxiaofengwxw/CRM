package com.situ.crm.vo;

public class CustomerService {

	private String serviceType;
	private Integer serviceTotal;
	
	public CustomerService() {
		super();
	}

	public CustomerService(String serviceType, Integer serviceTotal) {
		super();
		this.serviceType = serviceType;
		this.serviceTotal = serviceTotal;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getServiceTotal() {
		return serviceTotal;
	}

	public void setServiceTotal(Integer serviceTotal) {
		this.serviceTotal = serviceTotal;
	}

	@Override
	public String toString() {
		return "CustomerService [serviceType=" + serviceType + ", serviceTotal=" + serviceTotal + "]";
	}
	
}
