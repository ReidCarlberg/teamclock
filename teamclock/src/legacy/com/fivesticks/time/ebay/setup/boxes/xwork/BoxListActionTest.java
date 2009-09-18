/*
 * Created on May 11, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.ebay.setup.boxes.Box;
import com.fivesticks.time.ebay.setup.boxes.BoxTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author Reid
 */
public class BoxListActionTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasic() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        BoxListAction action = new BoxListAction();
        
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));
        
        String r = action.execute();   
        
        assertTrue(r.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getBoxes().size() == 0);

    }
    
    public void testFindsOne() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        //just get one--we don't care what it is.
        Box box = BoxTestFactory.getPersisted(owner);
        
        assertTrue(box != null && box.getId() != null);
        BoxListAction action = new BoxListAction();
        
        action.setSessionContext(SessionContextTestFactory.getContext(owner,
                SystemUserTestFactory.singleton.buildOwner(owner)));

        String r = action.execute();
        
        assertTrue(r.equals(ActionSupport.SUCCESS));
        
        assertTrue(action.getBoxes().size() == 1);
    }
}
