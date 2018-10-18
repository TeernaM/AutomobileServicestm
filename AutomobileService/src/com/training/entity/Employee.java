package com.training.entity;

public class Employee {
	
	private String employeeName;
	private String employeeId;
	private String password;
	public Employee() {
		super();
	}
	public Employee(String employeeName, String employeeId, String password) {
		super();
		this.employeeName = employeeName;
		this.employeeId = employeeId;
		this.password = password;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Employee [employeeName=" + employeeName + ", employeeId=" + employeeId + ", password=" + password + "]";
	}
	
	

}
