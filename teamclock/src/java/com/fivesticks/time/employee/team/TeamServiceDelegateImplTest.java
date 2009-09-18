/*
 * Created on Aug 22, 2005
 *
 */
package com.fivesticks.time.employee.team;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;

public class TeamServiceDelegateImplTest extends TestCase {

    SystemOwner systemOwner;

    User user;

    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();

        systemOwner = SystemOwnerTestFactory.getOwner();

        user = SystemUserTestFactory.singleton.buildOwner(systemOwner);

    }

    public void testBasic() throws Exception {
        Team t = TeamTestFactory.getTeam(systemOwner);
    }
    
    public void testIsMember() throws Exception {
        
        Team t = TeamTestFactory.getTeam(systemOwner);
        
        assertTrue(!TeamServiceDelegateFactory.factory.build(systemOwner).isMember(t,user));
        
    }
    
    public void testJoin() throws Exception {
        
        Team t = TeamTestFactory.getTeam(systemOwner);
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(systemOwner);
        
        sd.join(t, user, TeamRoleType.LEADER);
        
        assertTrue(sd.isMember(t,user));
    }

    public void testGetMembers() throws Exception {
        
        Team t = TeamTestFactory.getTeam(systemOwner);
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(systemOwner);
        
        sd.join(t,user,TeamRoleType.LEADER);
        
        assertEquals(1,sd.getMembers(t).size());
        
        sd.join(t,SystemUserTestFactory.singleton.buildOwner(systemOwner),TeamRoleType.STANDARD);
        
        assertEquals(2,sd.getMembers(t).size());
        
    }


    public void testGetMembersMultipleOwners() throws Exception {
        
        Team t = TeamTestFactory.getTeam(systemOwner);
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(systemOwner);
        
        sd.join(t,user,TeamRoleType.LEADER);
        
        assertEquals(1,sd.getMembers(t).size());
        
        sd.join(t,SystemUserTestFactory.singleton.buildOwner(systemOwner),TeamRoleType.STANDARD);
        
        assertEquals(2,sd.getMembers(t).size());
        
        SystemOwner systemOwner2 = SystemOwnerTestFactory.getOwner();
        
        Team t2 = TeamTestFactory.getTeam(systemOwner2);
        
        TeamServiceDelegate sd2 = TeamServiceDelegateFactory.factory.build(systemOwner2);
        
        sd2.join(t2, SystemUserTestFactory.singleton.buildOwner(systemOwner2),TeamRoleType.STANDARD);
        
        assertEquals(2,sd.getMembers(t).size());
        
        assertEquals(1,sd2.getMembers(t2).size());
        
    }
    
    public void testGetMembersAsUsers() throws Exception {
        
        Team t = TeamTestFactory.getTeam(systemOwner);
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(systemOwner);
        
        sd.join(t, user, TeamRoleType.STANDARD);
        
        Collection u = sd.getMembersAsUsers(t);
        
        assertEquals(1,u.size());
        
        assertTrue(u.toArray()[0] instanceof User);
        
        assertEquals(user.getUsername(), ((User)u.toArray()[0]).getUsername());
    }
    
    public void testUniqueShortName() throws Exception {
        Team t = TeamTestFactory.getTeam(systemOwner);
        
        Team t2 = TeamTestFactory.getTeam(systemOwner);
        
        Team t3 = TeamTestFactory.getTeam(systemOwner);
        
        t3.setNameShort(t.getNameShort());
        
        try {
            TeamServiceDelegateFactory.factory.build(systemOwner).save(t3);
            assertTrue(false); 
        } catch (TeamServiceDelegateException e) {
            //good
        }
    }
    
    public void testGetTeams() throws Exception {
        
        Team t = TeamTestFactory.getTeam(systemOwner);
        
        Team t2 = TeamTestFactory.getTeam(systemOwner);
        
        Team t3 = TeamTestFactory.getTeam(systemOwner);
        
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(systemOwner);
        
        sd.join(t, user, TeamRoleType.STANDARD);
        sd.join(t2, user, TeamRoleType.STANDARD);
        
        Collection c = sd.getTeams(user.getUsername());
        
        assertEquals(2,c.size());
        
        sd.leave(t2,user);
        
        assertEquals(1,sd.getTeams(user.getUsername()).size());
        
        
    }
    
    public void testGetTeamByNameShort() throws Exception {
        

        
        Team t2 = TeamTestFactory.getTeam(systemOwner);
        
 
        
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(systemOwner);
        
        Team t2a = sd.get(t2.getNameShort());
        
        assertTrue(t2a != null);
        
        Team tNull = sd.get("nothingFound");
        
        assertTrue(tNull == null);
        
        
    }    
    
    public void testForDeleteWithMembers() throws Exception {
        
        Team t = TeamTestFactory.getTeam(systemOwner);
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(systemOwner);
        
        sd.join(t, user, TeamRoleType.STANDARD);
        
        
        Collection c = sd.getTeams(user.getUsername());
        
        assertEquals(1,c.size());
        
        TeamMemberCriteriaParameters p = new TeamMemberCriteriaParameters();
        p.setUsername(user.getUsername());
        p.setTeamId(t.getId());
        
        TeamMember tm = (TeamMember) TeamMemberServiceDelegateFactory.factory.build(systemOwner).find(p).toArray()[0];
        
        assertTrue(tm != null);
        
        long tmid = tm.getId().longValue();
        
        sd.delete(t);
        
        TeamMember tmShouldBeNull = TeamMemberServiceDelegateFactory.factory.build(systemOwner).get(new Long(tmid));
        
        assertTrue(tmShouldBeNull == null);
        

        
        
        
        
    }
}
