package com.training.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.training.entity.Car;
import com.training.entity.Customer;

public class CustomerDaoImpl implements DaoInterface{
	
	private Connection con;
	
	public CustomerDaoImpl() {
		super();

		try {
			Context ctx = new InitialContext();
			
			DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/ds1");
			
			try {
				 con = dataSource.getConnection();
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public int addNewCustomer(Customer cust) {
		
		int rowsAdded = 0;
		List<Car>carList = new ArrayList<>();
		String sql = "insert into customerstm values("+"seqtm_customerId.nextval"+",?,?)";
		PreparedStatement ps = null;
		PreparedStatement pstm = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cust.getCustomerName());
			ps.setString(2, cust.getMobileNumber());
			rowsAdded = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql1 = "insert into cartm values("+"seqtm_customerId.currval"+",?)";
		try {
			ps = con.prepareStatement(sql1);
			carList = cust.getCarList();
			for (Car car : carList) {
				ps.setString(1, car.getVehicleNo());
				rowsAdded = ps.executeUpdate();
				String sql2 = "insert into vehicleServicestm values("+"seqtm_serviceId.nextval"+",?,?)";
				try{
				pstm = con.prepareStatement(sql2);
				pstm.setString(1, car.getVehicleNo());
				if(car.isPolishing()){
					pstm.setString(2, "polishing");
					rowsAdded = pstm.executeUpdate();
				}
				if(car.isWheelBalancing()){
					pstm.setString(2, "wheelBalancing");
					rowsAdded = pstm.executeUpdate();
				}
				if(car.isDecors()){
					pstm.setString(2, "decors");
					rowsAdded = pstm.executeUpdate();
				}
				
				}catch(SQLException e){
					e.printStackTrace();
				}
				
				finally{
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
									
			}
			
		}
	} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
		
		return rowsAdded;	
	}

	@Override
	public Customer retrieveCustomerDetails(String mobileNumber) {
		
		Customer cust = new Customer();
		String sql = "select * from customerstm cust natural join cartm where mobileNumber = ?";
		PreparedStatement ps  = null;
		String vehicleNo = "";
		Set<String>vehicleNoList = null;
		 vehicleNoList = new HashSet<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mobileNumber);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String customerName = rs.getString("customerName");
				cust.setCustomerName(customerName);
				cust.setMobileNumber(mobileNumber);
				vehicleNo = rs.getString("vehicleNo");
				vehicleNoList.add(vehicleNo);				
			}
				List<Car>carList = retrieveServiceDetails(vehicleNoList);
				cust.setCarList(carList);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}

	private List<Car> retrieveServiceDetails(Set<String> vehicleNoList) {
		
		System.out.println("abc");
		
		List<Car>carList ;
		carList = new ArrayList<>();
		PreparedStatement pstm = null;
		String sql1 = "Select * from vehicleServicestm where vehicleNo = ?";
		try {
			pstm = con.prepareStatement(sql1);
			for (String vehicleNumber : vehicleNoList) {
				Car car = new Car();
				pstm.setString(1, vehicleNumber);
				ResultSet rs = pstm.executeQuery();
				while(rs.next()){
					
					String serviceName = rs.getString("serviceName");
					//System.out.println(serviceName);
					if(serviceName.equals("polishing")){
						System.out.println("polishing");
						car.setPolishing(true);
					}
					
					if(serviceName.equals("wheelBalancing")){
						System.out.println("wheelBalancing");
						car.setWheelBalancing(true);
					}
					
					if(serviceName.equals("decors")){
						System.out.println("decors");
						car.setDecors(true);
					}
				
				}
				car.setVehicleNo(vehicleNumber);
				carList.add(car);
			}
			System.out.println(carList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carList;
	}

	@Override
	public int deleteCustomer(String mobileNumber) {
		
		PreparedStatement pstm = null;
		int rowsDeleted = 0;
		String sql = "delete from customerstm where mobileNumber = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, mobileNumber);
			rowsDeleted = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsDeleted;
	}

	@Override
	public int updateCustDetails(Customer cust) {
		
		int rowsUpdated = addNewCustomer(cust);
		
		return rowsUpdated;
	}
	
	
}