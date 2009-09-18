/*
 * Created on Jun 24, 2006
 *
 */
package com.fivesticks.time.lookups.util;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.lookups.LookupServiceDelegate;
import com.fivesticks.time.lookups.LookupServiceDelegateException;
import com.fivesticks.time.lookups.LookupServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;

public class LookupDecorator {

    private LookupServiceDelegate lookupServiceDelegate;

    public LookupDecorator(SystemOwner systemOwner) {
        this.lookupServiceDelegate = LookupServiceDelegateFactory.factory
                .build(systemOwner);
    }

    public void decorate(Collection targets)
            throws LookupServiceDelegateException {

        for (Iterator iter = targets.iterator(); iter.hasNext();) {
            Object element = (Object) iter.next();
            if (element instanceof LookupDecorateable) {
                LookupDecorateable target = (LookupDecorateable) element;
                this.decorate(target);
            }
        }
    }

    public void decorate(LookupDecorateable lookupDecorateable)
            throws LookupServiceDelegateException {

        lookupDecorateable.setLookup(this.lookupServiceDelegate
                .get(lookupDecorateable.getLookupId()));

    }
}
