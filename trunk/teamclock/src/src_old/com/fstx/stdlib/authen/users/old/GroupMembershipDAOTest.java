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
 * Created on Aug 21, 2003
 *
 */
package com.fstx.stdlib.authen.users.old;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Reid
 *  
 */
public class GroupMembershipDAOTest extends TestCase {

    private UserGroup group;

    private UserFilterParameters selectAll = new UserFilterParameters();

    protected void setUp() throws DAOException, Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        UserGroupDAO dao = UserGroupDAO.factory.build();
        GroupMembershipDAO gmdao = GroupMembershipDAO.factory.build();
        UserDAO udao = UserDAO.factory.build();
        group = new UserGroup("myEMSGroupsfsdfs");
        UserGroup g1 = new UserGroup("myOtherGroup");
        User u1 = UserFactory.singleton.getPersisted();
        User u2 = UserFactory.singleton.getPersisted();
        User u3 = UserFactory.singleton.getPersisted();
        group = dao.save(group);
        g1 = dao.save(g1);

        u1 = udao.save(u1);
        u2 = udao.save(u2);
        u3 = udao.save(u3);

        GroupMembership gm = new GroupMembership(group.getId(), u1.getId());
        GroupMembership gm1 = new GroupMembership(g1.getId(), u1.getId());
        GroupMembership gm2 = new GroupMembership(group.getId(), u3.getId());

        gm = gmdao.save(gm);
        gm1 = gmdao.save(gm1);
        gm2 = gmdao.save(gm2);
    }

    protected void tearDown() {

    }

    public void testBasic() {
        assertTrue(true);
    }

    public void testGetMemberList() throws DAOException {

        List list = GroupMembershipDAO.factory.build().find(
                GroupMembershipDAO.SELECT_BY_GROUP_USER_MEMBERS,
                group.getId().toString());

        assertTrue(list != null);
        assertTrue(list.size() > 0);

        assertTrue(list.get(0) instanceof User);

        log.info("group: " + group);
        log.info("members: " + list);
    }

    public void testGetMemberAndNonMemberList() throws Exception {
        Collection listUsers = UserDAO.factory.build().find(selectAll);

        GroupMembershipDAO dao = GroupMembershipDAO.factory.build();
        List listMembers = dao.find(
                GroupMembershipDAO.SELECT_BY_GROUP_USER_MEMBERS, ""
                        + group.getId());
        log.info("Members: " + listMembers);

        Collection nonMembers = UserDAO.factory.build().find(selectAll);
        Iterator i = listMembers.listIterator();

        while (i.hasNext()) {
            Object o = i.next();
            nonMembers.remove(o);
        }

        log.info("nonMemb: " + nonMembers);

        assertTrue(nonMembers.size() == listUsers.size() - listMembers.size());
    }

    public void testShouldHaveNoMembers() throws DAOException {
        UserGroup g1 = new UserGroup();

        GroupMembershipDAO dao = GroupMembershipDAO.factory.build();
        List list = dao.find(GroupMembershipDAO.SELECT_BY_GROUP_USER_MEMBERS,
                "" + g1.getId());

        assertTrue(list != null);
        assertTrue(list.size() == 0);

    }

    public void testFindByGroupAndUser() throws DAOException {
        //use the group build in setup...
        GroupMembershipDAO dao = GroupMembershipDAO.factory.build();
        //get a list of members...
        List listMembers = dao.find(
                GroupMembershipDAO.SELECT_BY_GROUP_USER_MEMBERS, ""
                        + group.getId());
        assertTrue(listMembers.size() > 0);
        User user = (User) listMembers.get(0);

        String[] data = { group.getId().toString(), user.getId().toString() };
        log.info("data:" + data);
        List one = dao.find(GroupMembershipDAO.SELECT_BY_GROUP_AND_USER, data);
        assertTrue(one != null);
        assertTrue(one.size() == 1);
        GroupMembership gm = (GroupMembership) one.get(0);
        assertTrue(gm.getUserId().equals(user.getId()));
        assertTrue(gm.getGroupId().equals(group.getId()));
    }

    static Log log = LogFactory.getLog(GroupMembershipDAOTest.class.getName());
}