/*
 * Created on Aug 31, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

/**
 * @author Reid
 */
public class DefaultSystemOwnerBroker {

    private static final String DEFAULT_KEY = "_default";

    private static final String DEFAULT_NAME = "_defaultName";

    private static SystemOwner defaultSystemOwner;

    public static SystemOwner getDefaultSystemOwner() {
        if (defaultSystemOwner == null) {
            defaultSystemOwner = new SystemOwner();
            defaultSystemOwner.setKey(DEFAULT_KEY);
            defaultSystemOwner.setName(DEFAULT_NAME);
        }
        return defaultSystemOwner;
    }
}