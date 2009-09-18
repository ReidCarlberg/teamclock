/*
 * Created on Dec 19, 2004 by REID
 */
package com.fivesticks.time.queuedmessages.xwork;

import com.fivesticks.time.common.delete.DeleteContext;
import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.queuedmessages.QueuedMessage;
import com.fivesticks.time.queuedmessages.QueuedMessageDeleteCommand;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.useradmin.UserTypeEnum;

/**
 * @author REID
 */
public class MessageModifyAction extends SessionContextAwareSupport implements
        MessageModifyActionContextAware {

//    private SessionContext sessionContext;

    private MessageModifyActionContext modifyActionContext;

    private Long target;

    private String submitMessageSave;

    private String submitMessageCancel;

    private String submitMessageDelete;

    private QueuedMessage queuedMessage;

    public String execute() throws Exception {

        if (this.getSubmitMessageCancel() != null)
            return SUCCESS;

        if (this.getSubmitMessageDelete() != null
                && this.getModifyActionContext().getTarget() != null) {
            DeleteContext dc = DeleteContextFactory.factory
                    .build(new QueuedMessageDeleteCommand(this
                            .getModifyActionContext().getTarget()));
            this.getSessionContext().setDeleteContext(dc);
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE;
        }

        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory
                .build(this.getSessionContext());

        //adding
        if (this.getTarget() == null && this.getSubmitMessageSave() == null) {
            this.getModifyActionContext().setTarget(null);
            return INPUT;
        }
        //modifying
        if (this.getTarget() != null) {
            this.setQueuedMessage(sd.get(this.getTarget()));
            this.getModifyActionContext().setTarget(queuedMessage);
            return INPUT;
        }

        validate();

        if (this.hasErrors()) {
            return INPUT;
        }

        decorate(); //handles presave stuff.

        sd.add(this.getQueuedMessage());

        return SUCCESS;
    }

    public void validate() {

        ValidationHelper help = new ValidationHelper();

        if (help.isStringEmpty(this.getQueuedMessage().getFromAddress())) {
            this.addFieldError("fromAddress", "From address is missing.");
        }

        if (help.isStringEmpty(this.getQueuedMessage().getFromName())) {
            this.addFieldError("fromName", "From name is missing.");
        }

        if (help.isStringEmpty(this.getQueuedMessage().getToAddress())) {
            this.addFieldError("toAddress", "To address is missing.");
        }

        if (help.isStringEmpty(this.getQueuedMessage().getToName())) {
            this.addFieldError("toName", "To name is missing.");
        }

        if (help.isStringEmpty(this.getQueuedMessage().getSubject())) {
            this.addFieldError("subject", "Subject is missing.");
        }

        if (help.isStringEmpty(this.getQueuedMessage().getMessage())) {
            this.addFieldError("message", "Message is missing.");
        }

    }

    public void decorate() throws Exception {
        if (this.getModifyActionContext().getTarget() != null) {
            this.getQueuedMessage().setId(
                    this.getModifyActionContext().getTarget().getId());
        }

        if (this.getSessionContext().getUserTypeEnum() == UserTypeEnum.STANDARD
                || (this.getQueuedMessage().getModifiedByUsername() == null || this
                        .getQueuedMessage().getModifiedByUsername().trim()
                        .length() == 0)) {
            this.getQueuedMessage().setModifiedByUsername(
                    this.getSessionContext().getUser().getUsername());
        }
    }

    /**
     * @return Returns the modifyActionContext.
     */
    public MessageModifyActionContext getModifyActionContext() {
        return modifyActionContext;
    }

    /**
     * @param modifyActionContext
     *            The modifyActionContext to set.
     */
    public void setModifyActionContext(
            MessageModifyActionContext modifyActionContext) {
        this.modifyActionContext = modifyActionContext;
    }


    /**
     * @return Returns the submitMessageCancel.
     */
    public String getSubmitMessageCancel() {
        return submitMessageCancel;
    }

    /**
     * @param submitMessageCancel
     *            The submitMessageCancel to set.
     */
    public void setSubmitMessageCancel(String submitMessageCancel) {
        this.submitMessageCancel = submitMessageCancel;
    }

    /**
     * @return Returns the submitMessageDelete.
     */
    public String getSubmitMessageDelete() {
        return submitMessageDelete;
    }

    /**
     * @param submitMessageDelete
     *            The submitMessageDelete to set.
     */
    public void setSubmitMessageDelete(String submitMessageDelete) {
        this.submitMessageDelete = submitMessageDelete;
    }

    /**
     * @return Returns the submitMessageSave.
     */
    public String getSubmitMessageSave() {
        return submitMessageSave;
    }

    /**
     * @param submitMessageSave
     *            The submitMessageSave to set.
     */
    public void setSubmitMessageSave(String submitMessageSave) {
        this.submitMessageSave = submitMessageSave;
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
     * @return Returns the queuedMessage.
     */
    public QueuedMessage getQueuedMessage() {
        return queuedMessage;
    }

    /**
     * @param queuedMessage
     *            The queuedMessage to set.
     */
    public void setQueuedMessage(QueuedMessage queuedMessage) {
        this.queuedMessage = queuedMessage;
    }
}