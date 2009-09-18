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
 * Created on Sep 9, 2003
 *
 */
package com.fivesticks.time.activity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.common.SessionContextAware;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 * 
 */
public class ActivityWrapper implements SessionContextAware, Serializable {

    private Activity time;

    private String startSimple;

    private String stopSimple;

    private String newTask;

    private String newProject;

    private String projectName;

    private String projectShortName;

    private SessionContext sessionContext;

    private static DecimalFormat df = new DecimalFormat("#,##0.000;(#)");
    
    public ActivityWrapper(Activity time) {
        this.time = time;
        handleSimpleDates();

    }

    /**
     * 
     */
    private void handleSimpleDates() {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");

        if (time.getStart() != null) {
            startSimple = sdf.format(time.getStart());
        }

        if (time.getStop() != null) {
            stopSimple = sdf.format(time.getStop());
        }

    }

    /**
     * @return
     */
    public String getComments() {
        return time.getComments();
    }

    public String getFormattedComments() {
        if (this.getComments() != null)
            return this.getComments().toUpperCase();

        return null;
    }

    /**
     * @return
     */
    public String getDate() {
        if (time.getDate() != null)
            return SimpleDate.factory.build(time.getDate()).getMmddyyyy();

        return null;
    }

    /**
     * @return
     */
    public Double getElapsed() {
        return time.getElapsed();
    }
    
    public Double getChargeableElapsed() {
        return time.getChargeableElapsed();
    }

    public String getFormattedElapsed() {
        

        Double e = this.getElapsed();

        if (e != null)
            return df.format(this.getElapsed());

        return "";
    }
    
    public String getFormattedChargeableElapsed() {
        

        Double e = this.getChargeableElapsed();

        if (e != null)
            return df.format(e);

        return "";
    }

    /**
     * @return
     */
    public Long getId() {
        return time.getId();
    }

    /**
     * @return
     */
    public String getProject() {
        if (projectName == null) {

            this.initializeNames();
        }
        return projectName;
        // return time.getProject();
    }

    private void initializeNames() {
        Project project = null;
        try {
            project = ProjectServiceDelegateFactory.factory.build(this.getSessionContext())
                    .getFstxProject(time.getProjectId());
            projectName = project.getLongName();
            projectShortName = project.getShortName();
        } catch (ProjectBDException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public String getProjectShort() {
        if (projectShortName == null) {
            this.initializeNames();

        }
        return projectShortName;
        // return time.getProject();
    }

    public String getFormattedProject() {
        if (this.getProject() != null)
            return this.getProject().toUpperCase();

        return null;
    }

    public String getNewProject() {

        return newProject;
    }

    /**
     * @return
     */
    public Date getStart() {
        return time.getStart();
    }

    public String getStartSimple() {
        return startSimple;
    }

    /**
     * @return
     */
    public Date getStop() {
        return time.getStop();
    }

    public String getStopSimple() {
        return stopSimple;
    }

    /**
     * @return
     */
    public String getTask() {
        return time.getTask();
    }

    public String getFormattedTask() {
        if (this.getTask() != null)
            return this.getTask().toUpperCase();

        return null;
    }

    public String getNewTask() {
        return newTask;
    }

    /**
     * @return
     */
    public String getUsername() {
        return time.getUsername();
    }

    public String getFormattedUsername() {
        if (this.getUsername() != null)
            return this.getUsername().toUpperCase();

        return null;
    }

    /**
     * @return
     */
    public boolean isReported() {
        return time.isReported();
    }

    /**
     * @param comments
     */
    public void setComments(String comments) {
        time.setComments(comments);
    }

    /**
     * @param date
     */
    public void setDate(String date) {
        SimpleDate sDate = SimpleDate.factory.build(date);
        time.setDate(sDate.getDate());

    }

    /**
     * @param elapsed
     */
    public void setElapsed(Double elapsed) {
        time.setElapsed(elapsed);
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        time.setId(id);
    }

    /**
     * @param project
     */
    public void setProject(String project) {
        if (project != null && project.length() > 0)
            time.setProject(project);
    }

    public void setNewProject(String project) {
        // time.setProject(project);

        newProject = project;
    }

    /**
     * @param reported
     */
    public void setReported(boolean reported) {
        time.setReported(reported);
    }

    /**
     * This is the one used by the DAO
     * 
     * @param start
     */
    public void setStart(Date start) {
        time.setStart(start);
    }

    public void setStartSimple(String s) {
        this.startSimple = s;
    }

    /**
     * @param stop
     */
    public void setStop(Date stop) {
        time.setStop(stop);
    }

    public void setStopSimple(String s) {

        this.stopSimple = s;
        // time.setStop(new ActivityResolver(time.getDate(),
        // this.stopSimple).resolve());
    }

    /**
     * @param task
     */
    public void setTask(String task) {
        if (task != null && task.length() > 0)
            time.setTask(task);
    }

    public void setNewTask(String task) {
        time.setTask(task);
        newTask = task;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        time.setUsername(username);
    }

    /**
     * @return
     */
    public Activity getActivity() {
        return time;
    }

    /**
     * @param time
     */
    public void setActivity(Activity time) {
        this.time = time;
    }

    /**
     * @return
     */
    public Long getProjectId() {
        return time.getProjectId();
    }

    /**
     * @param projectId
     */
    public void setProjectId(Long projectId) {
        time.setProjectId(projectId);
    }

    /**
     * @return
     */
    public Long getTaskId() {
        return time.getTaskId();
    }

    /**
     * @param taskId
     */
    public void setTaskId(Long taskId) {
        time.setTaskId(taskId);
    }

    /**
     * @return Returns the sessionContext.
     */
    public SessionContext getSessionContext() {
        return sessionContext;
    }

    /**
     * @param sessionContext
     *            The sessionContext to set.
     */
    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }
}