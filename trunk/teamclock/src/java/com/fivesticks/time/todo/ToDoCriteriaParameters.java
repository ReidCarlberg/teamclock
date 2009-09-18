/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.todo;

import java.util.Collection;
import java.util.Date;

import com.fivesticks.time.common.dao.CriteriaParameters;

/**
 * @author REID
 * 
 */
public class ToDoCriteriaParameters extends ToDo implements CriteriaParameters {

    private Date createTimestampRangeStart;

    private Date createTimestampRangeStop;

    private Date deadlineTimestampRangeStart;

    private Date deadlineTimestampRangeStop;

    private String todoComplete;

    private String detailLike;

    private String notAssignedTo;

//    private Boolean orderBySequenceAsc;
//
//    private Boolean orderBySequenceDesc;

    private Boolean unprioritized;

    private Collection projectIdIn;

    private String assignedToTeamName;

    private String orderBy;

    private Boolean sortDirection = Boolean.TRUE;// ASC=True, DESC=False;

    private Boolean tagIsNotNull;
    
    private int returnMaximum;

//    /**
//     * @return Returns the orderBySequenceDesc.
//     */
//    public Boolean getOrderBySequenceDesc() {
//        return orderBySequenceDesc;
//    }
//
//    /**
//     * @param orderBySequenceDesc
//     *            The orderBySequenceDesc to set.
//     */
//    public void setOrderBySequenceDesc(Boolean orderBySequenceDesc) {
//        this.orderBySequenceDesc = orderBySequenceDesc;
//    }

//    /**
//     * @return Returns the orderBySequence.
//     */
//    public Boolean getOrderBySequenceAsc() {
//        return orderBySequenceAsc;
//    }
//
//    /**
//     * @param orderBySequence
//     *            The orderBySequence to set.
//     */
//    public void setOrderBySequenceAsc(Boolean orderBySequence) {
//        this.orderBySequenceAsc = orderBySequence;
//    }

    /**
     * @return
     */
    public Date getCreateTimestampRangeStart() {
        return createTimestampRangeStart;
    }

    /**
     * @return
     */
    public Date getCreateTimestampRangeStop() {
        return createTimestampRangeStop;
    }

    /**
     * @return
     */
    public Date getDeadlineTimestampRangeStart() {
        return deadlineTimestampRangeStart;
    }

    /**
     * @return
     */
    public Date getDeadlineTimestampRangeStop() {
        return deadlineTimestampRangeStop;
    }

    /**
     * @param date
     */
    public void setCreateTimestampRangeStart(Date date) {
        createTimestampRangeStart = date;
    }

    /**
     * @param date
     */
    public void setCreateTimestampRangeStop(Date date) {
        createTimestampRangeStop = date;
    }

    /**
     * @param date
     */
    public void setDeadlineTimestampRangeStart(Date date) {
        deadlineTimestampRangeStart = date;
    }

    /**
     * @param date
     */
    public void setDeadlineTimestampRangeStop(Date date) {
        deadlineTimestampRangeStop = date;
    }

    /**
     * @return
     */
    public String getDetailLike() {
        return detailLike;
    }

    /**
     * @param string
     */
    public void setDetailLike(String string) {
        detailLike = string;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDo#isComplete()
     */
    public boolean isComplete() {
        throw new RuntimeException("not in the filter");
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.fivesticks.time.todo.ToDo#setComplete(boolean)
     */
    public void setComplete(boolean b) {
        throw new RuntimeException("not in the filter");
    }

    /**
     * @return
     */
    public String getTodoComplete() {
        return todoComplete;
    }

    /**
     * @param boolean1
     */
    public void setTodoComplete(String boolean1) {
        todoComplete = boolean1;
    }

    /**
     * @return Returns the notAssignedTo.
     */
    public String getNotAssignedTo() {
        return notAssignedTo;
    }

    /**
     * @param notAssignedTo
     *            The notAssignedTo to set.
     */
    public void setNotAssignedTo(String notAssignedTo) {
        this.notAssignedTo = notAssignedTo;
    }

    /**
     * @return Returns the projectIn.
     */
    public Collection getProjectIdIn() {
        return projectIdIn;
    }

    /**
     * @param projectIn
     *            The projectIn to set.
     */
    public void setProjectIdIn(Collection projectIn) {
        this.projectIdIn = projectIn;
    }

    /**
     * @return Returns the unprioritized.
     */
    public Boolean getUnprioritized() {
        return unprioritized;
    }

    /**
     * @param unprioritized
     *            The unprioritized to set.
     */
    public void setUnprioritized(Boolean unprioritized) {
        this.unprioritized = unprioritized;
    }

    /**
     * @return Returns the assignedToTeamName.
     */
    public String getAssignedToTeamName() {
        return assignedToTeamName;
    }

    /**
     * @param assignedToTeamName
     *            The assignedToTeamName to set.
     */
    public void setAssignedToTeamName(String assignedToTeamName) {
        this.assignedToTeamName = assignedToTeamName;
    }

    /**
     * @return Returns the returnMaximum.
     */
    public int getReturnMaximum() {
        return returnMaximum;
    }

    /**
     * @param returnMaximum
     *            The returnMaximum to set.
     */
    public void setReturnMaximum(int returnMaximum) {
        this.returnMaximum = returnMaximum;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(Boolean sortDirection) {
        this.sortDirection = sortDirection;
    }

    /*
     * We need to be able get the next direction so we know what to pass back.
     */
    public String getInverseSortDirection() {
        return new Boolean(!sortDirection.booleanValue()).toString();

    }

    public Object clone() {
        ToDoCriteriaParameters clone = new ToDoCriteriaParameters();
        clone.setAssignedToUser(this.getAssignedToUser());
        clone.setDeadlineTimestamp(this.getDeadlineTimestamp());
        clone.setDetail(this.getDetail());
        clone.setEnteredByUser(this.getEnteredByUser());
        clone.setExternalUser(this.getExternalUser());
        clone.setOwnerKey(this.getOwnerKey());
        clone.setPriority(this.getPriority());
        clone.setProjectId(this.getProjectId());
        clone.setSequence(this.getSequence());
        clone.setStatus(this.getStatus());
//        clone.setTag(this.getTag());
        clone.setCreateTimestamp(new Date());
        //clone.setComplete(false);

        clone.setCreateTimestampRangeStart(createTimestampRangeStart);
        clone.setCreateTimestampRangeStop(createTimestampRangeStop);
        clone.setDeadlineTimestampRangeStart(deadlineTimestampRangeStart);
        clone.setDeadlineTimestampRangeStop(deadlineTimestampRangeStop);
        clone.setTodoComplete(todoComplete);
        clone.setDetailLike(detailLike);
        clone.setNotAssignedTo(notAssignedTo);
//        clone.setOrderBySequenceAsc(orderBySequenceAsc);
//        clone.setOrderBySequenceDesc(orderBySequenceDesc);
        clone.setUnprioritized(unprioritized);
        clone.setProjectIdIn(projectIdIn);

        clone.setAssignedToTeamName(assignedToTeamName);

        clone.setOrderBy(orderBy);

        clone.setSortDirection(sortDirection);

        clone.setReturnMaximum(returnMaximum);

        return clone;

    }

    /**
     * @return Returns the tagIsNotNull.
     */
    public Boolean getTagIsNotNull() {
        return tagIsNotNull;
    }

    /**
     * @param tagIsNotNull The tagIsNotNull to set.
     */
    public void setTagIsNotNull(Boolean tagIsNotNull) {
        this.tagIsNotNull = tagIsNotNull;
    }
}