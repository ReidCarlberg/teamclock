/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2.xwork;

import com.fivesticks.time.register.v2.RegistrationPlan;
import com.fivesticks.time.settings.FstxTimeSettings;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.payments.PaymentMethod;
import com.fivesticks.time.systemowner.payments.PaymentMethodTestFactory;
import com.fstx.stdlib.authen.users.User;

public class RegistrationContextTestFactory {

    static int count = 0;
    
    public static RegistrationContext build() throws Exception {
        
        count++;
        SystemOwner owner = SystemOwnerTestFactory.getUnpersisted();
        User user = new User();
        user.setUsername("rcUser"+count);
        user.setPassword("rcPass" + count);
        user.setEmail(owner.getContactEmail());
        
        PaymentMethod paymentInformation = PaymentMethodTestFactory.buildUnpersistedSampleVisa();
        
        
        
        FstxTimeSettings settings = new FstxTimeSettings();
        settings.setSystemTimeZone("America/Chicago");
        
        RegistrationContext context = new RegistrationContext(RegistrationPlan.BASIC,owner,user,settings, paymentInformation);
        
        return context;
    }
}
