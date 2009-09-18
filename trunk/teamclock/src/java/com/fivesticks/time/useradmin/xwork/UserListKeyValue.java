/*
 * Created on Nov 25, 2004 by REID
 */
package com.fivesticks.time.useradmin.xwork;

/**
 * @author REID
 */
public class UserListKeyValue {

    private String key;

    private String value;

    public UserListKeyValue() {

    }

    public UserListKeyValue(String key, String value) {
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