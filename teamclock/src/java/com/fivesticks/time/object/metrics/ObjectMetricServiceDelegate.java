/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.object.metrics;

import java.util.Collection;

import com.fivesticks.time.common.IdReadable;
import com.fivesticks.time.customer.util.CustomerSettingType;

/**
 * @author Reid
 */
public interface ObjectMetricServiceDelegate {

    public void save(ObjectMetric target) throws ObjectMetricServiceDelegateException;
    
    public Collection getMetrics() throws ObjectMetricServiceDelegateException;

    public Collection getMetrics(IdReadable idReadable)
            throws ObjectMetricServiceDelegateException;

    //    public ObjectMetric getMetric(
    //            Class clazz,
    //            Long id,
    //            ObjectMetricTypeEnum systemOwnerMetricTypeEnum)
    //            throws ObjectMetricServiceDelegateException,
    // ObjectMetricNotFoundException;

    public ObjectMetric getMetric(IdReadable idReadable,
            ObjectMetricTypeEnum systemOwnerMetricTypeEnum)
            throws ObjectMetricServiceDelegateException,
            ObjectMetricNotFoundException;

    //    public String getMetricValue(
    //            Class clazz,
    //            Long id,
    //            ObjectMetricTypeEnum systemOwnerMetricTypeEnum)
    //            throws ObjectMetricServiceDelegateException,
    // ObjectMetricNotFoundException;

    public String getMetricValue(IdReadable idReadable,
            ObjectMetricTypeEnum systemOwnerMetricTypeEnum)
            throws ObjectMetricServiceDelegateException,
            ObjectMetricNotFoundException;

    //    public ObjectMetric setValue(
    //            Class clazz,
    //            Long id,
    //            ObjectMetricTypeEnum systemOwnerMetricTypeEnum,
    //            String metricValue)
    //            throws ObjectMetricServiceDelegateException;

    public ObjectMetric setValue(IdReadable idReadable,
            ObjectMetricTypeEnum systemOwnerMetricTypeEnum, String metricValue)
            throws ObjectMetricServiceDelegateException;

    public void delete(ObjectMetric systemOwnerMetric)
            throws ObjectMetricServiceDelegateException;

    /**
     * @param class1
     * @param is_auction_customer
     * @param string
     * @return
     */
    public Collection getMatchingObjectsByMetricValue(Class class1,
            CustomerSettingType is_auction_customer, String string) throws ObjectMetricServiceDelegateException;

}