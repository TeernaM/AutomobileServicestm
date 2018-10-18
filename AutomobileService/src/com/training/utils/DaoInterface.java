package com.training.utils;

import com.training.entity.Customer;

public interface DaoInterface {
	
	public int addNewCustomer(Customer cust);
	public Customer retrieveCustomerDetails(String mobileNumber);
	public int deleteCustomer(String mobileNumber);
	public int updateCustDetails(Customer cust);

}
