/*
 * Created on Nov 4, 2004 by Reid
 */
package com.fivesticks.time.account;

import java.io.Serializable;
import java.util.Date;

import com.fivesticks.time.common.AbstractObjectTypeAndIdAwareTimeObject;

/**
 * @author Reid
 */
public class AccountTransaction extends AbstractObjectTypeAndIdAwareTimeObject implements  Serializable {

    private static final long serialVersionUID = -4040211884138003762L;

//    private Long id;
//
//    private String ownerKey;

    private String objectTransactionType;
    
    private String objectTransactionCode;
    
    private Date date;

    private Long customerId;

    private String type;

    private Double amount;

    private String enteredBy;

    private String comments;

    private Long activityId;

    private boolean archived;
    
    //2006-06-01 Used to track balance progress.
    private Double decoratedBalance;

    /**
     * @return Returns the amount.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            The amount to set.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return Returns the archived.
     */
    public boolean isArchived() {
        
        
        return archived;
    }

    /**
     * @param archived
     *            The archived to set.
     */
    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return Returns the customerId.
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     *            The customerId to set.
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * @return Returns the date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            The date to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return Returns the enteredBy.
     */
    public String getEnteredBy() {
        return enteredBy;
    }

    /**
     * @param enteredBy
     *            The enteredBy to set.
     */
    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

//    /**
//     * @return Returns the id.
//     */
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * @param id
//     *            The id to set.
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    /**
//     * @return Returns the ownerKeyl.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownerKeyl
//     *            The ownerKeyl to set.
//     */
//    public void setOwnerKey(String ownerKeyl) {
//        this.ownerKey = ownerKeyl;
//    }

    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return Returns the activityId.
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * @param activityId
     *            The activityId to set.
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
    /**
     * @return Returns the objectTransactionType.
     */
    public String getObjectTransactionType() {
        return objectTransactionType;
    }
    /**
     * @param objectTransactionType The objectTransactionType to set.
     */
    public void setObjectTransactionType(String objectTransactionType) {
        this.objectTransactionType = objectTransactionType;
    }
    /**
     * @return Returns the objectTransactionCode.
     */
    public String getObjectTransactionCode() {
        return objectTransactionCode;
    }
    /**
     * @param objectTransactionCode The objectTransactionCode to set.
     */
    public void setObjectTransactionCode(String objectTransactionCode) {
        this.objectTransactionCode = objectTransactionCode;
    }

    /**
     * @return Returns the decoratedBalance.
     */
    public Double getDecoratedBalance() {
        return decoratedBalance;
    }

    /**
     * @param decoratedBalance The decoratedBalance to set.
     */
    public void setDecoratedBalance(Double decoratedBalance) {
        this.decoratedBalance = decoratedBalance;
    }
}