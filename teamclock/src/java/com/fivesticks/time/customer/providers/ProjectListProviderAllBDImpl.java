/*
 * Created on Jun 18, 2005
 *
 */
package com.fivesticks.time.customer.providers;

import java.io.Serializable;
import java.util.Collection;

import com.fivesticks.time.common.xwork.AbstractSystemOwnerAwareProvider;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;

/**
 * @author Reid
 *
 */
public class ProjectListProviderAllBDImpl extends
        AbstractSystemOwnerAwareProvider implements ProjectListProvider, Serializable {

    private Collection projects;
    
    /* (non-Javadoc)
     * @see com.fivesticks.time.customer.providers.ProjectListProvider#getProjects()
     */
    public Collection getProjects() throws ProjectListProviderException {

        if (this.projects == null) {
            
            try {
                projects = ProjectServiceDelegateFactory.factory.build(this.getSystemOwner()).getAllActive();
            } catch (ProjectBDException e) {
                throw new ProjectListProviderException(e);
            }
            
        }
        
        return projects;
    }

}
