/*
 * Created on Jun 4, 2005 by Reid
 */
package com.fivesticks.time.ebay.util;

import java.text.DecimalFormat;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.ebay.CommissionDiscountType;
import com.fivesticks.time.ebay.EbayItem;
import com.fivesticks.time.ebay.setup.commission.SimpleCommission;
import com.fivesticks.time.ebay.setup.commission.SimpleCommissionTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 */
public class EbayItemCalculatorTest extends TestCase {

    Log log = LogFactory.getLog(EbayItemCalculatorTest.class);
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }
    
    public void testCommission() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        EbayItem ebayItem = new EbayItem();
        
        ebayItem.setPrepayAmount(new Double(0.0));
        
        ebayItem.setShippingCharge(new Double(0.0));
        
        ebayItem.setPriceFinal(new Double(100.0));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());
        
        assertTrue(ebayItem.getCommission().doubleValue() == 25.0);
        
        /*
         * over 1000
         */
        ebayItem = new EbayItem();
        
        ebayItem.setPriceFinal(new Double(1500.00));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());
        
        assertTrue(ebayItem.getCommission().doubleValue() == 300.0);
        
    }
    
    public void testItemSpecificCommission() throws Exception {

        SystemOwner systemOwner = SystemOwnerTestFactory.getOwner();
        
        SimpleCommission c = SimpleCommissionTestFactory.buildPersistedTest(systemOwner, 75.0, 125.0);
        
        EbayItem ebayItem = new EbayItem();
        
        ebayItem.setPriceFinal(new Double(1000.00));
        
        ebayItem.setSimpleCommissionId(c.getId());
        
        new EbayItemCalculator().handleCalculate(ebayItem, systemOwner, c);
        
        assertTrue(ebayItem.getCommission().doubleValue() == 750.0);
        
        assertEquals(750.0, ebayItem.getCommission().doubleValue(),0.001);
        
    }
    
    public void testCommissionMinimum() throws Exception {
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        EbayItem ebayItem = new EbayItem();
        
        ebayItem.setPrepayAmount(new Double(0.0));
        
        ebayItem.setShippingCharge(new Double(0.0));
        
        ebayItem.setPriceFinal(new Double(10.0));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());
        
        assertTrue(ebayItem.getCommission().doubleValue() == 9.95);
    }
    
    public void testEbayFees() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        EbayItem ebayItem = new EbayItem();
        
        ebayItem.setPriceFinal(new Double(25.0));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());

        log.info("Ebay Final: " + ebayItem.getFeeEbayFinal().doubleValue());
        
        DecimalFormat df = new DecimalFormat("##.##");
        
        assertTrue(df.format(ebayItem.getFeeEbayFinal().doubleValue()).equals("1.31"));

        ebayItem.setPriceFinal(new Double(1000.0));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());

        assertTrue(df.format(ebayItem.getFeeEbayFinal().doubleValue()).equals("28.12"));

        ebayItem.setPriceFinal(new Double(1500.0));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());

        assertTrue(df.format(ebayItem.getFeeEbayFinal().doubleValue()).equals("35.62"));
        
    }
    
    public void testDiscount() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        EbayItem ebayItem = new EbayItem();

        ebayItem.setPriceFinal(new Double(1000.0));
        
        ebayItem.setCommissionDiscountMethod(CommissionDiscountType.FLAT.getName());
        
        ebayItem.setCommissionDiscountBase(new Double(5.0));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());
        
        assertTrue(ebayItem.getCommissionDiscount().doubleValue() == 5.0);
        
        assertTrue(ebayItem.getCommission().doubleValue() == 245.0);
        
        /*
         * 
         */
        
        ebayItem = new EbayItem();

        ebayItem.setPriceFinal(new Double(1000.0));
        
        ebayItem.setCommissionDiscountMethod(CommissionDiscountType.PERCENT.getName());
        
        ebayItem.setCommissionDiscountBase(new Double(20.0));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());
        
        log.info("discount: " + ebayItem.getCommissionDiscount().doubleValue());
        
        assertTrue(ebayItem.getCommissionDiscount().doubleValue() == 50.0);
        
        assertTrue(ebayItem.getCommission().doubleValue() == 200.0);

    }

    public void testDiscountForError() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        EbayItem ebayItem = new EbayItem();

        ebayItem.setPriceFinal(new Double(20.5));
        
        ebayItem.setCommissionDiscountMethod(CommissionDiscountType.FLAT.getName());
        
        ebayItem.setCommissionDiscountBase(new Double(5.0));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());
        
        assertTrue(ebayItem.getCommissionDiscount().doubleValue() == 5.0);
        
        log.info("commission is " + ebayItem.getCommission().doubleValue());
        
        assertTrue(ebayItem.getCommission().doubleValue() == 4.95);
        

    }
    
    public void testGross() throws Exception {
        
        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        EbayItem ebayItem = new EbayItem();

        ebayItem.setPriceFinal(new Double(1000.0));

        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());
        
        log.info("gross " + ebayItem.getGrossProfit().doubleValue());
        
        assertTrue(ebayItem.getGrossProfit().doubleValue() == 221.88);
        
        
    }
    
    public void testPrepay() throws Exception {

        SystemOwner owner = SystemOwnerTestFactory.getOwner();
        
        EbayItem ebayItem = new EbayItem();

        ebayItem.setPriceFinal(new Double(1000.0));

        ebayItem.setPrepayAmount(new Double(20.00));
        
        new EbayItemCalculator().handleCalculate(ebayItem, owner, SimpleCommissionTestFactory.buildUnpersistedTest());
        
        log.info("customer net: " + ebayItem.getCustomerNet());
        
        assertTrue(ebayItem.getCustomerNet().doubleValue() == 770.0);
        
    }

}
