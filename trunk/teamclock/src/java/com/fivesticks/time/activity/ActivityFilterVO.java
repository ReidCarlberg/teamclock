/*

 Five Sticks
 6957 W. North Ave., #202
 Chicago, IL 60302
 USA
 http://www.fivesticks.com
 mailto:info@fivesticks.com

 Copyright (c) 2003-2004, Five Sticks Publications, Inc.
 All rights reserved.

 Redistribution and use in source and binary forms, 
 with or without modification, are permitted provided
 that the following conditions are met:

 * Redistributions of source code must retain 
 the above copyright notice, this list of conditions 
 and the following disclaimer.
 * Redistributions in binary form must reproduce 
 the above copyright notice, this list of conditions 
 and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of the Five Sticks Publications, Inc.,
 nor the names of its contributors may be used to 
 endorse or promote products derived from this software 
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 SUCH DAMAGE.

 license: http://www.opensource.org/licenses/bsd-license.php

 This software uses a variety of Open Source software as
 a foundation.  See the file 

 [your install]/WEB-INF/component-acknowledgement.txt
 
 For more information.
 */
/*
 * Created on Sep 10, 2003
 *
 */
package com.fivesticks.time.activity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

/**
 * @author Reid
 *  
 */
public class ActivityFilterVO implements SystemOwnerKeyAware{

    private Long id;
    
    private String username;

    private String project;

    private String task;

    private Date start;

    private Date stop;

    private Long projectId;

    private Long taskId;

    private String comment;

    private String ownerKey;

    private Double minimumElapsed;

    private Double maximumElapsed;
    
    private Double minimumChargeableElapsed;

    private Double maximumChargeableElapsed;
    
    private Boolean sortDescending;

    private Boolean sortDescendingDateId = Boolean.FALSE;
    
    private Integer maxResults;
    /**
     * @return
     */
    public String getProject() {
        return project;
    }

    /**
     * @return
     */
    public Date getStart() {
        return start;
    }

    /**
     * @return
     */
    public Date getStop() {
        return stop;
    }

    /**
     * @return
     */
    public String getTask() {
        return task;
    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param string
     */
    public void setProject(String string) {
        project = string;
    }

    /**
     * @param date
     */
    public void setStart(Date date) {
        start = date;
    }

    /**
     * @param date
     */
    public void setStop(Date date) {
        stop = date;
    }

    /**
     * @param string
     */
    public void setTask(String string) {
        task = string;
    }

    /**
     * @param string
     */
    public void setUsername(String string) {
        username = string;
    }

    public String toString() {
        return new ToStringBuilder(this)
                .append("username:", this.getUsername()).append("project:",
                        this.getProject()).append("task:", this.getTask())
                .append("start:", this.getStart()).append("stop:",
                        this.getStop()).append(
                        "projectId:" + this.getProjectId()).toString();
    }

    /**
     * @return
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param string
     */
    public void setProjectId(Long string) {
        projectId = string;
    }

    /**
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param string
     */
    public void setComment(String string) {
        comment = string;
    }

    public String getOwnerKey() {
        return ownerKey;
    }

    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }

    /**
     * @return Returns the taskId.
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            The taskId to set.
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return Returns the maximumElapsed.
     */
    public Double getMaximumElapsed() {
        return maximumElapsed;
    }

    /**
     * @param maximumElapsed
     *            The maximumElapsed to set.
     */
    public void setMaximumElapsed(Double maximumElapsed) {
        this.maximumElapsed = maximumElapsed;
    }

    /**
     * @return Returns the minimumElapsed.
     */
    public Double getMinimumElapsed() {
        return minimumElapsed;
    }

    /**
     * @param minimumElapsed
     *            The minimumElapsed to set.
     */
    public void setMinimumElapsed(Double minimumElapsed) {
        this.minimumElapsed = minimumElapsed;
    }

    /**
     * @return Returns the maximumChargeableElapsed.
     */
    public Double getMaximumChargeableElapsed() {
        return maximumChargeableElapsed;
    }

    /**
     * @param maximumChargeableElapsed The maximumChargeableElapsed to set.
     */
    public void setMaximumChargeableElapsed(Double maximumChargeableElapsed) {
        this.maximumChargeableElapsed = maximumChargeableElapsed;
    }

    /**
     * @return Returns the minimumChargeableElapsed.
     */
    public Double getMinimumChargeableElapsed() {
        return minimumChargeableElapsed;
    }

    /**
     * @param minimumChargeableElapsed The minimumChargeableElapsed to set.
     */
    public void setMinimumChargeableElapsed(Double minimumChargeableElapsed) {
        this.minimumChargeableElapsed = minimumChargeableElapsed;
    }

    /**
     * @return Returns the sortDescending.
     */
    public Boolean getSortDescending() {
        return sortDescending;
    }

    /**
     * @param sortDescending The sortDescending to set.
     */
    public void setSortDescending(Boolean sortDescending) {
        this.sortDescending = sortDescending;
    }

    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Returns the maxResults.
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * @param maxResults The maxResults to set.
     */
    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * @return Returns the sortDescendingDateId.
     */
    public Boolean getSortDescendingDateId() {
        return sortDescendingDateId;
    }

    /**
     * @param sortDescendingDateId The sortDescendingDateId to set.
     */
    public void setSortDescendingDateId(Boolean sortDescendingDateId) {
        this.sortDescendingDateId = sortDescendingDateId;
    }
}