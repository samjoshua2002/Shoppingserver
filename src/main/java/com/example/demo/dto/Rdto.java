package com.example.demo.dto;

import java.util.Date;

public class Rdto {
    private String username;
    private String comment;
    private Date date; 
    private long id;

   

    public Rdto(String username, String comment, Date date, long id) {
		super();
		this.username = username;
		this.comment = comment;
		this.date = date;
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Rdto() {
        super();
    }
}