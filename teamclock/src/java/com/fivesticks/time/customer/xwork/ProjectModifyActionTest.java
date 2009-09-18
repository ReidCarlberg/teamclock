/*
 * Created on Aug 26, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import java.util.Collection;
import java.util.HashMap;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author shuji
 */
public class ProjectModifyActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testModifyWithoutSubmitAndTarget() throws Exception {

        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);
        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        ProjectModifyAction projectModifyAction = new ProjectModifyAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        projectModifyAction.setSessionContext(sessionContext);
        projectModifyAction.setProjectModifyContext(new ProjectModifyContext());

        //testging just excute without submit
        assertTrue(projectModifyAction.execute().equals(
                ProjectModifyAction.INPUT));

//        /*
//         * rsc 8/27/2004 What else should be true about the project modify?
//         * Shouldn't action context now contain a project modify context? -->
//         * No.
//         */
//
//        assertTrue(!ActionContext.getContext().getSession().containsKey(
//                ProjectModifyContext.KEY));

    }

    public void testModifyWithoutSubmitWithTarget() throws Exception {
        ActionContext context = new ActionContext(new HashMap());
        context.setSession(new HashMap());
        ActionContext.setContext(context);

        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        ProjectModifyAction projectModifyAction = new ProjectModifyAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        projectModifyAction.setSessionContext(sessionContext);

        /*
         * rsc 8/27/2004 the actual number here is out of our control. The
         * database may change it, so the easiest thing to do is to get the one
         * that setupdata installed.
         */
        //        //testing if set target = 1 assuming data target 1 is already there
        //        projectModifyAction.setTarget(new Long(1));
        Project baseProject = (Project) ProjectServiceDelegateFactory.factory.build(
                systemowner).getAllActive().toArray()[0];
        assertTrue(baseProject != null);
        projectModifyAction.setProjectModifyContext(new ProjectModifyContext());
        projectModifyAction.setTarget(baseProject.getId());
        assertTrue(projectModifyAction.execute().equals(
                ProjectModifyAction.INPUT));

        /*
         * rsc 8/27/2004 in this case, it should contain a modify context.
         */

        assertTrue(projectModifyAction.getNewShortName() != null);
    }

    public void testAddNewProject() throws Exception {

        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        ProjectModifyAction pma = new ProjectModifyAction();
        pma.setProjectModifyContext(new ProjectModifyContext());
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        pma.setSessionContext(sessionContext);
        ProjectServiceDelegate projectBD = ProjectServiceDelegateFactory.factory.build(sessionContext);

        Collection c1 = projectBD.getAllActive();
        //suppose to create a new customer name test1 and save it
        pma.setNewShortName("gaggaga");
        pma.setNewLongName("sdfdfd");
        pma.setNewIsActive(true);
        pma.setSessionContext(sessionContext);
        pma.setNewCustomer(new Long("2"));
        pma.setSubmitProject("");
        pma.execute();

        Collection c2 = projectBD.getAllActive();

        assertTrue(c1.size() + 1 == c2.size());

    }

    public void testModifyAProjectName() throws Exception {

        SystemOwner systemowner = SystemOwnerServiceDelegateFactory.factory.build()
                .get(UserBDFactory.factory.build().getByUsername("admin"));
        ProjectModifyAction pma = new ProjectModifyAction();
        SessionContext sessionContext = new SessionContext();
        sessionContext.setSystemOwner(systemowner);
        pma.setSessionContext(sessionContext);
        ProjectServiceDelegate projectBD = ProjectServiceDelegateFactory.factory.build(sessionContext);

        ProjectModifyContext pMcontext = new ProjectModifyContext();

        Project aProject = projectBD.getFstxProject(new Long(1));

        pMcontext.setTargetProject(aProject);

        String test = aProject.getLongName();
        pma.setProjectModifyContext(new ProjectModifyContext());
        pma.getProjectModifyContext().setTargetProject(aProject);
        pma.setNewLongName(test + "_1");
        pma.setNewShortName(aProject.getShortName() + "_22");
        pma.setSubmitProject("submit");
        pma.setNewCustomer(aProject.getCustId());
        pma.setStringActive("true");
        pma.setStringPostable("true");
        String ret = pma.execute();

        assertTrue(ret.equals(ActionSupport.SUCCESS.toString()));
        
        Project changedNameProject = projectBD
                .getFstxProjectByLongName(aProject.getLongName());
        
        assertTrue(changedNameProject != null);
        
        String test2 = changedNameProject.getLongName();

        assertTrue(changedNameProject.getLongName().equals(test + "_1"));

    }

}