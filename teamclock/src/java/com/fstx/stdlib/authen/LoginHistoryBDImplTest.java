/*
 * Created on Aug 16, 2005
 *
 */
package com.fstx.stdlib.authen;

import java.util.Collection;
import java.util.Date;

import junit.framework.TestCase;

import com.fivesticks.time.common.xwork.KeyValueVO;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

public class LoginHistoryBDImplTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        LoginHistoryBD bd = LoginHistoryBDFactory.factory.build();
        
        bd.recordLogin("user", new Date(), "here", owner.getKey());
        
        
    }
    
    public void testSearch() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        LoginHistoryBD bd = LoginHistoryBDFactory.factory.build();
        
        bd.recordLogin("user", new Date(), "here", owner.getKey());
        
        Collection c = bd.search(new LoginHistoryFilterParameters());
        
        assertEquals(1, c.size());
        
    }
    
    public void testSearchStatsByUser() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        LoginHistoryBD bd = LoginHistoryBDFactory.factory.build();
        
        bd.recordLogin("user", new Date(), "here", owner.getKey());

        Collection l = bd.searchStatsByUser(new LoginHistoryFilterParameters());
        
        assertEquals(1, l.size());
        
        KeyValueVO results = (KeyValueVO) l.toArray()[0];
        
//        assertEquals(2, results.length);
        
//        Integer count = (Integer) results[0];
        
        assertEquals("1",results.getKey());
        
//        String username = (String) results[1];
        
        assertEquals("user", results.getValue());
        
    }
    
    public void testSearchStatsByUserPartDeux() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        LoginHistoryBD bd = LoginHistoryBDFactory.factory.build();
        
        bd.recordLogin("user", new Date(), "here", owner.getKey());
        bd.recordLogin("user1", new Date(), "here", owner.getKey());
        bd.recordLogin("user1", new Date(), "here", owner.getKey());
        bd.recordLogin("user1", new Date(), "here", owner.getKey());

        Collection l = bd.searchStatsByUser(new LoginHistoryFilterParameters());
        
        assertEquals(2, l.size());
        
        KeyValueVO results1 = (KeyValueVO) l.toArray()[0];
        
//        assertEquals(2, results1.length);
        
//        Integer count = (Integer) results1[0];
        
        assertEquals("3",results1.getKey());
        
//        String username = (String) results1[1];
        
        assertEquals("user1", results1.getValue());
        
        KeyValueVO results2 = (KeyValueVO) l.toArray()[1];
        
//        assertEquals(2, results2.length);
        
//        Integer count2 = (Integer) results2[0];
        
        assertEquals("1",results2.getKey());
        
//        String username2 = (String) results2[1];
        
        assertEquals("user", results2.getValue());        
        
    }    


    public void testSearchStatsByOwner() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        LoginHistoryBD bd = LoginHistoryBDFactory.factory.build();
        
        bd.recordLogin("user", new Date(), "here", owner.getKey());

        Collection l = bd.searchStatsByOwner(new LoginHistoryFilterParameters());
        
        assertEquals(1, l.size());
        
        KeyValueVO results = (KeyValueVO) l.toArray()[0];
        
//        assertEquals(2, results.length);
        
//        Integer count = (Integer) results[0];
        
        assertEquals("1",results.getKey());
        
//        String username = (String) results[1];
        
        assertEquals(owner.getKey(), results.getValue());
        
    }
    
    public void testSearchStatsByOwnerPartDeux() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        SystemOwner owner2 = SystemOwnerTestFactory.getOwner();
        
        LoginHistoryBD bd = LoginHistoryBDFactory.factory.build();
        
        bd.recordLogin("user", new Date(), "here", owner.getKey());
        bd.recordLogin("user1", new Date(), "here", owner2.getKey());
        bd.recordLogin("user1", new Date(), "here", owner2.getKey());
        bd.recordLogin("user1", new Date(), "here", owner2.getKey());

        Collection l = bd.searchStatsByOwner(new LoginHistoryFilterParameters());
        
        assertEquals(2, l.size());
        
        KeyValueVO results1 = (KeyValueVO) l.toArray()[0];
        
//        assertEquals(2, results1.length);
        
//        Integer count = (Integer) results1[0];
        
        assertEquals("3",results1.getKey());
        
//        String username = (String) results1[1];
        
        assertEquals(owner2.getKey(), results1.getValue());
        
        KeyValueVO results2 = (KeyValueVO) l.toArray()[1];
        
//        assertEquals(2, results2.length);
        
//        Integer count2 = (Integer) results2[0];
        
        assertEquals("1",results2.getKey());
        
//        String username2 = (String) results2[1];
        
        assertEquals(owner.getKey(), results2.getValue());    
    }
    
}
