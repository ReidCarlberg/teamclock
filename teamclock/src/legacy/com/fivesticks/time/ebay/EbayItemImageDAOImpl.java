/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fivesticks.time.common.ParamValidator;

/**
 * @author REID
 */
public class EbayItemImageDAOImpl extends HibernateDaoSupport {

    public static final String SPRING_BEAN_NAME = "ebayItemImageDAO";

    public EbayItemImage save(EbayItemImage ebayItemImage) {
        this.getHibernateTemplate().saveOrUpdate(ebayItemImage);
        return ebayItemImage;
    }
    
    public void save(Collection all) {
        for (Iterator iter = all.iterator(); iter.hasNext();) {
            EbayItemImage element = (EbayItemImage) iter.next();
            this.save(element);
            
        }
    }

    public EbayItemImage get(Long id) {
        return (EbayItemImage) this.getHibernateTemplate().get(
                EbayItemImage.class, id);
    }

    public void delete(EbayItemImage ebayItemImage) {
        this.getHibernateTemplate().delete(ebayItemImage);
    }

    public Collection find(final EbayItemImageFilter params) {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(this
                .getSessionFactory());

        return hibernateTemplate.executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(EbayItemImage.class);

                //owner key
                if (ParamValidator.isSearchable(params.getOwnerKey())) {
                    criteria.add(Expression
                            .eq("ownerKey", params.getOwnerKey()));
                }
                //id
                if (ParamValidator.isSearchable(params.getId())) {
                    criteria.add(Expression.eq("id", params.getId()));
                }

                //project id
                if (ParamValidator.isSearchable(params.getEbayId())) {
                    criteria.add(Expression.eq("ebayId", params.getEbayId()));
                }

                
                //sort
                if (params.getSortSequenceDesc() != null
                        && params.getSortSequenceDesc().booleanValue()) {
                    criteria.addOrder(Order.desc("sequence"));
                } else {
                    criteria.addOrder(Order.asc("sequence"));
                }
                
                

                List ret = criteria.list();

                return ret;
            }
        });
    }

}