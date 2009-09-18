/*
 * Created on Jan 6, 2007
 *
 */
package com.fivesticks.time.contactv2.xwork;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;

public class ContactV2AssociateAction extends SessionContextAwareSupport
        implements CustomerModifyContextAware, ContactV2ListContextAware {

    private CustomerModifyContext customerModifyContext;
    
    private ContactV2ListContext contactV2ListContext;

    private Long targetContactId;

    public String execute() throws Exception {

        if (this.getCustomerModifyContext().getTargetCustomer() != null
                && this.getTargetContactId() != null) {

            ContactV2ServiceDelegate cv2sd = ContactV2ServiceDelegateFactory.factory
                    .build(this.getSessionContext());

            ContactV2 contactV2 = cv2sd.get(this.getTargetContactId());

            cv2sd.associate(contactV2, this.getCustomerModifyContext()
                    .getTargetCustomer());
            
            this.getContactV2ListContext().setAssociate(null);
            
        }

        return SUCCESS;
    }

    /**
     * @return the customerModifyContext
     */
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    /**
     * @param customerModifyContext
     *            the customerModifyContext to set
     */
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    /**
     * @return the target
     */
    public Long getTargetContactId() {
        return targetContactId;
    }

    /**
     * @param target
     *            the target to set
     */
    public void setTargetContactId(Long target) {
        this.targetContactId = target;
    }

    /**
     * @return the contactV2ListContext
     */
    public ContactV2ListContext getContactV2ListContext() {
        return contactV2ListContext;
    }

    /**
     * @param contactV2ListContext the contactV2ListContext to set
     */
    public void setContactV2ListContext(ContactV2ListContext contactV2ListContext) {
        this.contactV2ListContext = contactV2ListContext;
    }

}
