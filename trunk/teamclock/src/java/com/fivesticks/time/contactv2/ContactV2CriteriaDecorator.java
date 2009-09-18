/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.contactv2;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.fivesticks.time.common.dao.AbstractCriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class ContactV2CriteriaDecorator extends AbstractCriteriaDecorator {

    private Log log = LogFactory.getLog(ContactV2CriteriaDecorator.class);
    
    public Class getObjectClass() {

        return ContactV2.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {
        
        if (!(parameters instanceof ContactV2CriteriaParameters)) {
            throw new CriteriaBuilderException("parameters are not ContactV2CriteriaParameters");
        }
        
        ContactV2CriteriaParameters params = (ContactV2CriteriaParameters) parameters;        

        
        
        super.decorateCommon(criteria,params);
        
        this.decorateForParam(criteria, "nameFirst", params.getNameFirst());
        this.decorateForLike(criteria, "nameFirst", params.getNameFirstLike());
        this.decorateForParam(criteria, "nameLast", params.getNameLast());
        this.decorateForLike(criteria, "nameLast", params.getNameLastLike());
        
        this.decorateForLike(criteria, "nameFormatted", params.getNameFormattedLike());
        this.decorateForLike(criteria, "organizationName", params.getOrganizationNameLike());

        this.decorateForLike(criteria, "organizationCity", params.getOrganizationCityLike());

        this.decorateRange(criteria, "createDate", params.getCreateDateRangeStart(), params.getCreateDateRangeStop());
        this.decorateRange(criteria, "modifyDate", params.getModifyDateRangeStart(), params.getModifyDateRangeStop());
        
        if (params.getContactInterestLuId() != null) {
            criteria.createCriteria("interests").add(Restrictions.eq("id", params.getContactInterestLuId()));
        }
        
        if (params.getContactClassLuId() != null) {
            criteria.createCriteria("classes").add(Restrictions.eq("id", params.getContactClassLuId()));
        }
        
        if (params.getContactSourceLuId() != null) {
            criteria.createCriteria("sources").add(Restrictions.eq("id", params.getContactSourceLuId()));
        }        
        
        criteria.addOrder(Order.asc("nameLast"));
        criteria.addOrder(Order.asc("nameFirst"));
        
        
    }

}
