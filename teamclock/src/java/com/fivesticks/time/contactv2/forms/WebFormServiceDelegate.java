/*
 * Created on Sep 2, 2006
 *
 */
package com.fivesticks.time.contactv2.forms;

import java.util.Collection;

public interface WebFormServiceDelegate {

    public WebForm get(Long id) throws WebFormServiceDelegateException;
    
    public WebForm get(String key) throws WebFormServiceDelegateException;
    
    public void save(WebForm webForm) throws WebFormServiceDelegateException;
    
    public void delete(WebForm webForm) throws WebFormServiceDelegateException;
    
    public Collection find(WebFormCriteriaParameters params) throws WebFormServiceDelegateException;
    
    public Collection findAll() throws WebFormServiceDelegateException;
    
}
