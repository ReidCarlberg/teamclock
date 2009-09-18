/*
 * Created on Dec 3, 2004
 *
 * 
 */
package com.fivesticks.time.payperiodproductivity.xwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import com.opensymphony.webwork.util.Sorter;

/**
 * @author Nick
 * 
 *  
 */
public class PayPeriodProductivityReport {

    Map days = new HashMap();

    public void addPayPeriodReportVO(PayPeriodReportVO vo) {

        if (days.containsKey(vo.getDate())) {
            Collection dayUsers = (Collection) days.get(vo.getDate());
            dayUsers.add(vo);
        } else {

            Collection dayUsers = new ArrayList();
            dayUsers.add(vo);

            days.put(vo.getDate(), dayUsers);

        }

    }

    public Collection getDays() {
        Collection col = new TreeSet(new Sorter().getAscending("date"));
        for (Iterator iter = days.values().iterator(); iter.hasNext();) {
            Collection element = (Collection) iter.next();
            PayPeriodDayCollection dayCol = new PayPeriodDayCollection();
            dayCol.setCollection(element);
            col.add(dayCol);
        }
        return col;
    }

}