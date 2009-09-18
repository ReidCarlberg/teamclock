package com.fivesticks.time.customer;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fivesticks.time.common.AbstractTimeObject;

/** @author Hibernate CodeGenerator */
public class Task extends AbstractTimeObject implements Serializable {

//    /** identifier field */
//    private Long id;
//
//    private String ownerKey;

    /** persistent field */
    private String nameShort;

    /** persistent field */
    private String nameLong;

    /** full constructor */
    public Task(java.lang.String shortName, java.lang.String longName) {

        this.nameShort = shortName;
        this.nameLong = longName;
    }

    /** default constructor */
    public Task() {
    }

//    public java.lang.Long getId() {
//        return this.id;
//    }
//
//    public void setId(java.lang.Long id) {
//        this.id = id;
//    }

    public java.lang.String getNameShort() {
        return this.nameShort;
    }

    public void setNameShort(java.lang.String shortName) {
        this.nameShort = shortName;
    }

    public java.lang.String getNameLong() {
        return this.nameLong;
    }

    public void setNameLong(java.lang.String longName) {
        this.nameLong = longName;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Task))
            return false;
        Task castOther = (Task) other;
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
}