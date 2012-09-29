package com.bloodDonor.ConnectServer;
import java.io.Serializable;

public class User  implements Serializable {

 private static final long serialVersionUID = 7390103290165670089L;
	private Long id;
	private String name;
	private String place;
	private String Bloodtype;
	private String phone;
	private String userid;
	private String application;
	private String lat;
	private String lon;

 public User() {

 }

 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getname() {
	    return name;
	}

	public void setname(String name) {
	    this.name = name;
	}

	
	public String getplace() {
	    return place;
	}

	public void setplace(String place) {
	    this.place = place;
	}
	
	public String getPhone() {
	    return phone;
	}

	public void setPhone(String phone) {
	    this.phone = phone;
	}
	
	public void setBloodtype(String Bloodtype) {
		this.Bloodtype = Bloodtype;
	}

	public String getBloodtype() {
		return Bloodtype;
	}
	
	public String getuserid() {
	    return userid;
	}

	public void setuserid(String userid) {
	    this.userid= userid;
	}

	
	public String getapplication() {
	    return application;
	}

	public void setapplication(String application) {
	    this.application = application;
	}
	
	public String getlat() {
	    return lat;
	}

	public void setlat(String lat) {
	    this.lat = lat;
	}
	
	public String getlon() {
	    return lon;
	}

	public void setlon(String lon) {
	    this.lon = lon;
	}
	
	

}


