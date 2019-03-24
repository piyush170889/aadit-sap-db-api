package com.loungeapp.model;

import java.util.List;

import com.loungeapp.domain.OrderItems;

public class OrderDetailsWrapper {

	private int docEntry;

	private int docNum;

	private String numAtCard;

	private String cardCode;

	private String cardName;

	private String shipToCode;

	private List<OrderItems> orderItemsList;

	public OrderDetailsWrapper() {
	}

	public OrderDetailsWrapper(int docEntry, int docNum, String numAtCard, String cardCode, String cardName,
			String shipToCode, List<OrderItems> orderItemsList) {
		this.docEntry = docEntry;
		this.docNum = docNum;
		this.numAtCard = numAtCard;
		this.cardCode = cardCode;
		this.cardName = cardName;
		this.shipToCode = shipToCode;
		this.orderItemsList = orderItemsList;
	}

	public List<OrderItems> getOrderItemsList() {
		return orderItemsList;
	}

	public void setOrderItemsList(List<OrderItems> orderItemsList) {
		this.orderItemsList = orderItemsList;
	}

	public int getDocEntry() {
		return docEntry;
	}

	public void setDocEntry(int docEntry) {
		this.docEntry = docEntry;
	}

	public int getDocNum() {
		return docNum;
	}

	public void setDocNum(int docNum) {
		this.docNum = docNum;
	}

	public String getNumAtCard() {
		return numAtCard;
	}

	public void setNumAtCard(String numAtCard) {
		this.numAtCard = numAtCard;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getShipToCode() {
		return shipToCode;
	}

	public void setShipToCode(String shipToCode) {
		this.shipToCode = shipToCode;
	}

}
