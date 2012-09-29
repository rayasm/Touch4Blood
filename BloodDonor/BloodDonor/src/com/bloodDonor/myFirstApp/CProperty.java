package com.bloodDonor.myFirstApp;

public class CProperty {
	private long id;
	private String Name;
	private String bloodType;
	private String place;
	private String contact;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComment() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	
	public String getbloodType() {
		return bloodType;
	}

	public void setbloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
	public String getplace() {
		return place;
	}

	public void setplace(String place) {
		this.place = place;
	}
	
	public String getcontact() {
		return contact;
	}

	public void setcontact(String contact) {
		this.contact =  contact;
	}
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return Name;
	}
}
