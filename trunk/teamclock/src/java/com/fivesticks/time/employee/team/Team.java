/*
 * Created on Aug 21, 2005
 *
 */
package com.fivesticks.time.employee.team;

import java.io.Serializable;

import com.fivesticks.time.common.AbstractTimeObject;

public class Team extends AbstractTimeObject implements Serializable {

//    private Long id;
//    
//    private String ownerKey;
    
    private String name;

    private String nameShort;
    
    private Long status;
    
//    /**
//     * @return Returns the id.
//     */
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * @param id The id to set.
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownerKey The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }

    /**
     * @return Returns the nameShort.
     */
    public String getNameShort() {
        return nameShort;
    }

    /**
     * @param nameShort The nameShort to set.
     */
    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    /**
     * @return Returns the status.
     */
    public Long getStatus() {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(Long status) {
        this.status = status;
    }


    
    
}
