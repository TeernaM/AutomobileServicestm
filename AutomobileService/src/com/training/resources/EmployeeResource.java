package com.training.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("/employee")
public class EmployeeResource {
	
	Connection con;
	public EmployeeResource() {
		super();
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource dataSource=(DataSource) ctx.lookup("java:comp/env/jdbc/ds1");
			con=dataSource.getConnection();
		
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}
	@POST
	public String employeeValidate(@FormParam("employeeId")String employeeId, @FormParam("empPassword")String empPassword) {
		String result="";
		
		String sql = "select * from employeetm where employeeId = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employeeId);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			if(rs.next()){
				System.out.println(employeeId);
				if(empPassword.equals(rs.getString("empPassword"))){
				String employeeName = rs.getString("employeeName");
				result = employeeName;
				}else{
					result = "invalid password";
				}
			}else{
				result = "Invalid employeeId";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	@POST
	@Path("add")
	public String registerEmp(@FormParam("empNameR") String employeeName,@FormParam("empPassR") String password) {
		String empid="";
		try {
			String sql="insert into employeetm values(seqm_empid.nextval,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, employeeName);
			ps.setString(2, password);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String sql="select employeeId from employeetm where employeeName=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, employeeName);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				empid=rs.getString("employeeId");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empid;
	}

	
}
