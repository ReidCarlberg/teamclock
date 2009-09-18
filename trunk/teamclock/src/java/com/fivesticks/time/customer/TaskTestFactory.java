/*
 * Created on Apr 25, 2006
 *
 */
package com.fivesticks.time.customer;

import com.fivesticks.time.systemowner.SystemOwner;

public class TaskTestFactory {

    private static int counter = 0;

    public static Task buildPersisted(SystemOwner systemOwner) throws Exception{

        counter++;

        Task ret = new Task();
        ret.setNameLong("nameLong" + counter);
        ret.setNameShort("nameShort" + counter);
        TaskServiceDelegateFactory.factory.build(systemOwner).save(ret);

        return ret;
    }
}
