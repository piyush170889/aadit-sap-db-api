package com.loungeapp.model;

import java.util.List;

import com.loungeapp.domain.OITM;
import com.loungeapp.domain.Orders;

public class SAPDOResponseWrapper {

	private List<OITM> itemDetails;

	private List<Orders> orderDetails;

	public SAPDOResponseWrapper() {
	}

	public SAPDOResponseWrapper(List<OITM> itemDetails, List<Orders> orderDetails) {
		this.itemDetails = itemDetails;
		this.orderDetails = orderDetails;
	}

	public List<OITM> getItemDetails() {
		return itemDetails;
	}

	public void setItemDetails(List<OITM> itemDetails) {
		this.itemDetails = itemDetails;
	}

	public List<Orders> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<Orders> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
