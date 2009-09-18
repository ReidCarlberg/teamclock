/*
 * Created on Jun 27, 2006
 *
 */
package com.fivesticks.time.todo;

import java.util.List;

import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.dashboard.xwork.DashboardContext;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.todo.util.ToDoProjectVO;

public class ToDoServiceDelegateImplTest2 extends AbstractTimeTestCase {

    /*
     * makes sure it does the very basic and doesn't crash.
     */
    public void testGetDistinctProjects() throws Exception {

        List projects = ToDoServiceDelegateFactory.factory.build(
                this.sessionContext).findDistinctToDoProjects(
                new DashboardContext());

    }
    
    public void testCount() throws Exception {
        
        ToDo testA = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);
        ToDo testB = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);

        List projects = ToDoServiceDelegateFactory.factory.build(
                this.sessionContext).findDistinctToDoProjects(
                new DashboardContext());
        
        assertTrue(projects.size() == 1);
        
        ToDoProjectVO one = (ToDoProjectVO) projects.toArray()[0];
        
        assertEquals(new Long(2), one.getCount());
    }
    
    public void testCountMultipleProjects() throws Exception {
        
        ToDo testA = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);
        ToDo testB = ToDoTestFactory.getPersisted(this.customer, this.project, this.user, this.systemOwner);

        Project project2 = ProjectTestFactory.getPersisted(this.systemOwner, this.customer);
        ToDo testC = ToDoTestFactory.getPersisted(this.customer, project2, this.user, this.systemOwner);
        
        List projects = ToDoServiceDelegateFactory.factory.build(
                this.sessionContext).findDistinctToDoProjects(
                new DashboardContext());
        
        assertTrue(projects.size() == 2);
        
        ToDoProjectVO one = (ToDoProjectVO) projects.toArray()[0];        
        assertEquals(new Long(2), one.getCount());

        ToDoProjectVO two = (ToDoProjectVO) projects.toArray()[1];        
        assertEquals(new Long(1), two.getCount());

    }
    
 
}
