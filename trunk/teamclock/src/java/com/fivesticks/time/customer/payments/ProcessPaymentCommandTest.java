/*
 * Created on Aug 25, 2006 by Reid
 */
package com.fivesticks.time.customer.payments;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegate;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.CustomerTestFactory;
import com.fivesticks.time.systemowner.payments.PaymentMethod;
import com.fivesticks.time.systemowner.payments.PaymentMethodTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class ProcessPaymentCommandTest extends AbstractTimeTestCase {
    
    public void testBasic() throws Exception {
        
    	Customer testCustomer = CustomerTestFactory.getPersisted(this.systemOwner);
    	
    	testCustomer.setPayMethod("visa");
    	testCustomer.setPayCode("713");
    	testCustomer.setPayMonth("03");
    	testCustomer.setPayName("reid s carlberg");
    	testCustomer.setPayNumber("4000000000000000");
    	testCustomer.setPayStreet("6957 W. North Ave., #202");
    	testCustomer.setPayYear("2008");
    	testCustomer.setPayZip("60302");
    	
    	CustomerServiceDelegateFactory.factory.build(this.sessionContext).save(testCustomer);
    	
        ProcessPaymentCommand cmd = new ProcessPaymentCommand(testCustomer, "1.00");
        
        cmd.execute();
        
        
    }
    
}
