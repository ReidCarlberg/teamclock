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
package com.fstx.stdlib.author.old;

import java.util.HashSet;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.config.DatabaseInitializer;
import com.fstx.stdlib.authen.users.GroupMembership;
import com.fstx.stdlib.authen.users.GroupMembershipDAO;
import com.fstx.stdlib.authen.users.GroupMembershipDAOImpl;
import com.fstx.stdlib.authen.users.GroupRights;
import com.fstx.stdlib.authen.users.GroupRightsDAO;
import com.fstx.stdlib.authen.users.GroupRightsDAOImpl;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserDAO;
import com.fstx.stdlib.authen.users.UserDAOImpl;
import com.fstx.stdlib.authen.users.UserGroup;
import com.fstx.stdlib.authen.users.UserGroupDAO;
import com.fstx.stdlib.authen.users.UserGroupDAOImpl;
import com.fstx.stdlib.common.dao.todelete.DAOException;

/**
 * @author Nick
 *  
 */
public class AuthorizationBeanBuilderTest extends TestCase {

    private UserGroup g = null;

    private UserGroup g1 = null;

    private User u1 = null;

    private User u2 = null;

    private User u3 = null;

    private GroupMembership gm = null;

    private GroupMembership gm1 = null;

    private GroupMembership gm2 = null;

    private GroupRights gr = null;

    private GroupRights gr1 = null;

    private GroupRights gr2 = null;

    private GroupRights gr3 = null;

    /**
     * Constructor for AuthorizationBeanBuilderTest.
     * 
     * @param arg0
     */
    public AuthorizationBeanBuilderTest(String arg0) {
        super(arg0);
    }

    public void testBuildAuthorizationBean() throws DAOException {

        AuthorizationBean ab = new AuthorizationBeanBuilder()
                .buildAuthorizationBean(u1.getUsername());
        HashSet myRights = ab.getRights();

        assertEquals(myRights.size(), 4);
        assertTrue(myRights.contains("right1"));
        assertTrue(myRights.contains("right2"));
        assertTrue(myRights.contains("right3"));
        assertTrue(myRights.contains("Otherright2"));

        //				Iterator i = ab.getRights().iterator();
        //				String grTemp;
        //				while(i.hasNext())
        //				{
        //					grTemp = (String)i.next();
        //					
        //					log.info("My Sting: "+grTemp);
        //				}
        //				
    }

    static Log log = LogFactory.getLog(AuthorizationBeanBuilderTest.class
            .getName());

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {

        new DatabaseInitializer().initializeTables();

        UserGroupDAO dao = new UserGroupDAOImpl();
        GroupMembershipDAO gmdao = new GroupMembershipDAOImpl();
        UserDAO udao = new UserDAOImpl();
        GroupRightsDAO grdao = new GroupRightsDAOImpl();
        //Create groups
        g = new UserGroup("myEMSGroupsfsdfs");
        g1 = new UserGroup("myOtherGroup");

        //Create Users
        u1 = new User("Nicholas", "pass", "email@yahoo.com");
        u2 = new User("Jim", "pass", "email2@yahoo.com");
        u3 = new User("Jose", "pass", "email3@yahoo.com");

        // Save groups
        g = dao.save(g);
        g1 = dao.save(g1);

        // Save users
        u1 = udao.save(u1);
        u2 = udao.save(u2);
        u3 = udao.save(u3);

        // Create Group Memberships
        gm = new GroupMembership(g.getId(), u1.getId());
        gm1 = new GroupMembership(g1.getId(), u1.getId());
        gm2 = new GroupMembership(g.getId(), u3.getId());

        // Create Group Rights
        gr = new GroupRights(g.getId(), "right1");
        gr1 = new GroupRights(g.getId(), "right2");
        gr2 = new GroupRights(g.getId(), "right3");
        gr3 = new GroupRights(g1.getId(), "Otherright2");

        // Save Group Rights
        gr = grdao.save(gr);
        gr1 = grdao.save(gr1);
        gr2 = grdao.save(gr2);
        gr3 = grdao.save(gr3);

        // Save GroupMemberships
        gm = gmdao.save(gm);
        gm1 = gmdao.save(gm1);
        gm2 = gmdao.save(gm2);
        // TODO Auto-generated method stub
        super.setUp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        new UserGroupDAOImpl().delete(g);
        new UserGroupDAOImpl().delete(g1);
        new UserDAOImpl().delete(u1);
        new UserDAOImpl().delete(u2);
        new UserDAOImpl().delete(u3);

        new GroupMembershipDAOImpl().delete(gm);
        new GroupMembershipDAOImpl().delete(gm1);
        new GroupMembershipDAOImpl().delete(gm2);

        new GroupRightsDAOImpl().delete(gr);
        new GroupRightsDAOImpl().delete(gr1);
        new GroupRightsDAOImpl().delete(gr2);
        new GroupRightsDAOImpl().delete(gr3);

        super.tearDown();
    }

}