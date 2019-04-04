package com.loungeapp.model;

public class DispatchOrderMgmtTo {

	private String dispatchDate;
	
	private String dispatchTime;
	
	private int orderId;
	
	private Float dispatchQty;
	
	private String itemCode;
	
	private String itemName;
	
	public DispatchOrderMgmtTo() {}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(String dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Float getDispatchQty() {
		return dispatchQty;
	}

	public void setDispatchQty(Float dispatchQty) {
		this.dispatchQty = dispatchQty;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DispatchOrderMgmtTo [dispatchDate=");
		builder.append(dispatchDate);
		builder.append(", dispatchTime=");
		builder.append(dispatchTime);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", dispatchQty=");
		builder.append(dispatchQty);
		builder.append(", itemCode=");
		builder.append(itemCode);
		builder.append(", itemName=");
		builder.append(itemName);
		builder.append("]");
		return builder.toString();
	}
	
	
}
