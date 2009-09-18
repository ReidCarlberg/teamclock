/*
 * Created on May 17, 2005 by Reid
 */
package com.fivesticks.time.lookups;

import java.util.Collection;

/**
 * @author Reid
 */
public interface LookupServiceDelegate {
    
    public Collection getAll(LookupType type) throws LookupServiceDelegateException;
    
    public void save(Lookup target) throws LookupServiceDelegateException;
    
    public void delete(Lookup target) throws LookupServiceDelegateException;
    
    public Lookup get(Long id) throws LookupServiceDelegateException;
    
    public void replace(Long original, Long replaceWith) throws LookupServiceDelegateException;
    
    public void replace(Lookup original, Lookup replaceWith) throws LookupServiceDelegateException;
    
    public Collection search(LookupCriteriaParameters parameters) throws LookupServiceDelegateException;
    
}
