/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2.xwork;

import com.fivesticks.time.register.v2.RegistrationPlan;
import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.payments.PaymentMethod;
import com.fstx.stdlib.authen.users.User;

public class RegistrationContext {

    private RegistrationPlan plan;
    
    private SystemOwner systemOwner = new SystemOwner();
    private User user = new User();
    private FstxTimeSettings settings = new FstxTimeSettings();
    private PaymentMethod paymentInformation = new PaymentMethod();
    
    private boolean billingSameAsContact; 
    
    public RegistrationContext() {
        super();
    }
    
    /*
     * used mostly for testing...
     */
    public RegistrationContext(RegistrationPlan plan,
            SystemOwner systemOwner, User user, FstxTimeSettings settings, PaymentMethod paymentInformation) {
        this.plan = plan;
        this.systemOwner = systemOwner;
        this.user = user;
        this.settings = settings;
        this.paymentInformation = paymentInformation;
    }
    
    /**
     * @return Returns the paymentInformation.
     */
    public PaymentMethod getPaymentInformation() {
        return paymentInformation;
    }
    /**
     * @return Returns the settings.
     */
    public FstxTimeSettings getSettings() {
        return settings;
    }
    /**
     * @return Returns the systemOwner.
     */
    public SystemOwner getSystemOwner() {
        return systemOwner;
    }
    /**
     * @return Returns the user.
     */
    public User getUser() {
        return user;
    }
    
    public void reset() {
        systemOwner = new SystemOwner();
        user = new User();
        settings = new FstxTimeSettings();
        paymentInformation = new PaymentMethod();
    }
    /**
     * @return Returns the plan.
     */
    public RegistrationPlan getPlan() {
        return plan;
    }
    /**
     * @param plan The plan to set.
     */
    public void setPlan(RegistrationPlan plan) {
        this.plan = plan;
    }

    /**
     * @return Returns the billingSameAsContact.
     */
    public boolean isBillingSameAsContact() {
        return billingSameAsContact;
    }

    /**
     * @param billingSameAsContact The billingSameAsContact to set.
     */
    public void setBillingSameAsContact(boolean billingSameAsContact) {
        this.billingSameAsContact = billingSameAsContact;
    }


}
