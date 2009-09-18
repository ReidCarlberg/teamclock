/*
 * Created on Aug 24, 2005
 *  
 */
package com.fivesticks.time.todo;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.util.StringUtils;

import com.fivesticks.time.common.ParamUtility;
import com.fivesticks.time.common.ParamValidator;
import com.fivesticks.time.common.dao.AbstractCriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class ToDoCriteriaDecorator extends AbstractCriteriaDecorator {

    public Class getObjectClass() {

        return ToDo.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {

        if (!(parameters instanceof ToDoCriteriaParameters)) {
            throw new CriteriaBuilderException(
                    "parameters are not ToDoCriteriaParameters");
        }

        ToDoCriteriaParameters params = (ToDoCriteriaParameters) parameters;

        this.decorateCommon(criteria, params);

        // project id
        if (ParamValidator.isSearchable(params.getProjectId())) {
            criteria.add(Expression.eq("projectId", params.getProjectId()));
        } else if (params.getProjectIdIn() != null
                && params.getProjectIdIn().size() > 0) {
            criteria.add(Expression.in("projectId", params.getProjectIdIn()));
        }

        // create date
        new ParamUtility().buildDate(criteria, "createTimestamp", params
                .getCreateTimestamp(), params.getCreateTimestampRangeStart(),
                params.getCreateTimestampRangeStop());

        // deadline date
        new ParamUtility().buildDate(criteria, "deadlineTimestamp", params
                .getDeadlineTimestamp(), params
                .getDeadlineTimestampRangeStart(), params
                .getDeadlineTimestampRangeStop());

        // priority
        if (ParamValidator.isSearchable(params.getPriority())) {
            criteria.add(Expression.eq("priority", params.getPriority()));
        } else if (params.getUnprioritized() != null
                && params.getUnprioritized().booleanValue()) {
            criteria.add(Expression.isNull("priority"));
        }

        // detail
        if (ParamValidator.isSearchable(params.getDetail())) {
            criteria.add(Expression.eq("detail", params.getDetail()));
        } else if (ParamValidator.isSearchable(params.getDetailLike())) {
            criteria.add(Expression.like("detail", "%" + params.getDetailLike()
                    + "%"));
        }

        // complete
        if (ParamValidator.isSearchable(params.getTodoComplete())) {
            criteria.add(Expression.eq("complete", new Boolean(params
                    .getTodoComplete())));
        }

        // status
        decorateForParam(criteria, "status", params.getStatus());
        
        // external user
        decorateForParam(criteria, "externalUser", params.getExternalUser());

        // enteredby
        decorateForParam(criteria, "enteredByUser", params.getEnteredByUser());

        // assignedto
        decorateForParam(criteria, "assignedToUser", params.getAssignedToUser());

        // assignedto on team
        this.decorateForTeam(params.getOwnerKey(), criteria, "assignedToUser",
                params.getAssignedToTeamName());

        // NOT assignedto
        if (ParamValidator.isSearchable(params.getNotAssignedTo())) {
            criteria.add(Expression.not(Expression.eq("assignedToUser", params
                    .getNotAssignedTo())));
        }
        
        
        // related objects
       decorateForParam(criteria, "linkedObjectType", params.getLinkedObjectType());
       decorateForParam(criteria, "linkedObjectId", params.getLinkedObjectId());
        
        /*
         * RSC 2005-09-23
         */
        /*
         * NIck 2005-09-27 since no number is both less then 1 and greater than
         * 250 I'm assuming we want to this to be and.
         * 
         * If the number is not between 1 and 250 force it to 25.
         */
        if (params.getReturnMaximum() < 1 || params.getReturnMaximum() > 250) {
            // if (params.getReturnMaximum() < 1 && params.getReturnMaximum() >
            // 250) {
            criteria.setMaxResults(25);
        } else {
            criteria.setMaxResults(params.getReturnMaximum());
        }

        if (StringUtils.hasText(params.getOrderBy())) {
            if (params.getSortDirection() != null
                    && !params.getSortDirection().booleanValue()) {
                criteria.addOrder(Order.desc(params.getOrderBy()));
            } else {
                criteria.addOrder(Order.asc(params.getOrderBy()));
            }
        } else {
            /*
             * 2006-07-07 in the absense of any other order by, sort 
             * most recent first.
             */
            criteria.addOrder(Order.desc("createTimestamp"));
        }

        /*
         * 2005-01-12 Reid
         * 2006-07-07 reid removed.
         */
//        if (params.getOrderBySequenceAsc() != null
//                && params.getOrderBySequenceAsc().booleanValue()) {
//            criteria.addOrder(Order.asc("sequence"));
//        } else if (params.getOrderBySequenceDesc() != null
//                && params.getOrderBySequenceDesc().booleanValue()) {
//            criteria.addOrder(Order.desc("sequence"));
//        } else {
//            // criteria.addOrder(Order.asc("deadlineTimestamp"));
//            criteria.addOrder(Order.asc("priority"));
//            criteria.addOrder(Order.asc("sequence"));
//        }

    }

}