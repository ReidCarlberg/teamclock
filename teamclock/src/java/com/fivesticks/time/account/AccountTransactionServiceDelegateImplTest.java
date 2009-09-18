/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.customer.util.CustomerAccountTransactionType;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * Happens to use FSTXCustomers.
 * 
 * @author Reid
 */
public class AccountTransactionServiceDelegateImplTest extends TestCase {

    SystemOwner systemOwnerMain;

    SystemOwner systemOwnerTest;

    Customer fstxCustomer;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        
        systemOwnerMain = new SystemOwnerTestFactory().buildWithDefaultSettings();
        
        systemOwnerTest = new SystemOwnerTestFactory().buildWithDefaultSettings();

        fstxCustomer = CustomerTestFactory.getPersisted(systemOwnerMain);
    }

    public void testBasicTransaction() throws Exception {

        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        Collection before = sd.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(before.size() == 0);

        sd.increase(fstxCustomer, new Double(1.0), "comments", "username");

        Collection after = sd.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(after.size() == 1);
    }

    public void testDelete() throws Exception {

        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        Collection before = sd.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(before.size() == 0);

        sd.increase(fstxCustomer, new Double(1.0), "comments", "username");

        Collection after = sd.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(after.size() == 1);

        sd.delete((AccountTransaction) after.toArray()[0]);

        Collection afterDelete = sd.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(afterDelete.size() == 0);

    }

    public void testUpdate() throws Exception {
        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        sd.increase(fstxCustomer, new Double(1.0), "comments", "username");

        Collection after = sd.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(after.size() == 1);

        AccountTransaction current = (AccountTransaction) after.toArray()[0];

        assertTrue(current.getId() != null);

        current.setComments("these are edited comments");

        sd.save(current);

        AccountTransaction newVersion = sd.get(current.getId());

        assertTrue(newVersion != null);

        assertTrue(newVersion.getComments().equals(current.getComments()));
    }

    public void testBalanceSimple() throws Exception {
        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        Collection before = sd.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(before.size() == 0);

        sd.increase(fstxCustomer, new Double(1.0), "comments", "username");

        assertTrue(sd.getCurrentBalance(fstxCustomer).doubleValue() == 1.0);

    }

    public void testBalanceAddAndSubtract() throws Exception {

        Double valueAdd1 = new Double(6.0);

        Double valueUse1 = new Double(3.0);

        Double valueAdd2 = new Double(4.0);

        Double valueUse2 = new Double(3.5);

        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        sd.increase(fstxCustomer, valueAdd1, "test", "username");
        sd.decrease(fstxCustomer, valueUse1, "dff", "test");
        sd.increase(fstxCustomer, valueAdd2, "test", "username");
        sd.decrease(fstxCustomer, valueUse2, "dff", "test");

        Double b = sd.getCurrentBalance(fstxCustomer);

        assertEquals(b.doubleValue(), 3.4, 3.6);

    }

    public void testBalanceSet() throws Exception {

        Double valueAdd1 = new Double(6.0);

        Double valueUse1 = new Double(3.0);

        Double valueAdd2 = new Double(4.0);

        Double valueUse2 = new Double(3.5);

        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        sd.increase(fstxCustomer, valueAdd1, "test", "username");
        sd.decrease(fstxCustomer, valueUse1, "dff", "test");
        sd.increase(fstxCustomer, valueAdd2, "test", "username");
        sd.decrease(fstxCustomer, valueUse2, "dff", "test");

        Double b = sd.getCurrentBalance(fstxCustomer);

        assertEquals(b.doubleValue(), 3.4, 3.6);

        sd.adjust(fstxCustomer, new Double(100.0), "adjust", "reid");

        assertTrue(sd.getCurrentBalance(fstxCustomer).doubleValue() == 100.0);
    }

    /*
     * on the balance set, this was letting a historical balance set override
     * the current balance.
     *  
     */
    public void testHistoricalBalanceSet() throws Exception {

        Double valueAdd1 = new Double(6.0);

        Double valueUse1 = new Double(3.0);

        Double valueAdd2 = new Double(4.0);

        Double valueUse2 = new Double(3.5);

        AccountTransactionServiceDelegate sd = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        sd.adjust(fstxCustomer, new Double(100.0), "adjust", "reid");

        assertTrue(sd.getCurrentBalance(fstxCustomer).doubleValue() == 100.0);

        sd.increase(fstxCustomer, valueAdd1, "test", "username");
        sd.decrease(fstxCustomer, valueUse1, "dff", "test");
        sd.increase(fstxCustomer, valueAdd2, "test", "username");
        sd.decrease(fstxCustomer, valueUse2, "dff", "test");

        Double b = sd.getCurrentBalance(fstxCustomer);

        assertEquals(b.doubleValue(), 103.4, 103.6);

    }

    public void testSystemOwnerSeparation() throws Exception {

        AccountTransactionServiceDelegate sdMain = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        sdMain.increase(fstxCustomer, new Double(5.0), "comments",
                "here's a username");

        Collection before = sdMain.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(before.size() == 1);

        AccountTransactionServiceDelegate sdTest = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerTest, CustomerAccountTransactionType.TIME_ACCOUNT);

        Collection test = sdTest.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(test.size() == 0);

    }

    public void testBadCustomer() throws Exception {

        /*
         * RSC failes inconsistently when running the whole suite.
         * 2005-05-15
         */
        
        AccountTransactionServiceDelegate sdTest = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerTest, CustomerAccountTransactionType.TIME_ACCOUNT);

        try {
            Double bal = sdTest.getCurrentBalance(fstxCustomer);
            assertTrue(false); //should never be here.
        } catch (Exception e) {
            //great this should have failed. So we're good.
        }

    }

    public void testFindUnarchived() throws Exception {

        AccountTransactionServiceDelegate sdMain = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        sdMain.increase(fstxCustomer, new Double(5.0), "comments",
                "here's a username");
        sdMain.increase(fstxCustomer, new Double(5.0), "comments",
                "here's a username");
        sdMain.increase(fstxCustomer, new Double(5.0), "comments",
                "here's a username");
        sdMain.increase(fstxCustomer, new Double(5.0), "comments",
                "here's a username");

        Collection before = sdMain.findUnarchived(fstxCustomer);

        assertTrue(before.size() == 4);

        AccountTransaction first = (AccountTransaction) before.toArray()[0];

        first.setArchived(true);
        sdMain.save(first);

        Collection after = sdMain.findUnarchived(fstxCustomer);

        assertTrue(after.size() == 3);
    }

    public void testArchiveThroughDate() throws Exception {

        AccountTransactionServiceDelegate sdMain = AccountTransactionServiceDelegateFactory.factory
                .build(systemOwnerMain, CustomerAccountTransactionType.TIME_ACCOUNT);

        sdMain.increase(fstxCustomer, new Double(5.0), "comments",
                "here's a username");
        sdMain.increase(fstxCustomer, new Double(5.0), "comments",
                "here's a username");
        sdMain.increase(fstxCustomer, new Double(5.0), "comments",
                "here's a username");
        sdMain.increase(fstxCustomer, new Double(5.0), "comments",
                "here's a username");

        Collection before = sdMain.findUnarchived(fstxCustomer);
        assertTrue(before.size() == 4);

        SimpleDate dateAdjuster = SimpleDate.factory.buildMidnight();
        dateAdjuster.advanceDay(-4);

        AccountTransaction first = (AccountTransaction) before.toArray()[0];
        first.setDate(dateAdjuster.getDate());
        sdMain.save(first);
        
        
        AccountTransaction second = (AccountTransaction) before.toArray()[1];
        dateAdjuster.advanceDay();
        second.setDate(dateAdjuster.getDate());
        sdMain.save(second);

        Double originalBalance = sdMain.getCurrentBalance(fstxCustomer);

        dateAdjuster.advanceDay();

        sdMain.archiveThroughDate(fstxCustomer, dateAdjuster, "username");

        Collection after = sdMain.findUnarchived(fstxCustomer);

        assertTrue(after.size() == 3);

        /*
         * rsc 2005-05-23 was checking 0, correct order is reversed, so checking 2.
         */
        AccountTransaction adjust = (AccountTransaction) after.toArray()[2];

        assertTrue(adjust.getType().equals(
                AccountTransactionTypeEnum.BALANCE_SET.getName()));

        Double newBalance = sdMain.getCurrentBalance(fstxCustomer);

        assertTrue(originalBalance.doubleValue() == newBalance.doubleValue());
    }
}