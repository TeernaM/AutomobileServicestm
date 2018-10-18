package com.training.entity;

import java.util.List;

public class Customer {
	
	private String customerName;
	private int customerId;
	private String mobileNumber;
	private List<Car>carList;
	public Customer() {
		super();
	}
	public Customer(String customerName, int customerId, String mobileNumber, List<Car> carList) {
		super();
		this.customerName = customerName;
		this.customerId = customerId;
		this.mobileNumber = mobileNumber;
		this.carList = carList;
	}
	public Customer(String customerName, String mobileNumber, List<Car> carList) {
		super();
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.carList = carList;
	}
	
	 
	public Customer(String customerName, String mobileNumber) {
		super();
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public List<Car> getCarList() {
		return carList;
	}
	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", mobileNumber=" + mobileNumber + ", carList=" + carList
				+ "]";
	}
	
		

}
