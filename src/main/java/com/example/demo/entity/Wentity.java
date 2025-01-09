package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class Wentity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long wid;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="userid")
	private Aentity aentity;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id")
	private Pentity pentity;
	
	private boolean liked;

	public long getWid() {
		return wid;
	}

	public void setWid(long wid) {
		this.wid = wid;
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

	public boolean isLiked() {
		return liked;
	}

	public void setLiked(boolean liked) {
		this.liked = liked;
	}

	public Wentity(long wid, Aentity aentity, Pentity pentity, boolean liked) {
		super();
		this.wid = wid;
		this.aentity = aentity;
		this.pentity = pentity;
		this.liked = liked;
	}

	public Wentity() {
		super();
		
	}

	
	

}
