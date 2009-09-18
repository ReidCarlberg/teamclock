/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.customer;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author Reid
 *  
 */
public class TaskServiceDelegateImplTest extends TestCase {

    private SystemOwner systemOwner;

    /**
     * Constructor for FstxTaskBDImplTest.
     * 
     * @param arg0
     */
    public TaskServiceDelegateImplTest(String arg0) {
        super(arg0);
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        /*
         * this sets up an Admin user.
         */
        systemOwner = SystemOwnerServiceDelegateFactory.factory.build().get(
                UserBDFactory.factory.build().getByUsername("admin"));
    }

    public void testDaoAdd() throws Exception {

        Task t1 = new Task();
        t1.setNameShort("nameShort");
        t1.setNameLong("Longername");
        t1.setOwnerKey(systemOwner.getKey());

//        FstxTaskDAO dao = FstxTaskDAO.factory.build();
//
//        dao.save(t1);

        TaskServiceDelegateFactory.factory.build(systemOwner).save(t1);
        
        assertTrue(t1.getId() != null);

    }

    public void testBDFind() throws Exception {

        SessionContext sampleContext = new SessionContext();
        sampleContext.setSystemOwner(systemOwner);

        Task t1 = new Task();
        t1.setNameShort("nameShort");
        t1.setNameLong("Longername");
        t1.setOwnerKey(systemOwner.getKey());

        

        TaskServiceDelegateFactory.factory.build(systemOwner).save(t1);

        assertTrue(t1.getId() != null);

        Task t2 = TaskServiceDelegateFactory.factory.build(sampleContext).getTaskType(
                t1.getNameShort());

        assertTrue(t2 != null);

        assertTrue(t2.getId().equals(t1.getId()));

        assertTrue(t2.getNameShort().equals(t1.getNameShort()));

    }

    public void testGetAll() throws Exception {

        SessionContext sampleContext = new SessionContext();
        sampleContext.setSystemOwner(systemOwner);

        Collection before = TaskServiceDelegateFactory.factory.build(sampleContext)
                .getAllTaskTypes();

        assertTrue(before != null && before.size() == 1);

        Task t1 = new Task();
        t1.setNameShort("nameShort");
        t1.setNameLong("Longername");
        t1.setOwnerKey(systemOwner.getKey());

//        FstxTaskDAO dao = FstxTaskDAO.factory.build();

        TaskServiceDelegateFactory.factory.build(systemOwner).save(t1);

        assertTrue(t1.getId() != null);

        Collection after = TaskServiceDelegateFactory.factory.build(sampleContext)
                .getAllTaskTypes();

        assertTrue(after != null && after.size() == before.size() + 1);
    }

    public void testAdd() throws Exception {

        SessionContext sampleContext = new SessionContext();
        sampleContext.setSystemOwner(systemOwner);

        Collection before = TaskServiceDelegateFactory.factory.build(sampleContext)
                .getAllTaskTypes();

        assertTrue(before != null && before.size() == 1);

        TaskServiceDelegateFactory.factory.build(sampleContext).addTaskType("testTaskType");

        Collection after = TaskServiceDelegateFactory.factory.build(sampleContext)
                .getAllTaskTypes();

        assertTrue(after != null && after.size() == before.size() + 1);

    }

    public void testAddDuplicate() throws Exception {

        SessionContext sampleContext = new SessionContext();
        sampleContext.setSystemOwner(systemOwner);

        Collection before = TaskServiceDelegateFactory.factory.build(sampleContext)
                .getAllTaskTypes();

        assertTrue(before != null && before.size() == 1);

        TaskServiceDelegateFactory.factory.build(sampleContext).addTaskType("testTaskType");

        Collection after = TaskServiceDelegateFactory.factory.build(sampleContext)
                .getAllTaskTypes();

        assertTrue(after != null && after.size() == before.size() + 1);

        try {
            TaskServiceDelegateFactory.factory.build(sampleContext).addTaskType("testTaskType");
            assertTrue(false);
        } catch (Exception e) {

        }

    }
}