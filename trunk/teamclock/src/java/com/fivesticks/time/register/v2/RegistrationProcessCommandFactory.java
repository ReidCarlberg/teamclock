/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2;

import com.fivesticks.time.common.AbstractSpringObjectFactory;
import com.fivesticks.time.register.v2.xwork.RegistrationContext;

public class RegistrationProcessCommandFactory extends AbstractSpringObjectFactory {

    public static final String SPRING_BEAN_NAME = "registrationProcessCommand";
    public static final RegistrationProcessCommandFactory factory = new RegistrationProcessCommandFactory();
    
    public RegistrationProcessCommandIF build(RegistrationContext registrationContext) {
        RegistrationProcessCommand cmd =  (RegistrationProcessCommand) this.getBean(SPRING_BEAN_NAME);
        
        cmd.setRegistrationContext(registrationContext);
        
        return cmd;
    }
    
    public RegistrationProcessCommandIF buildWithMockPayment(RegistrationContext registrationContext) {
        RegistrationProcessCommand cmd = (RegistrationProcessCommand) this.getBean(SPRING_BEAN_NAME);
        cmd.setRegistrationContext(registrationContext);
        throw new RuntimeException("not yet");
//        return cmd;
    }
    
}
