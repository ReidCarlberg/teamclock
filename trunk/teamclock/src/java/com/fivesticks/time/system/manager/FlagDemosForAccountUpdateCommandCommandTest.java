/*
 * Created on Aug 25, 2006 by Reid
 */
package com.fivesticks.time.system.manager;

import java.util.Date;

import com.fivesticks.time.systemowner.AccountType;
import com.fivesticks.time.systemowner.RequiresUpdateAccountReasonType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class FlagDemosForAccountUpdateCommandCommandTest extends AbstractTimeTestCase {

    public void testBasicExecute() throws Exception {
        
        this.systemOwner.setRequiresAccountUpdate(false);
        
        this.systemOwner.setExpirationDate(new Date());
        
        this.systemOwner.setAccount(AccountType.DEMO.getName());
        
        SystemOwnerServiceDelegateFactory.factory.build().save(this.systemOwner);
        
        assertFalse(this.systemOwner.isRequiresAccountUpdate());
        
        assertNotNull(this.systemOwner.getExpirationDate());
        
        FlagDemosForAccountUpdateCommand cmd = new FlagDemosForAccountUpdateCommand();
        
        cmd.execute();
        
        SystemOwner soAfter = SystemOwnerServiceDelegateFactory.factory.build().get(this.systemOwner.getKey());
        
        assertTrue(soAfter.isRequiresAccountUpdate());
        
    }
    
    public void testUpdateIgnoresNoneDemoAccount() throws Exception {
        
        /*
         * here's one to affect
         */
        this.systemOwner.setRequiresAccountUpdate(false);
        
        this.systemOwner.setExpirationDate(new Date());
        
        this.systemOwner.setAccount(AccountType.DEMO.getName());
        
        SystemOwnerServiceDelegateFactory.factory.build().save(this.systemOwner);
        
        SystemOwner nonDemo = SystemOwnerTestFactory.getOwner();
        nonDemo.setAccountType(AccountType.STANDARD);
        nonDemo.setExpirationDate(new Date());
        SystemOwnerServiceDelegateFactory.factory.build().save(nonDemo);
        
        new FlagDemosForAccountUpdateCommand().execute();
        
        //vaidate not changed.
        SystemOwner postNonDemo = SystemOwnerServiceDelegateFactory.factory.build().get(nonDemo.getKey());
        
        assertNotNull(postNonDemo);
        
        assertFalse(postNonDemo.isRequiresAccountUpdate());
        
        
        
        //validate changed.
        SystemOwner soAfter = SystemOwnerServiceDelegateFactory.factory.build().get(this.systemOwner.getKey());
        assertTrue(soAfter.isRequiresAccountUpdate());        
        assertEquals(RequiresUpdateAccountReasonType.DEMO_OVER.getName(), soAfter.getRequiresAccountUpdateReason());
        
        
        
    }
}
