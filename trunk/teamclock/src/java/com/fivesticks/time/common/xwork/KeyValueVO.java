/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.common.xwork;

/**
 * @author Reid
 *  
 */
public class KeyValueVO {

    private final String key;

    private final String value;

    public KeyValueVO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * @return
     */
    public String getValue() {
        return value;
    }

}