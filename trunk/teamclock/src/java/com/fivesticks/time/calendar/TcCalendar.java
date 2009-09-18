package com.fivesticks.time.calendar;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fivesticks.time.common.AbstractTimeObject;

/** @author Hibernate CodeGenerator */
public class TcCalendar extends AbstractTimeObject implements Serializable {

//    /** identifier field */
//    private Long id;
//
//    private String ownerKey;

    /** persistent field */
    private String username;

    /** persistent field */
    private java.util.Date startDate;

    /** persistent field */
    private java.util.Date endDate;

    /** persistent field */
    private Long projectId;

    /** persistent field */
    private boolean publicViewable;

    /** nullable persistent field */
    private String description;

    private Boolean complete;

    private Long type;
    
    /** full constructor */
    public TcCalendar(java.lang.String username, java.util.Date startDate,
            java.util.Date endDate, java.lang.Long projectId,
            boolean publicViewable, java.lang.String description) {
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectId = projectId;
        this.publicViewable = publicViewable;
        this.description = description;

    }

    /** default constructor */
    public TcCalendar() {
    }

    /** minimal constructor */
    public TcCalendar(java.lang.String username, java.util.Date startDate,
            java.util.Date endDate, java.lang.Long projectId,
            boolean publicViewable) {
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectId = projectId;
        this.publicViewable = publicViewable;
    }

//    /**
//     * @return Returns the testShuji2.
//     */
//
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

    public java.util.Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }

    public java.lang.Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(java.lang.Long projectId) {
        this.projectId = projectId;
    }

    public boolean isPublicViewable() {
        return this.publicViewable;
    }

    public void setPublicViewable(boolean publicViewable) {
        this.publicViewable = publicViewable;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof TcCalendar))
            return false;
        TcCalendar castOther = (TcCalendar) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

//    /**
//     * @return Returns the ownKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownKey
//     *            The ownKey to set.
//     */
//    public void setOwnerKey(String ownKey) {
//        this.ownerKey = ownKey;
//    }

    /**
     * @return Returns the complete.
     */
    public Boolean getComplete() {
        return complete;
    }

    /**
     * @param complete
     *            The complete to set.
     */
    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
    /**
     * @return Returns the type.
     */
    public Long getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(Long type) {
        this.type = type;
    }
}