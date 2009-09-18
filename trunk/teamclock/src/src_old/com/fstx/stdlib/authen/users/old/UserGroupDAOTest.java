/*

 Five Sticks
 6957 W. North Ave., #202
 Chicago, IL 60302
 USA
 http://www.fivesticks.com
 mailto:info@fivesticks.com

 Copyright (c) 2003-2004, Five Sticks Publications, Inc.
 All rights reserved.

 Redistribution and use in source and binary forms, 
 with or without modification, are permitted provided
 that the following conditions are met:

 * Redistributions of source code must retain 
 the above copyright notice, this list of conditions 
 and the following disclaimer.
 * Redistributions in binary form must reproduce 
 the above copyright notice, this list of conditions 
 and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of the Five Sticks Publications, Inc.,
 nor the names of its contributors may be used to 
 endorse or promote products derived from this software 
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 SUCH DAMAGE.

 license: http://www.opensource.org/licenses/bsd-license.php

 This software uses a variety of Open Source software as
 a foundation.  See the file 

 [your install]/WEB-INF/component-acknowledgement.txt
 
 For more information.
 */
/*
 * Created on Aug 18, 2003
 *
 */
package com.fstx.stdlib.authen.users.old;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Nick
 *  
 */
public class UserGroupDAOTest extends TestCase {

    /**
     * Constructor for EMSGroupDAOTest.
     * 
     * @param arg0
     */
    public UserGroupDAOTest(String arg0) {
        super(arg0);

    }

    public void testSearchGroups() throws Exception {
        UserGroupDAO dao = UserGroupDAO.factory.build();
        GroupMembershipDAO gmdao = GroupMembershipDAO.factory.build();
        UserDAO udao = UserDAO.factory.build();
        
        
        UserGroup g = new UserGroup("myEMSGroupsfsdfs");
        UserGroup g1 = new UserGroup("myOtherGroup");
        User u1 = UserFactory.singleton.getPersisted();
        User u2 = UserFactory.singleton.getPersisted();
        User u3 = UserFactory.singleton.getPersisted();
        g = dao.save(g);
        g1 = dao.save(g1);

        u1 = udao.save(u1);
        u2 = udao.save(u2);
        u3 = udao.save(u3);

        GroupMembership gm = new GroupMembership(g.getId(), u1.getId());
        GroupMembership gm1 = new GroupMembership(g1.getId(), u1.getId());
        GroupMembership gm2 = new GroupMembership(g.getId(), u3.getId());

        gm = gmdao.save(gm);
        gm1 = gmdao.save(gm1);
        gm2 = gmdao.save(gm2);

        List l = dao.find(UserGroupDAO.SELECT_BY_USER, u1.getUsername());
        Iterator i = l.iterator();
        UserGroup g3 = null;
        while (i.hasNext()) {
            g3 = (UserGroup) i.next();
            log.info(g3.getName());
        }
    }

    static Log log = LogFactory.getLog(UserGroupDAOTest.class.getName());

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
    }

}