/*
 * Created on Nov 18, 2004 by Reid
 */
package com.fivesticks.time.account.xwork;

import com.fivesticks.time.account.ObjectTransactionType;
import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

/**
 * @author Reid
 */
public class AccountTransactionContext {

//    private FstxCustomer fstxCustomer;
    
    private SystemOwnerKeyAware systemOwnerKeyAware;
    
    private ObjectTransactionType objectTransactionType;
    
    private String label;
    
    private String backAction;

    /**
     * @return Returns the objectTransactionType.
     */
    public ObjectTransactionType getObjectTransactionType() {
        return objectTransactionType;
    }
    /**
     * @param objectTransactionType The objectTransactionType to set.
     */
    public void setObjectTransactionType(
            ObjectTransactionType objectTransactionType) {
        this.objectTransactionType = objectTransactionType;
    }
    /**
     * @return Returns the systemOwnerKeyAware.
     */
    public SystemOwnerKeyAware getSystemOwnerKeyAware() {
        return systemOwnerKeyAware;
    }
    /**
     * @param systemOwnerKeyAware The systemOwnerKeyAware to set.
     */
    public void setSystemOwnerKeyAware(SystemOwnerKeyAware systemOwnerKeyAware) {
        this.systemOwnerKeyAware = systemOwnerKeyAware;
    }
//    /**
//     * @return Returns the fstxCustomer.
//     */
//    public FstxCustomer getFstxCustomer() {
//        return fstxCustomer;
//    }
//
//    /**
//     * @param fstxCustomer
//     *            The fstxCustomer to set.
//     */
//    public void setFstxCustomer(FstxCustomer fstxCustomer) {
//        this.fstxCustomer = fstxCustomer;
//    }
    /**
     * @return Returns the backAction.
     */
    public String getBackAction() {
        return backAction;
    }
    /**
     * @param backAction The backAction to set.
     */
    public void setBackAction(String backAction) {
        this.backAction = backAction;
    }
    /**
     * @return Returns the label.
     */
    public String getLabel() {
        return label;
    }
    /**
     * @param label The label to set.
     */
    public void setLabel(String label) {
        this.label = label;
    }
}