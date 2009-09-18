/*
 * Created on Jun 13, 2006
 *
 */
package com.fivesticks.time.common.json;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;

public abstract class AbstractJSONConverter {

    private ProjectServiceDelegate projectServiceDelegate;
    
    private CustomerServiceDelegate customerServiceDelegate;

    protected void initialize(SessionContext sessionContext) {
        this.setProjectServiceDelegate(ProjectServiceDelegateFactory.factory.build(sessionContext));
        
        this.setCustomerServiceDelegate(CustomerServiceDelegateFactory.factory.build(sessionContext));
        
    }
    /**
     * @return Returns the customerServiceDelegate.
     */
    public CustomerServiceDelegate getCustomerServiceDelegate() {
        return customerServiceDelegate;
    }

    /**
     * @param customerServiceDelegate The customerServiceDelegate to set.
     */
    public void setCustomerServiceDelegate(
            CustomerServiceDelegate customerServiceDelegate) {
        this.customerServiceDelegate = customerServiceDelegate;
    }

    /**
     * @return Returns the projectServiceDelegate.
     */
    public ProjectServiceDelegate getProjectServiceDelegate() {
        return projectServiceDelegate;
    }

    /**
     * @param projectServiceDelegate The projectServiceDelegate to set.
     */
    public void setProjectServiceDelegate(
            ProjectServiceDelegate projectServiceDelegate) {
        this.projectServiceDelegate = projectServiceDelegate;
    }
    
}
