package com.loungeapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OCRD")
public class Customers {

	@Id
	@Column(name = "CardCode")
	private String cardCode;

	@Column(name = "CardName")
	private String cardName;

	@Column(name = "CardType")
	private String cardType;

	@Column(name = "GroupCode")
	private int groupCode;

	@Column(name = "CmpPrivate")
	private String cmpPrivate;

	@Column(name = "Phone1")
	private String phone1;

	@Column(name = "Phone2")
	private String phone2;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "CntctPrsn")
	private String cntctPrsn;

	@Column(name = "Notes")
	private String notes;

	@Column(name = "Balance")
	private float balance;

	@Column(name = "ChecksBal")
	private float checksBal;

	@Column(name = "DNotesBal")
	private float dNotesBal;

	@Column(name = "OrdersBal")
	private float ordersBal;

	@Column(name = "GroupNum")
	private int groupNum;

	public Customers() {
	}

	public Customers(String cardCode, String cardName, String cardType, int groupCode, String cmpPrivate, String phone1,
			String phone2, String fax, String cntctPrsn, String notes, float balance, float checksBal, float dNotesBal,
			float ordersBal, int groupNum) {
		this.cardCode = cardCode;
		this.cardName = cardName;
		this.cardType = cardType;
		this.groupCode = groupCode;
		this.cmpPrivate = cmpPrivate;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.fax = fax;
		this.cntctPrsn = cntctPrsn;
		this.notes = notes;
		this.balance = balance;
		this.checksBal = checksBal;
		this.dNotesBal = dNotesBal;
		this.ordersBal = ordersBal;
		this.groupNum = groupNum;
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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(int groupCode) {
		this.groupCode = groupCode;
	}

	public String getCmpPrivate() {
		return cmpPrivate;
	}

	public void setCmpPrivate(String cmpPrivate) {
		this.cmpPrivate = cmpPrivate;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCntctPrsn() {
		return cntctPrsn;
	}

	public void setCntctPrsn(String cntctPrsn) {
		this.cntctPrsn = cntctPrsn;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getChecksBal() {
		return checksBal;
	}

	public void setChecksBal(float checksBal) {
		this.checksBal = checksBal;
	}

	public float getdNotesBal() {
		return dNotesBal;
	}

	public void setdNotesBal(float dNotesBal) {
		this.dNotesBal = dNotesBal;
	}

	public float getOrdersBal() {
		return ordersBal;
	}

	public void setOrdersBal(float ordersBal) {
		this.ordersBal = ordersBal;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customers [cardCode=");
		builder.append(cardCode);
		builder.append(", cardName=");
		builder.append(cardName);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", groupCode=");
		builder.append(groupCode);
		builder.append(", cmpPrivate=");
		builder.append(cmpPrivate);
		builder.append(", phone1=");
		builder.append(phone1);
		builder.append(", phone2=");
		builder.append(phone2);
		builder.append(", fax=");
		builder.append(fax);
		builder.append(", cntctPrsn=");
		builder.append(cntctPrsn);
		builder.append(", notes=");
		builder.append(notes);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", checksBal=");
		builder.append(checksBal);
		builder.append(", dNotesBal=");
		builder.append(dNotesBal);
		builder.append(", ordersBal=");
		builder.append(ordersBal);
		builder.append(", groupNum=");
		builder.append(groupNum);
		builder.append("]");
		return builder.toString();
	}

	
}
