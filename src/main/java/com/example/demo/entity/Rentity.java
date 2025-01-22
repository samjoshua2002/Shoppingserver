package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Rentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userid")
    private Aentity aentity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id")
    private Pentity pentity;

    private String comment;

    @Temporal(TemporalType.DATE) 
    private Date date;

    // Getters and Setters
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Constructors
    public Rentity(long rid, Aentity aentity, Pentity pentity, String comment, Date date) {
        super();
        this.rid = rid;
        this.aentity = aentity;
        this.pentity = pentity;
        this.comment = comment;
        this.date = date;
    }

    public Rentity() {
        super();
    }
}