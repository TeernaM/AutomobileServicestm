package com.training.entity;

public class Car {

	private String vehicleNo;
	private boolean polishing;
	private boolean wheelBalancing;
	private boolean decors;
	public Car() {
		super();
	}
	public Car(String vehicleNo, boolean polishing, boolean wheelBalancing, boolean decors) {
		super();
		this.vehicleNo = vehicleNo;
		this.polishing = polishing;
		this.wheelBalancing = wheelBalancing;
		this.decors = decors;
	}
	
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public boolean isPolishing() {
		return polishing;
	}
	public void setPolishing(boolean polishing) {
		this.polishing = polishing;
	}
	public boolean isWheelBalancing() {
		return wheelBalancing;
	}
	public void setWheelBalancing(boolean wheelBalancing) {
		this.wheelBalancing = wheelBalancing;
	}
	public boolean isDecors() {
		return decors;
	}
	public void setDecors(boolean decors) {
		this.decors = decors;
	}
	@Override
	public String toString() {
		return "Car [vehicleNo=" + vehicleNo + ", polishing=" + polishing + ", wheelBalancing=" + wheelBalancing
				+ ", decors=" + decors + "]";
	}
	
	
}
