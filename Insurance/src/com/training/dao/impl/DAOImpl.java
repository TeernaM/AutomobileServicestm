package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DAOImpl {
	Connection con;

	public DAOImpl() {
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource dataSource=(DataSource) ctx.lookup("java:comp/env/jdbc/ds1");
			con=dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public double getInsuranceAmt(String vehicleNo,String service) {
		double amt=0;
		double netAmt = 0.0;
		try {
			String sql="select insuranceAmount from insurancetm where vehicleNo=? and service=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, vehicleNo);
			ps.setString(2, service);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				amt=rs.getDouble("insuranceAmount");
				}
			netAmt = calcNetAmt(amt,service);
			} 
		catch (Exception e) {
				e.printStackTrace();
			}
		return netAmt;
	}

	private double calcNetAmt(double amt,String service) {
		
		PreparedStatement pstm = null;
		double servicePrice = 0.0;
		double netAmount = 0.0;
		String sql = "select servicePrice from servicetm where serviceName = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, service);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				servicePrice = rs.getDouble("servicePrice");
			}
			netAmount = servicePrice - amt;
			//System.out.println(netAmount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return netAmount;
	}
	
	public void logInsurance(String vehicleNum,String service) {
		PreparedStatement pstm = null;
		String customerId="";
		double amount=0;
		String sql = "select customerId from cartm where vehicleNo=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, vehicleNum);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				customerId = rs.getString("customerId");
			}
			amount=getInsuranceAmt(vehicleNum,service);
			System.out.println("About to Log");
			Logger log=Logger.getLogger("second");
			log.info("Customer Id:" +customerId + " Vehicle Number: "+vehicleNum +" Service:"+service +" Amount:"+ amount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
