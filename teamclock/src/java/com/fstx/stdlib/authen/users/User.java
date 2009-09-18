package com.fstx.stdlib.authen.users;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fivesticks.time.common.IdReadable;

/** @author Hibernate CodeGenerator */
public class User implements Serializable, IdReadable {

    /** identifier field */
    private Long id;

    /** persistent field */
    private String username;

    /** persistent field */
    private String password;
    
    private String passwordHash;

    /** persistent field */
    private String email;

    private Integer inactive;
    
    private Date passwordExpires;
    
    private String resetKey;
    

//    /** full constructor */
//    public User(java.lang.String username, java.lang.String password,
//            java.lang.String email) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//    }

    /** default constructor */
    public User() {
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

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getPassword() {
        return this.password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public java.lang.String getEmail() {
        return this.email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof User))
            return false;
        User castOther = (User) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    /**
     * @return Returns the inactive.
     */
    public Integer getInactive() {
        return inactive;
    }

    /**
     * @param inactive
     *            The inactive to set.
     */
    public void setInactive(Integer inactive) {
        this.inactive = inactive;
    }

    public boolean isBooleanInactive() {
        return this.getInactive() != null && this.getInactive().intValue() == 1;
    }

    public void setBooleanInactive(boolean inactive) {
        if (inactive)
            this.setInactive(new Integer(1));
        else
            this.setInactive(new Integer(0));
    }
    /**
     * @return Returns the passwordExpires.
     */
    public Date getPasswordExpires() {
        return passwordExpires;
    }
    /**
     * @param passwordExpires The passwordExpires to set.
     */
    public void setPasswordExpires(Date passwordExpires) {
        this.passwordExpires = passwordExpires;
    }
    /**
     * @return Returns the passwordHash.
     */
    public String getPasswordHash() {
        return passwordHash;
    }
    /**
     * @param passwordHash The passwordHash to set.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * @return Returns the resetKey.
     */
    public String getResetKey() {
        return resetKey;
    }

    /**
     * @param resetKey The resetKey to set.
     */
    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }
}