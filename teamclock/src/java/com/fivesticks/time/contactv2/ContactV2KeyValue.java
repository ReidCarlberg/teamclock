/*
 * Created on Sep 16, 2006
 *
 */
package com.fivesticks.time.contactv2;

import com.fivesticks.time.common.AbstractTimeObject;

public class ContactV2KeyValue extends AbstractTimeObject {

    private Long contactV2Id;
    
    private String key;
    
    private String value;

    /**
     * @return the contactId
     */
    public Long getContactV2Id() {
        return contactV2Id;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactV2Id(Long contactId) {
        this.contactV2Id = contactId;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    
    
    
}
