/*
 * Created on Jul 14, 2006
 *
 */
package com.fivesticks.time.common.parser;

import org.springframework.util.StringUtils;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.systemowner.SystemUser;
import com.fivesticks.time.systemowner.SystemUserServiceDelegateException;
import com.fstx.stdlib.authen.users.User;

public class ProjectUserDetailParser extends AbstractQuickEntryParser {

    private Project targetProject;

    private User targetUser;

    private String targetDetail;

    public ProjectUserDetailParser(SessionContext sessionContext) {
        super(sessionContext);
    }

    public void parse(String input) throws Exception {

        if (!StringUtils.hasText(input)) {
            return;
        }

        input = input.trim();

        String[] parts = input.split(" ");

        SystemUser tempSystemUser = null;

        int rebuildStartAt = 0;

        int firstNonEmpty = this.getFirstNonEmptyElement(parts, 0);
        int secondNonEmpty = this.getFirstNonEmptyElement(parts, firstNonEmpty + 1);

        Project tempProject = this.getProjectServiceDelegate()
                .getFstxProjectByShortName(parts[firstNonEmpty]);


        try {
            if (tempProject == null) {
                tempSystemUser = this.getSystemUserServiceDelegate()
                        .getBySystemAndUser(
                                this.getSessionContext().getSystemOwner(),
                                parts[firstNonEmpty]);
                if (tempSystemUser != null) {
                    rebuildStartAt = secondNonEmpty;
                }
            } else if (parts.length > 1) {
                tempSystemUser = this.getSystemUserServiceDelegate()
                        .getBySystemAndUser(
                                this.getSessionContext().getSystemOwner(),
                                parts[secondNonEmpty]);
                if (tempSystemUser != null) {
                    rebuildStartAt = secondNonEmpty + 1;
                } 
            }
        } catch (SystemUserServiceDelegateException e) {
            // do nothing. just means it wasn't found.
            if (tempProject != null) {
                rebuildStartAt = secondNonEmpty;
            }
        }

        User tempUser = null;

        if (tempSystemUser != null) {
            tempUser = this.getUserBD().getByUsername(
                    tempSystemUser.getUsername());
            
            if (tempUser.isBooleanInactive()) {
            	tempUser = null;
            }
        }

        StringBuffer tempDetail = new StringBuffer();

        for (int i = rebuildStartAt; i < parts.length; i++) {
            tempDetail.append(parts[i]);
            if (i < parts.length - 1) {
                tempDetail.append(" ");
            }
        }

        this.setTargetProject(tempProject);

        this.setTargetUser(tempUser);

        this.setTargetDetail(tempDetail.toString().trim());
    }

    public int getFirstNonEmptyElement(String[] searchTarget, int startIndex) {
        int nonEmptyIndex = startIndex;
        boolean found = false;

        for (nonEmptyIndex = startIndex; nonEmptyIndex < searchTarget.length; nonEmptyIndex++) {
            if (StringUtils.hasText(searchTarget[nonEmptyIndex])) {
                found = true;
                break;
            }
        }

        if (found) {
            return nonEmptyIndex;
        } else {
            return -1;
        }
    }

    /**
     * @return Returns the detail.
     */
    public String getTargetDetail() {
        return targetDetail;
    }

    /**
     * @param detail
     *            The detail to set.
     */
    public void setTargetDetail(String detail) {
        this.targetDetail = detail;
    }

    /**
     * @return Returns the targetProject.
     */
    public Project getTargetProject() {
        return targetProject;
    }

    /**
     * @param targetProject
     *            The targetProject to set.
     */
    public void setTargetProject(Project targetProject) {
        this.targetProject = targetProject;
    }

    /**
     * @return Returns the targetUser.
     */
    public User getTargetUser() {
        return targetUser;
    }

    /**
     * @param targetUser
     *            The targetUser to set.
     */
    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

}
