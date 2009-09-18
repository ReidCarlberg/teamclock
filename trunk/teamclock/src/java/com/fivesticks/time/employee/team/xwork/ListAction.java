/*
 * Created on Aug 31, 2005
 *
 */
package com.fivesticks.time.employee.team.xwork;

import java.util.Collection;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.employee.team.TeamCriteriaParameters;
import com.fivesticks.time.employee.team.TeamServiceDelegateFactory;

public class ListAction extends SessionContextAwareSupport {

    private Collection teams;
    
    public String execute() throws Exception {
        
     
        this.setTeams(TeamServiceDelegateFactory.factory.build(this.getSystemOwner()).find(new TeamCriteriaParameters()));
        
        return SUCCESS;
    }

    /**
     * @return Returns the teams.
     */
    public Collection getTeams() {
        return teams;
    }

    /**
     * @param teams The teams to set.
     */
    public void setTeams(Collection teams) {
        this.teams = teams;
    }
    
}
