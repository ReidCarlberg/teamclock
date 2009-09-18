/*
 * Created on Aug 31, 2006
 *
 */
package com.fivesticks.time.contactv2;

import com.fivesticks.time.common.AbstractTimeObject;

public class CustomerContactV2Map extends AbstractTimeObject {


    
    private Long customerId;
    
    private Long contactV2Id;

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
     * @return Returns the customerId.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId The customerId to set.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

}
