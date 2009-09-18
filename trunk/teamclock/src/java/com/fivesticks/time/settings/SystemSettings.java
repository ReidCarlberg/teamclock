/*
 * Created on Jun 13, 2004
 *
 */
package com.fivesticks.time.settings;

import java.io.Serializable;

import com.fivesticks.time.common.AbstractTimeObject;

/**
 * @author REID
 *  
 */
public class SystemSettings extends AbstractTimeObject implements Serializable {

//    private Long id;
//
//    private String ownerKey;

    private String settingKey;

    private String setting;

//    /**
//     * @return
//     */
//    public Long getId() {
//        return id;
//    }

    /**
     * @return
     */
    public String getSetting() {
        return setting;
    }

    /**
     * @return
     */
    public String getSettingKey() {
        return settingKey;
    }

//    /**
//     * @param long1
//     */
//    public void setId(Long long1) {
//        id = long1;
//    }

    /**
     * @param string
     */
    public void setSetting(String string) {
        setting = string;
    }

    /**
     * @param string
     */
    public void setSettingKey(String string) {
        settingKey = string;
    }

//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownerKey
//     *            The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }

}