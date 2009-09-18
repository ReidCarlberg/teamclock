/*
 * Created on Aug 22, 2005
 *
 */
package com.fivesticks.time.employee.team;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;

public class TeamMemberServiceDelegateImplTest extends TestCase {

    SystemOwner systemOwner;
    
    User user;
    
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        systemOwner = SystemOwnerTestFactory.getOwner();
        user = SystemUserTestFactory.singleton.buildOwner(systemOwner);
    }
    
    public void testBasic() throws Exception {
        Team ret = TeamTestFactory.getTeam(systemOwner);
        
        assertTrue(ret.getId() != null && ret.getId().longValue() > 0);
        
        
        assertTrue(ret.getName() != null);
        
        
        TeamMember member = TeamMemberTestFactory.getTeamMember(systemOwner,ret,user);
        
        assertTrue(member.getId() != null && member.getId().longValue() > 0);
    }

    public void testDelete() throws Exception {
        
        Team team = TeamTestFactory.getTeam(systemOwner);
        
        TeamMember member = TeamMemberTestFactory.getTeamMember(systemOwner,team,user);
        
        assertTrue(member.getId() != null);
        
        long id = member.getId().longValue();
        
        TeamMemberServiceDelegateFactory.factory.build(systemOwner).delete(member);
        
        TeamMember memberShouldBeNull = TeamMemberServiceDelegateFactory.factory.build(systemOwner).get(new Long(id));
        
        assertTrue(memberShouldBeNull == null);
    }
}
