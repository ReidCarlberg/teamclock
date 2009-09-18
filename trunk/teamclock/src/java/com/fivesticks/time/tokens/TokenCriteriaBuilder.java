/*
 * Created on Aug 13, 2005
 *
 */
package com.fivesticks.time.tokens;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;

import com.fivesticks.time.common.dao.AbstractCriteriaDecorator;
import com.fivesticks.time.common.dao.CriteriaBuilderException;
import com.fivesticks.time.common.dao.CriteriaParameters;

public class TokenCriteriaBuilder extends AbstractCriteriaDecorator {

    private Log log = LogFactory.getLog(TokenCriteriaBuilder.class);

    public Class getObjectClass() {

        return Token.class;
    }

    public void decorateCriteria(Criteria criteria,
            CriteriaParameters parameters) throws CriteriaBuilderException {

        if (!(parameters instanceof TokenCriteriaParameters)) {
            throw new CriteriaBuilderException(
                    "parameters are not TokenCriteriaParameters");
        }

        TokenCriteriaParameters params = (TokenCriteriaParameters) parameters;

        this.decorateCommon(criteria, params);

        // id
        this.decorateForParam(criteria, "id", params.getId());

        // type
        this.decorateForParam(criteria, "type", params.getType());

        // ownerKey
        this.decorateForParam(criteria, "ownerKey", params.getOwnerKey());

        // username
        this.decorateForParam(criteria, "username", params.getUsername());

        // cust id
        this.decorateForParam(criteria, "custId", params.getCustId());
        
        // proj id
        this.decorateForParam(criteria, "projId", params.getProjId());
        
        // token
        this.decorateForParam(criteria, "token", params.getToken());

        // active
        this.decorateForParam(criteria, "active", params.getActiveSetting());

        // issued date
        this.decorateRange(criteria, "issued", params.getIssuedRangeStart(),
                params.getIssuedRangeEnd());

        // revoke date
        this.decorateRange(criteria, "revoked", params.getRevokedRangeStart(),
                params.getRevokedRangeEnd());

        log.info("criteria is " + criteria);
    }

}
