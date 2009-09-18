/*
 * Created on Jun 30, 2005 by Reid
 */
package com.fivesticks.time.account;

import java.util.Collection;

import com.fivesticks.time.systemowner.SystemOwnerKeyAware;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public interface AbstractAccountTransactionServiceDelegateIF {

    public AccountTransaction save(AccountTransaction prepayTransaction)
            throws AccountTransactionException;

    public AccountTransaction get(Long id) throws AccountTransactionException;

    public Collection find(SystemOwnerKeyAware systemOwnerKeyAware)
            throws AccountTransactionException;

    public Collection findAll(AccountTransactionCriteriaParameters filter)
            throws AccountTransactionException;

    public void delete(AccountTransaction prepayTransaction)
            throws AccountTransactionException;

    public void archiveThroughDate(SystemOwnerKeyAware systemOwnerKeyAware,
            SimpleDate dateRangeStop, String username)
            throws AccountTransactionException;

    public Collection findUnarchived(SystemOwnerKeyAware systemOwnerKeyAware)
            throws AccountTransactionException;

    public AccountTransaction save(AccountTransaction prepayTransaction,
            SystemOwnerKeyAware systemOwnerKeyAware)
            throws AccountTransactionException;

    public AccountTransaction increase(SystemOwnerKeyAware customer, Double amount,
            String comments, String username, String code)
            throws AccountTransactionException;

    public AccountTransaction increase(SystemOwnerKeyAware customer, Double amount,
            String comments, String username)
            throws AccountTransactionException;

    public AccountTransaction adjust(SystemOwnerKeyAware customer, Double amount,
            String comments, String username, String code)
            throws AccountTransactionException;

    public AccountTransaction adjust(SystemOwnerKeyAware customer, Double amount,
            String comments, String username)
            throws AccountTransactionException;

    public Double getCurrentBalance(SystemOwnerKeyAware customer)
            throws AccountTransactionException;


    public AccountTransaction decrease(SystemOwnerKeyAware customer, Double amount,
            String comments, String username)
            throws AccountTransactionException;

    public AccountTransaction decrease(SystemOwnerKeyAware customer, Double amount,
            String comments, String username, Long activityId)
            throws AccountTransactionException;
    
     
}