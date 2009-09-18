/*
 * Created on Jun 13, 2005
 *
 */
package com.fivesticks.time.superuser.xwork;

import java.util.Collection;

import com.fivesticks.time.common.util.GeographicCollection;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.systemowner.AccountType;
import com.fivesticks.time.systemowner.BillingFrequencyType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

/**
 * @author Reid
 * 
 */
public class SystemOwnerModifyAction extends SessionContextAwareSupport
        implements SystemOwnerManagerContextAware {

    private SystemOwnerManagerContext systemOwnerManagerContext;

    private SystemOwner targetOwner;

    private String target;

    private String cancelOwner;

    private String saveOwner;

    private boolean requiresAccountUpdate;
    /**
     * this is setup as modify only.
     * 
     */
    public String execute() throws Exception {

        if (this.getCancelOwner() != null)
            return SUCCESS;

        SystemOwnerServiceDelegate sd = SystemOwnerServiceDelegateFactory.factory
                .build();

        if (this.getSaveOwner() == null && this.getTarget() == null) {
            return SUCCESS;

        } else if (this.getTarget() != null) {
            SystemOwner o = sd.get(this.getTarget());
            this.setTargetOwner(o);
            this.getSystemOwnerManagerContext().setActiveSystemOwner(o);
            this.setRequiresAccountUpdate(o.isRequiresAccountUpdate());
            return INPUT;
        }

        validate();

        if (this.hasErrors())
            return INPUT;

        this.getTargetOwner().setId(
                this.getSystemOwnerManagerContext().getActiveSystemOwner()
                        .getId());
        this.getTargetOwner().setKey(
                this.getSystemOwnerManagerContext().getActiveSystemOwner()
                        .getKey());
        this.getTargetOwner().setActivated(
                this.getSystemOwnerManagerContext().getActiveSystemOwner()
                        .isActivated());
        
        this.getTargetOwner().setRequiresAccountUpdate(this.isRequiresAccountUpdate());

        sd.save(this.getTargetOwner());

        return SUCCESS;
    }

    public Collection getCountries() {
        return new GeographicCollection().getCountries();

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork.ActionSupport#validate()
     */
    @Override
    public void validate() {

        super.validate();
        
        AccountType at = AccountType.getByName(this.getTargetOwner().getAccount());
        
        if (at == null) {
            this.addFieldError("targetOwner.account", "Invalid Account Type.  'Demo', 'Time Clock Only' or 'Standard'");
        } else {
            BillingFrequencyType bt = BillingFrequencyType.getByName(this.getTargetOwner().getBillingFrequency());
            
            if (bt == null) {
                this.addFieldError("targetOwner.billingFrequency", "Billing frequency is either 'ANNUAL' or 'MONTHLY'" );
            }
        }
    }

    /**
     * @return Returns the systemOwnerManagerContext.
     */
    public SystemOwnerManagerContext getSystemOwnerManagerContext() {
        return systemOwnerManagerContext;
    }

    /**
     * @param systemOwnerManagerContext
     *            The systemOwnerManagerContext to set.
     */
    public void setSystemOwnerManagerContext(
            SystemOwnerManagerContext systemOwnerManagerContext) {
        this.systemOwnerManagerContext = systemOwnerManagerContext;
    }

    /**
     * @return Returns the targetOnwer.
     */
    public SystemOwner getTargetOwner() {
        return targetOwner;
    }

    /**
     * @param targetOnwer
     *            The targetOnwer to set.
     */
    public void setTargetOwner(SystemOwner targetOnwer) {
        this.targetOwner = targetOnwer;
    }

    /**
     * @return Returns the cancelOwner.
     */
    public String getCancelOwner() {
        return cancelOwner;
    }

    /**
     * @param cancelOwner
     *            The cancelOwner to set.
     */
    public void setCancelOwner(String cancelOwner) {
        this.cancelOwner = cancelOwner;
    }

    /**
     * @return Returns the saveOwner.
     */
    public String getSaveOwner() {
        return saveOwner;
    }

    /**
     * @param saveOwner
     *            The saveOwner to set.
     */
    public void setSaveOwner(String saveOwner) {
        this.saveOwner = saveOwner;
    }

    /**
     * @return Returns the target.
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isRequiresAccountUpdate() {
        return requiresAccountUpdate;
    }

    public void setRequiresAccountUpdate(boolean requiresAccountUpdate) {
        this.requiresAccountUpdate = requiresAccountUpdate;
    }
}
