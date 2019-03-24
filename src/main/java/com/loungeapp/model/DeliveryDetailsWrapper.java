package com.loungeapp.model;

import java.util.Date;

public class DeliveryDetailsWrapper {

	private int deliveryChallanNo;

	private int orderNo;

	private String itemCode;

	private String itemDesc;

	private float qty;

	private Date date;

	private String time;

	private String vehicleNo;

	private String driverName;

	public DeliveryDetailsWrapper() {
	}

	public DeliveryDetailsWrapper(int deliveryChallanNo, int orderNo, String itemCode, String itemDesc, float qty,
			Date date, String time, String vehicleNo, String driverName) {
		this.deliveryChallanNo = deliveryChallanNo;
		this.orderNo = orderNo;
		this.itemCode = itemCode;
		this.itemDesc = itemDesc;
		this.qty = qty;
		this.date = date;
		this.time = time;
		this.vehicleNo = vehicleNo;
		this.driverName = driverName;
	}

	public int getDeliveryChallanNo() {
		return deliveryChallanNo;
	}

	public void setDeliveryChallanNo(int deliveryChallanNo) {
		this.deliveryChallanNo = deliveryChallanNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public float getQty() {
		return qty;
	}

	public void setQty(float qty) {
		this.qty = qty;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

}
