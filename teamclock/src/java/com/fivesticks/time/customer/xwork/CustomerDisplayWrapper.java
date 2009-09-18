/*
 * Created on Dec 5, 2004 by REID
 */
package com.fivesticks.time.customer.xwork;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.lookups.Lookup;

/**
 * @author REID
 */
public class CustomerDisplayWrapper implements Serializable {

    private static final DecimalFormat format = new DecimalFormat("#,##0.00");

    private Customer fstxCustomer;

    private Double balance;

    private Lookup status;
    /**
     * @return
     */
    public String getCity() {
        return fstxCustomer.getCity();
    }

    /**
     * @return
     */
    public Long getId() {
        return fstxCustomer.getId();
    }

    /**
     * @return
     */
    public String getName() {
        return fstxCustomer.getName();
    }

    /**
     * @return
     */
    public String getOwnerKey() {
        return fstxCustomer.getOwnerKey();
    }

    /**
     * @return
     */
    public String getState() {
        return fstxCustomer.getState();
    }

    /**
     * @return
     */
    public String getStreet1() {
        return fstxCustomer.getStreet1();
    }

    /**
     * @return
     */
    public String getStreet2() {
        return fstxCustomer.getStreet2();
    }

    /**
     * @return
     */
    public String getZip() {
        return fstxCustomer.getZip();
    }

    public CustomerDisplayWrapper(Customer fstxCustomer) {
        this.fstxCustomer = fstxCustomer;
    }

    /**
     * @return Returns the fstxCustomer.
     */
    public Customer getFstxCustomer() {
        return fstxCustomer;
    }

    /**
     * @param fstxCustomer
     *            The fstxCustomer to set.
     */
    public void setFstxCustomer(Customer fstxCustomer) {
        this.fstxCustomer = fstxCustomer;
    }

    /**
     * @return Returns the balance.
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance
     *            The balance to set.
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getFormattedBalance() {
        return format.format(this.getBalance());
    }
    /**
     * @return Returns the status.
     */
    public Lookup getStatus() {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(Lookup status) {
        this.status = status;
    }
}