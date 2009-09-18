/*
 * Created on Nov 24, 2004 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.systemowner.SystemOwnerCriteriaParameters;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;

/**
 * @author Reid
 */
public class SystemOwnerListAction extends SessionContextAwareSupport implements SystemOwnerListContextAware {

    private String search;
    
    private String reset;

    private Collection systemOwners;
    
    private SystemOwnerListContext systemOwnerListContext;

    private SystemOwnerCriteriaParameters params = new SystemOwnerCriteriaParameters();

    public String execute() throws Exception {

        this.getSessionContext().setSuccessOverride(null);

        if (this.getReset() != null) {
            params = new SystemOwnerCriteriaParameters();
            this.getSystemOwnerListContext().setParams(null);
            return SUCCESS;
        }
        
        if (this.getSearch() != null) {
            
            params.setSortedByName(true);
            this.getSystemOwnerListContext().setParams(params);
            this.setSystemOwners(SystemOwnerServiceDelegateFactory.factory
                    .build().search(params));
        } else if (this.getSystemOwnerListContext().getParams() != null) {
            params = this.getSystemOwnerListContext().getParams();
            this.setSystemOwners(SystemOwnerServiceDelegateFactory.factory
                    .build().search(params));
        }

        
        

        return SUCCESS;
    }

    /**
     * @return Returns the systemOwners.
     */
    public Collection getSystemOwners() {
        return systemOwners;
    }

    /**
     * @param systemOwners
     *            The systemOwners to set.
     */
    public void setSystemOwners(Collection systemOwners) {
        this.systemOwners = systemOwners;
    }

    /**
     * @return Returns the params.
     */
    public SystemOwnerCriteriaParameters getParams() {
        return params;
    }

    /**
     * @param params
     *            The params to set.
     */
    public void setParams(SystemOwnerCriteriaParameters params) {
        this.params = params;
    }

    /**
     * @return Returns the search.
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search
     *            The search to set.
     */
    public void setSearch(String search) {
        this.search = search;
    }

    /**
     * @return Returns the reset.
     */
    public String getReset() {
        return reset;
    }

    /**
     * @param reset The reset to set.
     */
    public void setReset(String reset) {
        this.reset = reset;
    }

    /**
     * @return Returns the systemOwnerListContext.
     */
    public SystemOwnerListContext getSystemOwnerListContext() {
        return systemOwnerListContext;
    }

    /**
     * @param systemOwnerListContext The systemOwnerListContext to set.
     */
    public void setSystemOwnerListContext(
            SystemOwnerListContext systemOwnerListContext) {
        this.systemOwnerListContext = systemOwnerListContext;
    }
}