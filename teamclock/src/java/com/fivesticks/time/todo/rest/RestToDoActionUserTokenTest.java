/*
 * Created on Dec 14, 2005
 *
 */
package com.fivesticks.time.todo.rest;

import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.testutil.AbstractAPIBasedAccessTestCase;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoDisplayWrapper;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoTestFactory;
import com.fivesticks.time.ws.AbstractAuthBasedAuthenticationService;
import com.fivesticks.time.ws.xwork.AuthenticationBasedServiceSupport;
import com.opensymphony.xwork.Action;

public class RestToDoActionUserTokenTest extends AbstractAPIBasedAccessTestCase {

    RestToDoAction action;

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.testutil.AbstractAPIBasedAccessTestCase#setUp()
     */
    protected void setUp() throws Exception {

        super.setUp();

        action = new RestToDoAction();
        action
                .setAuthenticationBasedServiceSupport(new AuthenticationBasedServiceSupport());
        action.setToken(this.getUserToken());

    }

    public void testBasic() throws Exception {

        action.setAction(RestToDoActionType.LIST.getName());

        String r = action.execute();

        assertEquals(Action.SUCCESS, r);

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertNotNull(action.getListResults());

        assertEquals(0, action.getListResults().size());

    }

    public void testWithList() throws Exception {

        /*
         * quick setup -- add a couple of todos.
         */
        ToDoTestFactory.getPersisted(this.customer, this.project, this.user,
                this.systemOwner);

        action.setAction(RestToDoActionType.LIST.getName());

        String r = action.execute();

        assertEquals(Action.SUCCESS, r);

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertNotNull(action.getListResults());

        assertEquals(1, action.getListResults().size());

    }

    public void testWithListMultiplePriorities() throws Exception {
        ToDoTestFactory.getPersisted(this.customer, this.project, this.user,
                this.systemOwner);

        ToDo t = ToDoTestFactory.getPersisted(this.customer, this.project,
                this.user, this.systemOwner);
        t.setPriority(ToDoPriorityTypeEnum.Q2.getName());
        ToDoServiceDelegateFactory.factory.build(this.sessionContext).save(t);

        action.setAction(RestToDoActionType.LIST.getName());

        String r = action.execute();

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertNotNull(action.getListResults());

        assertEquals(2, action.getListResults().size());

        action.setPriority(ToDoPriorityTypeEnum.Q2.getName());

        r = action.execute();

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertNotNull(action.getListResults());

        assertEquals(1, action.getListResults().size());

    }
    
    public void testWithMultipleProjects() throws Exception {
        
        ToDoTestFactory.getPersisted(this.customer, this.project, this.user,
                this.systemOwner);

        Project project2 = ProjectTestFactory.getPersisted(this.systemOwner, this.customer);
        
        ToDo t = ToDoTestFactory.getPersisted(this.customer, this.project,
                this.user, this.systemOwner);
        t.setProjectId(project2.getId());
        ToDoServiceDelegateFactory.factory.build(this.sessionContext).save(t);

        action.setAction(RestToDoActionType.LIST.getName());

        String r = action.execute();

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertNotNull(action.getListResults());

        assertEquals(2, action.getListResults().size());

        action.setProjectKey(project2.getShortName());

        r = action.execute();

        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action
                .getResult());

        assertNotNull(action.getListResults());

        assertEquals(1, action.getListResults().size());
        
        assertEquals(((ToDoDisplayWrapper) action.getListResults().toArray()[0]).getProjectName(), project2.getShortName());
        
        
    }
    
    public void testSimplePost() throws Exception {

        assertEquals(0,ToDoServiceDelegateFactory.factory.build(this.sessionContext).findIncomplete().size());
        action.setAction(RestToDoActionType.POST.getName());

        action.setProjectKey(project.getShortName());
        action.setDetail("here is new detail");
        
        String r = action.execute();

        assertEquals(Action.SUCCESS,r);
        
        assertEquals(AbstractAuthBasedAuthenticationService.SUCCESS, action.getResult());
        
        assertEquals(1,ToDoServiceDelegateFactory.factory.build(this.sessionContext).findIncomplete().size());
        
        
    }
}
