/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.todo.xwork;

import java.net.URLDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.fivesticks.time.common.parser.ProjectUserDetailParser;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegate;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.dashboard.util.DashboardRecentActionType;
import com.fivesticks.time.dashboard.util.DashboardRecentToDoAction;
import com.fivesticks.time.dashboard.xwork.AbstractDashboardJSONAction;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.todo.events.ToDoEventPublisher;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * 2006-06-14 updated from "detail" to "searchTerm" 2006-07-15 switched to
 * external parser
 * 
 * @author Reid
 * 
 */
public class AddFromDashboardAction extends AbstractDashboardJSONAction {

    private String searchTerm;

    private String submit;

    private Log log = LogFactory.getLog(AddFromDashboardAction.class);

    public String execute() throws Exception {

        log.info("here we are in Dashboard action");

        if (!StringUtils.hasText(this.getSearchTerm())) {
            log.info("INPUT");
            return INPUT; // dashboard
        }

        ToDo newToDo = new ToDo();

        /*
         * 2005-10-15 - RSC Do we need to URL Decode things?
         */
        this.setSearchTerm(URLDecoder.decode(this.getSearchTerm(), "UTF-8"));

        ProjectUserDetailParser parser = new ProjectUserDetailParser(this
                .getSessionContext());

        parser.parse(this.getSearchTerm());

        ProjectServiceDelegate projBd = ProjectServiceDelegateFactory.factory
                .build(this.getSessionContext());
        Project proj = parser.getTargetProject();

        String assignee = this.getSessionContext().getUser().getUsername();

        /*
         * 2006-05-22 RSC if they didn't select a project, but we're filtering
         * on one, let's get the project we're filtering on.
         */
        if (proj == null
                && this.getDashboardContext().getToDoProjectFilter() != null
                && this.getDashboardContext().getToDoProjectFilter()
                        .longValue() > 0) {
            proj = projBd.getFstxProject(this.getDashboardContext()
                    .getToDoProjectFilter());
        }

        if (proj != null) {
            newToDo.setProjectId(proj.getId());

        } else {

            newToDo.setProjectId(new Long(this.getSessionContext()
                    .getSettings().getActivityDefaultProject()));
        }

        if (parser.getTargetUser() != null) {
            assignee = parser.getTargetUser().getUsername();
        }

        newToDo.setDetail(parser.getTargetDetail());

        newToDo.setDeadlineTimestamp(SimpleDate.factory.buildMidnight(
                this.getSessionContext().getResolvedNow()).getDate());
        newToDo.setAssignedToUser(assignee);

        // this puts it in for the parsing.
        newToDo.setEnteredByUser(this.getSessionContext().getUser()
                .getUsername());

        newToDo.setPriority(this.getDashboardContext().getPriority());

        ToDoServiceDelegateFactory.factory.build(this.getSessionContext())
                .save(newToDo);

        /*
         * Nick 2005-10-7 If I assigned this to someone else we want it to flag,
         * like the assign from dashboard.
         */
        if (!assignee.equals(this.getSessionContext().getUser().getUsername())) {
            new ToDoEventPublisher().publishToDoReassignedEvent(this
                    .getSessionContext(), this.getSessionContext().getUser()
                    .getUser(), newToDo);

            /*
             * 2006-06-25 reid@fivesticks.com and we want to add it to the
             * recent events queue.
             */

            this.getSessionContext().addToRecentActions(
                    new DashboardRecentToDoAction(
                            DashboardRecentActionType.TODO_ASSIGNED, newToDo,
                            this.getSystemOwner()));

        }
        return SUCCESS;
    }

    /**
     * @return
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     * @return
     */
    public String getSubmit() {
        return submit;
    }

    /**
     * @param string
     */
    public void setSearchTerm(String string) {
        searchTerm = string;
    }

    /**
     * @param string
     */
    public void setSubmit(String string) {
        submit = string;
    }

}