package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity

public class Aentity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userid;
	
	private String username;
	private String useremail;
	private String userotp;
	private String userpassword;
	private long usernumber;
	
	@OneToMany(mappedBy = "aentity", cascade = CascadeType.ALL)
	@JsonBackReference
    private List<Wentity> wishlists = new ArrayList<>();

//	-----------------------
	public List<Wentity> getWishlists() {
		return wishlists;
	}
	
	public Aentity(List<Wentity> wishlists) {
	super();
	this.wishlists = wishlists;
	}
	
	public void setWishlists(List<Wentity> wishlists) {
		this.wishlists = wishlists;
	}
//	-----------------------
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserotp() {
		return userotp;
	}
	public void setUserotp(String userotp) {
		this.userotp = userotp;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public long getUsernumber() {
		return usernumber;
	}
	public void setUsernumber(long usernumber) {
		this.usernumber = usernumber;
	}
	
	public Aentity(long userid, String username, String useremail, String userotp, String userpassword,
			long usernumber) {
		super();
		this.userid = userid;
		this.username = username;
		this.useremail = useremail;
		this.userotp = userotp;
		this.userpassword = userpassword;
		this.usernumber = usernumber;
	}
	
	public Aentity() {
		super();
	}	

}
