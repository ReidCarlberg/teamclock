/*
 * Created on Apr 30, 2004
 *
 */
package com.fivesticks.time.account;

import java.util.List;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Nick
 *  
 */
public interface AccountTransactionDAO {

    public static final String SPRING_BEAN_NAME = "accountTransactionDao";

    public static final AccountTransactionDAOFactory factory = new AccountTransactionDAOFactory();

    public abstract AccountTransaction save(AccountTransaction u)
            throws DAOException;

    public abstract AccountTransaction get(Long id) throws DAOException;

    public abstract void delete(AccountTransaction u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    /**
     * @param filterVO
     * @return
     */
    public abstract List find(AccountTransactionCriteriaParameters filter)
            throws DAOException;
}