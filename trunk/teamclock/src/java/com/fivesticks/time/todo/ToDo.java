/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.todo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.fivesticks.time.common.AbstractTimeObject;

/**
 * @author REID
 * 
 */
public class ToDo extends AbstractTimeObject implements Serializable {

//    private Log log = LogFactory.getLog(ToDo.class);

//    private Long id;
//
//    private String ownerKey;

    private Long projectId;

    private Date createTimestamp;

    private Date deadlineTimestamp;

    private String priority;

    private String detail;

    private boolean complete;

    private String externalUser;

    private String enteredByUser;

    private String assignedToUser;

    private Integer sequence;

    private String status;
    
    private Double estimatedTotalHours;
    
    private Double estimatedRemainingHours;
    
    private Double totalElapsedHours;
    
    private Double totalChargeableElapsedHours;
    
    private Date modifyDate;
    
    private String modifiedByUser;

    private String linkedObjectType;
    
    private Long linkedObjectId;
    
    /*
     * 2005-10-4 Tag allow todos to grouped independent of project, ect. A todo
     * is only allows a single tag.
     */
//    private String tag;

    private Collection schedules = new HashSet();

    /**
     * @return
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @return
     */
    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * @return
     */
    public Date getDeadlineTimestamp() {
        return deadlineTimestamp;
    }

    /**
     * @return
     */
    public String getDetail() {
        return detail;
    }

//    /**
//     * @return
//     */
//    public Long getId() {
//        return id;
//    }

    /**
     * @return
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @return
     */
    public Long getProjectId() {
        return projectId;
    }

    /**
     * @param b
     */
    public void setComplete(boolean b) {
        complete = b;
    }

    /**
     * @param date
     */
    public void setCreateTimestamp(Date date) {
        createTimestamp = date;
    }

    /**
     * @param date
     */
    public void setDeadlineTimestamp(Date date) {
        deadlineTimestamp = date;
    }

    /**
     * @param string
     */
    public void setDetail(String string) {
        detail = string;
    }

//    /**
//     * @param long1
//     */
//    public void setId(Long long1) {
//        id = long1;
//    }

    /**
     * @param string
     */
    public void setPriority(String string) {
        priority = string;
    }

    /**
     * @param long1
     */
    public void setProjectId(Long long1) {
        projectId = long1;
    }

    /**
     * @return
     */
    public String getAssignedToUser() {
        return assignedToUser;
    }

    /**
     * @return
     */
    public String getEnteredByUser() {
        return enteredByUser;
    }

    /**
     * @param string
     */
    public void setAssignedToUser(String string) {
        assignedToUser = string;
    }

    /**
     * @param string
     */
    public void setEnteredByUser(String string) {
        enteredByUser = string;
    }

//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownerKey
//     *            The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }

    /**
     * @return Returns the sequence.
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * @param sequence
     *            The sequence to set.
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * @return Returns the externalUser.
     */
    public String getExternalUser() {
        return externalUser;
    }

    /**
     * @param externalUser
     *            The externalUser to set.
     */
    public void setExternalUser(String externalUser) {
        this.externalUser = externalUser;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Object clone() {
        ToDo clone = new ToDo();
        clone.setAssignedToUser(this.assignedToUser);
        clone.setDeadlineTimestamp(this.deadlineTimestamp);
        clone.setDetail(this.detail);
        clone.setEnteredByUser(this.enteredByUser);
        clone.setExternalUser(this.externalUser);
        clone.setOwnerKey(this.getOwnerKey());
        clone.setPriority(this.priority);
        clone.setProjectId(this.projectId);
        clone.setSequence(this.sequence);
        clone.setStatus(this.status);
//        clone.setTag(this.tag);
        clone.setCreateTimestamp(new Date());
        // clone.setComplete(false);
        return clone;

    }

    public Collection getSchedules() {
        return schedules;
    }

    public void setSchedules(Collection schedules) {
        this.schedules = schedules;
    }

    /*
     * 2006-06-28 reid getting rid of tags altogether
     */
//    public String getTag() {
//        return tag;
//    }

//    public String getTagFriendly() {
////        StringBuffer buffer = new StringBuffer();
////
////        for (Iterator iter = this.getTags().iterator(); iter.hasNext();) {
////            String element = (String) iter.next();
////            if (buffer.length() > 0) {
////                buffer.append(" ");
////
////            }
////            buffer.append(element);
////        }
////
////        return buffer.toString();
//        return tag;
//    }
//
//    public void setTag(String tag) {
//        //this.tag = this.validateTag(tag);
//        this.tag = tag;
//
//    }

    /**
     * @return Returns the estimatedRemainingHours.
     */
    public Double getEstimatedRemainingHours() {
        return estimatedRemainingHours;
    }

    /**
     * @param estimatedRemainingHours The estimatedRemainingHours to set.
     */
    public void setEstimatedRemainingHours(Double estimatedRemainingHours) {
        this.estimatedRemainingHours = estimatedRemainingHours;
    }

    /**
     * @return Returns the estimatedTotalHours.
     */
    public Double getEstimatedTotalHours() {
        return estimatedTotalHours;
    }

    /**
     * @param estimatedTotalHours The estimatedTotalHours to set.
     */
    public void setEstimatedTotalHours(Double estimatedTotalHours) {
        this.estimatedTotalHours = estimatedTotalHours;
    }



    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Double getTotalChargeableElapsedHours() {
        return totalChargeableElapsedHours;
    }

    public void setTotalChargeableElapsedHours(Double totalChargeableElapsed) {
        this.totalChargeableElapsedHours = totalChargeableElapsed;
    }

    public Double getTotalElapsedHours() {
        return totalElapsedHours;
    }

    public void setTotalElapsedHours(Double totalElapsed) {
        this.totalElapsedHours = totalElapsed;
    }

    /**
     * @return the linkedObjectId
     */
    public Long getLinkedObjectId() {
        return linkedObjectId;
    }

    /**
     * @param linkedObjectId the linkedObjectId to set
     */
    public void setLinkedObjectId(Long linkedObjectId) {
        this.linkedObjectId = linkedObjectId;
    }

    /**
     * @return the linkedObjectType
     */
    public String getLinkedObjectType() {
        return linkedObjectType;
    }

    /**
     * @param linkedObjectType the linkedObjectType to set
     */
    public void setLinkedObjectType(String linkedObjectType) {
        this.linkedObjectType = linkedObjectType;
    }

//    /*
//     * Nick 2005-10-4 Tag must be in the form ---Tag1---Tag1---
//     * 2006-05-17 reid - no - not doing that.
//     */
//    private String validateTag(String rawString) {
//
//        Collection tags = this.parseTags(rawString);
//        StringBuffer updatedTag = new StringBuffer();
//
//        if (tags.size() == 0) {
//            return "";
//
//        }
////        updatedTag.append("---");
//
//        for (Iterator iter = tags.iterator(); iter.hasNext();) {
//            String tag = (String) iter.next();
//            if (tag.length() > 0) {
//                updatedTag.append(tag).append(" ");
//            }
//        }
//
//        return updatedTag.toString();
//
//    }

//    public Collection getTags() {
//        return this.parseTags(this.getTag());
//    }
//
//    public Collection parseTags(String tags) {
//        String[] tok = tags != null ? tags.split(" ") : new String[0];
//        Collection ret = new ArrayList();
//        for (int i = 0; i < tok.length; i++) {
//            String string = tok[i];
//            if (string.length() > 0) {
//                ret.add(string);
//            }
//        }
//
//        return ret;
//
//    }
}
