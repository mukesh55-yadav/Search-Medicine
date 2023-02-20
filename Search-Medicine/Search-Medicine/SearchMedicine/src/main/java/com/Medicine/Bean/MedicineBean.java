package com.Medicine.Bean;

public class MedicineBean extends BaseBean{

	private String companyName;
	private String medicineName;
	private long quantity;
	private long medicineid;
	private long userid;
	
	
	public long getMedicineid() {
		return medicineid;
	}
	public void setMedicineid(long medicineid) {
		this.medicineid = medicineid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
}
