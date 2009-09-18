package com.fivesticks.time.customer;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fivesticks.time.common.AbstractTimeObject;

/** @author Hibernate CodeGenerator */
public class Project extends AbstractTimeObject implements Serializable {
    //shuji sep.1 2004 modified private to protected to try extend this class
    // for ProjectWrapper
//    /** identifier field */
//    protected Long id;
//
//    protected String ownerKey;

    /** persistent field */
    protected Long custId;

    /** persistent field */
    protected String shortName;

    /** persistent field */
    protected String longName;

    /** persistent field */
    protected boolean active;

    protected Boolean postable;
    
    private Long projectClassId;

    /** full constructor */
    public Project(java.lang.Long custId, java.lang.String shortName,
            java.lang.String longName, boolean active) {
        this.custId = custId;

        this.shortName = shortName;
        this.longName = longName;
        this.active = active;
    }

    /** default constructor */
    public Project() {
    }

//    public java.lang.Long getId() {
//        return this.id;
//    }
//
//    public void setId(java.lang.Long id) {
//        this.id = id;
//    }

    public java.lang.Long getCustId() {
        return this.custId;
    }

    public void setCustId(java.lang.Long custId) {
        this.custId = custId;
    }

    public java.lang.String getShortName() {
        return this.shortName;
    }

    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }

    public java.lang.String getLongName() {
        return this.longName;
    }

    public void setLongName(java.lang.String longName) {
        this.longName = longName;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Project))
            return false;
        Project castOther = (Project) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

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

    /**
     * @return Returns the postable.
     */
    public Boolean isPostable() {
        return postable;
    }

    public Boolean getPostable() {
        return postable;
    }
    /**
     * @param postable
     *            The postable to set.
     */
    public void setPostable(Boolean postable) {
        this.postable = postable;
    }

    /**
     * @return Returns the projectClassId.
     */
    public Long getProjectClassId() {
        return projectClassId;
    }

    /**
     * @param projectClassId The projectClassId to set.
     */
    public void setProjectClassId(Long projectClassId) {
        this.projectClassId = projectClassId;
    }
}