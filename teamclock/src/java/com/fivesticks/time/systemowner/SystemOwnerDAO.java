/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import java.util.List;

/**
 * @author Reid
 */
public interface SystemOwnerDAO {

    public SystemOwner save(SystemOwner target);

    public SystemOwner get(Long id);

    public void delete(SystemOwner target);

    public List search(SystemOwnerCriteriaParameters params);

}