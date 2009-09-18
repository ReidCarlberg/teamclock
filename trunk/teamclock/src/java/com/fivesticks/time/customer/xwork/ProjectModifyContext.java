/*
 * Created on Aug 25, 2004 by shuji
 */
package com.fivesticks.time.customer.xwork;

import com.fivesticks.time.customer.Project;

/**
 * @author shuji
 */
public class ProjectModifyContext {
    public static final String KEY = "context.modify.project";

    private Project targetProject;

    public Project getTargetProject() {
        return targetProject;
    }

    public void setTargetProject(Project targetProject) {
        this.targetProject = targetProject;
    }
}