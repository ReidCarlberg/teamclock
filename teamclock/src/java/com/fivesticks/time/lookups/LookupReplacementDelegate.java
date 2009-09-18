/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import com.fivesticks.time.systemowner.SystemOwnerAware;

/**
 * @author Reid
 */
public interface LookupReplacementDelegate extends SystemOwnerAware {

    public void replace(Lookup original, Lookup replaceWith) throws LookupReplacementDelegateException;
}
