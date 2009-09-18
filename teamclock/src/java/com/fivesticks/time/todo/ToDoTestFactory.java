/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.todo;

import java.util.Date;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

/**
 * @author REID
 *  
 */
public class ToDoTestFactory {

    private static int counter = 0;

    public static ToDo getUnpersisted(Customer fstxCustomer,
            Project fstxProject, User enteredBy, SystemOwner systemOwner) {

        counter++;

        ToDo ret = new ToDo();

        /*
         * 2005-9-27 Nick
         * Seems reasonable that we build this with an assignment. 
         */
       
        ret.setAssignedToUser(enteredBy.getUsername());
        ret.setComplete(false);
        ret.setCreateTimestamp(new Date());
        ret.setDeadlineTimestamp(null);
        ret.setDetail("Here is the detail! " + counter);
        ret.setEnteredByUser(enteredBy.getUsername());
        ret.setPriority(ToDoPriorityTypeEnum.Q1.getName());
        //Nick 2005-10-04 changes to an actual priortiy.
        ret.setProjectId(fstxProject.getId());
//        ret.setTag("---Simple---");
        return ret;
    }

    public static ToDo getPersisted(Customer fstxCustomer,
            Project fstxProject, User enteredBy, SystemOwner systemOwner)
            throws Exception {
       
        return getPersisted(fstxCustomer,
                fstxProject, enteredBy, systemOwner, "Simple");

    }

   public static ToDo getPersisted(Customer fstxCustomer,
            Project fstxProject, User enteredBy, SystemOwner systemOwner, String tag) throws Exception {
        
        ToDo ret = getUnpersisted(fstxCustomer, fstxProject, enteredBy,
                systemOwner);
//        ret.setTag(tag);
        //ToDoDAO.factory.build().save(ret);
        
        SessionContext sc = SessionContextTestFactory.getContext(systemOwner, enteredBy);

        ToDoServiceDelegateFactory.factory.build(sc).save(ret);

        return ret;
       
       
    }




    public static ToDo getPersistedAssigned(Customer fstxCustomer,
            Project fstxProject, User enteredBy, SystemOwner systemOwner)
            throws Exception {
        ToDo ret = getUnpersisted(fstxCustomer, fstxProject, enteredBy,
                systemOwner);
        ret.setAssignedToUser(enteredBy.getUsername());
        //ToDoDAO.factory.build().save(ret);

        SessionContext sc = SessionContextTestFactory.getContext(systemOwner, enteredBy);
        
        ToDoServiceDelegateFactory.factory.build(sc).save(ret);

        return ret;
    }


    
    public static ToDo getPersisted(Customer fstxCustomer,
            Project fstxProject, User enteredBy, SystemOwner systemOwner,
            ToDoPriorityTypeEnum toDoPriorityTypeEnum) throws Exception {
        ToDo ret = getUnpersisted(fstxCustomer, fstxProject, enteredBy,
                systemOwner);
        ret.setPriority(toDoPriorityTypeEnum.getName());
        ret.setAssignedToUser(enteredBy.getUsername());

        //ToDoDAO.factory.build().save(ret);
        SessionContext sc = SessionContextTestFactory.getContext(systemOwner, enteredBy);
        ToDoServiceDelegateFactory.factory.build(sc).save(ret);

        return ret;
    }

    public static ToDo getPersisted(Customer fstxCustomer,
            Project fstxProject, User enteredBy, SystemOwner systemOwner,
            ToDoPriorityTypeEnum toDoPriorityTypeEnum, int sequence)
            throws Exception {
        ToDo ret = getUnpersisted(fstxCustomer, fstxProject, enteredBy,
                systemOwner);
        ret.setPriority(toDoPriorityTypeEnum.getName());
        ret.setSequence(new Integer(sequence));
        ret.setAssignedToUser(enteredBy.getUsername());

        //ToDoDAO.factory.build().save(ret);
        SessionContext sc = SessionContextTestFactory.getContext(systemOwner, enteredBy);
        ToDoServiceDelegateFactory.factory.build(sc).save(ret);

        return ret;
    }


}