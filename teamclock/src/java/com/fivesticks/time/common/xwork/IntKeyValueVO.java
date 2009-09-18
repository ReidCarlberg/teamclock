/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.common.xwork;

/**
 * @author Reid
 *  
 */
public class IntKeyValueVO {

    private final int key;

    private final int value;

    public IntKeyValueVO(int key, int value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return
     */
    public int getKey() {
        return key;
    }

    /**
     * @return
     */
    public int getValue() {
        return value;
    }

}