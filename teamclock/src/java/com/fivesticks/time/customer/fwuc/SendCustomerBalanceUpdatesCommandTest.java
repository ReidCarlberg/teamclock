/*
 * Created on Aug 16, 2005
 *
 */
package com.fivesticks.time.customer.fwuc;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.customer.contactxx.Contact;
import com.fivesticks.time.customer.contactxx.ContactTestFactory;
import com.fivesticks.time.queuedmessages.QueuedMessageFilter;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegate;
import com.fivesticks.time.queuedmessages.QueuedMessageServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

public class SendCustomerBalanceUpdatesCommandTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testBasic() throws Exception {
        
        /*
         * RSC 2005-08-16 Should just start and complete without any issues.
         * 
         */
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        owner.setKey("CXZASTPKGU");
        SystemOwnerServiceDelegateFactory.factory.build().save(owner);
        
        SendCustomerBalanceUpdatesCommand command = new SendCustomerBalanceUpdatesCommand();
        
        command.execute();
        
    }
    
    public void testBasicWithOneProject() throws Exception {
        
        /*
         * RSC 2005-08-16 Should just start and complete without any issues.
         * 
         */
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        owner.setKey("CXZASTPKGU");
        SystemOwnerServiceDelegateFactory.factory.build().save(owner);
        
        Customer testCust = CustomerTestFactory.getPersisted(owner);
        
        Project testProj = ProjectTestFactory.getPersisted(owner, testCust);
        
        testProj.setActive(true);
        testProj.setPostable(new Boolean(true));
        ProjectServiceDelegateFactory.factory.build(owner).save(testProj);
        
        Contact testCont = ContactTestFactory.singleton.getPersisted(owner, testCust);
        
        
        
        
        SendCustomerBalanceUpdatesCommand command = new SendCustomerBalanceUpdatesCommand();
        
        command.execute();
        
        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory.build(owner.getKey());
        Collection message = sd.search(new QueuedMessageFilter());
        
        assertEquals(1, message.size());
    }    
    
    public void testBasicWithTwoProjects() throws Exception {
        
        /*
         * RSC 2005-08-16 Should just start and complete without any issues.
         * 
         */
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        owner.setKey("CXZASTPKGU");
        SystemOwnerServiceDelegateFactory.factory.build().save(owner);
        
        Customer testCust = CustomerTestFactory.getPersisted(owner);
        
        Project testProj = ProjectTestFactory.getPersisted(owner, testCust);
        testProj.setActive(true);
        testProj.setPostable(new Boolean(true));
        ProjectServiceDelegateFactory.factory.build(owner).save(testProj);

        Project testProj2 = ProjectTestFactory.getPersisted(owner, testCust);
        testProj2.setActive(true);
        testProj2.setPostable(new Boolean(true));
        ProjectServiceDelegateFactory.factory.build(owner).save(testProj2);
        
        Contact testCont = ContactTestFactory.singleton.getPersisted(owner, testCust);
        
        SendCustomerBalanceUpdatesCommand command = new SendCustomerBalanceUpdatesCommand();
        
        command.execute();
        
        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory.build(owner.getKey());
        Collection message = sd.search(new QueuedMessageFilter());
        
        assertEquals(1, message.size());
    }       
    
    public void testBasicWithTwoCustomers() throws Exception {
        
        /*
         * RSC 2005-08-16 Should just start and complete without any issues.
         * 
         */
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        owner.setKey("CXZASTPKGU");
        SystemOwnerServiceDelegateFactory.factory.build().save(owner);
        
        Customer testCust = CustomerTestFactory.getPersisted(owner);
        
        Project testProj = ProjectTestFactory.getPersisted(owner, testCust);
        testProj.setActive(true);
        testProj.setPostable(new Boolean(true));
        ProjectServiceDelegateFactory.factory.build(owner).save(testProj);
        
        Contact testCont = ContactTestFactory.singleton.getPersisted(owner, testCust);

        Customer testCust2 = CustomerTestFactory.getPersisted(owner);
        
        Project testProj2 = ProjectTestFactory.getPersisted(owner, testCust2);
        testProj2.setActive(true);
        testProj2.setPostable(new Boolean(true));
        ProjectServiceDelegateFactory.factory.build(owner).save(testProj2);
        
        Contact testCont2 = ContactTestFactory.singleton.getPersisted(owner, testCust2);
        
        SendCustomerBalanceUpdatesCommand command = new SendCustomerBalanceUpdatesCommand();
        
        command.execute();
        
        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory.build(owner.getKey());
        Collection message = sd.search(new QueuedMessageFilter());
        
        assertEquals(2, message.size());
    }       
    
    public void testBasicWithTwoNonPostableProjects() throws Exception {
        
        /*
         * RSC 2005-08-16 Should just start and complete without any issues.
         * 
         */
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        owner.setKey("CXZASTPKGU");
        SystemOwnerServiceDelegateFactory.factory.build().save(owner);
        
        Customer testCust = CustomerTestFactory.getPersisted(owner);
        
        Project testProj = ProjectTestFactory.getPersisted(owner, testCust);
        testProj.setActive(true);
        testProj.setPostable(new Boolean(false));
        ProjectServiceDelegateFactory.factory.build(owner).save(testProj);

        Project testProj2 = ProjectTestFactory.getPersisted(owner, testCust);
        testProj2.setActive(true);
        testProj2.setPostable(new Boolean(false));
        ProjectServiceDelegateFactory.factory.build(owner).save(testProj2);
        
        Contact testCont = ContactTestFactory.singleton.getPersisted(owner, testCust);
        
        SendCustomerBalanceUpdatesCommand command = new SendCustomerBalanceUpdatesCommand();
        
        command.execute();
        
        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory.build(owner.getKey());
        Collection message = sd.search(new QueuedMessageFilter());
        
        assertEquals(0, message.size());
    }          

    public void testBasicWithTwoPostableProjectsAndMultipleOwners() throws Exception {
        
        /*
         * RSC 2005-08-16 Should just start and complete without any issues.
         * 
         */
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        owner.setKey("CXZASTPKGU");
        SystemOwnerServiceDelegateFactory.factory.build().save(owner);
        
        Customer testCust = CustomerTestFactory.getPersisted(owner);
        
        Project testProj = ProjectTestFactory.getPersisted(owner, testCust);
        testProj.setActive(true);
        testProj.setPostable(new Boolean(true));
        ProjectServiceDelegateFactory.factory.build(owner).save(testProj);

        Project testProj2 = ProjectTestFactory.getPersisted(owner, testCust);
        testProj2.setActive(true);
        testProj2.setPostable(new Boolean(true));
        ProjectServiceDelegateFactory.factory.build(owner).save(testProj2);
        
        Contact testCont = ContactTestFactory.singleton.getPersisted(owner, testCust);
        
        SystemOwner owner2 = SystemOwnerTestFactory.getOwner();
        
        Customer testCust2 = CustomerTestFactory.getPersisted(owner2);
        
        Project testProj22 = ProjectTestFactory.getPersisted(owner2, testCust2);
        testProj22.setActive(true);
        testProj22.setPostable(new Boolean(true));
        ProjectServiceDelegateFactory.factory.build(owner2).save(testProj22);

        Project testProj222 = ProjectTestFactory.getPersisted(owner2, testCust2);
        testProj222.setActive(true);
        testProj222.setPostable(new Boolean(true));
        ProjectServiceDelegateFactory.factory.build(owner2).save(testProj222);
        
        Contact testCont2 = ContactTestFactory.singleton.getPersisted(owner2, testCust2);
        
        
        SendCustomerBalanceUpdatesCommand command = new SendCustomerBalanceUpdatesCommand();
        
        command.execute();
        
        QueuedMessageServiceDelegate sd = QueuedMessageServiceDelegateFactory.factory.build(owner.getKey());
        Collection message = sd.search(new QueuedMessageFilter());
        
        assertEquals(1, message.size());
    }          
    
}
