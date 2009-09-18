/*
 * Created on Jun 11, 2004
 *
 */
package com.fivesticks.time.common.util;


/**
 * <P>
 * This rounds by the quarter hour.
 * 
 * 0-7 rounds down. 8-14 rounds up.
 * 
 * @author REID
 *  
 */
public class RounderSixthsImpl extends AbstractRounder implements Rounder {

    public int getRoundBy() {
        
        return 10;
    }



}