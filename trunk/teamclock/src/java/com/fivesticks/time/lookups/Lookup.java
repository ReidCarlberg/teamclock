/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import com.fivesticks.time.common.AbstractTimeObject;

/**
 * @author Reid
 */
public class Lookup extends AbstractTimeObject implements Comparable {

//    private Long id;
//    private String ownerKey;
    private String type;
    private String shortName;
    private String fullName;
    
    

    /**
     * @return Returns the fullName.
     */
    public String getFullName() {
        return fullName;
    }
    /**
     * @param fullName The fullName to set.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
//    /**
//     * @return Returns the id.
//     */
//    public Long getId() {
//        return id;
//    }
//    /**
//     * @param id The id to set.
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//    /**
//     * @param ownerKey The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }
    /**
     * @return Returns the shortName.
     */
    public String getShortName() {
        return shortName;
    }
    /**
     * @param shortName The shortName to set.
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    public String getIdAsString() {
        if (this.getId() != null) {
            return this.getId().toString();
        }
        return null;
    }
    public int compareTo(Object arg0) {
        
        Lookup lu = (Lookup) arg0;
        
        
        return this.getId().compareTo(lu.getId());
    }
}
