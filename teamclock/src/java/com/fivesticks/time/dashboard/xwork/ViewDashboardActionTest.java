/*
 * Created on Sep 22, 2005
 *  
 */
package com.fivesticks.time.dashboard.xwork;

import java.util.Collection;

import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoTestFactory;
import com.opensymphony.xwork.ActionSupport;

public class ViewDashboardActionTest extends AbstractTimeTestCase {

    protected void setUp() throws Exception {
        super.setUp();

      ToDo todo = ToDoTestFactory.getPersisted(this.customer,this.project,this.user,this.systemOwner);
      Collection col = ToDoServiceDelegateFactory.factory.build(this.sessionContext).findIncomplete();
    }

    public void testBasic() throws Exception {

        ViewDashboard action = new ViewDashboard();
        action.setSessionContext(this.sessionContext);
        action.setDashboardContext(new DashboardContext());
        assertEquals(ActionSupport.SUCCESS, action.execute());

    }

}