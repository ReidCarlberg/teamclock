/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.util.RandomStringBuilder;

/**
 * @author Reid
 */
public class SystemOwnerKeyGenerator {

    private Log log = LogFactory.getLog(SystemOwnerKeyGenerator.class);
    
    public static final SystemOwnerKeyGenerator singleton = new SystemOwnerKeyGenerator();

    private Random random = new Random();

    private SystemOwnerKeyGenerator() {

    }

    public String getGeneratedKey()  {
        String ret = null;

        /*
         * rsc 2005-05-23 added this in.  a number of tests were returning 
         * duplicate system owner keys.  this should filter this out.
         */
        SystemOwnerServiceDelegate sd = SystemOwnerServiceDelegateFactory.factory.build();
        
        int pass = 0;
        
            boolean unique = false;
            while (!unique) {
                pass++;
                log.info("Making pass " + pass + " through the key generator");
                ret = generateKey();
                if (!sd.isCurrentOwnerKey(ret))
                    unique = true;
            }
        
            
            

        return ret;
    }

    private String generateKey() {

        //between 10 and 20 chars long.
        int keyLength = 10 + Math.abs(random.nextInt() % 10);

        return new RandomStringBuilder().buildRandomString(keyLength);

    }




}