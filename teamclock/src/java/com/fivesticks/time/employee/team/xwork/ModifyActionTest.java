/*
 * Created on Sep 8, 2005
 *
 */
package com.fivesticks.time.employee.team.xwork;

import junit.framework.TestCase;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextTestFactory;
import com.fivesticks.time.employee.team.Team;
import com.fivesticks.time.employee.team.TeamCriteriaParameters;
import com.fivesticks.time.employee.team.TeamServiceDelegateFactory;
import com.fivesticks.time.employee.team.TeamTestFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.systemowner.SystemUserTestFactory;
import com.fivesticks.time.testutil.JunitSettings;
import com.fstx.stdlib.authen.users.User;
import com.opensymphony.xwork.ActionSupport;

public class ModifyActionTest extends TestCase {

    SystemOwner systemOwner;
    
    User user;
    
    SessionContext sessionContext;
    
    protected void setUp() throws Exception {
        super.setUp();
        
        JunitSettings.initializeTestSystem();
        
        systemOwner = SystemOwnerTestFactory.getOwner();
        
        user = SystemUserTestFactory.singleton.buildOwner(systemOwner);
        
        sessionContext = SessionContextTestFactory.getContext(systemOwner, user);
    }
    
    public void testBasicAddInput() throws Exception {
        
        ModifyAction action = new ModifyAction();
        
        action.setSessionContext(sessionContext);
        
        action.setModifyContext(new ModifyContext());
        
        String r = action.execute();
        
        assertEquals(ActionSupport.INPUT,r);
        
    }
    
    public void testBasicAddSuccess() throws Exception {

        ModifyAction action = new ModifyAction();
        
        action.setSessionContext(sessionContext);
        action.setModifyContext(new ModifyContext());
        action.setTargetTeam(new Team());
        
        action.getTargetTeam().setName("name");
        action.getTargetTeam().setNameShort("shortName");
        
        action.setSaveTeam("save");
        
        String r = action.execute();
        
        assertEquals(ActionSupport.SUCCESS,r);
        
        assertEquals(TeamServiceDelegateFactory.factory.build(systemOwner).find(new TeamCriteriaParameters()).size(),1);
    }

    public void testBasicAddFailsDuplicateShortName() throws Exception {

        Team team = TeamTestFactory.getTeam(systemOwner);
        
        ModifyAction action = new ModifyAction();
        
        action.setSessionContext(sessionContext);
        action.setModifyContext(new ModifyContext());
        action.setTargetTeam(new Team());
        
        action.getTargetTeam().setName(team.getName());
        action.getTargetTeam().setNameShort(team.getNameShort());
        
        action.setSaveTeam("save");
        
        String r = action.execute();
        
        assertEquals(ActionSupport.INPUT,r);
        
        assertTrue(action.hasFieldErrors());
    }

}
