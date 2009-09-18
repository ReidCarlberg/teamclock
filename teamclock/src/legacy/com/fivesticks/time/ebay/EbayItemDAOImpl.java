/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fivesticks.time.common.ParamValidator;

/**
 * 
 * @author REID
 */
public class EbayItemDAOImpl extends HibernateDaoSupport {

    /*
     * RSC 2005-08-13 SHOULD BE OBSOLETE
     */
    public EbayItemDAOImpl() {
        super();
        throw new RuntimeException("this should be used anywhere");
    }
    /*
     * 2005-08-10 RSC this is deprecated in favor of the generic.
     */
    public static final String SPRING_BEAN_NAME = "ebayItemDao";
    
    public EbayItem save(EbayItem ebayItem) {
        this.getHibernateTemplate().saveOrUpdate(ebayItem);
        return ebayItem;
    }
    
    public EbayItem get(Long id) {
        EbayItem ret = (EbayItem) this.getHibernateTemplate().get(EbayItem.class, id);
        return ret;
    }
    
    public void delete(EbayItem ebayItem) {
        this.getHibernateTemplate().delete(ebayItem);
    }
    
    public Collection find(final EbayItemFilter params) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(EbayItem.class);

                //owner key
                if (ParamValidator.isSearchable(params.getOwnerKey())) {
                    criteria.add(Expression.eq("ownerKey", params.getOwnerKey()));
                }
                //id
                if (ParamValidator.isSearchable(params.getId())) {
                    criteria.add(Expression.eq("id", params.getId()));
                }

                //project id
                if (ParamValidator.isSearchable(params.getCustId())) {
                    criteria.add(Expression.eq("custId", params
                            .getCustId()));
                } 

                //status
                if (ParamValidator.isSearchable(params.getItemStatus())) {
                    criteria.add(Expression.eq("itemStatus", params
                            .getItemStatus()));
                }  
                
                //excluded -- used for excluded types.
                if (params.getExcluded() != null) {
                    criteria.add(Expression.not(Expression.in("itemStatus", params.getExcluded())));
                }
                

                
                List ret = criteria.list();

                return ret;
            }
        });        
    }
}
