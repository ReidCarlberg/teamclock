/*
 * Created on Jan 20, 2005 by Reid
 */
package com.fivesticks.time.object.comments;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;

/**
 * @author Reid
 */
public class ObjectCommentServiceDelegateImplTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testBasic() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        Customer cust2 = CustomerTestFactory.getPersisted(owner);

        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);

        ObjectCommentServiceDelegateFactory.factory.build(owner).addComment(cust1,
                user1, "Here is a comment");

        Collection comments1 = ObjectCommentServiceDelegateFactory.factory
                .build(owner).getComments(cust1);

        assertTrue(comments1.size() == 1);

        Collection comments2 = ObjectCommentServiceDelegateFactory.factory
                .build(owner).getComments(cust2);

        assertTrue(comments2.size() == 0);

    }

    public void testMultipleEntries() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(owner);

        Customer cust2 = CustomerTestFactory.getPersisted(owner);

        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);

        ObjectCommentServiceDelegateFactory.factory.build(owner).addComment(cust1,
                user1, "Here is a comment");

        ObjectCommentServiceDelegateFactory.factory.build(owner).addComment(cust1,
                user1, "Here is a comment");

        ObjectCommentServiceDelegateFactory.factory.build(owner).addComment(cust1,
                user1, "Here is a comment");

        ObjectCommentServiceDelegateFactory.factory.build(owner).addComment(cust2,
                user1, "Here is a comment");

        ObjectCommentServiceDelegateFactory.factory.build(owner).addComment(cust2,
                user1, "Here is a comment");

        Collection comments1 = ObjectCommentServiceDelegateFactory.factory
                .build(owner).getComments(cust1);

        assertTrue(comments1.size() == 3);

        Collection comments2 = ObjectCommentServiceDelegateFactory.factory
                .build(owner).getComments(cust2);

        assertTrue(comments2.size() == 2);
    }

}