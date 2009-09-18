/*
 * Created on Dec 14, 2005
 *
 */
package com.fivesticks.time.todo.rest;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.ParamUtility;
import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.todo.ToDo;
import com.fivesticks.time.todo.ToDoCriteriaParameters;
import com.fivesticks.time.todo.ToDoDisplayBuilder;
import com.fivesticks.time.todo.ToDoPriorityTypeEnum;
import com.fivesticks.time.todo.ToDoServiceDelegateFactory;
import com.fivesticks.time.ws.xwork.AbstractRestAction;
import com.fstx.stdlib.common.simpledate.SimpleDate;

public class RestToDoAction extends AbstractRestAction {

    private RestToDoActionType actionType;

    private String priority;

//    private String tag;

    private String projectKey;

    private Long projectId;

    private Collection listResults;

    private String detail;

    private Date deadline;

    protected void handleActionTypeValidate() throws Exception {

        this.actionType = RestToDoActionType.getByName(this.getAction());

        if (this.actionType == null) {
            throw new RuntimeException();
        }

        if (this.actionType == RestToDoActionType.POST) {
            if (!ParamValidator.isSearchable(this.getDetail())
                    || (!ParamValidator.isSearchable(this.getProjectId()) && !ParamValidator
                            .isSearchable(this.getProjectKey()))) {
                throw new RuntimeException(
                        "post requires  detail and either a project id or a project key.");
            }

        }

    }

    protected void handleAction() throws Exception {

        if (ParamUtility.getSearchable(this.getProjectKey()) != null) {
            Project project = ProjectServiceDelegateFactory.factory.build(
                    this.getAuthenticationBasedServiceSupport()
                            .getSessionContext()).getFstxProjectByShortName(
                    this.getProjectKey());
            if (project != null) {

                this.setProjectId(project.getId());
            }
        }

        if (this.getActionType() == RestToDoActionType.LIST) {
            handleActionList();
        } else {
            handleActionPost();
        }
    }

    public void handleActionList() throws Exception {

        ToDoCriteriaParameters params = new ToDoCriteriaParameters();
        
        /*
         * 2006-05-18 RSC Need this since we might be using a user token.
         */
        params.setAssignedToUser(ParamUtility.getSearchable(this
                .getAuthenticationBasedServiceSupport().getSessionContext()
                .getUser().getUsername()));
        params.setPriority(ParamUtility.getSearchable(this.getPriority()));
        params.setProjectId(this.getProjectId());
        params.setTodoComplete(Boolean.FALSE.toString());

        Collection rawToDos = ToDoServiceDelegateFactory.factory
                .build(
                        this.getAuthenticationBasedServiceSupport()
                                .getSessionContext()).find(params);

        Collection ret = new ToDoDisplayBuilder(this
                .getAuthenticationBasedServiceSupport().getSessionContext())
                .build(rawToDos);

        this.setListResults(ret);

    }

    public void handleActionPost() throws Exception {

        ToDo ret = new ToDo();

        /*
         * 2006-04-30 RSC need to do this as session context user instead of the
         * username param since people might be using user tokens instead of
         * system tokens.
         */
        ret.setAssignedToUser(this.getAuthenticationBasedServiceSupport()
                .getSessionContext().getUser().getUsername());
        ret.setEnteredByUser(this.getAuthenticationBasedServiceSupport()
                .getSessionContext().getUser().getUsername());
        ret.setProjectId(this.getProjectId());
        ret.setDetail(this.getDetail());

        if (ParamUtility.getSearchable(this.getPriority()) != null) {
            ToDoPriorityTypeEnum p = ToDoPriorityTypeEnum.getByName(this
                    .getPriority());
            if (p == null) {
                p = ToDoPriorityTypeEnum.Q1;
            }
            ret.setPriority(p.getName());

        } else {
            ret.setPriority(ToDoPriorityTypeEnum.Q1.getName());

        }

        if (this.getDeadline() != null) {
            ret.setDeadlineTimestamp(this.getDeadline());
        } else {
            ret.setDeadlineTimestamp(SimpleDate.factory.buildMidnight()
                    .getDate());
        }

//        if (this.getTag() != null) {
//            ret.setTag(this.getTag());
//        }

        ToDoServiceDelegateFactory.factory
                .build(
                        this.getAuthenticationBasedServiceSupport()
                                .getSessionContext()).save(ret);
    }

    /**
     * @return Returns the actionType.
     */
    public RestToDoActionType getActionType() {
        return actionType;
    }

    /**
     * @param actionType
     *            The actionType to set.
     */
    public void setActionType(RestToDoActionType actionType) {
        this.actionType = actionType;
    }

    /**
     * @return Returns the priority.
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority
     *            The priority to set.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return Returns the projectId.
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param projectId
     *            The projectId to set.
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    /**
     * @return Returns the projectShortName.
     */
    public String getProjectKey() {
        return projectKey;
    }

    /**
     * @param projectShortName
     *            The projectShortName to set.
     */
    public void setProjectKey(String projectShortName) {
        this.projectKey = projectShortName;
    }

//    /**
//     * @return Returns the tag.
//     */
//    public String getTag() {
//        return tag;
//    }
//
//    /**
//     * @param tag
//     *            The tag to set.
//     */
//    public void setTag(String tag) {
//        this.tag = tag;
//    }

    /**
     * @return Returns the results.
     */
    public Collection getListResults() {
        return listResults;
    }

    /**
     * @param results
     *            The results to set.
     */
    public void setListResults(Collection results) {
        this.listResults = results;
    }

    /**
     * @return Returns the deadline.
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * @param deadline
     *            The deadline to set.
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * @return Returns the detail.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail
     *            The detail to set.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

}
