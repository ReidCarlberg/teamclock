/*
 * Created on Sep 10, 2003
 *
 */
package com.fivesticks.time.activity;

import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *  
 */
public class ActivityTestFactory {

    public static final ActivityTestFactory singleton = new ActivityTestFactory();

    private static int count = 0;

    public Activity build(SystemOwner systemOwner, User user1)
            throws Exception {

        Customer cust1 = CustomerTestFactory.getPersisted(systemOwner);

        Project project = ProjectTestFactory.getPersisted(systemOwner,
                cust1);

        return build(systemOwner, project, user1);

    }

    public Activity build(SystemOwner systemOwner, Project project,
            User user1) throws Exception {

        int id = ++count;

        Activity time = new Activity();

        time.setOwnerKey(systemOwner.getKey());
        time.setUsername(user1.getUsername());
        time.setProject(project.getShortName());
        time.setTask("task" + id);
        time.setComments("comments" + id);
        time.setProjectId(project.getId());
        time.setElapsed(new Double(123.123));
        time.setChargeableElapsed(new Double(123.123));

        SimpleDate sd = SimpleDate.factory.build();
        sd.advanceDay(id - 1);
        time.setDate(sd.getDate());

        time.setStart(new ActivityResolver(sd.getDate(), "10:30 a").resolve());

        sd.advanceDay(id + 2);
        time.setStop(new ActivityResolver(sd.getDate(), "10:30 a").resolve());

        //time = FstxActivityDAO.factory.build().save(time);
        
        
        time = ActivityBDFactory.factory.build(SessionContextTestFactory.getContext(systemOwner, user1)).save(time);

        
        return time;
    }

}