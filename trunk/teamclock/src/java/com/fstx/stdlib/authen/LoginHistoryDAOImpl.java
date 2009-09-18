/*

 Five Sticks
 6957 W. North Ave., #202
 Chicago, IL 60302
 USA
 http://www.fivesticks.com
 mailto:info@fivesticks.com

 Copyright (c) 2003-2004, Five Sticks Publications, Inc.
 All rights reserved.

 Redistribution and use in source and binary forms, 
 with or without modification, are permitted provided
 that the following conditions are met:

 * Redistributions of source code must retain 
 the above copyright notice, this list of conditions 
 and the following disclaimer.
 * Redistributions in binary form must reproduce 
 the above copyright notice, this list of conditions 
 and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of the Five Sticks Publications, Inc.,
 nor the names of its contributors may be used to 
 endorse or promote products derived from this software 
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 SUCH DAMAGE.

 license: http://www.opensource.org/licenses/bsd-license.php

 This software uses a variety of Open Source software as
 a foundation.  See the file 

 [your install]/WEB-INF/component-acknowledgement.txt
 
 For more information.
 */

package com.fstx.stdlib.authen;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fstx.stdlib.common.DAOException;

/**
 * @author Nick
 *  
 */
public class LoginHistoryDAOImpl extends HibernateDaoSupport implements
        LoginHistoryDAO {

    /**
     * @param params
     * @return
     */
    public Collection find(final LoginHistoryFilterParameters params)
            throws DAOException {

        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return (List) hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(LoginHistory.class);

                if (params.getUsername() != null
                        && params.getUsername().length() > 0) {
                    criteria.add(Expression
                            .eq("username", params.getUsername()));
                }

                if (params.getOwnerKeyForSuperUser() != null
                        && params.getOwnerKeyForSuperUser().length() > 0) {
                    criteria.add(Expression.eq("ownerKey", params
                            .getOwnerKeyForSuperUser()));
                } else if (params.getOwnerKey() != null) {
                    criteria.add(Expression
                            .eq("ownerKey", params.getOwnerKey()));
                }

                if (params.getType() != null) {
                    criteria.add(Expression.eq("type", params.getType()));
                }
                if (params.getDateFrom() != null && params.getDateTo() != null) {
                    criteria.add(Expression.between("timestamp", params
                            .getDateFrom(), params.getDateTo()));
                    //log.info("Using a between!");
                } else {
                    //log.info("Not Using a between!");
                    if (params.getDateFrom() != null) {
                        criteria.add(Expression.ge("timestamp", params
                                .getDateFrom()));
                    }
                    if (params.getDateTo() != null) {
                        criteria.add(Expression.le("timestamp", params
                                .getDateTo()));

                    }
                }

                List ret = criteria.list();

                return ret;
            }
        });

    }

    /**
     * @param lh
     */
    public LoginHistory save(LoginHistory target) throws DAOException {

        this.getHibernateTemplate().saveOrUpdate(
                target);
        
        return target;

    }

    public void delete(LoginHistory element) {
        this.getHibernateTemplate().delete(element);
    }

}