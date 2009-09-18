/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fivesticks.time.useradmin.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Reid
 */
public class PasswordDigester {
    
    public static final String SPRING_BEAN_NAME = "passwordDigester";
    
    private MessageDigest md;
    
    public PasswordDigester() {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public synchronized String getHash(final String unhashedPassword) {
        String ret = null;
        md.update(unhashedPassword.getBytes());
        BigInteger hash = new BigInteger(1, md.digest());
        String hashword = hash.toString(16);
        return hashword;
    }
}
