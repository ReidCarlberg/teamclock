/*
 * Created on Mar 12, 2005 by Reid
 */
package com.fstx.stdlib.authen.users;

import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class UserDAOImplTest extends TestCase {

    private Log log = LogFactory.getLog(this.getClass());
    
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        UserDAO dao = UserDAOFactory.factory.build();
        
        User u = new User();
        
        u.setUsername("username");
        u.setPassword("password");
        u.setPasswordExpires(new Date());
        u.setEmail("email@email.com");
        
        dao.save(u);
        
        
        assertTrue(u.getPasswordHash() != null);
        
        log.info("hash: " + u.getPasswordHash());
        
        assertTrue(u.getPassword() != null);
        
        assertTrue(u.getPassword().length() == 0);
        
        
    }

}
