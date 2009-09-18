/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.util.List;

/**
 * @author Reid
 */
public interface SystemUserDAO {

    public SystemUser save(SystemUser target);

    public SystemUser get(Long id);

    public void delete(SystemUser target);

    public List search(SystemUserSearchParameters params);

}