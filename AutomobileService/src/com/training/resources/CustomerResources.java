package com.training.resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.training.entity.Customer;
import com.training.utils.CustomerDaoImpl;

@Path("customer")
public class CustomerResources {
	
	CustomerDaoImpl customerDao = null;
	
	public CustomerResources() {
		super();
		customerDao = new CustomerDaoImpl();
	}
	@POST
	@Path("addCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public int addCustomer(Customer cust){
		
		System.out.println(cust);
		int rowsAdded = customerDao.addNewCustomer(cust);
		return rowsAdded;
	}
	@GET
	@Path("retrieveDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer retrieveDetails(@QueryParam("mobileNumber")String mobileNumber){
		System.out.println("abc2");
		//Customer cust = new Customer("abc",mobileNumber);
		Customer cust = customerDao.retrieveCustomerDetails(mobileNumber);
		return cust;
	}
	
	@GET
	@Path("deleteCust")
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteCustomer(@QueryParam("mobileNumber")String mobileNumber){
		
		System.out.println(mobileNumber);
		int rowsDeleted = customerDao.deleteCustomer(mobileNumber);
		return rowsDeleted;
	}
	@POST
	@Path("updateCust")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomer(Customer cust) {
		System.out.println(cust);
		int rowsUpdated = customerDao.updateCustDetails(cust);
		
	}
}
