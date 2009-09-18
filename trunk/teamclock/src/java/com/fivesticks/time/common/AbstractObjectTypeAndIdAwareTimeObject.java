/*
 * Created on Jul 6, 2006 by Reid
 */
package com.fivesticks.time.common;

public abstract class AbstractObjectTypeAndIdAwareTimeObject extends
        AbstractTimeObject {

    private Long objectId;
    private String objectType;

    /**
     * @return Returns the objectId.
     */
    public Long getObjectId() {
        return objectId;
    }

    /**
     * @return Returns the objectType.
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * @param objectId The objectId to set.
     */
    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    /**
     * @param objectType The objectType to set.
     */
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    
}
