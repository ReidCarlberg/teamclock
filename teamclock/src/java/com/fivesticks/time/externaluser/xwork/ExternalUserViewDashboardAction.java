/*
 * Created on Dec 21, 2004 by Reid
 */
package com.fivesticks.time.externaluser.xwork;

import com.fivesticks.time.menu.MenuSection;

/**
 * @author Reid
 */
public class ExternalUserViewDashboardAction extends AbstractExternalCustomerAction {

    

    public String execute() throws Exception {

        this.getSessionContext().setMenuSection(MenuSection.HOME);

        /*
         * these are all of the related customers.
         */
        if (this.getSessionContext().getExternalUserSessionContext()
                .getRelatedCustomers() == null) {
            handleInitializeRelatedCustomers();
        }

        /*
         * this would be the customer that their related by.
         */
        if (this.getSessionContext().getExternalUserSessionContext()
                .getActiveCustomer() == null) {
            return INPUT;
        }

        return SUCCESS;
    }



}