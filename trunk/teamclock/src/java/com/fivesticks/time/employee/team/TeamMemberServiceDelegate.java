/*
 * Created on Aug 22, 2005
 *
 */
package com.fivesticks.time.employee.team;

import java.util.Collection;

public interface TeamMemberServiceDelegate {

    public void save (TeamMember team) throws TeamMemberServiceDelegateException ;
    
    public TeamMember get(Long id) throws TeamMemberServiceDelegateException;
    
    public void delete(TeamMember team) throws TeamMemberServiceDelegateException;
    
    public void deleteAll(Collection teamMembers) throws TeamMemberServiceDelegateException;
    
    public Collection find(TeamMemberCriteriaParameters params) throws TeamMemberServiceDelegateException;
    
}
