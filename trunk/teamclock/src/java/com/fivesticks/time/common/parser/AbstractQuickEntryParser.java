/*
 * Created on Jul 14, 2006
 *
 */
package com.fivesticks.time.common.parser;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUserServiceDelegate;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDFactory;

public abstract class AbstractQuickEntryParser {

    private SessionContext sessionContext;
    
    private ProjectServiceDelegate projectServiceDelegate;
    
    private SystemUserServiceDelegate systemUserServiceDelegate;
    
    private UserBD userBD;
    
    public AbstractQuickEntryParser(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
        
        this.projectServiceDelegate = ProjectServiceDelegateFactory.factory.build(sessionContext);
        
        this.systemUserServiceDelegate = SystemUserServiceDelegateFactory.factory.build();
        
        this.userBD = UserBDFactory.factory.build();
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

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * @return Returns the systemUserServiceDelegate.
     */
    public SystemUserServiceDelegate getSystemUserServiceDelegate() {
        return systemUserServiceDelegate;
    }

    /**
     * @param systemUserServiceDelegate The systemUserServiceDelegate to set.
     */
    public void setSystemUserServiceDelegate(
            SystemUserServiceDelegate systemUserServiceDelegate) {
        this.systemUserServiceDelegate = systemUserServiceDelegate;
    }

    /**
     * @return Returns the userBD.
     */
    public UserBD getUserBD() {
        return userBD;
    }

    /**
     * @param userBD The userBD to set.
     */
    public void setUserBD(UserBD userBD) {
        this.userBD = userBD;
    }
    
}
