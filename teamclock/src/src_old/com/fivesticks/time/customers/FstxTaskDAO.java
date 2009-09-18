/*
 * Created on Apr 28, 2004
 *
 */
package com.fivesticks.time.customers;

import java.util.List;

import com.fivesticks.time.customer.FstxTask;
import com.fivesticks.time.customer.FstxTaskFilterParams;
import com.fstx.stdlib.common.DAOException;

/**
 * @author Nick
 *  
 */
public interface FstxTaskDAO {

    public static final String SPRING_BEAN_NAME = "fstxTaskDAO";

    public static final String SELECT_ALL = "from com.fivesticks.time.customer.FstxTask task where task.id > 0 order by task.id";

    public static final String SELECT_BY_NAME = "from com.fivesticks.time.customer.FstxTask task where task.nameShort = ?";

    public static final FstxTaskDAOFactory factory = new FstxTaskDAOFactory();

    public abstract FstxTask save(FstxTask u) throws DAOException;

    public abstract FstxTask get(Long id) throws DAOException;

    public abstract void delete(FstxTask u) throws DAOException;

    public abstract void delete(Long id) throws DAOException;

    public abstract List searchFstxTasks(String query) throws DAOException;

    public abstract List searchFstxTasks(String query, String value)
            throws DAOException;

    /**
     * @param filterVO
     * @return
     */
    public abstract List find(FstxTaskFilterParams filter) throws DAOException;
}