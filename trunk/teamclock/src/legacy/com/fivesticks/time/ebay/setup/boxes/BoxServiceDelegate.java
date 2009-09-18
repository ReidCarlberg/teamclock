/*
 * Created on May 4, 2005 by Reid
 */
package com.fivesticks.time.ebay.setup.boxes;

import java.util.Collection;

/**
 * @author Reid
 */
public interface BoxServiceDelegate {

    public static final String SPRING_BEAN_NAME = "ebaySetupBoxServiceDelegate";

    public static final BoxServiceDelegateFactory factory = new BoxServiceDelegateFactory();

    public Collection findLarger(Integer length, Integer width, Integer height)
            throws BoxServiceDelegateException;

    public Collection findLargerInstock(Integer length, Integer width,
            Integer height) throws BoxServiceDelegateException;

    public Collection find(BoxCriteriaParameters filter) throws BoxServiceDelegateException;
    
    public Box getBox(Long id) throws BoxServiceDelegateException;
    
    public void delete(Long id) throws BoxServiceDelegateException;
    
    public void delete(Box box) throws BoxServiceDelegateException;
    
    public void save(Box box) throws BoxServiceDelegateException;

}