/*
 * Created on Oct 22, 2004 by Reid
 */
package com.fstx.stdlib.common;

/**
 * @author Reid
 */
public class SelectItem {

    private String key;

    private String value;

    public SelectItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return Returns the key.
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            The key to set.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }
}