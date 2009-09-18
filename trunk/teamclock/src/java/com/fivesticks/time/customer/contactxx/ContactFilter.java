/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.customer.contactxx;

import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author Reid
 */
public class ContactFilter extends Contact implements CriteriaParameters {

    private String nameFirstLike;
    private String nameLastLike;

    
    
    /**
     * @return Returns the nameLike.
     */
    public String getNameFirstLike() {
        return nameFirstLike;
    }
    /**
     * @param nameLike The nameLike to set.
     */
    public void setNameFirstLike(String nameLike) {
        this.nameFirstLike = nameLike;
    }
    
    
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.Contact#setAlternateEmail1(java.lang.String)
     */
    public void setAlternateEmail1(String alternateEmail1) {

        throw new RuntimeException("not implemented");
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.Contact#setAlternateEmail2(java.lang.String)
     */
    public void setAlternateEmail2(String alternateEmail2) {

        throw new RuntimeException("not implemented");
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.Contact#setAlternateEmail3(java.lang.String)
     */
    public void setAlternateEmail3(String alternateEmail3) {

        throw new RuntimeException("not implemented");
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.Contact#setAlternateEmail4(java.lang.String)
     */
    public void setAlternateEmail4(String alternateEmail4) {

        throw new RuntimeException("not implemented");
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.Contact#setAlternatePhone(java.lang.String)
     */
    public void setAlternatePhone(String alternatePhone) {

        throw new RuntimeException("not implemented");
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.Contact#setId(java.lang.Long)
     */
    public void setId(Long id) {

        throw new RuntimeException("not implemented");
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.Contact#setPrimaryEmail(java.lang.String)
     */
    public void setPrimaryEmail(String primaryEmail) {

        throw new RuntimeException("not implemented");
    }
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.contact.Contact#setPrimaryPhone(java.lang.String)
     */
    public void setPrimaryPhone(String primaryPhone) {

        throw new RuntimeException("not implemented");
    }
    public String getNameLastLike() {
        return nameLastLike;
    }
    public void setNameLastLike(String nameLastLike) {
        this.nameLastLike = nameLastLike;
    }
}
