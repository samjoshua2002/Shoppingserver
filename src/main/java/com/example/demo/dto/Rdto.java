package com.example.demo.dto;

public class Rdto {
	    private String username;
	    private String comment;

	    // Constructor
	    public Rdto(String username, String comment) {
	        this.username = username;
	        this.comment = comment;
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

		public Rdto() {
			super();
			
		}
	    
	    
	}

