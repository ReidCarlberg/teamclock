/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.todo;

import java.util.Collection;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.employee.team.Team;
import com.fivesticks.time.employee.team.TeamRoleType;
import com.fivesticks.time.employee.team.TeamServiceDelegate;
import com.fivesticks.time.employee.team.TeamServiceDelegateFactory;
import com.fivesticks.time.employee.team.TeamTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserFactory;

/**
 * @author REID
 *  
 */
public class ToDoServiceDelegateTest extends AbstractTimeTestCase {

    



    public void testFindAllIncomplete() throws Exception {

        Collection before = ToDoServiceDelegateFactory.factory.build(
                this.sessionContext).findIncomplete();
        assertTrue(before.size() == 0);

        Customer fstxCustomer = CustomerTestFactory
                .getPersisted(systemOwner);
        Project fstxProject = ProjectTestFactory.getPersisted(
                systemOwner, fstxCustomer);
        User user = UserFactory.singleton.getPersisted();

        ToDo todo1 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
                user, systemOwner);

        Collection after = ToDoServiceDelegateFactory.factory
                .build(sessionContext).findIncomplete();
        assertTrue(after.size() == 1);

        todo1.setComplete(true);

        new ToDoDAOFactory().build().save(todo1);

        Collection fina = ToDoServiceDelegateFactory.factory.build(sessionContext)
                .findIncomplete();
        assertTrue(fina.size() == 0);

    }

    public void testFindAllIncompleteByCustomer() throws Exception {

        Customer fstxCustomer1 = CustomerTestFactory
                .getPersisted(systemOwner);
        Project fstxProject1 = ProjectTestFactory.getPersisted(
                systemOwner, fstxCustomer1);
        Project fstxProject1a = ProjectTestFactory.getPersisted(
                systemOwner, fstxCustomer1);
        User user1 = UserFactory.singleton.getPersisted();

        ToDo todo1 = ToDoTestFactory.getPersisted(fstxCustomer1, fstxProject1,
                user1, systemOwner);
        ToDo todo1a = ToDoTestFactory.getPersisted(fstxCustomer1,
                fstxProject1a, user1, systemOwner);

        Customer fstxCustomer2 = CustomerTestFactory
                .getPersisted(systemOwner);
        Project fstxProject2 = ProjectTestFactory.getPersisted(
                systemOwner, fstxCustomer2);
        User user2 = UserFactory.singleton.getPersisted();

        ToDo todo2 = ToDoTestFactory.getPersisted(fstxCustomer2, fstxProject2,
                user2, systemOwner);

        Collection all = ToDoServiceDelegateFactory.factory.build(sessionContext)
                .findIncomplete();
        assertTrue(all.size() == 3);

        Collection cust1 = ToDoServiceDelegateFactory.factory
                .build(sessionContext).findIncomplete(fstxCustomer1);
        assertTrue(cust1.size() == 2);

        todo1.setComplete(true);
        new ToDoDAOFactory().build().save(todo1);

        Collection cust1After = ToDoServiceDelegateFactory.factory.build(
                this.sessionContext).findIncomplete(fstxCustomer1);
        assertTrue(cust1After.size() == 1);
    }

    public void testFindAllIncompleteByAssignee() throws Exception {
        Customer fstxCustomer1 = CustomerTestFactory
                .getPersisted(systemOwner);
        Project fstxProject1 = ProjectTestFactory.getPersisted(
                systemOwner, fstxCustomer1);
        User user1 = UserFactory.singleton.getPersisted();

        ToDo todo1 = ToDoTestFactory.getPersisted(fstxCustomer1, fstxProject1,
                user1, systemOwner);
        todo1.setAssignedToUser(user1.getUsername());
        new ToDoDAOFactory().build().save(todo1);

        Customer fstxCustomer2 = CustomerTestFactory
                .getPersisted(systemOwner);
        Project fstxProject2 = ProjectTestFactory.getPersisted(
                systemOwner, fstxCustomer2);
        User user2 = UserFactory.singleton.getPersisted();

        ToDo todo2 = ToDoTestFactory.getPersisted(fstxCustomer2, fstxProject2,
                user2, systemOwner);
        todo2.setAssignedToUser(user2.getUsername());
        new ToDoDAOFactory().build().save(todo2);

        Collection all = ToDoServiceDelegateFactory.factory.build(sessionContext)
                .findIncomplete();
        assertTrue(all.size() == 2);

        Collection cust1 = ToDoServiceDelegateFactory.factory
                .build(sessionContext).findIncompleteByAssignee(user1);
        assertTrue(cust1.size() == 1);

        todo1.setComplete(true);
        new ToDoDAOFactory().build().save(todo1);

        Collection cust1After = ToDoServiceDelegateFactory.factory.build(
                this.sessionContext).findIncompleteByAssignee(user1);
        assertTrue(cust1After.size() == 0);
    }

    public void testFindAllIncompleteByAssigneeAndPriority() throws Exception {
        Customer fstxCustomer1 = CustomerTestFactory
                .getPersisted(systemOwner);
        Project fstxProject1 = ProjectTestFactory.getPersisted(
                systemOwner, fstxCustomer1);
        User user1 = UserFactory.singleton.getPersisted();

        ToDo todo1 = ToDoTestFactory.getPersisted(fstxCustomer1, fstxProject1,
                user1, systemOwner);
        todo1.setAssignedToUser(user1.getUsername());
        todo1.setPriority(ToDoPriorityTypeEnum.Q1.getName());
        new ToDoDAOFactory().build().save(todo1);

        Customer fstxCustomer2 = CustomerTestFactory
                .getPersisted(systemOwner);
        Project fstxProject2 = ProjectTestFactory.getPersisted(
                systemOwner, fstxCustomer2);
        User user2 = UserFactory.singleton.getPersisted();

        ToDo todo2 = ToDoTestFactory.getPersisted(fstxCustomer2, fstxProject2,
                user2, systemOwner);
        todo2.setAssignedToUser(user2.getUsername());
        new ToDoDAOFactory().build().save(todo2);

        Collection all = ToDoServiceDelegateFactory.factory.build(sessionContext)
                .findIncomplete();
        assertTrue(all.size() == 2);

        Collection cust1 = ToDoServiceDelegateFactory.factory
                .build(sessionContext).findIncompleteByAssignee(
                        user1.getUsername(), ToDoPriorityTypeEnum.Q1.getName());
        assertTrue(cust1.size() == 1);

        todo1.setComplete(true);
        new ToDoDAOFactory().build().save(todo1);

        Collection cust1After = ToDoServiceDelegateFactory.factory.build(
                sessionContext).findIncompleteByAssignee(user1);
        assertTrue(cust1After.size() == 0);
    }

