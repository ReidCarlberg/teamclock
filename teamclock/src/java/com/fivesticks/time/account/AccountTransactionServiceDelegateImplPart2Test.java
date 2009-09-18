/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.employee.Employee;
import com.fivesticks.time.employee.EmployeeTestFactory;
import com.fivesticks.time.employee.util.EmployeeAccountTransactionServiceDelegate;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class AccountTransactionServiceDelegateImplPart2Test extends TestCase {

    SystemOwner systemOwnerMain;

    SystemOwner systemOwnerTest;

    Employee employee;
    
    User user;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        
        systemOwnerMain = new SystemOwnerTestFactory().buildWithDefaultSettings();
        
        systemOwnerTest = new SystemOwnerTestFactory().buildWithDefaultSettings();

        user = SystemUserTestFactory.singleton.buildOwner(systemOwnerMain);
        
        employee = EmployeeTestFactory.getPersisted(systemOwnerMain, user);
    }

    public void testBasicTransaction() throws Exception {

        AccountTransactionServiceDelegate sd = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        AccountTransactionServiceDelegate sickSd = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);
        
        sickSd.increase(employee, new Double(5.0), "comments", "comments", null);

        sickSd.increase(employee, new Double(5.0), "comments", "comments", null);

        Collection before = sd.find(employee);

        assertTrue(before.size() == 0);

        sd.increase(employee, new Double(1.0), "comments", "username", null);

        Collection after = sd.find(employee);

        assertTrue(after.size() == 1);
        
        assertTrue(sickSd.find(employee).size() == 2);
    }

    public void testBasicTransactionWithMore() throws Exception {

        AccountTransactionServiceDelegate sdPersonal = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

        AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        Collection before = sdPersonal.find(employee);

        assertTrue(before.size() == 0);

        sdPersonal.increase(employee, new Double(1.0), "comments", "username");

        Collection after = sdPersonal.find(employee);

        assertTrue(after.size() == 1);
        
        assertTrue(sdSick.find(employee).size() == 2);

        assertTrue(sdVacation.find(employee).size() == 3);

    }

    public void testDelete() throws Exception {

        AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

        AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        
        AccountTransactionServiceDelegate sd = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        Collection before = sd.find(employee);

        assertTrue(before.size() == 0);

        sd.increase(employee, new Double(1.0), "comments", "username");

        Collection after = sd.find(employee);

        assertTrue(after.size() == 1);

        sd.delete((AccountTransaction) after.toArray()[0]);

        Collection afterDelete = sd.find(employee);

        assertTrue(afterDelete.size() == 0);

    }

    public void testUpdate() throws Exception {
        AccountTransactionServiceDelegate sd = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        sd.increase(employee, new Double(1.0), "comments", "username");

        Collection after = sd.find(employee);

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
        
        AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

        AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        
        AccountTransactionServiceDelegate sd = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        Collection before = sd.find(employee);

        assertTrue(before.size() == 0);

        sd.increase(employee, new Double(1.0), "comments", "username");

        assertTrue(sd.getCurrentBalance(employee).doubleValue() == 1.0);

    }

    public void testBalanceAddAndSubtract() throws Exception {

        AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

       AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        Double valueAdd1 = new Double(6.0);

        Double valueUse1 = new Double(3.0);

        Double valueAdd2 = new Double(4.0);

        Double valueUse2 = new Double(3.5);

        AccountTransactionServiceDelegate sd = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        sd.increase(employee, valueAdd1, "test", "username");
        sd.decrease(employee, valueUse1, "dff", "test");
        sd.increase(employee, valueAdd2, "test", "username");
        sd.decrease(employee, valueUse2, "dff", "test");

        Double b = sd.getCurrentBalance(employee);

        assertEquals(b.doubleValue(), 3.4, 3.6);

    }

    public void testBalanceSet() throws Exception {

        AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

        AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        
        Double valueAdd1 = new Double(6.0);

        Double valueUse1 = new Double(3.0);

        Double valueAdd2 = new Double(4.0);

        Double valueUse2 = new Double(3.5);

        AccountTransactionServiceDelegate sd = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        sd.increase(employee, valueAdd1, "test", "username");
        sd.decrease(employee, valueUse1, "dff", "test");
        sd.increase(employee, valueAdd2, "test", "username");
        sd.decrease(employee, valueUse2, "dff", "test");

        Double b = sd.getCurrentBalance(employee);

        assertEquals(b.doubleValue(), 3.4, 3.6);

        sd.adjust(employee, new Double(100.0), "adjust", "reid");

        assertTrue(sd.getCurrentBalance(employee).doubleValue() == 100.0);
    }

    /*
     * on the balance set, this was letting a historical balance set override
     * the current balance.
     *  
     */
    public void testHistoricalBalanceSet() throws Exception {

        AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

        AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        
        Double valueAdd1 = new Double(6.0);

        Double valueUse1 = new Double(3.0);

        Double valueAdd2 = new Double(4.0);

        Double valueUse2 = new Double(3.5);

        AccountTransactionServiceDelegate sd = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        sd.adjust(employee, new Double(100.0), "adjust", "reid", null);

        assertTrue(sd.getCurrentBalance(employee).doubleValue() == 100.0);

        sd.increase(employee, valueAdd1, "test", "username", null);
        sd.decrease(employee, valueUse1, "dff", "test", null);
        sd.increase(employee, valueAdd2, "test", "username", null);
        sd.decrease(employee, valueUse2, "dff", "test", null);

        Double b = sd.getCurrentBalance(employee);

        assertEquals(b.doubleValue(), 103.4, 103.6);

    }

    public void testSystemOwnerSeparation() throws Exception {

        AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

        AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        
        AccountTransactionServiceDelegate sdMain = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        sdMain.increase(employee, new Double(5.0), "comments",
                "here's a username", null);

        Collection before = sdMain.find(employee);

        assertTrue(before.size() == 1);

        AccountTransactionServiceDelegate sdTest = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerTest);

        Collection test = sdTest.findAll(new AccountTransactionCriteriaParameters());

        assertTrue(test.size() == 0);

    }

    public void testBadCustomer() throws Exception {

        AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

        AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        
        /*
         * RSC failes inconsistently when running the whole suite.
         * 2005-05-15
         */
        
        AccountTransactionServiceDelegate sdTest = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerTest);

        try {
            Double bal = sdTest.getCurrentBalance(employee);
            assertTrue(false); //should never be here.
        } catch (Exception e) {
            //great this should have failed. So we're good.
        }

    }

    public void testFindUnarchived() throws Exception {

        AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

        AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        
        AccountTransactionServiceDelegate sdMain = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        sdMain.increase(employee, new Double(5.0), "comments",
                "here's a username", null);
        sdMain.increase(employee, new Double(5.0), "comments",
                "here's a username", null);
        sdMain.increase(employee, new Double(5.0), "comments",
                "here's a username", null);
        sdMain.increase(employee, new Double(5.0), "comments",
                "here's a username", null);

        Collection before = sdMain.findUnarchived(employee);

        assertTrue(before.size() == 4);

        AccountTransaction first = (AccountTransaction) before.toArray()[0];

        first.setArchived(true);
        sdMain.save(first);

        Collection after = sdMain.findUnarchived(employee);

        assertTrue(after.size() == 3);
    }

    public void testArchiveThroughDate() throws Exception {

       AccountTransactionServiceDelegate sdSick = EmployeeAccountTransactionServiceDelegate.factory.buildSick(systemOwnerMain);

        AccountTransactionServiceDelegate sdVacation = EmployeeAccountTransactionServiceDelegate.factory.buildVacation(systemOwnerMain);

        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);
        sdSick.increase(employee, new Double(5.0), "comments", "comments", null);

        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);
        sdVacation.increase(employee, new Double(5.0), "comments", "comments", null);

        
        AccountTransactionServiceDelegate sdMain = EmployeeAccountTransactionServiceDelegate.factory
                .buildPersonal(systemOwnerMain);

        sdMain.increase(employee, new Double(5.0), "comments",
                "here's a username", null);
        sdMain.increase(employee, new Double(5.0), "comments",
                "here's a username", null);
        sdMain.increase(employee, new Double(5.0), "comments",
                "here's a username", null);
        sdMain.increase(employee, new Double(5.0), "comments",
                "here's a username", null);

        Collection before = sdMain.findUnarchived(employee);
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

        Double originalBalance = sdMain.getCurrentBalance(employee);

        dateAdjuster.advanceDay();

        sdMain.archiveThroughDate(employee, dateAdjuster, "username");

        Collection after = sdMain.findUnarchived(employee);

        assertTrue(after.size() == 3);

        /*
         * rsc 2005-05-23 was checking 0, correct order is reversed, so checking 2.
         */
        AccountTransaction adjust = (AccountTransaction) after.toArray()[2];

        assertTrue(adjust.getType().equals(
                AccountTransactionTypeEnum.BALANCE_SET.getName()));

        Double newBalance = sdMain.getCurrentBalance(employee);

        assertTrue(originalBalance.doubleValue() == newBalance.doubleValue());
    }
}