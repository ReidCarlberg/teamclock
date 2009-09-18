/*
 * Created on Aug 22, 2005
 *
 */
package com.fivesticks.time.employee.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.AbstractServiceDelegateException;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBD;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;

public class TeamServiceDelegateImpl extends AbstractServiceDelegate implements
        TeamServiceDelegate {

    public void save(Team team) throws TeamServiceDelegateException {

        if (!isValid(team)) {
            throw new TeamServiceDelegateException("invalid short name");
        }

        try {
            this.handleDecorate(team);
        } catch (AbstractServiceDelegateException e) {
            throw new TeamServiceDelegateException(e);
        }
        this.getDao().save(team);
    }

    private boolean isValid(Team team) throws TeamServiceDelegateException {
        boolean ret = true;

        TeamCriteriaParameters p = new TeamCriteriaParameters();

        p.setNameShort(team.getNameShort());

        Collection c = this.find(p);

        if (team.getId() == null) {
            if (c.size() != 0)
                ret = false;
        } else {
            if (c.size() > 1)
                ret = false;
            else if (c.size() == 1
                    && !((Team) c.toArray()[0]).getId().equals(team.getId()))
                ret = false;
        }

        return ret;

    }

    public Team get(Long id) throws TeamServiceDelegateException {
        Team ret = (Team) this.getDao().get(id);

        try {
            this.handleValidate(ret);
        } catch (AbstractServiceDelegateException e) {
            throw new TeamServiceDelegateException(e);
        }

        return ret;
    }

    public void delete(Team team) throws TeamServiceDelegateException {

        try {
            this.handleValidate(team);
        } catch (AbstractServiceDelegateException e) {
            throw new TeamServiceDelegateException(e);
        }

        /*
         * Delete the members
         */
        Collection members = this.getMembers(team);

        TeamMemberServiceDelegate tmsd = TeamMemberServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        for (Iterator iter = members.iterator(); iter.hasNext();) {
            TeamMember element = (TeamMember) iter.next();
            try {
                tmsd.delete(element);
            } catch (TeamMemberServiceDelegateException e) {
                throw new TeamServiceDelegateException(e);
            }
        }

        this.getDao().delete(team);
    }

    public Collection find(TeamCriteriaParameters params)
            throws TeamServiceDelegateException {

        try {
            this.handleDecorate(params);
        } catch (AbstractServiceDelegateException e) {
            throw new TeamServiceDelegateException(e);
        }

        return this.getDao().find(params);
    }

    public boolean isMember(Team team, User user)
            throws TeamServiceDelegateException {

        return this.isMember(team, user.getUsername());

    }

    public void join(Team team, User user, TeamRoleType teamRoleType)
            throws TeamServiceDelegateException {

        this.join(team,user.getUsername(),teamRoleType);


    }

    public void leave(Team team, User user) throws TeamServiceDelegateException {

        this.leave(team,user.getUsername());

    }

    public Collection getMembers(Team team) throws TeamServiceDelegateException {

        TeamMemberCriteriaParameters p = new TeamMemberCriteriaParameters();
        p.setTeamId(team.getId());

        TeamMemberServiceDelegate sd = TeamMemberServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        Collection c;
        try {
            c = sd.find(p);
        } catch (TeamMemberServiceDelegateException e) {
            throw new TeamServiceDelegateException(e);
        }

        return c;
    }

    public Collection getMembersAsUsers(Team team)
            throws TeamServiceDelegateException {

        Collection members = this.getMembers(team);

        Collection ret = new ArrayList();

        UserBD bd = UserBDFactory.factory.build();

        for (Iterator iter = members.iterator(); iter.hasNext();) {
            TeamMember element = (TeamMember) iter.next();

            try {
                ret.add(bd.getByUsername(element.getUsername()));
            } catch (UserBDException e) {
                throw new TeamServiceDelegateException(e);
            }

        }

        return ret;
    }

    public Collection getMembers(String nameShort)
            throws TeamServiceDelegateException {

        TeamCriteriaParameters tcp = new TeamCriteriaParameters();
        tcp.setNameShort(nameShort);
        Collection teams = this.find(tcp);
        if (teams.size() != 1) {
            throw new TeamServiceDelegateException(
                    "found too many teams with the same short name "
                            + nameShort + " (" + teams.size() + ")");
        }

        Team team = (Team) teams.toArray()[0];

        Collection members = this.getMembers(team);

        Collection ret = new ArrayList();

        for (Iterator iter = members.iterator(); iter.hasNext();) {
            TeamMember element = (TeamMember) iter.next();

            ret.add(element.getUsername());
        }

        return ret;
    }

    public Collection getTeams(String username)
            throws TeamServiceDelegateException {

        TeamMemberServiceDelegate sd = TeamMemberServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        TeamMemberCriteriaParameters p = new TeamMemberCriteriaParameters();
        p.setUsername(username);

        Collection mems;
        try {
            mems = sd.find(p);
        } catch (TeamMemberServiceDelegateException e) {
            throw new TeamServiceDelegateException(e);
        }

        Collection ret = new ArrayList();

        for (Iterator iter = mems.iterator(); iter.hasNext();) {
            TeamMember element = (TeamMember) iter.next();

            Team current = this.get(element.getTeamId());

            ret.add(current);

        }

        return ret;
    }

    public Team get(String nameShort) throws TeamServiceDelegateException {

        TeamCriteriaParameters p = new TeamCriteriaParameters();
        p.setNameShort(nameShort);

        Collection c = this.find(p);

        Team ret = null;

        if (c.size() == 1) {
            ret = (Team) c.toArray()[0];
        }

        return ret;
    }

    public Collection getMembersAsStrings(Team team)
            throws TeamServiceDelegateException {

        Collection temp = this.getMembers(team);

        Collection ret = new ArrayList();

        for (Iterator iter = temp.iterator(); iter.hasNext();) {
            TeamMember element = (TeamMember) iter.next();

            ret.add(element.getUsername());

        }
        return ret;
    }

    public void join(Team team, String username, TeamRoleType teamRoleType)
            throws TeamServiceDelegateException {
        
        if (isMember(team, username)) {
            return;
        }

        TeamMember member = new TeamMember();
        member.setTeamId(team.getId());
        member.setUsername(username);
        member.setRole(teamRoleType.getName());

        try {
            TeamMemberServiceDelegateFactory.factory.build(this.getSystemOwner())
                    .save(member);
        } catch (TeamMemberServiceDelegateException e) {
            throw new TeamServiceDelegateException(e);
        }        
    }

    public void leave(Team team, String username)
            throws TeamServiceDelegateException {
        
        if (!isMember(team, username)) {
            return;
        }

        TeamMemberCriteriaParameters p = new TeamMemberCriteriaParameters();
        p.setTeamId(team.getId());
        p.setUsername(username);

        TeamMemberServiceDelegate sd = TeamMemberServiceDelegateFactory.factory
                .build(this.getSystemOwner());

        Collection c;
        try {
            c = sd.find(p);
        } catch (TeamMemberServiceDelegateException e) {
            throw new TeamServiceDelegateException(e);
        }

        for (Iterator iter = c.iterator(); iter.hasNext();) {
            TeamMember element = (TeamMember) iter.next();
            try {
                sd.delete(element);
            } catch (TeamMemberServiceDelegateException e) {
                throw new TeamServiceDelegateException(e);
            }
        }        
    }

    public boolean isMember(Team team, String username)
            throws TeamServiceDelegateException {
        boolean ret = false;

        TeamMemberCriteriaParameters p = new TeamMemberCriteriaParameters();
        p.setTeamId(team.getId());
        p.setUsername(username);

        Collection c;
        try {
            c = TeamMemberServiceDelegateFactory.factory.build(this.getSystemOwner())
                    .find(p);
        } catch (TeamMemberServiceDelegateException e) {
            throw new TeamServiceDelegateException(e);
        }

        if (c.size() > 0) {
            ret = true;
        }

        return ret;
    }

}
