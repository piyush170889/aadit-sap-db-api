package com.loungeapp.model;

import java.util.Set;

public class SAPDORequestWrapper {

	private String currentDate;
	
	private Set<Integer> orderIds;
	
	private Set<String> itemCodes;
	
	public SAPDORequestWrapper() {}
	
	public SAPDORequestWrapper(String currentDate, Set<Integer> orderIds, Set<String> itemCodes) {
		this.currentDate = currentDate;
		this.orderIds = orderIds;
		this.itemCodes = itemCodes;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public Set<Integer> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(Set<Integer> orderIds) {
		this.orderIds = orderIds;
	}

	public Set<String> getItemCodes() {
		return itemCodes;
	}

	public void setItemCodes(Set<String> itemCodes) {
		this.itemCodes = itemCodes;
	}
	

}
