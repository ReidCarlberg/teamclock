/*
 * Created on Dec 3, 2004
 *
 * 
 */
package com.fivesticks.time.payperiodproductivity.xwork;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Nick
 * 
 *  
 */
public class PayPeriodDayCollection {
    private Collection collection;

    private Date date;

    public PayPeriodDayCollection() {
        super();

    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            PayPeriodReportVO element = (PayPeriodReportVO) iter.next();
            if (this.getDate() == null) {
                this.setDate(element.getDate());
            } else {
                if (!(this.getDate().equals(element.getDate()))) {
                    throw new RuntimeException(
                            "PayPeriodDayCollection: Dates not all equal");
                }
            }
        }

        this.collection = collection;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}