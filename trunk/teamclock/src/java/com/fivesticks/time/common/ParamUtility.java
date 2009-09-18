/*
 * Created on Jun 15, 2004
 *
 */
package com.fivesticks.time.common;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author REID
 *  
 */
public class ParamUtility {

    public static String getSearchable(String s) {
        if (s == null || s.trim().length() == 0) {
            return null;
        }
        return s.trim();
    }
    
    
    public void buildDate(Criteria criteria, String property, Date exactDate,
            Date dateRangeStart, Date dateRangeStop) {

        if (property == null || criteria == null)
            throw new RuntimeException("property or criteria is null");

        if (exactDate != null) {
            SimpleDate start = SimpleDate.factory.buildMidnight(exactDate);
            SimpleDate stop = SimpleDate.factory.buildMidnight(exactDate);
            stop.advanceDay();
            stop.advanceSecond(-1);
            buildDate(criteria, property, null, start.getDate(), stop.getDate());

        } else if (dateRangeStart != null && dateRangeStop != null) {
            criteria.add(Expression.between(property, dateRangeStart,
                    dateRangeStop));
        } else if (dateRangeStart != null) {
            criteria.add(Expression.ge(property, dateRangeStart));
        } else if (dateRangeStop != null) {
            criteria.add(Expression.le(property, dateRangeStop));
        }

    }

    public void buildStringEqualOrLike(Criteria criteria, String property,
            String exact, String like) {
        if (ParamValidator.isSearchable(exact)) {
            criteria.add(Expression.eq(property, exact));
        } else if (ParamValidator.isSearchable(like)) {
            criteria.add(Expression.like(property, "%" + like + "%"));
        }
    }
}