/*
 * Created on Sep 1, 2005
 *
 */
package com.fivesticks.time.employee.team.xwork;

import com.fivesticks.time.common.util.ValidationHelper;
import com.fivesticks.time.common.xwork.SessionContextAwareSupport;
import com.fivesticks.time.employee.team.Team;
import com.fivesticks.time.employee.team.TeamServiceDelegate;
import com.fivesticks.time.employee.team.TeamServiceDelegateFactory;

public class ModifyAction extends SessionContextAwareSupport implements
        ModifyContextAware {

    private ModifyContext modifyContext;

    private Long target;

    private String saveTeam;

    private String cancelTeam;

    private String deleteTeam;

    private Team targetTeam;

    public String execute() throws Exception {

        if (this.getCancelTeam() != null)
            return SUCCESS;

        TeamServiceDelegate sd = TeamServiceDelegateFactory.factory.build(this
                .getSystemOwner());

        if (this.getTarget() != null) {
            Team box = sd.get(this.getTarget());
            targetTeam = box;
            this.getModifyContext().setTarget(box);
            return INPUT;
        } else if (this.getDeleteTeam() != null) {
            // BoxDeleteCommand cmd = new
            // BoxDeleteCommand(this.getBoxModifyContext().getTarget());
            // this.getSessionContext().setDeleteContext(
            // DeleteContext.factory.build(cmd, this
            // .getSessionContext().getSuccessOverride()));
            throw new RuntimeException("not yet");
            // return GlobalForwardStatics.GLOBAL_COMMON_DELETE;
        } else if (this.getSaveTeam() == null) {
            this.getModifyContext().setTarget(null);
            this.setTargetTeam(new Team());
            return INPUT;
        }

        validate(sd);

        if (this.hasErrors()) {
            return INPUT;
        }

        if (this.getModifyContext().getTarget() != null) {
            this.getTargetTeam().setId(
                    this.getModifyContext().getTarget().getId());
        }

        sd.save(this.getTargetTeam());

        return SUCCESS;
    }

    public void validate(TeamServiceDelegate sd) throws Exception {

        ValidationHelper help = new ValidationHelper();

        if (help.isStringEmpty(this.getTargetTeam().getName())) {
            this.addFieldError("targetTeam.name", "Name is required.");
        }

        if (help.isStringEmpty(this.getTargetTeam().getNameShort())) {
            this.addFieldError("targetTeam.nameShort",
                    "Short name is required.");
        } else {
            Team temp = sd.get(this.getTargetTeam().getNameShort());

            if (temp != null) {
                if (this.getModifyContext().getTarget() == null
                        || (this.getModifyContext().getTarget() != null && !temp
                                .getId().equals(
                                        this.getModifyContext().getTarget()
                                                .getId()))) {
                    this.addFieldError("targetTeam.nameShort",
                            "Short name must be unique.");
                }
            }
        }

    }

    /**
     * @return Returns the cancelTeam.
     */
    public String getCancelTeam() {
        return cancelTeam;
    }

    /**
     * @param cancelTeam
     *            The cancelTeam to set.
     */
    public void setCancelTeam(String cancelTeam) {
        this.cancelTeam = cancelTeam;
    }

    /**
     * @return Returns the deleteTeam.
     */
    public String getDeleteTeam() {
        return deleteTeam;
    }

    /**
     * @param deleteTeam
     *            The deleteTeam to set.
     */
    public void setDeleteTeam(String deleteTeam) {
        this.deleteTeam = deleteTeam;
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
    public String getSaveTeam() {
        return saveTeam;
    }

    /**
     * @param saveTeam
     *            The saveTeam to set.
     */
    public void setSaveTeam(String saveTeam) {
        this.saveTeam = saveTeam;
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
     * @return Returns the targetTeam.
     */
    public Team getTargetTeam() {
        return targetTeam;
    }

    /**
     * @param targetTeam
     *            The targetTeam to set.
     */
    public void setTargetTeam(Team targetTeam) {
        this.targetTeam = targetTeam;
    }
}
