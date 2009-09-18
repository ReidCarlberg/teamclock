/*
 * Created on Aug 31, 2006 by Reid
 */
package com.fivesticks.time.contactv2.xwork;

import com.fivesticks.time.contactv2.ContactV2CriteriaParameters;

public class ContactV2ListContext {

    private ContactV2CriteriaParameters params;
   
    private Boolean associate;

    public ContactV2CriteriaParameters getParams() {
        return params;
    }

    public void setParams(ContactV2CriteriaParameters params) {
        this.params = params;
    }

    /**
     * @return the associate
     */
    public Boolean getAssociate() {
        return associate;
    }

    /**
     * @param associate the associate to set
     */
    public void setAssociate(Boolean associate) {
        this.associate = associate;
    }



}
