/*
 * Created on Jun 11, 2004
 *
 */
package com.fivesticks.time.common.util;

import java.util.Date;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * <p>
 * Might have different ways to round any particular punch, as recorded in the
 * eventTimestamp property.
 * 
 * @author REID
 *  
 */
public interface Rounder {

    public Date round(Date toRound);
    
    public SimpleDate round(SimpleDate toRound);

}