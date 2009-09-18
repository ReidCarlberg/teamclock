/*
 * Created on Sep 9, 2005
 *
 */
package com.fivesticks.time.employee.team.xwork;

import java.util.Collection;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.employee.team.Team;
import com.fivesticks.time.employee.team.TeamRoleType;
import com.fivesticks.time.employee.team.TeamServiceDelegate;
import com.fivesticks.time.employee.team.TeamServiceDelegateFactory;
import com.fivesticks.time.employee.team.TeamTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

public class ModifyTeamMembershipActionTest extends TestCase {

    SystemOwner systemOwner;
    
    User user;
    
    User user2;
    
    User user3;
    
    Team team;
    
    SessionContext sessionContext;
    
    ModifyTeamMembershipAction action;
    
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        
        systemOwner = SystemOwnerTestFactory.getOwner();
        
        user = SystemUserTestFactory.singleton.buildOwner(systemOwner);
        user2 = SystemUserTestFactory.singleton.buildOwner(systemOwner);
        user3 = SystemUserTestFactory.singleton.buildOwner(systemOwner);
        
        team = TeamTestFactory.getTeam(systemOwner);
        
        sessionContext = SessionContextTestFactory.getContext(systemOwner, user);
        
        action = new ModifyTeamMembershipAction();
        
        action.setSessionContext(sessionContext);
    }
    
    public void testBasicAdd() throws Exception {
        
        action.setModifyContext(new ModifyContext());
        action.getModifyContext().setTarget(team);

        String[] members = {user.getUsername()};
        
        action.setSelectedMembers(members);
        action.setSaveTeamMembers("save");
        
        String r = action.execute();
        
        assertEquals(ActionSupport.SUCCESS,r);
        
        assertEquals(1, TeamServiceDelegateFactory.factory.build(systemOwner).getMembers(team).size());
        
    }
    
    public void testBasicInput() throws Exception {
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(systemOwner);
        
        sd.join(team,user,TeamRoleType.STANDARD);
        sd.join(team, user2, TeamRoleType.STANDARD);
        
        action.setModifyContext(new ModifyContext());
        action.setTarget(team.getId());
        
        String r = action.execute();
        
        assertEquals(ActionSupport.INPUT,r);
        
        assertEquals(2,action.getSelectedMembers().length);
    }
    
    public void testBasicRemove() throws Exception {
        
        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(systemOwner);
        
        sd.join(team,user,TeamRoleType.STANDARD);
        sd.join(team, user2, TeamRoleType.STANDARD);
        
        action.setModifyContext(new ModifyContext());
        action.getModifyContext().setTarget(team);
        
        String[] mem = {user.getUsername(), user3.getUsername()};
        action.setSelectedMembers(mem);
        action.setSaveTeamMembers("save");
        
        
        String r = action.execute();
        
        assertEquals(ActionSupport.SUCCESS,r);
        
        Collection memAfter = sd.getMembersAsStrings(team);
        
        assertTrue(memAfter.contains(user.getUsername()));
        assertTrue(memAfter.contains(user3.getUsername()));
        assertTrue(!memAfter.contains(user2.getUsername()));
        
        
        
    }

}
