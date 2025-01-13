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
	
	private String size;

	private int quantity;

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Centity(long cid, Aentity aentity, Pentity pentity, String size, int quantity) {
		super();
		this.cid = cid;
		this.aentity = aentity;
		this.pentity = pentity;
		this.size = size;
		this.quantity = quantity;
	}

	public Centity() {
		super();
		
	}
	
	
	
	
}