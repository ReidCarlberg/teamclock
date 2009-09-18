/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2;

import java.util.Collection;

import junit.framework.TestCase;

public class RegistrationPlanTest extends TestCase {

    public void testBasic() throws Exception {
        
        assertEquals(6,RegistrationPlan.getPlans().size());
        
    }
    
    public void testKeys() throws Exception {
        assertNotNull(RegistrationPlan.getPlan(RegistrationPlan.BASIC.getCode()));
        assertNotNull(RegistrationPlan.getPlan(RegistrationPlan.SIMPLE.getCode()));
        assertNotNull(RegistrationPlan.getPlan(RegistrationPlan.STARTER.getCode()));
        assertNotNull(RegistrationPlan.getPlan(RegistrationPlan.BUSINESS1.getCode()));
        assertNotNull(RegistrationPlan.getPlan(RegistrationPlan.BUSINESS2.getCode()));
        assertNotNull(RegistrationPlan.getPlan(RegistrationPlan.BUSINESS3.getCode()));
    }
    
    public void testOrder() throws Exception {
        Collection plans = RegistrationPlan.getPlans();
        
        assertEquals(RegistrationPlan.BASIC.getCode(),((RegistrationPlan)plans.toArray()[0]).getCode());
        assertEquals(RegistrationPlan.SIMPLE.getCode(),((RegistrationPlan)plans.toArray()[1]).getCode());
        assertEquals(RegistrationPlan.STARTER.getCode(),((RegistrationPlan)plans.toArray()[2]).getCode());
        assertEquals(RegistrationPlan.BUSINESS1.getCode(),((RegistrationPlan)plans.toArray()[3]).getCode());
        assertEquals(RegistrationPlan.BUSINESS2.getCode(),((RegistrationPlan)plans.toArray()[4]).getCode());
        assertEquals(RegistrationPlan.BUSINESS3.getCode(),((RegistrationPlan)plans.toArray()[5]).getCode());
    }
}
