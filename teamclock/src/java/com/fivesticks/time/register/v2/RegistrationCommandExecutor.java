/*
 * Created on Dec 17, 2005
 *
 */
package com.fivesticks.time.register.v2;

import com.fivesticks.time.common.AbstractTransactionExecutorAware;
import com.fivesticks.time.register.v2.xwork.RegistrationContext;

public class RegistrationCommandExecutor extends
        AbstractTransactionExecutorAware {

    public void execute(RegistrationContext registrationContext) {
        RegistrationProcessCommandIF cmd = RegistrationProcessCommandFactory.factory.build(registrationContext);
        
        /*
         * 2005-12-17 Should put a nice transaction wrapper around everything.
         */
        this.getTransactionExecutor().execute(cmd);
        
        
    }
}
