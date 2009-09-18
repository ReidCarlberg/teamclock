/*
 * Created on Dec 19, 2005
 *
 */
package com.fivesticks.time.systemowner.payments;

import com.fivesticks.time.testutil.AbstractTimeTestCase;

public class PaymentMethodServiceDelegateImplTest extends AbstractTimeTestCase {

    public void testBasicGetSystemOwnerPaymentMethod() throws Exception {

        PaymentMethod m = PaymentMethodTestFactory
                .buildSampleVisa(this.systemOwner);

        assertNotNull(m);

        PaymentMethod recalled = PaymentMethodServiceDelegateFactory.factory
                .build(this.systemOwner).getCurrent();
        
        assertNotNull(recalled);
        
        PaymentMethod m2 = PaymentMethodTestFactory.buildSampleVisa(this.systemOwner);
        m2.setCurrent(false);
        PaymentMethodServiceDelegateFactory.factory.build(this.systemOwner).save(m2);
        
        PaymentMethod recalled2 = PaymentMethodServiceDelegateFactory.factory
        .build(this.systemOwner).getCurrent();
        
        assertEquals(recalled.getId(), recalled2.getId());
    }
}
