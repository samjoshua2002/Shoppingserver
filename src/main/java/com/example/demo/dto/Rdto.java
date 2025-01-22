package com.example.demo.dto;

import java.util.Date;

public class Rdto {
    private String username;
    private String comment;
    private Date date; 

    // Constructor
    public Rdto(String username, String comment, Date date) {
        this.username = username;
        this.comment = comment;
        this.date = date;
    }

    // Getters and Setters
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

    public Rdto() {
        super();
    }
}