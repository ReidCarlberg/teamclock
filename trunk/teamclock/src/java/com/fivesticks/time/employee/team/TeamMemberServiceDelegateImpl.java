/*
 * Created on Aug 22, 2005
 *
 */
package com.fivesticks.time.employee.team;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;

public class TeamMemberServiceDelegateImpl extends AbstractServiceDelegate
        implements TeamMemberServiceDelegate {

    public void save(TeamMember team) throws TeamMemberServiceDelegateException {
        
        try {
            this.handleDecorate(team);
        } catch (AbstractServiceDelegateException e) {
            throw new TeamMemberServiceDelegateException(e);
        }
        
        this.getDao().save(team);
        
    }

    public TeamMember get(Long id) throws TeamMemberServiceDelegateException {

        TeamMember ret = (TeamMember) this.getDao().get(id);
        
        try {
            this.handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
         throw new TeamMemberServiceDelegateException(e);
        }
        
        return ret;
    }

    public void delete(TeamMember teamMember)
            throws TeamMemberServiceDelegateException {
        
        try {
            this.handleValidate(teamMember);
        } catch (AbstractServiceDelegateException e) {
            throw new TeamMemberServiceDelegateException(e);
        }
        
        this.getDao().delete(teamMember);
    }

    public Collection find(TeamMemberCriteriaParameters params)
            throws TeamMemberServiceDelegateException {

        try {
            this.handleDecorate(params);
        } catch (AbstractServiceDelegateException e) {
            throw new TeamMemberServiceDelegateException(e);
        }
        
        
        return this.getDao().find(params);
    }

    public void deleteAll(Collection teamMembers) throws TeamMemberServiceDelegateException {
        
        for (Iterator iter = teamMembers.iterator(); iter.hasNext();) {
            TeamMember element = (TeamMember) iter.next();
            try {
                this.handleValidate(element);
            } catch (AbstractServiceDelegateException e) {
                throw new TeamMemberServiceDelegateException(e);
            }
        }
        this.getDao().deleteAll(teamMembers);
        
    }

}
