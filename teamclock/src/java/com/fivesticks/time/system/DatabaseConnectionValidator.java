/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.system;

import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.common.SpringBeanBroker;

/**
 * @author Reid
 *  
 */
public class DatabaseConnectionValidator {

    private Log log = LogFactory.getLog(DatabaseConnectionValidator.class);

    public void validate() throws DatabaseConnectionValidatorException {

        log.debug("getting a data source...");
        
        BasicDataSource datasource = (BasicDataSource) SpringBeanBroker
                .getBeanFactory().getBean("myDataSource");

        log.debug("have a data source, now trying to get a connection");
        
        try {
            datasource.getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectionValidatorException(e);
        }

        log.debug("got the connection, returning.");
    }
}