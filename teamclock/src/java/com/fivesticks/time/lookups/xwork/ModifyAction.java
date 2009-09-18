/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups.xwork;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.lookups.Lookup;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;

/**
 * @author Reid
 */
public class ModifyAction extends SessionContextAwareSupport implements
        ListContextAware, ModifyContextAware {

    private ListContext listContext;
    
    private ModifyContext modifyContext;
    
    private Long target;
    
    private String cancelLookup;
    private String saveLookup;
    private String copyLookup;
    private String deleteLookup;
    
    private Lookup targetLookup;
    
    public String execute() throws Exception {
        
        if (this.getCancelLookup() != null)
            return this.getSuccess();
        
        if (this.getDeleteLookup() != null && this.getModifyContext().getTarget() != null) {
            
        }
        
        if (this.getCopyLookup() != null && this.getModifyContext().getTarget() != null) {
            this.setTargetLookup(this.getModifyContext().getTarget());
            this.getModifyContext().setTarget(null);
            this.getTargetLookup().setId( null);
            return INPUT;
        }
        
        LookupServiceDelegate sd= LookupServiceDelegateFactory.factory.build(this.getSystemOwner());
        
        if (this.getTarget() != null && this.getTarget().longValue() > 0) {
            Lookup t = sd.get(this.getTarget());
            if (t != null) {
                this.setTargetLookup(t);
                this.getModifyContext().setTarget(t);
            }
            return INPUT;
        }
        
        if (this.getSaveLookup() == null) {
            this.getModifyContext().setTarget(null);
            this.setTargetLookup(new Lookup());
            return INPUT;
        }
        
        validate();
        
        if (this.hasErrors())
            return INPUT;
        
        //save
        if (this.getModifyContext().getTarget() != null) {
            this.getTargetLookup().setId( this.getModifyContext().getTarget().getId());
        }
        
        this.getTargetLookup().setType(this.getListContext().getTargetType().getName());
        
        sd.save(this.getTargetLookup());
        
        return this.getSuccess();
    }

    public void validate() {
        ValidationHelper vh = new ValidationHelper();
        
        if (vh.isStringEmpty(this.getTargetLookup().getShortName())) {
            this.addFieldError("targetLookup.shortName", "Short name is required.");
        }
        
        if (vh.isStringEmpty(this.getTargetLookup().getFullName())) {
            this.addFieldError("targetLookup.fullName", "Full name is required.");
        }
    }
    
    /**
     * @return Returns the cancelLookup.
     */
    public String getCancelLookup() {
        return cancelLookup;
    }
    /**
     * @param cancelLookup The cancelLookup to set.
     */
    public void setCancelLookup(String cancelLookup) {
        this.cancelLookup = cancelLookup;
    }
    /**
     * @return Returns the copyLookup.
     */
    public String getCopyLookup() {
        return copyLookup;
    }
    /**
     * @param copyLookup The copyLookup to set.
     */
    public void setCopyLookup(String copyLookup) {
        this.copyLookup = copyLookup;
    }
    /**
     * @return Returns the deleteLookup.
     */
    public String getDeleteLookup() {
        return deleteLookup;
    }
    /**
     * @param deleteLookup The deleteLookup to set.
     */
    public void setDeleteLookup(String deleteLookup) {
        this.deleteLookup = deleteLookup;
    }
    /**
     * @return Returns the listContext.
     */
    public ListContext getListContext() {
        return listContext;
    }
    /**
     * @param listContext The listContext to set.
     */
    public void setListContext(ListContext listContext) {
        this.listContext = listContext;
    }
    /**
     * @return Returns the modifyContext.
     */
    public ModifyContext getModifyContext() {
        return modifyContext;
    }
    /**
     * @param modifyContext The modifyContext to set.
     */
    public void setModifyContext(ModifyContext modifyContext) {
        this.modifyContext = modifyContext;
    }
    /**
     * @return Returns the saveLookup.
     */
    public String getSaveLookup() {
        return saveLookup;
    }
    /**
     * @param saveLookup The saveLookup to set.
     */
    public void setSaveLookup(String saveLookup) {
        this.saveLookup = saveLookup;
    }
    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }
    /**
     * @param target The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }
    /**
     * @return Returns the targetLookup.
     */
    public Lookup getTargetLookup() {
        return targetLookup;
    }
    /**
     * @param targetLookup The targetLookup to set.
     */
    public void setTargetLookup(Lookup targetLookup) {
        this.targetLookup = targetLookup;
    }
}
