package com.loungeapp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ODLN")
public class DeliveryHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DocEntry")
	private int docEntry;

	@Column(name = "DocNum")
	private int docNum;

	@Column(name = "DocType")
	private String docType;

	@Column(name = "CANCELED")
	private String canceled;

	@Column(name = "DocStatus")
	private String docStatus;

	@Column(name = "DocDate")
	private Date docDate;

	@Column(name = "DocTime")
	private String docTime;

	@Column(name = "CardCode")
	private String cardCode;

	@Column(name = "CardName")
	private String cardName;

	@Column(name = "U_vehno")
	private String uVehno;

	@Column(name = "U_drname")
	private String uDrname;

	@Column(name = "CreateTS")
	private String createTS;

	@Column(name = "UpdateTS")
	private String updateTS;

	public DeliveryHeader() {
	}

	public DeliveryHeader(int docEntry, int docNum, String docType, String canceled, String docStatus, Date docDate,
			String docTime, String cardCode, String cardName, String uVehno, String uDrname, String createTS,
			String updateTS) {
		this.docEntry = docEntry;
		this.docNum = docNum;
		this.docType = docType;
		this.canceled = canceled;
		this.docStatus = docStatus;
		this.docDate = docDate;
		this.docTime = docTime;
		this.cardCode = cardCode;
		this.cardName = cardName;
		this.uVehno = uVehno;
		this.uDrname = uDrname;
		this.createTS = createTS;
		this.updateTS = updateTS;
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

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getDocTime() {
		return docTime;
	}

	public void setDocTime(String docTime) {
		this.docTime = docTime;
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

	public String getuVehno() {
		return uVehno;
	}

	public void setuVehno(String uVehno) {
		this.uVehno = uVehno;
	}

	public String getuDrname() {
		return uDrname;
	}

	public void setuDrname(String uDrname) {
		this.uDrname = uDrname;
	}

	public String getCreateTS() {
		return createTS;
	}

	public void setCreateTS(String createTS) {
		this.createTS = createTS;
	}

	public String getUpdateTS() {
		return updateTS;
	}

	public void setUpdateTS(String updateTS) {
		this.updateTS = updateTS;
	}

}
