package com.fstx.stdlib.authen;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class LoginHistory implements Serializable {

    private static final SimpleDateFormat sdf = new SimpleDateFormat(
            "MM/dd/yyyy hh:mm:ss");

    /** identifier field */
    private Long id;

    private String ownerKey;

    /** persistent field */
    private String username;

    /** persistent field */
    private java.util.Date timestamp;

    /** persistent field */
    private String type;

    private String location;

    /** full constructor */
    public LoginHistory(java.lang.String userName, java.util.Date timestamp,
            java.lang.String type) {
        this.username = userName;
        this.timestamp = timestamp;
        this.type = type;
    }

    /** default constructor */
    public LoginHistory() {
    }

    public java.lang.Long getId() {
        return this.id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public java.lang.String getUsername() {
        return this.username;
    }

    public void setUsername(java.lang.String userName) {
        this.username = userName;
    }

    public java.util.Date getTimestamp() {
        return this.timestamp;
    }

    public String getFormattedTimestamp() {
        return sdf.format(this.getTimestamp());
    }

    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }

    public java.lang.String getType() {
        return this.type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof LoginHistory))
            return false;
        LoginHistory castOther = (LoginHistory) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    /**
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param string
     */
    public void setLocation(String string) {
        location = string;
    }

    /**
     * @return Returns the ownerKey.
     */
    public String getOwnerKey() {
        return ownerKey;
    }

    /**
     * @param ownerKey
     *            The ownerKey to set.
     */
    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }
}