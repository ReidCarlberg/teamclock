/*
 * Created on Sep 3, 2006
 *
 */
package com.fivesticks.time.common;

import java.util.Date;

public abstract class AbstractDateAwareTimeObject extends AbstractTimeObject {

    private Date createDate;
    
    private Date modifyDate;
    
    /**
     * @return Returns the createDate.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate The createDate to set.
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return Returns the modifyDate.
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate The modifyDate to set.
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
    
    

}
