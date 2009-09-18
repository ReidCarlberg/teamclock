/*
 * Created on Oct 6, 2005
 *
 * 
 */
package com.fivesticks.time.customer.util;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.object.metrics.ObjectMetricNotFoundException;
import com.fivesticks.time.object.metrics.ObjectMetricServiceDelegateException;

public interface CustomerSettingServiceDelegate {

    public abstract CustomerSettingVO find(Customer customer)
            throws ObjectMetricServiceDelegateException,
            ObjectMetricNotFoundException;

    public abstract void save(Customer customer, CustomerSettingVO vo)
            throws ObjectMetricServiceDelegateException;

}