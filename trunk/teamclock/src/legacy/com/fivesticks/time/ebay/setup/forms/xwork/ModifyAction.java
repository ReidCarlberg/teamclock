/*
 * Created on Jun 5, 2005
 *
 */
package com.fivesticks.time.ebay.setup.forms.xwork;

import java.util.Collection;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.ebay.setup.forms.EbayForm;
import com.fivesticks.time.ebay.setup.forms.EbayFormServiceDelegate;
import com.fivesticks.time.ebay.setup.forms.util.FormType;

/**
 * @author Reid
 *  
 */
public class ModifyAction extends SessionContextAwareSupport implements
        ModifyContextAware {

    private ModifyContext modifyContext;

    private Long target;

    private String submitForm;

    private String cancelForm;

    private String deleteForm;

    private String copyForm;

    private EbayForm targetForm;

    public String execute() throws Exception {

        if (this.getCancelForm() != null)
            return SUCCESS;

        if (this.getCopyForm() != null
                && this.getModifyContext().getTarget() != null) {
            this.getModifyContext().setTarget(null);
            return INPUT;
        }

        if (this.getDeleteForm() != null
                && this.getModifyContext().getTarget() != null) {
            throw new RuntimeException("delete not implemented yet.");
        }

        EbayFormServiceDelegate sd = EbayFormServiceDelegate.factory.build(this
                .getSystemOwner());

        if (this.getTarget() != null) {
            this.setTargetForm(sd.load(this.getTarget()));
            this.getModifyContext().setTarget(this.getTargetForm());
            return INPUT;
        } else if (this.getSubmitForm() == null) {
            this.setTargetForm(new EbayForm());
            this.getModifyContext().setTarget(null);
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        if (this.getModifyContext().getTarget() != null) {
            this.getTargetForm().setId(
                    this.getModifyContext().getTarget().getId());
        }
        
        sd.save(this.getTargetForm());

        return SUCCESS;
    }

    public void validate() {

        ValidationHelper helper = new ValidationHelper();

        if (helper.isStringEmpty(this.getTargetForm().getType())) {
            this.addFieldError("targetForm.type", "Type is required.");
        }

        if (helper.isStringEmpty(this.getTargetForm().getName())) {
            this.addFieldError("targetForm.name", "Name is required.");
        }

        if (helper.isStringEmpty(this.getTargetForm().getLinebreak())) {
            this.addFieldError("targetForm.linebreak", "Linebreak is required.");
        }
        
        if (helper.isStringEmpty(this.getTargetForm().getForm())) {
            this.addFieldError("targetForm.form", "Form is required.");
        }

    }

    /**
     * @return Returns the cancelForm.
     */
    public String getCancelForm() {
        return cancelForm;
    }

    /**
     * @param cancelForm
     *            The cancelForm to set.
     */
    public void setCancelForm(String cancelForm) {
        this.cancelForm = cancelForm;
    }

    /**
     * @return Returns the copyForm.
     */
    public String getCopyForm() {
        return copyForm;
    }

    /**
     * @param copyForm
     *            The copyForm to set.
     */
    public void setCopyForm(String copyForm) {
        this.copyForm = copyForm;
    }

    /**
     * @return Returns the deleteForm.
     */
    public String getDeleteForm() {
        return deleteForm;
    }

    /**
     * @param deleteForm
     *            The deleteForm to set.
     */
    public void setDeleteForm(String deleteForm) {
        this.deleteForm = deleteForm;
    }

    /**
     * @return Returns the modifyContext.
     */
    public ModifyContext getModifyContext() {
        return modifyContext;
    }

    /**
     * @param modifyContext
     *            The modifyContext to set.
     */
    public void setModifyContext(ModifyContext modifyContext) {
        this.modifyContext = modifyContext;
    }

    /**
     * @return Returns the submitForm.
     */
    public String getSubmitForm() {
        return submitForm;
    }

    /**
     * @param submitForm
     *            The submitForm to set.
     */
    public void setSubmitForm(String submitForm) {
        this.submitForm = submitForm;
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
     * @return Returns the targetForm.
     */
    public EbayForm getTargetForm() {
        return targetForm;
    }

    /**
     * @param targetForm
     *            The targetForm to set.
     */
    public void setTargetForm(EbayForm targetForm) {
        this.targetForm = targetForm;
    }

    public Collection getFormTypes() {
        return FormType.getAll();
    }
}
