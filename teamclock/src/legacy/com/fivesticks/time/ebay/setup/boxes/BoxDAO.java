/*
 * Created on May 4, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import java.util.Collection;

/**
 * @author Reid
 */
public interface BoxDAO {
    
    public static final String SPRING_BEAN_NAME = "ebaySetupBoxDao";
    
    public void save(Box target);

    public Box get(Long id);

    public void delete(Box target);

    public Collection find(final BoxCriteriaParameters params);
}