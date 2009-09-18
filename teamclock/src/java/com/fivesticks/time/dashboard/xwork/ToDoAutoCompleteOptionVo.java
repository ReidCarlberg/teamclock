/*
 * Created on Sep 30, 2005
 *
 * 
 */
package com.fivesticks.time.dashboard.xwork;

import com.fivesticks.time.customer.Project;
import com.fstx.stdlib.authen.users.User;

public class ToDoAutoCompleteOptionVo {

    private Project project;

    private User user;

    public ToDoAutoCompleteOptionVo(Project project, User userElement) {
        super();
        this.project = project;
        this.user = userElement;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
