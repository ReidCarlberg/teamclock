/*
 * Created on Aug 22, 2005
 *
 */
package com.fivesticks.time.employee.team;

import java.util.Collection;

import com.fstx.stdlib.authen.users.User;

public interface TeamServiceDelegate {

    public void save (Team team) throws TeamServiceDelegateException ;
    
    public Team get(Long id) throws TeamServiceDelegateException;
    
    public Team get(String nameShort) throws TeamServiceDelegateException;
    
    public void delete(Team team) throws TeamServiceDelegateException;
    
    public Collection find(TeamCriteriaParameters params) throws TeamServiceDelegateException;
    
    public void join(Team team, User user, TeamRoleType teamRoleType) throws TeamServiceDelegateException;
    
    public void join(Team team, String username, TeamRoleType teamRoleType) throws TeamServiceDelegateException;
    
    public void leave(Team team, User user) throws TeamServiceDelegateException;
    
    public void leave(Team team, String username) throws TeamServiceDelegateException;
    
    public Collection getMembers(Team team) throws TeamServiceDelegateException;
    
    public Collection getMembersAsUsers(Team team) throws TeamServiceDelegateException;
    
    public Collection getMembersAsStrings(Team team) throws TeamServiceDelegateException;
    
    public Collection getMembers(String nameShort) throws TeamServiceDelegateException;
    
    public Collection getTeams(String username) throws TeamServiceDelegateException;
    
    public boolean isMember(Team team, User user) throws TeamServiceDelegateException;
    
    public boolean isMember(Team team, String username) throws TeamServiceDelegateException;
    
}
