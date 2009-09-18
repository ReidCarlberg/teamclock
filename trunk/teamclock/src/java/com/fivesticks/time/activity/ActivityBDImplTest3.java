/*
 * Created on Jan 10, 2007
 *
 */
package com.fivesticks.time.activity;

import java.util.Collection;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionServiceDelegate;
import com.fivesticks.time.account.AccountTransactionServiceDelegateFactory;
import com.fivesticks.time.account.ObjectTransactionType;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegate;
import com.fivesticks.time.customer.util.CustomerAccountTransactionServiceDelegateFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class ActivityBDImplTest3 extends AbstractTimeTestCase {

    ActivityBD activityBD;
    
    protected void setUp() throws Exception {
        super.setUp();
        
         activityBD = ActivityBDFactory.factory.build(this.sessionContext);
    }
    
    public void testGetUnpostedActivity() throws Exception {
        
        
        
        
        Collection c = activityBD.getUnpostedActivityForPostableProjects(this.user.getUsername());
        
        assertTrue(c != null);
        
        log.info(c.size());
        
        assertEquals(0, c.size());
    }
    
    public void testGetUnpostedActivityWithData() throws Exception {
        
        Project testProject = ProjectTestFactory.getPersistedPostable(this.systemOwner, this.customer);
        
        Activity testActivity = ActivityTestFactory.singleton.build(this.systemOwner, testProject, this.user);
        
        Collection c = activityBD.getUnpostedActivityForPostableProjects(this.user.getUsername());
        
        assertTrue(c != null);
        
        log.info(c.size());
        
        assertEquals(1, c.size());    
        
        AccountTransactionServiceDelegate accountTransactionServiceDelegate = CustomerAccountTransactionServiceDelegate.factory.buildTimeAccount(systemOwner);
        
        AccountTransaction at = accountTransactionServiceDelegate.decrease(this.customer, new Double(20.0), "Here are comments", this.user.getUsername());
        
        testActivity.setAcctId(at.getId());
        
        activityBD.save(testActivity);
        
        c = activityBD.getUnpostedActivityForPostableProjects(this.user.getUsername());
        
        assertTrue(c != null);
        
        log.info(c.size());
        
        assertEquals(0, c.size());         
    }

}
