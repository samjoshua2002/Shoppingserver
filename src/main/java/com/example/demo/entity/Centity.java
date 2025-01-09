package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Centity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cid;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="userid")
	private Aentity aentity;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id")
	private Pentity pentity;

	public long getWid() {
		return cid;
	}

	public void setWid(long cid) {
		this.cid = cid;
	}

	public Aentity getAentity() {
		return aentity;
	}

	public void setAentity(Aentity aentity) {
		this.aentity = aentity;
	}

	public Pentity getPentity() {
		return pentity;
	}

	public void setPentity(Pentity pentity) {
		this.pentity = pentity;
	}

	public Centity(long cid, Aentity aentity, Pentity pentity) {
		super();
		this.cid = cid;
		this.aentity = aentity;
		this.pentity = pentity;
	}

	public Centity() {
		super();
		
	}
	

	
	

}
