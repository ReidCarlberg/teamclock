/*
 * Created on Jul 6, 2006 by Reid
 */
package com.fivesticks.time.customer;

import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityBD;
import com.fivesticks.time.activity.ActivityBDFactory;
import com.fivesticks.time.activity.ActivityTestFactory;
import com.fivesticks.time.calendar.CalendarBD;
import com.fivesticks.time.calendar.CalendarBDFactory;
import com.fivesticks.time.calendar.CalendarTestFactory;
import com.fivesticks.time.calendar.TcCalendar;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegate;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.ToDoTestFactory;

public class CustomerServiceDelegateImplTestDelete extends AbstractTimeTestCase {

    public void testBasic() throws Exception {

        long id = this.customer.getId().longValue();

        CustomerServiceDelegate csd = CustomerServiceDelegateFactory.factory
                .build(this.sessionContext);

        csd.delete(this.customer);

        try {
            Customer customer = csd.getFstxCustomer(new Long(id));
            assertTrue(false);
        } catch (Exception e) {
        }
    }

    /*
     * 2006-07-07 has some problems with deleting extra external users so I'm
     * throwing a more detailed test in.
     */
    public void testDetailed() throws Exception {

        /*
         * customer 1
         */
        Customer cust1 = CustomerTestFactory
                .getPersisted(this.systemOwner);
        Project proj1a = ProjectTestFactory.getPersisted(
                this.systemOwner, cust1);

        ToDo todo1a = ToDoTestFactory.getPersisted(cust1, proj1a, this.user,
                this.systemOwner);

        Activity act1a = ActivityTestFactory.singleton.build(
                this.systemOwner, proj1a, this.user);

        TcCalendar cal1a = CalendarTestFactory.build(this.systemOwner,
                this.user, proj1a);

        /*
         * customer 2
         */
        Customer cust2 = CustomerTestFactory
                .getPersisted(this.systemOwner);
        Project proj2a = ProjectTestFactory.getPersisted(
                this.systemOwner, cust2);

        ToDo todo2a = ToDoTestFactory.getPersisted(cust2, proj2a, this.user,
                this.systemOwner);

        Activity act2a = ActivityTestFactory.singleton.build(
                this.systemOwner, proj2a, this.user);

        TcCalendar cal2a = CalendarTestFactory.build(this.systemOwner,
                this.user, proj2a);

        /*
         * let's delete cust1
         */

        CustomerServiceDelegate csd = CustomerServiceDelegateFactory.factory
                .build(this.sessionContext);

        ProjectServiceDelegate psd = ProjectServiceDelegateFactory.factory
                .build(this.sessionContext);

        ToDoServiceDelegate tsd = ToDoServiceDelegateFactory.factory
                .build(this.sessionContext);

        ActivityBD asd = ActivityBDFactory.factory
                .build(this.sessionContext);

        CalendarBD calbd = CalendarBDFactory.factory
                .build(this.sessionContext);

        csd.delete(cust1);

        /* verify that cust1 is gone */
        try {
            csd.getFstxCustomer(cust1.getId());
            assertTrue(false);
        } catch (Exception e) {

        }

        try {
            psd.getFstxProject(proj1a.getId());
            assertTrue(false);
        } catch (Exception e) {

        }

        assertNull(tsd.get(todo1a.getId()));

        try {
            asd.get(act1a.getId());
            assertTrue(false);
        } catch (Exception e) {

        }

        try {
            calbd.get(cal1a.getId());
            assertTrue(false);
        } catch (Exception e) {

        }

        /* verify thta cust2 is still there */
        try {
            csd.getFstxCustomer(cust2.getId());
            
        } catch (Exception e) {
            assertTrue(false);
        }

        try {
            psd.getFstxProject(proj2a.getId());
            
        } catch (Exception e) {
            assertTrue(false);
        }

        assertNotNull(tsd.get(todo2a.getId()));

        try {
            asd.get(act2a.getId());
            
        } catch (Exception e) {
            assertTrue(false);
        }

        try {
            calbd.get(cal2a.getId());

        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    /*
     * 2006-09-01 reid 
     * 
     * right now this deletes all associated contacts.
     */
    public void testDeleteAssociatedContacts() throws Exception {
        
    }
}
