package com.fivesticks.time.contactv2.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.contactv2.ContactV2;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegate;
import com.fivesticks.time.contactv2.ContactV2ServiceDelegateFactory;
import com.fivesticks.time.customer.xwork.CustomerModifyContext;
import com.fivesticks.time.customer.xwork.CustomerModifyContextAware;
import com.fivesticks.time.object.comments.ObjectCommentServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.opensymphony.xwork.Action;

/**
 * @author reid 2006-08-30
 */
public class ContactV2DetailAction extends SessionContextAwareSupport implements
        CustomerModifyContextAware, ContactV2ModifyContextAware {

    private CustomerModifyContext customerModifyContext;

    private ContactV2ModifyContext contactV2ModifyContext;

    private Long target;

    private ContactV2 targetContactV2;

    private Collection settingsClassifications;

    private Collection settingsSources;

    private Collection settingsInterests;

    private Collection keyValues;

    private Collection comments;

    private Collection todos;

    public String execute() throws Exception {

        ContactV2ServiceDelegate sd = ContactV2ServiceDelegateFactory.factory
                .build(this.getSessionContext().getSystemOwner());

        if (this.getTarget() != null) {

            this.getContactV2ModifyContext()
                    .setTarget(sd.get(this.getTarget()));
            targetContactV2 = this.getContactV2ModifyContext().getTarget();
        } else if (this.getContactV2ModifyContext().getTarget() != null) {
            /*
             * 2006-09-02 Reid force a reload so it's current.
             * 
             * it seems like I shouldn't have to do this but something in the
             * param interceptor is asking me to.
             */
            ContactV2 target = sd.get(this.getContactV2ModifyContext()
                    .getTarget().getId());

            this.setTargetContactV2(target);
            this.getContactV2ModifyContext().setTarget(target);
        }

        if (this.getTargetContactV2() == null) {
            //this.getSessionContext().setMessage("Failed to load contact.");
            return Action.ERROR;
        }



        this.setKeyValues(sd.getKeyValues(this.getTargetContactV2()));

        this.setComments(ObjectCommentServiceDelegateFactory.factory.build(
                this.getSystemOwner()).getComments(this.getTargetContactV2()));

        this.setTodos(ToDoServiceDelegateFactory.factory.build(
                this.getSessionContext()).findRelatedTodos(
                this.getTargetContactV2()));

        return SUCCESS;

    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    /**
     * @return Returns the customerModifyContext.
     */
    public CustomerModifyContext getCustomerModifyContext() {
        return customerModifyContext;
    }

    /**
     * @param customerModifyContext
     *            The customerModifyContext to set.
     */
    public void setCustomerModifyContext(
            CustomerModifyContext customerModifyContext) {
        this.customerModifyContext = customerModifyContext;
    }

    /**
     * @return Returns the contactModifyContext.
     */
    public ContactV2ModifyContext getContactV2ModifyContext() {
        return contactV2ModifyContext;
    }

    /**
     * @param contactModifyContext
     *            The contactModifyContext to set.
     */
    public void setContactV2ModifyContext(
            ContactV2ModifyContext contactModifyContext) {
        this.contactV2ModifyContext = contactModifyContext;
    }

    /**
     * @return Returns the contactV2.
     */
    public ContactV2 getTargetContactV2() {
        return targetContactV2;
    }

    /**
     * @param contactV2
     *            The contactV2 to set.
     */
    public void setTargetContactV2(ContactV2 contactV2) {
        this.targetContactV2 = contactV2;
    }

    /**
     * @return Returns the settingsClassifications.
     */
    public Collection getSettingsClassifications() {
        return settingsClassifications;
    }

    /**
     * @param settingsClassifications
     *            The settingsClassifications to set.
     */
    public void setSettingsClassifications(Collection settingsClassifications) {
        this.settingsClassifications = settingsClassifications;
    }

    /**
     * @return Returns the settingsInterests.
     */
    public Collection getSettingsInterests() {
        return settingsInterests;
    }

    /**
     * @param settingsInterests
     *            The settingsInterests to set.
     */
    public void setSettingsInterests(Collection settingsInterests) {
        this.settingsInterests = settingsInterests;
    }

    /**
     * @return Returns the settingsSources.
     */
    public Collection getSettingsSources() {
        return settingsSources;
    }

    /**
     * @param settingsSources
     *            The settingsSources to set.
     */
    public void setSettingsSources(Collection settingsSources) {
        this.settingsSources = settingsSources;
    }

    /**
     * @return the keyValues
     */
    public Collection getKeyValues() {
        return keyValues;
    }

    /**
     * @param keyValues
     *            the keyValues to set
     */
    public void setKeyValues(Collection keyValues) {
        this.keyValues = keyValues;
    }

    public boolean isHasKeyValues() {
        return this.keyValues != null && this.keyValues.size() > 0;
    }

    /**
     * @return the comments
     */
    public Collection getComments() {
        return comments;
    }

    /**
     * @param comments
     *            the comments to set
     */
    public void setComments(Collection comments) {
        this.comments = comments;
    }

    /**
     * @return the todos
     */
    public Collection getTodos() {
        return todos;
    }

    /**
     * @param todos
     *            the todos to set
     */
    public void setTodos(Collection todos) {
        this.todos = todos;
    }
}