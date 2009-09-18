/*
 * Created on Aug 25, 2006 by Reid
 */
package com.fivesticks.time.system.payment;

import com.fivesticks.time.systemowner.payments.PaymentMethod;
import com.fivesticks.time.systemowner.payments.PaymentMethodTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class ProcessPaymentCommandTest extends AbstractTimeTestCase {
    
    public void testBasic() throws Exception {
        
        PaymentMethod paymentMethod = PaymentMethodTestFactory.buildSampleVisa(this.systemOwner);
        
        ProcessPaymentCommand cmd = new ProcessPaymentCommand(this.systemOwner, paymentMethod, "14.95");
        
        cmd.execute();
        
        
    }
    
}
