package com.loungeapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "DLN1")
@IdClass(OrderItemKey.class)
public class DeliveryFooter {

	@Id
	@Column(name = "DocEntry")
	private int docEntry; // Delivery Header No. With Which this Delivery
							// Details is associated

	@Id
	@Column(name = "LineNum")
	private int lineNum;

	@Column(name = "BaseEntry")
	private int baseEntry;

	@Column(name = "LineStatus")
	private String lineStatus;

	@Column(name = "ItemCode")
	private String itemCode;

	@Column(name = "Dscription")
	private String dscription;

	@Column(name = "Quantity")
	private float quantity;

	@Column(name = "ShipDate")
	private Date shipDate;

	@Column(name = "OpenQty")
	private float openQty;

	@Column(name = "Price")
	private float price;

	@Column(name = "Currency")
	private String currency;

	@Column(name = "Rate")
	private float rate;

	@Column(name = "LineTotal")
	private float lineTotal;

	@Column(name = "DocDate")
	private Date docDate;

	@Column(name = "ShipToCode")
	private String shipToCode;

	@Column(name = "ShipToDesc")
	private String shipToDesc;

	@Column(name = "QtyToShip")
	private float qtyToShip;

	@Column(name = "DelivrdQty")
	private float delivrdQty;

	@Column(name = "OrderedQty")
	private float orderedQty;

	public DeliveryFooter() {
	}

	public int getDocEntry() {
		return docEntry;
	}

	public void setDocEntry(int docEntry) {
		this.docEntry = docEntry;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public int getBaseEntry() {
		return baseEntry;
	}

	public void setBaseEntry(int baseEntry) {
		this.baseEntry = baseEntry;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDscription() {
		return dscription;
	}

	public void setDscription(String dscription) {
		this.dscription = dscription;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public float getOpenQty() {
		return openQty;
	}

	public void setOpenQty(float openQty) {
		this.openQty = openQty;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public float getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(float lineTotal) {
		this.lineTotal = lineTotal;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getShipToCode() {
		return shipToCode;
	}

	public void setShipToCode(String shipToCode) {
		this.shipToCode = shipToCode;
	}

	public String getShipToDesc() {
		return shipToDesc;
	}

	public void setShipToDesc(String shipToDesc) {
		this.shipToDesc = shipToDesc;
	}

	public float getQtyToShip() {
		return qtyToShip;
	}

	public void setQtyToShip(float qtyToShip) {
		this.qtyToShip = qtyToShip;
	}

	public float getDelivrdQty() {
		return delivrdQty;
	}

	public void setDelivrdQty(float delivrdQty) {
		this.delivrdQty = delivrdQty;
	}

	public float getOrderedQty() {
		return orderedQty;
	}

	public void setOrderedQty(float orderedQty) {
		this.orderedQty = orderedQty;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DeliveryFooter [docEntry=");
		builder.append(docEntry);
		builder.append(", lineNum=");
		builder.append(lineNum);
		builder.append(", baseEntry=");
		builder.append(baseEntry);
		builder.append(", lineStatus=");
		builder.append(lineStatus);
		builder.append(", itemCode=");
		builder.append(itemCode);
		builder.append(", dscription=");
		builder.append(dscription);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", shipDate=");
		builder.append(shipDate);
		builder.append(", openQty=");
		builder.append(openQty);
		builder.append(", price=");
		builder.append(price);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", rate=");
		builder.append(rate);
		builder.append(", lineTotal=");
		builder.append(lineTotal);
		builder.append(", docDate=");
		builder.append(docDate);
		builder.append(", shipToCode=");
		builder.append(shipToCode);
		builder.append(", shipToDesc=");
		builder.append(shipToDesc);
		builder.append(", qtyToShip=");
		builder.append(qtyToShip);
		builder.append(", delivrdQty=");
		builder.append(delivrdQty);
		builder.append(", orderedQty=");
		builder.append(orderedQty);
		builder.append("]");
		return builder.toString();
	}

}