//    public void testResequence() throws Exception {
//
//        Collection before = ToDoServiceDelegateFactory.factory.build(
//                sessionContext).findIncomplete();
//        assertTrue(before.size() == 0);
//
//        FstxCustomer fstxCustomer = FstxCustomerTestFactory
//                .getPersisted(systemOwner);
//        FstxProject fstxProject = FstxProjectTestFactory.getPersisted(
//                systemOwner, fstxCustomer);
//        User user = UserFactory.singleton.getPersisted();
//
//        ToDo todo1 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
//                user, systemOwner, ToDoPriorityTypeEnum.Q1);
//
//        ToDo todo2 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
//                user, systemOwner, ToDoPriorityTypeEnum.Q1);
//
//        ToDo todo3 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
//                user, systemOwner, ToDoPriorityTypeEnum.Q1);
//
//        Collection beforeSequence = ToDoServiceDelegateFactory.factory.build(
//                sessionContext).findIncomplete();
//        assertTrue(beforeSequence.size() == 3);
//
//        for (Iterator iter = beforeSequence.iterator(); iter.hasNext();) {
//            ToDo element = (ToDo) iter.next();
//            assertTrue(element.getSequence() == null);
//        }
//
//        ToDoServiceDelegateFactory.factory.build(sessionContext)
//                .resequenceIncomplete(null, ToDoPriorityTypeEnum.Q1);
//
//        Collection afterSequence = ToDoServiceDelegateFactory.factory.build(
//                sessionContext).findIncomplete();
//        assertTrue(afterSequence.size() == 3);
//
//        int i = 0;
//
//        for (Iterator iter = afterSequence.iterator(); iter.hasNext();) {
//            i++;
//            ToDo element = (ToDo) iter.next();
//            assertTrue(element.getSequence() != null);
//            assertTrue(element.getSequence().intValue() == i);
//        }
//
//    }

//    public void testResequenceSooner() throws Exception {
//
//        Collection before = ToDoServiceDelegateFactory.factory.build(
//                sessionContext).findIncomplete();
//        assertTrue(before.size() == 0);
//
//        FstxCustomer fstxCustomer = FstxCustomerTestFactory
//                .getPersisted(systemOwner);
//        FstxProject fstxProject = FstxProjectTestFactory.getPersisted(
//                systemOwner, fstxCustomer);
//        User user = UserFactory.singleton.getPersisted();
//
//        ToDo todo1 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
//                user, systemOwner, ToDoPriorityTypeEnum.Q1, 1);
//
//        ToDo todo2 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
//                user, systemOwner, ToDoPriorityTypeEnum.Q1, 2);
//
//        ToDo todo3 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
//                user, systemOwner, ToDoPriorityTypeEnum.Q1, 3);
//
//        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory
//                .build(sessionContext);
//
//        sd.resequenceIncompleteSooner(todo3);
//
//        Collection after = sd.findIncompleteByAssignee(user, new Boolean(true));
//
//        assertTrue(after.size() == 3);
//
//        assertTrue(((ToDo) after.toArray()[0]).getId().longValue() == todo1
//                .getId().longValue());
//
//        assertTrue(((ToDo) after.toArray()[1]).getId().longValue() == todo3
//                .getId().longValue());
//
//        assertTrue(((ToDo) after.toArray()[2]).getId().longValue() == todo2
//                .getId().longValue());
//
//    }

