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
	private Float quantity;

	@Column(name = "ShipDate")
	private Date shipDate;

	@Column(name = "OpenQty")
	private Float openQty;

	@Column(name = "Price")
	private Float price;

	@Column(name = "Currency")
	private String currency;

	@Column(name = "Rate")
	private Float rate;

	@Column(name = "LineTotal")
	private Float lineTotal;

	@Column(name = "DocDate")
	private Date docDate;

	@Column(name = "ShipToCode")
	private String shipToCode;

	@Column(name = "ShipToDesc")
	private String shipToDesc;

	@Column(name = "QtyToShip")
	private Float qtyToShip;

	@Column(name = "DelivrdQty")
	private Float delivrdQty;

	@Column(name = "OrderedQty")
	private Float orderedQty;

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

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public Float getOpenQty() {
		return openQty;
	}

	public void setOpenQty(Float openQty) {
		this.openQty = openQty;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public Float getLineTotal() {
		return lineTotal;
	}

	public void setLineTotal(Float lineTotal) {
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

	public Float getQtyToShip() {
		return qtyToShip;
	}

	public void setQtyToShip(Float qtyToShip) {
		this.qtyToShip = qtyToShip;
	}

	public Float getDelivrdQty() {
		return delivrdQty;
	}

	public void setDelivrdQty(Float delivrdQty) {
		this.delivrdQty = delivrdQty;
	}

	public Float getOrderedQty() {
		return orderedQty;
	}

	public void setOrderedQty(Float orderedQty) {
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
