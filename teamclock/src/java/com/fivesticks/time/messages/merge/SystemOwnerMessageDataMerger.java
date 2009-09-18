/*
 * Created on Jun 6, 2005
 *
 */
package com.fivesticks.time.messages.merge;

import com.fivesticks.time.messages.Message;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.util.forms.FormDataMergerException;

/**
 * @author Reid
 * 
 */
public class SystemOwnerMessageDataMerger implements MessageDataMerger {

    public static String OWNER_NAME_TAG = "<systemowner:name />";

    public static String OWNER_COMPANY_TAG = "<systemowner:company />";

    public static String OWNER_KEY_TAG = "<systemowner:key />";

    private SystemOwner systemOwner;

    private SystemOwner systemOwnerTarget;

    private Message message;

    public SystemOwnerMessageDataMerger(SystemOwner systemOwner,
            SystemOwner systemOwnerTarget, Message message) {
        this.systemOwner = systemOwner;
        this.systemOwnerTarget = systemOwnerTarget;
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.ebay.setup.forms.util.FormDataMerger#getMergedData()
     */
    public String getMergedData() throws FormDataMergerException {

        String ret = this.message.getMessage();

        return this.merge(ret);
    }

    public String getMergedSubject() throws FormDataMergerException {

        String ret = this.message.getSubject();

        return this.merge(ret);

    }

    private String merge(String ret) {

        ret = ret.replaceAll(OWNER_NAME_TAG, this.getSystemOwnerTarget()
                .getContactName());

        ret = ret
                .replaceAll(OWNER_COMPANY_TAG, this.getSystemOwnerTarget().getName());

        ret = ret.replaceAll(OWNER_KEY_TAG, this.getSystemOwnerTarget().getKey());

        return ret;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public SystemOwner getSystemOwnerTarget() {
        return systemOwnerTarget;
    }

    public void setSystemOwnerTarget(SystemOwner systemOwnerTarget) {
        this.systemOwnerTarget = systemOwnerTarget;
    }
    
    public Message getMergedMessage() throws FormDataMergerException {
        
        Message ret = new Message();
        
        ret.setMessage(this.getMergedData());
        ret.setSubject(this.getMergedSubject());
        ret.setOwnerKey(this.getSystemOwner().getKey());
        
        return ret;
    }
}
