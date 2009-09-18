/*
 * Created on Sep 22, 2005
 *
 */
package com.fivesticks.time.customer.xwork;

import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.opensymphony.xwork.ActionSupport;

public class CustomerProjectModifyActionTest extends AbstractTimeTestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testBasic() throws Exception {

        CustomerProjectModifyAction action = new CustomerProjectModifyAction();
        action.setSessionContext(this.sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(this.customer);
        action.setProjectModifyContext(new ProjectModifyContext());

        String s = action.execute();

        assertEquals(ActionSupport.INPUT, s);

    }

    public void testBasicAdd() throws Exception {

        CustomerProjectModifyAction action = new CustomerProjectModifyAction();
        action.setSessionContext(this.sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(this.customer);
        action.setProjectModifyContext(new ProjectModifyContext());

        action.setTargetProject(new Project());

        action.getTargetProject().setShortName("junitShort");
        action.getTargetProject().setLongName("Short test from witin Junit.");
        action.getTargetProject().setPostable(new Boolean(true));
        action.getTargetProject().setActive(true);

        action.setSaveProject("save");

        String s = action.execute();

        assertEquals(ActionSupport.SUCCESS, s);

    }

    public void testBasicAdd_Fails_MissingData() throws Exception {

        CustomerProjectModifyAction action = new CustomerProjectModifyAction();
        action.setSessionContext(this.sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(this.customer);
        action.setProjectModifyContext(new ProjectModifyContext());

        action.setTargetProject(new Project());

        // action.getTargetProject().setShortName("junitShort");
        // action.getTargetProject().setLongName("Short test from witin
        // Junit.");
        // action.getTargetProject().setPostable(new Boolean(true));
        // action.getTargetProject().setActive(true);

        action.setSaveProject("save");

        String s = action.execute();

        assertTrue(action.hasErrors());

        assertEquals(ActionSupport.INPUT, s);
    }

    public void testBasicAdd_Fails_DuplicateShortName() throws Exception {

        CustomerProjectModifyAction action = new CustomerProjectModifyAction();
        action.setSessionContext(this.sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(this.customer);
        action.setProjectModifyContext(new ProjectModifyContext());

        action.setTargetProject(new Project());

        action.getTargetProject().setShortName(project.getShortName());
        action.getTargetProject().setLongName("Short test from witin Junit.");
        action.getTargetProject().setPostable(new Boolean(true));
        action.getTargetProject().setActive(true);

        action.setSaveProject("save");

        String s = action.execute();

        assertTrue(action.hasErrors());

        assertEquals(ActionSupport.INPUT, s);
    }

    public void testBasicEdit_Succeeds() throws Exception {

        Project project2 = ProjectTestFactory.getPersisted(
                this.systemOwner, this.customer);

        CustomerProjectModifyAction action = new CustomerProjectModifyAction();
        action.setSessionContext(this.sessionContext);
        action.setCustomerModifyContext(new CustomerModifyContext());
        action.getCustomerModifyContext().setTargetCustomer(this.customer);
        action.setProjectModifyContext(new ProjectModifyContext());

        action.setTargetProject(project2);
        action.getProjectModifyContext().setTargetProject(project2);

        action.getTargetProject().setShortName(project2.getShortName());
        action.getTargetProject().setLongName(project2.getLongName()+"1");
        action.getTargetProject().setPostable(project2.isPostable());
        action.getTargetProject().setActive(project2.isActive());
        

        action.setSaveProject("save");
        
        String s = action.execute();

        assertTrue(!action.hasErrors());

        assertEquals(ActionSupport.SUCCESS, s);        
    }

}
