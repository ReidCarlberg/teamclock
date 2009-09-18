package com.fivesticks.time.activity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fivesticks.time.common.AbstractTimeObject;

/** @author Hibernate CodeGenerator */
public class Activity extends AbstractTimeObject implements Serializable {

//    /** identifier field */
//    private Long id;
//
//    private String ownerKey;

    private Long oldId;

    /** persistent field */
    private String username;

    /** persistent field */
    private String project;

    /** persistent field */
    private String task;

    /** persistent field */
    private Long projectId;

    /** persistent field */
    private Long taskId;

    /** nullable persistent field */
    private java.util.Date date;

    /** nullable persistent field */
    private java.util.Date start;

    /** nullable persistent field */
    private java.util.Date stop;

    /** nullable persistent field */
    private Double elapsed;
    
    private Double chargeableElapsed;

    /** nullable persistent field */
    private String comments;

    /** nullable persistent field */
    private boolean reported;

    private Long toDoId;
    
    private Long acctId;
    


    /** full constructor */
    public Activity(java.lang.String username, java.lang.String project,
            java.lang.String task, java.lang.Long projectId,
            java.lang.Long taskId, java.util.Date date, java.util.Date start,
            java.util.Date stop, java.lang.Double elapsed,
            java.lang.String comments, boolean reported) {
        this.username = username;
        this.project = project;
        this.task = task;
        this.projectId = projectId;
        this.taskId = taskId;
        this.date = date;
        this.start = start;
        this.stop = stop;
        this.elapsed = elapsed;
        this.comments = comments;
        this.reported = reported;
    }

    /** default constructor */
    public Activity() {
    }

    /** minimal constructor */
    public Activity(java.lang.String username, java.lang.String project,
            java.lang.String task, java.lang.Long projectId,
            java.lang.Long taskId) {
        this.username = username;
        this.project = project;
        this.task = task;
        this.projectId = projectId;
        this.taskId = taskId;
    }

//    public java.lang.Long getId() {
//        return this.id;
//    }
//
//    public void setId(java.lang.Long id) {
//        this.id = id;
//    }

    public java.lang.String getUsername() {
        return this.username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getProject() {
        return this.project;
    }

    public void setProject(java.lang.String project) {
        this.project = project;
    }

    public java.lang.String getTask() {
        return this.task;
    }

    public void setTask(java.lang.String task) {
        this.task = task;
    }

    public java.lang.Long getProjectId() {
        return this.projectId;
    }

    public void setProjectId(java.lang.Long projectId) {
        this.projectId = projectId;
    }

    public java.lang.Long getTaskId() {
        return this.taskId;
    }

    public void setTaskId(java.lang.Long taskId) {
        this.taskId = taskId;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public java.util.Date getStart() {
        return this.start;
    }

    public void setStart(java.util.Date start) {
        this.start = start;
    }

    public java.util.Date getStop() {
        return this.stop;
    }

    public void setStop(java.util.Date stop) {
        this.stop = stop;
    }

    public java.lang.Double getElapsed() {
        return this.elapsed;
    }

    public void setElapsed(java.lang.Double elapsed) {
        this.elapsed = elapsed;
    }

    public java.lang.String getComments() {
        return this.comments;
    }

    public void setComments(java.lang.String comments) {
        this.comments = comments;
    }

    public boolean isReported() {
        return this.reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public String toString() {
        return new ToStringBuilder(this).append("id", getId()).toString();
    }

    public boolean equals(Object other) {
        if (!(other instanceof Activity))
            return false;
        Activity castOther = (Activity) other;
        return new EqualsBuilder().append(this.getId(), castOther.getId())
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder().append(getId()).toHashCode();
    }

    /**
     * @return
     */
    public Long getOldId() {
        return oldId;
    }

    /**
     * @param long1
     */
    public void setOldId(Long long1) {
        oldId = long1;
    }

    /**
     * @return Returns the chargeableElapsed.
     */
    public Double getChargeableElapsed() {
        return chargeableElapsed;
    }

    /**
     * @param chargeableElapsed The chargeableElapsed to set.
     */
    public void setChargeableElapsed(Double chargeableElapsed) {
        this.chargeableElapsed = chargeableElapsed;
    }

    public Long getToDoId() {
        return toDoId;
    }

    public void setToDoId(Long toDoId) {
        this.toDoId = toDoId;
    }
    
    /**
     * @return the acctId
     */
    public Long getAcctId() {
        return acctId;
    }

    /**
     * @param acctId the acctId to set
     */
    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }    


}