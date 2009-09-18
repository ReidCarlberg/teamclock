/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.util;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.ebay.setup.commission.SimpleCommission;

/**
 * @author Reid
 *
 */
public class CommissionCalculatorTest extends TestCase {

    private Log log = LogFactory.getLog(CommissionCalculatorTest.class);
    
    public void testBasic() throws Exception {
        
        Commission c = new CommissionBuilder().buildStandard();
        
        double r = new CommissionCalculator().getCommission(c, 500.0);
        
        log.info("comm " + r);
        
        assertTrue(r == 125.0);
        
        double r2 = new CommissionCalculator().getCommission(c, 1500.0);
        
        assertTrue(r2 == 300.0);
        
        double r3 = new CommissionCalculator().getCommission(c, 30.0);
        
        assertTrue(r3 == 9.95);
    }
    
    public void testSimpleCommission() throws Exception {
        
        SimpleCommission sc = new SimpleCommission();
        
        sc.setMinimum(9.95);
        sc.setOneMin(0.0);
        sc.setOneMax(1000.0);
        sc.setOneRate(25.0);
        sc.setTwoMin(1000.01);
        sc.setTwoMax(10000.00);
        sc.setTwoRate(10.0);
        
        Commission c = new CommissionBuilder().build(sc);
        
        double r = new CommissionCalculator().getCommission(c, 500.0);
        
        log.info("comm " + r);
        
        assertTrue(r == 125.0);
        
        double r2 = new CommissionCalculator().getCommission(c, 1500.0);
        
        assertTrue(r2 == 300.0);
        
        double r3 = new CommissionCalculator().getCommission(c, 30.0);
        
        assertTrue(r3 == 9.95);
    }    

}
