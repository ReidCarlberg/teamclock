/*
 * Created on Aug 24, 2005
 *
 */
package com.fivesticks.time.employee.team;

import com.fivesticks.time.systemowner.SystemOwner;

public class TeamTestFactory {

    private static int count; 
    public static Team getTeam(SystemOwner systemOwner) throws Exception {
        
        Team ret = new Team();
        
        ret.setName("team" + (++count));
        ret.setNameShort("short" + count);
        
        TeamServiceDelegateFactory.factory.build(systemOwner).save(ret);
        
        return ret;
    }
}
