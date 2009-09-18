/*
 * Created on Aug 26, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.util.Iterator;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.ProjectDisplayWrapper;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author shuji
 */
public class ProjectListActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testList() throws Exception {

        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        
        assertTrue(systemowner != null);
        
        ProjectListAction projectListAction = new ProjectListAction();

        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        projectListAction.setSessionContext(sessionContext);

        assertTrue(projectListAction.execute()
                .equals(ProjectListAction.SUCCESS));
        assertTrue(projectListAction.getProjects() != null);

        /*
         * rsc 8/27/2004 We should have more than 0
         */

        assertTrue(projectListAction.getProjects().size() > 0);

        /*
         * rsc 8/27/2004 make sure they belong to the right owner
         */
        for (Iterator i = projectListAction.getProjects().iterator(); i
                .hasNext();) {
            ProjectDisplayWrapper current = (ProjectDisplayWrapper) i.next();
            assertTrue(current.getProject().getOwnerKey().equals(systemowner.getKey()));
        }
    }
}