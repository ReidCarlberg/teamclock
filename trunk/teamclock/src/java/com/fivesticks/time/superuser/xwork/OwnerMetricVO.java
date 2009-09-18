/*
 * Created on Feb 8, 2005 by Reid
 */
package com.fivesticks.time.superuser.xwork;

import java.io.Serializable;

import com.fivesticks.time.object.metrics.ObjectMetric;
import com.fivesticks.time.systemowner.SystemOwner;

/**
 * @author Reid
 */
public class OwnerMetricVO implements Serializable {

    private SystemOwner owner;

    private ObjectMetric metric;

    /**
     * @return Returns the metric.
     */
    public ObjectMetric getMetric() {
        return metric;
    }

    /**
     * @param metric
     *            The metric to set.
     */
    public void setMetric(ObjectMetric metric) {
        this.metric = metric;
    }

    /**
     * @return Returns the owner.
     */
    public SystemOwner getOwner() {
        return owner;
    }

    /**
     * @param owner
     *            The owner to set.
     */
    public void setOwner(SystemOwner owner) {
        this.owner = owner;
    }
}