/*
 * Created on Jun 11, 2004
 *
 */
package com.fivesticks.time.common.util;


/**
 * <P>
 * This doesn't round at all. This is default behavior.
 * 
 * 2005-11-09 Note that if you set round by to 0, you will get a division by zero error, which is now fun.
 * 
 * 
 * @author REID
 *  
 */
public class RounderRawImpl extends AbstractRounder implements Rounder {

    public int getRoundBy() {
        
        return 1;
    }



}