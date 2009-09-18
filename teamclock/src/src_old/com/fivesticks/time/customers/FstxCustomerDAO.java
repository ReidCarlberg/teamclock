/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customers;

import java.util.List;

import com.fivesticks.time.customer.FstxCustomer;
import com.fivesticks.time.customer.FstxCustomerFilterVO;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Nick
 *  
 */
public interface FstxCustomerDAO {

    public static final String SPRING_BEAN_NAME = "fstxCustomerDAO";

    public static final FstxCustomerDAOFactory factory = new FstxCustomerDAOFactory();

    public abstract FstxCustomer save(FstxCustomer u) throws DAOException;

    public abstract FstxCustomer get(Long id) throws DAOException;

    public abstract void delete(FstxCustomer u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    /**
     * @param filterVO
     * @return
     */
    public abstract List find(FstxCustomerFilterVO filter) throws DAOException;
}