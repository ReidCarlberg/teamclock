/*
 * Created on Sep 3, 2006
 *
 */
package com.fivesticks.time.contactv2.forms.xwork;

import java.util.Collection;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.forms.WebForm;
import com.fivesticks.time.contactv2.forms.WebFormServiceDelegate;
import com.fivesticks.time.contactv2.forms.WebFormServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.lookups.LookupType;
import com.fivesticks.time.messages.MessageServiceDelegateFactory;

public class WebFormModifyAction extends SessionContextAwareSupport implements
        WebFormModifyContextAware {

    private WebFormModifyContext webFormModifyContext;

    private Collection contactClassifications;

    private Collection contactSources;

    private Collection contactInterests;

    private Collection messages;

    private String saveForm;

    private String cancelForm;

    private String deleteForm;

    private boolean formIsActive;

    private boolean formIsSendNotification;

    private boolean formIsSendThankYou;

    private Long target;

    private WebForm webForm;

    public String execute() throws Exception {

        if (StringUtils.hasText(this.getCancelForm())) {
            return SUCCESS;
        }

        WebFormServiceDelegate wsd = WebFormServiceDelegateFactory.factory
                .build(this.getSessionContext());

        /*
         * setup the form
         */
        
        this.setMessages(MessageServiceDelegateFactory.factory.build(this.getSystemOwner()).findAll());
        
        LookupServiceDelegate lsd = LookupServiceDelegateFactory.factory.build(this.getSystemOwner());
        
        this.setContactClassifications(lsd.getAll(LookupType.CONTACT_CLASS));
        
        this.setContactInterests(lsd.getAll(LookupType.CONTACT_INTEREST));
        
        this.setContactSources(lsd.getAll(LookupType.CONTACT_SOURCE));
        
        if (this.getTarget() != null) {
            WebForm webForm = wsd.get(this.getTarget());
            if (webForm != null) {
                this.getWebFormModifyContext().setTarget(webForm);
                this.webForm = webForm;
                this.setFormIsActive(webForm.isActive());
                this.setFormIsSendNotification(webForm.isSendNotification());
                this.setFormIsSendThankYou(webForm.isSendThankYouMessage());
                return INPUT;
            }
        } else if (StringUtils.hasText(this.getDeleteForm())) {

            WebFormDeleteCommand dc = new WebFormDeleteCommand(this
                    .getWebFormModifyContext().getTarget());
            DeleteContext deleteContext = DeleteContextFactory.factory
                    .build(dc);

            this.getSessionContext().setDeleteContext(deleteContext);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;

        } else if (!StringUtils.hasText(this.getSaveForm())) {

            this.setWebForm(new WebForm());
            this.getWebFormModifyContext().setTarget(null);
            return INPUT;

        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        if (this.getWebFormModifyContext().getTarget() != null) {
            this.getWebForm().setId(
                    this.getWebFormModifyContext().getTarget().getId());

            this.getWebForm().setKey(this.getWebFormModifyContext().getTarget().getKey());
            
            this.getWebFormModifyContext().setTarget(null);
        }

        wsd.save(this.getWebForm());

        return SUCCESS;
    }

    /**
     * @return Returns the webFormModifyContext.
     */
    public WebFormModifyContext getWebFormModifyContext() {
        return webFormModifyContext;
    }

    /**
     * @param webFormModifyContext
     *            The webFormModifyContext to set.
     */
    public void setWebFormModifyContext(
            WebFormModifyContext webFormModifyContext) {
        this.webFormModifyContext = webFormModifyContext;
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
     * @return Returns the contactClassifications.
     */
    public Collection getContactClassifications() {
        return contactClassifications;
    }

    /**
     * @param contactClassifications
     *            The contactClassifications to set.
     */
    public void setContactClassifications(Collection contactClassifications) {
        this.contactClassifications = contactClassifications;
    }

    /**
     * @return Returns the contactInterests.
     */
    public Collection getContactInterests() {
        return contactInterests;
    }

    /**
     * @param contactInterests
     *            The contactInterests to set.
     */
    public void setContactInterests(Collection contactInterests) {
        this.contactInterests = contactInterests;
    }

    /**
     * @return Returns the contactSources.
     */
    public Collection getContactSources() {
        return contactSources;
    }

    /**
     * @param contactSources
     *            The contactSources to set.
     */
    public void setContactSources(Collection contactSources) {
        this.contactSources = contactSources;
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
     * @return Returns the formIsActive.
     */
    public boolean isFormIsActive() {
        return formIsActive;
    }

    /**
     * @param formIsActive
     *            The formIsActive to set.
     */
    public void setFormIsActive(boolean formIsActive) {
        this.formIsActive = formIsActive;
    }

    /**
     * @return Returns the formIsSendNotification.
     */
    public boolean isFormIsSendNotification() {
        return formIsSendNotification;
    }

    /**
     * @param formIsSendNotification
     *            The formIsSendNotification to set.
     */
    public void setFormIsSendNotification(boolean formIsSendNotification) {
        this.formIsSendNotification = formIsSendNotification;
    }

    /**
     * @return Returns the formIsSendThankYou.
     */
    public boolean isFormIsSendThankYou() {
        return formIsSendThankYou;
    }

    /**
     * @param formIsSendThankYou
     *            The formIsSendThankYou to set.
     */
    public void setFormIsSendThankYou(boolean formIsSendThankYou) {
        this.formIsSendThankYou = formIsSendThankYou;
    }

    /**
     * @return Returns the messages.
     */
    public Collection getMessages() {
        return messages;
    }

    /**
     * @param messages
     *            The messages to set.
     */
    public void setMessages(Collection messages) {
        this.messages = messages;
    }

    /**
     * @return Returns the saveForm.
     */
    public String getSaveForm() {
        return saveForm;
    }

    /**
     * @param saveForm
     *            The saveForm to set.
     */
    public void setSaveForm(String saveForm) {
        this.saveForm = saveForm;
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
     * @return Returns the webForm.
     */
    public WebForm getWebForm() {
        return webForm;
    }

    /**
     * @param webForm
     *            The webForm to set.
     */
    public void setWebForm(WebForm webForm) {
        this.webForm = webForm;
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork.ActionSupport#validate()
     */
    @Override
    public void validate() {
        
        super.validate();
        
        this.validateFieldHasText(this.getWebForm().getName(), "webForm.name","Name is required.");
        this.validateFieldHasText(this.getWebForm().getSuccessURL(), "webForm.successURL","Success URL is required.");
        this.validateFieldHasText(this.getWebForm().getFailureURL(), "webForm.failureURL","Failure URL is required.");
        this.validateFieldHasText(this.getWebForm().getAssignToUsername(), "webForm.assignToUsername","Assign To Username is required.");
        
        this.validateFieldHasLong(this.getWebForm().getContactClass(), "webForm.contactClass", "Contact class is required.");
        this.validateFieldHasLong(this.getWebForm().getContactInterest(), "webForm.contactInterest", "Contact interest is required.");
        this.validateFieldHasLong(this.getWebForm().getContactSource(), "webForm.contactSource", "Contact source is required.");
        
        if (this.getWebForm().isSendNotification()) {
            this.validateFieldHasLong(this.getWebForm().getNotificationMessageId(), "webForm.notificationMessageId", "If you want to send a notification, notification message id is required.");    
        }
        
        if (this.getWebForm().isSendThankYouMessage()) {
            this.validateFieldHasLong(this.getWebForm().getThankYouMessageId(), "webForm.thankYouMessageId", "If you want to send a thank you, thank you message id is required.");
        }
    }

}
