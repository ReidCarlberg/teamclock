/*
 * Created on Aug 24, 2005
 *
 */
package com.fivesticks.time.employee.team;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

public class TeamMemberTestFactory {

    private static int count;
    
    public static TeamMember getTeamMember(SystemOwner systemOwner, Team team, User user ) throws Exception {
    
        count++;
        
        TeamMember ret = new TeamMember();
        
        ret.setTeamId(team.getId());
        ret.setUsername(user.getUsername());
        ret.setRole(TeamRoleType.LEADER.getName());
        ret.setActive(true);
        
        TeamMemberServiceDelegateFactory.factory.build(systemOwner).save(ret);
        
        return ret;
        
        
    }
}
