package com.fivesticks.time.accountactivity;

import java.util.Collection;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.account.AccountTransaction;
import com.fivesticks.time.account.AccountTransactionDAO;
import com.fivesticks.time.account.AccountTransactionTypeEnum;
import com.fivesticks.time.activity.Activity;
import com.fivesticks.time.activity.ActivityTestFactory;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectTestFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserFactory;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *  
 */
public class AccountActivityServiceDelegateTest extends TestCase {

    SystemOwner systemOwner;

    User user1;

    User user2;

    Activity time1;

    Activity time2;

    Activity time3;

    Activity time4;

    /**
     * @param arg0
     */
    public AccountActivityServiceDelegateTest(String arg0) {
        super(arg0);

    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    public void setUp() throws Exception {
        super.setUp();

        JunitSettings.initializeTestSystem();

        systemOwner = new SystemOwnerTestFactory().buildWithDefaultSettings();

        user1 = UserFactory.singleton.build();
        user2 = UserFactory.singleton.build();

        SystemUserServiceDelegateFactory.factory.build().associate(systemOwner, user1,
                null);
        SystemUserServiceDelegateFactory.factory.build().associate(systemOwner, user2,
                null);

        time1 = new ActivityTestFactory().build(systemOwner, user1);
        time2 = new ActivityTestFactory().build(systemOwner, user1);
        time3 = new ActivityTestFactory().build(systemOwner, user2);
        time4 = new ActivityTestFactory().build(systemOwner, user2);
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUnmatched() throws Exception {
        SimpleDate today = SimpleDate.factory.buildMidnight();

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj = ProjectTestFactory.getPersistedPostable(owner,
                cust1);

        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);

        Activity act1 = ActivityTestFactory.singleton.build(owner,
                proj, user1);
        Activity act2 = ActivityTestFactory.singleton.build(owner,
                proj, user1);
        Activity act3 = ActivityTestFactory.singleton.build(owner,
                proj, user1);
        Activity act4 = ActivityTestFactory.singleton.build(owner,
                proj, user1);

        AccountActivityServiceDelegate t = AccountActivityServiceDelegateFactory.factory
                .build(SessionContextTestFactory.getContext(owner, this.user1));

        Collection unposted = t.findUnpostedActivity(today);

        assertTrue(unposted.size() == 4);

    }

    public void testUnmatchedNotPostable() throws Exception {
        SimpleDate today = SimpleDate.factory.buildMidnight();

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();

        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj = ProjectTestFactory.getPersisted(owner, cust1);

        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);

        Activity act1 = ActivityTestFactory.singleton.build(owner,
                proj, user1);
        Activity act2 = ActivityTestFactory.singleton.build(owner,
                proj, user1);
        Activity act3 = ActivityTestFactory.singleton.build(owner,
                proj, user1);
        Activity act4 = ActivityTestFactory.singleton.build(owner,
                proj, user1);

        AccountActivityServiceDelegate t = AccountActivityServiceDelegateFactory.factory
                .build(SessionContextTestFactory.getContext(this.systemOwner, this.user1));

        Collection unposted = t.findUnpostedActivity(today);

        assertTrue(unposted.size() == 0);

    }

    public void testUnmatchedWithOnePost() throws Exception {

        SimpleDate today = SimpleDate.factory.buildMidnight();

        SystemOwner owner = SystemOwnerTestFactory.singleton.buildWithDefaultSettings();
        Customer cust1 = CustomerTestFactory.getPersisted(owner);
        Project proj = ProjectTestFactory.getPersistedPostable(owner,
                cust1);
        User user1 = SystemUserTestFactory.singleton.buildOwner(owner);

        Activity act1 = ActivityTestFactory.singleton.build(owner,
                proj, user1);
        Activity act2 = ActivityTestFactory.singleton.build(owner,
                proj, user1);
        Activity act3 = ActivityTestFactory.singleton.build(owner,
                proj, user1);
        Activity act4 = ActivityTestFactory.singleton.build(owner,
                proj, user1);

        AccountTransaction accountTransaction = new AccountTransaction();
        
        accountTransaction.setObjectType(cust1.getClass().getName());
        accountTransaction.setObjectId(cust1.getId());
        accountTransaction.setObjectTransactionType(CustomerAccountTransactionType.TIME_ACCOUNT.getName());
        /*
         * reid 2005-06-20 what to do with the activity id.
         */
        accountTransaction.setActivityId(act1.getId());
        accountTransaction.setAmount(new Double(1));
        accountTransaction.setArchived(false);
        accountTransaction.setComments("n");
        /*
         * reid 2005-06-20 removed.
         * reid 2005-06-24 needs to be not null.
         */
        accountTransaction.setCustomerId(cust1.getId());
        accountTransaction.setDate(new Date());
        accountTransaction.setEnteredBy(user1.getUsername());
        accountTransaction.setOwnerKey(owner.getKey());
        accountTransaction.setType(AccountTransactionTypeEnum.USE_VALUE
                .getName());

        AccountTransactionDAO.factory.build().save(accountTransaction);

        AccountActivityServiceDelegate t = AccountActivityServiceDelegateFactory.factory
                .build(SessionContextTestFactory.getContext(owner, this.user1));

        Collection r = t.findUnpostedActivity(today);

        assertTrue(r.size() == 3);

    }

    static Log log = LogFactory.getLog(AccountActivityServiceDelegateTest.class
            .getName());

}