/*
 * Created on Sep 1, 2006
 *
 */
package com.fivesticks.time.contactv2;

import com.fivesticks.time.common.AbstractTimeObject;

public class ContactV2LookupMap extends AbstractTimeObject {

    
    private Long contactV2Id;
    
    private String luType;
    
    private Long luId;

    /**
     * @return Returns the contactV2Id.
     */
    public Long getContactV2Id() {
        return contactV2Id;
    }

    /**
     * @param contactV2Id The contactV2Id to set.
     */
    public void setContactV2Id(Long contactV2Id) {
        this.contactV2Id = contactV2Id;
    }

    /**
     * @return Returns the luId.
     */
    public Long getLuId() {
        return luId;
    }

    /**
     * @param luId The luId to set.
     */
    public void setLuId(Long luId) {
        this.luId = luId;
    }

    /**
     * @return Returns the luType.
     */
    public String getLuType() {
        return luType;
    }

    /**
     * @param luType The luType to set.
     */
    public void setLuType(String luType) {
        this.luType = luType;
    }
    
}
