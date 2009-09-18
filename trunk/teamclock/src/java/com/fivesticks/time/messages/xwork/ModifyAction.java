/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.messages.xwork;

import com.fivesticks.time.common.delete.DeleteContextFactory;
import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.GlobalForwardStatics;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.messages.Message;
import com.fivesticks.time.messages.MessageDeleteCommand;
import com.fivesticks.time.messages.MessageServiceDelegate;
import com.fivesticks.time.messages.MessageServiceDelegateFactory;
import com.opensymphony.xwork.ActionSupport;

public class ModifyAction extends SessionContextAwareSupport implements
        ModifyContextAware {

    private ModifyContext modifyContext;

    private Long target;

    private Message targetMessage;

    private String saveMessage;

    private String cancelMessage;

    private String deleteMessage;

    private String copyMessage;

    public String execute() throws Exception {

        if (this.getCancelMessage() != null)
            return SUCCESS;

        MessageServiceDelegate sd = MessageServiceDelegateFactory.factory.build(this
                .getSystemOwner());

        if (this.getTarget() != null) {
            Message t = sd.get(this.getTarget());
            if (t == null) {
                return ERROR;
            }
            this.getModifyContext().setTarget(t);
            this.setTargetMessage(t);
            return INPUT;
        }
        
        if (this.getDeleteMessage() != null) {
            MessageDeleteCommand cmd = new MessageDeleteCommand(this.getModifyContext().getTarget());
            this.getSessionContext().setDeleteContext(
                    DeleteContextFactory.factory.build(cmd, this
                            .getSessionContext().getSuccessOverride()));
            return GlobalForwardStatics.GLOBAL_COMMON_DELETE; 
        }
        
        if (this.getCopyMessage() != null) {
            this.getModifyContext().setTarget(null);
            this.getTargetMessage().setId(null);
            return INPUT;
        }
        
        if (this.getSaveMessage() == null) {
            this.getModifyContext().setTarget(null);
            this.setTargetMessage(new Message());
            return INPUT;
        }

        validate();
        
        if (this.getModifyContext().getTarget() != null) {
            this.getTargetMessage().setId(this.getModifyContext().getTarget().getId());
        }
        
        sd.save(this.getTargetMessage());
        
        return ActionSupport.SUCCESS;
    }

    public void validate() {
        
        ValidationHelper helper = new ValidationHelper();
        
        if (helper.isStringEmpty(this.getTargetMessage().getName())) {
            this.addFieldError("targetMessage.name", "Name is required.");
        }
        
        if (helper.isStringEmpty(this.getTargetMessage().getSubject())) {
            this.addFieldError("targetMessage.subject", "Subject is required.");
        }
        
        if (helper.isStringEmpty(this.getTargetMessage().getMessage())) {
            this.addFieldError("targetMessage.message", "Message is required.");
        }
        
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
     * @return Returns the cancelMessage.
     */
    public String getCancelMessage() {
        return cancelMessage;
    }

    /**
     * @param cancelMessage
     *            The cancelMessage to set.
     */
    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }

    /**
     * @return Returns the copyMessage.
     */
    public String getCopyMessage() {
        return copyMessage;
    }

    /**
     * @param copyMessage
     *            The copyMessage to set.
     */
    public void setCopyMessage(String copyMessage) {
        this.copyMessage = copyMessage;
    }

    /**
     * @return Returns the deleteMessage.
     */
    public String getDeleteMessage() {
        return deleteMessage;
    }

    /**
     * @param deleteMessage
     *            The deleteMessage to set.
     */
    public void setDeleteMessage(String deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    /**
     * @return Returns the saveMessage.
     */
    public String getSaveMessage() {
        return saveMessage;
    }

    /**
     * @param saveMessage
     *            The saveMessage to set.
     */
    public void setSaveMessage(String saveMessage) {
        this.saveMessage = saveMessage;
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
     * @return Returns the targetMessage.
     */
    public Message getTargetMessage() {
        return targetMessage;
    }

    /**
     * @param targetMessage
     *            The targetMessage to set.
     */
    public void setTargetMessage(Message targetMessage) {
        this.targetMessage = targetMessage;
    }

}
