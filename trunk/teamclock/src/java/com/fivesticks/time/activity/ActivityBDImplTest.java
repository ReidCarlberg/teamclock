/*
 * Created on Apr 29, 2004
 *
 */
package com.fivesticks.time.activity;

import java.util.Date;

import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

/**
 * <p>
 * Formerly tested saving with Wrapper. Now, that's not used. Tests basic BD
 * save functionality.
 * 
 * @author Reid
 * @author Nick
 *  
 */
public class ActivityBDImplTest extends AbstractTimeTestCase {
//    private SystemOwner systemOwner;
//
//    private FstxProject project;
//
//    private User user;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
//    protected void setUp() throws Exception {
//        super.setUp();
////        JunitSettings.initializeTestSystem();
////        systemOwner = SystemOwnerServiceDelegate.factory.build().get(
////                UserBD.factory.build().getByUsername("admin"));
////        FstxCustomer customer = FstxCustomerTestFactory
////                .getPersisted(systemOwner);
////        project = FstxProjectTestFactory.getPersisted(systemOwner, customer);
////        user = UserServiceDelegate.factory.build(systemOwner)
////                .createNewUser("user1", "user1", "user1@fivesticks.com",
////                        UserTypeEnum.OWNERADMIN);
//    }



    public void testSave() throws Exception {
        Activity activity = new Activity();
        activity.setComments("comments");
        activity.setDate(new Date());
        activity.setElapsed(new Double(1.0));
        activity.setOwnerKey(systemOwner.getKey());
        activity.setProjectId(project.getId());
        activity.setStart(new Date());
        activity.setStop(new Date());
        activity.setTask("tasks");
        
        activity.setUsername(user.getUsername());
        activity.setProject(project.getShortName());
        ActivityBDFactory.factory.build(SessionContextTestFactory.getContext(systemOwner, user)).save(activity);

        assertTrue(activity.getId() != null);
    }

    //    /*
    //     * Test for void save(FstxActivityWrapper, String, AuthenticatedUser)
    //     *
    //     *
    //     * modify Existing, createNew, createNew for existing project start and
    // stop
    //     *
    //     */
    //    public void
    // testSaveFstxActivityWrapperStringAuthenticatedUser_ModifyExisting()
    //            throws Exception {
    //
    //        FstxActivityBD timeBD = FstxActivityBD.factory.build(systemOwner);
    //
    //        FstxActivity time = new FstxActivity();
    //        time.setDate(new Date());
    //        time.setId(new Long(1));
    //        time.setProjectId(new Long(1));
    //        time.setTask("testTask");
    //        time.setUsername("user1");
    //        FstxActivityWrapper wrapper = new FstxActivityWrapper(time);
    //        wrapper.setStartSimple("12:00");
    //        String newProjectName = "";
    //
    //        User one =
    // UserServiceDelegate.factory.build(systemOwner).createNewUser("user1","pass","email",UserTypeEnum.USERTYPE_OWNERADMIN);
    //        AuthenticatedUser aUser = AuthenticatedUser.factory.build(one);
    //
    //        timeBD.save(wrapper, newProjectName, aUser);
    //
    //        FstxActivity testActivity = timeBD.get(wrapper.getId());
    //        assertEquals(1, testActivity.getId().longValue());
    //
    //    }

    //    public void
    // testSaveFstxActivityWrapperStringAuthenticatedUser_CreateNew()
    //            throws Exception {
    //
    //        FstxActivityBD timeBD = FstxActivityBD.factory.build(systemOwner);
    //
    //        FstxActivity time = new FstxActivity();
    //        time.setDate(new Date());
    //        time.setId(new Long(1));
    //        time.setProjectId(new Long(1));
    //        time.setTask("testTask");
    //        time.setUsername("user1");
    //        FstxActivityWrapper wrapper = new FstxActivityWrapper(time);
    //        wrapper.setStartSimple("12:00");
    //
    //        String newProjectName = "newProject";
    //
    //        AuthenticatedUser user = new AuthenticatedUserFactoryImpl()
    //                .build(new User("user1", "pass", "email"));
    //
    //        timeBD.save(wrapper, newProjectName, user);
    //
    //        FstxActivity testActivity = timeBD.get(wrapper.getId());
    //        assertEquals(1, testActivity.getId().longValue());
    //        assertEquals(1, testActivity.getProjectId().longValue());
    //
    //    }
    //
    //    /*
    //     * This tests that if the user attemts to create a new project with an
    //     * existing name, the existing one will be used.
    //     */
    //    public void
    // testSaveFstxActivityWrapperStringAuthenticatedUser_CreateNewNotUnique()
    //            throws Exception {
    //
    //        FstxActivityBD timeBD = FstxActivityBD.factory.build(systemOwner);
    //
    //        FstxActivity time = new FstxActivity();
    //        time.setDate(new Date());
    //        time.setId(new Long(1));
    //        time.setProjectId(new Long(1));
    //        time.setTask("testTask");
    //        time.setUsername("user1");
    //        FstxActivityWrapper wrapper = new FstxActivityWrapper(time);
    //        wrapper.setStartSimple("12:00");
    //
    //        String newProjectName = "notUnique";
    //
    //        AuthenticatedUser user = new AuthenticatedUserFactoryImpl()
    //                .build(new User("user1", "pass", "email"));
    //
    //        timeBD.save(wrapper, newProjectName, user);
    //
    //        FstxActivity testActivity = timeBD.get(wrapper.getId());
    //        assertEquals(1, testActivity.getId().longValue());
    //        assertEquals(23, testActivity.getProjectId().longValue());
    //
    //    }
    //
    //    public void
    // testSaveFstxActivityWrapperStringAuthenticatedUser_TestElapsedActivity()
    //            throws Exception {
    //
    //        FstxActivityBD timeBD = FstxActivityBD.factory.build(systemOwner);
    //
    //        FstxActivity time = new FstxActivity();
    //        time.setDate(new Date());
    //        time.setId(new Long(1));
    //        time.setProjectId(new Long(1));
    //        time.setTask("testTask");
    //        time.setUsername("user1");
    //        FstxActivityWrapper wrapper = new FstxActivityWrapper(time);
    //        wrapper.setStartSimple("12:00");
    //        wrapper.setStopSimple("1:30p");
    //        String newProjectName = "notUnique";
    //
    //        AuthenticatedUser user = new AuthenticatedUserFactoryImpl()
    //                .build(new User("user1", "pass", "email"));
    //
    //        timeBD.save(wrapper, newProjectName, user);
    //
    //        FstxActivity testActivity = timeBD.get(wrapper.getId());
    //        assertEquals(1, testActivity.getId().longValue());
    //        assertEquals(23, testActivity.getProjectId().longValue());
    //        assertEquals(1.5, testActivity.getElapsed().doubleValue(), 0.001);
    //    }

}