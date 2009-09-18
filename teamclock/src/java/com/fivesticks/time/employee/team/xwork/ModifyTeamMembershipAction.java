/*
 * Created on Sep 1, 2005
 *
 */
package com.fivesticks.time.employee.team.xwork;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.employee.team.Team;
import com.fivesticks.time.employee.team.TeamRoleType;
import com.fivesticks.time.employee.team.TeamServiceDelegate;
import com.fivesticks.time.employee.team.TeamServiceDelegateFactory;

public class ModifyTeamMembershipAction extends SessionContextAwareSupport
        implements ModifyContextAware {

    private ModifyContext modifyContext;

    private Long target;

    private String saveTeamMembers;

    private String cancelTeamMembers;
    
    private String[] selectedMembers;
    
//    private Log log = LogFactory.getLog(ModifyTeamMembershipAction.class);

    public String execute() throws Exception {

        if (this.getCancelTeamMembers() != null
                || (this.getTarget() == null && this.getSaveTeamMembers() == null))
            return SUCCESS;

        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(this
                .getSystemOwner());
        
        String[] proto = {};

        if (this.getTarget() != null) {
            Team team = sd.get(this.getTarget());
            this.getModifyContext().setTarget(team);
            
            Collection members = sd.getMembersAsStrings(team);
            
            
            
            this.setSelectedMembers((String[])members.toArray(proto));
            
//            for (int i = 0; i < this.getSelectedMembers().length; i++) {
//                log.info("selected member " + this.getSelectedMembers()[i]);
//            }
            
            
            
            return INPUT;
        } 

        if (this.getSaveTeamMembers() == null || this.getModifyContext().getTarget() == null) {
            //shouldn't be here -- just go back to the input screen.
            return SUCCESS;
        }
        
        Collection currentMembers = sd.getMembersAsStrings(this.getModifyContext().getTarget());
        
        //add any new members
        
        if (this.getSelectedMembers() == null) {
            this.setSelectedMembers(proto);
        }
        for (int i = 0; i < this.getSelectedMembers().length; i++) {
            if (currentMembers.contains(this.getSelectedMembers()[i])) {
                currentMembers.remove(this.getSelectedMembers()[i]);
            } else {
               sd.join(this.getModifyContext().getTarget(),this.getSelectedMembers()[i],TeamRoleType.STANDARD);
            }
        }
        
        //now remove anyone left over
        for (Iterator iter = currentMembers.iterator(); iter.hasNext();) {
            String element = (String) iter.next();
            sd.leave(this.getModifyContext().getTarget(), element);
        }

        return SUCCESS;
    }



    /**
     * @return Returns the cancelTeam.
     */
    public String getCancelTeamMembers() {
        return cancelTeamMembers;
    }

    /**
     * @param cancelTeam
     *            The cancelTeam to set.
     */
    public void setCancelTeamMembers(String cancelTeam) {
        this.cancelTeamMembers = cancelTeam;
    }

    /**
     * @return Returns the modifyContext.
     */
    public ModifyContext getModifyContext() {
        return modifyContext;
    }

    /**
     * @param modifyContext
     *            The modifyContext to set.
     */
    public void setModifyContext(ModifyContext modifyContext) {
        this.modifyContext = modifyContext;
    }

    /**
     * @return Returns the saveTeam.
     */
    public String getSaveTeamMembers() {
        return saveTeamMembers;
    }

    /**
     * @param saveTeam
     *            The saveTeam to set.
     */
    public void setSaveTeamMembers(String saveTeam) {
        this.saveTeamMembers = saveTeam;
    }

    /**
     * @return Returns the target.
     */
    public Long getTarget() {
        return target;
    }

    /**
     * @param target
     *            The target to set.
     */
    public void setTarget(Long target) {
        this.target = target;
    }



    /**
     * @return Returns the selectedMembers.
     */
    public String[] getSelectedMembers() {
        return selectedMembers;
    }



    /**
     * @param selectedMembers The selectedMembers to set.
     */
    public void setSelectedMembers(String[] selectedMembers) {
        this.selectedMembers = selectedMembers;
    }

}
