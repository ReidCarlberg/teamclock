package com.fivesticks.time.timeclock;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fivesticks.time.common.AbstractTimeObject;

/** @author Hibernate CodeGenerator */
public class Timeclock extends AbstractTimeObject implements Serializable {

//    /** identifier field */
//    private Long id;
//
//    private String ownerKey;

//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownerKey
//     *            The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }

    /** persistent field */
    private String username;

    /** persistent field */
    private java.util.Date timestamp;

    /** persistent field */
    private String event;

    /** nullable persistent field */
    private java.util.Date eventTimestamp;

    /** nullable persistent field */
    private String comment;
    
    private String sourceip;

    public String getSourceip() {
        return sourceip;
    }

    public void setSourceip(String sourceip) {
        this.sourceip = sourceip;
    }

    /** full constructor */
    public Timeclock(java.lang.String username, java.util.Date timestamp,
            java.lang.String event, java.util.Date eventTimestamp,
            java.lang.String comment) {
        this.username = username;
        this.timestamp = timestamp;
        this.event = event;
        this.eventTimestamp = eventTimestamp;
        this.comment = comment;
    }

    /** default constructor */
    public Timeclock() {
    }

    /** minimal constructor */
    public Timeclock(java.lang.String username, java.util.Date timestamp,
            java.lang.String event) {
        this.username = username;
        this.timestamp = timestamp;
        this.event = event;
    }

//    public java.lang.Long getId() {
//        return this.id;
//    }
//
//    public void setId(java.lang.Long id) {
//        this.id = id;
//    }

    public java.lang.String getUsername() {
        return this.username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.util.Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }

    public java.lang.String getEvent() {
        return this.event;
    }

    public void setEvent(java.lang.String event) {
        this.event = event;
    }

    public java.util.Date getEventTimestamp() {
        return this.eventTimestamp;
    }

    public void setEventTimestamp(java.util.Date eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public java.lang.String getComment() {
        return this.comment;
    }

    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Timeclock))
            return false;
        Timeclock castOther = (Timeclock) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

}