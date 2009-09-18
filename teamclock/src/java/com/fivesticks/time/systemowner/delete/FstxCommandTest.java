/*
 * Created on Aug 24, 2004 by REID
 */
package com.fivesticks.time.systemowner.delete;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.common.SpringBeanBroker;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author REID
 */
public class FstxCommandTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

    public void testTransactionCommand() throws Exception {

        Collection all = SystemOwnerServiceDelegateFactory.factory.build().findAll();
        SystemOwner so = (SystemOwner) all.toArray()[0];
//
////        SessionContext sessionContext = new SessionContext();
//        Collection users = UserServiceDelegate.factory.build(so)
//                .listUserAndType();
//
//        User user = null;
//        for (Iterator iter = users.iterator(); iter.hasNext();) {
//            UserAndTypeVO element = (UserAndTypeVO) iter.next();
//            if (element.getUserTypeEnum().getName().equals(
//                    UserTypeEnum.OWNERADMIN.getName())) {
//
//                user = element.getUser();
//            }
//        }

//        assertNotNull(user);
//
//        AuthenticatedUser auser = null;
//
//        auser = Authenticator.singleton.authenticate(user.getUsername(), user
//                .getPassword());
//
//        sessionContext.setUser(auser);

//        SessionContext sessionContext = SessionContextTestFactory.getContext(so, user);
        
        FstxCommand txnCommand = (FstxCommand) SpringBeanBroker
                .getBeanFactory()
                .getBean("deleteSystemOwnerTransactionCommand");
        //        txnCommand.setSessionContext(sessionContext);
        txnCommand.setTarget(so);
        txnCommand.execute();

        Collection all2 = SystemOwnerServiceDelegateFactory.factory.build().findAll();
        assertEquals(0, all2.size());
    }

}