/*
 * Created on Oct 4, 2004
 *
 * 
 */
package com.fivesticks.time.todo.schedule;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;



/**
 * @author Nick
 * 
 *  
 */
public class ToDoScheduleCriteriaBuilder {

    public ToDoScheduleCriteriaBuilder() {
        super();

    }

    public Class getObjectClass() {

        return ToDoSchedule.class;
    }

    public Criteria buildCriteria(ToDoScheduleQueryParameters queryParameters,
            Criteria criteria) throws ToDoScheduleCriteriaBuilderException {

        if (!(queryParameters instanceof ToDoScheduleQueryParameters)) {

            throw new ToDoScheduleCriteriaBuilderException(
                    queryParameters.getClass()
                            + "is not an instance of WorkOrderScheduleQueryParameters:  cannot be used to search for workOrderSchedule.");
        }

        ToDoScheduleQueryParameters workOrderScheduleQueryParameters = (ToDoScheduleQueryParameters) queryParameters;

        //        if (workOrderScheduleQueryParameters.getOrgId() != null) {
        //            criteria.add(Expression.eq("wosOrgId",
        //                    workOrderScheduleQueryParameters.getOrgId()));
        //        }
        if (workOrderScheduleQueryParameters.getBeforeInitiationDate() != null) {

            criteria
                    .add(Expression.le("initiationDate",
                            workOrderScheduleQueryParameters
                                    .getBeforeInitiationDate()));
        }

        return criteria;
    }
}