//    public void testResequenceLater() throws Exception {
//
//        Collection before = ToDoServiceDelegateFactory.factory.build(
//                sessionContext).findIncomplete();
//        assertTrue(before.size() == 0);
//
//        FstxCustomer fstxCustomer = FstxCustomerTestFactory
//                .getPersisted(systemOwner);
//        FstxProject fstxProject = FstxProjectTestFactory.getPersisted(
//                systemOwner, fstxCustomer);
//        User user = UserFactory.singleton.getPersisted();
//
//        ToDo todo1 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
//                user, systemOwner, ToDoPriorityTypeEnum.Q1, 1);
//
//        ToDo todo2 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
//                user, systemOwner, ToDoPriorityTypeEnum.Q1, 2);
//
//        ToDo todo3 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
//                user, systemOwner, ToDoPriorityTypeEnum.Q1, 3);
//
//        ToDoServiceDelegate sd = ToDoServiceDelegateFactory.factory
//                .build(sessionContext);
//
//        sd.resequenceIncompleteLater(todo1);
//
//        Collection after = sd.findIncompleteByAssignee(user, new Boolean(true));
//
//        assertTrue(after.size() == 3);
//
//        assertTrue(((ToDo) after.toArray()[0]).getId().longValue() == todo2
//                .getId().longValue());
//
//        assertTrue(((ToDo) after.toArray()[1]).getId().longValue() == todo1
//                .getId().longValue());
//
//        assertTrue(((ToDo) after.toArray()[2]).getId().longValue() == todo3
//                .getId().longValue());
//
//    }

    public void testStatus() throws Exception {

        Collection before = ToDoServiceDelegateFactory.factory.build(
                sessionContext).findIncomplete();
        assertTrue(before.size() == 0);

        Customer fstxCustomer = CustomerTestFactory
                .getPersisted(systemOwner);
        Project fstxProject = ProjectTestFactory.getPersisted(
                systemOwner, fstxCustomer);
        User user = UserFactory.singleton.getPersisted();

        ToDo todo1 = ToDoTestFactory.getPersisted(fstxCustomer, fstxProject,
                user, systemOwner);

        todo1.setComplete(true);
        todo1.setStatus("status");

        new ToDoDAOFactory().build().save(todo1);

        ToDoCriteriaParameters filter = new ToDoCriteriaParameters();
        filter.setStatus("status");

        Collection fina = ToDoServiceDelegateFactory.factory.build(sessionContext)
                .find(filter);
        assertTrue(fina.size() == 1);

        ToDoCriteriaParameters filter2 = new ToDoCriteriaParameters();
        filter2.setStatus("statusXX");

        Collection fina2 = ToDoServiceDelegateFactory.factory
                .build(sessionContext).find(filter2);
        assertTrue(fina2.size() == 0);

    }
    
    public void testAssignedToTeam() throws Exception {
        
        
        
        Customer cust1 = CustomerTestFactory.getPersisted(this.systemOwner);
        Project proj1 = ProjectTestFactory.getPersisted(this.systemOwner, cust1);
        
        User user1 = SystemUserTestFactory.singleton.buildOwner(this.systemOwner);
        User user2 = SystemUserTestFactory.singleton.buildOwner(this.systemOwner);
        User user3 = SystemUserTestFactory.singleton.buildOwner(this.systemOwner);
        
        
        Team team = TeamTestFactory.getTeam(this.systemOwner);
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(this.systemOwner);
        
        sd.join(team,user1,TeamRoleType.STANDARD);
        sd.join(team,user2,TeamRoleType.STANDARD);
        
        ToDo todo1 = ToDoTestFactory.getPersistedAssigned(cust1,proj1,user1,this.systemOwner);
        ToDo todo2 = ToDoTestFactory.getPersistedAssigned(cust1,proj1,user2,this.systemOwner);
        ToDo todo3 = ToDoTestFactory.getPersistedAssigned(cust1,proj1,user3,this.systemOwner);
        
        ToDoCriteriaParameters p = new ToDoCriteriaParameters();
        p.setAssignedToUser(user1.getUsername());
        
        assertEquals(1,ToDoServiceDelegateFactory.factory.build(this.sessionContext).find(p).size());
        
        p.setAssignedToUser(null);
        p.setAssignedToTeamName(team.getNameShort());
        
        assertEquals(2,ToDoServiceDelegateFactory.factory.build(this.sessionContext).find(p).size());

        p.setAssignedToUser(user2.getUsername());
        p.setAssignedToTeamName(null);
        
        assertEquals(1,ToDoServiceDelegateFactory.factory.build(this.sessionContext).find(p).size());

        p.setAssignedToUser(user3.getUsername());
        p.setAssignedToTeamName(null);
        
        assertEquals(1,ToDoServiceDelegateFactory.factory.build(this.sessionContext).find(p).size());

    }

}

