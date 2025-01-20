package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Rentity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rid;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="userid")
	private Aentity aentity;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id")
	private Pentity pentity;
	
	private String comment;

	private int rating;

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Rentity(long rid, Aentity aentity, Pentity pentity, String comment, int rating) {
		super();
		this.rid = rid;
		this.aentity = aentity;
		this.pentity = pentity;
		this.comment = comment;
		this.rating = rating;
	}

	public Rentity() {
		super();
	
	}

}
