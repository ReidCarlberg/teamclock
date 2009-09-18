/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.customer;

import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author REID
 *  
 */
public class ProjectTestFactory {

    private static int counter = 0;

    public static Project getPersisted(SystemOwner systemOwner,
            Customer fstxCustomer) throws Exception {

        counter++;

        Project ret = new Project();

        ret.setCustId(fstxCustomer.getId());

        ret.setShortName("name" + counter);
        ret.setLongName("name" + counter);

        ret.setOwnerKey(systemOwner.getKey());

        ProjectServiceDelegateFactory.factory.build(systemOwner).save(ret);

        return ret;

    }

    public static Project getPersistedPostable(SystemOwner systemOwner,
            Customer fstxCustomer) throws Exception {

        counter++;

        Project ret = new Project();

        ret.setCustId(fstxCustomer.getId());
        ret.setShortName("name" + counter);
        ret.setLongName("name" + counter);
        ret.setOwnerKey(systemOwner.getKey());

        ret.setPostable(new Boolean(true));

        ProjectServiceDelegateFactory.factory.build(systemOwner).save(ret);

        return ret;

    }

}