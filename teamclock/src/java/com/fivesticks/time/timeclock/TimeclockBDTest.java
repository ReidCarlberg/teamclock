package com.fivesticks.time.timeclock;

import java.util.Collection;

import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDFactory;

/**
 * @author REID
 *  
 */
public class TimeclockBDTest extends AbstractTimeTestCase {

//    private SystemOwner systemOwner;
//
//    private SessionContext sessionContext;

//    private TimeclockFilterParameters selectAll = new TimeclockFilterParametersBuilder()
//            .buildSelectAll();
//
//    private Log log = LogFactory.getLog(this.getClass());
    
    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
//        JunitSettings.initializeTestSystem();
//        systemOwner = SystemOwnerServiceDelegate.factory.build().get(
//                UserBD.factory.build().getByUsername("admin"));
//        sessionContext = new SessionContext();
//        sessionContext.setSystemOwner(systemOwner);
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    /**
     * tests clock in
     * 
     * @throws Exception
     */
    public void testBasic_B() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail("admin@fivesticks.com");

        TimeclockFilterParameters p = new TimeclockFilterParameters();
        p.setUsername(user.getUsername());
        Collection listBefore = TiimeclockBDFactory.factory.build(sessionContext).searchByFilter(p);

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");

        Collection listAfter = TiimeclockBDFactory.factory.build(sessionContext).searchByFilter(p);

        assertTrue(listAfter.size() == listBefore.size() + 1);

        Timeclock one = (Timeclock) listAfter.toArray()[listAfter
                .toArray().length - 1];

        assertTrue(one.getEvent().equals(
                TimeclockEventEnum.CLOCK_IN.getName()));
    }

    /**
     * tests clock oiut
     * Nick 2005-10-13 This failed on the ant run, but passes here!
     * @throws Exception
     */
    public void testBasic_C() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail("admin@fivesticks.com");

        TimeclockFilterParameters p = new TimeclockFilterParameters();
        p.setUsername(user.getUsername());

        
        Collection listBefore = TiimeclockBDFactory.factory.build(sessionContext).searchByFilter(p);

        
        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");
        Thread.sleep(1000);
        
        TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");

        
        Collection listAfter = TiimeclockBDFactory.factory.build(sessionContext).searchByFilter(p);
        
        assertTrue(listAfter.size() == listBefore.size() + 2);

        Timeclock one = (Timeclock) listAfter.toArray()[listAfter
                .toArray().length - 1];

        assertTrue(one.getEvent().equals(
                TimeclockEventEnum.CLOCK_OUT.getName()));
    }

    /**
     * tests break start
     * 
     * @throws Exception
     */
    public void testBasic_D() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail("admin@fivesticks.com");

        TimeclockFilterParameters p = new TimeclockFilterParameters();
        p.setUsername(user.getUsername());
        Collection listBefore = TiimeclockBDFactory.factory.build(sessionContext).searchByFilter(p);

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");
        
        TiimeclockBDFactory.factory.build(sessionContext).breakStart(user,"testip");

        Collection listAfter = TiimeclockBDFactory.factory.build(sessionContext).searchByFilter(p);

        assertTrue(listAfter.size() == listBefore.size() + 2);

        Timeclock one = (Timeclock) listAfter.toArray()[listAfter
                .toArray().length - 1];

        
        assertTrue(one.getEvent().equals(
                TimeclockEventEnum.BREAK_START.getName()));
    }

    /**
     * tests break stop
     * 
     * @throws Exception
     */
    public void testBasic_E() throws Exception {
        
        //here
        User user = UserBDFactory.factory.build().getUserByEmail("admin@fivesticks.com");

        TimeclockFilterParameters p = new TimeclockFilterParameters();
        p.setUsername(user.getUsername());
        Collection listBefore = TiimeclockBDFactory.factory.build(sessionContext).searchByFilter(p);

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");
        TiimeclockBDFactory.factory.build(sessionContext).breakStart(user,"testip");
        TiimeclockBDFactory.factory.build(sessionContext).breakStop(user,"testip");

        Collection listAfter = TiimeclockBDFactory.factory.build(sessionContext).searchByFilter(p);

        assertTrue(listAfter.size() == listBefore.size() + 3);

        Timeclock one = (Timeclock) listAfter.toArray()[listAfter
                .toArray().length - 1];

        assertTrue(one.getEvent().equals(
                TimeclockEventEnum.BREAK_STOP.getName()));
    }

    /**
     * already clocked in.
     * 
     * @throws Exception
     */
    public void testException_A() throws Exception {

        User user = UserBDFactory.factory.build().getUserByEmail("admin@fivesticks.com");

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");

        try {
            TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");
            assertTrue(false);
        } catch (TimeclockBDException e) {

        }

    }

    /**
     * already clocked out.
     * 
     * @throws Exception
     */
    public void testException_B() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail("admin@fivesticks.com");

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");
        TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");
        try {
            TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");
            assertTrue(false);
        } catch (TimeclockBDException e) {

        }

    }

    /**
     * not clocked in for the break
     * 
     * @throws Exception
     */
    public void testException_C() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail("admin@fivesticks.com");

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");
        TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");
        try {
            TiimeclockBDFactory.factory.build(sessionContext).breakStart(user,"testip");
            assertTrue(false);
        } catch (TimeclockBDException e) {

        }

    }

    /**
     * not clocked in for the break
     * 
     * @throws Exception
     */
    public void testException_D() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail("admin@fivesticks.com");

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");
        TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");
        try {
            TiimeclockBDFactory.factory.build(sessionContext).breakStop(user,"testip");
            assertTrue(false);
        } catch (TimeclockBDException e) {

        }

    }

    public void testClockOutOnBreakFails() throws Exception {
        User user = UserBDFactory.factory.build().getUserByEmail("admin@fivesticks.com");

        TiimeclockBDFactory.factory.build(sessionContext).punchIn(user,"testip");
        TiimeclockBDFactory.factory.build(sessionContext).breakStart(user,"testip");

        try {
            TiimeclockBDFactory.factory.build(sessionContext).punchOut(user,"testip");
            assertTrue(false);
        } catch (TimeclockBDException e) {

        }

    }

}