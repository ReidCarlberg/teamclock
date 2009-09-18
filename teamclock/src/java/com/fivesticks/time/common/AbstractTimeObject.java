/*
 * Created on Nov 29, 2005
 *
 */
package com.fivesticks.time.common;

import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

public abstract class AbstractTimeObject implements SystemOwnerKeyAware {

    private String ownerKey;
    private Long id;
    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return Returns the ownerKey.
     */
    public String getOwnerKey() {
        return ownerKey;
    }
    /**
     * @param ownerKey The ownerKey to set.
     */
    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }
    


}
