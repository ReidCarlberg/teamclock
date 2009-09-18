/*
 * Created on Dec 29, 2004 by Reid
 */
package com.fivesticks.time.object.metrics;

import java.io.Serializable;

import com.fivesticks.time.common.AbstractObjectTypeAndIdAwareTimeObject;

/**
 * @author Reid
 */
public class ObjectMetric extends AbstractObjectTypeAndIdAwareTimeObject implements Serializable {

//    private Long id;
//
//    private String ownerKey;

//    private String objectType;
//
//    private Long objectId;

    private String metricType;

    private String metricValue;

//    /**
//     * @return Returns the id.
//     */
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * @param id
//     *            The id to set.
//     */
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    /**
//     * @return Returns the ownerKey.
//     */
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//
//    /**
//     * @param ownerKey
//     *            The ownerKey to set.
//     */
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }

    /**
     * @return Returns the username.
     */
    public String getMetricType() {
        return metricType;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    /**
     * @return Returns the metricValue.
     */
    public String getMetricValue() {
        return metricValue;
    }

    /**
     * @param metricValue
     *            The metricValue to set.
     */
    public void setMetricValue(String metricValue) {
        this.metricValue = metricValue;
    }

//    /**
//     * @return Returns the objectId.
//     */
//    public Long getObjectId() {
//        return objectId;
//    }
//
//    /**
//     * @param objectId
//     *            The objectId to set.
//     */
//    public void setObjectId(Long objectId) {
//        this.objectId = objectId;
//    }
//
//    /**
//     * @return Returns the objectType.
//     */
//    public String getObjectType() {
//        return objectType;
//    }
//
//    /**
//     * @param objectType
//     *            The objectType to set.
//     */
//    public void setObjectType(String objectType) {
//        this.objectType = objectType;
//    }
}