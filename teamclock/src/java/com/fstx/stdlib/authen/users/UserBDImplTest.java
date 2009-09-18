/*
 * Created on Jul 15, 2005
 *
 */
package com.fstx.stdlib.authen.users;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

/**
 * @author Reid
 *
 */
public class UserBDImplTest extends AbstractTimeTestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        
    }
    
    public void testBasic() throws Exception {
        //place holder
    }
    
    public void testResetKey() throws Exception {
        
        UserBD bd = UserBDFactory.factory.build();
        bd.addResetKey(this.user);
        
        assertNotNull(this.user.getResetKey());
    }
    

}
