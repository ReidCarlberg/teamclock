/*
 * Created on Nov 22, 2004 by Reid
 */
package com.fivesticks.time.accountactivity;

import java.util.Collection;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public interface AccountActivityServiceDelegate {

    public Collection findUnpostedActivity(SimpleDate fromDate)
            throws AccountActivityServiceDelegateException;

}