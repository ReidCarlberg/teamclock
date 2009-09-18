/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission.xwork;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.setup.commission.SimpleCommission;
import com.fivesticks.time.ebay.setup.commission.SimpleCommissionServiceDelegate;

/**
 * @author Reid
 *  
 */
public class ModifyAction extends SessionContextAwareSupport implements
        ModifyContextAware {

    private ModifyContext modifyContext;

    private SimpleCommission targetCommission;

    private Long target;

    private String saveCommission;

    private String cancelCommission;
    
    private String copyCommission;

    private String deleteCommission;

    public String execute() throws Exception {

        if (this.getCancelCommission() != null)
            return SUCCESS;

        SimpleCommissionServiceDelegate sd = SimpleCommissionServiceDelegate.factory
                .build(this.getSystemOwner());

        if (this.getModifyContext().getTarget() != null
                && this.getDeleteCommission() != null
                && this.getConfirm() != null) {
            sd.delete(this.getModifyContext().getTarget());
            return this.getSuccess();
        }
        
        if (this.getModifyContext().getTarget() != null && this.getCopyCommission() != null) {
            this.setTargetCommission(this.getModifyContext().getTarget());
            this.getModifyContext().setTarget(null);
            this.getTargetCommission().setId(null);
            return INPUT;
        } 
        
        if (this.getTarget() != null) {
            this.setTargetCommission(sd.get(this.getTarget()));
            this.getModifyContext().setTarget(this.getTargetCommission());
            return INPUT;
        } else if (this.getSaveCommission() == null) {
            this.setTargetCommission(new SimpleCommission());
            this.getModifyContext().setTarget(null);
            return INPUT;
        }
        
        validate();
        
        if (this.hasErrors()) {
            return INPUT;
        }

        if (this.getModifyContext().getTarget() != null)
            this.getTargetCommission().setId(this.getModifyContext().getTarget().getId());
        
        sd.save(this.getTargetCommission());
        
        return SUCCESS;
    }

    public void validate() {
        ValidationHelper vh = new ValidationHelper();
        
        vh.validateNonEmpty(this.getTargetCommission().getName(), this, "targetCommission.name", "Name is required.");
        
    }
    /**
     * @return Returns the cancelCommission.
     */
    public String getCancelCommission() {
        return cancelCommission;
    }

    /**
     * @param cancelCommission
     *            The cancelCommission to set.
     */
    public void setCancelCommission(String cancelCommission) {
        this.cancelCommission = cancelCommission;
    }

    /**
     * @return Returns the deleteCommission.
     */
    public String getDeleteCommission() {
        return deleteCommission;
    }

    /**
     * @param deleteCommission
     *            The deleteCommission to set.
     */
    public void setDeleteCommission(String deleteCommission) {
        this.deleteCommission = deleteCommission;
    }

    /**
     * @return Returns the saveCommission.
     */
    public String getSaveCommission() {
        return saveCommission;
    }

    /**
     * @param saveCommission
     *            The saveCommission to set.
     */
    public void setSaveCommission(String saveCommission) {
        this.saveCommission = saveCommission;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }

    /**
     * @return Returns the targetCommission.
     */
    public SimpleCommission getTargetCommission() {
        return targetCommission;
    }

    /**
     * @param targetCommission
     *            The targetCommission to set.
     */
    public void setTargetCommission(SimpleCommission targetCommission) {
        this.targetCommission = targetCommission;
    }

    /**
     * @return Returns the modifyActionContext.
     */
    public ModifyContext getModifyContext() {
        return modifyContext;
    }

    /**
     * @param modifyActionContext
     *            The modifyActionContext to set.
     */
    public void setModifyContext(ModifyContext modifyActionContext) {
        this.modifyContext = modifyActionContext;
    }
    /**
     * @return Returns the copyCommission.
     */
    public String getCopyCommission() {
        return copyCommission;
    }
    /**
     * @param copyCommission The copyCommission to set.
     */
    public void setCopyCommission(String copyCommission) {
        this.copyCommission = copyCommission;
    }
}
